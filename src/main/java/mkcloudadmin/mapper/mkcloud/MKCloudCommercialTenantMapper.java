package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudAdvancePaymentPlan;
import mkcloudadmin.model.mkcloud.po.MKCloudCommercialTenant;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MKCloudCommercialTenantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudCommercialTenant record);

    int insertSelective(MKCloudCommercialTenant record);

    MKCloudCommercialTenant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudCommercialTenant record);

    int updateByPrimaryKey(MKCloudCommercialTenant record);

    List<MKCloudCommercialTenant> selectByList(
            @Param("merchantCode") String  merchantCode,
            @Param("merchantName") String merchantName ,
            @Param("cooperationTimeBegin") Date cooperationTimeBegin,
            @Param("cooperationTimeEnd") Date cooperationTimeEnd ,
            @Param("cooperativeState") String cooperativeState);
        }
