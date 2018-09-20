package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudLoanInfo {
    private Long id;

    private String productCode;

    private String productName;

    private String institutionId;

    private String type;

    private String limitRange;

    private String periodRange;

    private String pointUrl;

    private String lable;

    private String detailPageUrl;

    private String bannerUrl;

    private BigDecimal allCommission;

    private BigDecimal inCommission;

    private BigDecimal outCommission;

    private BigDecimal outCommission2;

    private BigDecimal outCommission3;

    private Integer order;

    private Integer status;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId == null ? null : institutionId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLimitRange() {
        return limitRange;
    }

    public void setLimitRange(String limitRange) {
        this.limitRange = limitRange == null ? null : limitRange.trim();
    }

    public String getPeriodRange() {
        return periodRange;
    }

    public void setPeriodRange(String periodRange) {
        this.periodRange = periodRange == null ? null : periodRange.trim();
    }

    public String getPointUrl() {
        return pointUrl;
    }

    public void setPointUrl(String pointUrl) {
        this.pointUrl = pointUrl == null ? null : pointUrl.trim();
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable == null ? null : lable.trim();
    }

    public String getDetailPageUrl() {
        return detailPageUrl;
    }

    public void setDetailPageUrl(String detailPageUrl) {
        this.detailPageUrl = detailPageUrl == null ? null : detailPageUrl.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public BigDecimal getAllCommission() {
        return allCommission;
    }

    public void setAllCommission(BigDecimal allCommission) {
        this.allCommission = allCommission;
    }

    public BigDecimal getInCommission() {
        return inCommission;
    }

    public void setInCommission(BigDecimal inCommission) {
        this.inCommission = inCommission;
    }

    public BigDecimal getOutCommission() {
        return outCommission;
    }

    public void setOutCommission(BigDecimal outCommission) {
        this.outCommission = outCommission;
    }

    public BigDecimal getOutCommission2() {
        return outCommission2;
    }

    public void setOutCommission2(BigDecimal outCommission2) {
        this.outCommission2 = outCommission2;
    }

    public BigDecimal getOutCommission3() {
        return outCommission3;
    }

    public void setOutCommission3(BigDecimal outCommission3) {
        this.outCommission3 = outCommission3;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}