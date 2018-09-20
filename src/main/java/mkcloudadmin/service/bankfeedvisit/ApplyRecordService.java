package mkcloudadmin.service.bankfeedvisit;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
public interface ApplyRecordService {
    /**
     * 申卡记录查询
     * @param page
     * @param applyRecordQueryDTO
     * @return
     */
    Map<String,Object> queryApplyRecord(Page<MKCloudApplicationImportDetailVO> page, ApplyRecordQueryDTO applyRecordQueryDTO);
}
