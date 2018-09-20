package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBusinessPeopleAtt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudBusinessPeopleAttMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBusinessPeopleAtt record);
    /**
     * 新增推广人员附件信息 (trim if结构)
     * @param record
     * @return
     */
    int insertSelective(MKCloudBusinessPeopleAtt record);

    MKCloudBusinessPeopleAtt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessPeopleAtt record);
    /*int updateBySelective(MKCloudBusinessPeopleAtt record);*/
    int updateByPrimaryKey(MKCloudBusinessPeopleAtt record);

    /**
     *  根据business_people_id删
     * @return
     */
    int deleteByPeopleId(@Param("businessPeopleId") String businessPeopleId);
       List<MKCloudBusinessPeopleAtt> selectPeopleCode(@Param("businessPeopleId") String businessPeopleId);

       Long updateByBusinessPeopleCodeAndAttName(@Param("attachmentAddress") String attachmentAddress,@Param("businessPeopleId") String businessPeopleId,@Param("attachmentName") String attachmentName);
}