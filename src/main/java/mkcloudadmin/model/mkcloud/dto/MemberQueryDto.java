package mkcloudadmin.model.mkcloud.dto;

/**
 * 会员管理查询参数接收类.
 * @author hewenbin
 * @version v1.0 2018年7月21日 下午4:30:46 hewenbin
 */
public class MemberQueryDto {

	private String memberCode;
	private String memberName;
	private String memberMobile;
	private String businessCode;
	private String businessName;
	private String businessMobile;
	private String businessStatus;
	private String registerBeginDate;
	private String registerEndDate;

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode != null ? memberCode.trim() : memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName != null ? memberName.trim() : memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile != null ? memberMobile.trim() : memberMobile;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode != null ? businessCode.trim() : businessCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName != null ? businessName.trim() : businessName;
	}

	public String getBusinessMobile() {
		return businessMobile;
	}

	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile != null ? businessMobile.trim() : businessMobile;
	}

	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public String getRegisterBeginDate() {
		return registerBeginDate;
	}

	public void setRegisterBeginDate(String registerBeginDate) {
		this.registerBeginDate = registerBeginDate != null ? registerBeginDate.trim() : registerBeginDate;
	}

	public String getRegisterEndDate() {
		return registerEndDate;
	}

	public void setRegisterEndDate(String registerEndDate) {
		this.registerEndDate = registerEndDate != null ? registerEndDate.trim() : registerEndDate;
	}

}
