package mkcloudadmin.service.mkcloudproduct.impl;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.mapper.mkcloud.FinanceCreditCardDetailMapper;
import mkcloudadmin.mapper.mkcloud.FinanceProductMainMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudCreditCardInfoMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceCreditCardDetail;
import mkcloudadmin.model.mkcloud.po.FinanceProductMain;
import mkcloudadmin.model.mkcloud.po.MKCloudCreditCardInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudCreditCardInfoVO;
import mkcloudadmin.service.mkcloudproduct.CreditCardProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mkcloud-admin
 *
 * @description: 信用卡操作实现层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-10 11:52
 **/
@Service
public class CreditCardProductImpl implements CreditCardProduct{

    @Autowired
    private FinanceCreditCardDetailMapper financeCreditCardDetailMapper;

    @Autowired
    private FinanceProductMainMapper financeProductMainMapper;

    @Autowired
    private MKCloudCreditCardInfoMapper creditCardInfoMapper;

    @Autowired
    private BaseApi baseApi;

    @Override
    public Map<String,Object> queryFinanceProductList(String productCode, String productName, String status, Page<MKCloudCreditCardInfo> page) {

        List<MKCloudCreditCardInfo> cloudCreditCardInfos = creditCardInfoMapper.selectProducts(productCode,productName,status,page);
        Long total = creditCardInfoMapper.selectProductsCount(productCode,productName,status);
//        if (total>0){
//
//        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",reConver(cloudCreditCardInfos));


        return resultMap;
    }

    @Override
    @Transactional
    public Boolean createCreditCarProduct(Map<String, String> map) {
        MKCloudCreditCardInfo creditCardInfo = new MKCloudCreditCardInfo();

        creditCardInfo.setProductCode(map.get("productCode"));
        creditCardInfo.setProductName(map.get("productName"));
        creditCardInfo.setPointUrl(map.get("pointUrl"));
        creditCardInfo.setDetailPageUrl(map.get("detailPageUrl"));
        creditCardInfo.setBannerUrl(map.get("logoUrl"));
        creditCardInfo.setInstitutionId(map.get("institutionId"));
        creditCardInfo.setAllCommission(new BigDecimal(map.get("allCommission")));
//        creditCardInfo.setInCommission(new BigDecimal(map.get("inCommission")));
        creditCardInfo.setOutCommission(new BigDecimal(map.get("outCommission")));
        creditCardInfo.setOutCommission2(new BigDecimal(map.get("outCommission2")));
        creditCardInfo.setOutCommission3(new BigDecimal(map.get("outCommission3")));

        creditCardInfo.setOrder(new Integer(map.get("seqNo")));
        creditCardInfo.setStatus(Integer.valueOf(map.get("status")));
        int i = 0;
        if("add".equals(map.get("opMethod"))){//新增
            creditCardInfo.setCreator(baseApi.getUserId());
            i = creditCardInfoMapper.insertSelective(creditCardInfo);

        }else if("edit".equals(map.get("opMethod"))){//维护
            creditCardInfo.setId(Long.valueOf(map.get("productId")));
            creditCardInfo.setUpdator(baseApi.getUserId());
            i=creditCardInfoMapper.updateByPrimaryKeySelective(creditCardInfo);
        }
        if(1==i){
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public Boolean updateFinanceProduct(Map<String,String> map) {
        FinanceProductMain financeProductMain = null;
        FinanceCreditCardDetail  fccd = null;

        financeProductMain = new FinanceProductMain();
        financeProductMain.setId(Long.valueOf(map.get("productId")));
        financeProductMain.setProductName(map.get("productName"));
        financeProductMain.setType(2);
        financeProductMain.setRedirectUrl(map.get("redirectUrl"));
        financeProductMain.setAmountType(1); //1:金额值

        financeProductMain.setTotalBonus(new BigDecimal(map.get("totalBonus")));
        financeProductMain.setTerminalBonus(new BigDecimal(map.get("terminalBonus")));
        financeProductMain.setDirectBonus(new BigDecimal(map.get("directBonus")));
        financeProductMain.setIndirectBonus(new BigDecimal(map.get("indirectBonus")));

        financeProductMain.setLogoUrl(map.get("logoUrl"));
        financeProductMain.setSeqNo(new Integer(map.get("seqNo")));
        financeProductMain.setCashbackDate(map.get("cashbackDate"));
        if("Y".equals(map.get("isShow"))){
            financeProductMain.setIsShow(new Integer(1));
        }else {
            financeProductMain.setIsShow(new Integer(0));
        }

        financeProductMainMapper.updateByPrimaryKeySelective(financeProductMain);
        fccd = new FinanceCreditCardDetail();

        fccd.setProductId(Long.valueOf(map.get("productId")));
        fccd.setDetailPageUrl(map.get("detailPageUrl"));
        fccd.setPassRate(map.get("passRate"));
        fccd.setRebackCashDesc(map.get("rebackCashDesc"));

        financeCreditCardDetailMapper.updateByProductIdSelective(fccd);
        return true;
    }
    @Override
    public MKCloudCreditCardInfo queryCreditCardProductForEdit(Long productId){

        MKCloudCreditCardInfo creditCardInfo =creditCardInfoMapper.selectByPrimaryKey(productId);
        return creditCardInfo;
    }
    @Override
    @Transactional
    public void delCreditCardProductById(Long productId){

        creditCardInfoMapper.deleteLogicByPrimaryKey(productId);
    }

    @Override
    public Boolean checkProductCode(String productCode) {
        Long l = creditCardInfoMapper.selectByProductCode(productCode);
        if(l==0){
            return false;
        }
        return true;
    }

    public   List<MKCloudCreditCardInfoVO> reConver(List<MKCloudCreditCardInfo> cardInfos){
        List<MKCloudCreditCardInfoVO> list=new ArrayList<>();
        MKCloudCreditCardInfoVO  info=null;
        if(null!=cardInfos && cardInfos.size()>0) {
            for (MKCloudCreditCardInfo cardInfo : cardInfos) {
                info = new MKCloudCreditCardInfoVO();
                info.setId(cardInfo.getId());
                info.setProductCode(cardInfo.getProductCode());
                info.setProductName(cardInfo.getProductName());
                info.setInstitutionId(cardInfo.getInstitutionId());
                info.setPointUrl(cardInfo.getPointUrl());
                info.setLable(cardInfo.getLable());
                info.setDetailPageUrl(cardInfo.getDetailPageUrl());
                info.setBannerUrl(cardInfo.getBannerUrl());
                info.setAllCommission(cardInfo.getAllCommission());
                info.setInCommission(cardInfo.getInCommission());
                info.setOutCommission(cardInfo.getOutCommission());
                info.setOutCommission2(cardInfo.getOutCommission2());
                info.setOutCommission3(cardInfo.getOutCommission3());
                info.setOrder(cardInfo.getOrder());
                info.setStatus(cardInfo.getStatus());
                info.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cardInfo.getCreateTime()));
                info.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cardInfo.getUpdateTime()));
                info.setIsDelete(cardInfo.getIsDelete());
                info.setCreator(cardInfo.getCreator());
                info.setUpdator(cardInfo.getUpdator());
                info.setVersion(cardInfo.getVersion());
                list.add(info);
            }
        }
        return  list;
    }

}
