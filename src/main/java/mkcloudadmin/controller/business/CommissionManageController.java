package mkcloudadmin.controller.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.CommissionConfirmQueryDTO;
import mkcloudadmin.model.mkcloud.dto.CommissionDetailQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;
import mkcloudadmin.service.commissionmanage.impl.CommissionManageImpl;
import mkcloudadmin.util.LogUtil;
import org.springframework.stereotype.Controller;
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
 * @description: 佣金管理
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-30 10:06
 **/
@Controller
@RequestMapping("commissionManage")
public class CommissionManageController {
    @Resource
    private CommissionManageImpl commissionManageImpl;
    /**
      *功能描述: 佣金确认查询
      * @author: moruihai
      * @date: 2018/7/31 16:43
      * @param: [pageNum, pageSize, commissionConfirmQueryDTO]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryCommissionManageData")
    public Map<String, Object> queryCommissionManageData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                         CommissionConfirmQueryDTO commissionConfirmQueryDTO) {


        Map<String, Object>  res = new HashMap<>();

        Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage = new Page<MKCloudCommissionDetail>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudCommissionManageDataMap = commissionManageImpl.queryCommissionManageData(commissionConfirmQueryDTO,mkCloudCommissionDetailPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudCommissionManageDataMap.get("count"));
        res.put("data",mkCloudCommissionManageDataMap.get("data"));
        return res;
    }
    /**
      *功能描述:佣金确认明细查询
      * @author: moruihai
      * @date: 2018/7/31 18:19
      * @param: [pageNum, pageSize, commissionConfirmQueryDTO]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryCommissionManageDetail")
    public Map<String, Object> queryCommissionManageDetail(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                           CommissionConfirmQueryDTO commissionConfirmQueryDTO) {

        //@RequestParam(value = "method", required = false) String method, CommissionConfirmQueryDTO commissionConfirmQueryDTO


        Map<String, Object>  res = new HashMap<>();

        Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage = new Page<MKCloudCommissionDetail>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudCommissionManageDataMap = commissionManageImpl.queryCommissionManageDetail(commissionConfirmQueryDTO,mkCloudCommissionDetailPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudCommissionManageDataMap.get("count"));

        res.put("data",mkCloudCommissionManageDataMap.get("data"));
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/handleCommissionManage")
    public Map<String, Object> handleCommissionManage(CommissionConfirmQueryDTO commissionConfirmQueryDTO) {

        commissionManageImpl.handleCommissionManage(commissionConfirmQueryDTO);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "成功");
        return res;
    }

    /**
      *功能描述:分佣明细页面查询功能
      * @author: moruihai
      * @date: 2018/8/1 9:29
      * @param: [pageNum, pageSize, commissionDetailQueryDTO]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryCommissionSearchDetailData")
    public Map<String, Object> queryCommissionSearchDetailData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                               CommissionDetailQueryDTO commissionDetailQueryDTO) {


        Map<String, Object>  res = new HashMap<>();

        Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage = new Page<MKCloudCommissionDetail>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudCommissionSearchDetailMap = commissionManageImpl.queryCommissionSearchDetailData(commissionDetailQueryDTO,mkCloudCommissionDetailPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudCommissionSearchDetailMap.get("count"));

        res.put("data",mkCloudCommissionSearchDetailMap.get("data"));
        return res;
    }



}
