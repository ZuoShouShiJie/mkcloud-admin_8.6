package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportBatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudApplicationImportBatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudApplicationImportBatch record);

    int insertSelective(MKCloudApplicationImportBatch record);

    MKCloudApplicationImportBatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudApplicationImportBatch record);

    int updateByPrimaryKey(MKCloudApplicationImportBatch record);
    /**
     * 查询个数
     * @param method
     * @return
     */
    Long queryMKCloudImportBatchCount(@Param("method") String method);

    /**
     * 查询批次列表
     * @param method
     * @param page
     * @return
     */
    List<MKCloudApplicationImportBatch> queryMKCloudImportBatchList(@Param("method") String method, @Param("page") Page<MKCloudApplicationImportBatch> page);

    /**
     * 确认批次信息
     * @param batchId
     *//*
    Long confirmVisitInfo(@Param("batchId") String batchId);*/

    /**
     * 删除批次信息
     * @param batchId
     * @return
     */
    Long delByBatchId(@Param("batchId") String batchId);

    Long updateApplicationImportBatchByBatchId(@Param("batchId") String batchId);

    /**
     * 根据batchID获取数据
     * @param batchId
     * @return
     */
    MKCloudApplicationImportBatch selectByBatchId(String batchId);
}