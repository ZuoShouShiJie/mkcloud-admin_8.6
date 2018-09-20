package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudRoleInfo;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudRoleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudRoleInfo record);

    int insertSelective(MKCloudRoleInfo record);

    MKCloudRoleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudRoleInfo record);

    int updateByPrimaryKey(MKCloudRoleInfo record);

   List<MKCloudRoleInfo> queryRoleInfo(MKCloudRoleInfo record);

    MKCloudRoleInfo queryRoleByName(MKCloudRoleInfo record);
    List<MKCloudRoleInfo> queryRoleByUserId(MKCloudRoleInfo record);
}