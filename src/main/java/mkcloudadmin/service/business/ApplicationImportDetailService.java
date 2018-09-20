package mkcloudadmin.service.business;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.ResponseResult;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudCreditCardInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ApplicationImportDetailService {
    /**
     * 根据businessPeopleId查看申请记录表
     * @param
     * @return
     */
    Map<String,Object> selectByBusinessPeopleId(
            String businessPeopleId,  String state, String applyCardTime, Page<MKCloudApplicationImportDetailVO> page);
}