package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceProductMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceProductMainMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(FinanceProductMain record);

    Long insertSelective(FinanceProductMain record);

    FinanceProductMain selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(FinanceProductMain record);

    int updateByPrimaryKey(FinanceProductMain record);

    Long queryFinanceProductCount(@Param("typ") Integer typ,@Param("productName") String productName,@Param("isShow") Integer isShow);

    List<FinanceProductMain> selectByTypeAndProductNameAndIsShow(@Param("typ") Integer typ,@Param("productName") String productName,@Param("isShow") Integer isShow,@Param("page") Page<FinanceProductMain> page);


    List querySingleFinanceProductForEdit(@Param("productId") Long productId);
    Long deleteLogicByPrimaryKey(@Param("productId") Long productId);

    //保险产品
    /**
     * 根据产品ID查询办卡数据
     * @param productId
     * @return
     */
    FinanceProductMain selectByProductId(@Param("productId") Long productId);


}