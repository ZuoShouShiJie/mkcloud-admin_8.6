package mkcloudadmin.service.bankfeedback;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
public interface BackRecordService {


    Map<String,Object> queryBackRecord(Page<MKCloudBankImportDetailVO> page, ApplyRecordQueryDTO applyRecordQueryDTO);
}
