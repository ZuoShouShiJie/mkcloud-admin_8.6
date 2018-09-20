package mkcloudadmin.service.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.ResponseResult;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;

import mkcloudadmin.model.mkcloud.vo.MKCloudBusinessPeopleDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 推广人员管理
 */
public interface BusinessPeopleService {

    /**
     * 新增内部推广人员
     * @param map
     * @return
     */
    String   addBusinessPeopleIn(Map<String, String> map);
    /**
     * 修改内部推广人员
     */
    String  updateBusinessPeopleIn(Map<String, String> map);
    /**
     * (后管)
     * 根据businessPeopleCode查看查看推广人员信息   ，
     * @param businessPeopleCode
     * @return
     */
    MKCloudBusinessPeopleDetailVo selectPeopleCode(String businessPeopleCode);
    /**
     * (后管)
     * 根据条件查看推广人员
     * @param
     * @return
     */
    Map<String,Object>  selectQueryByCriteriaPeople(Page<MKCloudBusinessPeople> page,
           String businessPeopleType,
             String businessPeopleCode,
           String businessPeopleName,
            String tel,
             String state);

    /**
     * 审核外部推广人，状态1为通过0为拒绝
     * @param statc
     * @param peopleCode
     */
    void updateByStatic(String statc,String peopleCode);
}
