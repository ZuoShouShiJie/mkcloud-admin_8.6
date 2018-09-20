package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudMemberInfo;
import mkcloudadmin.model.mkcloud.vo.MKCloudClientBaseInfoVO;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudMemberInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudMemberInfo record);

    int insertSelective(MKCloudMemberInfo record);

    MKCloudMemberInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudMemberInfo record);
    int updateBySelective(MKCloudMemberInfo record);
    int updateByPrimaryKey(MKCloudMemberInfo record);

    List<MKCloudMemberInfoVo> selectMembers(@Param("memberCode") String memberCode, @Param("memberName") String memberName, @Param("memberMobile") String memberMobile,
                                            @Param("businessCode") String businessCode, @Param("businessName") String businessName, @Param("businessMobile") String businessMobile,
                                            @Param("businessStatus") String businessStatus, @Param("registerBeginDate") String registerBeginDate, @Param("registerEndDate") String registerEndDate,
                                            @Param("page") Page<MKCloudMemberInfoVo> page);

    Long selectMemberCount(@Param("memberCode") String memberCode, @Param("memberName") String memberName, @Param("memberMobile") String memberMobile,
                           @Param("businessCode") String businessCode, @Param("businessName") String businessName, @Param("businessMobile") String businessMobile,
                           @Param("businessStatus") String businessStatus, @Param("registerBeginDate") String registerBeginDate, @Param("registerEndDate") String registerEndDate);

    List<MKCloudMemberInfoVo> selectFullMembers(@Param("memberCode") String memberCode, @Param("memberName") String memberName, @Param("memberMobile") String memberMobile,
                                            @Param("businessCode") String businessCode, @Param("businessName") String businessName, @Param("businessMobile") String businessMobile,
                                            @Param("businessStatus") String businessStatus, @Param("registerBeginDate") String registerBeginDate, @Param("registerEndDate") String registerEndDate);


    List<MKCloudMemberInfo> selectByTelList(@Param("telList") List<String> telList);

    MKCloudMemberInfo   selectByTel(@Param("tel") String tel);

    MKCloudMemberInfo selectByClientBaseInfo(@Param("mobileNum") String mobileNum,@Param("name") String name);
    

    MKCloudMemberInfo selectByFullConditionInfo(@Param("mobileNum") String mobileNum,@Param("name") String name);

    MKCloudMemberInfo queryMemberCodeByName(@Param("applyName") String applyName,@Param("applyMobile")  String applyMobile);

}