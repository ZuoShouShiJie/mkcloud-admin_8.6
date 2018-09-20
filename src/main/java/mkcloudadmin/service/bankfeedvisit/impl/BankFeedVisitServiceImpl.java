package mkcloudadmin.service.bankfeedvisit.impl;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportBatchVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.bankfeedvisit.BankFeedVisitService;
import mkcloudadmin.util.ParseExcelUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/7/31.
 */
@Service
public class BankFeedVisitServiceImpl implements BankFeedVisitService {

    private static final Logger logger = LoggerFactory.getLogger(BankFeedVisitServiceImpl.class);

    @Autowired
    private MKCloudApplicationImportBatchMapper mkCloudApplicationImportBatchMapper;

    @Autowired
    private MKCloudApplicationImportDetailMapper mkCloudApplicationImportDetailMapper;
    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;
    @Autowired
    private MKCloudBusinessRelationshipMapper mkCloudBusinessRelationshipMapper;
    @Autowired
    private MKCloudBusinessPeopleMapper mkCloudBusinessPeopleMapper;
    @Resource
    private BaseApi baseApi;

    /**
     * 申请批次列表查询
     * @param method
     * @param mkCloudApplicationImportBatchPage
     * @return
     */
    @Override
    public Map<String, Object> queryBankVisitBatchList(String method, Page<MKCloudApplicationImportBatch> mkCloudApplicationImportBatchPage) {
        List<MKCloudApplicationImportBatchVO> mkCloudApplicationImportBatchVOList = new ArrayList<>();
        Long total = mkCloudApplicationImportBatchMapper.queryMKCloudImportBatchCount(method);
        if(total > 0){
            List<MKCloudApplicationImportBatch> mkCloudApplicationImportBatchList = mkCloudApplicationImportBatchMapper.queryMKCloudImportBatchList(method,mkCloudApplicationImportBatchPage);
            if(mkCloudApplicationImportBatchList != null && mkCloudApplicationImportBatchList.size() > 0){
                MKCloudApplicationImportBatchVO mkCloudApplicationImportBatchVO = null;
                Long num = 1l;
                for (MKCloudApplicationImportBatch mkCloudApplicationImportBatch : mkCloudApplicationImportBatchList){
                    mkCloudApplicationImportBatchVO = new MKCloudApplicationImportBatchVO();
                    mkCloudApplicationImportBatchVO.setSeqNo(num);
                    mkCloudApplicationImportBatchVO.setId(mkCloudApplicationImportBatch.getId());
                    mkCloudApplicationImportBatchVO.setBatchId(mkCloudApplicationImportBatch.getBatchId());
                    mkCloudApplicationImportBatchVO.setCounts(mkCloudApplicationImportBatch.getCounts());
                    mkCloudApplicationImportBatchVO.setState(mkCloudApplicationImportBatch.getState());
                    mkCloudApplicationImportBatchVO.setStateName(mkCloudApplicationImportBatch.getState());
                    mkCloudApplicationImportBatchVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudApplicationImportBatch.getCreateTime()));
                    mkCloudApplicationImportBatchVO.setCreator(mkCloudApplicationImportBatch.getCreator());
                    mkCloudApplicationImportBatchVOList.add(mkCloudApplicationImportBatchVO);
                    num++;
                }
            }
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudApplicationImportBatchVOList);
        return resultMap;
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @Override
    @Transactional
    public String handleExcel(MultipartFile file) throws Exception {
        Workbook wb = ParseExcelUtil.readExcel(file);
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            int rownum = sheet.getPhysicalNumberOfRows();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String batchNo = "FW"+sdf.format(new Date())+ RandomStringUtils.randomNumeric(2);
            Long allId = 0l;
            int sum = 0;
            FinanceExcelAllPage excelAll = null;
            String cell = null;
            int failNum =0;
            String userName = baseApi.getUserId();
            Row row = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells()-1;
            for (int i = 1; i < rownum; i++) {
                MKCloudApplicationImportDetail detail = new MKCloudApplicationImportDetail();
                detail.setBatchId(batchNo);
                row = sheet.getRow(i);
                int j = 0;
                if (row != null) {
                    for (; j < colnum; j++) {
                        Object o = ParseExcelUtil.getCellFormatValue(row.getCell(j));
                        cell = o == null ? null : (String)o;

                        switch (j) {
                            case 0:
                                if(cell == null || "".equals(cell.trim())){
                                    failNum ++;
                                }
                                detail.setVisitId(cell == null ? null:cell.trim());
                                break;
                            case 1:
                                if(cell == null || "".equals(cell.trim())){
                                    failNum ++;
                                }
                                detail.setApplyId(cell == null ? null:cell.trim());
                                break;
                            case 2:
                                if(cell == null || "".equals(cell.trim()) || cell.length() != 11){
                                    failNum ++;
                                }
                                detail.setApplyMobile(cell == null ? null:cell.trim());
                                break;
                            case 3:
                                if(cell == null || "".equals(cell.trim())){
                                    failNum ++;
                                }
                                detail.setApplyName(cell == null ? null:cell.trim());
                                break;
                            case 4:
                                detail.setApplyIdCard(cell == null ? null:cell.trim());
                                break;
                            case 5:
                                detail.setApplyBank(cell == null ? null:cell.trim());
                                break;
                            case 6:
                                if(cell == null || "".equals(cell.trim())){
                                    failNum ++;
                                }
                                detail.setApplyProduct(cell == null ? null:cell.trim());
                                break;
                            case 7:
                                detail.setPresenter(cell == null ? null:cell.trim());
                                break;
                            case 8:
                                detail.setApplyCardTime(cell == null ? null:cell.trim());
                                break;
                            case 9:
                                detail.setAuditStatus(cell == null ? null:cell.trim());
                                break;
                            case 10:
                                detail.setRemark(cell == null ? null:cell.trim());
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    break;
                }
                sum++;
                //completionService.submit(new Task(detail));
                detail.setCreator(userName);
                detail.setUpdator(userName);
                Long num =  mkCloudApplicationImportDetailMapper.updateByApplyIdAndPrduct(detail);
                if(num ==0){
                    mkCloudApplicationImportDetailMapper.insertSelective(detail);
                }

            }
            MKCloudApplicationImportBatch mkCloudApplicationImportBatch = new MKCloudApplicationImportBatch();
            mkCloudApplicationImportBatch.setBatchId(batchNo);
            mkCloudApplicationImportBatch.setCounts(rownum-1);
            mkCloudApplicationImportBatch.setState("0");
            mkCloudApplicationImportBatch.setFailCounts(failNum);
            if(failNum > 0){
                mkCloudApplicationImportBatch.setFailReason("存在重要字段为空!");
            }
            mkCloudApplicationImportBatch.setCreator(userName);
            mkCloudApplicationImportBatch.setUpdator(userName);
            mkCloudApplicationImportBatchMapper.insertSelective(mkCloudApplicationImportBatch);
            if(failNum > 0){
                return "上传的文件数据不完整!";
            }
        }
        return "";
    }

    /**
     * 确认信息
     * @param batchId
     */
    @Transactional
    public String confirmVisitInfo(String batchId) {
        //判断批次是否有不完整数据
        MKCloudApplicationImportBatch batch = mkCloudApplicationImportBatchMapper.selectByBatchId(batchId);
        if (batch.getFailCounts() != null && batch.getFailCounts() > 0){
            return "批次存在不完整数据";
        }
        //根据批次号查询出所有的记录
        List<MKCloudApplicationImportDetail> mkCloudApplicationImportDetailList = mkCloudApplicationImportDetailMapper.selectAllDataByBatchId(batchId);
        if(mkCloudApplicationImportDetailList != null && mkCloudApplicationImportDetailList.size()>0) {
            String mobileNum = null;
            String name = null;
            /*MKCloudCommissionDetail mkCloudCommissionDetail = null;*/
            MKCloudApplicationImportDetail mkCloudApplicationImportDetail = null;
            for (MKCloudApplicationImportDetail mka : mkCloudApplicationImportDetailList) {
                mobileNum = mka.getApplyMobile();
                mobileNum = mobileNum.substring(0, 3) + "____" + mobileNum.substring(7, 11);
                name = mka.getApplyName();
                //推广人匹配
                MKCloudMemberInfo mkCloudMemberInfo = mkCloudMemberInfoMapper.selectByClientBaseInfo(mobileNum, name);
                if(mkCloudMemberInfo != null){
                    mkCloudApplicationImportDetail = new MKCloudApplicationImportDetail();
                    mkCloudApplicationImportDetail.setId(mka.getId());
                    mkCloudApplicationImportDetail.setApplyName(mkCloudMemberInfo.getMemberName());
                 //   mkCloudApplicationImportDetail.setApplyMobile(mkCloudMemberInfo.getTel());

                }
                if (mkCloudMemberInfo != null &&  !StringUtils.isEmpty(mkCloudMemberInfo.getBusinessPeopleCode())) {

                    List<String> businessPersionCodeList = new ArrayList<>();
                    businessPersionCodeList.add(mkCloudMemberInfo.getBusinessPeopleCode());
                    //查询推广人信息
                    List<MKCloudBusinessPeople> mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPersionCodeList);
                    if(mkCloudBusinessPeopleList != null && mkCloudBusinessPeopleList.size()>0){

                        /*mkCloudApplicationImportDetail = new MKCloudApplicationImportDetail();*/
                        mkCloudApplicationImportDetail.setBusinessPeopleCode(mkCloudBusinessPeopleList.get(0).getBusinessPeopleCode());
                        mkCloudApplicationImportDetail.setBusinessPeopleName(mkCloudBusinessPeopleList.get(0).getBusinessPeopleName());
                    }

                }
                if(mkCloudApplicationImportDetail != null){
                    mkCloudApplicationImportDetailMapper.updateByPrimaryKeySelective(mkCloudApplicationImportDetail);
                }
            }

            mkCloudApplicationImportBatchMapper.updateApplicationImportBatchByBatchId(batchId);
        }
        return "";
    }

    /**
     * 删除信息
     * @param batchId
     */
    @Transactional
    public void delBatchInfo(String batchId) {
        mkCloudApplicationImportBatchMapper.delByBatchId(batchId);
        mkCloudApplicationImportDetailMapper.delBybatchId(batchId);
    }

    /**
     * 申请批次明细
     * @param method
     * @param detailPage
     * @return
     */
    @Override
    public Map<String, Object> queryBatchDetailList(String method, Page<MKCloudApplicationImportDetail> detailPage) {
        List<MKCloudApplicationImportDetailVO> detailVOList = new ArrayList<>();
        Long total = mkCloudApplicationImportDetailMapper.queryBatchDetailCount(method);
        if(total > 0){
            List<MKCloudApplicationImportDetail> detailList = mkCloudApplicationImportDetailMapper.queryBatchDetailList(method,detailPage);
            if(detailList != null && detailList.size() > 0){
                MKCloudApplicationImportDetailVO detailVO = null;
                Long sum = 1l;
                for(MKCloudApplicationImportDetail detail : detailList){
                    detailVO = new MKCloudApplicationImportDetailVO();
                    detailVO.setSeqNo(sum);
                    detailVO.setId(detail.getId());
                    detailVO.setApplyId(detail.getApplyId());
                    detailVO.setApplyName(detail.getApplyName());
                    detailVO.setApplyMobile(detail.getApplyMobile());
                    detailVO.setApplyIdCard(detail.getApplyIdCard());
                    detailVO.setApplyBank(detail.getApplyBank());
                    detailVO.setApplyProduct(detail.getApplyProduct());
                    detailVO.setApplyCardTime(detail.getApplyCardTime());
                    detailVO.setAuditStatus(detail.getAuditStatus());
                    detailVO.setBusinessPeopleCode(detail.getBusinessPeopleCode());
                    detailVO.setBusinessPeopleName(detail.getBusinessPeopleName());
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
