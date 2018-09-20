package mkcloudadmin.service.bankfeedvisit;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportBatch;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/31.
 */
public interface BankFeedVisitService {
    /**
     * 申请记录批次查询
     * @param method
     * @param mkCloudApplicationImportBatchPage
     * @return
     */
    Map<String,Object> queryBankVisitBatchList(String method, Page<MKCloudApplicationImportBatch> mkCloudApplicationImportBatchPage);

    /**
     * 上传excel
     * @param file
     * @throws IOException
     */
    String handleExcel(MultipartFile file) throws IOException,Exception;

    /**
     * 确认批次信息
     * @param batchId
     */
    String confirmVisitInfo(String batchId);

    /**
     * 删除批次
     * @param batchId
     */
    void delBatchInfo(String batchId);

    /**
     * 批次明细表查询
     * @param method
     * @param detailPage
     * @return
     */
    Map<String,Object> queryBatchDetailList(String method, Page<MKCloudApplicationImportDetail> detailPage);
}
