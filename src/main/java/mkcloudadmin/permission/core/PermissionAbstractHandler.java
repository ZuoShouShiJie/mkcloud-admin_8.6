package mkcloudadmin.permission.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;

import mkcloudadmin.permission.utils.ErrorCode;
import mkcloudadmin.permission.utils.Utility;
import mkcloudadmin.permission.vo.BaseResponse;

public abstract class PermissionAbstractHandler implements PermissionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${ignore.global.status}")
    private String strStaus;
    
    @Value("${ignore.permission.url}")
    private String ignorePermissionUrl;
    
    //@Value("${ignore.business.url}")
    private String ignoreBusinessUrl = null;
    
    public PermissionAbstractHandler() {
    }

    public boolean handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    		String methodUrl = request.getRequestURI();
        this.logger.info("******权限拦截url={}", methodUrl);
        String agent = request.getHeader("User-Agent") == null ? "NOT" : request.getHeader("User-Agent").toLowerCase();
        String ip = Utility.getUserIP(request);
        PermissionAuthor.createSession(agent, ip);
        boolean ignoreStatus = Boolean.valueOf(strStaus == null ? "false" : strStaus).booleanValue();
        if (ignoreStatus) {
            this.logger.info("******权限拦截已关******");
            return true;
        } else {
            //String ignorePermissionUrl = propertyConfigurer.getProperty("ignore.permission.url");
            long ignorePermissionCount = Arrays.stream((ignorePermissionUrl == null ? "NULL" : ignorePermissionUrl).split(",")).filter((s) -> {
                return methodUrl.endsWith(s);
            }).count();
            if (ignorePermissionCount > 0L) {
                return true;
            } else {
                Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies() == null ? new Cookie[0] : request.getCookies()).filter((cookie) -> {
                    return cookie.getName().equals("sessionId");
                }).findFirst();
                Cookie coo = cookieOptional.isPresent() ? (Cookie)cookieOptional.get() : null;
                PermissionAuthor.createSession(ip, agent, coo == null ? "" : coo.getValue());
                long ignoreBusinessCount = Arrays.stream((ignoreBusinessUrl == null ? "NULL" : ignoreBusinessUrl).split(",")).filter((s) -> {
                    return methodUrl.endsWith(s);
                }).count();
                if (ignoreBusinessCount > 0L) {
                    return true;
                } else {
                    this.logger.info("******权限拦截sesssionId={}", coo == null ? "" : coo.getValue());
                    BaseResponse baseResponse = PermissionAuthor.methodPermission(methodUrl);
                    if (!baseResponse.getSucceed().booleanValue()) {
                        if (baseResponse.getErrorCode().equals(ErrorCode.USER_SESSION_NOT_EXIST.getErrorCode())) {
                            this.userSessionNotExist(request, response, baseResponse);
                            return false;
                        } else if (baseResponse.getErrorCode().equals(ErrorCode.USER_NOT_PERMISSION.getErrorCode())) {
                            this.userNotPermission(request, response, baseResponse);
                            return false;
                        } else {
//                            response.setCharacterEncoding("UTF-8");
                            response.setContentType("text/html;charset=utf-8");
                            response.getWriter().write(JSON.toJSONString(baseResponse));
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    private void userSessionNotExist(HttpServletRequest request, HttpServletResponse response, BaseResponse baseResponse) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
            response.setContentType("text/html;charset=UTF-8");
            StringBuilder str = new StringBuilder();
            str.append("<script>").append("top.location.href='" + tempContextUrl + "/admin-web/login" + "'").append("</script>");
            response.getWriter().write(str.toString());
        } else {
            response.getWriter().write(JSON.toJSONString(baseResponse));
        }

    }

    private void userNotPermission(HttpServletRequest request, HttpServletResponse response, BaseResponse baseResponse) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <title>用户无权限</title>\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n    <style>\n        *{\n            margin: 0;\n            padding: 0;\n        }\n\n    </style>\n</head>\n<body>\n<div style=\"line-height: 300px; text-align: center;\">您没有权限访问该数据，请联系管理员为您分配相应权限！</div>\n</body>\n</html>");
        } else {
            response.getWriter().write(JSON.toJSONString(baseResponse));
        }

    }
}
