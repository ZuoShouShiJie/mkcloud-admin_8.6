//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class UserInfoAddReq {
    private String userUid = "";
    private String companyUid = "";
    private String officeUid = "";
    private String loginName = "";
    public static String LOGIN_NAME_STR = "loginName";
    private String name = "";
    private String phone = "";
    private String mobile = "";
    private String email = "";
    private String passwd = "";
    private String remark = "";
    List<String> roleUids;

    public UserInfoAddReq() {
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserUid() {
        return this.userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public List<String> getRoleUids() {
        if (this.roleUids == null) {
            this.roleUids = new ArrayList();
        }

        return this.roleUids;
    }

    public void setRoleUids(List<String> roleUids) {
        this.roleUids = roleUids;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
