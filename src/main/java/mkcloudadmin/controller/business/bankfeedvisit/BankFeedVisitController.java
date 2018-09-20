package mkcloudadmin.controller.business.bankfeedvisit;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportBatch;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.service.bankfeedvisit.impl.BankFeedVisitServiceImpl;
import mkcloudadmin.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/7/31.
 */
@Controller
@RequestMapping("feedVisitackInfoImport")
public class BankFeedVisitController extends BaseApi{

    private static final Logger logger = LoggerFactory.getLogger(BankFeedVisitController.class);

    @Resource
    private BankFeedVisitServiceImpl bankFeedVisitService;

    /**
     * 申请批次列表查询
     * @param pageNum 当前页,数字,不能为null
     * @param pageSize 分页单位,数字,不能为null
     * @param method 查询条件,可以为null
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryfeedVisitInfoImportData",method = RequestMethod.POST)
    public Map<String,Object> queryfeedVisitInfoImportData(@RequestParam("page") Integer pageNum,@RequestParam("limit") Integer pageSize,
                                                           @RequestParam(value = "method",required = false) String method){
        Map<String , Object> res = new HashMap<>();
        Page<MKCloudApplicationImportBatch> mkCloudApplicationImportBatchPage = new Page<>(pageSize, pageNum.longValue());
        Map<String,Object> mkCloudBankVisitTotalMap = bankFeedVisitService.queryBankVisitBatchList(method,mkCloudApplicationImportBatchPage);
        res.put("code",0);
        res.put("msg","成功");
        res.put("count",mkCloudBankVisitTotalMap.get("count"));
        res.put("data",mkCloudBankVisitTotalMap.get("data"));
        return res;
    }

    /**
     * excel导入
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(path = "/excelUpload",method = RequestMethod.POST)
    public Map<String,Object> excelUpload(MultipartFile file, HttpServletRequest request) throws Exception{
        Map<String, Object> res = new HashMap<>();
        Long start = System.currentTimeMillis();
        logger.info("#excelUpload--time={}",start);
        try {
            String failmsg = bankFeedVisitService.handleExcel(file);
            res.put("msg","解析成功!");
            if(!StringUtils.isEmpty(failmsg)){
                res.put("msg", failmsg);
            }
            return res;
        } catch (Exception e) {
            res.put("msg","解析失败!");
            return res;
        } finally {
            long end = System.currentTimeMillis();
            logger.info("#excelUpload--end={},diff={}",end,end-start);
        }
    }

    /**
     * 批次明细查询
     * @param pageNum
     * @param pageSize
     * @param method
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryBatchDetailInfo",method = RequestMethod.POST)
    public Map<String,Object> queryBatchDetailInfo(@RequestParam("page") Integer pageNum,@RequestParam("limit") Integer pageSize,
                                                   @RequestParam(value = "method",required = false) String method){
        Map<String , Object> res = new HashMap<>();
        Page<MKCloudApplicationImportDetail> detailPage = new Page<>(pageSize, pageNum.longValue());
        Map<String,Object> detailMap = bankFeedVisitService.queryBatchDetailList(method,detailPage);
        res.put("code",0);
        res.put("msg","成功");
        res.put("count",detailMap.get("count"));
        res.put("data",detailMap.get("data"));
        return res;
    }

    /**
     * 确认批次信息
     * @param batchId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/confirmVisitInfo",method = RequestMethod.POST)
    public Map<String,Object> confirmVisitInfo(@RequestParam(value = "batchId",required = true) String batchId){
        logger.info(LogUtil.getFormatLog("batchId" + batchId,"请求入参"));
        String resString = bankFeedVisitService.confirmVisitInfo(batchId);
        Map<String, Object>  res = new HashMap<>();
        if(!StringUtils.isEmpty(resString)){
            res.put("code",1);
            res.put("msg", resString);
        }else {
            res.put("code",0);
            res.put("msg", "成功");
        }
        return res;
    }

    /**
     * 删除批次
     * @param batchId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delBatchInfo",method = RequestMethod.POST)
    public Map<String,Object> delBatchInfo(@RequestParam(value = "batchId",required = true) String batchId){
        logger.info(LogUtil.getFormatLog("batchId" + batchId,"请求入参"));
        bankFeedVisitService.delBatchInfo(batchId);
        Map<String, Object> res = new HashMap<>();
        res.put("code",0);
        res.put("msg","删除成功");
        return res;
    }

}
