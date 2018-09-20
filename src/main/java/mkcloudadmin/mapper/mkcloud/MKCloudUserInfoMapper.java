package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudUserInfo record);

    int insertSelective(MKCloudUserInfo record);

    MKCloudUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudUserInfo record);

    int updateByPrimaryKey(MKCloudUserInfo record);

    MKCloudUserInfo queryOperatorByNamePa(MKCloudUserInfo record);

    Long queryOperatorInfoCount(@Param("id") Long id,@Param("userLoginName") String userLoginName,@Param("status") String status,@Param("orgCode") String orgCode);
    List<MKCloudUserInfo> queryOperatorInfo(@Param("id") Long id,@Param("userLoginName") String userLoginName,@Param("status") String status,@Param("orgCode") String orgCode,@Param("page") Page<MKCloudUserInfo> page);

}