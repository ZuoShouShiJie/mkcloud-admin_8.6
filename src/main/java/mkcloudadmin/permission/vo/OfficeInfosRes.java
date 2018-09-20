//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficeInfosRes implements Serializable {
    private int id;
    private String uuid;
    private int createUser = 0;
    private Date createTime;
    private int updateUser = 0;
    private Date updateTime;
    private String remark = "";
    private String parentId = "";
    private String parentUid = "";
    private String parentName = "";
    private String parentIds = "";
    private String name = "";
    private Integer sort = Integer.valueOf(0);
    private String areaId = "";
    private String code = "";
    private String type = "";
    private String typeDesc = "";
    private String grade = "";
    private String address = "";
    private String zipCode = "";
    private String master = "";
    private String phone = "";
    private String fax = "";
    private String email = "";
    private String useable = "";
    private String primaryPerson = "";
    private String deputyPerson = "";
    private List<mkcloudadmin.permission.vo.OfficeInfosRes> childOffice;

    public OfficeInfosRes() {
    }

    public String getTypeDesc() {
        return this.typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return this.parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return this.master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseable() {
        return this.useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public String getPrimaryPerson() {
        return this.primaryPerson;
    }

    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    public String getDeputyPerson() {
        return this.deputyPerson;
    }

    public void setDeputyPerson(String deputyPerson) {
        this.deputyPerson = deputyPerson;
    }

    public List<mkcloudadmin.permission.vo.OfficeInfosRes> getChildOffice() {
        if (this.childOffice == null) {
            this.childOffice = new ArrayList();
        }

        return this.childOffice;
    }

    public void setChildOffice(List<mkcloudadmin.permission.vo.OfficeInfosRes> childOffice) {
        this.childOffice = childOffice;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getParentUid() {
        return this.parentUid;
    }

    public void setParentUid(String parentUid) {
        this.parentUid = parentUid;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
