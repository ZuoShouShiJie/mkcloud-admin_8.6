package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeopleDeployLog;

public interface MKCloudBusinessPeopleDeployLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBusinessPeopleDeployLog record);

    int insertSelective(MKCloudBusinessPeopleDeployLog record);

    MKCloudBusinessPeopleDeployLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessPeopleDeployLog record);

    int updateByPrimaryKey(MKCloudBusinessPeopleDeployLog record);
}