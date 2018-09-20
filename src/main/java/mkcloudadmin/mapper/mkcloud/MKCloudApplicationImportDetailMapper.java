package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudApplicationImportDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudApplicationImportDetail record);

    int insertSelective(MKCloudApplicationImportDetail record);

    MKCloudApplicationImportDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudApplicationImportDetail record);

    int updateByNameCode(MKCloudApplicationImportDetail record);


    int updateByPrimaryKey(MKCloudApplicationImportDetail record);

    /**
     * 查询明细条数
     * @param method
     * @return
     */
    Long queryBatchDetailCount(@Param("method") String method);

    /**
     * 查询批次明细列表
     * @param method
     * @param page
     * @return
     */
    List<MKCloudApplicationImportDetail> queryBatchDetailList(@Param("method") String method, @Param("page") Page<MKCloudApplicationImportDetail> page);

    /**
     * 根据批次号删除明细
     * @param batchId
     * @return
     */
    Long delBybatchId(@Param("batchId") String batchId);

    List<MKCloudApplicationImportDetailVO> selectByBusinessPeopleId(
            @Param("page") Page<MKCloudApplicationImportDetailVO> page ,
            @Param("businessPeopleCode") String businessPeopleCode,
           /* @Param("state") String state,*/
            @Param("applyCardTime") String applyCardTime);
    int selectCount(@Param("businessPeopleCode") String businessPeopleCode,
                    /*@Param("state") String state,*/
                    @Param("applyCardTime") String applyCardTime);


    /**
      *功能描述:根据批次号查询所有的该批次数据
      * @author: moruihai
      * @date: 2018/8/3 13:55
      * @param: [batchId]
      * @return: java.util.List<mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail>
      */
    List<MKCloudApplicationImportDetail> selectAllDataByBatchId(@Param("batchId") String batchId);

    Long selectNoBusinessPersionDataCount(@Param("applyName") String applyName,@Param("applyMobile") String applyMobile,
                                          @Param("beginDate") String beginDate,@Param("endDate") String endDate);
    List<MKCloudApplicationImportDetail> selectNoBusinessPersionDataList(@Param("applyName") String applyName,@Param("applyMobile") String applyMobile,
                                          @Param("beginDate") String beginDate,@Param("endDate") String endDate,
                                          @Param("page") Page<MKCloudApplicationImportDetail> page);

    /**
     * 多条件查询条数
     * @param applyProduct
     * @param applyName
     * @param applyMobile
     * @param batchId
     * @param businessPeopleCode
     * @param businessPeopleName
     * @param applyBeginDate
     * @param applyEndDate
     * @return
     */
    Long queryApplyCount(@Param("applyProduct") String applyProduct,@Param("applyName") String applyName,
                         @Param("applyMobile") String applyMobile,@Param("batchId") String batchId,
                         @Param("businessPeopleCode") String businessPeopleCode,@Param("businessPeopleName") String businessPeopleName,
                         @Param("applyBeginDate") String applyBeginDate,@Param("applyEndDate") String applyEndDate,@Param("status") String status);

    /**
     * 条件查询申卡记录
     * @param applyProduct
     * @param applyName
     * @param applyMobile
     * @param batchId
     * @param businessPeopleCode
     * @param businessPeopleName
     * @param applyBeginDate
     * @param applyEndDate
     * @param page
     * @return
     */
    List<MKCloudApplicationImportDetail> queryApplyRecord(@Param("applyProduct") String applyProduct,@Param("applyName") String applyName,
                                                            @Param("applyMobile") String applyMobile,@Param("batchId") String batchId,
                                                            @Param("businessPeopleCode") String businessPeopleCode,@Param("businessPeopleName") String businessPeopleName,
                                                            @Param("applyBeginDate") String applyBeginDate,@Param("applyEndDate") String applyEndDate,@Param("status") String status,
                                                            @Param("page") Page<MKCloudApplicationImportDetailVO> page);


    Long updateByApplyIdAndPrduct(MKCloudApplicationImportDetail record);
    MKCloudApplicationImportDetail selectByVisitId(@Param("visitId") String applyId);
    int  updateByVisitId(MKCloudApplicationImportDetail detail);
    List<MKCloudApplicationImportDetail> selectByAuditStatus(@Param("auditStatus") String auditStatus,@Param("version") String version);

    int querySuccessCount();
    int querySuccessOwnCount();
    int querySuccessPromotersCount();
    int queryFailCount();
    int queryFailOwnCount();
    int queryFailPromotersCount();
    int queryInCount();
    int queryInOwnCount();
    int queryInPromotersCount();
}