package mkcloudadmin.service.excel;



import mkcloudadmin.model.mkcloud.po.FinanceExcelAllPage;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author yaolei
 * @Title: ExcelBiz
 * @ProjectName mkcloud-app
 * @Description: excel操作
 * @date 2018/7/6下午1:52
 */
public interface ExcelService {

    /**
     * 保存excel数据
     * @param excelAll
     * @return
     */
   public int saveExcelAll(FinanceExcelAllPage excelAll);

    /**
     *
     * 更新excel
     * @param excelAll
     * @return
     */
   public int updateExcelAll(FinanceExcelAllPage excelAll);

    /**
     *
     * @param id
     * @return
     */
   public FinanceExcelAllPage selectExcelById(long id);

    /**
     *
     * @param excelAll
     * @return
     */
   public FinanceExcelAllPage selectExcelsByExcel(FinanceExcelAllPage excelAll);

    /**
     *
     * 处理上传excel
     * @param file
     */
    void handleExcel(MultipartFile file) throws IOException, Exception;

    /**
     * 下载excel
     * @return
     * @param status
     * @param allId
     */
    Workbook createExcel(String  status, String allId);

    /**
     *
     * @param key
     * @param value
     */
    void updateAccAndSaveOrder(Long key, BigDecimal value);
}
