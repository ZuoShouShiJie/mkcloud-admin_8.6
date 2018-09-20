package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeopleAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudBusinessPeopleAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBusinessPeopleAccount record);

    /**
     * 新增推广人员银行卡信息 (trim if结构)
     *
     * @param record
     * @return
     */
    int insertSelective(MKCloudBusinessPeopleAccount record);

    MKCloudBusinessPeopleAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessPeopleAccount record);
    int updateBySelective(MKCloudBusinessPeopleAccount record);
    int updateByPrimaryKey(MKCloudBusinessPeopleAccount record);

    MKCloudBusinessPeopleAccount selectByBusinessPeopleId(@Param("businessPeopleId") String businessPeopleId);

    /**
     * 根据推广人员peopleId查询推广人员银行卡信息
     *
     * @param
     * @return
     */
    List<MKCloudBusinessPeopleAccount> selectPeopleCode(@Param("businessPeopleId") String businessPeopleId);

    /**
     * 根据business_people_id删
     *
     * @return
     */
    int deleteByPeopleId(String businessPeopleId);

    int updateByStatic(@Param("state") String state,
                       @Param("businessPeopleCode") String businessPeopleCode);
}