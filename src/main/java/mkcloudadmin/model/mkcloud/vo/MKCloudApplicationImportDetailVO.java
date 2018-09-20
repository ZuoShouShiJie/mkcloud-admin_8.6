package mkcloudadmin.model.mkcloud.vo;


/**
 * 申卡批次明细信息展示
 */
public class MKCloudApplicationImportDetailVO{

    private Integer number ;
    private Integer monthNumber;
    private String businessPeopleLevel;

    public String getBusinessPeopleLevel() {
        return businessPeopleLevel;
    }

    public void setBusinessPeopleLevel(String businessPeopleLevel) {
        this.businessPeopleLevel = businessPeopleLevel;
    }

    private Long seqNo;

    private Long id;

    private String batchId; //批次号

    private String applyId; //申请人id

    private String applyName;  //申请人姓名

    private String applyMobile; //申请人手机号

    private String applyIdCard; //申请人身份证号

    private String applyBank; //商户

    private String applyProduct; //产品名称

    private String applyCardTime; //用户申请时间

    private String auditStatus; //申请状态

    private String businessPeopleCode; //业务人员工号

    private String businessPeopleName; //业务人员姓名

    private String createTime; //数据导入时间

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

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

    public String getApplyCardTime() {
        return applyCardTime;
    }

    public void setApplyCardTime(String applyCardTime) {
        this.applyCardTime = applyCardTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}