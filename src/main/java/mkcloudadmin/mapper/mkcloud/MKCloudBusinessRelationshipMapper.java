package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBusinessRelationship;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberRelationVo;
import org.apache.ibatis.annotations.Param;

public interface MKCloudBusinessRelationshipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBusinessRelationship record);

    int insertSelective(MKCloudBusinessRelationship record);

    MKCloudBusinessRelationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBusinessRelationship record);

    int updateByPrimaryKey(MKCloudBusinessRelationship record);

    MKCloudMemberRelationVo selectByBussinessPersonCode(@Param("bussinessPersonCode") String bussinessPersonCode);
}