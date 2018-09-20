package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudBankImportTotalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBankImportTotal record);

    int insertSelective(MKCloudBankImportTotal record);

    MKCloudBankImportTotal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBankImportTotal record);

    int updateByPrimaryKey(MKCloudBankImportTotal record);

    Long queryFeedbackBankTotalCount(@Param("method") String method);

    List<MKCloudBankImportTotal> selectByBatchId(@Param("method") String method, @Param("page") Page<MKCloudBankImportTotal> page);

    Long delFeedBackInfoImportDataByBatchId(@Param("batchId") String batchId);

    Long updateFeedBackInfoImportTotalByBatchId(@Param("batchId") String batchId);

    MKCloudBankImportTotal selectDataByBatchId(@Param("batchId") String batchId);
}