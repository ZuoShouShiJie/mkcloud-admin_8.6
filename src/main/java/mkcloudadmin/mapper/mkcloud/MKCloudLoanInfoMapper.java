package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudLoanInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudLoanInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudLoanInfo record);

    int insertSelective(MKCloudLoanInfo record);

    MKCloudLoanInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudLoanInfo record);

    int updateByPrimaryKey(MKCloudLoanInfo record);

    List<MKCloudLoanInfo> selectLoans(@Param("productCode") String productCode, @Param("productName") String productName, @Param("status") String status,
                                      @Param("page") Page<MKCloudLoanInfo> loanInfoPage);

    Long selectLoanCount(@Param("productCode") String productCode, @Param("productName") String productName, @Param("status") String status);

    int deleteLogicByPrimaryKey(@Param("productId") Long productId);

    Long selectByProductCode(@Param("productCode") String productCode);
}