//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class RoleInfoListReq extends PageBaseReq {
    private String userUid;
    private String roleName = "";
    private String roleTypeValue = "";

    public RoleInfoListReq() {
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleTypeValue() {
        return this.roleTypeValue;
    }

    public void setRoleTypeValue(String roleTypeValue) {
        this.roleTypeValue = roleTypeValue;
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
