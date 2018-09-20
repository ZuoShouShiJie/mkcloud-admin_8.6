package mkcloudadmin.mapper.mkcloud;


import mkcloudadmin.model.mkcloud.po.FinanceUserAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FinanceUserAccountMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FinanceUserAccount record);

    int insertSelective(FinanceUserAccount record);

    FinanceUserAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceUserAccount record);

    int updateByPrimaryKey(FinanceUserAccount record);

    FinanceUserAccount checkUserBalance(FinanceUserAccount account);

    int countDownMoney(FinanceUserAccount userAcc);

    int increaseMoney(FinanceUserAccount userAcc);

    List<FinanceUserAccount> selectAccounts();

    int upCanWithDrawMoneyAndDownIncome(@Param("id") Long userId,@Param("money") BigDecimal sum,@Param("versionNum") Long versionNum);
}