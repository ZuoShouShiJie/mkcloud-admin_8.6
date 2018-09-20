package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 分佣确认明细
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-31 17:41
 **/
public class MKCloudCommissionConfirmDetailVO {
    private String applyName;
    private String applyMobile;
    private String applyProduct;
    private String commissionCreateDate;
    private BigDecimal commissionAmount;
    private Long seqNo;

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

    public String getApplyProduct() {
        return applyProduct;
    }

    public void setApplyProduct(String applyProduct) {
        this.applyProduct = applyProduct;
    }

    public String getCommissionCreateDate() {
        return commissionCreateDate;
    }

    public void setCommissionCreateDate(String commissionCreateDate) {
        this.commissionCreateDate = commissionCreateDate;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }
}
