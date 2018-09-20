package mkcloudadmin.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.DateUtil;
import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.BusinessPeopleTypeEnum;
import mkcloudadmin.model.base.StaticEnum;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.util.DateUtils;
import mkcloudadmin.util.HttpGetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class RecordingImportTimingTasks  {
    private static final Logger logger = LoggerFactory.getLogger(RecordingImportTimingTasks.class);
    @Autowired
    private MKCloudBankImportTotalMapper bankImportTotalMapper;
    @Autowired
    private MKCloudBankImportDetailMapper bankImportDetailMapper;
    @Autowired
    private MKCloudApplicationImportDetailMapper mkCloudApplicationImportDetailMapper;
    @Autowired
    private MKCloudMemberInfoMapper memberInfoMapper;
    @Autowired
    private MKCloudBusinessPeopleMapper  businessPeopleMapper;

 /*   @Value("${incomeMoney.to.canwithdraw}")
    private String cron;*/
  @Scheduled(cron = "0 0/30 8-20 * * *")
    @Transactional
    public void recording() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "10875_5NTjK76k3fUe8KVz4BfqK");
        Date date = DateUtils.getBeginDate(new Date(),30);
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      params.put("begin", format.format(date));
        String url = "https://www.youdancard.com/api/visit-list1";
        String message = HttpGetUtil.httpRequestToString(url
                , params);
        System.out.println(message);
        JSONObject resposne = JSONObject.parseObject(message);
        logger.info("返回的数据："+resposne);
        String code =  resposne.getString("code");
        if ("0".equals(code)){
            JSONArray jsonArray = (JSONArray) resposne.get("data");
            if (jsonArray!=null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    MKCloudApplicationImportDetail tem = new MKCloudApplicationImportDetail();
                    tem.setVisitId(jsonObject.getString("visit_id"));
                    tem.setApplyCardTime(jsonObject.getString("visit_date"));
                    tem.setApplyName(jsonObject.getString("username"));
                    tem.setApplyBank(jsonObject.getString("bank_name"));
                    tem.setApplyProduct(jsonObject.getString("product_name"));
                    tem.setAuditStatus(jsonObject.getString("status"));
                    tem.setRemark(jsonObject.getString("is_second"));
                    tem.setApplyMobile(jsonObject.getString("phone"));
                    MKCloudApplicationImportDetail detail = mkCloudApplicationImportDetailMapper.selectByVisitId(tem.getVisitId());
                    MKCloudMemberInfo info = memberInfoMapper.selectByTel(tem.getApplyMobile());
                    if (null != info && null != info.getBusinessPeopleCode() && !"".equals(info.getBusinessPeopleCode())) {
                        MKCloudBusinessPeople people = businessPeopleMapper.selectPeopleCode(info.getBusinessPeopleCode());
                        if (null != people && null != people.getBusinessPeopleName() && !"".equals(people.getBusinessPeopleName())) {
                            tem.setBusinessPeopleName(people.getBusinessPeopleName());
                        }
                        tem.setBusinessPeopleCode(info.getBusinessPeopleCode());
                    }
                    if (null == info) {
                        memberInfoMapper.insertSelective(reConveradd(tem));
                        logger.info("会员表插入成功");
                    } else {
                        memberInfoMapper.updateBySelective(reConverupate(tem));
                    }
                    if (null == detail) {
                        logger.info("peopleCode:" + tem.getBusinessPeopleCode());
                        mkCloudApplicationImportDetailMapper.insertSelective(tem);
                    } else {
                        mkCloudApplicationImportDetailMapper.updateByVisitId(tem);
                    }
                }
            }
           /* bankImportTotalMapper.insertSelective(reConver(jsonArray,updateCount));*/
            logger.info("插入成功");
        }else {
            logger.info("插入失败");
        }
    }
   /*@Scheduled(cron = "0 0 18 * * ?")*/
   @Scheduled(cron = "0 0 21 * * ?")
    @Transactional
    public void addImportDateil() {
       String  batchId= "FW" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + (int) ((Math.random() * 9 + 1) * 10);
        List<MKCloudApplicationImportDetail> detailList = mkCloudApplicationImportDetailMapper.selectByAuditStatus(StaticEnum.bankSuccess_Import.getMsg(),StaticEnum.version_no.getCode());
        if (null != detailList && detailList.size() > 0) {
            for (MKCloudApplicationImportDetail detail : detailList) {
                    detail.setVersion(Integer.valueOf(StaticEnum.version.getCode()));
                    mkCloudApplicationImportDetailMapper.updateByVisitId(detail);
                    bankImportDetailMapper.insertSelective(reConver(detail,batchId));
            }
            bankImportTotalMapper.insertSelective(reConver(detailList,batchId));
            logger.info("插入成功");
        }else{
            logger.info("无最新成功状态的数据");
        }
    }
    public MKCloudMemberInfo reConveradd(MKCloudApplicationImportDetail appDetail){
        MKCloudMemberInfo memberInfo=new MKCloudMemberInfo();
            memberInfo.setTel(appDetail.getApplyMobile());
            memberInfo.setType(BusinessPeopleTypeEnum.PEOPLE_TYPE_PEOPLE.getCode());
            memberInfo.setChannelCode("0101");
            memberInfo.setMemberName(appDetail.getApplyName());
            memberInfo.setMemberCode(getMemberCode(memberInfo.getChannelCode(), appDetail.getApplyMobile()));
            memberInfo.setState(StaticEnum.EFFECTIVE.getCode());
            memberInfo.setHasBusiness(StaticEnum.EFFECTIVE.getCode());
            if("成功".equals(appDetail.getAuditStatus())) {
                memberInfo.setBusinessStatus(StaticEnum.member_sussess.getCode());
            }else if("资料提交中".equals(appDetail.getAuditStatus()) || "银行审核中".equals(appDetail.getAuditStatus()) ){
                memberInfo.setBusinessStatus(StaticEnum.member_shen.getCode());
            }else{
                memberInfo.setBusinessStatus(StaticEnum.member_fail.getCode());
            }
            return  memberInfo;
    }
    public MKCloudMemberInfo reConverupate(MKCloudApplicationImportDetail appDetail){
        MKCloudMemberInfo memberInfo=new MKCloudMemberInfo();
        memberInfo.setTel(appDetail.getApplyMobile());
        memberInfo.setChannelCode("0101");
        memberInfo.setMemberName(appDetail.getApplyName());
        memberInfo.setMemberCode(getMemberCode(memberInfo.getChannelCode(), appDetail.getApplyMobile()));
        memberInfo.setState( StaticEnum.EFFECTIVE.getCode());
        memberInfo.setHasBusiness(StaticEnum.EFFECTIVE.getCode());
        if("成功".equals(appDetail.getAuditStatus())) {
            memberInfo.setBusinessStatus(StaticEnum.member_sussess.getCode());
        }else if("资料提交中".equals(appDetail.getAuditStatus())){
            memberInfo.setBusinessStatus(StaticEnum.member_shen.getCode());
        }else{
            memberInfo.setBusinessStatus(StaticEnum.member_fail.getCode());
        }
        return  memberInfo;
    }
    public static String getMemberCode(String channelCode, String tel) {
            int codeLength = 4; // 4位验证码
            StringBuffer charValue = new StringBuffer();
            String str = "0123456789";
            Random random = new Random();
            for (int i = 0; i < codeLength; i++) {
                int number = random.nextInt(str.length());
                charValue.append(str.charAt(number));
            }
            StringBuffer sb = new StringBuffer(channelCode).append(tel).append(charValue);
            return sb.toString();
        }
    public MKCloudBankImportDetail reConver(MKCloudApplicationImportDetail appDetail,String batchId){
        MKCloudBankImportDetail bankDetail=new MKCloudBankImportDetail();
        bankDetail.setBatchId(batchId);
        bankDetail.setApplyId(appDetail.getVisitId());
        bankDetail.setCusName(appDetail.getApplyName());
        bankDetail.setCusTel(appDetail.getApplyMobile());
        bankDetail.setInstitutionName(appDetail.getApplyBank());
        bankDetail.setProductName(appDetail.getApplyProduct());
        bankDetail.setApplyCardDate(appDetail.getApplyCardTime());
        bankDetail.setApplyCardTime(appDetail.getApplyCardTime());
        bankDetail.setRemark(appDetail.getRemark());
        bankDetail.setAuditStatus(appDetail.getAuditStatus());
        bankDetail.setBusinessPeopleCode(appDetail.getBusinessPeopleCode());
        bankDetail.setBusinessPeopleName(appDetail.getBusinessPeopleName());
        bankDetail.setCusIdNo(appDetail.getApplyIdCard());
        return  bankDetail;
    }

    public MKCloudBankImportTotal reConver(List<MKCloudApplicationImportDetail> detailList,String batchId){
        MKCloudBankImportTotal total=new MKCloudBankImportTotal();
        total.setBatchId(batchId);
        total.setCounts(detailList.size());
        total.setState("0");
        return  total;
    }
}
