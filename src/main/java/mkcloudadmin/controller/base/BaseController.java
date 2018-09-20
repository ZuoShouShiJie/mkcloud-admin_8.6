package mkcloudadmin.controller.base;

import java.util.HashMap;
import java.util.Map;

import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基本Controller.
 * @author hewenbin
 * @version v1.0 2018年6月13日 下午5:52:19 hewenbin
 */
@Controller
@RequestMapping("/")
public class BaseController extends SessionApi {
	
	/**页面编码和页面路径映射关系*/
	private static final Map<String, String> PAGE_MAP = new HashMap<>();

	static {
		//---------------------不要在这里添加，后续会删除--------------------------------------------------------
		PAGE_MAP.put("apiRules","views/apirule/apirules"); // 规则列表空页面
		PAGE_MAP.put("addRule", "views/apirule/addRulePage"); // 添加规则列表空页面
		PAGE_MAP.put("questionandanswer","views/questionandanswer/questionAndAnswers");//Q&A展示页面
        PAGE_MAP.put("addQuestion","views/questionandanswer/addQuestion");//创建Q&A
        PAGE_MAP.put("editQuestion","views/questionandanswer/editQuestion");//创建Q&A
        PAGE_MAP.put("financialProducts", "views/productmanagement/financialProducts"); // 理财产品列表页面
        PAGE_MAP.put("addFinancial", "views/productmanagement/addFinancialPage"); //理财添加页面

        PAGE_MAP.put("insuranceProduct", "views/productmanagement/insuranceProducts"); // 保险产品产品列表页面
        PAGE_MAP.put("addInsurance", "views/productmanagement/addInsurancePage"); //保险添加页面
        PAGE_MAP.put("financeHome", "views/productmanagement/financeHome"); // 金榕家集合页列表页面
        PAGE_MAP.put("addFinanceHomePage", "views/productmanagement/addFinanceHomePage"); //金榕家集合页添加页面
        PAGE_MAP.put("registerHome", "views/productmanagement/registerHome"); // 注册宝列表页面
        PAGE_MAP.put("addRegisterHomePage", "views/productmanagement/addRegisterHomePage"); //注册宝添加页面
		PAGE_MAP.put("uploadExcel", "views/uploadExcel/uploadExcel"); //上传excel,开始计算分润
		PAGE_MAP.put("commission", "views/commission/commission"); //上传excel,开始计算分润
       //---------------------不要在上面添加，后续会删除------------------------------------------------------------

		PAGE_MAP.put("index", "views/index"); //主页
		PAGE_MAP.put("feedVisitInfoImport","views/recordmanagement/feedVisitInfoImport");//申请批次信息导入主页
		PAGE_MAP.put("feedVisitInfoImportDetail","views/recordmanagement/feedVisitInfoImportDetail");//申请批次明细
		PAGE_MAP.put("feedBackInfoImport", "views/recordmanagement/feedBackInfoImport"); //主页
		PAGE_MAP.put("feedBackInfoImportDetail", "views/recordmanagement/feedBackInfoImportDetail"); //银行反馈明细页
		PAGE_MAP.put("queryVisitDetailRecord", "views/recordmanagement/queryVisitDetailRecord");//申卡记录查询
		PAGE_MAP.put("queryBackDetailRecord", "views/recordmanagement/queryBackDetailRecord");//申卡记录查询

		PAGE_MAP.put("membersManage", "views/membersmanage/membersManage"); //会员管理页面
		
		PAGE_MAP.put("commissionAudit", "views/commissionmanage/commissionAudit"); //分佣确认页
		PAGE_MAP.put("commissionAuditDetail", "views/commissionmanage/commissionAuditDetail_bak"); //分佣确认明细页
		PAGE_MAP.put("commissionDetailSearch", "views/commissionmanage/commissionDetailSearch"); //分佣明细查询页
		PAGE_MAP.put("unpaidDetailPage", "views/paymentmanagement/unpaidDetailPage"); //待付款清单
		PAGE_MAP.put("paidDetailPage", "views/paymentmanagement/paidDetailPage"); //已付款清单


		PAGE_MAP.put("creditCardProduct", "views/productmanagement/creditCardProducts"); // 信用卡产品列表页面
		PAGE_MAP.put("addCreditCard", "views/productmanagement/addCreditCardPage"); //信用卡添加页面
		PAGE_MAP.put("loanProduct", "views/productmanagement/loanProducts"); // 贷款产品列表页面
		PAGE_MAP.put("addLoan", "views/productmanagement/addLoanPage"); //贷款添加页面
		PAGE_MAP.put("inBusinessPeople", "views/businesspeople/inBusinessPeople"); // 内部推广员查询页面
		PAGE_MAP.put("addBusinessPeople", "views/businesspeople/addBusinessPeople"); // 新增内部推广员页面
		PAGE_MAP.put("outBusinessPeople", "views/businesspeople/outBusinessPeople"); // 外部推广员查询页面
		PAGE_MAP.put("outAddBusinessPeople", "views/businesspeople/outAddBusinessPeople"); // 新增外部推广员页面
		PAGE_MAP.put("applicationImportDetail", "views/businesspeople/applicationImportDetail"); // 办件量查询

		PAGE_MAP.put("inSelectBusinessPeople", "views/businesspeople/inSelectBusinessPeople"); // 查
		PAGE_MAP.put("outSeleceBusinessPeople", "views/businesspeople/outSeleceBusinessPeople"); // 查

		PAGE_MAP.put("nobusinesspersonRelated", "views/recordmanagement/nobusinesspersonRelated");
		PAGE_MAP.put("deployPage", "views/recordmanagement/deployPage");

		PAGE_MAP.put("totalCardAmount", "views/businesspeople/totalCardAmount"); // 信用卡产品列表页面

		PAGE_MAP.put("applyCountPage", "views/statisticsreport/applyCountPage");//办卡量汇总
		PAGE_MAP.put("incomeSummaryPage","views/statisticsreport/incomeSummaryPage");//收入汇总
		PAGE_MAP.put("paymentSummaryPage","views/statisticsreport/paymentSummaryPage");//付款统计

		PAGE_MAP.put("login", "views/login");
		PAGE_MAP.put("index", "views/index");
		PAGE_MAP.put("addUser", "views/addUser");
		PAGE_MAP.put("changeUser", "views/changeUser");
		PAGE_MAP.put("userInfoList", "views/userInfoList");

		PAGE_MAP.put("menumanagement", "views/rightsmanagement/menumanagement");//权限管理
		PAGE_MAP.put("rolemanagement", "views/rightsmanagement/rolemanagement");//角色管理
		PAGE_MAP.put("usermanagement", "views/rightsmanagement/usermanagement");//用户管理
		PAGE_MAP.put("addmenumanagement", "views/rightsmanagement/addmenumanagement");//新增权限管理菜单
		PAGE_MAP.put("addusermanagement", "views/rightsmanagement/addusermanagement");//新增用户管理菜单
		PAGE_MAP.put("updateusermanagement", "views/rightsmanagement/updateusermanagement");//修改用户管理菜单
		PAGE_MAP.put("addrolemanagement", "views/rightsmanagement/addrolemanagement");//新增角色管理菜单
		PAGE_MAP.put("updaterolemanagement", "views/rightsmanagement/updaterolemanagement");//修改角色管理菜单
		PAGE_MAP.put("addrolemenumanagement", "views/rightsmanagement/addrolemenumanagement");//角色管理里的菜单管理
		PAGE_MAP.put("userassignrolesmanagement", "views/rightsmanagement/userassignrolesmanagement");//用户管理里的分配角色
		PAGE_MAP.put("allomenumanagement", "views/rightsmanagement/allomenumanagement");//角色管理中，角色分配菜单


		//商户管理
		PAGE_MAP.put("commercialTenant", "views/commercialtenant/commercialTenant");//查询商户
		PAGE_MAP.put("addMmercialTenant", "views/commercialtenant/addMmercialTenant");//维护商户
		PAGE_MAP.put("selectMmercialTenant", "views/commercialtenant/selectMmercialTenant");//维护商户
		PAGE_MAP.put("updateMmercialTenant", "views/commercialtenant/updateMmercialTenant");//维护商户

		//预付款管理
		PAGE_MAP.put("advancePaidDetailPage", "views/advancePaidDetail/advancePaidDetailPage"); //预付款查询
		PAGE_MAP.put("advancePaymentPlan", "views/advancePaidDetail/advancePaymentPlan"); //预付款查询
		PAGE_MAP.put("addAdvancePaymentPlan", "views/advancePaidDetail/addAdvancePaymentPlan"); //预付款查询
		PAGE_MAP.put("mprepaymentRuleConfiguration", "views/advancePaidDetail/mprepaymentRuleConfiguration"); //预付款查询
		PAGE_MAP.put("addMprepaymentRuleConfiguration", "views/advancePaidDetail/addMprepaymentRuleConfiguration"); //预付款查询
		PAGE_MAP.put("updateMprepaymentRuleConfiguration", "views/advancePaidDetail/updateMprepaymentRuleConfiguration"); //预付款
		PAGE_MAP.put("updateAdvancePaymentPlan", "views/advancePaidDetail/updateAdvancePaymentPlan"); //预付款
		PAGE_MAP.put("totalAmount", "views/advancePaidDetail/totalAmount"); //预付款

	}
	/**
	 * 打开页面入口.访问方式示例： /page?page=addRules
	 * @param
	 * @return
	 * @author hewenbin
	 * @version BaseController.java, v1.0 2018年6月13日 下午5:35:28 hewenbin
	 */
	@RequestMapping("page")
    public String apiPage(@RequestParam("page") String pageCode, ModelAndView arg3) {
		String pagePath = PAGE_MAP.get(pageCode);
		pagePath = pagePath == null ? "404" : pagePath;

		MKCloudUserInfo user = (MKCloudUserInfo)getSession().getAttribute(USER_KEY_IN_SESSION);
		if(user == null){
			pagePath = PAGE_MAP.get("login");
		}
		return pagePath;
	}

	/**
	 * 打开页面入口.访问方式示例： /page_addRules
	 * @param  page 页面编码
	 * @return
	 * @author hewenbin
	 * @version BaseController.java, v1.0 2018年6月13日 下午5:35:28 hewenbin
	 */
	@RequestMapping("page_{page}")
	public String apiPage_2(@PathVariable("page") String pageCode, ModelAndView arg3) {
		String pagePath = PAGE_MAP.get(pageCode);
		pagePath = pagePath == null ? "404" : pagePath;
		return pagePath;
	}
	
}
