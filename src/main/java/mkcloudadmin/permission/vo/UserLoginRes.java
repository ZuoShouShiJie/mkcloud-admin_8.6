//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class UserLoginRes implements Serializable {
    private String sessionId;
    private UserInfoRes userInfoRes;
    private UserMenuRes userMenuRes;

    public UserLoginRes() {
    }

    public UserMenuRes getUserMenuRes() {
        return this.userMenuRes;
    }

    public void setUserMenuRes(UserMenuRes userMenuRes) {
        this.userMenuRes = userMenuRes;
    }

    public UserInfoRes getUserInfoRes() {
        return this.userInfoRes;
    }

    public void setUserInfoRes(UserInfoRes userInfoRes) {
        this.userInfoRes = userInfoRes;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
