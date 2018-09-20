package mkcloudadmin.controller.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.PaymentQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudPaymentRecord;
import mkcloudadmin.model.mkcloud.vo.MKCloudUnPaidSearchVO;
import mkcloudadmin.service.paymentmanagement.impl.PaymentManageImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 付款管理controller层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-01 11:02
 **/
@Controller
@RequestMapping("paymentManage")
public class PaymentManageController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentManageController.class);
    @Resource
    private PaymentManageImpl paymentManage;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryUnpaidData")
    public Map<String, Object> queryUnpaidData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                         PaymentQueryDTO paymentQueryDTO) {


        Map<String, Object>  res = new HashMap<>();

        Page<MKCloudPaymentRecord> mkCloudPaymentRecordPage = new Page<>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudUnpaidDataMap = paymentManage.queryUnpaidData(paymentQueryDTO,mkCloudPaymentRecordPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudUnpaidDataMap.get("count"));
        res.put("data",mkCloudUnpaidDataMap.get("data"));
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/handleUnpaidCommissionInfo")
    public Map<String, Object> handleUnpaidCommissionInfo(MKCloudUnPaidSearchVO mkCloudUnPaidSearchVO) {

        paymentManage.handleUnpaidCommissionInfo(mkCloudUnPaidSearchVO);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "成功");
        return res;

    }
     /**
       *功能描述:已付款清单查询
       * @author: moruihai
       * @date: 2018/8/1 15:35
       * @param: [pageNum, pageSize, paymentQueryDTO]
       * @return: java.util.Map<java.lang.String,java.lang.Object>
       */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryPaidData")
    public Map<String, Object> queryPaidData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                               PaymentQueryDTO paymentQueryDTO) {


        Map<String, Object>  res = new HashMap<>();

        Page<MKCloudPaymentRecord> mkCloudPaymentRecordPage = new Page<>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudPaidDataMap = paymentManage.queryPaidData(paymentQueryDTO,mkCloudPaymentRecordPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudPaidDataMap.get("count"));
        res.put("data",mkCloudPaidDataMap.get("data"));
        return res;
    }
    /**
      *功能描述: 下载excel
      * @author: moruihai
      * @date: 2018/8/3 11:03
      * @param: [type, allId, response]
      * @return: void
      */
    @RequestMapping(path = "/excelDownlload", method = RequestMethod.GET)
    @ResponseBody
    public void excelDownlload(@RequestParam("payee") String payee, @RequestParam("payeeAccount") String payeeAccount,@RequestParam("costType") String costType,
                               @RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate,HttpServletResponse response) throws Exception {

        HSSFWorkbook wb = (HSSFWorkbook)paymentManage.createExcel(payee,payeeAccount,costType,beginDate,endDate);

        try{
            response.setHeader("Content-Disposition", "attachment; filename=info.xls");
            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        }catch (IOException e) {
            logger.info("#excelDownlload--error={}",e);
        }
    }
}
