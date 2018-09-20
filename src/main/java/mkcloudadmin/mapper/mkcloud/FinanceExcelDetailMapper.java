package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.mkcloud.po.FinanceExcelDetail;

import java.util.List;

public interface FinanceExcelDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceExcelDetail record);

    int insertSelective(FinanceExcelDetail record);

    FinanceExcelDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceExcelDetail record);

    int updateByPrimaryKey(FinanceExcelDetail record);

    List<FinanceExcelDetail> selectByPrimaryKeyAndStatus(FinanceExcelDetail detail);
}