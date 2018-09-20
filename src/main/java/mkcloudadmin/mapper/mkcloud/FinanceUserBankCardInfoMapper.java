package mkcloudadmin.mapper.mkcloud;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mkcloudadmin.model.mkcloud.po.FinanceUserBankCardInfo;

public interface FinanceUserBankCardInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceUserBankCardInfo record);

    int insertSelective(FinanceUserBankCardInfo record);

    FinanceUserBankCardInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceUserBankCardInfo record);

    int updateByPrimaryKey(FinanceUserBankCardInfo record);

    /**
     * 查询用户的默认银行卡信息.
     * @param userId
     * @return
     * @author hewenbin
     * @version FinanceUserBankCardInfoMapper.java, v1.0 2018年7月10日 下午7:52:19 hewenbin
     */
    List<FinanceUserBankCardInfo> selectDefaultBankCard(List userId);
    
    /**
     * 通过银行卡账户姓名的“姓”匹配用户的默认银行卡.
     * @param accountName 示例：李
     * @return
     * @author hewenbin
     * @version FinanceUserBankCardInfoMapper.java, v1.0 2018年7月12日 下午5:54:50 hewenbin
     */
    List<FinanceUserBankCardInfo> selectDefaultCardLikeFirstName(@Param("firstName") String firstName);
}