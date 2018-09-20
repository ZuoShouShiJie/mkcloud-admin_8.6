package mkcloudadmin.service.institution;

import java.util.Map;

public interface InstitutionService {
    Map<String,Object> queryInstitutionList(String type, String code);
}
