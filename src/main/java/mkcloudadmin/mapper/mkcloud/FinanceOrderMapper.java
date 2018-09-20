package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.mkcloud.po.FinanceOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FinanceOrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FinanceOrder record);

    int insertSelective(FinanceOrder record);

    FinanceOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceOrder record);

    int updateByPrimaryKey(FinanceOrder record);

    List<FinanceOrder> selectByOrder(FinanceOrder order);

    List<FinanceOrder> selectOrders(@Param("userId") Long userId,@Param("date") Date date);

    int updateOrdersByIds(@Param("list") List<FinanceOrder> list);
}