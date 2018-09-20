package mkcloudadmin.service.statisticsreport.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudCommSettlementMapper;
import mkcloudadmin.model.mkcloud.vo.PaymentCountVO;
import mkcloudadmin.service.statisticsreport.IncomeSummaryService;
import mkcloudadmin.service.statisticsreport.PaymentSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/24.
 */
@Service
public class PaymentSummaryServiceImpl implements PaymentSummaryService{

    private static final Logger logger = LoggerFactory.getLogger(PaymentSummaryServiceImpl.class);

    @Autowired
    private MKCloudCommSettlementMapper mkCloudCommSettlementMapper;

    /**
     * 付款统计
     * @return
     */
    @Override
    public Map<String, Object> queryPayCount() {
        Map<String, Object> res = new HashMap<>();
        List<PaymentCountVO> countList = new ArrayList<>();
        PaymentCountVO countVO = new PaymentCountVO();
        BigDecimal paidCommission = mkCloudCommSettlementMapper.queryPaidCommission();
        if (paidCommission == null){
            paidCommission = new BigDecimal("0.00");
        }
        countVO.setPaidCommission(String.valueOf(paidCommission.setScale(2)));
        BigDecimal waitCommission = mkCloudCommSettlementMapper.queryWaitCommission();
        if(waitCommission == null){
            waitCommission = new BigDecimal("0.00");
        }
        countVO.setWaitCommission(String.valueOf(waitCommission.setScale(2)));
        countList.add(countVO);
        res.put("data",countList);
        return res;
    }
}
