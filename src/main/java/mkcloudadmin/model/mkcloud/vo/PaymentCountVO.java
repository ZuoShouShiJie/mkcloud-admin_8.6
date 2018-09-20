package mkcloudadmin.model.mkcloud.vo;

/**
 * Created by Administrator on 2018/8/27.
 */
public class PaymentCountVO {

    private String paidCommission;
    private String waitCommission;

    public String getPaidCommission() {
        return paidCommission;
    }

    public void setPaidCommission(String paidCommission) {
        this.paidCommission = paidCommission;
    }

    public String getWaitCommission() {
        return waitCommission;
    }

    public void setWaitCommission(String waitCommission) {
        this.waitCommission = waitCommission;
    }
}
