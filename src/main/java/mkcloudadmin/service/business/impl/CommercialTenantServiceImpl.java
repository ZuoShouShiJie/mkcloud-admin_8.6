package mkcloudadmin.service.business.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudCommercialTenantMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudCommissionDetailMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetail;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import mkcloudadmin.model.mkcloud.po.MKCloudCommercialTenant;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudCommercialTenantVO;
import mkcloudadmin.service.business.CommercialTenantService;
import mkcloudadmin.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class CommercialTenantServiceImpl  implements CommercialTenantService {
    @Autowired
    private MKCloudCommercialTenantMapper commercialTenantMapper;

    /**
     * 查询所有商户信息
     * @param
     * @return
     */
    @Override
    public Map<String,Object> selectByCommercialTenantList(String  merchantCode,
                                                           String merchantName ,
                                                           String  cooperationTimeBegin,
                                                           String  cooperationTimeEnd ,
                                                           String cooperativeState){
        Date beginDate =  StringUtils.isBlank(cooperationTimeBegin)?null:(DateUtils.stringToDate(cooperationTimeBegin+" 00:00:00",DateUtils.fm_yyyy_MM_dd_HHmmss));
        Date endDate =    StringUtils.isBlank(cooperationTimeEnd)?null:(DateUtils.stringToDate(cooperationTimeEnd+" 23:59:59",DateUtils.fm_yyyy_MM_dd_HHmmss));

        Map<String,Object> resultMap = new HashMap<>();
        List<MKCloudCommercialTenant> tenants = commercialTenantMapper.selectByList(merchantCode,merchantName,beginDate,endDate,cooperativeState);
        resultMap.put("tenantList",reConver(tenants));
        return  resultMap;
    }
    @Override
    public String addCommercialTenant(Map<String, String> map){
        MKCloudCommercialTenant tenant =new MKCloudCommercialTenant();
           //将日期转成Date对象作比较
            tenant.setTerminationTime(DateUtils.stringToDate(map.get("terminationTime"),"yyyy-MM-dd"));
            tenant.setCooperationTime(DateUtils.stringToDate(map.get("cooperationTime"),"yyyy-MM-dd"));
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //比较两个日期
            int result=tenant.getCooperationTime().compareTo(tenant.getTerminationTime());
            //如果日期相等返回0
            if(result>0){
                //大于0，参数tenant.getTerminationTime()就是在tenant.getCooperationTime()之前
               return  "合作日期大于合作终止日期";
            }else if(result==0){
                return  "合作日期等于合作终止日期";
            }
            tenant.setMerchantName(map.get("merchantName"));
            List<MKCloudCommercialTenant> listn = commercialTenantMapper.selectByList(null,tenant.getMerchantName(),null,null,null);
            if(null!=listn &&listn.size()>0){
                return  "该商户名称已存在";
            }
            List<MKCloudCommercialTenant> list = commercialTenantMapper.selectByList(null,null,null,null,null);

            tenant.setMerchantCode(merchantCode(list));
            tenant.setMerchantType(map.get("merchantType"));
            tenant.setCooperativeState(map.get("cooperativeState"));

            tenant.setRemarks(map.get("remarks"));
            tenant.setTerminationTime(DateUtils.stringToDate(map.get("terminationTime"),"yyyy-MM-dd"));
            commercialTenantMapper.insertSelective(tenant);

        return  "";
    }

    @Override
    public String updateCommercialTenant(Map<String, String> map){
        MKCloudCommercialTenant tenant =new MKCloudCommercialTenant();
        tenant.setTerminationTime(DateUtils.stringToDate(map.get("terminationTime"),"yyyy-MM-dd"));
        tenant.setCooperationTime(DateUtils.stringToDate(map.get("cooperationTime"),"yyyy-MM-dd"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //比较两个日期
        int result=tenant.getCooperationTime().compareTo(tenant.getTerminationTime());
        //如果日期相等返回0
        if(result>0){
            //大于0，参数tenant.getTerminationTime()就是在tenant.getCooperationTime()之前
            return  "合作日期大于合作终止日期";
        }else if(result==0){
            return  "合作日期等于合作终止日期";
        }
        tenant.setId(Long.valueOf(map.get("id")));
        tenant.setMerchantCode(map.get("merchantCode"));
        tenant.setMerchantName(map.get("merchantName"));
        tenant.setMerchantType(map.get("merchantType"));
        tenant.setCooperativeState(map.get("cooperativeState"));
        tenant.setCooperationTime(DateUtils.stringToDate(map.get("cooperationTime"),"yyyy-MM-dd"));
        tenant.setRemarks(map.get("remarks"));
        tenant.setTerminationTime(DateUtils.stringToDate(map.get("terminationTime"),"yyyy-MM-dd"));
        commercialTenantMapper.updateByPrimaryKeySelective(tenant);
        return  "";
    }

    public List<MKCloudCommercialTenantVO> reConver(List<MKCloudCommercialTenant> tenants){
        List<MKCloudCommercialTenantVO> list=new ArrayList<>();
        MKCloudCommercialTenantVO vo=null;
        if(null!=tenants && tenants.size()>0) {
            for (MKCloudCommercialTenant tenant : tenants) {
                vo = new MKCloudCommercialTenantVO();
                vo.setId(tenant.getId());
                vo.setMerchantCode(tenant.getMerchantCode());
                vo.setMerchantName(tenant.getMerchantName());
                vo.setMerchantType(tenant.getMerchantType());
                vo.setCooperativeState(tenant.getCooperativeState());
                if("1".equals(tenant.getCooperativeState())){
                    vo.setCooperativeStateName("合作中");
                }else if("0".equals(tenant.getCooperativeState())){
                    vo.setCooperativeStateName("合作终止");
                }
                vo.setCooperationTime(new SimpleDateFormat("yyyy-MM-dd").format(tenant.getCooperationTime()));
                vo.setRemarks(tenant.getRemarks());
                vo.setTerminationTime(new SimpleDateFormat("yyyy-MM-dd").format(tenant.getTerminationTime()));
                list.add(vo);
            }
        }
        return  list;
    }
    public  String merchantCode(List<MKCloudCommercialTenant> list){
        String merchantCode = null;
        if (null != list && list.size() > 0) {
            merchantCode = list.get(list.size() - 1).getMerchantCode();
            Integer pCode = Integer.valueOf(merchantCode);
            return  (String.valueOf(++pCode));
        } else {
            return  "110";
        }
    }
}
