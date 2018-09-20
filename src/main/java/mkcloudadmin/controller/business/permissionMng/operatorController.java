package mkcloudadmin.controller.business.permissionMng;

import mkcloudadmin.controller.base.SessionApi;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import mkcloudadmin.model.mkcloud.po.MKCloudUserInfo;
import mkcloudadmin.service.operatorMng.OperatorInfo;
import mkcloudadmin.service.userinfo.impl.UserInfoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @description:
 * @author: MORUIHAI
 * @create: 2018-08-04 23:01
 **/
@Controller
@RequestMapping("operator")
public class operatorController extends SessionApi {
    private static Logger logger = LoggerFactory.getLogger(operatorController.class);
    @Resource
    private OperatorInfo operatorInfo;

    /**
     * 根据用户权限查菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryUserMenu")
    @ResponseBody
    public Map<String, Object> queryUserMenu(@RequestParam Map<String, String> param) {
        logger.info("queryUserMenu请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryUserMenu(param);
        logger.info("queryUserMenu返回结果：" + result);
        return result;
    }


    /**
     * 功能描述:添加新用户
     *
     * @author: moruihai
     * @date: 2018/8/8 10:27
     * @param: [param]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addOperator")
    @ResponseBody
    public Map<String, Object> addOperator(@RequestParam Map<String, String> param) {
        logger.info("addOperator请求参数：" + param);
        Map<String, Object> result = operatorInfo.addOperator(param);
        logger.info("addOperator返回结果：" + result);
        return result;
    }

    /**
     * 查询用户信息
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryOperatorInfo")
    @ResponseBody
    public Map<String, Object> queryOperatorInfo(@RequestParam Map<String, String> param) {
        logger.info("queryOperatorInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryOperatorInfo(param);
        logger.info("queryOperatorInfo返回结果：" + result);
        return result;
    }


    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/updateOperator")
    @ResponseBody
    public Map<String, Object> updateOperator(@RequestParam Map<String, String> param) {
        logger.info("updateOperator请求参数：" + param);
        Map<String, Object> result = operatorInfo.updateOperator(param);
        logger.info("updateOperator返回结果：" + result);
        return result;

    }

    /**
     * 修改当前用户密码
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/changeUserData")
    @ResponseBody
    public Map<String, Object> changeUserData(@RequestParam Map<String, String> param) {
        logger.info("changeUserData请求参数：" + param);
        Map<String, Object> result = operatorInfo.updateOperatorPass(param);
        logger.info("changeUserData返回结果：" + result);
        return result;
    }

    /**
     * 通过userId查询角色
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/queryRoleByUserId")
    @ResponseBody
    public Map<String, Object> queryRoleByUserId(@RequestParam Map<String, String> param) {
        logger.info("queryRoleByUserId请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryRoleByUserId(param);
        logger.info("queryRoleByUserId返回结果：" + result);
        return result;

    }


    /**
     * 用户配置角色
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/userAlloRole")
    @ResponseBody
    public Map<String, Object> userAlloRole(@RequestParam Map<String, String> param) {
        logger.info("userAlloRole请求参数：" + param);
        Map<String, Object> result = operatorInfo.userAlloRole(param);
        logger.info("userAlloRole返回结果：" + result);
        return result;
    }

//-------------------------------------------角色管理----------------------------------------------------------------------


    /**
     * 查询角色
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryRoleInfo")
    @ResponseBody
    public Map<String, Object> queryRoleInfo(@RequestParam Map<String, String> param) {
        logger.info("queryRoleInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryRoleInfo(param);
        logger.info("queryRoleInfo返回结果：" + result);
        return result;
    }

    /**
     * 新增角色
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/addRoleInfo")
    @ResponseBody
    public Map<String, Object> addRoleInfo(@RequestParam Map<String, String> param) {
        logger.info("addRoleInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.addRoleInfo(param);
        logger.info("addRoleInfo返回结果：" + result);
        return result;
    }

    /**
     * 修改角色
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/updateRoleInfo")
    @ResponseBody
    public Map<String, Object> updateRoleInfo(@RequestParam Map<String, String> param) {
        logger.info("updateRoleInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.updateRoleInfo(param);
        logger.info("updateRoleInfo返回结果：" + result);
        return result;
    }

    /**
     * 通过角色Id查询菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryMenuByRoleId")
    @ResponseBody
    public Map<String, Object> queryMenuByRoleId(@RequestParam Map<String, String> param) {
        logger.info("queryMenuByRoleId请求参数：" + param);
        Map<String, Object> resp = operatorInfo.queryMenuByRoleId(param);
        logger.info("queryMenuByRoleId返回结果：" + resp);
        return resp;
    }

    /**
     * 角色分配菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/roleAlloMenu")
    @ResponseBody
    public Map<String, Object> roleAlloMenu(@RequestParam Map<String, String> param) {
        logger.info("roleAlloMenu请求参数：" + param);
        Map<String, Object> result = operatorInfo.roleAlloMenu(param);
        logger.info("roleAlloMenu返回结果：" + result);
        return result;
    }

//----------------------------------------------菜单管理------------------------------------------------------------

    /**
     * 查询菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryMenuInfo")
    @ResponseBody
    public Map<String, Object> queryMenuInfo(@RequestParam Map<String, String> param) {
        logger.info("queryMenuInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryMenuInfo(param);
        logger.info("queryMenuInfo返回结果：" + result);
        return result;
    }

    /**
     * 修改菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/updateMenuInfo")
    @ResponseBody
    public Map<String, Object> updateMenuInfo(@RequestParam Map<String, String> param) {
        logger.info("updateMenuInfo请求参数：" + param);
        Map<String, Object> result = operatorInfo.updateMenuInfo(param);
        logger.info("updateMenuInfo返回结果：" + result);
        return result;
    }

    /**
     * 查询一级菜单
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/queryFirstMenu")
    @ResponseBody
    public Map<String, Object> queryFirstMenu(@RequestParam Map<String, String> param) {
        logger.info("queryFirstMenu请求参数：" + param);
        Map<String, Object> result = operatorInfo.queryFirstMenu(param);
        logger.info("queryFirstMenu返回结果：" + result);
        return result;
    }

}
