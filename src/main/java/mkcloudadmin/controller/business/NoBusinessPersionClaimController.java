package mkcloudadmin.controller.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.NoBusinessPerSearchDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import mkcloudadmin.service.nobusinesspersionmanage.NoPersionBusinessManage;
import mkcloudadmin.service.nobusinesspersionmanage.impl.NoPersionBusinessManageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 无推广人员匹配操作controller
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-03 15:02
 **/
@Controller
@RequestMapping("noBusinessPersionManage")
public class NoBusinessPersionClaimController {
    @Resource
    private NoPersionBusinessManageImpl noPersionBusinessManage;


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryNoBusinessPersionData")
    public Map<String, Object> queryNoBusinessPersionData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                         NoBusinessPerSearchDTO noBusinessPerSearchDTO) {


        Map<String, Object>  res = new HashMap<>();
        if(StringUtils.isEmpty(noBusinessPerSearchDTO.getApplyName()) && StringUtils.isEmpty(noBusinessPerSearchDTO.getApplyMobile())
                && StringUtils.isEmpty(noBusinessPerSearchDTO.getBeginDate()) && StringUtils.isEmpty(noBusinessPerSearchDTO.getEndDate())){
            res.put("code",0);
            res.put("msg", "");
            res.put("count",null);
            res.put("data",null);
            return res;

        }


        Page<MKCloudApplicationImportDetail> mkCloudApplicationImportDetailPage = new Page<MKCloudApplicationImportDetail>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudCommissionManageDataMap = noPersionBusinessManage.queryNoBusinessPersionData(noBusinessPerSearchDTO,mkCloudApplicationImportDetailPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudCommissionManageDataMap.get("count"));
        res.put("data",mkCloudCommissionManageDataMap.get("data"));
        return res;
    }

    /**
      *功能描述:分派保存
      * @author: moruihai
      * @date: 2018/8/3 16:41
      * @param: [param]
      * @return: java.lang.Boolean
      */
    @RequestMapping(method = RequestMethod.POST, path = "saveDeployBusinessData")
    @ResponseBody
    public Map<String, Object> saveDeployBusinessData(@RequestParam Map<String, String> param) {

        Map<String, Object>  res = new HashMap<>();
        //校验推广号是否存在
        String businessPeopleCode = param.get("businessPeopleCode");

        MKCloudBusinessPeople mkCloudBusinessPeople = noPersionBusinessManage.queryBusinessPeople(businessPeopleCode);
        if (mkCloudBusinessPeople != null){
            Boolean response = noPersionBusinessManage.saveDeployBusinessData(mkCloudBusinessPeople,param);
            res.put("msg", "处理成功！");
        }else {
            res.put("msg", "推广员工号不存在！");

        }
        return res;

      //  Boolean res = financeProductImpl.createFinanceProduct(param);

    }
}
