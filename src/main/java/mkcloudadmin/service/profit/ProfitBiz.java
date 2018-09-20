package mkcloudadmin.service.profit;


import mkcloudadmin.model.mkcloud.paging.FinanceProfitPage;
import mkcloudadmin.model.mkcloud.po.FinanceProfit;

/**
 * @author yaolei
 * @Title: ProfitBiz
 * @ProjectName mkcloud-app
 * @Description: 
 * @date 2018/7/6下午1:57
 */
public interface ProfitBiz {

    /**
     * 保存分润表
     * @param profit
     * @return
     */
    public int saveProfit(FinanceProfit profit);

    /**
     * 更新分润数据
     * @param profit
     * @return
     */
    public int updateProfit(FinanceProfit profit);

    /**
     * 查询返现
     * @param page
     * @return
     */
    FinanceProfitPage queryCommissionRecords(FinanceProfitPage page);
}
