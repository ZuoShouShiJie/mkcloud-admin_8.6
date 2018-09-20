package mkcloudadmin.model.mkcloud.vo;

/**
 * @program: mkcloud-admin
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 17:01
 **/
public class FinanceProductMainPageVO {
    private Long id;

    private String productName;

    private Integer category;

    private String redirectUrl;

    private String productDes;

    private String productType;

    private String maxAmount;

    private String feeRate;

    private String logoUrl;

    private Integer seqNo;

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
        this.productName = productName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        if (productType == 1) {
            this.productType = "贷款";
        }else if (productType == 2) {
            this.productType = "信用卡";
        }else if(productType == 3) {
            this.productType = "注册返现";
        }else if(productType == 4) {
            this.productType = "下载APP返现";
        }else if(productType == 5) {
            this.productType = "核卡返现";
        }else if(productType == 6) {
            this.productType = "首刷返现";
        }else {
            this.productType = "";
        }
    }
    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow == 1 ? "是":"否";
    }

    @Override
    public String toString() {
        return "FinanceProductMainPageVO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", productDes='" + productDes + '\'' +
                ", productType='" + productType + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", feeRate='" + feeRate + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", seqNo=" + seqNo +
                ", isShow='" + isShow + '\'' +
                '}';
    }
}
