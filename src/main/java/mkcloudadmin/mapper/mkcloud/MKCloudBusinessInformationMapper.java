package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBusinessInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudBusinessInformationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBusinessInformation record);

    int insertSelective(MKCloudBusinessInformation record);

    MKCloudBusinessInformation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessInformation record);

    int updateByPrimaryKey(MKCloudBusinessInformation record);

    List<MKCloudBusinessInformation> selectInstitutionsByType(@Param("type") String type, @Param("code")String code);

    List<MKCloudBusinessInformation> selectInstitutionsByCode(List<String> codeList);

}