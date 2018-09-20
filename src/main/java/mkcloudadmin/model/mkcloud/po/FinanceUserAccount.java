package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceUserAccount {
    private Long id;

    private Long userId;

    private BigDecimal canWithdrawMoney;

    private BigDecimal withdrawedMoney;

    private BigDecimal incomeMoney;

    private BigDecimal sumChargeMoney;

    private BigDecimal money;

    private String userName;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Long versionNum;

    private String isFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getCanWithdrawMoney() {
        return canWithdrawMoney;
    }

    public void setCanWithdrawMoney(BigDecimal canWithdrawMoney) {
        this.canWithdrawMoney = canWithdrawMoney;
    }

    public BigDecimal getWithdrawedMoney() {
        return withdrawedMoney;
    }

    public void setWithdrawedMoney(BigDecimal withdrawedMoney) {
        this.withdrawedMoney = withdrawedMoney;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Long versionNum) {
        this.versionNum = versionNum;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag == null ? null : isFlag.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public BigDecimal getSumChargeMoney() {
        return sumChargeMoney;
    }

    public void setSumChargeMoney(BigDecimal sumChargeMoney) {
        this.sumChargeMoney = sumChargeMoney;
    }
}