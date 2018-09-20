//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

public class UserInfoListReq extends PageBaseReq {
    private String userUid;
    private String loginName;
    private String name;
    private String companyUid;
    private String officeUid;

    public UserInfoListReq() {
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getCompanyUid() {
        return this.companyUid;
    }

    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    public String getOfficeUid() {
        return this.officeUid;
    }

    public void setOfficeUid(String officeUid) {
        this.officeUid = officeUid;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
