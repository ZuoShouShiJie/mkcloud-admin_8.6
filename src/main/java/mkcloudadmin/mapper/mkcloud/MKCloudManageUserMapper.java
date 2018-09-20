package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudManageUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudManageUser record);

    int insertSelective(MKCloudManageUser record);

    MKCloudManageUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudManageUser record);

    int updateByPrimaryKey(MKCloudManageUser record);

    MKCloudManageUser queryByUserNameAndPwd(@Param("username") String username,@Param("password") String password);

    MKCloudManageUser queryByUserName(@Param("username") String username);

    Long updateByUserId(MKCloudManageUser mkCloudManageUser);

    Long selectUserInfoCount(@Param("username") String username);
    List<MKCloudManageUser> selectUserInfoList(@Param("page") Page<MKCloudManageUser> mkCloudManageUserPage, @Param("username") String username);
}