package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudPrepaymentRuleConfiguration;

import java.util.List;

public interface MKCloudPrepaymentRuleConfigurationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudPrepaymentRuleConfiguration record);

    int insertSelective(MKCloudPrepaymentRuleConfiguration record);

    MKCloudPrepaymentRuleConfiguration selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudPrepaymentRuleConfiguration record);

    int updateByPrimaryKey(MKCloudPrepaymentRuleConfiguration record);

    List<MKCloudPrepaymentRuleConfiguration> selectByPrepaymentRuleConfiguration();
    List<MKCloudPrepaymentRuleConfiguration>  selectByPrepay();
}