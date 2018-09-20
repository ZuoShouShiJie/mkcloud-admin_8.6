package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudPaymentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MKCloudPaymentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudPaymentRecord record);

    int insertSelective(MKCloudPaymentRecord record);

    MKCloudPaymentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudPaymentRecord record);

    int updateByPrimaryKey(MKCloudPaymentRecord record);

    List<MKCloudPaymentRecord> selectBySettlementIds(@Param("settlementIds") List<String> settlementIds);

    Long selectUnpaidDataCount(@Param("payee") String payee, @Param("payeeAccount") String payeeAccount, @Param("beginDate") Date beginDate,
                               @Param("endDate") Date endDate, @Param("costType") String costType);

    List<MKCloudPaymentRecord> selectUnpaidDataList(@Param("payee") String payee,@Param("payeeAccount") String payeeAccount,@Param("beginDate") Date beginDate,
                               @Param("endDate") Date endDate,@Param("costType") String costType,@Param("page") Page<MKCloudPaymentRecord> page);


     Long selectPaidDataCount(@Param("payee") String payee,@Param("payeeAccount") String payeeAccount,@Param("beginDate") Date beginDate,
                              @Param("endDate") Date endDate,@Param("costType") String costType,@Param("confirmBeginDate")Date confirmBeginDate,
                              @Param("confirmEndDate") Date confirmEndDate);

    List<MKCloudPaymentRecord> selectPaidDataList(@Param("payee") String payee,@Param("payeeAccount") String payeeAccount,@Param("beginDate") Date beginDate,
                                                    @Param("endDate") Date endDate,@Param("costType") String costType,@Param("confirmBeginDate")Date confirmBeginDate,
                                                  @Param("confirmEndDate") Date confirmEndDate,@Param("page") Page<MKCloudPaymentRecord> page);

    List<MKCloudPaymentRecord> selectUnpaidAllDataList(@Param("payee") String payee,@Param("payeeAccount") String payeeAccount,@Param("beginDate") String beginDate,
                                                       @Param("endDate") String endDate,@Param("costType") String costType);


    MKCloudPaymentRecord selectBySingleSettlementId(@Param("settlementId") String settlementId);
}