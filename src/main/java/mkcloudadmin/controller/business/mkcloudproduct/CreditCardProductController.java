package mkcloudadmin.controller.business.mkcloudproduct;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.model.base.Page;

import mkcloudadmin.model.mkcloud.po.MKCloudCreditCardInfo;
import mkcloudadmin.service.mkcloudproduct.impl.CreditCardProductImpl;
import mkcloudadmin.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mkcloud-admin
 *
 * @description: 信用卡产品controller层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-10 11:43
 **/
@Controller
@RequestMapping("creditCardProduct")
public class CreditCardProductController extends BaseApi {
    @Resource
    private CreditCardProductImpl creditCardProductImpl;
    private static final Logger logger = LoggerFactory.getLogger(CreditCardProductController.class);
    /**
     * 查询 api 信用卡产品列表数据.
     * @param pageNum 第几页，不能为 null，并且只能时数字
     * @param pageSize 每页数据量，不能为 null，并且只能时数字
     * @param method 可为 null
     * @param isShow 可为 null
     * @return
     * @author moruihai
     * @version financeProductController.java, v1.0 2018年7月10日
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryCreditCardProductData")
    public Map<String, Object> queryCreditCardProductData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                         @RequestParam(value = "productCode", required = false) String productCode,
                                                         @RequestParam(value = "productName", required = false) String productName,
                                                         @RequestParam(value = "status", required = false) String status) {
        logger.info(LogUtil.getFormatLog("pageNum:"+pageNum+",pageSize:"+pageSize+",productCode:"+productCode+",productName:"+productName+",status:"+status,"请求入参"));

        Map<String, Object>  res = new HashMap<>();
        Page<MKCloudCreditCardInfo> cardInfoPage = new Page<MKCloudCreditCardInfo>(pageSize,pageNum.longValue());

        Map<String,Object> map = creditCardProductImpl.queryFinanceProductList(productCode,productName,status,cardInfoPage);

        res.put("code",0);
        res.put("msg", "");
        res.put("count",map.get("count"));
        res.put("data",map.get("data"));

        return res;
    }
    /**
     * 添加/更新信用卡产品.
     * @author moruihai
     */
    @RequestMapping(method = RequestMethod.POST, path = "saveCreditCardProductData")
    @ResponseBody
    public Boolean saveCreditCardProductData(@RequestParam Map<String, String> param) {
        Boolean res = creditCardProductImpl.createCreditCarProduct(param);
        return res;
    }
    /**
     * 根据productId查询信用卡产品.
     * @author moruihai
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/querySingleCreditCardProduct")
    public Map<String, Object> querySingleCreditCardProduct(@RequestParam(value = "productId", required = true) Long productId) {
        logger.info(LogUtil.getFormatLog("productId:"+productId,"请求入参"));

        Map<String, Object>  res = new HashMap<>();
        MKCloudCreditCardInfo creditCardInfo = creditCardProductImpl.queryCreditCardProductForEdit(productId);
        res.put("data",creditCardInfo);
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/delCreditCardProductData")
    public Map<String, Object> delCreditCardProductData(@RequestParam(value = "productId", required = true) Long productId) {
        logger.info(LogUtil.getFormatLog("productId:"+productId,"请求入参"));
        creditCardProductImpl.delCreditCardProductById(productId);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "删除成功");
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/checkCode")
    public Boolean checkProductCode(@RequestParam(value = "productCode", required = true) String productCode) {
        Boolean res = creditCardProductImpl.checkProductCode(productCode);
        return res;
    }
}
