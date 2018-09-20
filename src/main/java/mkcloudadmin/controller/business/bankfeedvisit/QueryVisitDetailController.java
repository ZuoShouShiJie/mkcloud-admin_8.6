package mkcloudadmin.controller.business.bankfeedvisit;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.bankfeedvisit.ApplyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
@Controller
@RequestMapping("/queryVisitDteail")
public class QueryVisitDetailController {

    private static final Logger logger = LoggerFactory.getLogger(QueryVisitDetailController.class);

    @Autowired
    private ApplyRecordService applyRecordService;

    /**
     * s申卡记录查询
     * @param pageNum
     * @param pageSize
     * @param applyRecordQueryDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryApplyRecord",method = RequestMethod.POST)
    public Map<String ,Object> queryApplyRecord(@RequestParam("page") Long pageNum, @RequestParam("limit") Integer pageSize,
                                                ApplyRecordQueryDTO applyRecordQueryDTO){
        Page<MKCloudApplicationImportDetailVO> page = new Page<>(pageSize,pageNum);
        Map<String,Object> importDetailVOMap = applyRecordService.queryApplyRecord(page,applyRecordQueryDTO);
        Map<String, Object> res = new HashMap<>();
        res.put("code",0);
        res.put("msg","成功");
        res.put("count",importDetailVOMap.get("count"));
        res.put("data",importDetailVOMap.get("data"));
        return res;
    }
}
