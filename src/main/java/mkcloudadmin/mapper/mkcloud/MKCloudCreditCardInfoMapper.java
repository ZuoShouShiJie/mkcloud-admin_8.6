package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudCreditCardInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudCreditCardInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudCreditCardInfo record);

    int insertSelective(MKCloudCreditCardInfo record);

    MKCloudCreditCardInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudCreditCardInfo record);

    int updateByPrimaryKey(MKCloudCreditCardInfo record);


    MKCloudCreditCardInfo selectByProductName(@Param("productName") String productName);

    List<MKCloudCreditCardInfo> selectProducts(@Param("productCode") String productCode, @Param("productName") String productName, @Param("status") String status,
                                               @Param("page") Page<MKCloudCreditCardInfo> page);

    Long selectProductsCount(@Param("productCode") String productCode, @Param("productName") String productName, @Param("status") String status);

    int deleteLogicByPrimaryKey(@Param("productId") Long productId);

    Long selectByProductCode(@Param("productCode") String productCode);
}