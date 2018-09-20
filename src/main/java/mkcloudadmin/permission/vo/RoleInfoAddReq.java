//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleInfoAddReq {
    private String userUid;
    private String roleName;
    private String roleTypeValue;
    private String officeUid;
    private String systemkey;
    private List<String> menuUids;

    public RoleInfoAddReq() {
    }

    public List<String> getMenuUids() {
        if (this.menuUids == null) {
            this.menuUids = new ArrayList();
        }

        return this.menuUids;
    }

    public String getSystemkey() {
        return this.systemkey;
    }

    public void setSystemkey(String systemkey) {
        this.systemkey = systemkey;
    }

    public void setMenuUids(List<String> menuUids) {
        this.menuUids = menuUids;
    }

    public String getOfficeUid() {
        return this.officeUid;
    }

    public void setOfficeUid(String officeUid) {
        this.officeUid = officeUid;
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

    public String getRoleTypeValue() {
        return this.roleTypeValue;
    }

    public void setRoleTypeValue(String roleTypeValue) {
        this.roleTypeValue = roleTypeValue;
    }
}
