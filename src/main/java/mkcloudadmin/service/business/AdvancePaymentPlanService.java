package mkcloudadmin.service.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudAdvancePaymentPlan;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import mkcloudadmin.model.mkcloud.vo.MKCloudAdvancePaymentPlanVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBusinessPeopleDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 预付款计划管理
 */
public interface AdvancePaymentPlanService {
    /**
     * 预付款金额明细查询及预付款金额明细查询
     * @return
     */
    Map<String,Object> selectByPayMentPlan();


    /**
     * 预付款流水维护
     * @return
     */
    Map<String,Object> selectWaterMaintenance(String code,String name);

    /**
     * 维护预付款流水
     * @param map
     * @return
     */
    String  addPayMentPlan(Map<String, String> map);
/**
 * 查询结算方式
 *
 */
    Map<String,Object> selectDataDictionary();
}
