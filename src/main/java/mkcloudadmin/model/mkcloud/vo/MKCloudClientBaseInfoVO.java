package mkcloudadmin.model.mkcloud.vo;

import mkcloudadmin.model.mkcloud.po.MKCloudMemberInfo;

/**
  *功能描述:
  * @author: moruihai
  * @date: 2018/8/1 19:33
  * @param:  * @param null
  * @return:
  */
public class MKCloudClientBaseInfoVO{
	

    private String mobileNum;
    private String name;
    private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
