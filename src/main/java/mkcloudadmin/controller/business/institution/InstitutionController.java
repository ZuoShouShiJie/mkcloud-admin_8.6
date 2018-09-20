package mkcloudadmin.controller.business.institution;

import mkcloudadmin.service.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/queryInstitution")
    public Map<String, Object> queryCreditCardProductData(@RequestParam(value = "type", required = false) String type,
                                                          @RequestParam(value = "code", required = false) String code) {

        Map<String, Object> res = new HashMap<>();

        Map<String, Object> map = institutionService.queryInstitutionList(type,code);

        res.put("code", 0);
        res.put("msg", "");
        res.put("data", map.get("data"));

        return res;
    }
}
