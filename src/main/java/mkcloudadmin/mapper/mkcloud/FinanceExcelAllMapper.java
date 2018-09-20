package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.mkcloud.po.FinanceExcelAllPage;

import java.util.List;

public interface FinanceExcelAllMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FinanceExcelAllPage record);

    int insertSelective(FinanceExcelAllPage record);

    FinanceExcelAllPage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceExcelAllPage record);

    int updateByPrimaryKey(FinanceExcelAllPage record);

    List<FinanceExcelAllPage> selectExcelsByExcel(FinanceExcelAllPage excelAll);

    Long queryExcelCount(FinanceExcelAllPage excelAll);
}