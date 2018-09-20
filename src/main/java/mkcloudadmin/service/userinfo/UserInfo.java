package mkcloudadmin.service.userinfo;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;

import java.util.List;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-05 00:06
 **/
public interface UserInfo {
    MKCloudManageUser queryByUserNameAndPwd(String username, String password);

    MKCloudManageUser queryManageUser(String userName);

    void insertManageUser(Map<String, String> param);

    void updateManageUser(Map<String, String> param);

    Map<String, Object> queryUserInfoData(String method,Page<MKCloudManageUser> mkCloudManageUserPage);
}
