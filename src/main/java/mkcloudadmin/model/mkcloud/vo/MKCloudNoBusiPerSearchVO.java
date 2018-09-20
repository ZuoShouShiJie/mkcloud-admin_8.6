package mkcloudadmin.model.mkcloud.vo;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 无推广人员查询VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-03 14:53
 **/
public class MKCloudNoBusiPerSearchVO {
    private String applyName;  //申请人姓名

    private String applyMobile; //申请人手机号

    private String applyIdCard; //申请人身份证号

    private String applyProduct; //产品名称

    private String applyCardTime; //用户申请时间
    private String businessPeopleCode; //业务人员工号

    private String businessPeopleName; //业务人员姓名

    private String claim;  //认领状态
    private Long id;
    private String applyStatus;//申请时间

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }
}
