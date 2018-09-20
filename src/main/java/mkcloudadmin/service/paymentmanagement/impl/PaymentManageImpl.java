package mkcloudadmin.service.paymentmanagement.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudCommSettlementMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudCommissionDetailMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudPaymentRecordMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.PaymentQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudCommSettlement;
import mkcloudadmin.model.mkcloud.po.MKCloudPaymentRecord;
import mkcloudadmin.model.mkcloud.vo.MKCloudPaidSearchVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudUnPaidSearchVO;
import mkcloudadmin.service.paymentmanagement.PaymentManage;
import mkcloudadmin.util.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 付款管理
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-01 12:39
 **/
@Service
public class PaymentManageImpl implements PaymentManage{
    @Autowired
    private MKCloudPaymentRecordMapper mkCloudPaymentRecordMapper;
    @Autowired
    private MKCloudCommissionDetailMapper mkCloudCommissionDetailMapper;
    @Autowired
    private MKCloudCommSettlementMapper mkCloudCommSettlementMapper;
    @Override
    public Map<String, Object> queryUnpaidData(PaymentQueryDTO paymentQueryDTO, Page<MKCloudPaymentRecord> mkCloudPaymentRecordPage) {
        String payee = paymentQueryDTO.getPayee();
        String payeeAccount = paymentQueryDTO.getPayeeAccount();
        String costType = paymentQueryDTO.getCostType();

        String createTime = paymentQueryDTO.getBeginDate();
        String createTimeTwo = paymentQueryDTO.getEndDate();
        Date beginDate =  StringUtils.isBlank(createTime)?null:(DateUtils.stringToDate(createTime+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date endDate =    StringUtils.isBlank(createTimeTwo)?null:(DateUtils.stringToDate(createTimeTwo+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        costType = (costType==null)?"1":costType;

        Long total = mkCloudPaymentRecordMapper.selectUnpaidDataCount(payee,payeeAccount,beginDate,endDate,costType);
        List<MKCloudUnPaidSearchVO> mkCloudUnPaidSearchVOList = new ArrayList<>();
        if (total>0){
            //根据类型查主表数据
            List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectUnpaidDataList(payee,payeeAccount,beginDate,endDate,costType,mkCloudPaymentRecordPage);

            if (mkCloudPaymentRecordList !=null && mkCloudPaymentRecordList.size()>0){
                MKCloudUnPaidSearchVO mkCloudUnPaidSearchVO = null;
                Long num =1l;
                for(MKCloudPaymentRecord mkc :mkCloudPaymentRecordList){
                    mkCloudUnPaidSearchVO = new MKCloudUnPaidSearchVO();

                    mkCloudUnPaidSearchVO.setCostType("1");
                    mkCloudUnPaidSearchVO.setCostTypeName("佣金");
                    mkCloudUnPaidSearchVO.setPayableAmount(mkc.getPayableAmount());
                    mkCloudUnPaidSearchVO.setPayee(mkc.getPayee());
                    mkCloudUnPaidSearchVO.setPayeeBank(mkc.getPayeeBank());
                    mkCloudUnPaidSearchVO.setPayeeAccount(mkc.getPayeeAccount());
                    mkCloudUnPaidSearchVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                    mkCloudUnPaidSearchVO.setSeqNo(num);

                    mkCloudUnPaidSearchVO.setSettlementId(mkc.getSettlementId());
                    mkCloudUnPaidSearchVO.setId(mkc.getId());
                    num++;
                    mkCloudUnPaidSearchVOList.add(mkCloudUnPaidSearchVO);
                }

            }

        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudUnPaidSearchVOList);

        return resultMap;
    }

    @Override
    @Transactional
    public void handleUnpaidCommissionInfo(MKCloudUnPaidSearchVO mkCloudUnPaidSearchVO) {
        //更新mkcloud_payment_record
        MKCloudPaymentRecord mkCloudPaymentRecord = new MKCloudPaymentRecord();
        mkCloudPaymentRecord.setPaidAmount(mkCloudUnPaidSearchVO.getPayableAmount());
        mkCloudPaymentRecord.setState("1"); //已支付

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String batchCode = "ZF"+sdf.format(new Date())+ RandomStringUtils.randomNumeric(2);
        mkCloudPaymentRecord.setBatchCode(batchCode);
        mkCloudPaymentRecord.setConfirmTime(new Date());
        mkCloudPaymentRecord.setConfirmUser("");
        mkCloudPaymentRecord.setPaidBank(mkCloudUnPaidSearchVO.getPayeeBank());
        mkCloudPaymentRecord.setPaidAcocunt(mkCloudUnPaidSearchVO.getPayeeAccount());
        mkCloudPaymentRecord.setId(mkCloudUnPaidSearchVO.getId());

        mkCloudPaymentRecordMapper.updateByPrimaryKeySelective(mkCloudPaymentRecord);
        //更新mkcloud_comm_settlement
        mkCloudCommSettlementMapper.updateStateBySettlementId(mkCloudUnPaidSearchVO.getSettlementId());

        //更新mkcloud_commission_detail
        mkCloudCommissionDetailMapper.updateStateBySettlementId(mkCloudUnPaidSearchVO.getSettlementId());
    }

    @Override
    public Map<String, Object> queryPaidData(PaymentQueryDTO paymentQueryDTO, Page<MKCloudPaymentRecord> mkCloudPaymentRecordPage) {
        String payee = paymentQueryDTO.getPayee();
        String payeeAccount = paymentQueryDTO.getPayeeAccount();
        String costType = paymentQueryDTO.getCostType();

        String createTime = paymentQueryDTO.getBeginDate();
        String createTimeTwo = paymentQueryDTO.getEndDate();
        Date beginDate =  StringUtils.isBlank(createTime)?null:(DateUtils.stringToDate(createTime+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date endDate =    StringUtils.isBlank(createTimeTwo)?null:(DateUtils.stringToDate(createTimeTwo+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        String confirmBeginDate1 = paymentQueryDTO.getConfirmBeginDate();
        String confirmEndDate1 = paymentQueryDTO.getConfirmEndDate();

        Date confirmBeginDate =StringUtils.isBlank(confirmBeginDate1)?null:(DateUtils.stringToDate(confirmBeginDate1+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date confirmEndDate =    StringUtils.isBlank(confirmEndDate1)?null:(DateUtils.stringToDate(confirmEndDate1+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        costType = (costType==null)?"1":costType;

        Long total = mkCloudPaymentRecordMapper.selectPaidDataCount(payee,payeeAccount,beginDate,endDate,costType,confirmBeginDate,confirmEndDate);
        List<MKCloudPaidSearchVO> mkCloudPaidSearchVOList = new ArrayList<>();
        if (total>0){
            //根据类型查主表数据
            List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectPaidDataList(payee,payeeAccount,beginDate,endDate,costType,
                    confirmBeginDate,confirmEndDate,mkCloudPaymentRecordPage);

            if (mkCloudPaymentRecordList !=null && mkCloudPaymentRecordList.size()>0){
                MKCloudPaidSearchVO mkCloudPaidSearchVO = null;
                Long num =1l;
                for(MKCloudPaymentRecord mkc :mkCloudPaymentRecordList){
                    mkCloudPaidSearchVO = new MKCloudPaidSearchVO();

                    mkCloudPaidSearchVO.setCostType("1");
                    mkCloudPaidSearchVO.setCostTypeName("佣金");
                    mkCloudPaidSearchVO.setPaidAmount(mkc.getPaidAmount());
                    mkCloudPaidSearchVO.setPayee(mkc.getPayee());
                    mkCloudPaidSearchVO.setPayeeBank(mkc.getPayeeBank());
                    mkCloudPaidSearchVO.setPayeeAccount(mkc.getPayeeAccount());
                    mkCloudPaidSearchVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                    mkCloudPaidSearchVO.setConfirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getConfirmTime()));
                    mkCloudPaidSearchVO.setSeqNo(num);
                    num++;
                    mkCloudPaidSearchVOList.add(mkCloudPaidSearchVO);
                }

            }

        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudPaidSearchVOList);

        return resultMap;
    }

    @Override
    public Workbook createExcel(String payee, String payeeAccount, String costType, String beginDate, String endDate) {

        List<MKCloudUnPaidSearchVO> mkCloudUnPaidSearchVOList = new ArrayList<>();

        //根据类型查主表数据
        List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectUnpaidAllDataList(payee,payeeAccount,beginDate,endDate,costType);

        if (mkCloudPaymentRecordList !=null && mkCloudPaymentRecordList.size()>0){
            MKCloudUnPaidSearchVO mkCloudUnPaidSearchVO = null;
            Long num =1l;
            for(MKCloudPaymentRecord mkc :mkCloudPaymentRecordList){
                mkCloudUnPaidSearchVO = new MKCloudUnPaidSearchVO();

                mkCloudUnPaidSearchVO.setCostType("1");
                mkCloudUnPaidSearchVO.setCostTypeName("佣金");
                mkCloudUnPaidSearchVO.setPayableAmount(mkc.getPayableAmount());
                mkCloudUnPaidSearchVO.setPayee(mkc.getPayee());
                mkCloudUnPaidSearchVO.setPayeeBank(mkc.getPayeeBank());
                mkCloudUnPaidSearchVO.setPayeeAccount(mkc.getPayeeAccount());
                mkCloudUnPaidSearchVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                mkCloudUnPaidSearchVO.setSeqNo(num);

                mkCloudUnPaidSearchVO.setSettlementId(mkc.getSettlementId());
                mkCloudUnPaidSearchVO.setId(mkc.getId());
                num++;
                mkCloudUnPaidSearchVOList.add(mkCloudUnPaidSearchVO);
            }

        }

  /*      FinanceExcelDetail detail = new FinanceExcelDetail();
        detail.setAllId(allId);
        detail.setStatus(type);
        FinanceExcelAllPage f = allMapper.selectByPrimaryKey(Long.parseLong(allId));

        List<FinanceExcelDetail> list = detailMapper.selectByPrimaryKeyAndStatus(detail);*/

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
        cell.setCellValue("序号");

        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("费用类型");

        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("收款账户名");

        cell = row.createCell(3, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("收款银行");

        cell = row.createCell(4, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("收款账号");

        cell = row.createCell(5, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("生成时间");

        cell = row.createCell(6, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("待付款金额");


        if (mkCloudUnPaidSearchVOList != null && mkCloudUnPaidSearchVOList.size() != 0) {
            int length = mkCloudUnPaidSearchVOList.size();
            // 下面是具体内容
            for (int i = 0; i < length; i++) {
                row = sheet.createRow(i + 1);
                // 序号
                cell = row.createCell(0, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getSeqNo());
                // 费用类型
                cell = row.createCell(1, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getCostTypeName());

                // 收款账户名
                cell = row.createCell(2, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getPayee());

                // 收款银行
                cell = row.createCell(3, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getPayeeBank());

                // 收款账号

                cell = row.createCell(4, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getPayeeAccount());

                // 生成时间

                cell = row.createCell(5, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getCreateTime());

                // 待付款金额

                cell = row.createCell(6, cell.CELL_TYPE_STRING);
                cell.setCellValue(mkCloudUnPaidSearchVOList.get(i).getPayableAmount().stripTrailingZeros().toPlainString());

            }
        }
        return workbook;
    }
}
