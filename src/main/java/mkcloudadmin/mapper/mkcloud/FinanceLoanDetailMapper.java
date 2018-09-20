package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.FinanceLoanDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceLoanDetailMapper {
    Long deleteByPrimaryKey(Long id);

    Long insert(FinanceLoanDetail record);

    Long insertSelective(FinanceLoanDetail record);

    FinanceLoanDetail selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(FinanceLoanDetail record);

    Long updateByPrimaryKey(FinanceLoanDetail record);

    Long updateByProductIdSelective(FinanceLoanDetail record);

    /**
     * 根据产品数组查询贷款页数据
     * @param productIds
     * @return
     */
    List<FinanceLoanDetail> selectByProductIdList(@Param("productIds") List<Long> productIds);
    /**
     * 根据产品ID查询贷款数据
     * @param productId
     * @return
     */
    FinanceLoanDetail selectByProductId(@Param("productId") Long productId);

    Long deleteLogicByPrimaryKey(@Param("productId") Long productId);

}