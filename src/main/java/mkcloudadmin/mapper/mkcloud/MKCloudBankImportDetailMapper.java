package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MKCloudBankImportDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBankImportDetail record);

    int insertSelective(MKCloudBankImportDetail record);

    MKCloudBankImportDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBankImportDetail record);

    int updateByNameCode(MKCloudBankImportDetail record);

    int updateByPrimaryKey(MKCloudBankImportDetail record);
    Long queryFeedbackBankDetailCount(@Param("method") String method);

    List<MKCloudBankImportDetail> selectByBatchId(@Param("method") String method, @Param("page") Page<MKCloudBankImportDetail> page);

    Long delFeedBackInfoImportDataByBatchId(@Param("batchId") String batchId);

    List<MKCloudBankImportDetail> selectAllDataByBatchId(@Param("batchId") String batchId);

    Long queryBackCount(@Param("applyProduct") String applyProduct,@Param("applyName") String applyName,
                         @Param("applyMobile") String applyMobile,@Param("batchId") String batchId,
                         @Param("businessPeopleCode") String businessPeopleCode,@Param("businessPeopleName") String businessPeopleName,
                         @Param("applyBeginDate") String applyBeginDate,@Param("applyEndDate") String applyEndDate);

    List<MKCloudBankImportDetail> queryBackRecord(@Param("applyProduct") String applyProduct,@Param("applyName") String applyName,
                                                          @Param("applyMobile") String applyMobile,@Param("batchId") String batchId,
                                                          @Param("businessPeopleCode") String businessPeopleCode,@Param("businessPeopleName") String businessPeopleName,
                                                          @Param("applyBeginDate") String applyBeginDate,@Param("applyEndDate") String applyEndDate,
                                                          @Param("page") Page<MKCloudBankImportDetailVO> page);

    Long selectAllCountByBusinessPeopleCode(@Param("businessPeopleCode") String businessPeopleCode);
    Long selectMonthCountByBusinessPeopleCode(@Param("businessPeopleCode") String businessPeopleCode,@Param("beginDate") String beginDate,@Param("endDate")String endDate);

    List<MKCloudBankImportDetail> selectByApplyId(@Param("applyId") String applyId);
    List<MKCloudBankImportDetail>  selectByBankImportDetail(@Param("page") Page<MKCloudBankImportDetail> page);
    int selectByBankCount();

    BigDecimal queryTotalCommission();
    BigDecimal queryNaturalTotal();
    BigDecimal queryRecommendTotal();
    BigDecimal queryPromotersTotal();
}