package mkcloudadmin.model.mkcloud.vo;

/**
 * @program: mkcloud-admin
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 10:43
 **/
public class FinanceProductMainVO {

    private Long id;

    private String productName;

    private Integer type;

    private String redirectUrl;

    private String logoUrl;

    private Integer seqNo;

    private String cashbackDate;

    private String isShow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getCashbackDate() {
        return cashbackDate;
    }

    public void setCashbackDate(String cashbackDate) {
        this.cashbackDate = cashbackDate == null ? null : cashbackDate.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow == 1 ? "是":"否";
    }

    @Override
    public String toString() {
        return "FinanceProductMainVO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", type=" + type +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", seqNo=" + seqNo +
                ", cashbackDate='" + cashbackDate + '\'' +
                ", isShow='" + isShow + '\'' +
                '}';
    }
}
