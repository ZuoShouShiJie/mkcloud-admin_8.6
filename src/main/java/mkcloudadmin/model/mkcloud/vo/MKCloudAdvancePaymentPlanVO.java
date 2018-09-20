package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudAdvancePaymentPlanVO {
    private  String  totalPailMoney;
    private  String  usedPailMoney;
    private  String  overPailplusMoney;
    private Long id;
    private String adjustType;
    private String adjustTypeName;
    private String advance;

    private String accountDate;

    private String accountBank;

    private String accountName;

    private String account;

    private String createUser;

    private String createTime;

    public String getAdjustTypeName() {
        return adjustTypeName;
    }

    public void setAdjustTypeName(String adjustTypeName) {
        this.adjustTypeName = adjustTypeName;
    }

    public String getTotalPailMoney() {
        return totalPailMoney;
    }

    public void setTotalPailMoney(String totalPailMoney) {
        this.totalPailMoney = totalPailMoney;
    }

    public String getUsedPailMoney() {
        return usedPailMoney;
    }

    public void setUsedPailMoney(String usedPailMoney) {
        this.usedPailMoney = usedPailMoney;
    }

    public String getOverPailplusMoney() {
        return overPailplusMoney;
    }

    public void setOverPailplusMoney(String overPailplusMoney) {
        this.overPailplusMoney = overPailplusMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType == null ? null : adjustType.trim();
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank == null ? null : accountBank.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}