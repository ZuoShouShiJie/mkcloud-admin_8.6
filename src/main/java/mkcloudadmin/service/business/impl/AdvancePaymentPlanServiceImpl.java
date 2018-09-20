package mkcloudadmin.service.business.impl;

import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.StaticEnum;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudAdvancePaymentPlanVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommercialTenantVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudPrepaymentRuleConfigurationVO;
import mkcloudadmin.service.business.AdvancePaymentPlanService;
import mkcloudadmin.service.business.PrepaymentRuleConfigurationService;
import mkcloudadmin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预付款计划管理
 */
@Service
public class AdvancePaymentPlanServiceImpl implements AdvancePaymentPlanService {
@Autowired
private MKCloudAdvancePaymentPlanMapper advancePaymentPlanMapper;

    @Autowired
    private MKCloudPrepaymentRuleConfigurationMapper prepaymentRuleConfigurationMapper;
    @Autowired
    private MKCloudBankImportDetailMapper bankImportDetailMapper;
    @Autowired
    private MKCloudDataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private MKCloudCommissionDetailMapper commissionDetailMapper;
    @Autowired
    private MKCloudCommercialTenantMapper commercialTenantMapper;
    /**
     * 预付款金额明细查询
     * @return
     */
    @Override
  public   Map<String,Object> selectByPayMentPlan(){
      Map<String,Object> resultMap = new HashMap<>();
      List<MKCloudAdvancePaymentPlanVO> list=new ArrayList<>();
        List<MKCloudPrepaymentRuleConfigurationVO> voList=new ArrayList<>();
        List<MKCloudAdvancePaymentPlan> paymentPlansAnd=null;
        List<MKCloudPrepaymentRuleConfiguration> configurationList=null;
        MKCloudDataDictionary  dictionarys=null;
        MKCloudDataDictionary  dictionaryOne=null;
        MKCloudAdvancePaymentPlanVO planVO=new MKCloudAdvancePaymentPlanVO();
        BigDecimal totalPailMoney =new BigDecimal(0);
        BigDecimal usedPailMoney =new BigDecimal(0);
        BigDecimal overplusPailMoney =new BigDecimal(0);
        paymentPlansAnd=advancePaymentPlanMapper.selectByPayMentPlan();
        if(null!=paymentPlansAnd && paymentPlansAnd.size()>0){
            for (MKCloudAdvancePaymentPlan plan:paymentPlansAnd){
                if(StaticEnum.djustType_add.getCode().equals(plan.getAdjustType())) {
                    totalPailMoney = totalPailMoney.add(plan.getAdvance());
                }else if(StaticEnum.djustType_subtract.getCode().equals(plan.getAdjustType())){
                    totalPailMoney = totalPailMoney.subtract(plan.getAdvance());
                }
            }
            //预付款总计
          planVO.setTotalPailMoney(totalPailMoney.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
      }else{
          //预付款总计
          planVO.setTotalPailMoney( new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
      }
        configurationList=prepaymentRuleConfigurationMapper.selectByPrepaymentRuleConfiguration();
        if(null!=configurationList && configurationList.size()>0){
            long count=0l;
            for (MKCloudPrepaymentRuleConfiguration  configuration:configurationList){
                dictionarys=  dataDictionaryMapper.selectByState(configuration.getSettlementType(),StaticEnum.EFFECTIVE.getCode());
                if(null!=dictionarys){
                    dictionaryOne=  dataDictionaryMapper.selectByState(dictionarys.getParentCode(),StaticEnum.EFFECTIVE.getCode());
                    if(null!=dictionaryOne) {
                        String beginDate= DateUtils.dateToString(configuration.getSettltmentBeginDate(), "yyyy-MM-dd");
                        String endDate= DateUtils.dateToString(configuration.getSettlementEndDate(), "yyyy-MM-dd");
                        count = bankImportDetailMapper.queryBackCount(null,null,null,null,null,null,beginDate,endDate);
                        voList.add(reConver(configuration, dictionarys, count));
                       usedPailMoney=usedPailMoney.add(configuration.getPrice().multiply(BigDecimal.valueOf(count)));
                    }else{
                        planVO.setUsedPailMoney( new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    }
                }else{
                    planVO.setUsedPailMoney( new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                }
            }
            planVO.setUsedPailMoney(usedPailMoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }else{
            //预付款已用
            planVO.setUsedPailMoney( new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        }
        //预付款结余
        overplusPailMoney=totalPailMoney.subtract(usedPailMoney);
        planVO.setOverPailplusMoney(overplusPailMoney.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        list.add(planVO);

        resultMap.put("configurationList", voList);
        resultMap.put("planList", reConver(paymentPlansAnd));
        resultMap.put("planVO",list);
        return  resultMap;
  }
    @Override
    public   Map<String,Object> selectWaterMaintenance(String code,String name){
        Map<String,Object> resultMap = new HashMap<>();
        List<MKCloudCommercialTenantVO> list=new ArrayList<>();
        List<MKCloudAdvancePaymentPlan>   planList=advancePaymentPlanMapper.selectByPayMentPlan();
        if(null!=planList && planList.size()>0){
            List<MKCloudCommercialTenant> tenant=null;
          for (MKCloudAdvancePaymentPlan plan:planList){
              if(null!=plan.getMerchantCode() && !"".equals(plan.getMerchantCode())) {
                  tenant = commercialTenantMapper.selectByList(plan.getMerchantCode(), null, null, null, null);
              }
              list.add(reConver(plan,tenant));
          }
        }
        List<MKCloudCommercialTenantVO> list2=new ArrayList<>();
       if((null!=code && !code.equals("")) && (null!=name && !name.equals(""))){
            if (null != list && list.size() > 0) {
                for (MKCloudCommercialTenantVO vo : list) {
                    if (code.equals(vo.getMerchantCode()) && name.equals(vo.getMerchantName())){
                        list2.add(vo);
                    }
                }
                resultMap.put("planList",list2);
            }
        }else  if((null!=code && !code.equals("")) || (null!=name && !name.equals(""))) {
           if (null != list && list.size() > 0) {
               for (MKCloudCommercialTenantVO vo : list) {
                   if (code.equals(vo.getMerchantCode()) || name.equals(vo.getMerchantName())){
                       list2.add(vo);
                   }
               }
               resultMap.put("planList",list2);
           }
       } else{
            resultMap.put("planList",list);
        }
        return  resultMap;
    }
    @Override
    public  String  addPayMentPlan(Map<String, String> map){
      MKCloudAdvancePaymentPlan plan=new   MKCloudAdvancePaymentPlan();
        plan.setMerchantCode(map.get("merchantCode"));
        plan.setAdjustType(map.get("adjustType"));
        plan.setAccount(map.get("account"));
        plan.setAccountBank(map.get("accountBank"));
        plan.setAccountName(map.get("accountName"));
        plan.setAccountDate(DateUtils.stringToDate(map.get("accountDate"),"yyyy-MM-dd"));
        plan.setAdvance( new BigDecimal(map.get("advance")));
        if("add".equals(map.get("opMethod"))){
            advancePaymentPlanMapper.insertSelective(plan);
        }else{
            plan.setId(Long.valueOf(map.get("id")));
            advancePaymentPlanMapper.updateByPrimaryKeySelective(plan);
        }
    return "";
    }

    @Override
    public Map<String,Object>  selectDataDictionary(){
        Map<String,Object> resultMap = new HashMap<>();
        List<MKCloudDataDictionary> list=new ArrayList<>();
       List<MKCloudDataDictionary>  dictionaryList=dataDictionaryMapper.selectByDataDictionary(StaticEnum.EFFECTIVE.getCode());
      if(null!=dictionaryList && dictionaryList.size()>0){
          for (MKCloudDataDictionary dictionary:dictionaryList){
              if( null!=dictionary.getParentCode()  && !"".equals(dictionary.getParentCode()) ){
                list.add(dictionary);
              }
          }
      }
       resultMap.put("dictionaryList",list);
        return  resultMap;
    }

    public   List<MKCloudAdvancePaymentPlanVO> reConver( List<MKCloudAdvancePaymentPlan>   planList){
        List<MKCloudAdvancePaymentPlanVO> voList=new ArrayList<>();
        if (null!=planList && planList.size()>0){
            MKCloudAdvancePaymentPlanVO vo=null;
            for (MKCloudAdvancePaymentPlan plan :planList){
                vo=new MKCloudAdvancePaymentPlanVO();
                vo.setAccount(plan.getAccount());
                vo.setAccountBank(plan.getAccountBank());
                vo.setAccountDate(  new SimpleDateFormat("yyyy-MM-dd").format(plan.getAccountDate()));
                vo.setAccountName(plan.getAccountName());
                vo.setAdjustType(plan.getAdjustType());
                if("1".equals(plan.getAdjustType())){
                    vo.setAdjustTypeName("新增预付款");
                }else if("-1".equals(plan.getAdjustType())){
                    vo.setAdjustTypeName("提取预付款");
                }
                vo.setAdvance(plan.getAdvance().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
             voList.add(vo);
            }
        }
        return voList;
    }

    public  MKCloudPrepaymentRuleConfigurationVO reConver(MKCloudPrepaymentRuleConfiguration configuration,MKCloudDataDictionary  dictionarys,long size){
        MKCloudPrepaymentRuleConfigurationVO vo=new MKCloudPrepaymentRuleConfigurationVO();
        vo.setSettlementType(dictionarys.getDescribe());
        vo.setShow(configuration.getShow());
        vo.setTotalCount(size);
        vo.setPrice(configuration.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        BigDecimal  toTalMoney=configuration.getPrice().multiply(BigDecimal.valueOf(size));
        vo.setTotalMoney(toTalMoney.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        vo.setSettltmentBeginDate(new SimpleDateFormat("yyyy-MM-dd").format(configuration.getSettltmentBeginDate()));
        vo.setSettlementEndDate(new SimpleDateFormat("yyyy-MM-dd").format(configuration.getSettlementEndDate()));
        return vo;
    }
    public   MKCloudCommercialTenantVO reConver( MKCloudAdvancePaymentPlan   plan,List<MKCloudCommercialTenant> tenant){
        MKCloudCommercialTenantVO  vo=new MKCloudCommercialTenantVO();
        vo.setId(plan.getId());
        vo.setAccount(plan.getAccount());
        vo.setAccountBank(plan.getAccountBank());
        vo.setAccountDate(  new SimpleDateFormat("yyyy-MM-dd").format(plan.getAccountDate()));
        vo.setAccountName(plan.getAccountName());
        vo.setAdjustType(plan.getAdjustType());
        if("1".equals(plan.getAdjustType())){
            vo.setAdjustTypeName("新增预付款");
        }else if("-1".equals(plan.getAdjustType())){
            vo.setAdjustTypeName("提取预付款");
        }
        vo.setAdvance(plan.getAdvance().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        if(null!=tenant && tenant.size()>0){
            for(MKCloudCommercialTenant tenant1:tenant){
                vo.setMerchantCode(tenant1.getMerchantCode());
                vo.setMerchantName(tenant1.getMerchantName());
                vo.setMerchantType(tenant1.getMerchantType());
            }
        }
        return vo;
    }
}
