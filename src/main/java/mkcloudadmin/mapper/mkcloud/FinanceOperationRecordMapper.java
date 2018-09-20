package mkcloudadmin.mapper.mkcloud;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import mkcloudadmin.model.mkcloud.po.FinanceOperationRecord;

public interface FinanceOperationRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceOperationRecord record);

    int insertSelective(FinanceOperationRecord record);

    FinanceOperationRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceOperationRecord record);

    int updateByPrimaryKey(FinanceOperationRecord record);
    
    /**
     * 查询指定用户对指定产品的操作记录.
     * @param userIds 用户ID列表（如果==null，则不作为查询条件）
     * @param productId not null
     * @param beginTime not null
     * @param endTime not null
     * @return
     * @author hewenbin
     * @version FinanceOperationRecordMapper.java, v1.0 2018年7月12日 下午7:33:14 hewenbin
     */
	List<FinanceOperationRecord> selectCountByProductId(@Param("userIds") List<Long> userIds, @Param("productId") Long productId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}