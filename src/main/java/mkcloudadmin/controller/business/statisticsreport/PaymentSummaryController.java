package mkcloudadmin.controller.business.statisticsreport;
import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.service.statisticsreport.impl.IncomeSummaryServiceImpl;
import mkcloudadmin.service.statisticsreport.impl.PaymentSummaryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/7/31.
 */
@Controller
@RequestMapping("paymentSummary")
public class PaymentSummaryController extends BaseApi{

    private static final Logger logger = LoggerFactory.getLogger(PaymentSummaryController.class);

    @Resource
    private PaymentSummaryServiceImpl paymentSummaryService;

    /**
     * 付款统计
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryPaymentSummary",method = RequestMethod.POST)
    public Map<String,Object> queryPaymentSummary(){
        Map<String , Object> res = new HashMap<>();
        Map<String,Object> payMap = paymentSummaryService.queryPayCount();
        res.put("code",0);
        res.put("msg","成功");
        res.put("data",payMap.get("data"));
        return res;
    }


}
