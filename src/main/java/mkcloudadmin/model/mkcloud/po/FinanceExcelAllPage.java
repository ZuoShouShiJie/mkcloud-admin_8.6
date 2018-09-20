package mkcloudadmin.model.mkcloud.po;

import mkcloudadmin.util.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanceExcelAllPage extends BasePage  {
    private Long id;

    private String batchNo;

    private String creater;

    private String updater;

    private Long detailsNum;

    private Long detailsValidNum;

    private String status;

    private Long failNum;

    private Date createTime;

    private String createTimeShow;

    private Date updateTime;

    private Long versionNum;

    private String isFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Long getDetailsNum() {
        return detailsNum;
    }

    public void setDetailsNum(Long detailsNum) {
        this.detailsNum = detailsNum;
    }

    public Long getDetailsValidNum() {
        return detailsValidNum;
    }

    public void setDetailsValidNum(Long detailsValidNum) {
        this.detailsValidNum = detailsValidNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTimeShow = sdf.format(createTime);
    }
    public Long getFailNum() {
        return failNum;
    }

    public void setFailNum(Long failNum) {
        this.failNum = failNum;
    }

    public String getCreateTimeShow() {return createTimeShow; }

    public void setCreateTimeShow(String  createTimeShow) {
        this.createTimeShow = createTimeShow;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Long versionNum) {
        this.versionNum = versionNum;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag == null ? null : isFlag.trim();
    }
}