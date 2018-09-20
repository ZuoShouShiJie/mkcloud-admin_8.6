//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.core;

import java.util.concurrent.ConcurrentHashMap;

import mkcloudadmin.permission.vo.BaseResponse;

public class PermissionSession {
    private String sessionId;
    private String ip;
    private String agent;
    private ConcurrentHashMap<String, Object> context = new ConcurrentHashMap();
    BaseResponse permissionRes;

    public PermissionSession() {
    }

    public void setAttribute(String name, Object o) {
        this.context.put(name, o);
    }

    public Object getAttribute(String name) {
        return this.context.get(name);
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public BaseResponse getPermissionRes() {
        return this.permissionRes;
    }

    public void setPermissionRes(BaseResponse permissionRes) {
        this.permissionRes = permissionRes;
    }
}
