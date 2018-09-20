package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudCommissionDetail {
    private Long id;

    private String businessPeopleId;

    private String cusName;

    private String cusTel;

    private String cusIdNo;

    private String level;

    private String businessPeopleName;

    private BigDecimal businessPeopleCommission;

    private String state;

    private String detailId;

    private String batchId;

    private String institutionCode;

    private String institutionName;

    private String productName;

    private Date approvalDate;

    private String approvalResult;

    private String settlementId;

    private BigDecimal commission;

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

    public String getBusinessPeopleId() {
        return businessPeopleId;
    }

    public void setBusinessPeopleId(String businessPeopleId) {
        this.businessPeopleId = businessPeopleId == null ? null : businessPeopleId.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel == null ? null : cusTel.trim();
    }

    public String getCusIdNo() {
        return cusIdNo;
    }

    public void setCusIdNo(String cusIdNo) {
        this.cusIdNo = cusIdNo == null ? null : cusIdNo.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getBusinessPeopleName() {
        return businessPeopleName;
    }

    public void setBusinessPeopleName(String businessPeopleName) {
        this.businessPeopleName = businessPeopleName == null ? null : businessPeopleName.trim();
    }

    public BigDecimal getBusinessPeopleCommission() {
        return businessPeopleCommission;
    }

    public void setBusinessPeopleCommission(BigDecimal businessPeopleCommission) {
        this.businessPeopleCommission = businessPeopleCommission;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult == null ? null : approvalResult.trim();
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId == null ? null : settlementId.trim();
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
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