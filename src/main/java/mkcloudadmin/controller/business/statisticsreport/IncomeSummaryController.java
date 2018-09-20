package mkcloudadmin.controller.business.statisticsreport;
import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.service.statisticsreport.impl.IncomeSummaryServiceImpl;
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
@RequestMapping("incomeSummary")
public class IncomeSummaryController extends BaseApi{

    private static final Logger logger = LoggerFactory.getLogger(IncomeSummaryController.class);

    @Resource
    private IncomeSummaryServiceImpl incomeSummaryService;

    /**
     * 收入汇总
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryIncomeSummary",method = RequestMethod.POST)
    public Map<String,Object> queryIncomeSummary(){
        Map<String , Object> res = new HashMap<>();
        Map<String, Object> incomeMap = incomeSummaryService.queryIncomeCount();
        res.put("code",0);
        res.put("msg","成功");
        res.put("data",incomeMap.get("data"));
        return res;
    }


}
