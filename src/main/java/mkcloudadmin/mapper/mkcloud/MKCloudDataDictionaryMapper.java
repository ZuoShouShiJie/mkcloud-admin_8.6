package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudDataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MKCloudDataDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudDataDictionary record);

    int insertSelective(MKCloudDataDictionary record);

    MKCloudDataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudDataDictionary record);

    int updateByPrimaryKey(MKCloudDataDictionary record);
    /**
     * 查询所有有效的数据字典配置信息
     * @return
     */
  MKCloudDataDictionary selectByState(@Param("code") String code,@Param("state") String state);


  List<MKCloudDataDictionary> selectByDataDictionary(@Param("state") String state);

    int updateByCode(MKCloudDataDictionary record);
}