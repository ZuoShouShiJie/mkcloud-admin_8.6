package mkcloudadmin.service.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudPrepaymentRuleConfiguration;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;

import java.util.List;
import java.util.Map;

public interface PrepaymentRuleConfigurationService {
    /**
     * 成交明细查询
     * @param detailPage
     * @return
     */
    Map<String,Object> selectApplicationImportDetail(Page<MKCloudBankImportDetailVO> detailPage, String beginDate, String endDate);

    Map<String,Object> selectPrepaymentRuleConfiguration(String code,String name);


    String addPrepaymentRuleConfiguration(Map<String, String> map);
}