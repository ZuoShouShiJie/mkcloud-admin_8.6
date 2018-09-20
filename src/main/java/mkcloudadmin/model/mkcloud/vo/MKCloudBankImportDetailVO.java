package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 银行反馈明细VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-28 21:47
 **/
public class MKCloudBankImportDetailVO {

    private Long seqNo;

    private Long id;

    private String batchId; //批次号

    private String applyId; //申请号

    private String applyName; //客户

    private String applyMobile; //客户手机号

    private String applyIdCard; //客户身份证号

    private String applyBank; //商户

    private String applyProduct; //产品名称

    private String applyCardDate; //商户审批日期

    private String auditStatus; //申请状态

    private BigDecimal commission;

    private BigDecimal inCommission;

    private BigDecimal outCommission;

    private BigDecimal outCommission2;

    private BigDecimal outCommission3;

    private String businessPeopleCode;

    private String businessPeopleName;

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

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
        this.batchId = batchId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyMobile() {
        return applyMobile;
    }

    public void setApplyMobile(String applyMobile) {
        this.applyMobile = applyMobile;
    }

    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard;
    }

    public String getApplyBank() {
        return applyBank;
    }

    public void setApplyBank(String applyBank) {
        this.applyBank = applyBank;
    }

    public String getApplyProduct() {
        return applyProduct;
    }

    public void setApplyProduct(String applyProduct) {
        this.applyProduct = applyProduct;
    }

    public String getApplyCardDate() {
        return applyCardDate;
    }

    public void setApplyCardDate(String applyCardDate) {
        this.applyCardDate = applyCardDate;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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
        this.businessPeopleCode = businessPeopleCode;
    }

    public String getBusinessPeopleName() {
        return businessPeopleName;
    }

    public void setBusinessPeopleName(String businessPeopleName) {
        this.businessPeopleName = businessPeopleName;
    }
}
