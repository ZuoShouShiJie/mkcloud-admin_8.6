package mkcloudadmin.controller.business.statisticsreport;
import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.service.statisticsreport.impl.ApplyCountServiceImpl;
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
@RequestMapping("applyStatisticsCount")
public class ApplyStatisticsCountController extends BaseApi{

    private static final Logger logger = LoggerFactory.getLogger(ApplyStatisticsCountController.class);

    @Resource
    private ApplyCountServiceImpl applyCountService;

    /**
     * 申请统计明细
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/applyCount",method = RequestMethod.POST)
    public Map<String,Object> applyCount(){
        Map<String , Object> res = new HashMap<>();
        Map<String , Object> countMap = applyCountService.queryCount();
        res.put("code",0);
        res.put("msg","成功");
        res.put("data",countMap.get("data"));
        return res;
    }


}
