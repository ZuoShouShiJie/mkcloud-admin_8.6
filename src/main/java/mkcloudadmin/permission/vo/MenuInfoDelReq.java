//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class MenuInfoDelReq {
    private String userUid;
    private String menuUid;

    public MenuInfoDelReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getMenuUid() {
        return this.menuUid;
    }

    public void setMenuUid(String menuUid) {
        this.menuUid = menuUid;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
