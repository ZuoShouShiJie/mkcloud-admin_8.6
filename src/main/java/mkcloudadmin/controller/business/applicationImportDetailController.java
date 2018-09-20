package mkcloudadmin.controller.business;

import mkcloudadmin.job.RecordingImportTimingTasks;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.business.ApplicationImportDetailService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/applicationImportDetail")
public class applicationImportDetailController {
    private static Logger logger = LoggerFactory.getLogger(BusinessPeopleController.class);
    @Autowired
    private ApplicationImportDetailService applicationImportDetailService;
    @Autowired
    private RecordingImportTimingTasks recordingImportTimingTasks;

    /**
     *
     * 推广人办件量查询
     * @param
     * @return
     */
   @RequestMapping(method = RequestMethod.POST, path = "/selectByApplicationImport")
    @ResponseBody
    public Object selectWchatTel(HttpServletRequest request,
                                              @RequestParam("page") Integer pageNum,
                                 @RequestParam("limit") Integer pageSize,
                                 @Param("businessPeopleId") String businessPeopleId,
                                 @Param("state") String state,
                                 @Param("applyCardTime") String applyCardTime){
       Map<String, Object> res = new HashMap<>();
       Page<MKCloudApplicationImportDetailVO> cardInfoPage = new Page<MKCloudApplicationImportDetailVO>(pageSize,pageNum.longValue());
       Map<String,Object> map = applicationImportDetailService.selectByBusinessPeopleId(businessPeopleId,state,applyCardTime,cardInfoPage);
       res.put("code",0);
       res.put("msg", "");
       res.put("count",map.get("selectCount"));
       res.put("data",map.get("ImportDetailList"));
       return res;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/recording")
    @ResponseBody
    public void time(HttpServletRequest request){
        recordingImportTimingTasks.recording();
    }

}
