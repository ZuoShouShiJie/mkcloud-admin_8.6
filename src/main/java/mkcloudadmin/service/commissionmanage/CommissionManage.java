package mkcloudadmin.service.commissionmanage;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.CommissionConfirmQueryDTO;
import mkcloudadmin.model.mkcloud.dto.CommissionDetailQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;

import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-30 11:51
 **/
public interface CommissionManage {
    public Map<String,Object> queryCommissionManageData(CommissionConfirmQueryDTO commissionConfirmQueryDTO,Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage);

    public Map<String, Object> queryCommissionManageDetail(CommissionConfirmQueryDTO commissionConfirmQueryDTO,
                                                         Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage);

    public void handleCommissionManage(CommissionConfirmQueryDTO commissionConfirmQueryDTO);

    public Map<String, Object> queryCommissionSearchDetailData(CommissionDetailQueryDTO commissionDetailQueryDTO, Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage);
}
