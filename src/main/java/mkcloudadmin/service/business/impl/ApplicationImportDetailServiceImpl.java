package mkcloudadmin.service.business.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudApplicationImportDetailMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.base.ResponseResult;
import mkcloudadmin.model.base.StaticEnum;
import mkcloudadmin.model.mkcloud.po.MKCloudApplicationImportDetail;
import mkcloudadmin.model.mkcloud.vo.MKCloudApplicationImportDetailVO;
import mkcloudadmin.service.business.ApplicationImportDetailService;
import mkcloudadmin.util.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationImportDetailServiceImpl implements ApplicationImportDetailService {

    @Autowired
    private MKCloudApplicationImportDetailMapper applicationImportDetailMapper;
    /**
     * 根据businessPeopleId查看申请记录表
     * @param
     * @return
     */
   @Override
    public  Map<String,Object>  selectByBusinessPeopleId(
           String businessPeopleId, String state, String applyCardTime, Page<MKCloudApplicationImportDetailVO> page) {
       Map<String,Object> resultMap = new HashMap<>();
      if(state.equals(StaticEnum.EFFECTIVE.getMsg())) {
            state="1";
            if(null!=applyCardTime && applyCardTime.equals("1")) {
                String time=DateUtils.dateToString(new Date(),"yyyy-MM");
                List<MKCloudApplicationImportDetailVO> details = applicationImportDetailMapper.selectByBusinessPeopleId(page, businessPeopleId, DateUtils.dateToString(new Date(),"yyyy-MM"));
                int selectCount = applicationImportDetailMapper.selectCount(businessPeopleId, DateUtils.dateToString(new Date(),"yyyy-MM"));
                resultMap.put("ImportDetailList",details);
                resultMap.put("selectCount",selectCount);
            }else {
                List<MKCloudApplicationImportDetailVO> details = applicationImportDetailMapper.selectByBusinessPeopleId(page, businessPeopleId, null);
                int selectCount = applicationImportDetailMapper.selectCount(businessPeopleId, null);
                resultMap.put("ImportDetailList",details);
                resultMap.put("selectCount",selectCount);
            }
       }else{
          resultMap.put("ImportDetailList",null);
          resultMap.put("selectCount",null);
      }
        return resultMap;
    }
}