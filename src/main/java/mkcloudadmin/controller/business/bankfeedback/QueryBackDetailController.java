package mkcloudadmin.controller.business.bankfeedback;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.ApplyRecordQueryDTO;
import mkcloudadmin.model.mkcloud.vo.MKCloudBankImportDetailVO;
import mkcloudadmin.service.bankfeedback.BackRecordService;
import mkcloudadmin.service.bankfeedback.impl.BackRecordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
@Controller
@RequestMapping("/queryBackDteail")
public class QueryBackDetailController {

    private static final Logger logger = LoggerFactory.getLogger(QueryBackDetailController.class);

    @Autowired
    private BackRecordServiceImpl backRecordService;

    /**
     * 反馈记录查询
     * @param pageNum
     * @param pageSize
     * @param applyRecordQueryDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/queryBackRecord",method = RequestMethod.POST)
    public Map<String ,Object> queryBackRecord(@RequestParam("page") Long pageNum, @RequestParam("limit") Integer pageSize,
                                               ApplyRecordQueryDTO applyRecordQueryDTO){
        Page<MKCloudBankImportDetailVO> page = new Page<>(pageSize,pageNum);
        Map<String,Object> detailVOMap = backRecordService.queryBackRecord(page,applyRecordQueryDTO);
        Map<String, Object> res = new HashMap<>();
        res.put("code",0);
        res.put("msg","成功");
        res.put("count",detailVOMap.get("count"));
        res.put("data",detailVOMap.get("data"));
        return res;
    }
}
