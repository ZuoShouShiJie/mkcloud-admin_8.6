package mkcloudadmin.controller.business;

import mkcloudadmin.model.mkcloud.po.MKCloudCommercialTenant;
import mkcloudadmin.service.business.AdvancePaymentPlanService;
import mkcloudadmin.service.business.CommercialTenantService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/commercialTenant")
public class CommercialTenantController {
    private static Logger logger = LoggerFactory.getLogger(BusinessPeopleController.class);

    @Autowired
    private CommercialTenantService commercialTenantService;

    /**
     *
     * 查询所有商户信息
     * @param
     * @return
     */
   @RequestMapping(method = RequestMethod.POST, path = "/selectByCommercialTenantList")
    @ResponseBody
    public Object selectByCommercialTenantList(HttpServletRequest request,
                                               @Param("merchantCode")  String  merchantCode,
                                               @Param("merchantName") String merchantName ,
                                               @Param("cooperationTimeBegin") String  cooperationTimeBegin,
                                               @Param("cooperationTimeEnd") String cooperationTimeEnd ,
                                               @Param("cooperativeState") String cooperativeState){


       Map<String,Object> map = commercialTenantService.selectByCommercialTenantList(merchantCode,merchantName,cooperationTimeBegin,cooperationTimeEnd,cooperativeState);
       map.put("code",0);
       map.put("msg", "");
       map.put("data",map.get("tenantList"));
       return map;
    }

    /**
     * 新增/修改商户信息
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addCommercialTenant")
    @ResponseBody
    public Map<String, Object>addBusinessPeopleIn(@RequestParam Map<String, String> param) throws Exception {
        Map<String, Object>  res = new HashMap<>();
        if("add".equals(param.get("opMethod"))){
            String resString= commercialTenantService.addCommercialTenant(param);
            if(!StringUtils.isEmpty(resString) && !"".equals(resString)){
                res.put("code",1);
                res.put("msg", resString);
            }else {
                res.put("code",0);
                res.put("msg", "成功");
            }
            return  res;
        }else{
            String resString= commercialTenantService.updateCommercialTenant(param);
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
}
