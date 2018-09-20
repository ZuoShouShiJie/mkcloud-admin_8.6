package mkcloudadmin.model.mkcloud.dto;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 付款管理
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-01 11:49
 **/
public class PaymentQueryDTO {
    private String payee;
    private String payeeAccount;
    private String costType;
    private String beginDate;
    private String endDate;

    private String confirmBeginDate;
    private String confirmEndDate;

    public String getConfirmBeginDate() {
        return confirmBeginDate;
    }

    public void setConfirmBeginDate(String confirmBeginDate) {
        this.confirmBeginDate = confirmBeginDate;
    }

    public String getConfirmEndDate() {
        return confirmEndDate;
    }

    public void setConfirmEndDate(String confirmEndDate) {
        this.confirmEndDate = confirmEndDate;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
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
