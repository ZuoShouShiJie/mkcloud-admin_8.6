package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.mkcloud.po.MKCloudBankImportDetailTem;

public interface MKCloudBankImportDetailTemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MKCloudBankImportDetailTem record);

    int insertSelective(MKCloudBankImportDetailTem record);

    MKCloudBankImportDetailTem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MKCloudBankImportDetailTem record);

    int updateByPrimaryKey(MKCloudBankImportDetailTem record);
}