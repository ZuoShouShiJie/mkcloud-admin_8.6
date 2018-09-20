package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceProductMainPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceProductMainPageMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(FinanceProductMainPage record);

    Long insertSelective(FinanceProductMainPage record);

    FinanceProductMainPage selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(FinanceProductMainPage record);

    Long updateByPrimaryKey(FinanceProductMainPage record);

    Long deleteLogicByPrimaryKey(@Param("productId") Long productId);

    Long queryFinanceProductCount(@Param("category") Integer category,@Param("productName") String productName,@Param("isShow") Integer isShow);

    List<FinanceProductMainPage> selectByCategoryAndProductNameAndIsShow(@Param("category") Integer category,@Param("productName") String productName,@Param("isShow") Integer isShow,@Param("page") Page<FinanceProductMainPage> page);


}