package mkcloudadmin.service.operatorMng;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;

import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-05 00:06
 **/
public interface OperatorInfo {
    MKCloudUserInfo queryOperatorByNamePa(String username, String password, String orgCode);

    Map<String, Object> queryUserMenu(Map<String, String> param);

    Map<String, Object> addOperator(Map<String, String> param);

    Map<String, Object> queryOperatorInfo(Map<String, String> param);

    Map<String, Object> updateOperator(Map<String, String> param);

    Map<String, Object> updateOperatorPass(Map<String, String> param);

    Map<String, Object> queryRoleByUserId(Map<String, String> param);

    Map<String, Object> userAlloRole(Map<String, String> param);

//------------------------角色管理-----------------------------------------------

    Map<String, Object> queryRoleInfo(Map<String, String> param);

    Map<String, Object> addRoleInfo(Map<String, String> param);

    Map<String, Object> updateRoleInfo(Map<String, String> param);

    Map<String, Object> queryMenuByRoleId(Map<String, String> param);

    Map<String, Object> roleAlloMenu(Map<String, String> param);


    //---------------------------菜单管理----------------------------------------------
    Map<String, Object> queryMenuInfo(Map<String, String> param);

    Map<String, Object> updateMenuInfo(Map<String, String> param);

    Map<String, Object> queryFirstMenu(Map<String, String> param);

}
