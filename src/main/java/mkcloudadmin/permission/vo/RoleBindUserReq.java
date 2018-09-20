//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class RoleBindUserReq {
    private String userUid;
    private String roleUid;
    private List<String> bindUserUids;

    public RoleBindUserReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getRoleUid() {
        return this.roleUid;
    }

    public void setRoleUid(String roleUid) {
        this.roleUid = roleUid;
    }

    public List<String> getBindUserUids() {
        if (this.bindUserUids == null) {
            this.bindUserUids = new ArrayList();
        }

        return this.bindUserUids;
    }

    public void setBindUserUids(List<String> bindUserUids) {
        this.bindUserUids = bindUserUids;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
