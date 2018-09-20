package mkcloudadmin.model.mkcloud.dto;

import java.math.BigDecimal;

/**
  *功能描述:
  * @author: moruihai
  * @date: 2018/8/1 9:26
  * @param:  * @param null
  * @return:
  */
public class CommissionDetailQueryDTO {
    private String businessPeopleId;
    private String businessPeopleName;
    private String cusTel;
    private String cusName;
    private String beginDate;
    private String endDate;


    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
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
