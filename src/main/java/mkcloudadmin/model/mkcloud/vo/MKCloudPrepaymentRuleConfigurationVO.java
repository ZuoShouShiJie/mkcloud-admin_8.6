package mkcloudadmin.model.mkcloud.vo;

import java.util.Date;

public class MKCloudPrepaymentRuleConfigurationVO {
    private Long id;

    private String settlementType;
    private String settlementTypeName;
    private String settlementMode;

    private Short settlementPeriod;
    private String settlementPeriodUnit;

    private String  settltmentBeginDate;

    private String  settlementEndDate;

    private String price;

    private String show;

    private String isUnitSettle;

    private String state;
    private String stateName;
    private Date createTime;

    private String createUser;
private long totalCount;
    private String totalMoney;
    private String merchantCode;

    private String merchantName;

    public String getMerchantCode() {
        return merchantCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getSettlementTypeName() {
        return settlementTypeName;
    }

    public void setSettlementTypeName(String settlementTypeName) {
        this.settlementTypeName = settlementTypeName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType == null ? null : settlementType.trim();
    }

    public String getSettlementMode() {
        return settlementMode;
    }

    public void setSettlementMode(String settlementMode) {
        this.settlementMode = settlementMode == null ? null : settlementMode.trim();
    }

    public Short getSettlementPeriod() {
        return settlementPeriod;
    }

    public void setSettlementPeriod(Short settlementPeriod) {
        this.settlementPeriod = settlementPeriod;
    }

    public String getSettlementPeriodUnit() {
        return settlementPeriodUnit;
    }

    public void setSettlementPeriodUnit(String settlementPeriodUnit) {
        this.settlementPeriodUnit = settlementPeriodUnit == null ? null : settlementPeriodUnit.trim();
    }

    public String getSettltmentBeginDate() {
        return settltmentBeginDate;
    }

    public void setSettltmentBeginDate(String settltmentBeginDate) {
        this.settltmentBeginDate = settltmentBeginDate;
    }

    public String getSettlementEndDate() {
        return settlementEndDate;
    }

    public void setSettlementEndDate(String settlementEndDate) {
        this.settlementEndDate = settlementEndDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show == null ? null : show.trim();
    }

    public String getIsUnitSettle() {
        return isUnitSettle;
    }

    public void setIsUnitSettle(String isUnitSettle) {
        this.isUnitSettle = isUnitSettle == null ? null : isUnitSettle.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
}