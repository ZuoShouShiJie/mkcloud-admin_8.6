package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudInstitutionAtt;

public interface MKCloudInstitutionAttMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudInstitutionAtt record);

    int insertSelective(MKCloudInstitutionAtt record);

    MKCloudInstitutionAtt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudInstitutionAtt record);

    int updateByPrimaryKey(MKCloudInstitutionAtt record);
}