package mkcloudadmin.service.business.impl;

import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.StaticEnum;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudAdvancePaymentPlanVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommercialTenantVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudPrepaymentRuleConfigurationVO;
import mkcloudadmin.service.business.PrepaymentRuleConfigurationService;
import mkcloudadmin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PrepaymentRuleConfigurationServiceImpl implements PrepaymentRuleConfigurationService {
@Autowired
private MKCloudPrepaymentRuleConfigurationMapper prepaymentRuleConfigurationMapper;
   @Autowired
   private MKCloudBankImportDetailMapper bankImportDetailMapper;
   @Autowired
   private MKCloudApplicationImportDetailMapper applicationImportDetailMapper;
   @Autowired
   private MKCloudCommissionDetailMapper commissionDetailMapper;
   @Autowired
   private MKCloudCommercialTenantMapper commercialTenantMapper;
   @Autowired
   private MKCloudDataDictionaryMapper dataDictionaryMapper;
   /**
    * 成交明细查询
    * @param detailPage
    * @return
    */
   @Override
   public Map<String,Object> selectApplicationImportDetail(Page<MKCloudBankImportDetailVO> detailPage, String beginDate, String endDate){
      Map<String,Object> resultMap = new HashMap<>();
      List<MKCloudBankImportDetail> details = bankImportDetailMapper.queryBackRecord(null,null,null,null,null,null,beginDate,endDate,detailPage);
      long count = bankImportDetailMapper.queryBackCount(null,null,null,null,null,null,beginDate,endDate);
      resultMap.put("detailsList", details);
      resultMap.put("count", count);
      return  resultMap;
   }

 public   Map<String,Object> selectPrepaymentRuleConfiguration(String code,String name){
    Map<String,Object> resultMap = new HashMap<>();
    List<MKCloudPrepaymentRuleConfigurationVO> list=new ArrayList<>();
    List<MKCloudPrepaymentRuleConfiguration>   configurationList=prepaymentRuleConfigurationMapper.selectByPrepay();
    if(null!=configurationList && configurationList.size()>0){
       List<MKCloudCommercialTenant> tenant=null;
       MKCloudDataDictionary dictionary=null;
       MKCloudDataDictionary dictionaryP=null;
       for (MKCloudPrepaymentRuleConfiguration plan:configurationList){
          if(null!=plan.getMerchantCode() && !"".equals(plan.getMerchantCode())) {
             tenant = commercialTenantMapper.selectByList(plan.getMerchantCode(), null, null, null, null);
          }
          if(null!=plan.getSettlementType() && !"".equals(plan.getSettlementType())){
              dictionary=   dataDictionaryMapper.selectByState(plan.getSettlementType(),null);
             if(null!=dictionary){
                dictionaryP=  dataDictionaryMapper.selectByState(dictionary.getParentCode(),null);
                if(null!=dictionaryP && "0".equals(dictionaryP.getState())){
                   dictionary.setState(dictionaryP.getState());
                }
             }
          }
          list.add(reConver(plan,tenant,dictionary));
       }
    }
    List<MKCloudPrepaymentRuleConfigurationVO> list2=new ArrayList<>();
    if((null!=code && !code.equals("")) && (null!=name && !name.equals(""))){
       if (null != list && list.size() > 0) {
          for (MKCloudPrepaymentRuleConfigurationVO vo : list) {
             if (code.equals(vo.getMerchantCode()) && name.equals(vo.getMerchantName())){
                list2.add(vo);
             }
          }
          resultMap.put("configurationList",list2);
       }
    }else  if((null!=code && !code.equals("")) || (null!=name && !name.equals(""))) {
       if (null != list && list.size() > 0) {
          for (MKCloudPrepaymentRuleConfigurationVO vo : list) {
             if (code.equals(vo.getMerchantCode()) || name.equals(vo.getMerchantName())){
                list2.add(vo);
             }
          }
          resultMap.put("configurationList",list2);
       }
    } else{
       resultMap.put("configurationList",list);
    }
    return  resultMap;
  }
  @Override
  public  String addPrepaymentRuleConfiguration(Map<String, String> map){

      MKCloudPrepaymentRuleConfiguration configuration=new MKCloudPrepaymentRuleConfiguration();
      configuration.setShow(map.get("show"));
      configuration.setMerchantCode(map.get("merchantCode"));
      configuration.setSettlementType(map.get("settlementType"));
      configuration.setPrice(new BigDecimal(map.get("price")));
      configuration.setSettltmentBeginDate(DateUtils.stringToDate(map.get("settltmentBeginDate"),"yyyy-MM-dd"));
      configuration.setSettlementEndDate(DateUtils.stringToDate(map.get("settlementEndDate"),"yyyy-MM-dd"));
      configuration.setState(map.get("state"));

      if("add".equals(map.get("opMethod"))){
         //比较两个日期
         int result=configuration.getSettltmentBeginDate().compareTo(configuration.getSettlementEndDate());
         //如果日期相等返回0
         if(result>0){
            //大于0，参数tenant.getTerminationTime()就是在tenant.getCooperationTime()之前
            return  "结算起始日期大于结算结束日期";
         }else if(result==0){
            return  "结算起始日期等于结算结束日期";
         }
         prepaymentRuleConfigurationMapper.insertSelective(configuration);
      }else{
         configuration.setId(Long.valueOf(map.get("id")));
         if("1".equals(configuration.getState())){
            MKCloudDataDictionary nary=new MKCloudDataDictionary();
            nary.setState(configuration.getState());
            nary.setCode(configuration.getSettlementType());
            dataDictionaryMapper.updateByCode(nary);
            MKCloudDataDictionary dictionary=  dataDictionaryMapper.selectByState(configuration.getSettlementType(),null);
            if(null!=dictionary){
               nary.setCode(dictionary.getParentCode());
               dataDictionaryMapper.updateByCode(nary);
            }
         }
         prepaymentRuleConfigurationMapper.updateByPrimaryKeySelective(configuration);
      }
      return  "";

  }

   public   MKCloudPrepaymentRuleConfigurationVO reConver( MKCloudPrepaymentRuleConfiguration configuration,List<MKCloudCommercialTenant> tenant,MKCloudDataDictionary dictionary){
      MKCloudPrepaymentRuleConfigurationVO  vo=new MKCloudPrepaymentRuleConfigurationVO();
      vo.setId(configuration.getId());
      if(null!=dictionary) {
         vo.setSettlementTypeName(dictionary.getDescribe());
         if("0".equals(dictionary.getState())){
            vo.setStateName("禁用");
            vo.setState(dictionary.getState());
         }else if("1".equals(configuration.getState())){
            vo.setStateName("启用");
            vo.setState(configuration.getState());
         }else{
            vo.setStateName("禁用");
            vo.setState(configuration.getState());
         }
      }
      vo.setSettlementType(configuration.getSettlementType());
      vo.setShow(configuration.getShow());
      vo.setPrice(configuration.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
      vo.setState(configuration.getState());
      if(null!=tenant && tenant.size()>0){
         for(MKCloudCommercialTenant tenant1:tenant){
            vo.setMerchantCode(tenant1.getMerchantCode());
            vo.setMerchantName(tenant1.getMerchantName());
         }
      }
      vo.setSettltmentBeginDate(DateUtils.dateToString(configuration.getSettltmentBeginDate(),"yyyy-MM-dd"));
      vo.setSettlementEndDate(DateUtils.dateToString(configuration.getSettlementEndDate(),"yyyy-MM-dd"));
      return vo;
   }
}


