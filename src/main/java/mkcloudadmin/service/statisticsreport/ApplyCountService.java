package mkcloudadmin.service.statisticsreport;

import mkcloudadmin.model.mkcloud.vo.ApplyCountVO;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/24.
 */
public interface ApplyCountService {

    Map<String , Object> queryCount();
}
