package mkcloudadmin.service.userinfo.impl;

import mkcloudadmin.mapper.mkcloud.MKCloudManageUserMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.vo.MKCloudUserListVO;
import mkcloudadmin.service.userinfo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description: 用户相关操作
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-05 00:07
 **/
@Service
public class UserInfoImpl implements UserInfo {
    @Autowired
    private MKCloudManageUserMapper mkCloudManageUserMapper;

    @Override
    public MKCloudManageUser queryByUserNameAndPwd(String username, String password) {

        MKCloudManageUser mkCloudManageUser = mkCloudManageUserMapper.queryByUserNameAndPwd(username, password);
        return mkCloudManageUser;
    }

    @Override
    public MKCloudManageUser queryManageUser(String userName) {




        MKCloudManageUser mkCloudManageUser = mkCloudManageUserMapper.queryByUserName(userName);
        return mkCloudManageUser;
    }

    @Override
    @Transactional
    public void insertManageUser(Map<String, String> param) {
        String userId = param.get("username");
        String password = param.get("password");

        MKCloudManageUser mkCloudManageUser = new MKCloudManageUser();
        mkCloudManageUser.setStatus("1");
        mkCloudManageUser.setUserId(userId);
        mkCloudManageUser.setPassword(password);
        mkCloudManageUserMapper.insertSelective(mkCloudManageUser);

    }

    @Override
    @Transactional
    public void updateManageUser(Map<String, String> param) {
        String userId = param.get("username");
        String password =param.get("newPassword");
        MKCloudManageUser mkCloudManageUser = new MKCloudManageUser();
        // mkCloudManageUser.setStatus("1");
        mkCloudManageUser.setUserId(userId);
        mkCloudManageUser.setPassword(password);
        mkCloudManageUserMapper.updateByUserId(mkCloudManageUser);

    }

    @Override
    public Map<String, Object> queryUserInfoData(String method, Page<MKCloudManageUser> mkCloudManageUserPage) {
        Long total = mkCloudManageUserMapper.selectUserInfoCount(method);
        List<MKCloudUserListVO> mkCloudCommissionSearchDetailVOList = new ArrayList<>();
        if (total > 0) {
            //根据类型查主表数据
            List<MKCloudManageUser> mkCloudManageUserList = mkCloudManageUserMapper.selectUserInfoList(mkCloudManageUserPage, method);

            if (mkCloudManageUserList != null && mkCloudManageUserList.size() > 0) {
                MKCloudUserListVO mkCloudUserListVO = null;
                for(MKCloudManageUser mkm :mkCloudManageUserList){
                    mkCloudUserListVO = new MKCloudUserListVO();
                    mkCloudUserListVO.setId(mkm.getId());
                    mkCloudUserListVO.setUserId(mkm.getUserId());
                    mkCloudUserListVO.setStatus(mkm.getStatus());
                    mkCloudUserListVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkm.getCreateTime()));
                    mkCloudUserListVO.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkm.getUpdateTime()));

                    mkCloudCommissionSearchDetailVOList.add(mkCloudUserListVO);
                }



            }

        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",total);
        resultMap.put("data",mkCloudCommissionSearchDetailVOList);

        return resultMap;
    }
}
