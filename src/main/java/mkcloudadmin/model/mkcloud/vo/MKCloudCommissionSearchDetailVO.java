package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
  *功能描述:
  * @author: moruihai
  * @date: 2018/8/1 10:07
  * @param:  * @param null
  * @return:
  */
public class MKCloudCommissionSearchDetailVO {

    private String businessPeopleId;
    private String businessPeopleName;
    private BigDecimal businessPeopleCommission;
    private String memberCode;
    private String cusName;
    private String cusIdNo;
    private String cusTel;
    private String createTime;
    private String institutionName;
    private String productName;
    private String approvalDate;
    private String confirmTime;
    private Long seqNo;

    public String getBusinessPeopleId() {
        return businessPeopleId;
    }

    public void setBusinessPeopleId(String businessPeopleId) {
        this.businessPeopleId = businessPeopleId;
    }

    public String getBusinessPeopleName() {
        return businessPeopleName;
    }

    public void setBusinessPeopleName(String businessPeopleName) {
        this.businessPeopleName = businessPeopleName;
    }

    public BigDecimal getBusinessPeopleCommission() {
        return businessPeopleCommission;
    }

    public void setBusinessPeopleCommission(BigDecimal businessPeopleCommission) {
        this.businessPeopleCommission = businessPeopleCommission;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusIdNo() {
        return cusIdNo;
    }

    public void setCusIdNo(String cusIdNo) {
        this.cusIdNo = cusIdNo;
    }

    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }
}
