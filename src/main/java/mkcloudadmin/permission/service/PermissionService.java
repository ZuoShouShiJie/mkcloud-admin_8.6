//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.service;

import mkcloudadmin.permission.vo.*;

public interface PermissionService {
    BaseResponse login(UserLoginReq var1);

    BaseResponse loginQuit(LoginQuitReq var1);

    BaseResponse menuChird(UserMenuReq var1);

    BaseResponse methodPermission(MenuPermissionReq var1);

    BaseResponse getRoleUserList(RoleUserListReq var1);
}
