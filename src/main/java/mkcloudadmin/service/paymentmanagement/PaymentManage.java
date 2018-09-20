package mkcloudadmin.service.paymentmanagement;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.PaymentQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudPaymentRecord;
import mkcloudadmin.model.mkcloud.vo.MKCloudUnPaidSearchVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-01 12:38
 **/
public interface PaymentManage {

    public Map<String,Object> queryUnpaidData(PaymentQueryDTO paymentQueryDTO, Page<MKCloudPaymentRecord>  mkCloudPaymentRecordPage);

    void handleUnpaidCommissionInfo(MKCloudUnPaidSearchVO mkCloudUnPaidSearchVO);

    public Map<String,Object> queryPaidData(PaymentQueryDTO paymentQueryDTO, Page<MKCloudPaymentRecord>  mkCloudPaymentRecordPage);

    /**
     * 下载excel
     * @return
     */
    Workbook createExcel(String  payee,String payeeAccount,String costType,String beginDate,String endDate);

}
