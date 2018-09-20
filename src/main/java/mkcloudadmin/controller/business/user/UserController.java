package mkcloudadmin.controller.business.user;

import mkcloudadmin.controller.base.SessionApi;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import mkcloudadmin.service.operatorMng.impl.OperatorInfoImpl;
import mkcloudadmin.service.userinfo.impl.UserInfoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-04 23:01
 **/
@Controller
@RequestMapping("user")
public class UserController extends SessionApi {
    @Resource
    private UserInfoImpl userInfo;
    @Resource
    private OperatorInfoImpl operatorInfo;
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public Map<String, Object> queryCommissionManageData(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, Object> res = new HashMap<>();
        MKCloudUserInfo user =  (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
        if(user ==null){
//           MKCloudManageUser mkCloudManageUser =  userInfo.queryByUserNameAndPwd(username,password);
            MKCloudUserInfo mkCloudUserInfo = operatorInfo.queryOperatorByNamePa(username,password,"1000");

           if(mkCloudUserInfo !=null){
               if("0".equals(mkCloudUserInfo.getStatus())){
                   res.put("code",1);
                   res.put("msg", "用户已失效！");

               }else {
                   getSession().setAttribute(USER_KEY_IN_SESSION,mkCloudUserInfo);
                   res.put("data",mkCloudUserInfo.getId());
                   res.put("code",0);
                   res.put("msg", "成功");
               }

           }else {
               res.put("code",1);
               res.put("msg", "用户或者密码不正确！");
           }

        }else {
            if(username.equals(user.getUserLoginName()) && password.equals(user.getPassword())){
                res.put("data",user.getId());
                res.put("code",0);
                res.put("msg", "成功");
            }else {
                res.put("code",1);
                res.put("msg", "用户或者密码不正确！");
            }
        }

        return res;
    }
    @RequestMapping("/logout")
    public String logout() {
        cleanCache();
        getSession().removeAttribute(USER_KEY_IN_SESSION);

        return redirect("/page?page=login");
    }
    /**
      *功能描述:添加新用户
      * @author: moruihai
      * @date: 2018/8/8 10:27
      * @param: [param]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    @RequestMapping(method = RequestMethod.POST, path = "/addUserData")
    @ResponseBody
    public Map<String, Object> addUserData(@RequestParam Map<String, String> param) {

        Map<String, Object>  res = new HashMap<>();
        //校验用户名是否存在
        String userName = param.get("username");

        MKCloudManageUser mkCloudManageUser = userInfo.queryManageUser(userName);
        if (mkCloudManageUser != null){
            res.put("code",1);
            res.put("msg", "用户名已存在！");
        }else {
            userInfo.insertManageUser(param);

            res.put("code",0);
            res.put("msg", "添加成功！");

        }
        return res;

    }

    @RequestMapping(method = RequestMethod.POST, path = "/changeUserData")
    @ResponseBody
    public Map<String, Object> changeUserData(@RequestParam Map<String, String> param) {




        Map<String, Object>  res = new HashMap<>();
        //校验用户名是否存在
        String userName = param.get("username");
        String oldPassword = param.get("oldPassword");

        MKCloudManageUser mkCloudManageUser = userInfo.queryByUserNameAndPwd(userName,oldPassword);
        if (mkCloudManageUser == null){
            res.put("code",'1');
            res.put("msg", "用户信息不存在！");
        }else {
            userInfo.updateManageUser(param);
            res.put("code",'0');
            res.put("msg", "修改成功！");

        }
        return res;

    }
    /**
     *功能描述:查询用户列表
     * @author: moruihai
     * @date: 2018/8/9 18:03
     * @param: [param]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(method = RequestMethod.POST, path = "/queryUserInfoData")
    @ResponseBody
    public Map<String, Object> queryUserInfoData(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,@RequestParam(value = "method", required = false) String method) {
        Map<String, Object> res = new HashMap<>();

        Page<MKCloudManageUser> mkCloudManageUserPage = new Page<MKCloudManageUser>(pageSize,pageNum.longValue());

        Map<String,Object> mkCloudManageUserMap = userInfo.queryUserInfoData(method,mkCloudManageUserPage);
        res.put("code",0);
        res.put("msg", "");
        res.put("count",mkCloudManageUserMap.get("count"));
        res.put("data",mkCloudManageUserMap.get("data"));
        return res;
    }
}
