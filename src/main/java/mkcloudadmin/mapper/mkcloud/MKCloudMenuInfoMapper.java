package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudMenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MKCloudMenuInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudMenuInfo record);

    int insertSelective(MKCloudMenuInfo record);

    MKCloudMenuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudMenuInfo record);

    int updateByPrimaryKey(MKCloudMenuInfo record);

    List<MKCloudMenuInfo> queryMenuInfo(MKCloudMenuInfo record);


    List<MKCloudMenuInfo> queryMenuByRoleId(MKCloudMenuInfo record);

    List<MKCloudMenuInfo> queryFirstMenu(MKCloudMenuInfo record);

    List<MKCloudMenuInfo> queryParentMenu(@Param("orgCode") String orgCode,@Param("menuIdList") List<String> menuIdList);


    List<MKCloudMenuInfo> queryUserMenu(MKCloudMenuInfo record);

}