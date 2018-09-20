package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudRoleMenu;

public interface MKCloudRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudRoleMenu record);

    int insertSelective(MKCloudRoleMenu record);

    MKCloudRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudRoleMenu record);

    int updateByPrimaryKey(MKCloudRoleMenu record);

    int updateStatus(MKCloudRoleMenu record);
}