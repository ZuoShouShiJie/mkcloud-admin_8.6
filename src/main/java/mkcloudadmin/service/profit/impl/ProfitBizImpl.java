package mkcloudadmin.service.profit.impl;

import mkcloudadmin.mapper.mkcloud.FinanceProfitMapper;
import mkcloudadmin.model.mkcloud.paging.FinanceProfitPage;
import mkcloudadmin.model.mkcloud.po.FinanceProfit;
import mkcloudadmin.service.profit.ProfitBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yaolei
 * @Title: ProfitBizImpl
 * @ProjectName mkcloud-app
 * @Description: 
 * @date 2018/7/6下午1:58
 */
@Component
public class ProfitBizImpl implements ProfitBiz {

    private static final Logger logger = LoggerFactory.getLogger(ProfitBizImpl.class);

    @Autowired
    public FinanceProfitMapper mapper;

    /**
     * 保存
     * @param profit
     * @return
     */
    public int saveProfit(FinanceProfit profit){
       return  mapper.insertSelective(profit);
    }

    /**
     * 更新分润表
     * @param profit
     * @return
     */
    public int updateProfit(FinanceProfit profit){
       return mapper.updateByPrimaryKeySelective(profit);
    }

    /**
     *
     * @param page
     * @return
     */
    @Override
    public FinanceProfitPage queryCommissionRecords(FinanceProfitPage page) {

        Long total = mapper.queryFinanceProfitCount(page);
        if (total > 0) {
            List list = mapper.selectFinanceProfitsByProfit(page);
            page.setDatas(list);
        }
        page.setTotal(total.intValue());
        return page;
    }

}
