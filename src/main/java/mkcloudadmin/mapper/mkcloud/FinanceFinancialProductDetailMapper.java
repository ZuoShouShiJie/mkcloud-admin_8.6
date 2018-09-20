package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.mkcloud.po.FinanceFinancialProductDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceFinancialProductDetailMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(FinanceFinancialProductDetail record);

    Long insertSelective(FinanceFinancialProductDetail record);

    FinanceFinancialProductDetail selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(FinanceFinancialProductDetail record);

    Long updateByPrimaryKey(FinanceFinancialProductDetail record);
    Long updateByProductIdSelective(FinanceFinancialProductDetail record);
    Long deleteByProductId(@Param("productId") Long productId);

    /**
     * 根据产品数组查询我要理财页数据
     * @param productIds
     * @return
     */
    List<FinanceFinancialProductDetail> selectByProductIdList(@Param("productIds") List<Long> productIds);
    /**
     * 根据产品ID查询我要理财页数据
     * @param productId
     * @return
     */
    FinanceFinancialProductDetail selectByProductId(@Param("productId") Long productId);

    Long deleteLogicByPrimaryKey(@Param("productId") Long productId);
}