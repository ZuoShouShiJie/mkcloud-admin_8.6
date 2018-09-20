//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class LoginQuitReq {
    private String sesssionId;

    public LoginQuitReq() {
    }

    public String getSesssionId() {
        return this.sesssionId;
    }

    public void setSesssionId(String sesssionId) {
        this.sesssionId = sesssionId;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
