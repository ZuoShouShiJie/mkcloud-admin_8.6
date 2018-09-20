package mkcloudadmin.controller.business.bankfeedback;


import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportTotal;
import mkcloudadmin.service.bankfeedback.impl.BankFeedBackServiceImpl;
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
import java.util.*;

@Controller
@RequestMapping("feedBackInfoImport")
public class BankFeedBackController extends BaseApi {

    private static final Logger logger = LoggerFactory.getLogger(BankFeedBackController.class);
    @Resource
    private BankFeedBackServiceImpl bankFeedBackService;


    @RequestMapping(path = "/excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> excelUpload(MultipartFile file, HttpServletRequest request) throws Exception {

        Map<String, Object>  res = new HashMap<>();
        res.put("msg", "解析失败");
        long start = System.currentTimeMillis();
        logger.info("#excelUpload--time={}",start);
        try {
            String resString = bankFeedBackService.handleExcel(file);
            res.put("msg", "解析成功");
            if(!StringUtils.isEmpty(resString)){
                res.put("msg", resString);
            }
            return res;
        } catch (Exception e) {
            return res;
        }finally {
            long end = System.currentTimeMillis();
            logger.info("#excelUpload--end={},diff={}",end,end-start);
        }
    }

    /**
     *
     * @param pageNum 第几页，不能为 null，并且只能时数字
     * @param pageSize 每页数据量，不能为 null，并且只能时数字
     * @param method 可为 null
     * @return
     * @author moruihai
     * @version , v1.0 2018年7月10日
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryfeedBackInfoImportData")
    public Map<String, Object> queryfeedBackInfoImportData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                         @RequestParam(value = "method", required = false) String method) {


        Map<String, Object>  res = new HashMap<>();
        Page<MKCloudBankImportTotal> mkCloudBankImportTotalPage = new Page<MKCloudBankImportTotal>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudBankImportTotalMap = bankFeedBackService.queryFeedbackBankTotalList(method,mkCloudBankImportTotalPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudBankImportTotalMap.get("count"));

        res.put("data",mkCloudBankImportTotalMap.get("data"));
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryfeedBackInfoImportDetail")
    public Map<String, Object> queryfeedBackInfoImportDetail(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                           @RequestParam(value = "method", required = false) String method) {


        Map<String, Object>  res = new HashMap<>();
        Page<MKCloudBankImportDetail> mkCloudBankImportDetailPage = new Page<MKCloudBankImportDetail>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudBankImportDetailMap = bankFeedBackService.queryFeedbackBankDetailList(method,mkCloudBankImportDetailPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudBankImportDetailMap.get("count"));

        res.put("data",mkCloudBankImportDetailMap.get("data"));
        return res;
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/delFeedBackInfoImportData")
    public Map<String, Object> delFeedBackInfoImportData(@RequestParam(value = "batchId", required = true) String batchId) {
        logger.info(LogUtil.getFormatLog("batchId"+batchId,"请求入参"));
        bankFeedBackService.delFeedBackInfoImportDataByBatchId(batchId);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "删除成功");
        return res;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/handleFeedBackInfo")
    public Map<String, Object> handleFeedBackInfo(@RequestParam(value = "batchId", required = true) String batchId) {
        logger.info(LogUtil.getFormatLog("batchId"+batchId,"请求入参"));
        String resString = bankFeedBackService.handleFeedBackInfo(batchId);

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


}
