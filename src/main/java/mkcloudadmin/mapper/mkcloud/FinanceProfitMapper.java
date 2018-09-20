package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.paging.FinanceProfitPage;
import mkcloudadmin.model.mkcloud.po.FinanceProfit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceProfitMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FinanceProfit record);

    int insertSelective(FinanceProfit record);

    FinanceProfit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceProfit record);

    int updateByPrimaryKey(FinanceProfit record);

    List<FinanceProfit> selectProfitsByUserId(@Param("userId") Long userId);

    Long queryFinanceProfitCount(FinanceProfitPage page);

    List selectFinanceProfitsByProfit(FinanceProfitPage page);
}