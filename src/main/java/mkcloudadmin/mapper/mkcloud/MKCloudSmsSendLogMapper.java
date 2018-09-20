package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudSmsSendLog;

public interface MKCloudSmsSendLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudSmsSendLog record);

    int insertSelective(MKCloudSmsSendLog record);

    MKCloudSmsSendLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudSmsSendLog record);

    int updateByPrimaryKey(MKCloudSmsSendLog record);
}