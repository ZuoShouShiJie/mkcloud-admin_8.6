package mkcloudadmin.model.mkcloud.vo;

import mkcloudadmin.model.mkcloud.po.MKCloudMemberInfo;

/**
 * 用户信息展示实体类.
 * @author panzhongkang
 * @version v1.0 2018年7月31日 上午11:03:56 panzhongkang
 */
public class MKCloudMemberInfoVo extends MKCloudMemberInfo {
	
    private Integer seqNo;
    private String memberCode;
    private String mobileNum;
    private String memberName;
    private String workAddress;
    private String businessCode;
    private String businessName;
    private String businessType;
	private String registerTime;
	private String hasBusiness;
	private String businessStatus;

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String getMemberCode() {
		return memberCode;
	}

	@Override
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Override
	public String getMemberName() {
		return memberName;
	}

	@Override
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String getWorkAddress() {
		return workAddress;
	}

	@Override
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getHasBusiness() {
		return hasBusiness;
	}

	public void setHasBusiness(String hasBusiness) {
		this.hasBusiness = hasBusiness;
	}

	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
}
