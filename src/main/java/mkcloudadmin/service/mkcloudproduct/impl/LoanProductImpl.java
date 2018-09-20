package mkcloudadmin.service.mkcloudproduct.impl;

import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.mapper.mkcloud.FinanceLoanDetailMapper;
import mkcloudadmin.mapper.mkcloud.FinanceProductMainMapper;
import mkcloudadmin.mapper.mkcloud.MKCloudLoanInfoMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceLoanDetail;
import mkcloudadmin.model.mkcloud.po.FinanceProductMain;
import mkcloudadmin.model.mkcloud.po.MKCloudLoanInfo;
import mkcloudadmin.model.mkcloud.vo.LoanProductVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudLoanInfoVO;
import mkcloudadmin.service.mkcloudproduct.LoanProduct;
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
 * @description: 贷款产品业务层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-10 20:24
 **/
@Service
public class LoanProductImpl implements LoanProduct{
    @Autowired
    private FinanceLoanDetailMapper financeLoanDetailMapper;
    @Autowired
    private FinanceProductMainMapper financeProductMainMapper;
    @Autowired
    private MKCloudLoanInfoMapper loanInfoMapper;
    @Autowired
    private BaseApi baseApi;
    @Override
    @Transactional
    public Boolean createFinanceProduct(Map<String, String> map) {
        MKCloudLoanInfo loanInfo = new MKCloudLoanInfo();

        loanInfo.setProductCode(map.get("productCode"));
        loanInfo.setProductName(map.get("productName"));
        loanInfo.setType(map.get("type"));
        loanInfo.setPointUrl(map.get("pointUrl"));
        loanInfo.setDetailPageUrl(map.get("detailPageUrl"));
        loanInfo.setBannerUrl(map.get("logoUrl"));
        loanInfo.setInstitutionId(map.get("institutionId"));
        loanInfo.setAllCommission(new BigDecimal(map.get("allCommission")));
        loanInfo.setInCommission(new BigDecimal(map.get("inCommission")));
        loanInfo.setOutCommission(new BigDecimal(map.get("outCommission")));

        loanInfo.setOrder(new Integer(map.get("seqNo")));
        loanInfo.setStatus(Integer.valueOf(map.get("status")));
        int i = 0;
        if("add".equals(map.get("opMethod"))){
            loanInfo.setCreator(baseApi.getUserId());
            i = loanInfoMapper.insertSelective(loanInfo);

        }else if("edit".equals(map.get("opMethod"))){
            loanInfo.setId(Long.valueOf(map.get("productId")));
            loanInfo.setUpdator(baseApi.getUserId());
            i=loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
        }
        if(1==i){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateFinanceProduct(Map<String, String> map) {
        FinanceProductMain financeProductMain = null;
        FinanceLoanDetail  fld = null;

        financeProductMain = new FinanceProductMain();
        financeProductMain.setId(Long.valueOf(map.get("productId")));
        financeProductMain.setProductName(map.get("productName"));
        financeProductMain.setType(3);
        financeProductMain.setRedirectUrl(map.get("redirectUrl"));
        financeProductMain.setAmountType(2); //1:百分比

        financeProductMain.setTotalBonus((new BigDecimal(map.get("totalBonus").replace("%","")).divide(new BigDecimal(100))).setScale(4));
        financeProductMain.setTerminalBonus((new BigDecimal(map.get("terminalBonus").replace("%","")).divide(new BigDecimal(100))).setScale(4));
        financeProductMain.setDirectBonus((new BigDecimal(map.get("directBonus").replace("%","")).divide(new BigDecimal(100))).setScale(4));
        financeProductMain.setIndirectBonus((new BigDecimal(map.get("indirectBonus").replace("%","")).divide(new BigDecimal(100))).setScale(4));


        financeProductMain.setLogoUrl(map.get("logoUrl"));
        financeProductMain.setSeqNo(new Integer(map.get("seqNo")));
        financeProductMain.setCashbackDate(map.get("cashbackDate"));
        if("Y".equals(map.get("isShow"))){
            financeProductMain.setIsShow(new Integer(1));
        }else {
            financeProductMain.setIsShow(new Integer(0));
        }

        financeProductMainMapper.updateByPrimaryKeySelective(financeProductMain);

        fld = new FinanceLoanDetail();
        fld.setProductId(Long.valueOf(map.get("productId")));
        fld.setMark1(map.get("mark1"));
        fld.setMark2(map.get("mark2"));
        fld.setAmountScope(map.get("amountScope"));
        fld.setDateScope(map.get("dateScope"));

        financeLoanDetailMapper.updateByProductIdSelective(fld);
        return true;
    }

    @Override
   public Map<String,Object> queryLoanProductList(String productCode, String productName, String status, Page<MKCloudLoanInfo> loanInfoPage) {
        List<MKCloudLoanInfo> loanInfos = loanInfoMapper.selectLoans(productCode,productName,status,loanInfoPage);
        Long total = loanInfoMapper.selectLoanCount(productCode,productName,status);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",reConver(loanInfos));
        return resultMap;
    }

    @Override
    public LoanProductVO querySingleFinanceProductForEdit(Long productId) {
        FinanceProductMain fpm = financeProductMainMapper.selectByPrimaryKey(productId);
        FinanceLoanDetail financeLoanDetail = financeLoanDetailMapper.selectByProductId(productId);
        LoanProductVO loanProductVO = new LoanProductVO();
        loanProductVO.setId(fpm.getId());
        loanProductVO.setProductName(fpm.getProductName());
        loanProductVO.setType(fpm.getType());
        loanProductVO.setRedirectUrl(fpm.getRedirectUrl());
        loanProductVO.setLogoUrl(fpm.getLogoUrl());
        loanProductVO.setSeqNo(fpm.getSeqNo());
        loanProductVO.setCashbackDate(fpm.getCashbackDate());
        loanProductVO.setIsShow(fpm.getIsShow());

        loanProductVO.setMark1(financeLoanDetail.getMark1());
        loanProductVO.setMark2(financeLoanDetail.getMark2());
        loanProductVO.setAmountScope(financeLoanDetail.getAmountScope());
        loanProductVO.setDateScope(financeLoanDetail.getDateScope());

        loanProductVO.setTotalBonus(fpm.getTotalBonus());
        loanProductVO.setDirectBonus(fpm.getDirectBonus());
        loanProductVO.setTerminalBonus(fpm.getTerminalBonus());
        loanProductVO.setIndirectBonus(fpm.getIndirectBonus());

        return loanProductVO;
    }

    @Override
    @Transactional
    public void deleteFinanceProductByProductId(Long productId) {
        loanInfoMapper.deleteLogicByPrimaryKey(productId);

    }
    @Override
    public Boolean checkProductCode(String productCode) {
        Long l = loanInfoMapper.selectByProductCode(productCode);
        if(l==0){
            return false;
        }
        return true;
    }


    public  List<MKCloudLoanInfoVO> reConver(List<MKCloudLoanInfo>  infos) {
        List<MKCloudLoanInfoVO> list = new ArrayList<>();
        MKCloudLoanInfoVO infoVO = null;
        if (null != infos && infos.size() > 0) {
            for (MKCloudLoanInfo info : infos) {
                infoVO = new MKCloudLoanInfoVO();
                infoVO.setId(info.getId());
                infoVO.setProductCode(info.getProductCode());
                infoVO.setProductName(info.getProductName());
                infoVO.setInstitutionId(info.getInstitutionId());
                infoVO.setType(info.getType());
                infoVO.setLimitRange(info.getLimitRange());
                infoVO.setPeriodRange(info.getPeriodRange());
                infoVO.setPointUrl(info.getPointUrl());
                infoVO.setLable(info.getLable());
                infoVO.setDetailPageUrl(info.getDetailPageUrl());
                infoVO.setBannerUrl(info.getBannerUrl());
                infoVO.setAllCommission(info.getAllCommission());
                infoVO.setInCommission(info.getInCommission());
                infoVO.setOutCommission(info.getOutCommission());
                infoVO.setOutCommission2(info.getOutCommission2());
                infoVO.setOutCommission3(info.getOutCommission3());
                infoVO.setOrder(info.getOrder());
                infoVO.setStatus(info.getStatus());
                infoVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info.getCreateTime()));
                infoVO.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info.getUpdateTime()));
                infoVO.setIsDelete(info.getIsDelete());
                infoVO.setCreator(info.getCreator());
                infoVO.setUpdator(info.getUpdator());
                infoVO.setVersion(info.getVersion());
                list.add(infoVO);
            }
        }
        return  list;
    }
}
