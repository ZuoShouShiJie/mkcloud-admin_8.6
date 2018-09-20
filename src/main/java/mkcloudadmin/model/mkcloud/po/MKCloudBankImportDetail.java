package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;
import java.util.Date;

public class MKCloudBankImportDetail {
    private Long id;

    private String batchId;

    private String applyId;

    private String cusName;

    private String cusTel;

    private String cusIdNo;

    private String institutionCode;

    private String institutionName;

    private String productName;

    private String presenter;

    private String applyCardDate;

    private String applyCardTime;

    private String auditStatus;

    private String remark;

    private BigDecimal commission;

    private BigDecimal inCommission;

    private BigDecimal outCommission;

    private BigDecimal outCommission2;

    private BigDecimal outCommission3;

    private String businessPeopleCode;

    private String businessPeopleName;

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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
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

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter == null ? null : presenter.trim();
    }

    public String getApplyCardDate() {
        return applyCardDate;
    }

    public void setApplyCardDate(String applyCardDate) {
        this.applyCardDate = applyCardDate == null ? null : applyCardDate.trim();
    }

    public String getApplyCardTime() {
        return applyCardTime;
    }

    public void setApplyCardTime(String applyCardTime) {
        this.applyCardTime = applyCardTime == null ? null : applyCardTime.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
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