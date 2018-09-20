package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommissionConfirmVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MKCloudCommissionDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudCommissionDetail record);

    int insertSelective(MKCloudCommissionDetail record);

    MKCloudCommissionDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudCommissionDetail record);

    int updateByPrimaryKey(MKCloudCommissionDetail record);

    Long selectCommissionManageDataCount(@Param("businessPeopleId") String businessPeopleId,@Param("businessPeopleName") String businessPeopleName);

    /**
     * selectCommissionManageDataList
     * @param businessPeopleId
     * @param businessPeopleName
     * @param page
     * @return
     */
    List<MKCloudCommissionConfirmVO>  selectCommissionManageDataList(@Param("businessPeopleId") String businessPeopleId, @Param("businessPeopleName") String businessPeopleName,
                                                                     @Param("page") Page<MKCloudCommissionDetail> page);

    /**分佣确认：查询待确认客户数据
     *
     * @param businessPeopleId
     * @return
     */
    Long selectCommissionManageUnDetailCount(@Param("businessPeopleId") String businessPeopleId);

    List<MKCloudCommissionDetail>  selectCommissionManageUnDetailList(@Param("businessPeopleId") String businessPeopleId,
                                                                      @Param("page") Page<MKCloudCommissionDetail> page);
    /**
      *功能描述:分佣确认：查询确认客户信息
      * @author: moruihai
      * @date: 2018/7/31 18:12
      * @param: [settlementId]
      * @return: java.lang.Long
      */
    Long selectCommissionManageDoDetailCount(@Param("settlementId")String settlementId);

    List<MKCloudCommissionDetail>  selectCommissionManageDoDetailList(@Param("settlementId") String settlementId,
                                                                      @Param("page") Page<MKCloudCommissionDetail> page);

    Long updateByBusinessPeopleIdSelective(MKCloudCommissionDetail mkCloudCommissionDetail);

    /**
      *功能描述:分佣明细页面查询
      * @author: moruihai
      * @date: 2018/8/1 9:41
      * @param: [businessPeopleId, businessPeopleName, beginDate, endDate, cusTel, cusName]
      * @return: java.lang.Long
      */
    Long selectCommissionSearchDetailCount(@Param("businessPeopleId") String businessPeopleId,@Param("businessPeopleName") String businessPeopleName,@Param("beginDate") Date beginDate,
                                           @Param("endDate") Date endDate,@Param("cusTel") String cusTel,@Param("cusName") String cusName);

    List<MKCloudCommissionDetail> selectCommissionSearchDetailList(@Param("businessPeopleId") String businessPeopleId, @Param("businessPeopleName") String businessPeopleName, @Param("beginDate") Date beginDate,
                                                                   @Param("endDate") Date endDate, @Param("cusTel") String cusTel, @Param("cusName") String cusName, @Param("page") Page<MKCloudCommissionDetail> page);

    Long updateStateBySettlementId(@Param("settlementId") String settlementId);

    /**
      *功能描述:
      * @author: moruihai
      * @date: 2018/8/4 21:14
      * @param:  * @param null
      * @return:
      */
    MKCloudCommissionDetail selectByBusinessPeopleCodeAndClientInfo(@Param("businessPeopleCode") String businessPeopleCode,@Param("cusTel") String cusTel,
                                                                    @Param("batchId") String batchId,@Param("productName") String productName);

    List<MKCloudCommissionDetail>   selectCommApprovalDates(@Param("beginDate") String beginDate,
                                                            @Param("endDate") String endDate,@Param("page") Page<MKCloudCommissionDetail> page);

    Long  selectCommApprovalDateCount(@Param("beginDate") String beginDate,
                                      @Param("endDate") String endDate);
}