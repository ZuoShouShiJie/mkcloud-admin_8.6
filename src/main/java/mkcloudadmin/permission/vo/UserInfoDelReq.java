//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class UserInfoDelReq {
    private String userUid;
    private String delUserUid;

    public UserInfoDelReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getDelUserUid() {
        return this.delUserUid;
    }

    public void setDelUserUid(String delUserUid) {
        this.delUserUid = delUserUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
