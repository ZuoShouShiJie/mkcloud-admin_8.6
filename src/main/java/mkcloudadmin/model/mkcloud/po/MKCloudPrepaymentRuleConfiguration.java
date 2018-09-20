package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudPrepaymentRuleConfiguration {
    private Long id;

    private String merchantCode;

    private String settlementType;

    private String settlementMode;

    private BigDecimal settlementPeriod;

    private String settlementPeriodUnit;

    private Date settltmentBeginDate;

    private Date settlementEndDate;

    private BigDecimal price;

    private String show;

    private String isUnitSettle;

    private String state;

    private Date createTime;

    private String createUser;

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
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
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

    public BigDecimal getSettlementPeriod() {
        return settlementPeriod;
    }

    public void setSettlementPeriod(BigDecimal settlementPeriod) {
        this.settlementPeriod = settlementPeriod;
    }

    public String getSettlementPeriodUnit() {
        return settlementPeriodUnit;
    }

    public void setSettlementPeriodUnit(String settlementPeriodUnit) {
        this.settlementPeriodUnit = settlementPeriodUnit == null ? null : settlementPeriodUnit.trim();
    }

    public Date getSettltmentBeginDate() {
        return settltmentBeginDate;
    }

    public void setSettltmentBeginDate(Date settltmentBeginDate) {
        this.settltmentBeginDate = settltmentBeginDate;
    }

    public Date getSettlementEndDate() {
        return settlementEndDate;
    }

    public void setSettlementEndDate(Date settlementEndDate) {
        this.settlementEndDate = settlementEndDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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