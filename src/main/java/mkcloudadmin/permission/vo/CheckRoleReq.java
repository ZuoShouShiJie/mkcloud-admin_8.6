//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class CheckRoleReq {
    private String userUid;
    private String roleName;
    private String checkRoleUid;

    public CheckRoleReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCheckRoleUid() {
        return this.checkRoleUid;
    }

    public void setCheckRoleUid(String checkRoleUid) {
        this.checkRoleUid = checkRoleUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
