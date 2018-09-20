package mkcloudadmin.service.institution.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudBusinessInformationMapper;
import mkcloudadmin.model.mkcloud.po.MKCloudBusinessInformation;
import mkcloudadmin.service.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    private MKCloudBusinessInformationMapper businessInformationMapper;

    @Override
    public Map<String, Object> queryInstitutionList(String type, String code) {
        List<MKCloudBusinessInformation> businessInformations = businessInformationMapper.selectInstitutionsByType(type,code);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",businessInformations);
        return resultMap;
    }
}
