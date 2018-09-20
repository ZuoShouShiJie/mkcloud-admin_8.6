package mkcloudadmin.service.bankfeedback.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudBankImportDetailMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudCommissionDetailMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudMemberInfoMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudPaymentRecordMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudCommissionDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudMemberInfo;
import mkcloudadmin.model.mkcloud.po.MKCloudPaymentRecord;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailSearchVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.service.bankfeedback.BackRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
@Service
public class BackRecordServiceImpl implements BackRecordService {

    private static final Logger logger = LoggerFactory.getLogger(BackRecordServiceImpl.class);

    @Autowired
    private MKCloudBankImportDetailMapper mkCloudBankImportDetailMapper;
    @Autowired
    private MKCloudCommissionDetailMapper mkCloudCommissionDetailMapper;
    @Autowired
    private MKCloudPaymentRecordMapper mkCloudPaymentRecordMapper;
    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;

    /**
     * 反馈记录查询
     * @param page
     * @param applyRecordQueryDTO
     * @return
     */
    @Override
    public Map<String,Object> queryBackRecord(Page<MKCloudBankImportDetailVO> page, ApplyRecordQueryDTO applyRecordQueryDTO) {
        String applyProduct = applyRecordQueryDTO.getApplyProduct();
        String applyName = applyRecordQueryDTO.getApplyName();
        String applyMobile = applyRecordQueryDTO.getApplyMobile();
        String batchId = applyRecordQueryDTO.getBatchId();
        String businessPeopleCode = applyRecordQueryDTO.getBusinessPeopleCode();
        String businessPeopleName = applyRecordQueryDTO.getBusinessPeopleName();
        String applyBeginDate = applyRecordQueryDTO.getApplyBeginDate();
        String applyEndDate = applyRecordQueryDTO.getApplyEndDate();

        List<MKCloudBankImportDetailVO> detailVOList = new ArrayList<>();
        Long total = mkCloudBankImportDetailMapper.queryBackCount(applyProduct,applyName,applyMobile,batchId,businessPeopleCode,businessPeopleName,applyBeginDate,applyEndDate);
        if(total > 0){
            List<MKCloudBankImportDetail> detailList = mkCloudBankImportDetailMapper.queryBackRecord(applyProduct,applyName,applyMobile,batchId,businessPeopleCode,businessPeopleName,applyBeginDate,applyEndDate,page);
            if(detailList != null && detailList.size() > 0){
                MKCloudBankImportDetailSearchVO detailVO = null;
                Long sum = 1l;
                MKCloudCommissionDetail mkCloudCommissionDetail = null;
                MKCloudPaymentRecord mkCloudPaymentRecord = null;
                MKCloudMemberInfo mkCloudMemberInfo = null;
                for (MKCloudBankImportDetail detail : detailList){
                    //根据推荐人Code和客户tel查询
                    mkCloudCommissionDetail = mkCloudCommissionDetailMapper.selectByBusinessPeopleCodeAndClientInfo(detail.getBusinessPeopleCode(),detail.getCusTel(),detail.getBatchId(),detail.getProductName());
                    if(mkCloudCommissionDetail != null){
                        mkCloudPaymentRecord = mkCloudPaymentRecordMapper.selectBySingleSettlementId(mkCloudCommissionDetail.getSettlementId());
                    }

                    detailVO = new MKCloudBankImportDetailSearchVO();
                    detailVO.setSeqNo(sum);
                    detailVO.setCommissionState("待分佣");

                    //根据推荐人Code和客户tel查询
                    mkCloudCommissionDetail = mkCloudCommissionDetailMapper.selectByBusinessPeopleCodeAndClientInfo(detail.getBusinessPeopleCode(),detail.getCusTel(),detail.getBatchId(),detail.getProductName());
                    if(mkCloudCommissionDetail != null && "1".equals(mkCloudCommissionDetail.getState())){
                        mkCloudPaymentRecord = mkCloudPaymentRecordMapper.selectBySingleSettlementId(mkCloudCommissionDetail.getSettlementId());
                        if(mkCloudPaymentRecord != null){
                            detailVO.setCommissionState("已分佣");
                            detailVO.setConfirmDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudPaymentRecord.getConfirmTime()));

                        }
                    }

                    detailVO.setId(detail.getId());
                    //根据客户姓名和手机号关联会员表查询会员编号
                    mkCloudMemberInfo = mkCloudMemberInfoMapper.queryMemberCodeByName(detail.getCusName(),detail.getCusTel());
                    if (mkCloudMemberInfo != null && mkCloudMemberInfo.getMemberCode() != null && !mkCloudMemberInfo.getMemberCode().equals("")) {
                        detailVO.setApplyId(mkCloudMemberInfo.getMemberCode());
                    }
                    detailVO.setApplyName(detail.getCusName());
                    detailVO.setApplyMobile(detail.getCusTel());
                    detailVO.setApplyBank(detail.getInstitutionName());
                    detailVO.setApplyProduct(detail.getProductName());
                    detailVO.setApplyCardDate(detail.getApplyCardDate());
                    detailVO.setAuditStatus(detail.getAuditStatus());
                    detailVO.setBusinessPeopleCode(detail.getBusinessPeopleCode());
                    detailVO.setBusinessPeopleName(detail.getBusinessPeopleName());
                    detailVO.setBatchId(detail.getBatchId());
                    detailVO.setCommission(detail.getCommission());
                    detailVO.setInCommission(detail.getInCommission());
                    detailVO.setOutCommission(detail.getOutCommission());
                    detailVO.setOutCommission2(detail.getOutCommission2());
                    detailVO.setOutCommission3(detail.getOutCommission3());
                    detailVOList.add(detailVO);
                    sum++;
                }
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("count",total);
        res.put("data",detailVOList);
        return res;
    }
}
