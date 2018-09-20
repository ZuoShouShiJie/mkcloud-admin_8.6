package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudUserRole;

public interface MKCloudUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudUserRole record);

    int insertSelective(MKCloudUserRole record);

    MKCloudUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudUserRole record);

    int updateByPrimaryKey(MKCloudUserRole record);

    int updateStatus(MKCloudUserRole record);
}