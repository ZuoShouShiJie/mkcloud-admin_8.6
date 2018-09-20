package mkcloudadmin.mapper.mkcloud;

import java.util.List;

import mkcloudadmin.model.mkcloud.po.FinanceUserInviteInfo;

public interface FinanceUserInviteInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceUserInviteInfo record);

    int insertSelective(FinanceUserInviteInfo record);

    FinanceUserInviteInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceUserInviteInfo record);

    int updateByPrimaryKey(FinanceUserInviteInfo record);

    /**
     * 根据多个id查邀请人信息集合
     *
     * @param userId
     * @return java.util.List<mkcloudadmin.model.mkcloud.po.FinanceUserInviteInfo>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 18:22
     */
    List<FinanceUserInviteInfo> selectInviteInfoByUserId(List<Long> userId);
}