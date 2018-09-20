package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceProductMain {
    private Long id;

    private String productName;

    private Integer type;

    private String redirectUrl;

    private Integer amountType;

    private BigDecimal totalBonus;

    private BigDecimal terminalBonus;

    private BigDecimal directBonus;

    private BigDecimal indirectBonus;

    private String logoUrl;

    private Integer seqNo;

    private String cashbackDate;

    private Integer isShow;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private String creator;

    private String updator;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(BigDecimal totalBonus) {
        this.totalBonus = totalBonus;
    }

    public BigDecimal getTerminalBonus() {
        return terminalBonus;
    }

    public void setTerminalBonus(BigDecimal terminalBonus) {
        this.terminalBonus = terminalBonus;
    }

    public BigDecimal getDirectBonus() {
        return directBonus;
    }

    public void setDirectBonus(BigDecimal directBonus) {
        this.directBonus = directBonus;
    }

    public BigDecimal getIndirectBonus() {
        return indirectBonus;
    }

    public void setIndirectBonus(BigDecimal indirectBonus) {
        this.indirectBonus = indirectBonus;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getCashbackDate() {
        return cashbackDate;
    }

    public void setCashbackDate(String cashbackDate) {
        this.cashbackDate = cashbackDate == null ? null : cashbackDate.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FinanceProductMain{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", type=" + type +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", amountType=" + amountType +
                ", totalBonus=" + totalBonus +
                ", terminalBonus=" + terminalBonus +
                ", directBonus=" + directBonus +
                ", indirectBonus=" + indirectBonus +
                ", logoUrl='" + logoUrl + '\'' +
                ", seqNo=" + seqNo +
                ", cashbackDate='" + cashbackDate + '\'' +
                ", isShow=" + isShow +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", creator='" + creator + '\'' +
                ", updator='" + updator + '\'' +
                ", version=" + version +
                '}';
    }
}