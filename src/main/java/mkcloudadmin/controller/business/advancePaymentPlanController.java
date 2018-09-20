package mkcloudadmin.controller.business;

import mkcloudadmin.job.RecordingImportTimingTasks;
import mkcloudadmin.mapper.mkcloud.MKCloudAdvancePaymentPlanMapper;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudAdvancePaymentPlan;
import mkcloudadmin.model.mkcloud.po.MKCloudCommercialTenant;
import mkcloudadmin.model.mkcloud.vo.MKCloudAdvancePaymentPlanVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.business.AdvancePaymentPlanService;
import mkcloudadmin.service.business.ApplicationImportDetailService;
import mkcloudadmin.util.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/advancePaymentPlan")
public class advancePaymentPlanController {
    private static Logger logger = LoggerFactory.getLogger(BusinessPeopleController.class);

    @Autowired
    private AdvancePaymentPlanService advancePaymentPlanService;

    /**
     *
     * 预付款金额明细查询
     * @param
     * @return
     */
   @RequestMapping(method = RequestMethod.POST, path = "/selectByAdvancePaymentPlan")
    @ResponseBody
    public Object selectByAdjustType(HttpServletRequest request){
       Map<String,Object> map = advancePaymentPlanService.selectByPayMentPlan();
       map.put("code",0);
       map.put("msg", "");
       map.put("data",map.get("planVO"));
       return map;
    }

    /**
     *
     * 预付款金额明细查询
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/selectByPayMentList")
    @ResponseBody
    public Object selectWchatTel(HttpServletRequest request){

        Map<String,Object> map = advancePaymentPlanService.selectByPayMentPlan();
        map.put("code",0);
        map.put("msg", "");
       /* map.put("count",map.get("totalCount"));*/
        map.put("data",map.get("planList"));
        return map;
    }
    /**
     *
     * 预付款金额明细查询
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/waterMaintenance")
    @ResponseBody
    public Object selectWchatTel(HttpServletRequest request,
                                 @Param("merchantCode") String merchantCode,
                                 @Param("merchantName") String merchantName){

        Map<String,Object> map = advancePaymentPlanService.selectWaterMaintenance(merchantCode,merchantName);
        map.put("code",0);
        map.put("msg", "");
        /* map.put("count",map.get("totalCount"));*/
        map.put("data",map.get("planList"));
        return map;
    }
    /**
     * 新增/修改预付款信息
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addAdvancePayMentPlan")
    @ResponseBody
    public Map<String, Object>addBusinessPeopleIn(@RequestParam Map<String, String> param) throws Exception {
        Map<String, Object>  res = new HashMap<>();
            String resString= advancePaymentPlanService.addPayMentPlan(param);
            if(!StringUtils.isEmpty(resString) && !"".equals(resString)){
                res.put("code",1);
                res.put("msg", resString);
            }else {
                res.put("code",0);
                res.put("msg", "成功");
            }
            return  res;
    }
}
