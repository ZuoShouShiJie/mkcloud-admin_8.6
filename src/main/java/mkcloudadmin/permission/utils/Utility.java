//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public final class Utility {
    private static final Logger logger = LoggerFactory.getLogger(mkcloudadmin.permission.utils.Utility.class);

    public Utility() {
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String maskUserName(String userName) {
        switch(userName.length()) {
            case 0:
                return "*";
            case 1:
                return "*";
            case 2:
                return "*" + userName.substring(1, userName.length());
            case 3:
                return userName.substring(0, 1) + "*" + userName.substring(userName.length() - 1, userName.length());
            default:
                return userName.substring(0, 1) + "**" + userName.substring(userName.length() - 1, userName.length());
        }
    }

    public static String maskUserPasswd(String passwd) {
        return passwd.substring(0, 1) + "***";
    }

    public static String getUserIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.indexOf(",") > 0) {
                logger.info("RequestIPs=" + ip);
                String[] ipArr = ip.split(",");
                ip = ipArr[0];
            }
        } else {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
