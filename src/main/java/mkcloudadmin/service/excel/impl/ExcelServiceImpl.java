package mkcloudadmin.service.excel.impl;

import com.alibaba.fastjson.JSON;
import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.ExcelDetailStatus;
import mkcloudadmin.model.base.OrderStatus;
import mkcloudadmin.model.base.OrderType;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.service.excel.ExcelService;
import mkcloudadmin.service.user.UserInfoService;
import mkcloudadmin.util.ArithUtil;
import mkcloudadmin.util.ParseExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yaolei
 * @Title: ExcelBizImpl
 * @ProjectName mkcloud-app
 * @Description: excel操作
 * @date 2018/7/6下午1:52
 */
@Component
public class ExcelServiceImpl implements ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);


    @Value("${threadPool.coreNum}")
    private int threadPoolSize;

    @Value("${threadPool.maxNum}")
    private int maximumPoolSize;

    private static ExecutorService executorService;

    private CompletionService<FinanceCalculateMoney> completionService;

    @Autowired
    public FinanceExcelAllMapper allMapper;

    @Autowired
    public FinanceExcelDetailMapper detailMapper;

    @Autowired
    public FinanceProfitMapper profitMapper;

//    @Autowired
//    public FinanceProductImpl financeProductService;

    @Autowired
    public FinanceUserAccountMapper accountMapper;

    @Autowired
    public FinanceOrderMapper orderMapper;

    @Autowired
    public UserInfoService userInfoService;

    @Autowired
    private TransactionTemplate transactionTemplate;


    @PostConstruct
    public void init() {
        executorService = new ThreadPoolExecutor(
                threadPoolSize, maximumPoolSize, 30000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue());
        completionService = new ExecutorCompletionService<FinanceCalculateMoney>(executorService);
    }


    @Override
    public void handleExcel(MultipartFile file) throws Exception {

        Workbook wb = ParseExcelUtil.readExcel(file);

        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            int rownum = sheet.getPhysicalNumberOfRows();
            String batchNo = null;
            Long allId = 0l;
            int sum = 0;
            FinanceExcelAllPage excelAll = null;
            String cell = null;
            Row row = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells()-1;
            for (int i = 1; i < rownum; i++) {
                FinanceExcelDetail detail = new FinanceExcelDetail();
                row = sheet.getRow(i);
                int j = 0;
                try {
                    if (row != null) {
                        for (; j < colnum; j++) {
                            if (i == 1 && j == 0) {
                                    excelAll = new FinanceExcelAllPage();
                                    batchNo = (String) ParseExcelUtil.getCellFormatValue(row.getCell(j));
                                    excelAll.setBatchNo(batchNo);
                                    excelAll.setCreater("admin");
                                    excelAll.setDetailsNum(new Long(rownum)-1);
                                    excelAll.setStatus("00");//初始化
                                    saveExcelAll(excelAll);
                                    allId = excelAll.getId();
                            }
                            //根据单元格处理对应的bean信息
                            detail.setAllId(String.valueOf(allId));
                            Object o = ParseExcelUtil.getCellFormatValue(row.getCell(j));
                            cell = o == null ? null : (String)o;

                            switch (j) {
                                case 0:
                                    break;
                                case 1:
                                    detail.setProdName(cell == null ? null:cell.trim());
                                    break;
                                case 2:
                                    detail.setUserName(cell == null ? null:cell.trim());
                                    break;
                                case 3:
                                    detail.setMobile(cell == null ? null:cell.trim());
                                    break;
                                case 4:
                                    detail.setMoney(new BigDecimal(cell == null ? "0":cell.trim()));
                                    break;
                                case 5:
                                    detail.setParentID(cell == null ? null:cell.trim());
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        break;
                    }
                    sum++;
                    completionService.submit(new Task(detail));
                }catch (DuplicateKeyException e){
                    throw e;
                }catch (Exception e) {
                    logger.info("#导入excel--i={}，j={}出错",i,j);
                    FinanceExcelAllPage all = new FinanceExcelAllPage();
                    all.setId(allId);
                    all.setFailNum(1l);
                    logger.info("#updateExcelAll--all={},i={}",JSON.toJSONString(all),updateExcelAll(all));
                    detail.setErrorMessge("解析异常第"+i+"行，"+j+"列出问题");
                    detail.setStatus(ExcelDetailStatus.parseDataError.getCode());//产品信息不存在
                    detailMapper.insertSelective(detail);
                }

            }
            if(sum > 0){
                postCalculateMoney(completionService,allId,sum);
            }
        }

    }



    /**
     *
     * 内部类主要处理商品匹配，计算金额，插入明细，插入分润表
     *
     */
    private class Task implements Callable<FinanceCalculateMoney> {

        public FinanceExcelDetail detail;

        public Task(FinanceExcelDetail d) {
           this.detail = d;
        }


        @Override
        public FinanceCalculateMoney call() throws Exception {

            FinanceCalculateMoney financeCalculateMoney = new FinanceCalculateMoney();
            FinanceProductMain productMain = null;
            List<Long>  userIds = null;
            Map<String, Object> resMap = null;
            try {

                if(detail.getParentID() == null || "".equals(detail.getParentID())){
                    userIds = userInfoService.matchUser(detail.getUserName(),detail.getMobile(),new BigDecimal(detail.getProdName()).longValue(),new Date(),7);
                }else{
                    userIds = userInfoService.matchUser(detail.getUserName(),detail.getMobile(),new BigDecimal(detail.getParentID()).longValue(),new Date(),7);
                }

                //查询产品信息
//                productMain = financeProductService.selectFinanceProductByPrimaryKey(new BigDecimal(detail.getProdName()).longValue());
                financeCalculateMoney.setStatus(ExcelDetailStatus.success.getCode());//状态正常
                detail.setStatus(ExcelDetailStatus.success.getCode());//状态正常

                if(productMain == null){
                    detail.setErrorMessge("未查到"+detail.getProdName()+"产品信息");
                    detail.setStatus(ExcelDetailStatus.noProduct.getCode());//产品信息不存在
                    financeCalculateMoney.setStatus(ExcelDetailStatus.noProduct.getCode());//产品信息不存在
                    detailMapper.insertSelective(detail);
                    return financeCalculateMoney;
                }

                //查询用户信息
                if(userIds == null || userIds.size() <=0){
                    detail.setErrorMessge("未查到"+detail.getMobile()+"&"+detail.getUserName()+"用户信息");
                    detail.setStatus(ExcelDetailStatus.noUserInfo.getCode());//用户信息不存在
                    financeCalculateMoney.setStatus(ExcelDetailStatus.noUserInfo.getCode());//未查到用户信息
                    detailMapper.insertSelective(detail);
                    return financeCalculateMoney;
                }

                if(userIds != null && userIds.size() >= 2){
                    detail.setErrorMessge("查到"+detail.getMobile()+"&"+detail.getUserName()+"多条用户匹配信息");
                    detail.setStatus(ExcelDetailStatus.moreUserInfo.getCode());//查到多条匹配信息
                    financeCalculateMoney.setStatus(ExcelDetailStatus.moreUserInfo.getCode());//查到多条匹配信息
                    detailMapper.insertSelective(detail);
                    return financeCalculateMoney;
                }

            } catch (Exception e) {
                logger.info("#查询分润关系,记录明细异常--e={}",e);
                detailMapper.insertSelective(detail);
                financeCalculateMoney.setStatus(ExcelDetailStatus.sysError.getCode());
                return financeCalculateMoney;
            }

            //插入明细信息
            try {
                int i = detailMapper.insertSelective(detail);
                logger.info("#明细插入数据库--detail={},i",JSON.toJSONString(detail),i);
            } catch (Exception e) {
                logger.info("#明细插入数据库异常--e={},detail={}",e,JSON.toJSONString(detail));
                financeCalculateMoney.setStatus(ExcelDetailStatus.dbError.getCode());
                return financeCalculateMoney;
            }

            //查询父级&上上级信息

            FinanceProfit profit = null;
            try {
                resMap = userInfoService.queryInvateInfo(userIds.get(0));
                //开始计算分润信息，计算本人的分润信息
                profit = new FinanceProfit();
                profit.setDetailId(detail.getId());
                profit.setProdId(productMain.getId());
                profit.setProdName(productMain.getProductName());
                profit.setTerminalId(userIds.get(0));
                financeCalculateMoney.setTerminalId(userIds.get(0));
                if(productMain.getAmountType() == 1){
                    profit.setTerminalMoney(productMain.getTerminalBonus());
                }else{
                    profit.setTerminalMoney(ArithUtil.mul(detail.getMoney(),productMain.getTerminalBonus()));
                }
                financeCalculateMoney.setTerminalMoney(profit.getTerminalMoney());
                profit.setTerminalName(String.valueOf(resMap.get("realName")));
                profit.setTerminalPhone(String.valueOf(resMap.get("mobileNum")));
                //计算父级的分润信息
                if(null != resMap.get("parent")){

                    Map<String,Object> parent = (Map<String, Object>) resMap.get("parent");
                    profit.setParentId(Long.valueOf(String.valueOf(parent.get("userId"))));
                    financeCalculateMoney.setParentId(profit.getParentId());
                    profit.setParentName(String.valueOf(parent.get("realName")));
                    profit.setParentPhone(String.valueOf(parent.get("mobileNum")));

                    if(productMain.getAmountType() == 1){
                        profit.setParentMoney(productMain.getDirectBonus());
                    }else{
                        profit.setParentMoney(ArithUtil.mul(detail.getMoney(),productMain.getDirectBonus()));
                    }
                    financeCalculateMoney.setParentMoney(profit.getParentMoney());

                }
                //计算上上级的分润信息
                if(null != resMap.get("grand")){

                    Map<String,Object> parent = (Map<String, Object>) resMap.get("grand");
                    profit.setGrandParentId(Long.valueOf(String.valueOf(parent.get("userId"))));
                    financeCalculateMoney.setGrandParentId(profit.getGrandParentId());
                    profit.setGrandParentName(String.valueOf(parent.get("realName")));
                    profit.setGrandParentPhone(String.valueOf(parent.get("mobileNum")));
                    if(productMain.getAmountType() == 1){
                        profit.setGrandParentMoney(productMain.getIndirectBonus());
                    }else{
                        profit.setGrandParentMoney(ArithUtil.mul(detail.getMoney(),productMain.getIndirectBonus()));
                    }
                    financeCalculateMoney.setGrandParentMoney(profit.getGrandParentMoney());
                }
                profit.setStatus("00");//正常
                logger.info("#call--profit={}",JSON.toJSONString(profit));
                int i = profitMapper.insert(profit);
                logger.info("#call--i={},id={}",i,profit.getId());
            } catch (Exception e) {
                logger.info("#计算分润信息异常--e={},profit={},financeCalculateMoney={}",e,JSON.toJSONString(profit),JSON.toJSONString(financeCalculateMoney));
                financeCalculateMoney.setStatus(ExcelDetailStatus.sysError.getCode());
                return financeCalculateMoney;
            }


            return financeCalculateMoney;
        }
    }

    /**
     * 计算金额后续后续处理，主要是更新账户信息，更新上传excel主表信息，插入流水表
     * @param completionService
     * @param allId
     */

    private void postCalculateMoney(CompletionService<FinanceCalculateMoney> completionService,Long allId,int totalTaskNum){

        long successSum = 0,failSum = 0;
        Map<Long,BigDecimal> userProfit = new HashMap<Long,BigDecimal>();
        for(int j =0;j< totalTaskNum;j++){
            FinanceCalculateMoney financeCalculateMoney =  null;
            try {
                Future<FinanceCalculateMoney> future = completionService.take();
                if(future != null){
                    financeCalculateMoney =  future.get();
                }
                logger.info("#financeCalculateMoney={}",JSON.toJSONString(financeCalculateMoney));
            } catch (InterruptedException e) {
                logger.info("#postCalculateMoney--InterruptedException={}",e);
            } catch (ExecutionException e) {
                logger.info("#postCalculateMoney--ExecutionException={}",e);
            }
            if(financeCalculateMoney == null || !financeCalculateMoney.getStatus().equals(ExcelDetailStatus.success.getCode())){
                failSum++;
            }else{
                if(userProfit.containsKey(financeCalculateMoney.getTerminalId())){
                    userProfit.put(financeCalculateMoney.getTerminalId(), ArithUtil.add(financeCalculateMoney.getTerminalMoney(),userProfit.get(financeCalculateMoney.getTerminalId())));
                }else{
                    userProfit.put(financeCalculateMoney.getTerminalId(),financeCalculateMoney.getTerminalMoney());
                }
                if(financeCalculateMoney.getParentId() != null){
                    if(userProfit.containsKey(financeCalculateMoney.getParentId())){
                        userProfit.put(financeCalculateMoney.getParentId(), ArithUtil.add(financeCalculateMoney.getParentMoney(),userProfit.get(financeCalculateMoney.getParentId())));
                    }else{
                        userProfit.put(financeCalculateMoney.getParentId(),financeCalculateMoney.getParentMoney());
                    }
                }
                if(financeCalculateMoney.getGrandParentId() != null){
                    if(userProfit.containsKey(financeCalculateMoney.getGrandParentId())){
                        userProfit.put(financeCalculateMoney.getGrandParentId(), ArithUtil.add(financeCalculateMoney.getGrandParentMoney(),userProfit.get(financeCalculateMoney.getGrandParentId())));
                    }else{
                        userProfit.put(financeCalculateMoney.getGrandParentId(),financeCalculateMoney.getGrandParentMoney());
                    }
                }
                successSum++;
            }
        }

        try {
            FinanceExcelAllPage all = new FinanceExcelAllPage();
            all.setDetailsValidNum(successSum);
            all.setId(allId);
            all.setFailNum(failSum);
            int i = updateExcelAll(all);
            logger.info("#updateExcelAll--all={},i={}",JSON.toJSONString(all),i);
        } catch (Exception e) {
            logger.info("#更新excel异常--e={}",e);
        }

        userProfit.forEach((key, value) -> {
            try {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        try {
                            updateAccAndSaveOrder(key, value);
                        } catch (Exception e) {
                            logger.info("#updateAccAndSaveOrder--e={}",e);
                            transactionStatus.setRollbackOnly();
                        }
                    }
                });
            } catch (Exception e) {
                logger.info("#更新账户信息&记录流水异常，e={}",e);
            }
        });
    }

    public void updateAccAndSaveOrder(Long key, BigDecimal value) {

        logger.info("#financeCalculateMoney--key={},value={}",key,value);
        FinanceUserAccount acc = new FinanceUserAccount();

        acc.setUserId(key);
        acc.setMoney(value);
        acc.setSumChargeMoney(value);

        int i = accountMapper.increaseMoney(acc);
        logger.info("#更新账户金额--i={},key={}",i,key);
        FinanceOrder order = new FinanceOrder();
        Random rand = new Random();
        int h = rand.nextInt(899) + 100;
        order.setMoney(value);
        order.setSerialId(System.currentTimeMillis()+""+h);
        order.setUserId(key);
        order.setTransType(OrderType.charge.getCode());
        order.setStatus(OrderStatus.rechargeSuccess.getCode());

        i = orderMapper.insertSelective(order);
        logger.info("#更新账户信息&记录，acc={},order={},i={}", JSON.toJSONString(acc),JSON.toJSONString(order),i);
    }

    @Override
    public int saveExcelAll(FinanceExcelAllPage excelAll) {
        return allMapper.insertSelective(excelAll);
    }

    @Override
    public int updateExcelAll(FinanceExcelAllPage excelAll) {
        return allMapper.updateByPrimaryKeySelective(excelAll);
    }

    @Override
    public FinanceExcelAllPage selectExcelById(long id) {
        return allMapper.selectByPrimaryKey(id);
    }

    @Override
    public FinanceExcelAllPage selectExcelsByExcel(FinanceExcelAllPage excelAll) {

        Long total = allMapper.queryExcelCount(excelAll);
        if (total > 0) {
            List list = allMapper.selectExcelsByExcel(excelAll);
            excelAll.setDatas(list);
        }
        excelAll.setTotal(total.intValue());
        return excelAll;
    }
    @Override
    public Workbook createExcel(String  type, String allId) {

        FinanceExcelDetail detail = new FinanceExcelDetail();
        detail.setAllId(allId);
        detail.setStatus(type);
        FinanceExcelAllPage f = allMapper.selectByPrimaryKey(Long.parseLong(allId));

        List<FinanceExcelDetail> list = detailMapper.selectByPrimaryKeyAndStatus(detail);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFFont font=    workbook.createFont();
        font.setFontHeightInPoints((short)12);            //设置字体的大小
        font.setFontName("微软雅黑");                        //设置字体的样式，如：宋体、微软雅黑等
        font.setItalic(false);
        font.setBoldweight((short) (170 * 200));//斜体true为斜体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //对文中进行加粗
        font.setColor(HSSFColor.BLACK.index);            //设置字体的颜色
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        Sheet sheet = workbook.createSheet();
        // 第一行文字说明
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("id");

        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("批次Id");

        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("产品id");

        cell = row.createCell(3, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("用户名");

        cell = row.createCell(4, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("手机号");

        cell = row.createCell(5, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("错误信息");

        cell = row.createCell(6, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        cell = row.createCell(7, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("类型");

        cell = row.createCell(8, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("上传时间");

        if (list != null && list.size() != 0) {
            int length = list.size();
            // 下面是具体内容
            for (int i = 0; i < length; i++) {
                row = sheet.createRow(i + 1);
                // 批次号
                cell = row.createCell(0, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getId());
                // 上传人
                cell = row.createCell(1, cell.CELL_TYPE_STRING);
                cell.setCellValue(f.getBatchNo());

                // 上传总数
                cell = row.createCell(2, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getProdName());

                // 成功数
                cell = row.createCell(3, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getUserName());

                // 失败数

                cell = row.createCell(4, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getMobile());

                // 状态

                cell = row.createCell(5, cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getErrorMessge());

                cell = row.createCell(6, Cell.CELL_TYPE_STRING);
                cell.setCellValue(ExcelDetailStatus.getParam().get(list.get(i).getStatus()));

                cell = row.createCell(7, Cell.CELL_TYPE_STRING);
                cell.setCellValue(list.get(i).getType());

                // 上传时间

                cell = row.createCell(8, cell.CELL_TYPE_STRING);
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getCreateTime()));

            }
        }
        return workbook;
    }

}
