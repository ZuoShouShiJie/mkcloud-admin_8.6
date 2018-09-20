package mkcloudadmin.service.bankfeedvisit.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudApplicationImportDetailMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudMemberInfoMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudMemberInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.bankfeedvisit.ApplyRecordService;
import mkcloudadmin.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018/8/3.
 */
@Service
public class ApplyRecordServiceImpl implements ApplyRecordService {

    private static final Logger logger = LoggerFactory.getLogger(ApplyRecordServiceImpl.class);

    @Autowired
    private MKCloudApplicationImportDetailMapper mkCloudApplicationImportDetailMapper;

    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;

    /**
     * 申卡记录查询
     * @param page
     * @param applyRecordQueryDTO
     * @return
     */
    @Override
    public Map<String,Object> queryApplyRecord(Page<MKCloudApplicationImportDetailVO> page, ApplyRecordQueryDTO applyRecordQueryDTO) {
        String applyProduct = applyRecordQueryDTO.getApplyProduct();
        String applyName = applyRecordQueryDTO.getApplyName();
        String applyMobile = applyRecordQueryDTO.getApplyMobile();
        String batchId = applyRecordQueryDTO.getBatchId();
        String status=applyRecordQueryDTO.getAuditStatus();
        String businessPeopleCode = applyRecordQueryDTO.getBusinessPeopleCode();
        String businessPeopleName = applyRecordQueryDTO.getBusinessPeopleName();
        String applyBeginDate = applyRecordQueryDTO.getApplyBeginDate();
        String applyEndDate = applyRecordQueryDTO.getApplyEndDate();

        List<MKCloudApplicationImportDetailVO> detailVOList = new ArrayList<>();
        Long total = mkCloudApplicationImportDetailMapper.queryApplyCount(applyProduct,applyName,applyMobile,batchId,businessPeopleCode,businessPeopleName,applyBeginDate,applyEndDate,status);
        if(total > 0){
            List<MKCloudApplicationImportDetail> detailList = mkCloudApplicationImportDetailMapper.queryApplyRecord(applyProduct,applyName,applyMobile,batchId,businessPeopleCode,businessPeopleName,applyBeginDate,applyEndDate,status,page);
            if(detailList != null && detailList.size() > 0){
                MKCloudApplicationImportDetailVO detailVO = null;
                Long sum = 1l;
                MKCloudMemberInfo memberInfo = null;
                for (MKCloudApplicationImportDetail detail : detailList){
                    detailVO = new MKCloudApplicationImportDetailVO();
                    detailVO.setSeqNo(sum);
                    detailVO.setId(detail.getId());
                    //根据客户姓名和手机号关联会员表查询会员编号
                    memberInfo = mkCloudMemberInfoMapper.queryMemberCodeByName(detail.getApplyName(),detail.getApplyMobile());
                    if (memberInfo != null) {
                        detailVO.setApplyId(memberInfo.getMemberCode());
                    }
                    detailVO.setApplyName(detail.getApplyName());
                    detailVO.setApplyMobile(detail.getApplyMobile());
                    detailVO.setApplyBank(detail.getApplyBank());
                    detailVO.setApplyProduct(detail.getApplyProduct());
                    detailVO.setApplyCardTime(detail.getApplyCardTime());
                    detailVO.setAuditStatus(detail.getAuditStatus());
                    detailVO.setBusinessPeopleCode(detail.getBusinessPeopleCode());
                    detailVO.setBusinessPeopleName(detail.getBusinessPeopleName());
                    detailVO.setBatchId(detail.getBatchId());
                    detailVO.setCreateTime(detail.getCreateTime()==null?"":DateUtils.dateToString(detail.getCreateTime(),DateUtils.fm_yyyy_MM_dd_HHmmss));
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
