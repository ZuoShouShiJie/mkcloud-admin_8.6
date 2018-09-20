package mkcloudadmin.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息服务接口.
 * @author hewenbin
 * @version v1.0 2018年7月12日 下午5:17:57 hewenbin
 */
public interface UserInfoService {

	
	/**
	 * 通过指定参数来匹配用户.
	 * @param realName 用户姓名、银行卡账户名，例如：张***.
	 * @param mobileNum 手机号，例如：135****3434
	 * @param productId 产品ID
	 * @param actionDate 操作时间
	 * @return 匹配到的UserId
	 * @author hewenbin
	 * @version UserInfoService.java, v1.0 2018年7月12日 下午5:31:13 hewenbin
	 */
	List<Long> matchUser(String realName, String mobileNum, Long productId, Date actionDate, int beforeDays);

	/**
	 * 查询用户个人及其邀请人信息（父级、父父级）.
	 * @param userId
	 * @return
	 * <pre>
	 * 	示例：
	 *   {
	 *       "realName": "关羽",
	 *       "mobileNum": "13819191918",
	 *       "parent": {
	 *           "realName": "张飞飞",
	 *           "mobileNum": "18819191918",
	 *           "userId": 9
	 *       },
	 *       "grand": {
	 *           "realName": "张飞飞",
	 *           "mobileNum": "15512340003",
	 *           "userId": 15
	 *       }
	 *   }
	 * </pre>
	 * @author hewenbin
	 * @version UserInfoService.java, v1.0 2018年7月13日 下午2:00:26 hewenbin
	 */
	Map<String, Object> queryInvateInfo(Long userId);
}
