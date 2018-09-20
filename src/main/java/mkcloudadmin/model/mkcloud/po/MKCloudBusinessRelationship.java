package mkcloudadmin.model.mkcloud.po;

import java.util.Date;

public class MKCloudBusinessRelationship {
    private Long id;

    private String businessPeopleId;

    private String businessPeopleParentId;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessPeopleId() {
        return businessPeopleId;
    }

    public void setBusinessPeopleId(String businessPeopleId) {
        this.businessPeopleId = businessPeopleId == null ? null : businessPeopleId.trim();
    }

    public String getBusinessPeopleParentId() {
        return businessPeopleParentId;
    }

    public void setBusinessPeopleParentId(String businessPeopleParentId) {
        this.businessPeopleParentId = businessPeopleParentId == null ? null : businessPeopleParentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}