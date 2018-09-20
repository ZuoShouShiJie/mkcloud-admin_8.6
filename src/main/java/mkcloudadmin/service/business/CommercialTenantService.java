package mkcloudadmin.service.business;

import mkcloudadmin.model.mkcloud.po.MKCloudCommercialTenant;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface CommercialTenantService {
    /**
     * 查询所有商户信息
     * @return
     */
    Map<String,Object> selectByCommercialTenantList(
            String  merchantCode,
            String merchantName ,
            String  cooperationTimeBegin,
            String cooperationTimeEnd ,
            String cooperativeState);
    String addCommercialTenant(Map<String, String> map);
    String updateCommercialTenant(Map<String, String> map);
}
