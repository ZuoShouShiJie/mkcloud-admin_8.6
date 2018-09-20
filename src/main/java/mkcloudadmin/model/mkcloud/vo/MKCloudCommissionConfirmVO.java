package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 分佣确认VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-30 14:06
 **/
public class MKCloudCommissionConfirmVO {
    private Long seqNo;
    private String businessPeopleType;
    private String businessPeopleId;
    private String businessPeopleName;
    private String businessPeopleLevel;
    private String businessPeopleLevelName;
    private String settlementId;
    private String state;
    private String createTime;
    private BigDecimal settlementCommission;
    private String stateName;
    private String confirmTime;

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getBusinessPeopleType() {
        return businessPeopleType;
    }

    public void setBusinessPeopleType(String businessPeopleType) {
        if("0".equals(businessPeopleType)){
            this.businessPeopleType = "内部";
        }else if("1".equals(businessPeopleType)){
            this.businessPeopleType = "外部";
        }
    }

    public String getBusinessPeopleLevel() {
        return businessPeopleLevel;
    }

    public void setBusinessPeopleLevel(String businessPeopleLevel) {
        this.businessPeopleLevel = businessPeopleLevel;
    }

    public String getBusinessPeopleLevelName() {
        return businessPeopleLevelName;
    }

    public void setBusinessPeopleLevelName(String businessPeopleLevelName) {
        if("0".equals(businessPeopleLevelName)){
            this.businessPeopleLevelName = "初级";
        }else if("1".equals(businessPeopleLevelName)){
            this.businessPeopleLevelName = "中级";
        }else if("2".equals(businessPeopleLevelName)){
            this.businessPeopleLevelName = "高级";
        }
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getSettlementCommission() {
        return settlementCommission;
    }

    public void setSettlementCommission(BigDecimal settlementCommission) {
        this.settlementCommission = settlementCommission;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;

    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }
}
