package mkcloudadmin.controller.base;

import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import org.springframework.stereotype.Service;

@Service
public class BaseApi extends SessionApi {
	
	public String getUserId(){
		MKCloudUserInfo userInfo = (MKCloudUserInfo)getSession().getAttribute(USER_KEY_IN_SESSION);
		if (userInfo!=null){
			return userInfo.getUserLoginName();
		}else {
			return "";
		}

	}
}
