package mkcloudadmin.service.mkcloudproduct;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceProductMain;
import mkcloudadmin.model.mkcloud.po.MKCloudLoanInfo;
import mkcloudadmin.model.mkcloud.vo.LoanProductVO;

import java.util.Map;

/**
 * @program: mkcloud-admin
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-14 15:54
 **/
public interface LoanProduct {
    /**
      *功能描述:生成贷款产品
      * @author moruihai
      * @date 2018/7/16 16:40
      * @param: [map]
      * @return java.lang.Boolean
      */
    public Boolean createFinanceProduct(Map<String, String> map);

    /**
      *功能描述:更新贷款产品
      * @author: moruihai
      * @date: 2018/7/16 16:41
      * @param: [map]
      * @return: java.lang.Boolean
      */
    public Boolean updateFinanceProduct(Map<String,String> map);

    /**
      *功能描述:查询贷款列表
      * @author: moruihai
      * @date: 2018/7/16 16:42
      * @param: [method, isShow, financeProductPage]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    public Map<String,Object> queryLoanProductList(String productCode, String productName, String status, Page<MKCloudLoanInfo> loanInfoPage);

    /**
      *功能描述:查询单个贷款信息
      * @author: moruihai
      * @date: 2018/7/16 16:42
      * @param: [productId]
      * @return: mkcloudadmin.model.mkcloud.vo.mkcloudproduct.LoanProductVO
      */
    public LoanProductVO querySingleFinanceProductForEdit(Long productId);

    /**
      *功能描述:逻辑删除产品
      * @author: moruihai
      * @date: 2018/7/16 16:43
      * @param: [productId]
      * @return: void
      */
    public void deleteFinanceProductByProductId(Long productId);

    Boolean checkProductCode(String productCode);
}
