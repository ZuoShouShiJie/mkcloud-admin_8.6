package mkcloudadmin.service.statisticsreport.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudApplicationImportDetailMapper;
import mkcloudadmin.model.mkcloud.vo.ApplyCountVO;
import mkcloudadmin.service.statisticsreport.ApplyCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/24.
 */
@Service
public class ApplyCountServiceImpl implements ApplyCountService {

    private static final Logger logger = LoggerFactory.getLogger(ApplyCountServiceImpl.class);

    @Autowired
    private MKCloudApplicationImportDetailMapper mkCloudApplicationImportDetailMapper;

    /**
     * 申请统计查询
     * @return
     */
    @Override
    public Map<String , Object> queryCount() {
        Map<String, Object> map = new HashMap<>();
        List<ApplyCountVO> volist = new ArrayList<ApplyCountVO>();
        ApplyCountVO applyCountVO = new ApplyCountVO();
        int successCount = mkCloudApplicationImportDetailMapper.querySuccessCount();
        applyCountVO.setSuccessCount(successCount);
        int successOwnCount = mkCloudApplicationImportDetailMapper.querySuccessOwnCount();
        applyCountVO.setSuccessOwnCount(successOwnCount);
        int successPromotersCount = mkCloudApplicationImportDetailMapper.querySuccessPromotersCount();
        applyCountVO.setSuccessPromotersCount(successPromotersCount);
        int errorCount = mkCloudApplicationImportDetailMapper.queryFailCount();
        applyCountVO.setErrorCount(errorCount);
        int errorOwnCount = mkCloudApplicationImportDetailMapper.queryFailOwnCount();
        applyCountVO.setErrorOwnCount(errorOwnCount);
        int errorPromotersCount = mkCloudApplicationImportDetailMapper.queryFailPromotersCount();
        applyCountVO.setErrorPromotersCount(errorPromotersCount);
        int inCount = mkCloudApplicationImportDetailMapper.queryInCount();
        applyCountVO.setInCount(inCount);
        int inOwnCount = mkCloudApplicationImportDetailMapper.queryInOwnCount();
        applyCountVO.setInOwnCount(inOwnCount);
        int inPromotersCount = mkCloudApplicationImportDetailMapper.queryInPromotersCount();
        applyCountVO.setInPromotersCount(inPromotersCount);
        applyCountVO.setAllCount(successCount+errorCount+inCount);
        volist.add(applyCountVO);
        map.put("data",volist);
        return map;
    }
}
