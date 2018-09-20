package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudCreditCardInfoVO {
    private Long        id;

    private String      productCode;

    private String      productName;

    private String      institutionId;

    private String      pointUrl;

    private String      lable;

    private String      detailPageUrl;

    private String      bannerUrl;

    private BigDecimal  allCommission;

    private BigDecimal  inCommission;

    private BigDecimal  outCommission;

    private BigDecimal  outCommission2;

    private BigDecimal  outCommission3;

    private Integer     order;

    private Integer     status;

    private String        createTime;

    private String        updateTime;

    private Integer     isDelete;

    private String      creator;

    private String      updator;

    private Integer     version;

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