package mkcloudadmin.service.nobusinesspersionmanage.impl;

import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.NoBusinessPerSearchDTO;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudNoBusiPerSearchVO;
import mkcloudadmin.service.nobusinesspersionmanage.NoPersionBusinessManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 无推广人员操作service层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-03 15:12
 **/
@Service
public class NoPersionBusinessManageImpl implements NoPersionBusinessManage{
    @Autowired
    private MKCloudApplicationImportDetailMapper mkCloudApplicationImportDetailMapper;
    @Autowired
    private MKCloudBusinessPeopleMapper mkCloudBusinessPeopleMapper;
    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;
    @Autowired
    private MKCloudBusinessPeopleAttMapper mkCloudBusinessPeopleAttMapper;
    @Autowired
    private MKCloudBusinessPeopleDeployLogMapper mkCloudBusinessPeopleDeployLogMapper;
@Autowired
    private MKCloudBankImportDetailMapper mkCloudBankImportDetailMapper;

    @Override
    public Map<String, Object> queryNoBusinessPersionData(NoBusinessPerSearchDTO noBusinessPerSearchDTO, Page<MKCloudApplicationImportDetail> mkCloudApplicationImportDetailPage) {
        String applyName  = noBusinessPerSearchDTO.getApplyName();
        String applyMobile = noBusinessPerSearchDTO.getApplyMobile();
        String beginDate = noBusinessPerSearchDTO.getBeginDate();
        String endDate = noBusinessPerSearchDTO.getEndDate();

        List<MKCloudNoBusiPerSearchVO> mkCloudNoBusiPerSearchVOList = new ArrayList<>();
        Long total = mkCloudApplicationImportDetailMapper.selectNoBusinessPersionDataCount(applyName,applyMobile,beginDate,endDate);
        if (total>0){
            //根据类型查主表数据
            List<MKCloudApplicationImportDetail> mkCloudApplicationImportDetailList = mkCloudApplicationImportDetailMapper.selectNoBusinessPersionDataList(applyName,applyMobile,beginDate,endDate,mkCloudApplicationImportDetailPage);

            if (mkCloudApplicationImportDetailList !=null && mkCloudApplicationImportDetailList.size()>0){
                MKCloudNoBusiPerSearchVO mkCloudNoBusiPerSearchVO = null;
                Long num =1l;
                for(MKCloudApplicationImportDetail mkc :mkCloudApplicationImportDetailList){
                    mkCloudNoBusiPerSearchVO = new MKCloudNoBusiPerSearchVO();

                    mkCloudNoBusiPerSearchVO.setApplyName(mkc.getApplyName());
                    mkCloudNoBusiPerSearchVO.setApplyMobile(mkc.getApplyMobile());
                    mkCloudNoBusiPerSearchVO.setApplyIdCard(mkc.getApplyIdCard());
                    mkCloudNoBusiPerSearchVO.setApplyProduct(mkc.getApplyProduct());
                    mkCloudNoBusiPerSearchVO.setApplyCardTime(mkc.getApplyCardTime());
                    mkCloudNoBusiPerSearchVO.setBusinessPeopleCode(mkc.getBusinessPeopleCode());
                    mkCloudNoBusiPerSearchVO.setBusinessPeopleName(mkc.getBusinessPeopleName());
                    mkCloudNoBusiPerSearchVO.setApplyStatus(mkc.getAuditStatus());
                    mkCloudNoBusiPerSearchVO.setClaim("待认领");
                    mkCloudNoBusiPerSearchVO.setId(mkc.getId());

                    mkCloudNoBusiPerSearchVOList.add(mkCloudNoBusiPerSearchVO);
                }

            }

        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudNoBusiPerSearchVOList);

        return resultMap;
    }

    @Override
    public MKCloudBusinessPeople queryBusinessPeople(String businessPeopleCode) {
        MKCloudBusinessPeople mkCloudBusinessPeople = mkCloudBusinessPeopleMapper.selectPeopleCode(businessPeopleCode);
        return mkCloudBusinessPeople;

    }

    @Override
    @Transactional
    public Boolean saveDeployBusinessData(MKCloudBusinessPeople mkCloudBusinessPeople,Map<String, String> param) {
        String businessPeopleCode = param.get("businessPeopleCode");
        String logoUrl = param.get("logoUrl");
        Long id = Long.valueOf(param.get("pid"));
        String applyName = param.get("applyName");
        String applyMobile1 = param.get("applyMobile");

        String applyMobile = applyMobile1.substring(0, 3) + "____" + applyMobile1.substring(7, 11);

        //更新记录导入表
        MKCloudApplicationImportDetail mkCloudApplicationImportDetail = new MKCloudApplicationImportDetail();
        mkCloudApplicationImportDetail.setBusinessPeopleName(mkCloudBusinessPeople.getBusinessPeopleName());
        mkCloudApplicationImportDetail.setBusinessPeopleCode(mkCloudBusinessPeople.getBusinessPeopleCode());
        mkCloudApplicationImportDetail.setApplyName(applyName);
        mkCloudApplicationImportDetail.setApplyMobile(applyMobile1);
        mkCloudApplicationImportDetailMapper.updateByNameCode(mkCloudApplicationImportDetail);

        MKCloudBankImportDetail mkCloudBankImportDetail = new MKCloudBankImportDetail();
        mkCloudBankImportDetail.setBusinessPeopleName(mkCloudBusinessPeople.getBusinessPeopleName());
        mkCloudBankImportDetail.setBusinessPeopleCode(mkCloudBusinessPeople.getBusinessPeopleCode());
        mkCloudBankImportDetail.setCusName(applyName);
        mkCloudBankImportDetail.setCusTel(applyMobile1);
        mkCloudBankImportDetailMapper.updateByNameCode(mkCloudBankImportDetail);

        //更新会员表
      //  MKCloudMemberInfo mkCloudMemberInfoSearch = mkCloudMemberInfoMapper.selectByFullConditionInfo(applyMobile,applyName);
        MKCloudMemberInfo mkCloudMemberInfoSearch = mkCloudMemberInfoMapper.selectByClientBaseInfo(applyMobile,applyName);
        if(mkCloudMemberInfoSearch != null){
            MKCloudMemberInfo mkCloudMemberInfo = new MKCloudMemberInfo();
            mkCloudMemberInfo.setBusinessPeopleCode(mkCloudBusinessPeople.getBusinessPeopleCode());
            mkCloudMemberInfo.setId(mkCloudMemberInfoSearch.getId());
            mkCloudMemberInfoMapper.updateByPrimaryKeySelective(mkCloudMemberInfo);
        }


        //mkCloudMemberInfo.setBusinessPeopleId(mkCloudBusinessPeople);
        //插入分派证明
        MKCloudBusinessPeopleAtt mkCloudBusinessPeopleAtt = new MKCloudBusinessPeopleAtt();
        mkCloudBusinessPeopleAtt.setBusinessPeopleId(mkCloudBusinessPeople.getBusinessPeopleCode());
        mkCloudBusinessPeopleAtt.setAttachmentType("图片");
        mkCloudBusinessPeopleAtt.setAttachmentName("推广员证明");
        mkCloudBusinessPeopleAtt.setAttachmentAddress(logoUrl);
        mkCloudBusinessPeopleAttMapper.insertSelective(mkCloudBusinessPeopleAtt);

        //插入分派日志表
        MKCloudBusinessPeopleDeployLog mkCloudBusinessPeopleDeployLog = new MKCloudBusinessPeopleDeployLog();
        mkCloudBusinessPeopleDeployLog.setBusinessPeopleCode(mkCloudBusinessPeople.getBusinessPeopleCode());
        mkCloudBusinessPeopleDeployLog.setBusinessPeopleName(mkCloudBusinessPeople.getBusinessPeopleName());
        mkCloudBusinessPeopleDeployLog.setCusTel(applyMobile);
        mkCloudBusinessPeopleDeployLog.setCusIdNo("");
        mkCloudBusinessPeopleDeployLog.setCusName(applyName);
        mkCloudBusinessPeopleDeployLogMapper.insertSelective(mkCloudBusinessPeopleDeployLog);

        return null;
    }
}
