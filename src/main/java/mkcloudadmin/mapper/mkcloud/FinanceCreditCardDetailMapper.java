package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.FinanceCreditCardDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceCreditCardDetailMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(FinanceCreditCardDetail record);

    Long insertSelective(FinanceCreditCardDetail record);

    FinanceCreditCardDetail selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(FinanceCreditCardDetail record);

    int updateByPrimaryKey(FinanceCreditCardDetail record);

    Long updateByProductIdSelective(FinanceCreditCardDetail record);

    /**
     * 根据产品数组查询办卡页数据
     * @param productIds
     * @return
     */
    List<FinanceCreditCardDetail> selectByProductIdList(@Param("productIds") List<Long> productIds);
    /**
     * 根据产品ID查询办卡数据
     * @param productId
     * @return
     */
    FinanceCreditCardDetail selectByProductId(@Param("productId") Long productId);

    Long deleteLogicByPrimaryKey(@Param("productId") Long productId);
}