package mkcloudadmin.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RESTAPI方法异常统一拦截.
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午3:37:52 hewenbin
 */
@RestControllerAdvice
public class ExceptionAdvice {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	

	@ExceptionHandler
    public void exception(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
		logger.error("ajax请求异常",ex);
		
    }
}
