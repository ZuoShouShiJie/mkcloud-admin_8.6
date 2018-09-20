package mkcloudadmin.service.bankfeedback;



import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportTotal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface BankFeedBackService {

    /**
     *
     * 处理上传excel
     * @param file
     */
    String handleExcel(MultipartFile file) throws IOException, Exception;
    /**
      *功能描述: 查询反馈数据批次
      * @author: moruihai
      * @date: 2018/7/27 20:50
      * @param:  * @param null
      * @return:
      */
    public Map<String,Object> queryFeedbackBankTotalList(String method, Page<MKCloudBankImportTotal> mkCloudBankImportTotalPage);

    public Map<String, Object> queryFeedbackBankDetailList(String method, Page<MKCloudBankImportDetail> mkCloudBankImportDetailPage);

    public void delFeedBackInfoImportDataByBatchId(String  batchId);

    public String handleFeedBackInfo(String  batchId);

}
