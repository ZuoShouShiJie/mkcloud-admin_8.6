package mkcloudadmin.model.mkcloud.dto;

/**
 * 申卡查询参数接收类
 * Created by Administrator on 2018/8/3.
 */
public class ApplyRecordQueryDTO {

    private String applyProduct;//产品名称
    private String applyName;//客户姓名
    private String applyMobile;//客户手机号
    private String batchId;//导入批次号
    private String businessPeopleCode;//推广员员工号
    private String businessPeopleName;//推广员姓名
    private String applyBeginDate;//申请时间开始
    private String applyEndDate;//申请时间结束
private String auditStatus;

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getApplyProduct() {
        return applyProduct;
    }

    public void setApplyProduct(String applyProduct) {
        this.applyProduct = applyProduct;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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

    public String getApplyBeginDate() {
        return applyBeginDate;
    }

    public void setApplyBeginDate(String applyBeginDate) {
        this.applyBeginDate = applyBeginDate;
    }

    public String getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(String applyEndDate) {
        this.applyEndDate = applyEndDate;
    }
}
