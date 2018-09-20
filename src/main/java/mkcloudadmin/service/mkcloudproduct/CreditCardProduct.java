package mkcloudadmin.service.mkcloudproduct;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudCreditCardInfo;

import java.util.Map;

/**
 * @program: mkcloud-admin
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 14:59
 **/
public interface CreditCardProduct {
    /**
     * 生成信用卡产品
     * added by moruihai
     * @param map
     * @return
     */
    public Boolean createCreditCarProduct(Map<String, String> map);

    /**
     * 更新信用卡产品数据
     * added by moruihai
     * @param map
     * @return
     */
    public Boolean updateFinanceProduct(Map<String,String> map);

    /**
     * 查询信用卡产品列表
     * added by moruihai
     *
     * @param productCode
     * @param productName
     * @param status
     * @param page
     * @return
     */
    public Map<String,Object> queryFinanceProductList(String productCode, String productName, String status, Page<MKCloudCreditCardInfo> page);

    /**
     * 查询单个信用卡产品
     * added by moruihai
     * @param productId
     * @return
     */
    public MKCloudCreditCardInfo queryCreditCardProductForEdit(Long productId);

    /**
     * 逻辑删除信用卡产品
     * added by moruihai
     * @param productId
     */
    public void delCreditCardProductById(Long productId);

    Boolean checkProductCode(String productCode);
}
