//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.core;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import mkcloudadmin.MKCloudAdminApplication;
import mkcloudadmin.permission.service.PermissionService;
import mkcloudadmin.permission.utils.ErrorCode;
import mkcloudadmin.permission.utils.Utility;
import mkcloudadmin.permission.vo.BaseResponse;
import mkcloudadmin.permission.vo.LoginQuitReq;
import mkcloudadmin.permission.vo.MenuPermissionReq;
import mkcloudadmin.permission.vo.RoleUserListReq;
import mkcloudadmin.permission.vo.UserLoginReq;
import mkcloudadmin.permission.vo.UserLoginRes;
import mkcloudadmin.permission.vo.UserMenuReq;

public class PermissionAuthor {
    private static Logger logger = LoggerFactory.getLogger(mkcloudadmin.permission.core.PermissionAuthor.class);
    private static final ThreadLocal<PermissionSession> permissionSessionLocal = new ThreadLocal();

//    @Autowired
//    PermissionService permissionService;
    
    public PermissionAuthor() {
    }

    public static PermissionSession createSession(String agent, String ip) {
        return createSession(agent, ip, (String)null);
    }

    public static PermissionSession createSession(String agent, String ip, String sessionId) {
        PermissionSession session = new PermissionSession();
        session.setIp(ip);
        session.setAgent(agent);
        session.setSessionId(sessionId);
        permissionSessionLocal.set(session);
        return (PermissionSession)permissionSessionLocal.get();
    }

    public static PermissionSession getSesssion() {
        PermissionSession session = (PermissionSession)permissionSessionLocal.get();
        if (session == null) {
            logger.error("获取权限session值为NULL");
        }

        return session;
    }

    public static UserLoginRes getUserLoginRes() {
        PermissionSession session = (PermissionSession)permissionSessionLocal.get();
        if (session == null) {
            logger.error("获取权限session值为NULL");
            return null;
        } else if (!session.getPermissionRes().getSucceed().booleanValue()) {
            logger.error("获取权限数据值错误");
            return null;
        } else {
            String loginRes = "";
            UserLoginRes res = null;

            try {
                loginRes = JSON.toJSONString(session.getPermissionRes().getData());
                res = (UserLoginRes) JSON.parseObject(loginRes, UserLoginRes.class);
                return res;
            } catch (Exception var4) {
                logger.error("获取权限loginRes值={}", loginRes);
                logger.error("获取权限session值异常={}", var4);
                return res;
            }
        }
    }

    public static BaseResponse getRoleUserList(String roleCode) {
        PermissionService permissionService = MKCloudAdminApplication.appContect.getBean(PermissionService.class);
        RoleUserListReq req = new RoleUserListReq();
        req.setRoleCode(roleCode);
        req.setUserUid(getUserLoginRes().getUserInfoRes().getUserUid());
        BaseResponse baseResponse = permissionService.getRoleUserList(req);
        return baseResponse;
    }

    public static BaseResponse authLogin(String loginName, String passwd, HttpServletRequest request, HttpServletResponse response) {
        return authLogin(loginName, passwd, request, response, (LoginHandler)null);
    }

    public static BaseResponse authLogin(String loginName, String passwd, HttpServletRequest request, HttpServletResponse response, LoginHandler handler) {
        PermissionService permissionService = MKCloudAdminApplication.appContect.getBean(PermissionService.class);
        UserLoginReq userInfoReq = new UserLoginReq();
        String agent = request.getHeader("User-Agent").toLowerCase();
        String ip = Utility.getUserIP(request);
        userInfoReq.setLoginName(loginName);
        userInfoReq.setPasswd(passwd);
        userInfoReq.setAgent(agent);
        userInfoReq.setIp(ip);
        BaseResponse baseResponse = permissionService.login(userInfoReq);
        if (handler != null) {
            BaseResponse base = handler.handle(baseResponse);
            if (!base.getSucceed().booleanValue()) {
                return base;
            }
        }

        if (baseResponse.getSucceed().booleanValue()) {
            UserLoginRes userLoginRes = (UserLoginRes) JSON.parseObject(JSON.toJSONString(baseResponse.getData()), UserLoginRes.class);
            String sessionId = userLoginRes.getSessionId();
            getSesssion().setSessionId(sessionId);
            Cookie cookie = new Cookie("sessionId", userLoginRes.getSessionId());
            cookie.setMaxAge(2147483647);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        getSesssion().setPermissionRes(baseResponse);
        return baseResponse;
    }

    public static BaseResponse loginQuit(HttpServletRequest request) {
        BaseResponse baseResponse = BaseResponse.newInstance();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            baseResponse.setErrorCode(ErrorCode.USER_SESSION_NOT_EXIST.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.USER_SESSION_NOT_EXIST.getErrorMessage());
            return baseResponse;
        } else {
            Cookie coo = (Cookie)Arrays.stream(request.getCookies()).filter((cookie) -> {
                return cookie.getName().equals("sessionId");
            }).findFirst().get();
            if (coo == null) {
                baseResponse.setErrorCode(ErrorCode.USER_SESSION_NOT_EXIST.getErrorCode());
                baseResponse.setErrorMessage(ErrorCode.USER_SESSION_NOT_EXIST.getErrorMessage());
                return baseResponse;
            } else {
            		PermissionService permissionService = MKCloudAdminApplication.appContect.getBean(PermissionService.class);
                LoginQuitReq req = new LoginQuitReq();
                req.setSesssionId(coo.getValue());
                return permissionService.loginQuit(req);
            }
        }
    }

    public static BaseResponse methodPermission(String methodUrl) {
        PermissionService permissionService = MKCloudAdminApplication.appContect.getBean(PermissionService.class);
        MenuPermissionReq req = new MenuPermissionReq();
        req.setSesssionId(getSesssion().getSessionId());
        req.setHerf(methodUrl);
        req.setIp(getSesssion().getIp());
        req.setAgent(getSesssion().getAgent());
        BaseResponse baseResponse = permissionService.methodPermission(req);
        ((PermissionSession)permissionSessionLocal.get()).setPermissionRes(baseResponse);
        return baseResponse;
    }

    public static BaseResponse menuChild(String menuUid) {
    		PermissionService permissionService = MKCloudAdminApplication.appContect.getBean(PermissionService.class);
        UserMenuReq req = new UserMenuReq();
        req.setMenuUid(menuUid);
        req.setSessionId(getSesssion().getSessionId());
        BaseResponse baseResponse = permissionService.menuChird(req);
        ((PermissionSession)permissionSessionLocal.get()).setPermissionRes(baseResponse);
        return baseResponse;
    }
}
