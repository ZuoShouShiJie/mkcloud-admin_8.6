package mkcloudadmin.service.membersmanage;

import java.util.List;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.MemberQueryDto;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;
import org.apache.poi.ss.usermodel.Workbook;

public interface MembersService {

    
	/**
	 * 查询用户列表.
	 * @param page
	 * @param memberQueryDto
	 * @return
	 * @author hewenbin
	 * @version MembersService.java, v1.0 2018年7月21日 下午6:43:33 hewenbin
	 */
    List<MKCloudMemberInfoVo> queryMemberInfoList(Page<MKCloudMemberInfoVo> page, MemberQueryDto memberQueryDto);

	Workbook createExcel(MemberQueryDto memberQueryDto);
}
