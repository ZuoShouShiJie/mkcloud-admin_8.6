package mkcloudadmin.model.mkcloud.dto;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-03 15:06
 **/
public class NoBusinessPerSearchDTO {
    private String applyName;  //申请人姓名

    private String applyMobile; //申请人手机号
    private String beginDate;
    private String endDate;

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
