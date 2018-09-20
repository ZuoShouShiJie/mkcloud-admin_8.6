package mkcloudadmin.controller.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import mkcloudadmin.model.mkcloud.vo.MKCloudBusinessPeopleDetailVo;
import mkcloudadmin.service.business.BusinessPeopleService;
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
@RequestMapping("/businessPeople")
public class BusinessPeopleController {
    private static Logger logger = LoggerFactory.getLogger(BusinessPeopleController.class);
    @Autowired
    private BusinessPeopleService businessPeopleService;

    /**
     * (后管)
     * 根据businessPeopleCode查看查看推广人员信息
     * @param businessPeopleCode
     * @return
     */
    @RequestMapping("/selectPeopleCode")
    @ResponseBody
    public Map<String, Object> selectPeopleCode(@RequestParam(value = "businessPeopleCode", required = true) String businessPeopleCode) throws Exception {
        Map<String, Object>  res = new HashMap<>();
        MKCloudBusinessPeopleDetailVo peopleDetailInfo = businessPeopleService.selectPeopleCode(businessPeopleCode);
        res.put("data",peopleDetailInfo);
        return res;
    }
    /**
     * (后管)
     * 根据条件查询推广人员信息
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/selectQueryByCriteriaPeople")
    @ResponseBody
    public Object selectQueryByCriteriaPeople(HttpServletRequest request,
                                              @RequestParam("page") Integer pageNum,
                                              @RequestParam("limit") Integer pageSize,
                                              @Param("businessPeopleType") String businessPeopleType,
                                              @Param("businessPeopleCode") String businessPeopleCode,
                                              @Param("businessPeopleName") String businessPeopleName,
                                              @Param("tel") String tel,
                                              @Param("state") String state) throws Exception {
        Map<String, Object>  res = new HashMap<>();
        Page<MKCloudBusinessPeople> cardInfoPage = new Page<MKCloudBusinessPeople>(pageSize,pageNum.longValue());

        Map<String,Object> map = businessPeopleService.selectQueryByCriteriaPeople(cardInfoPage,businessPeopleType,businessPeopleCode,businessPeopleName,tel,state);

        res.put("code",0);
        res.put("msg", "");
        res.put("count",map.get("count"));
        res.put("data",map.get("peoPleDetailList"));
        return res;
    }
    /**
     * 新增/修改内部推广人员
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addBusinessPeopleIn")
    @ResponseBody
    public Map<String, Object>addBusinessPeopleIn(@RequestParam Map<String, String> param) throws Exception {
        Map<String, Object>  res = new HashMap<>();
        if("add".equals(param.get("opMethod"))){
             String resString= businessPeopleService.addBusinessPeopleIn(param);
            if(!StringUtils.isEmpty(resString) && !"".equals(resString)){
                res.put("code",1);
                res.put("msg", resString);
            }else {
                res.put("code",0);
                res.put("msg", "成功");
            }
            return  res;
        }else{
            String resString= businessPeopleService.updateBusinessPeopleIn(param);
            if(!StringUtils.isEmpty(resString) && !"".equals(resString)){
                res.put("code",1);
                res.put("msg", resString);
            }else {
                System.out.println("888*********************************************************************************************"+resString);
                res.put("code",0);
                res.put("msg", "成功");
            }
            return  res;
        }
    }

    /**
     * 外部推广人审核，审核外部推广人，状态1为通过0为拒绝
     * @param state
     * @param businessPeopleCode
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/updateByState")
    public Map<String, Object> updateBState(
            @RequestParam(value = "state") String  state,
            @RequestParam(value = "businessPeopleCode", required = true) String  businessPeopleCode
            ) {
        businessPeopleService.updateByStatic(state,businessPeopleCode);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "审核成功");
        return res;
    }
}
