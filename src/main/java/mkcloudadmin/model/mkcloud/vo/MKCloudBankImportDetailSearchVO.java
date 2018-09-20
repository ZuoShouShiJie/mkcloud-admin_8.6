package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
  *功能描述:
  * @author: moruihai
  * @date: 2018/8/4 21:30
  * @param:  * @param null
  * @return:
  */
public class MKCloudBankImportDetailSearchVO extends MKCloudBankImportDetailVO{
    private String commissionState ;
    private String confirmDate;

    public String getCommissionState() {
        return commissionState;
    }

    public void setCommissionState(String commissionState) {
        this.commissionState = commissionState;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }
}
