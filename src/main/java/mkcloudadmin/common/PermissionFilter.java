//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import mkcloudadmin.permission.core.PermissionAbstractHandler;

@Configuration
@WebFilter(urlPatterns ="/*")
public class PermissionFilter extends PermissionAbstractHandler implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PermissionFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest requestS, ServletResponse responseS, FilterChain chain) throws IOException, ServletException {
    		logger.debug("PermissionFilter start==================================================");
        HttpServletRequest request = (HttpServletRequest)requestS;
        HttpServletResponse response = (HttpServletResponse)responseS;
        String path = request.getServletPath();
        String queryString = request.getQueryString();
        logger.debug("path:" + path + "?" + queryString);
        if (path.startsWith("/frame") || path.startsWith("/ueditor") || path.startsWith("/business")) {
        		chain.doFilter(request, response);
        }else{
        		if (this.handleRequest(request, response)) {
        			chain.doFilter(request, response);
        		}
        }

    }

    public void destroy() {
    }
}
