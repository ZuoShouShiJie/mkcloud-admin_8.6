//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class RoleInfoRes {
    private String roleUid;
    private String officeId = "";
    private String officeName = "";
    private String roleName = "";
    private String roleType = "";
    private String systemkey = "";
    private List<RoleMenuInfoRes> roleMenuList;

    public RoleInfoRes() {
    }

    public String getSystemkey() {
        return this.systemkey;
    }

    public void setSystemkey(String systemkey) {
        this.systemkey = systemkey;
    }

    public String getRoleUid() {
        return this.roleUid;
    }

    public void setRoleUid(String roleUid) {
        this.roleUid = roleUid;
    }

    public String getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<RoleMenuInfoRes> getRoleMenuList() {
        if (this.roleMenuList == null) {
            this.roleMenuList = new ArrayList();
        }

        return this.roleMenuList;
    }

    public String getOfficeName() {
        return this.officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setRoleMenuList(List<RoleMenuInfoRes> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
