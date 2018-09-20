package mkcloudadmin.service.bankfeedback.impl;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.CodeEnum;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.ResponseResult;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportTotalVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudClientBaseInfoVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberRelationVo;
import mkcloudadmin.service.bankfeedback.BankFeedBackService;
import mkcloudadmin.util.ParseExcelUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BankFeedBackServiceImpl implements BankFeedBackService {

    private static final Logger logger = LoggerFactory.getLogger(BankFeedBackServiceImpl.class);


    @Autowired
    private MKCloudBankImportDetailMapper mkCloudBankImportDetailMapper;
    @Autowired
    private MKCloudBankImportTotalMapper mkCloudBankImportTotalMapper;
    @Autowired
    private MKCloudMemberInfoMapper mkCloudMemberInfoMapper;
    @Autowired
    private MKCloudCreditCardInfoMapper mkCloudCreditCardInfoMapper;

    @Autowired
    private MKCloudBusinessRelationshipMapper mkCloudBusinessRelationshipMapper;
    @Autowired
    private MKCloudBusinessPeopleMapper mkCloudBusinessPeopleMapper;
    @Autowired
    private MKCloudCommissionDetailMapper mkCloudCommissionDetailMapper;
    @Resource
    private BaseApi baseApi;

    @Override
    @Transactional
    public String handleExcel(MultipartFile file) throws Exception {

        Workbook wb = ParseExcelUtil.readExcel(file);

        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            int rownum = sheet.getPhysicalNumberOfRows();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String batchNo = "FW" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(2);
            Long allId = 0l;
            int sum = 0;
            FinanceExcelAllPage excelAll = null;
            String cell = null;
            Row row = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells() - 1;
            int failNum = 0;
            StringBuffer sb = new StringBuffer();
            StringBuffer failString = new StringBuffer();
            String userName = baseApi.getUserId();
            for (int i = 1; i < rownum; i++) {
                MKCloudBankImportDetail detail = new MKCloudBankImportDetail();
                detail.setBatchId(batchNo);
                row = sheet.getRow(i);
                int j = 0;
                if (row != null) {
                    for (; j < colnum; j++) {
                        Object o = ParseExcelUtil.getCellFormatValue(row.getCell(j));
                        cell = o == null ? null : (String) o;

                        switch (j) {
                            case 0:
                                if (cell == null || "".equals(cell.trim())) {
                                    failNum++;
                                    sb.append("第" + i + "行" + (j + 1) + "列为空");
                                }
                                detail.setApplyId(cell == null ? null : cell.trim());
                                break;
                            case 1:
                                if (cell == null || "".equals(cell.trim())) {
                                    failNum++;
                                    sb.append("第" + i + "行" + (j + 1) + "列为空");
                                }
                                detail.setCusName(cell == null ? null : cell.trim());
                                break;
                            case 2:
                                if (cell == null || "".equals(cell.trim()) || cell.length() != 11) {
                                    failNum++;
                                    sb.append("第" + i + "行" + (j + 1) + "列为空或者不为11位");
                                }
                                detail.setCusTel(cell == null ? null : cell.trim());
                                break;
                            case 3:
                                detail.setCusIdNo(cell == null ? null : cell.trim());
                                break;
                            case 4:
                                detail.setInstitutionName(cell == null ? null : cell.trim());
                                break;
                            case 5:
                                if (cell == null || "".equals(cell.trim())) {
                                    failNum++;
                                    sb.append("第" + i + "行" + (j + 1) + "列为空");
                                }
                                detail.setProductName(cell == null ? null : cell.trim());
                                break;

                            case 6:
                                detail.setPresenter(cell == null ? null : cell.trim());
                                break;
                            case 7:
                                detail.setApplyCardDate(cell == null ? null : cell.trim());
                                break;

                            case 8:
                                detail.setApplyCardTime(cell == null ? null : cell.trim());
                                break;
                            case 9:
                                if (cell == null || "".equals(cell.trim())) {
                                    failNum++;
                                    sb.append("第" + i + "行" + (j + 1) + "列为空");
                                }
                                detail.setAuditStatus(cell == null ? null : cell.trim());
                                break;
                            case 10:
                                detail.setRemark(cell == null ? null : cell.trim());
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

                List<MKCloudBankImportDetail> mkCloudBankImportDetailList = mkCloudBankImportDetailMapper.selectByApplyId(detail.getApplyId());
                if (mkCloudBankImportDetailList != null && mkCloudBankImportDetailList.size() > 0) {
                    failNum++;
                    sb.append("第" + i + "行数据存在重复");
                    failString.append("第" + i + "行数据存在重复");
                }

                detail.setCreator(userName);
                detail.setUpdator(userName);
                mkCloudBankImportDetailMapper.insertSelective(detail);


            }
            MKCloudBankImportTotal mkCloudBankImportTotal = new MKCloudBankImportTotal();
            mkCloudBankImportTotal.setBatchId(batchNo);
            mkCloudBankImportTotal.setCounts(rownum - 1);
            mkCloudBankImportTotal.setFailCounts(failNum);
            mkCloudBankImportTotal.setFailReason(sb == null ? "" : sb.toString());
            mkCloudBankImportTotal.setState("0");
            mkCloudBankImportTotal.setCreator(userName);
            mkCloudBankImportTotal.setUpdator(userName);
            mkCloudBankImportTotalMapper.insertSelective(mkCloudBankImportTotal);

            if (failNum > 0) {
                failString.append("上传的文件数据不完整!");
                return failString.toString();
            }
        }

        return "";

    }

    @Override
    public Map<String, Object> queryFeedbackBankTotalList(String method, Page<MKCloudBankImportTotal> mkCloudBankImportTotalPage) {
        List<MKCloudBankImportTotalVO> mkCloudBankImportTotalVOList = new ArrayList<>();
        Long total = mkCloudBankImportTotalMapper.queryFeedbackBankTotalCount(method);
        if (total > 0) {
            List<MKCloudBankImportTotal> mkCloudBankImportTotalList = mkCloudBankImportTotalMapper.selectByBatchId(method, mkCloudBankImportTotalPage);
            if (mkCloudBankImportTotalList != null && mkCloudBankImportTotalList.size() > 0) {
                MKCloudBankImportTotalVO mkCloudBankImportTotalVO = null;
                Long num = 1l;
                for (MKCloudBankImportTotal bankImportTotal : mkCloudBankImportTotalList) {
                    mkCloudBankImportTotalVO = new MKCloudBankImportTotalVO();

                    mkCloudBankImportTotalVO.setSeqNo(num);
                    mkCloudBankImportTotalVO.setId(bankImportTotal.getId());
                    mkCloudBankImportTotalVO.setCounts(bankImportTotal.getCounts());
                    mkCloudBankImportTotalVO.setState(bankImportTotal.getState());
                    mkCloudBankImportTotalVO.setBatchId(bankImportTotal.getBatchId());
                    mkCloudBankImportTotalVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bankImportTotal.getCreateTime()));
                    mkCloudBankImportTotalVO.setCreator(bankImportTotal.getCreator());
                    mkCloudBankImportTotalVO.setStateName(bankImportTotal.getState());

                    mkCloudBankImportTotalVOList.add(mkCloudBankImportTotalVO);
                    num++;
                }

            }

        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", total);
        resultMap.put("data", mkCloudBankImportTotalVOList);


        return resultMap;
    }

    @Override
    public Map<String, Object> queryFeedbackBankDetailList(String method, Page<MKCloudBankImportDetail> mkCloudBankImportDetailPage) {
        List<MKCloudBankImportDetailVO> mkCloudBankImportDetailVOList = new ArrayList<>();
        Long total = mkCloudBankImportDetailMapper.queryFeedbackBankDetailCount(method);
        if (total > 0) {
            List<MKCloudBankImportDetail> mkCloudBankImportDetailTemList = mkCloudBankImportDetailMapper.selectByBatchId(method, mkCloudBankImportDetailPage);
            if (mkCloudBankImportDetailTemList != null && mkCloudBankImportDetailTemList.size() > 0) {
                MKCloudBankImportDetailVO mkCloudBankImportDetailVO = null;
                Long num = 1l;
                for (MKCloudBankImportDetail bankImportDetail : mkCloudBankImportDetailTemList) {
                    mkCloudBankImportDetailVO = new MKCloudBankImportDetailVO();

                    mkCloudBankImportDetailVO.setSeqNo(num);
                    mkCloudBankImportDetailVO.setId(bankImportDetail.getId());
                    mkCloudBankImportDetailVO.setBatchId(bankImportDetail.getBatchId());
                    mkCloudBankImportDetailVO.setApplyName(bankImportDetail.getCusName());
                    mkCloudBankImportDetailVO.setApplyMobile(bankImportDetail.getCusTel());
                    mkCloudBankImportDetailVO.setApplyIdCard(bankImportDetail.getCusIdNo());
                    mkCloudBankImportDetailVO.setApplyBank(bankImportDetail.getInstitutionName());
                    mkCloudBankImportDetailVO.setApplyProduct(bankImportDetail.getProductName());
                    mkCloudBankImportDetailVO.setApplyCardDate(bankImportDetail.getApplyCardDate());
                    mkCloudBankImportDetailVO.setAuditStatus(bankImportDetail.getAuditStatus());
                    mkCloudBankImportDetailVO.setCommission(bankImportDetail.getCommission());


                    mkCloudBankImportDetailVOList.add(mkCloudBankImportDetailVO);
                    num++;
                }

            }

        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", total);
        resultMap.put("data", mkCloudBankImportDetailVOList);


        return resultMap;
    }

    @Transactional
    public void delFeedBackInfoImportDataByBatchId(String batchId) {

        mkCloudBankImportTotalMapper.delFeedBackInfoImportDataByBatchId(batchId);
        mkCloudBankImportDetailMapper.delFeedBackInfoImportDataByBatchId(batchId);

    }

    @Transactional
    public String handleFeedBackInfo(String batchId) {

        MKCloudBankImportTotal mkCloudBankImportTotal = mkCloudBankImportTotalMapper.selectDataByBatchId(batchId);
        if (mkCloudBankImportTotal.getFailCounts() != null && mkCloudBankImportTotal.getFailCounts() > 0) {
            return "批次存在不完整数据或重复数据";
        }
        //根据批次号查询出所有的记录
        List<MKCloudBankImportDetail> mkCloudBankImportDetailList = mkCloudBankImportDetailMapper.selectAllDataByBatchId(batchId);
        if (mkCloudBankImportDetailList != null && mkCloudBankImportDetailList.size() > 0) {
            String mobileNum = null;
            String name = null;
            MKCloudCommissionDetail mkCloudCommissionDetail = null;
            MKCloudBankImportDetail mkCloudBankImportDetail = null;
            MKCloudMemberInfo mmemeb = null;
            for (MKCloudBankImportDetail mkb : mkCloudBankImportDetailList) {
                mobileNum = mkb.getCusTel();

                mobileNum = mobileNum.substring(0, 3) + "____" + mobileNum.substring(7, 11);
                name = mkb.getCusName();
                //推广人匹配
                MKCloudMemberInfo mkCloudMemberInfo = mkCloudMemberInfoMapper.selectByClientBaseInfo(mobileNum, name);
                MKCloudCreditCardInfo mkCloudCreditCardInfo = null;
                if (mkCloudMemberInfo != null) {
                    mkCloudBankImportDetail = new MKCloudBankImportDetail();
                    mkCloudBankImportDetail.setId(mkb.getId());
                    mkCloudBankImportDetail.setCusName(mkCloudMemberInfo.getMemberName());
                    mkCloudBankImportDetail.setCusTel(mkCloudMemberInfo.getTel());

                    mkCloudCreditCardInfo = mkCloudCreditCardInfoMapper.selectByProductName(mkb.getInstitutionName());
                    if (mkCloudCreditCardInfo != null) {
                        mkCloudBankImportDetail.setCommission(mkCloudCreditCardInfo.getAllCommission());
                    } else {
                        return "该批次下," + mkb.getInstitutionName() + "产品不存在";
                    }

                    mmemeb = new MKCloudMemberInfo();
                    mmemeb.setId(mkCloudMemberInfo.getId());
                    mmemeb.setHasBusiness("1");
                    mmemeb.setBusinessStatus("0");

                } else {
                    return "该批次下,用户" + mkb.getCusName() + "不存在";
                }
                List<String> businessPersionCodeList = new ArrayList<>();

                MKCloudMemberRelationVo mkCloudBusinessRelationship = mkCloudBusinessRelationshipMapper.selectByBussinessPersonCode(mkCloudMemberInfo == null ? "" : mkCloudMemberInfo.getBusinessPeopleCode());
                businessPersionCodeList.add(mkCloudMemberInfo == null ? "" : mkCloudMemberInfo.getBusinessPeopleCode());
                businessPersionCodeList.add(mkCloudBusinessRelationship == null ? "" : mkCloudBusinessRelationship.getSecondGrade());
                businessPersionCodeList.add(mkCloudBusinessRelationship == null ? "" : mkCloudBusinessRelationship.getThirdGrade());
                //查询推广人信息
                List<MKCloudBusinessPeople> mkCloudBusinessPeopleList = mkCloudBusinessPeopleMapper.selectByBusinessPeopleCodeList(businessPersionCodeList);
                Map<String, MKCloudBusinessPeople> mkCloudBusinessPeopleMap = new HashMap<>();
                if (mkCloudBusinessPeopleList != null) {
                    mkCloudBusinessPeopleList.forEach(mkCloudBusinessPeople -> mkCloudBusinessPeopleMap.put(mkCloudBusinessPeople.getBusinessPeopleCode(), mkCloudBusinessPeople));
                }

                String firstBusinessPeopleCode = mkCloudMemberInfo == null ? "" : mkCloudMemberInfo.getBusinessPeopleCode();
                MKCloudBusinessPeople firstBusinessPepole = mkCloudBusinessPeopleMap.get(firstBusinessPeopleCode);


                if (firstBusinessPepole != null) {
                     /*mkCloudBankImportDetail = new MKCloudBankImportDetail();
                     mkCloudBankImportDetail.setId(mkb.getId());*/
                    mkCloudBankImportDetail.setBusinessPeopleCode(firstBusinessPepole.getBusinessPeopleCode());
                    mkCloudBankImportDetail.setBusinessPeopleName(firstBusinessPepole.getBusinessPeopleName());
                }

                //只有成功的才分佣计算
//                 if(firstBusinessPepole !=null  && ("成功".equals(mkb.getAuditStatus()))){

                //计算分佣
                BigDecimal commossion3 = new BigDecimal(0);
                BigDecimal commossion2 = new BigDecimal(0);
                BigDecimal commossion1 = new BigDecimal(0);
                Boolean isExistCommission2 = false;
                Boolean isExistCommission3 = false;

                String businessPeopleThirdCode = mkCloudBusinessRelationship == null ? "" : mkCloudBusinessRelationship.getThirdGrade();
                MKCloudBusinessPeople thirdBusinessPepole = mkCloudBusinessPeopleMap.get(businessPeopleThirdCode);
                if (thirdBusinessPepole != null) {
                    commossion3 = mkCloudCreditCardInfo == null ? new BigDecimal(0) : mkCloudCreditCardInfo.getOutCommission3();
                    isExistCommission3 = true;
                }

                String businessPeopleParentCode = mkCloudBusinessRelationship == null ? "" : mkCloudBusinessRelationship.getSecondGrade();
                MKCloudBusinessPeople parentBusinessPepole = mkCloudBusinessPeopleMap.get(businessPeopleParentCode);
                if (parentBusinessPepole != null) {
                    commossion2 = mkCloudCreditCardInfo == null ? new BigDecimal(0) : mkCloudCreditCardInfo.getOutCommission2();
                    isExistCommission2 = true;
                }

                commossion1 = (mkCloudCreditCardInfo == null ? new BigDecimal(0) : mkCloudCreditCardInfo.getOutCommission()).subtract(commossion2).subtract(commossion3);
                mkCloudCommissionDetail = new MKCloudCommissionDetail();
                //查询产品信息
                if (firstBusinessPepole == null) {
                   commossion1 = new BigDecimal(0);
               }

                     /*mkCloudBankImportDetail.setCommission(mkCloudCreditCardInfo.getAllCommission());*/
                mkCloudBankImportDetail.setInCommission(mkCloudCreditCardInfo.getInCommission());

                mkCloudBankImportDetail.setOutCommission(commossion1);
                mkCloudBankImportDetail.setOutCommission2(commossion2);
                mkCloudBankImportDetail.setOutCommission3(commossion3);
                mkCloudBankImportDetail.setInstitutionCode(mkCloudCreditCardInfo.getInstitutionId());
                if (StringUtils.isNoneBlank(mkCloudMemberInfo.getBusinessPeopleCode())) { //加
                    mkCloudCommissionDetail.setBusinessPeopleId(mkCloudMemberInfo.getBusinessPeopleCode());
                    mkCloudCommissionDetail.setCusName(mkCloudMemberInfo.getMemberName());
                    mkCloudCommissionDetail.setCusTel(mkCloudMemberInfo.getTel());
                    mkCloudCommissionDetail.setCusIdNo(mkb.getCusIdNo());
                    mkCloudCommissionDetail.setLevel("1");  //一级分佣
                    mkCloudCommissionDetail.setBusinessPeopleName(mkb.getBusinessPeopleName());
                    //0:内 1：外
                     /*if("0".equals(firstBusinessPepole.getBusinessPeopleType())){
                         mkCloudCommissionDetail.setBusinessPeopleCommission(mkCloudCreditCardInfo.getInCommission());
                     }else if("1".equals(firstBusinessPepole.getBusinessPeopleType())){
                         mkCloudCommissionDetail.setBusinessPeopleCommission(mkCloudCreditCardInfo.getOutCommission());
                     }*/
                    mkCloudCommissionDetail.setBusinessPeopleCommission(commossion1);
                    mkCloudCommissionDetail.setState("0"); //未分佣
                    mkCloudCommissionDetail.setDetailId(mkb.getApplyId());
                    mkCloudCommissionDetail.setBatchId(mkb.getBatchId());
                    mkCloudCommissionDetail.setInstitutionCode(mkCloudCreditCardInfo.getInstitutionId());
                    mkCloudCommissionDetail.setInstitutionName(mkb.getInstitutionName());
                    mkCloudCommissionDetail.setProductName(mkb.getProductName());
                    mkCloudCommissionDetail.setApprovalResult("0"); //待审核
                    mkCloudCommissionDetail.setCommission(mkCloudCreditCardInfo.getAllCommission());

                    mkCloudCommissionDetailMapper.insertSelective(mkCloudCommissionDetail);
                }

                //如果存在二级分佣
                     /*String businessPeopleParentCode = mkCloudBusinessRelationship == null ? "" :mkCloudBusinessRelationship.getSecondGrade();
                     MKCloudBusinessPeople parentBusinessPepole = mkCloudBusinessPeopleMap.get(businessPeopleParentCode);*/
                if (isExistCommission2) {

                    mkCloudCommissionDetail = new MKCloudCommissionDetail();

                    mkCloudCommissionDetail.setBusinessPeopleId(parentBusinessPepole.getBusinessPeopleCode());
                    mkCloudCommissionDetail.setCusName(mkCloudMemberInfo.getMemberName());
                    mkCloudCommissionDetail.setCusTel(mkCloudMemberInfo.getTel());
                    mkCloudCommissionDetail.setCusIdNo(mkb.getCusIdNo());
                    mkCloudCommissionDetail.setLevel("2");  //二级分佣
                    mkCloudCommissionDetail.setBusinessPeopleName(parentBusinessPepole.getBusinessPeopleName());
                    mkCloudCommissionDetail.setBusinessPeopleCommission(commossion2);

                    mkCloudCommissionDetail.setState("0"); //未分佣
                    mkCloudCommissionDetail.setDetailId(mkb.getApplyId());
                    mkCloudCommissionDetail.setBatchId(mkb.getBatchId());
                    mkCloudCommissionDetail.setInstitutionCode(mkCloudCreditCardInfo.getInstitutionId());
                    mkCloudCommissionDetail.setInstitutionName(mkb.getInstitutionName());
                    mkCloudCommissionDetail.setProductName(mkb.getProductName());
                    mkCloudCommissionDetail.setApprovalResult("0"); //待审核
                    mkCloudCommissionDetail.setCommission(mkCloudCreditCardInfo.getAllCommission());

                    mkCloudCommissionDetailMapper.insertSelective(mkCloudCommissionDetail);

                }
                //如果存在三级分佣
                     /*String businessPeopleThirdCode = mkCloudBusinessRelationship == null ? "" :mkCloudBusinessRelationship.getThirdGrade();
                     MKCloudBusinessPeople thirdBusinessPepole = mkCloudBusinessPeopleMap.get(businessPeopleThirdCode);*/
                if (isExistCommission3) {

                    mkCloudCommissionDetail = new MKCloudCommissionDetail();

                    mkCloudCommissionDetail.setBusinessPeopleId(thirdBusinessPepole.getBusinessPeopleCode());
                    mkCloudCommissionDetail.setCusName(mkCloudMemberInfo.getMemberName());
                    mkCloudCommissionDetail.setCusTel(mkCloudMemberInfo.getTel());
                    mkCloudCommissionDetail.setCusIdNo(mkb.getCusIdNo());
                    mkCloudCommissionDetail.setLevel("3");  //二级分佣
                    mkCloudCommissionDetail.setBusinessPeopleName(thirdBusinessPepole.getBusinessPeopleName());
                    mkCloudCommissionDetail.setBusinessPeopleCommission(commossion3);

                    mkCloudCommissionDetail.setState("0"); //未分佣
                    mkCloudCommissionDetail.setDetailId(mkb.getApplyId());
                    mkCloudCommissionDetail.setBatchId(mkb.getBatchId());
                    mkCloudCommissionDetail.setInstitutionCode(mkCloudCreditCardInfo.getInstitutionId());
                    mkCloudCommissionDetail.setInstitutionName(mkb.getInstitutionName());
                    mkCloudCommissionDetail.setProductName(mkb.getProductName());
                    mkCloudCommissionDetail.setApprovalResult("0"); //待审核
                    mkCloudCommissionDetail.setCommission(mkCloudCreditCardInfo.getAllCommission());

                    mkCloudCommissionDetailMapper.insertSelective(mkCloudCommissionDetail);

                }

                if (mmemeb != null) {
                    mmemeb.setBusinessStatus("1");
                }

//                 }

                if (mkCloudBankImportDetail != null) {
                    mkCloudBankImportDetailMapper.updateByPrimaryKeySelective(mkCloudBankImportDetail);
                }
                if (mmemeb != null) {
                    mkCloudMemberInfoMapper.updateByPrimaryKeySelective(mmemeb);
                }

            }

        }
        //根据客户姓名和手机号查询会员表获得推广人员信息
        //根据产品名称查询产品分佣信息

        mkCloudBankImportTotalMapper.updateFeedBackInfoImportTotalByBatchId(batchId);
        return "";
    }

}
