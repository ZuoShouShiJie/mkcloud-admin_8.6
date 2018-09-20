//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class MenuPermissionReq {
    private String sesssionId;
    private String herf;
    private String agent;
    private String ip;

    public MenuPermissionReq() {
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSesssionId() {
        return this.sesssionId;
    }

    public void setSesssionId(String sesssionId) {
        this.sesssionId = sesssionId;
    }

    public String getHerf() {
        return this.herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
