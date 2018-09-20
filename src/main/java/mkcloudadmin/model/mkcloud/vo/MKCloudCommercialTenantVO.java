package mkcloudadmin.model.mkcloud.vo;

import java.util.Date;

public class MKCloudCommercialTenantVO {
    private Long id;

    private String merchantCode;

    private String merchantName;

    private String  cooperationTime;
    private String merchantType;
    private String cooperativeState;
    private String cooperativeStateName;
    private String terminationTime;

    private String remarks;
    private String adjustType;
    private String adjustTypeName;
    private String advance;

    private String accountDate;

    private String accountBank;

    private String accountName;

    private String account;
    private String createUser;

    private String createTime;

    private String updateUser;

    private String updateTime;

    public String getAdjustTypeName() {
        return adjustTypeName;
    }

    public void setAdjustTypeName(String adjustTypeName) {
        this.adjustTypeName = adjustTypeName;
    }

    public String getCooperativeStateName() {
        return cooperativeStateName;
    }

    public void setCooperativeStateName(String cooperativeStateName) {
        this.cooperativeStateName = cooperativeStateName;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCooperationTime() {
        return cooperationTime;
    }

    public void setCooperationTime(String cooperationTime) {
        this.cooperationTime = cooperationTime;
    }

    public String getCooperativeState() {
        return cooperativeState;
    }

    public void setCooperativeState(String cooperativeState) {
        this.cooperativeState = cooperativeState;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(String terminationTime) {
        this.terminationTime = terminationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}