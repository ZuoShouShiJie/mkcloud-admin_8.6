package mkcloudadmin.controller.business.membersmanage;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.MemberQueryDto;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;
import mkcloudadmin.service.membersmanage.MembersService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members")
public class MembersController {
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private MembersService membersService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, path = "/queryMembers")
	public Map<String, Object> queryMembersJson(@RequestParam("page") Long pageNum,
			@RequestParam("limit") Integer pageSize, MemberQueryDto memberQueryDto) {
		Page<MKCloudMemberInfoVo> page = new Page<>(pageSize, pageNum);
		membersService.queryMemberInfoList(page, memberQueryDto);
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);
		res.put("msg", "");
		res.put("count", page.getTotalCount());
		res.put("data", page.getDataList());
		return res;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, path = "/exportMembers")
	public void exportMembers(HttpServletResponse response,MemberQueryDto memberQueryDto) {
		HSSFWorkbook wb = (HSSFWorkbook)membersService.createExcel(memberQueryDto);
		try{
			response.setHeader("Content-Disposition", "attachment; filename=" + new String("会员信息".getBytes(), "ISO8859-1") + ".xls");
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		}catch (IOException e) {
			logger.info("#excelDownlload--error={}",e);
		}
	}


}
