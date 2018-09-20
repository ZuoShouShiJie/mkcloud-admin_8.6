package mkcloudadmin.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mkcloudadmin.mapper.mkcloud.FinanceOperationRecordMapper;
import mkcloudadmin.mapper.mkcloud.FinanceUserBankCardInfoMapper;
import mkcloudadmin.mapper.mkcloud.FinanceUserInfoMapper;
import mkcloudadmin.mapper.mkcloud.FinanceUserInviteInfoMapper;
import mkcloudadmin.model.mkcloud.po.FinanceOperationRecord;
import mkcloudadmin.model.mkcloud.po.FinanceUserBankCardInfo;
import mkcloudadmin.model.mkcloud.po.FinanceUserInfo;
import mkcloudadmin.model.mkcloud.po.FinanceUserInviteInfo;
import mkcloudadmin.service.user.UserInfoService;

/**
 * @author hewenbin
 * @version v1.0 2018年7月12日 下午5:36:16 hewenbin
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private FinanceUserInfoMapper userInfoMapper;
	@Autowired
	private FinanceUserBankCardInfoMapper bankCardMapper;
	@Autowired
	private FinanceOperationRecordMapper operationRecordMapper;
	@Autowired
	private FinanceUserInviteInfoMapper inviteInfoMapper;
	
	@Override
	public List<Long> matchUser(String realName, String mobileNum, Long productId, Date actionDate, int beforeDays) {
		List<Long> resUserIds = new ArrayList<>();
		if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(mobileNum) || productId == null || productId < 1 || actionDate == null) {
			return resUserIds;
		}

		/**
		 * 优先级（如果用户在1中有，但是在2中没有，则跳过，不再匹配）
		 * 操作记录：1
		 * 手机号：2
		 * 姓名：3
		 */
		// 匹配操作记录
		//int beforeDays = 7; 匹配操作时间暂时固定前七天 ，需要做活 会出现1-2 > 1 或者 1-2-3 >1 的情况
		Calendar c = Calendar.getInstance();
        c.setTime(actionDate);
        c.add(Calendar.DATE, -beforeDays);
        Date beginTime = c.getTime();
        
		List<FinanceOperationRecord> records = operationRecordMapper.selectCountByProductId(null, productId, beginTime, actionDate);
		Set<Long> set1 = new HashSet<>();
		records.forEach(info -> set1.add(info.getUserId()));
		
		// 匹配手机号（xxx____xxxx）
		mobileNum = mobileNum.substring(0, 3) + "____" + mobileNum.substring(7, 11);;
		List<FinanceUserInfo> mobiles = userInfoMapper.selectLikeMobile(mobileNum);
		Set<Long>  set2  = new HashSet<>();
		mobiles.forEach(info -> set2.add(info.getId()));
				
		// 匹配姓名（x%）
		List<FinanceUserBankCardInfo> users = bankCardMapper.selectDefaultCardLikeFirstName(realName.substring(0, 1));
		Set<Long> set3 = new HashSet<>();
		users.forEach(info -> set3.add(info.getUserId()));

		set1.retainAll(set2);
		if (set1.size() > 1) {
			set1.retainAll(set3);
		}
		resUserIds.addAll(set1);
		
		// 不可能走到这里
		return resUserIds;

	}
	
	@Override
	public Map<String, Object> queryInvateInfo(Long userId) {
		/**
		 * 示例：
		 * {
		 *     "realName": "关羽",
		 *     "mobileNum": "13819191918",
		 *     "parent": {
		 *         "realName": "张飞飞",
		 *         "mobileNum": "18819191918",
		 *         "userId": 9
		 *     },
		 *     "grand": {
		 *         "realName": "张飞飞",
		 *         "mobileNum": "15512340003",
		 *         "userId": 15
		 *     }
		 * }
		 */
		Map<String, Object> resMap = new HashMap<>();
		List<Long> userIds = Arrays.asList(userId);
		FinanceUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		List<FinanceUserBankCardInfo> cardInfos = bankCardMapper.selectDefaultBankCard(userIds);
		resMap.put("realName", cardInfos.isEmpty()? "***" : cardInfos.get(0).getAccountName());
		resMap.put("mobileNum", userInfo.getMobileNum());
		
		
		// 父级 parent
		List<FinanceUserInviteInfo> invteInfos = inviteInfoMapper.selectInviteInfoByUserId(userIds);
		if (invteInfos.isEmpty()) {
			return resMap;
		}
		Long parentId = invteInfos.get(0).getParentUserId();
		userIds = Arrays.asList(parentId);
		userInfo = userInfoMapper.selectByPrimaryKey(parentId);
		cardInfos = bankCardMapper.selectDefaultBankCard(userIds);
		Map<String, Object> parentMap = new HashMap<>();
		resMap.put("parent", parentMap);
		parentMap.put("userId", userInfo.getId());
		parentMap.put("realName", cardInfos.isEmpty()? "***" : cardInfos.get(0).getAccountName());
		parentMap.put("mobileNum", userInfo.getMobileNum());
		
		
		// 父父级 grand
		List<FinanceUserInviteInfo> parentInvteInfos = inviteInfoMapper.selectInviteInfoByUserId(userIds);
		if (parentInvteInfos.isEmpty()) {
			return resMap;
		}
		Long grandId = parentInvteInfos.get(0).getParentUserId();
		userIds = Arrays.asList(grandId);
		userInfo = userInfoMapper.selectByPrimaryKey(grandId);
		cardInfos = bankCardMapper.selectDefaultBankCard(userIds);
		Map<String, Object> grandMap = new HashMap<>();
		resMap.put("grand", grandMap);
		grandMap.put("userId", userInfo.getId());
		grandMap.put("realName", cardInfos.isEmpty()? "***" : cardInfos.get(0).getAccountName());
		grandMap.put("mobileNum", userInfo.getMobileNum());
		
		return resMap;
	}
	
}
