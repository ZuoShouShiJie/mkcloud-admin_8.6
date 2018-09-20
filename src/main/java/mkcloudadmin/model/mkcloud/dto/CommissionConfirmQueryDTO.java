package mkcloudadmin.model.mkcloud.dto;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 分佣审核查询
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-30 11:56
 **/
public class CommissionConfirmQueryDTO {
    private String businessPeopleId;
    private String businessPeopleName;
    private String approvalResult;
    private String beginDate;
    private String endDate;

    private String settlementId;
    private String state;

    private BigDecimal settlementCommission;

    public BigDecimal getSettlementCommission() {
        return settlementCommission;
    }

    public void setSettlementCommission(BigDecimal settlementCommission) {
        this.settlementCommission = settlementCommission;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
