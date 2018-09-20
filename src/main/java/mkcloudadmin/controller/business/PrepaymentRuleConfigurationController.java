package mkcloudadmin.controller.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudAdvancePaymentPlan;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.service.business.AdvancePaymentPlanService;
import mkcloudadmin.service.business.PrepaymentRuleConfigurationService;
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
@RequestMapping("/PrepaymentRuleConfiguration")
public class PrepaymentRuleConfigurationController {
    private static Logger logger = LoggerFactory.getLogger(BusinessPeopleController.class);

    @Autowired
    private PrepaymentRuleConfigurationService prepaymentRuleConfigurationService;

    @Autowired
    private AdvancePaymentPlanService advancePaymentPlanService;

    /**
     * 用款记录
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/selectByPrepaymentRuleList")
    @ResponseBody
    public Object selectWchatTel(HttpServletRequest request/*, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize*/){
/*        Page<MKCloudAdvancePaymentPlan> cardInfoPage = new Page<MKCloudAdvancePaymentPlan>(pageSize,pageNum.longValue());*/
        Map<String,Object> map = advancePaymentPlanService.selectByPayMentPlan();
        map.put("code",0);
        map.put("msg", "");
        map.put("data",map.get("configurationList"));
        return map;
    }

    /**
     * 成交明细查询
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/applicationImportDetailList")
    @ResponseBody
    public Object selectApplicationImportDetail(HttpServletRequest request,
                                                  @RequestParam("page") Integer pageNum,
                                                  @RequestParam("limit") Integer pageSize,
                                                  @RequestParam("beginDate") String beginDate,
                                                  @RequestParam("endDate")String endDate){
              Page<MKCloudBankImportDetailVO> cardInfoPage = new Page<MKCloudBankImportDetailVO>(pageSize,pageNum.longValue());
        Map<String,Object> map = prepaymentRuleConfigurationService.selectApplicationImportDetail(cardInfoPage,beginDate,endDate);
        map.put("code",0);
        map.put("msg", "");
        map.put("count",map.get("count"));
        map.put("data",map.get("detailsList"));
        return map;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/selectPrepaymentRuleConfiguration")
    @ResponseBody
    public Object selectWchatTel(HttpServletRequest request,
                                 @Param("merchantCode") String merchantCode,
                                 @Param("merchantName") String merchantName){

        Map<String,Object> map = prepaymentRuleConfigurationService.selectPrepaymentRuleConfiguration(merchantCode,merchantName);
        map.put("code",0);
        map.put("msg", "");
        /* map.put("count",map.get("totalCount"));*/
        map.put("data",map.get("configurationList"));
        return map;
    }
    /**
     * 新增/修改结算配置
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addPrepaymentRuleConfiguration")
    @ResponseBody
    public Map<String, Object>addBusinessPeopleIn(@RequestParam Map<String, String> param) throws Exception {
        Map<String, Object>  res = new HashMap<>();
        String resString= prepaymentRuleConfigurationService.addPrepaymentRuleConfiguration(param);
        if(!StringUtils.isEmpty(resString) && !"".equals(resString)){
            res.put("code",1);
            res.put("msg", resString);
        }else {
            res.put("code",0);
            res.put("msg", "成功");
        }
        return  res;
    }
    /**
     * 计算方式
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/selectDataDictionary")
    @ResponseBody
    public Object selectDataDictionary(HttpServletRequest request/*, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize*/){
        /*        Page<MKCloudAdvancePaymentPlan> cardInfoPage = new Page<MKCloudAdvancePaymentPlan>(pageSize,pageNum.longValue());*/
        Map<String,Object> map = advancePaymentPlanService.selectDataDictionary();
        map.put("code",0);
        map.put("msg", "");
        map.put("data",map.get("dictionaryList"));
        return map;
    }

}
