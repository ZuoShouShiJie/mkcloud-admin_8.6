package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceUserRegisterChannelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceUserRegisterChannelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceUserRegisterChannelInfo record);

    int insertSelective(FinanceUserRegisterChannelInfo record);

    FinanceUserRegisterChannelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceUserRegisterChannelInfo record);

    int updateByPrimaryKey(FinanceUserRegisterChannelInfo record);

    /**
     * 查询用户的注册渠道
     *
     * @param userId
     * @return java.util.List<mkcloudadmin.model.mkcloud.po.FinanceUserRegisterChannelInfo>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 17:55
     */
    List<FinanceUserRegisterChannelInfo> selectChannelByUserId(List userId);

    /**
     * 根据渠道查用户
     *
     * @param page
     * @param realName
     * @param channelCode
     * @return java.util.List<mkcloudadmin.model.mkcloud.po.FinanceUserRegisterChannelInfo>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 17:54
     */
    List<FinanceUserRegisterChannelInfo> queryUserByChannelCode(@Param("page") Page<FinanceUserRegisterChannelInfo> page, @Param("channelCode") String channelCode);

    /**
     * 根据姓名，渠道查用户数
     *
     * @param realName
     * @param channelCode
     * @return java.lang.Long
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 17:43
     */
    Long queryCountByChannelCode(@Param("channelCode") String channelCode);
}