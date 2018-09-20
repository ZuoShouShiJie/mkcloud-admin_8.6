package mkcloudadmin.service.statisticsreport.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudBankImportDetailMapper;
import mkcloudadmin.model.mkcloud.vo.IncomeCountVO;
import mkcloudadmin.service.statisticsreport.IncomeSummaryService;
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
public class IncomeSummaryServiceImpl implements IncomeSummaryService{

    private static final Logger logger = LoggerFactory.getLogger(IncomeSummaryServiceImpl.class);

    @Autowired
    private MKCloudBankImportDetailMapper mkCloudBankImportDetailMapper;

    /**
     * 收入汇总
     * @return
     */
    @Override
    public Map<String, Object> queryIncomeCount() {
        Map<String,Object> res = new HashMap<>();
        List<IncomeCountVO> countVOList = new ArrayList<>();
        IncomeCountVO countVO = new IncomeCountVO();
        BigDecimal totalCommission = mkCloudBankImportDetailMapper.queryTotalCommission();
        if(totalCommission == null){
            totalCommission = new BigDecimal("0.00");
        }
        countVO.setTotalCommission(String.valueOf(totalCommission.setScale(2)));
        BigDecimal naturalTotal = mkCloudBankImportDetailMapper.queryNaturalTotal();
        if(naturalTotal == null){
            naturalTotal = new BigDecimal("0.00");
        }
        countVO.setNaturalTotal(String.valueOf(naturalTotal.setScale(2)));
        BigDecimal recommendTotal = mkCloudBankImportDetailMapper.queryRecommendTotal();
        if (recommendTotal == null){
            recommendTotal = new BigDecimal("0.00");
        }
        countVO.setRecommendTotal(String.valueOf(recommendTotal.setScale(2)));
        BigDecimal promotersTotal = mkCloudBankImportDetailMapper.queryPromotersTotal();
        if (promotersTotal == null){
            promotersTotal = new BigDecimal("0.00");
        }
        countVO.setPromotersTotal(String.valueOf(promotersTotal.setScale(2)));
        BigDecimal promotersProfit = recommendTotal.subtract(promotersTotal);
        countVO.setPromotersProfit(String.valueOf(promotersProfit.setScale(2)));
        BigDecimal totalProfit = naturalTotal.add(promotersProfit);
        countVO.setTotalProfit(String.valueOf(totalProfit.setScale(2)));
        countVOList.add(countVO);
        res.put("data",countVOList);
        return res;
    }
}
