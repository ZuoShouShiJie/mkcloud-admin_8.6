package mkcloudadmin.service.nobusinesspersionmanage;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.NoBusinessPerSearchDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;

import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-03 15:11
 **/
public interface NoPersionBusinessManage {

    public Map<String,Object> queryNoBusinessPersionData(NoBusinessPerSearchDTO noBusinessPerSearchDTO, Page<MKCloudApplicationImportDetail> mkCloudApplicationImportDetailPage);

    public MKCloudBusinessPeople queryBusinessPeople(String businessPeopleCode);

    public Boolean saveDeployBusinessData(MKCloudBusinessPeople mkCloudBusinessPeople,Map<String,String> param);
}
