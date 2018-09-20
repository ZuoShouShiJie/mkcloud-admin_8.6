package mkcloudadmin.model.mkcloud.vo;

import java.util.Date;

public class MKCloudBusinessPeopleDetailVo {
    private Long allCommission;
    private Long inCommission;
    private Long id;

    private String businessPeopleCode;

    private String businessPeopleName;

    private String idNo;

    private String tel;

    private String wchat;

    private String businessPeopleType;

    private String businessPeopleLevel;

    private String province;

    private String city;

    private String district;

    private String detailedAddress;

    private String state;

    private String openId;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;
    private Long accountId;
    private String accountBank;

    private String accountCode;

    private String accountState;
      private Long attId;
    private String attachmentType;//附件类型
    private String attachmentName;//附件名称
    private String attachmentAddress;//附件地址s;
    private String attachmentSize;//附件大小

    private String memberName;

    private String workAddress;

    private String type;

    private String hasBusiness;
    private String businessStatus;
    private String faceOfIDCardUrl ;
    private String  backOfIDCardUrl ;
    private String  handIDCardUrl ;
    private String  contractUrl;
    private String stateName;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAttId() {
        return attId;
    }

    public void setAttId(Long attId) {
        this.attId = attId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        if("1".equals(stateName)){
            this.stateName ="有效";

        }else if("2".equals(stateName)){
            this.stateName ="待审核";

        }else if("0".equals(stateName)){
            this.stateName ="失效";

        }
    }

    public String getFaceOfIDCardUrl() {
        return faceOfIDCardUrl;
    }

    public void setFaceOfIDCardUrl(String faceOfIDCardUrl) {
        this.faceOfIDCardUrl = faceOfIDCardUrl;
    }

    public String getBackOfIDCardUrl() {
        return backOfIDCardUrl;
    }

    public void setBackOfIDCardUrl(String backOfIDCardUrl) {
        this.backOfIDCardUrl = backOfIDCardUrl;
    }

    public String getHandIDCardUrl() {
        return handIDCardUrl;
    }

    public void setHandIDCardUrl(String handIDCardUrl) {
        this.handIDCardUrl = handIDCardUrl;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public Long getAllCommission() {
        return allCommission;
    }

    public void setAllCommission(Long allCommission) {
        this.allCommission = allCommission;
    }

    public Long getInCommission() {
        return inCommission;
    }

    public void setInCommission(Long inCommission) {
        this.inCommission = inCommission;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHasBusiness() {
        return hasBusiness;
    }

    public void setHasBusiness(String hasBusiness) {
        this.hasBusiness = hasBusiness;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getAccountBank() {
        return accountBank;
    }
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }
    public String getAccountCode() {
        return accountCode;
    }
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    public String getAccountState() {
        return accountState;
    }
    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }


    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}