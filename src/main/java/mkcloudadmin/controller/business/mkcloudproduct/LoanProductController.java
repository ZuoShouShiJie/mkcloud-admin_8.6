package mkcloudadmin.controller.business.mkcloudproduct;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudLoanInfo;
import mkcloudadmin.model.mkcloud.vo.LoanProductVO;
import mkcloudadmin.service.mkcloudproduct.impl.LoanProductImpl;
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
 * @description: 贷款操作controller层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-10 20:20
 **/
@Controller
@RequestMapping("loanProduct")
public class LoanProductController extends BaseApi{
    @Resource
    private LoanProductImpl loanProductImpl;
    private static final Logger logger = LoggerFactory.getLogger(LoanProductController.class);
    /**
     * 查询 api 贷款产品列表数据.
     * @param pageNum 第几页，不能为 null，并且只能时数字
     * @param pageSize 每页数据量，不能为 null，并且只能时数字
     * @param productCode 可为 null
     * @param status 可为 null
     * @return
     * @author moruihai
     * @version financeProductController.java, v1.0 2018年7月10日
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryLoanProductData")
    public Map<String, Object> queryLoanProductData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                    @RequestParam(value = "productCode", required = false) String productCode,
                                                    @RequestParam(value = "productName", required = false) String productName,
                                                    @RequestParam(value = "status", required = false) String status) {
        logger.info(LogUtil.getFormatLog("pageNum:"+pageNum+",pageSize:"+pageSize+",productCode:"+productCode+",status:"+status,"请求入参"));

        Map<String, Object>  res = new HashMap<>();
        Page<MKCloudLoanInfo> loanInfoPage = new Page<MKCloudLoanInfo>(pageSize,pageNum.longValue());

        Map<String,Object> loanProductVOMap = loanProductImpl.queryLoanProductList(productCode,productName,status,loanInfoPage);

        res.put("code",0);
        res.put("msg", "");
        res.put("count",loanProductVOMap.get("count"));
        res.put("data",loanProductVOMap.get("data"));

        return res;
    }
    /**
     * 添加/更新理财产品.
     * @author moruihai
     */
    @RequestMapping(method = RequestMethod.POST, path = "saveLoanProductData")
    @ResponseBody
    public Boolean saveLoanProductData(@RequestParam Map<String, String> param) {
        Map<String, Object> info = new HashMap<>();

            Boolean res = loanProductImpl.createFinanceProduct(param);

        return res;
    }
    /**
     * 根据productId查询贷款产品.
     * @author moruihai
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/querySingleLoanProduct")
    public Map<String, Object> querySingleLoanProduct(@RequestParam(value = "productId", required = true) Long productId) {
        logger.info(LogUtil.getFormatLog("productId:"+productId,"请求入参"));
        Map<String, Object>  res = new HashMap<>();

        LoanProductVO loanProductVO = loanProductImpl.querySingleFinanceProductForEdit(productId);
        res.put("data",loanProductVO);
        return res;
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/delLoanProductData")
    public Map<String, Object> delLoanProductData(@RequestParam(value = "productId", required = true) Long productId) {
        logger.info(LogUtil.getFormatLog("productId:"+productId,"请求入参"));
        loanProductImpl.deleteFinanceProductByProductId(productId);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "删除成功");
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/checkCode")
    public Boolean checkProductCode(@RequestParam(value = "productCode", required = true) String productCode) {
        Boolean res = loanProductImpl.checkProductCode(productCode);
        return res;
    }
}
