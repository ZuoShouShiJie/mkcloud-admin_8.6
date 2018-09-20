package mkcloudadmin.model.mkcloud.po;

import java.util.Date;

public class MKCloudBusinessPeople {
    private Long id;

    private String businessPeopleCode;

    private String businessPeopleName;

    private String idNo;

    private String tel;

    private String wchat;

    private String businessPeopleType;

    private String businessPeopleLevel;

    private String workAddress;

    private String province;

    private String city;

    private String district;

    private String detailedAddress;

    private String state;

    private String openId;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String workUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessPeopleCode() {
        return businessPeopleCode;
    }

    public void setBusinessPeopleCode(String businessPeopleCode) {
        this.businessPeopleCode = businessPeopleCode == null ? null : businessPeopleCode.trim();
    }

    public String getBusinessPeopleName() {
        return businessPeopleName;
    }

    public void setBusinessPeopleName(String businessPeopleName) {
        this.businessPeopleName = businessPeopleName == null ? null : businessPeopleName.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getWchat() {
        return wchat;
    }

    public void setWchat(String wchat) {
        this.wchat = wchat == null ? null : wchat.trim();
    }

    public String getBusinessPeopleType() {
        return businessPeopleType;
    }

    public void setBusinessPeopleType(String businessPeopleType) {
        this.businessPeopleType = businessPeopleType == null ? null : businessPeopleType.trim();
    }

    public String getBusinessPeopleLevel() {
        return businessPeopleLevel;
    }

    public void setBusinessPeopleLevel(String businessPeopleLevel) {
        this.businessPeopleLevel = businessPeopleLevel == null ? null : businessPeopleLevel.trim();
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress == null ? null : workAddress.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }
}