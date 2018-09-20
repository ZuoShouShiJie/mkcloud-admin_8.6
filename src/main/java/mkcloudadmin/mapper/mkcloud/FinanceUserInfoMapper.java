package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceUserInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceUserInfo record);

    int insertSelective(FinanceUserInfo record);

    FinanceUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceUserInfo record);

    int updateByPrimaryKey(FinanceUserInfo record);


    /**
     * 根据用户id集合查询用户信息集合
     *
     * @param userId
     * @return java.util.List<mkcloudadmin.model.mkcloud.po.FinanceUserInfo>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 18:26
     */
    List<FinanceUserInfo> selectUserByIdList(List<Long> userId);


    /**
     * 通过手机号模糊匹配用户（中间四位模糊匹配）.
     *
     * @param mobileNum 示例：139____2323
     * @return
     * @author hewenbin
     * @version FinanceUserInfoMapper.java, v1.0 2018年7月12日 下午5:45:55 hewenbin
     */
    List<FinanceUserInfo> selectLikeMobile(@Param("mobileNum") String mobileNum);

    
    /**
     * 查询用户基本信息、注册信息.
     * @param mobileNum 为空则不限制
     * @param platformCode 为空则不限制
     * @param channelCode 为空则不限制
     * @param createBeginDate 为空则不限制
     * @param createEndDate 为空则不限制
     * @param page not null
     * @return
     * @author hewenbin
     * @version FinanceUserInfoMapper.java, v1.0 2018年7月21日 下午5:11:41 hewenbin
     */
	List<MKCloudMemberInfoVo> selectUsers(@Param("mobileNum") String mobileNum,
                                          @Param("platformCode") String platformCode, @Param("channelCode") String channelCode,
                                          @Param("createBeginDate") String createBeginDate, @Param("createEndDate") String createEndDate,
                                          @Param("page") Page<MKCloudMemberInfoVo> page);
	
	Long selectUserCount(@Param("mobileNum") String mobileNum,
			@Param("platformCode") String platformCode, @Param("channelCode") String channelCode,
			@Param("createBeginDate") String createBeginDate, @Param("createEndDate") String createEndDate);
}