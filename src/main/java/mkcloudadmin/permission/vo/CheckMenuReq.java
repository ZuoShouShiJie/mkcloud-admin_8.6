//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class CheckMenuReq {
    private String userUid;
    private String name;
    private String checkMenuUid;

    public CheckMenuReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckMenuUid() {
        return this.checkMenuUid;
    }

    public void setCheckMenuUid(String checkMenuUid) {
        this.checkMenuUid = checkMenuUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
