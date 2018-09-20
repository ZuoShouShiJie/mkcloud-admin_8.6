//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class UserInfoReq {
    private String userUid;
    private String updateUserUid;

    public UserInfoReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUpdateUserUid() {
        return this.updateUserUid;
    }

    public void setUpdateUserUid(String updateUserUid) {
        this.updateUserUid = updateUserUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
