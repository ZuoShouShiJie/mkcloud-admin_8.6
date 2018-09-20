package mkcloudadmin.service.business.impl;

import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.*;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.MKCloudBusinessPeopleDetailVo;
import mkcloudadmin.service.business.BusinessPeopleService;
import mkcloudadmin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusinessPeopleServiceImpl implements BusinessPeopleService {
    @Autowired
    private MKCloudBusinessPeopleMapper businessPeopleMapper;
    @Autowired
    private MKCloudBusinessPeopleAccountMapper  businessPeopleAccountMapper;
    @Autowired
    private MKCloudBusinessPeopleAttMapper businessPeopleAttMapper;
    @Autowired
    private MKCloudMemberInfoMapper memberInfoMapper ;
    @Autowired
    private MKCloudApplicationImportDetailMapper applicationImportDetailMapper ;
    /**
     * (后管)
     * 根据businessPeopleCode查看查看推广人员信息   ，
     * @param peopleType
     * @return
     */
    @Override
    public MKCloudBusinessPeopleDetailVo selectPeopleCode(String peopleType) {
        List<MKCloudBusinessPeopleDetailVo> list=new ArrayList();
        MKCloudBusinessPeople peopleList= businessPeopleMapper.selectPeopleCode(peopleType);
        List<MKCloudBusinessPeopleAccount>  account=null;
        List<MKCloudBusinessPeopleAtt> atts=null;
        if(null!=peopleList ){
            account= businessPeopleAccountMapper.selectPeopleCode(peopleList.getBusinessPeopleCode());
            atts=businessPeopleAttMapper.selectPeopleCode(peopleList.getBusinessPeopleCode());
        }
        return   reConver(peopleList,account,null,null,atts);
    }

    /**
     * (后管)
     * 根据条件查询推广人员信息
     * @param
     * @return
     */
    @Override
    public   Map<String,Object>  selectQueryByCriteriaPeople(Page<MKCloudBusinessPeople> page,
                                                       String businessPeopleType,
                                                       String businessPeopleCode,
                                                       String businessPeopleName,
                                                       String tel,
                                                       String state) {
        Map<String,Object> resultMap = new HashMap<>();
        List<MKCloudBusinessPeopleDetailVo> list=new ArrayList();
        List<MKCloudBusinessPeople> peopleList = businessPeopleMapper.selectPeopleqQueryByCriteria( page,businessPeopleType,
               businessPeopleCode,
                businessPeopleName,
                 tel,
               state);
          if(null!=peopleList && peopleList.size()>0){
                for (MKCloudBusinessPeople people: peopleList){
                    Integer number =0;
                    Integer  monthNumber=0;
                    List<MKCloudBusinessPeopleAccount>  account= businessPeopleAccountMapper.selectPeopleCode(people.getBusinessPeopleCode());
                    List<MKCloudBusinessPeopleAtt> attList=businessPeopleAttMapper.selectPeopleCode(people.getBusinessPeopleCode());
                    if(people.getState().equals(StaticEnum.EFFECTIVE.getCode())) {
                        number = applicationImportDetailMapper.selectCount(people.getBusinessPeopleCode(),null);
                        monthNumber = applicationImportDetailMapper.selectCount(people.getBusinessPeopleCode(), DateUtils.dateToString(new Date(), "yyyy-MM"));
                    }
                        list.add(reConver(people,account,number,monthNumber,attList));
                    }
                }
            Long total =businessPeopleMapper.selectCount(businessPeopleType,businessPeopleCode,
                    businessPeopleName,
                    tel,
                    state);
        resultMap.put("count",total);
        resultMap.put("peoPleDetailList",list);
         return  resultMap;
    }
    /**
     * 新增内部推广人员
     * @param map
     * @return
     */
    @Transactional
    @Override
    public String addBusinessPeopleIn(Map<String, String> map) {
        MKCloudBusinessPeople people=  businessPeopleMapper.selectByTel(map.get("tel"));
        if(null==people) {
            List<MKCloudBusinessPeople> peopleList = businessPeopleMapper.selectByBusinessPeopleCode();
            String peoPleCode = businessPeopleCode(peopleList);
            List<MKCloudBusinessPeopleAtt> list = new ArrayList<>();
            MKCloudBusinessPeopleAtt account = new MKCloudBusinessPeopleAtt();
            account.setBusinessPeopleId(peoPleCode);
            account.setAttachmentName("faceOfIDCardUrl");
            account.setAttachmentSize("13k");
            account.setAttachmentAddress(map.get("faceOfIDCardUrl"));
            account.setAttachmentType("imge");
            list.add(account);

            MKCloudBusinessPeopleAtt account1 = new MKCloudBusinessPeopleAtt();
            account1.setBusinessPeopleId(peoPleCode);
            account1.setAttachmentName("backOfIDCardUrl");
            account1.setAttachmentSize("13k");
            account1.setAttachmentAddress(map.get("backOfIDCardUrl"));
            account1.setAttachmentType("imge");
            list.add(account1);

            MKCloudBusinessPeopleAtt account2 = new MKCloudBusinessPeopleAtt();
            account2.setBusinessPeopleId(peoPleCode);
            account2.setAttachmentName("handIDCardUrl");
            account2.setAttachmentSize("13k");
            account2.setAttachmentAddress(map.get("handIDCardUrl"));
            account2.setAttachmentType("imge");
            list.add(account2);

            MKCloudBusinessPeopleAtt account3 = new MKCloudBusinessPeopleAtt();
            account3.setBusinessPeopleId(peoPleCode);
            account3.setAttachmentName("contractUrl");
            account3.setAttachmentSize("13k");
            account3.setAttachmentAddress(map.get("contractUrl"));
            account3.setAttachmentType("imge");
            list.add(account3);

            for (MKCloudBusinessPeopleAtt att : list) {
                businessPeopleAttMapper.insertSelective(att);
            }

            MKCloudMemberInfo info = memberInfoMapper.selectByTel(map.get("tel"));
            MKCloudMemberInfo memberInfo = new MKCloudMemberInfo();
            memberInfo.setTel(map.get("tel"));
            if("0".equals(map.get("state"))){
                memberInfo.setType(BusinessPeopleTypeEnum.PEOPLE_TYPE_PEOPLE.getCode());
            }else if("1".equals(map.get("state"))){
                memberInfo.setType(BusinessPeopleTypeEnum.PEOPLE_TYPE_IN1.getCode());
            }
            if (null == info) {
                memberInfo.setChannelCode("0101");
                memberInfo.setMemberName(map.get("businessPeopleName"));
                memberInfo.setMemberCode(getMemberCode(memberInfo.getChannelCode(), map.get("tel")));
                memberInfo.setWorkAddress(map.get("province") + map.get("city") + map.get("district"));
                memberInfo.setState(map.get("state"));
                memberInfo.setHasBusiness(StaticEnum.INVALID.getCode());
                memberInfoMapper.insertSelective(memberInfo);
            }else{
                if(null!=info && null!=info.getMemberName() && !"".equals(info.getMemberName()) && !map.get("businessPeopleName").equals(info.getMemberName())) {
                    return  "输入的姓名"+map.get("businessPeopleName")+"与注册会员时不同!";
                }
                memberInfoMapper.updateBySelective(memberInfo);
            }

            MKCloudBusinessPeople mkCloudBusinessPeople = new MKCloudBusinessPeople();
            mkCloudBusinessPeople.setBusinessPeopleCode(peoPleCode);
            mkCloudBusinessPeople.setBusinessPeopleName(map.get("businessPeopleName"));
            mkCloudBusinessPeople.setIdNo(map.get("idNo"));
            mkCloudBusinessPeople.setTel(map.get("tel"));
            mkCloudBusinessPeople.setWchat(map.get("wchat"));
            mkCloudBusinessPeople.setBusinessPeopleType("0");
            mkCloudBusinessPeople.setBusinessPeopleLevel(map.get("businessPeopleLevel"));
            mkCloudBusinessPeople.setProvince(map.get("province"));
            mkCloudBusinessPeople.setCity(map.get("city"));
            mkCloudBusinessPeople.setDistrict(map.get("district"));
            mkCloudBusinessPeople.setDetailedAddress(map.get("detailedAddress"));
            mkCloudBusinessPeople.setState(map.get("state"));

            businessPeopleMapper.insertSelective(mkCloudBusinessPeople);

            MKCloudBusinessPeopleAccount mkCloudBusinessPeopleAccount = new MKCloudBusinessPeopleAccount();

            mkCloudBusinessPeopleAccount.setAccountBank(map.get("accountBank"));
            mkCloudBusinessPeopleAccount.setBusinessPeopleId(peoPleCode);
            mkCloudBusinessPeopleAccount.setAccountCode(map.get("accountCode"));
            mkCloudBusinessPeopleAccount.setAccountState(map.get("1"));
            businessPeopleAccountMapper.insertSelective(mkCloudBusinessPeopleAccount);
            return "";
        }else{
           return   "输入的手机号已存在!";
        }

    }
    /**
     * 修改内部推广人员
     * @param map
     * @return
     */
    @Transactional
    @Override
    public String updateBusinessPeopleIn(Map<String, String> map) {
        boolean ok=false;

        List<MKCloudBusinessPeopleAtt> list=new ArrayList<>();
        String businessPeopleCode = map.get("businessPeopleCode");
        MKCloudBusinessPeopleAtt attr=new MKCloudBusinessPeopleAtt();
        attr.setAttachmentName("faceOfIDCardUrl");
        attr.setAttachmentSize("13k");
        attr.setAttachmentAddress(map.get("faceOfIDCardUrl"));
        list.add(attr);
        attr=new MKCloudBusinessPeopleAtt();
        attr.setAttachmentName("backOfIDCardUrl");
        attr.setAttachmentSize("13k");
        attr.setAttachmentAddress(map.get("backOfIDCardUrl"));
        list.add(attr);

        attr=new MKCloudBusinessPeopleAtt();
        attr.setAttachmentName("handIDCardUrl");
        attr.setAttachmentSize("13k");
        attr.setAttachmentAddress(map.get("handIDCardUrl"));
        list.add(attr);

        attr=new MKCloudBusinessPeopleAtt();
        attr.setId(Long.valueOf(map.get("attId")));
        attr.setAttachmentName("contractUrl");
        attr.setAttachmentSize("13k");
        attr.setAttachmentAddress(map.get("contractUrl"));
        list.add(attr);


        MKCloudBusinessPeopleDetailVo people = new MKCloudBusinessPeopleDetailVo();
        people.setId(Long.valueOf(map.get("id")));
        people.setBusinessPeopleCode(map.get("businessPeopleCode"));
        people.setBusinessPeopleName(map.get("businessPeopleName"));
        people.setIdNo(map.get("idNo"));
        people.setTel(map.get("tel"));
        people.setWchat(map.get("wchat"));
        people.setBusinessPeopleType(BusinessPeopleTypeEnum.PEOPLE_TYPE_IN.getCode());
        people.setBusinessPeopleLevel(map.get("businessPeopleLevel"));
        people.setProvince(map.get("province"));
        people.setCity(map.get("city"));
        people.setDistrict(map.get("district"));
        people.setDetailedAddress(map.get("detailedAddress"));
        people.setState(map.get("state"));
        people.setAccountId(Long.valueOf(map.get("accountId")));
        people.setAccountBank(map.get("accountBank"));
        people.setAccountCode(map.get("accountCode"));
        people.setAccountState(StaticEnum.EFFECTIVE.getCode());
        people.setAttachmentType(map.get("attachmentType"));
        people.setAttachmentName(map.get("attachmentName"));
        people.setAttachmentSize(map.get("attachmentSize"));

        MKCloudBusinessPeople people1=  businessPeopleMapper.selectPeopleCode(people.getBusinessPeopleCode());
        if(null!=people1 && null!=people1.getTel() && !"".equals(people1.getTel()) && !people.getTel().equals(people1.getTel())){
            MKCloudMemberInfo memberInfo1=  memberInfoMapper.selectByTel(people1.getTel());
            MKCloudMemberInfo memberInfo2=  memberInfoMapper.selectByTel(people.getTel());
            if(null!=memberInfo1 && null!=memberInfo2 && !memberInfo1.getId().equals(memberInfo2.getId())){
                return  "输入的手机号已存在!";
            }else{
                memberInfo1.setTel(people.getTel());
                memberInfo1.setMemberName(people.getBusinessPeopleName());
                memberInfoMapper.updateByPrimaryKeySelective(memberInfo1);
            }

            businessPeopleMapper.updateByPrimaryKeySelective(reConverPeople(people));
        }else{
            businessPeopleMapper.updateByPrimaryKeySelective(reConverPeople(people));
        }
        if(null!=list  && list.size()>0) {
            for (MKCloudBusinessPeopleAtt att : list) {
                if(!"".equals(att.getAttachmentAddress()) && null!=att.getAttachmentAddress()) {
                    businessPeopleAttMapper.updateByBusinessPeopleCodeAndAttName(att.getAttachmentAddress(),businessPeopleCode,att.getAttachmentName());
                }
            }
        }
       List<MKCloudBusinessPeopleAccount> accountList=businessPeopleAccountMapper.selectPeopleCode(people.getBusinessPeopleCode());
        if (null != accountList && accountList.size()>0) {
        for (MKCloudBusinessPeopleAccount account:accountList) {
                if (people.getAccountBank().equals(account.getAccountBank())
                        && people.getAccountCode().equals(account.getAccountCode())) {
                    ok=true;
                }
                if (ok){ break;}
            }
            if(!ok){
                businessPeopleAccountMapper.updateByStatic(StaticEnum.INVALID.getCode(),people.getBusinessPeopleCode());
                businessPeopleAccountMapper.insertSelective(reConverAccount(people));
            }
        }
        if("0".equals(people.getState())) {
            MKCloudMemberInfo memberInfo = new MKCloudMemberInfo();
            memberInfo.setTel(people.getTel());
            memberInfo.setState(people.getState());
            memberInfo.setType(BusinessPeopleTypeEnum.PEOPLE_TYPE_PEOPLE.getCode());
            memberInfoMapper.updateBySelective(memberInfo);
        }else if("1".equals(people.getState())){
            MKCloudMemberInfo memberInfo = new MKCloudMemberInfo();
            memberInfo.setTel(people.getTel());
            memberInfo.setState(people.getState());
            memberInfo.setType(BusinessPeopleTypeEnum.PEOPLE_TYPE_IN1.getCode());
            memberInfoMapper.updateBySelective(memberInfo);
        }
        return  "";
    }

    /**
     * 审核外部推广人，状态1为通过0为拒绝
     * @param statc
     * @param peopleCode
     */
    @Override
    @Transactional
    public void updateByStatic(String statc,String peopleCode){
        businessPeopleMapper.updateByStatic(statc,peopleCode);
        businessPeopleAccountMapper.updateByStatic(statc,peopleCode);
       /*MKCloudBusinessPeople people=  businessPeopleMapper.selectPeopleCode(peopleCode);
        if(null!=people){
        memberInfoMapper.updateByStatic(statc, people.getTel());
        }*/
    }
    public static MKCloudBusinessPeopleDetailVo reConver(MKCloudBusinessPeople people,List<MKCloudBusinessPeopleAccount> account,Integer number ,Integer mathNumber,List<MKCloudBusinessPeopleAtt> atts){
        MKCloudBusinessPeopleDetailVo detail=new MKCloudBusinessPeopleDetailVo();
        detail.setAllCommission(number.longValue());
        detail.setInCommission(mathNumber.longValue());
        detail.setId(people.getId());
        detail.setBusinessPeopleCode(people.getBusinessPeopleCode());
        detail.setBusinessPeopleName(people.getBusinessPeopleName());
        detail.setTel(people.getTel());
        detail.setIdNo(people.getIdNo());
        detail.setState(people.getState());
        detail.setStateName(people.getState());
        if(null!=account & account.size()>0)  {
            for (MKCloudBusinessPeopleAccount  account1: account){
                if(StaticEnum.EFFECTIVE.getCode().equals(account1.getAccountState())){
                    detail.setAccountId(account1.getId());
                    detail.setAccountBank(account1.getAccountBank());
                    detail.setAccountCode(account1.getAccountCode());
                    detail.setAccountState(account1.getAccountState());
                }
            }
        }
        detail.setWchat(people.getWchat());
        detail.setBusinessPeopleType(people.getBusinessPeopleType());
        if("0".equals(people.getBusinessPeopleLevel())){
            detail.setBusinessPeopleLevel("低级");
        }else  if("1".equals(people.getBusinessPeopleLevel())){
            detail.setBusinessPeopleLevel("中级");
        }else if("2".equals(people.getBusinessPeopleLevel())){
            detail.setBusinessPeopleLevel("高级");
        }else{
            detail.setBusinessPeopleLevel(null);
        }
        detail.setProvince(people.getProvince());
        detail.setCity(people.getCity());
        detail.setDistrict(people.getDistrict());
        detail.setDetailedAddress(people.getDetailedAddress());
        detail.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(people.getCreateTime()));
        detail.setCreateUser(people.getCreateUser());
        detail.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(people.getUpdateTime()));
        detail.setUpdateUser(people.getUpdateUser());
        if(null!=atts && atts.size()>0){
            for (MKCloudBusinessPeopleAtt att :atts){
                if("backOfIDCardUrl".equals(att.getAttachmentName())){
                    detail.setAttId(att.getId());
                    detail.setBackOfIDCardUrl(att.getAttachmentAddress());}
                if("faceOfIDCardUrl".equals(att.getAttachmentName())){
                    detail.setAttId(att.getId());
                    detail.setFaceOfIDCardUrl(att.getAttachmentAddress());}
                if("handIDCardUrl".equals(att.getAttachmentName())){
                    detail.setAttId(att.getId());
                    detail.setHandIDCardUrl(att.getAttachmentAddress());}
                if("contractUrl".equals(att.getAttachmentName())){
                    detail.setAttId(att.getId());
                    detail.setContractUrl(att.getAttachmentAddress());
                }
            }
        }
        return detail;
    }

    public static MKCloudBusinessPeopleAccount reConverAccount(MKCloudBusinessPeopleDetailVo people){
        MKCloudBusinessPeopleAccount account=new MKCloudBusinessPeopleAccount();
        account.setBusinessPeopleId(people.getBusinessPeopleCode());
        account.setAccountBank(people.getAccountBank());
        account.setAccountCode(people.getAccountCode());
        account.setAccountState(people.getState());
        return account;
    }

    public static MKCloudBusinessPeople reConverPeople(MKCloudBusinessPeopleDetailVo people){
        MKCloudBusinessPeople detail=new MKCloudBusinessPeople();
        detail.setId(people.getId());
        detail.setBusinessPeopleCode(people.getBusinessPeopleCode());
        detail.setBusinessPeopleName(people.getBusinessPeopleName());
        detail.setTel(people.getTel());
        detail.setIdNo(people.getIdNo());
        detail.setWchat(people.getWchat());
        detail.setBusinessPeopleType(people.getBusinessPeopleType());
        detail.setBusinessPeopleLevel(people.getBusinessPeopleLevel());
        detail.setProvince(people.getProvince());
        detail.setCity(people.getCity());
        detail.setDistrict(people.getDistrict());
        detail.setDetailedAddress(people.getDetailedAddress());
        detail.setState(people.getState());
        return detail;
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
    public  String businessPeopleCode(List<MKCloudBusinessPeople> peopleList){
        String peoPleCode = null;
        if (null != peopleList && peopleList.size() > 0) {
            peoPleCode = peopleList.get(peopleList.size() - 1).getBusinessPeopleCode();
            if ("9".equals(peoPleCode.substring(0,1)) && peoPleCode.length()>=7) {
                Integer pCode = Integer.valueOf(peoPleCode.substring(1));
                return  (String.valueOf(++pCode));
            } else {
                Integer pCode = Integer.valueOf(peoPleCode);
                return  (String.valueOf(++pCode));
            }
        } else {
            return  "100001";
        }
    }
}
