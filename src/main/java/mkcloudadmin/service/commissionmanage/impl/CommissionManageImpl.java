package mkcloudadmin.service.commissionmanage.impl;

import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.CommissionConfirmQueryDTO;
import mkcloudadmin.model.mkcloud.dto.CommissionDetailQueryDTO;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommissionConfirmDetailVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommissionConfirmVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommissionSearchDetailVO;
import mkcloudadmin.service.commissionmanage.CommissionManage;
import mkcloudadmin.util.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 佣金管理
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-30 11:52
 **/
@Service
public class CommissionManageImpl implements CommissionManage{
    @Autowired
    private MKCloudCommissionDetailMapper mkCloudCommissionDetailMapper;
    @Autowired
    private MKCloudBusinessPeopleMapper mkCloudBusinessPeopleMapper;
    @Autowired
    private MKCloudCommSettlementMapper mkCloudCommSettlementMapper;
    @Autowired
    private MKCloudPaymentRecordMapper mkCloudPaymentRecordMapper;
    @Autowired
    private MKCloudBusinessPeopleAccountMapper mkCloudBusinessPeopleAccountMapper;
    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;



    @Override
    public Map<String, Object> queryCommissionManageData(CommissionConfirmQueryDTO commissionConfirmQueryDTO,
                                                         Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage) {

        String businessPeopleId = commissionConfirmQueryDTO.getBusinessPeopleId();
        String businessPeopleName = commissionConfirmQueryDTO.getBusinessPeopleName();
        String approvalResult =commissionConfirmQueryDTO.getApprovalResult();
        String createTime = commissionConfirmQueryDTO.getBeginDate();
        String createTimeTwo = commissionConfirmQueryDTO.getEndDate();
        Date beginDate =  StringUtils.isBlank(createTime)?null:(DateUtils.stringToDate(createTime+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date endDate =    StringUtils.isBlank(createTimeTwo)?null:(DateUtils.stringToDate(createTimeTwo+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        List<MKCloudCommissionConfirmVO> mkCloudCommissionConfirmVOList = new ArrayList<>();
        Long total =0l;
        Long total1 =0l;
        if("0".equals(approvalResult)){
            total = mkCloudCommissionDetailMapper.selectCommissionManageDataCount(businessPeopleId,businessPeopleName);
            if (total>0){
                //根据类型查主表数据
                List<MKCloudCommissionConfirmVO> mkCloudCommissionDetailList = mkCloudCommissionDetailMapper.selectCommissionManageDataList(businessPeopleId,
                        businessPeopleName,mkCloudCommissionDetailPage);

                if (mkCloudCommissionDetailList !=null && mkCloudCommissionDetailList.size()>0){
                    //遍历主表数据取得id
                    List<String> businessPeopleIds = new ArrayList<>();
                    mkCloudCommissionDetailList.forEach(mkCloudCommissionDetail ->businessPeopleIds.add(mkCloudCommissionDetail.getBusinessPeopleId()));
                    //根据推广人id 查询推广人类型
                    List<MKCloudBusinessPeople>  mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPeopleIds);

                    Map<String,MKCloudBusinessPeople>  mkcloudBusinessPeopleMap = new HashMap();
                    mkCloudBusinessPeopleList.forEach(mkc ->mkcloudBusinessPeopleMap.put(mkc.getBusinessPeopleCode(),mkc));

                    MKCloudBusinessPeople mkCloudBusinessPeople =null;
                    MKCloudCommissionConfirmVO mkCloudCommissionConfirmVO = null;

                    Long seqno = 1l;

                    for(MKCloudCommissionConfirmVO mkc :mkCloudCommissionDetailList){
                        mkCloudBusinessPeople = mkcloudBusinessPeopleMap.get(mkc.getBusinessPeopleId());

                        mkCloudCommissionConfirmVO = new MKCloudCommissionConfirmVO();
                        mkCloudCommissionConfirmVO.setBusinessPeopleId(mkc.getBusinessPeopleId());
                        mkCloudCommissionConfirmVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                        mkCloudCommissionConfirmVO.setCreateTime("");
                        mkCloudCommissionConfirmVO.setBusinessPeopleType(mkCloudBusinessPeople.getBusinessPeopleType());
                        mkCloudCommissionConfirmVO.setSettlementCommission(mkc.getSettlementCommission());
                        mkCloudCommissionConfirmVO.setStateName("待确认");
                        mkCloudCommissionConfirmVO.setConfirmTime("");

                        mkCloudCommissionConfirmVO.setBusinessPeopleLevel(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevelName(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setSettlementId("");
                        mkCloudCommissionConfirmVO.setState("0");
                        mkCloudCommissionConfirmVO.setSeqNo(seqno);
                        seqno++;

                        mkCloudCommissionConfirmVOList.add(mkCloudCommissionConfirmVO);
                    }

                }

            }
        }else if("1".equals(approvalResult)){
            total = mkCloudCommSettlementMapper.selectCommissionManageDataCount(businessPeopleId,businessPeopleName,beginDate,endDate);
            if (total>0){
                //根据类型查主表数据
                List<MKCloudCommSettlement> mkCloudCommSettlementList = mkCloudCommSettlementMapper.selectCommissionManageDataList(businessPeopleId,businessPeopleName,
                        beginDate,endDate,mkCloudCommissionDetailPage);

                if (mkCloudCommSettlementList !=null && mkCloudCommSettlementList.size()>0){
                    //遍历主表数据取得id
                    List<String> businessPeopleIds = new ArrayList<>();
                    mkCloudCommSettlementList.forEach(mkCloudCommissionDetail ->businessPeopleIds.add(mkCloudCommissionDetail.getBusinessPeopleId()));
                    //根据推广人id 查询推广人类型
                    List<MKCloudBusinessPeople>  mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPeopleIds);

                    Map<String,MKCloudBusinessPeople>  mkcloudBusinessPeopleMap = new HashMap();
                    mkCloudBusinessPeopleList.forEach(mkc ->mkcloudBusinessPeopleMap.put(mkc.getBusinessPeopleCode(),mkc));

                    List<String> settlementIds = new ArrayList<>();
                    mkCloudCommSettlementList.forEach(mkCloudCommissionDetail ->settlementIds.add(mkCloudCommissionDetail.getSettlementId()));
                    //根据汇总id 查询支付确认时间
                    List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectBySettlementIds(settlementIds);

                    Map<String,MKCloudPaymentRecord>  mkCloudPaymentRecordMap = new HashMap();
                    mkCloudPaymentRecordList.forEach(mkc ->mkCloudPaymentRecordMap.put(mkc.getSettlementId(),mkc));

                    MKCloudBusinessPeople mkCloudBusinessPeople =null;
                    MKCloudCommissionConfirmVO mkCloudCommissionConfirmVO = null;
                    MKCloudPaymentRecord mkCloudPaymentRecord = null;

                    Long seqno = 1l;

                    for(MKCloudCommSettlement mkc :mkCloudCommSettlementList){
                        mkCloudBusinessPeople = mkcloudBusinessPeopleMap.get(mkc.getBusinessPeopleId());
                        mkCloudPaymentRecord = mkCloudPaymentRecordMap.get(mkc.getSettlementId());
                        mkCloudCommissionConfirmVO = new MKCloudCommissionConfirmVO();
                        mkCloudCommissionConfirmVO.setBusinessPeopleId(mkc.getBusinessPeopleId());
                        mkCloudCommissionConfirmVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                        mkCloudCommissionConfirmVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getApprovalDate()));
                        mkCloudCommissionConfirmVO.setBusinessPeopleType(mkCloudBusinessPeople.getBusinessPeopleType());
                        mkCloudCommissionConfirmVO.setSettlementCommission(mkc.getSettlementCommission());
                        mkCloudCommissionConfirmVO.setStateName("已确认");
                        if(mkCloudPaymentRecord !=null && mkCloudPaymentRecord.getConfirmTime() !=null){
                            mkCloudCommissionConfirmVO.setConfirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudPaymentRecord.getConfirmTime()));

                        }
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevel(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevelName(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setSettlementId(mkc.getSettlementId());
                        mkCloudCommissionConfirmVO.setState("1");
                        mkCloudCommissionConfirmVO.setSeqNo(seqno);
                        seqno++;

                        mkCloudCommissionConfirmVOList.add(mkCloudCommissionConfirmVO);
                    }

                }

            }
        }else{
            total = mkCloudCommissionDetailMapper.selectCommissionManageDataCount(businessPeopleId,businessPeopleName);
                //根据类型查主表数据
                List<MKCloudCommissionConfirmVO> mkCloudCommissionDetailList = mkCloudCommissionDetailMapper.selectCommissionManageDataList(businessPeopleId,
                        businessPeopleName,mkCloudCommissionDetailPage);

                if (mkCloudCommissionDetailList !=null && mkCloudCommissionDetailList.size()>0){
                    //遍历主表数据取得id
                    List<String> businessPeopleIds = new ArrayList<>();
                    mkCloudCommissionDetailList.forEach(mkCloudCommissionDetail ->businessPeopleIds.add(mkCloudCommissionDetail.getBusinessPeopleId()));
                    //根据推广人id 查询推广人类型
                    List<MKCloudBusinessPeople>  mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPeopleIds);

                    Map<String,MKCloudBusinessPeople>  mkcloudBusinessPeopleMap = new HashMap();
                    mkCloudBusinessPeopleList.forEach(mkc ->mkcloudBusinessPeopleMap.put(mkc.getBusinessPeopleCode(),mkc));

                    MKCloudBusinessPeople mkCloudBusinessPeople =null;
                    MKCloudCommissionConfirmVO mkCloudCommissionConfirmVO = null;
                    Long seqno = 1l;
                    for(MKCloudCommissionConfirmVO mkc :mkCloudCommissionDetailList){
                        mkCloudBusinessPeople = mkcloudBusinessPeopleMap.get(mkc.getBusinessPeopleId());

                        mkCloudCommissionConfirmVO = new MKCloudCommissionConfirmVO();
                        mkCloudCommissionConfirmVO.setBusinessPeopleId(mkc.getBusinessPeopleId());
                        mkCloudCommissionConfirmVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                        mkCloudCommissionConfirmVO.setCreateTime("");
                        mkCloudCommissionConfirmVO.setBusinessPeopleType(mkCloudBusinessPeople.getBusinessPeopleType());
                        mkCloudCommissionConfirmVO.setSettlementCommission(mkc.getSettlementCommission());
                        mkCloudCommissionConfirmVO.setStateName("待确认");
                        mkCloudCommissionConfirmVO.setConfirmTime("");

                        mkCloudCommissionConfirmVO.setBusinessPeopleLevel(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevelName(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setSettlementId("");
                        mkCloudCommissionConfirmVO.setState("0");
                        mkCloudCommissionConfirmVO.setSeqNo(seqno);
                        seqno++;

                        mkCloudCommissionConfirmVOList.add(mkCloudCommissionConfirmVO);
                    }

            }
            total1 = mkCloudCommSettlementMapper.selectCommissionManageDataCount(businessPeopleId,businessPeopleName,beginDate,endDate);
                //根据类型查主表数据
                List<MKCloudCommSettlement> mkCloudCommSettlementList = mkCloudCommSettlementMapper.selectCommissionManageDataList(businessPeopleId,businessPeopleName,
                        beginDate,endDate,mkCloudCommissionDetailPage);

                if (mkCloudCommSettlementList !=null && mkCloudCommSettlementList.size()>0){
                    //遍历主表数据取得id
                    List<String> businessPeopleIds = new ArrayList<>();
                    mkCloudCommSettlementList.forEach(mkCloudCommissionDetail ->businessPeopleIds.add(mkCloudCommissionDetail.getBusinessPeopleId()));
                    //根据推广人id 查询推广人类型
                    List<MKCloudBusinessPeople>  mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPeopleIds);

                    Map<String,MKCloudBusinessPeople>  mkcloudBusinessPeopleMap = new HashMap();
                    mkCloudBusinessPeopleList.forEach(mkc ->mkcloudBusinessPeopleMap.put(mkc.getBusinessPeopleCode(),mkc));

                    List<String> settlementIds = new ArrayList<>();
                    mkCloudCommSettlementList.forEach(mkCloudCommissionDetail ->settlementIds.add(mkCloudCommissionDetail.getSettlementId()));
                    //根据汇总id 查询支付确认时间
                    List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectBySettlementIds(settlementIds);

                    Map<String,MKCloudPaymentRecord>  mkCloudPaymentRecordMap = new HashMap();
                    mkCloudPaymentRecordList.forEach(mkc ->mkCloudPaymentRecordMap.put(mkc.getSettlementId(),mkc));

                    MKCloudBusinessPeople mkCloudBusinessPeople =null;
                    MKCloudCommissionConfirmVO mkCloudCommissionConfirmVO = null;
                    MKCloudPaymentRecord mkCloudPaymentRecord = null;
                    Long seqno = 1l;
                    for(MKCloudCommSettlement mkc :mkCloudCommSettlementList){
                        mkCloudBusinessPeople = mkcloudBusinessPeopleMap.get(mkc.getBusinessPeopleId());
                        mkCloudPaymentRecord = mkCloudPaymentRecordMap.get(mkc.getSettlementId());
                        mkCloudCommissionConfirmVO = new MKCloudCommissionConfirmVO();
                        mkCloudCommissionConfirmVO.setBusinessPeopleId(mkc.getBusinessPeopleId());
                        mkCloudCommissionConfirmVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                        mkCloudCommissionConfirmVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getApprovalDate()));
                        mkCloudCommissionConfirmVO.setBusinessPeopleType(mkCloudBusinessPeople.getBusinessPeopleType());
                        mkCloudCommissionConfirmVO.setSettlementCommission(mkc.getSettlementCommission());
                        mkCloudCommissionConfirmVO.setStateName("已确认");
                        if(mkCloudPaymentRecord !=null && mkCloudPaymentRecord.getConfirmTime() !=null){
                            mkCloudCommissionConfirmVO.setConfirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudPaymentRecord.getConfirmTime()));

                        }
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevel(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setBusinessPeopleLevelName(mkCloudBusinessPeople.getBusinessPeopleLevel());
                        mkCloudCommissionConfirmVO.setSettlementId(mkc.getSettlementId());
                        mkCloudCommissionConfirmVO.setState("1");
                        mkCloudCommissionConfirmVO.setSeqNo(seqno);
                        seqno++;
                        mkCloudCommissionConfirmVOList.add(mkCloudCommissionConfirmVO);
                    }
            }
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total+total1);
        resultMap.put("data",mkCloudCommissionConfirmVOList);
        return resultMap;

    }
    @Override
    public Map<String, Object> queryCommissionManageDetail(CommissionConfirmQueryDTO commissionConfirmQueryDTO,
                                                         Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage) {

        String businessPeopleId = commissionConfirmQueryDTO.getBusinessPeopleId();
        String settlementId = commissionConfirmQueryDTO.getSettlementId();
        String state =commissionConfirmQueryDTO.getState();


        List<MKCloudCommissionConfirmDetailVO> mkCloudCommissionConfirmDetailVOList = new ArrayList();
        Long total =0l;

        if("0".equals(state)){
            total = mkCloudCommissionDetailMapper.selectCommissionManageUnDetailCount(businessPeopleId);
            if (total>0){
                //根据类型查主表数据
                List<MKCloudCommissionDetail> mkCloudCommissionDetailList = mkCloudCommissionDetailMapper.selectCommissionManageUnDetailList(businessPeopleId,mkCloudCommissionDetailPage);

                if (mkCloudCommissionDetailList !=null && mkCloudCommissionDetailList.size()>0){

                    MKCloudCommissionConfirmDetailVO mkCloudCommissionConfirmDetailVO;
                    Long num =1l;
                    for(MKCloudCommissionDetail mkc :mkCloudCommissionDetailList){
                        mkCloudCommissionConfirmDetailVO = new MKCloudCommissionConfirmDetailVO();
                        mkCloudCommissionConfirmDetailVO.setApplyName(mkc.getCusName());
                        mkCloudCommissionConfirmDetailVO.setApplyMobile(mkc.getCusTel());
                        mkCloudCommissionConfirmDetailVO.setApplyProduct(mkc.getProductName());
                        mkCloudCommissionConfirmDetailVO.setCommissionCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                        mkCloudCommissionConfirmDetailVO.setCommissionAmount(mkc.getBusinessPeopleCommission());
                        mkCloudCommissionConfirmDetailVO.setSeqNo(num);
                        num++;
                        mkCloudCommissionConfirmDetailVOList.add(mkCloudCommissionConfirmDetailVO);
                    }

                }

            }

        }else if("1".equals(state)){
            total = mkCloudCommissionDetailMapper.selectCommissionManageDoDetailCount(settlementId);
            if (total>0){
                //根据类型查主表数据
                List<MKCloudCommissionDetail> mkCloudCommissionDetailList = mkCloudCommissionDetailMapper.selectCommissionManageDoDetailList(settlementId,mkCloudCommissionDetailPage);

                if (mkCloudCommissionDetailList !=null && mkCloudCommissionDetailList.size()>0){

                    MKCloudCommissionConfirmDetailVO mkCloudCommissionConfirmDetailVO;
                    Long num =1l;
                    for(MKCloudCommissionDetail mkc :mkCloudCommissionDetailList){
                        mkCloudCommissionConfirmDetailVO = new MKCloudCommissionConfirmDetailVO();
                        mkCloudCommissionConfirmDetailVO.setApplyName(mkc.getCusName());
                        mkCloudCommissionConfirmDetailVO.setApplyMobile(mkc.getCusTel());
                        mkCloudCommissionConfirmDetailVO.setApplyProduct(mkc.getProductName());
                        mkCloudCommissionConfirmDetailVO.setCommissionCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                        mkCloudCommissionConfirmDetailVO.setCommissionAmount(mkc.getBusinessPeopleCommission());
                        mkCloudCommissionConfirmDetailVO.setSeqNo(num);
                        num++;
                        mkCloudCommissionConfirmDetailVOList.add(mkCloudCommissionConfirmDetailVO);
                    }

                }

            }


        }


        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudCommissionConfirmDetailVOList);

        return resultMap;

    }

    @Override
    @Transactional
    public void handleCommissionManage(CommissionConfirmQueryDTO commissionConfirmQueryDTO){

        String businessPeopleId = commissionConfirmQueryDTO.getBusinessPeopleId();
        String businessPeopleName = commissionConfirmQueryDTO.getBusinessPeopleName();
        BigDecimal settlementCommission = commissionConfirmQueryDTO.getSettlementCommission();

        MKCloudCommSettlement mkCloudCommSettlement = new MKCloudCommSettlement();

        mkCloudCommSettlement.setBusinessPeopleId(businessPeopleId);
        mkCloudCommSettlement.setBusinessPeopleName(businessPeopleName);
        mkCloudCommSettlement.setSettlementCommission(settlementCommission);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String settlementId = "HZ"+sdf.format(new Date())+ RandomStringUtils.randomNumeric(2);

        mkCloudCommSettlement.setSettlementId(settlementId);
        mkCloudCommSettlement.setState("0"); //待支付
        mkCloudCommSettlement.setApprovalDate(new Date());
        mkCloudCommSettlement.setApprovalResult("1"); //已确认

        mkCloudCommSettlementMapper.insertSelective(mkCloudCommSettlement);

        MKCloudCommissionDetail mkCloudCommissionDetail = new MKCloudCommissionDetail();
        mkCloudCommissionDetail.setBusinessPeopleId(businessPeopleId);
        mkCloudCommissionDetail.setApprovalResult("1");
        mkCloudCommissionDetail.setApprovalDate(new Date());
        mkCloudCommissionDetail.setSettlementId(settlementId);

        mkCloudCommissionDetailMapper.updateByBusinessPeopleIdSelective(mkCloudCommissionDetail);

        //支付记录表插入数据
        MKCloudBusinessPeopleAccount mkCloudBusinessPeopleAccount = mkCloudBusinessPeopleAccountMapper.selectByBusinessPeopleId(businessPeopleId);

        MKCloudPaymentRecord mkCloudPaymentRecord = new MKCloudPaymentRecord();
        mkCloudPaymentRecord.setCostType("1"); //佣金
        mkCloudPaymentRecord.setSettlementId(settlementId);
        mkCloudPaymentRecord.setPayableAmount(settlementCommission);
        mkCloudPaymentRecord.setPayee(businessPeopleName);
        if(mkCloudBusinessPeopleAccount != null){
            mkCloudPaymentRecord.setPayeeBank(mkCloudBusinessPeopleAccount.getAccountBank());
            mkCloudPaymentRecord.setPayeeAccount(mkCloudBusinessPeopleAccount.getAccountCode());
        }

        mkCloudPaymentRecord.setState("0"); //待支付

        mkCloudPaymentRecordMapper.insertSelective(mkCloudPaymentRecord);

    }

    @Override
    public Map<String, Object> queryCommissionSearchDetailData(CommissionDetailQueryDTO commissionDetailQueryDTO, Page<MKCloudCommissionDetail> mkCloudCommissionDetailPage) {
        String businessPeopleId = commissionDetailQueryDTO.getBusinessPeopleId();
        String businessPeopleName = commissionDetailQueryDTO.getBusinessPeopleName();
        String cusTel = commissionDetailQueryDTO.getCusTel();
        String cusName = commissionDetailQueryDTO.getCusName();
        String createTime =commissionDetailQueryDTO.getBeginDate();
        String createTimeTwo = commissionDetailQueryDTO.getEndDate();
        Date beginDate =  StringUtils.isBlank(createTime)?null:(DateUtils.stringToDate(createTime+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date endDate =    StringUtils.isBlank(createTimeTwo)?null:(DateUtils.stringToDate(createTimeTwo+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        Long total = mkCloudCommissionDetailMapper.selectCommissionSearchDetailCount(businessPeopleId,businessPeopleName,beginDate,endDate,cusTel,cusName);
        List<MKCloudCommissionSearchDetailVO> mkCloudCommissionSearchDetailVOList = new ArrayList<>();
        if (total>0){
            //根据类型查主表数据
            List<MKCloudCommissionDetail> mkCloudCommissionDetailList = mkCloudCommissionDetailMapper.selectCommissionSearchDetailList(businessPeopleId,businessPeopleName,
                    beginDate,endDate,cusTel,cusName,mkCloudCommissionDetailPage);

            if (mkCloudCommissionDetailList !=null && mkCloudCommissionDetailList.size()>0){

                List<String> settlementIds = new ArrayList<>();
                mkCloudCommissionDetailList.forEach(mkCloudCommissionDetail ->settlementIds.add(mkCloudCommissionDetail.getSettlementId()));
                //根据汇总id 查询支付确认时间
                List<MKCloudPaymentRecord> mkCloudPaymentRecordList = mkCloudPaymentRecordMapper.selectBySettlementIds(settlementIds);

                Map<String,MKCloudPaymentRecord>  mkCloudPaymentRecordMap = new HashMap();
                mkCloudPaymentRecordList.forEach(mkc ->mkCloudPaymentRecordMap.put(mkc.getSettlementId(),mkc));

                //根据手机号查会员表
                List<String> telList = new ArrayList<>();
                mkCloudCommissionDetailList.forEach(mkCloudCommissionDetail ->telList.add(mkCloudCommissionDetail.getCusTel()));

                List<MKCloudMemberInfo> mkCloudMemberInfoList = mkCloudMemberInfoMapper.selectByTelList(telList);

                Map<String,MKCloudMemberInfo>  mkCloudMemberInfoMap = new HashMap();
                mkCloudMemberInfoList.forEach(mkc ->mkCloudMemberInfoMap.put(mkc.getTel(),mkc));


                MKCloudMemberInfo mkCloudMemberInfo =null;
                MKCloudCommissionSearchDetailVO mkCloudCommissionSearchDetailVO = null;
                MKCloudPaymentRecord mkCloudPaymentRecord = null;

                Long num =1l;
                for(MKCloudCommissionDetail mkc :mkCloudCommissionDetailList){
                    mkCloudMemberInfo = mkCloudMemberInfoMap.get(mkc.getCusTel());
                    mkCloudPaymentRecord = mkCloudPaymentRecordMap.get(mkc.getSettlementId());
                    mkCloudCommissionSearchDetailVO = new MKCloudCommissionSearchDetailVO();
                    mkCloudCommissionSearchDetailVO.setBusinessPeopleId(mkc.getBusinessPeopleId());
                    mkCloudCommissionSearchDetailVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                    mkCloudCommissionSearchDetailVO.setBusinessPeopleCommission(mkc.getBusinessPeopleCommission());
                    mkCloudCommissionSearchDetailVO.setMemberCode(mkCloudMemberInfo == null?"":mkCloudMemberInfo.getMemberCode());
                    mkCloudCommissionSearchDetailVO.setCusName(mkc.getCusName());
                    mkCloudCommissionSearchDetailVO.setCusTel(mkc.getCusTel());
                    mkCloudCommissionSearchDetailVO.setCusIdNo(mkc.getCusIdNo());
                    mkCloudCommissionSearchDetailVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getCreateTime()));
                    mkCloudCommissionSearchDetailVO.setInstitutionName(mkc.getInstitutionName());
                    mkCloudCommissionSearchDetailVO.setProductName(mkc.getProductName());
                    if(mkc !=null && mkc.getApprovalDate() !=null){
                        mkCloudCommissionSearchDetailVO.setApprovalDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkc.getApprovalDate()));
                    }
                    if(mkCloudPaymentRecord !=null && mkCloudPaymentRecord.getConfirmTime() !=null){
                        mkCloudCommissionSearchDetailVO.setConfirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudPaymentRecord.getConfirmTime()));

                    }
                    mkCloudCommissionSearchDetailVO.setSeqNo(num);
                    num++;

                    mkCloudCommissionSearchDetailVOList.add(mkCloudCommissionSearchDetailVO);
                }

            }

        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudCommissionSearchDetailVOList);

        return resultMap;
    }
}
