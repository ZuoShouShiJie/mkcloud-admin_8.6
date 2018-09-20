package mkcloudadmin.model.mkcloud.po;

import java.util.Date;

public class MKCloudBankImportDetailTem {
    private Long id;

    private String batchId;

    private String applyId;

    private String applyName;

    private String applyMobile;

    private String applyIdCard;

    private String applyBank;

    private String applyProduct;

    private String presenter;

    private String applyCardDate;

    private String applyCardTime;

    private String auditStatus;

    private String remark;

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

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyMobile() {
        return applyMobile;
    }

    public void setApplyMobile(String applyMobile) {
        this.applyMobile = applyMobile == null ? null : applyMobile.trim();
    }

    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard == null ? null : applyIdCard.trim();
    }

    public String getApplyBank() {
        return applyBank;
    }

    public void setApplyBank(String applyBank) {
        this.applyBank = applyBank == null ? null : applyBank.trim();
    }

    public String getApplyProduct() {
        return applyProduct;
    }

    public void setApplyProduct(String applyProduct) {
        this.applyProduct = applyProduct == null ? null : applyProduct.trim();
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