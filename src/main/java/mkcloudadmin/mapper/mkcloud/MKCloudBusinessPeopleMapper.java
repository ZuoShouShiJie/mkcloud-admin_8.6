package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeople;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudBusinessPeopleMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 新增推广人员
     * @param record
     * @return
     */
    int insert(MKCloudBusinessPeople record);
    /**
     * 新增推广人员 (trim if结构)
     * @param record
     * @return
     */
    int insertSelective(MKCloudBusinessPeople record);

    MKCloudBusinessPeople selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessPeople record);

    int updateByPrimaryKey(MKCloudBusinessPeople record);


    List<MKCloudBusinessPeople> selectByBusinessPeopleCodeList(@Param("businessPeopleCodes") List<String> businessPeopleCodes);
    /**
     * (后管)
     *根据编号 查看推广人员信息
     * @return
     */
    MKCloudBusinessPeople  selectPeopleCode(@Param("businessPeopleCode") String businessPeopleCode);
    /**
     * (后管)
     *根据条件查看推广人员
     * @param
     * @return
     */
    List<MKCloudBusinessPeople>   selectPeopleqQueryByCriteria(
            @Param("page") Page<MKCloudBusinessPeople> page,
            @Param("businessPeopleType") String businessPeopleType,
            @Param("businessPeopleCode") String businessPeopleCode,
            @Param("businessPeopleName") String businessPeopleName,
            @Param("tel") String tel,
            @Param("state") String state);

    Long selectCount( @Param("businessPeopleType") String businessPeopleType,
                      @Param("businessPeopleCode") String businessPeopleCode,
                      @Param("businessPeopleName") String businessPeopleName,
                      @Param("tel") String tel,
                      @Param("state") String state);
    /**
     *  根据business_people_code删
     * @return
     */
    int deleteByPeopleId(String businessPeopleCode);

    MKCloudBusinessPeople    selectByNameTel(
            @Param("businessPeopleName") String businessPeopleName,
            @Param("tel") String tel );
    MKCloudBusinessPeople selectByTel(@Param("tel") String tel);
    int updateByStatic(@Param("state") String state,
                       @Param("businessPeopleCode") String businessPeopleCode );

    List<MKCloudBusinessPeople> selectByBusinessPeopleCode();
}