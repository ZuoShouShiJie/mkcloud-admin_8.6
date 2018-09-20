package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudAdvancePaymentPlan;

import java.util.List;

public interface MKCloudAdvancePaymentPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudAdvancePaymentPlan record);

    int insertSelective(MKCloudAdvancePaymentPlan record);

    MKCloudAdvancePaymentPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudAdvancePaymentPlan record);

    int updateByPrimaryKey(MKCloudAdvancePaymentPlan record);
    List<MKCloudAdvancePaymentPlan> selectByPayMentPlan();
    int   selectByPayMentPlanCount();
}
