package mkcloudadmin.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import mkcloudadmin.model.mkcloud.po.MKCloudManageUser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author zsf
 * @创建时间:2016年3月24日 下午5:10:34
 */
public abstract class SessionApi extends UrlFilenameViewController {
	/** 存放用户登录后的session key */
	public static final String USER_KEY_IN_SESSION = "USER_KEY_IN_SESSION";


	protected final Logger logger = LoggerFactory.getLogger(getClass());

	 /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
        currentResponse.set(response);
	}

	/**
	 * 日期转换
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));

		DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat.setLenient(false);
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dateTimeFormat, true));
		
		binder.registerCustomEditor(short.class, new CustomNumberEditor(Short.class, false));
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
	}

	/**
	 * 获取当前请求的request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return currentRequest.get();
	}
	/**
	 * 获取当前请求的response
	 * @return
	 */
	public HttpServletResponse getResponse(){
		return currentResponse.get();
	}
	/**
	 * 获取当前请求的session
	 * @return
	 */
	public HttpSession getSession(){
		return getRequest().getSession();
	}

	/**
	 * 获取request传递过来的参数
	 * @return
	 */
	/*public Map<String, Object> getParamMap() {
		return RequestUtils.getParamMap(getRequest());
	}*/

	/**
	 * 获取当前访问的path
	 * 
	 * @return
	 */
	public String getPath() {
		String path = getViewNameForRequest(getRequest());
		return path;
	}

	/**
	 * 从request中获取参数值
	 * 
	 * @param key
	 * @return
	 */
	protected String getPara(String key) {
		return getRequest().getParameter(key);
	}

	/**
	 * 从request中获取int类型值,如果没有取到或key为null，则返回-1
	 * 
	 * @param key
	 * @return
	 */
	protected int getParaToInt(String key) {
		if (StringUtils.isNotBlank(key)) {
			String value = getRequest().getParameter(key);
			if(NumberUtils.isCreatable(value)){
				return NumberUtils.toInt(value);
			}
		}
		return -1;
	}

	/**
	 * 将信息(key/value)保存到request中
	 * 
	 * @param key
	 * @param value
	 */
	protected void setRequestAttr(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	/**
	 * 从request中获取key的值
	 * 
	 * @param key
	 * @return
	 */
	protected Object getRequestAttr(String key) {
		return getRequest().getAttribute(key);
	}

	/**
	 * 将信息(key/value)保存到session中
	 * 
	 * @param key
	 * @param value
	 */
	protected void setSessionAttr(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从session中获取key的值
	 * 
	 * @param key
	 * @return
	 */
	protected Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 将信息(kek/value)从session中删除
	 * 
	 * @param key
	 */
	protected void removeSessionAttr(String key) {
		getSession().removeAttribute(key);
	}

	/**
	 * 将对象转换成json，date类型将转换成yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param object
	 *            需要转换成json格式的对象
	 * @return
	 */
	protected String toJSONStringWithDateFormat(Object object) {
		String jsonString = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
		logger.info(jsonString);
		return jsonString;
	}

	/**
	 * 将对象转换成json
	 * 
	 * @param object
	 *            需要转换成json格式的对象
	 * @param dateformat
	 *            日期格式，如果为null,则默认为yyyy-MM-dd HH:mm:ss格式
	 * @return
	 */
	protected String toJSONStringWithDateFormat(Object object, String dateformat) {
		String jsonString = null;
		if (dateformat == null) {
			jsonString = toJSONStringWithDateFormat(object);
		} else {
			jsonString = JSON.toJSONStringWithDateFormat(object, dateformat, SerializerFeature.WriteDateUseDateFormat);
		}
		logger.info(jsonString);
		return jsonString;
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 *//*
	protected String getClientIp() {
		return RequestUtils.getClientIp(getRequest());
	}*/

	/**
	 * 根据cookie键值名取cookie的值
	 * 
	 * @param key
	 *            cookie的键值
	 * @return 返回cookie的值
	 */
	protected String getCookies(String key) {
		String CookieValue = null;
		Cookie[] cookies = getRequest().getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					CookieValue = c.getValue();
					break;
				}
			}
		}
		return CookieValue;
	}
	
	/**
	 * 取当前用户浏览器信息
	 * @return
	 *//*
	protected String getUserAgent() {
		return RequestUtils.getUserAgent(getRequest());
	}*/
	
	/**
	 * redirect到指定地址
	 * @param url
	 * @return
	 */
	protected String redirect(String url) {
		return "redirect:" + url;
	}
	
	/**
	 * 清除cookie
	 * @param key
	 * @param domain
	 */
	protected void clearCookie( String key,String domain) {
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		if (domain != null && !domain.equals("")) {
			cookie.setDomain(domain);
		}
		getResponse().addCookie(cookie);
	}

	/**
	 * 写cookie
	 * @param key
	 * @param value
	 * @param validateTime 有效期，单位分钟
	 */
	protected void writeCookies(String key,String value, int validateTime) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(60 * validateTime);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
	}

	/**
	 * 写cookie
	 * @param key cookie键值
	 * @param value cookie值
	 */
	protected void writeCookies(String key,String value) {
		Cookie cookie = new Cookie(key, value);
		// cookie.setMaxAge(60*30);//30分钟有效期
		cookie.setPath("/");
		getResponse().addCookie(cookie);
	}
	
	protected ResponseEntity<byte[]> getResponseEntity(File file, String downloadFileName){
		HttpHeaders headers = new HttpHeaders();
		String fileName=null;
		try {
			fileName = new String(downloadFileName.getBytes("UTF-8"),"iso-8859-1");
			//为了解决中文名称乱码问题
			headers.setContentDispositionFormData("attachment", fileName); 
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 清除缓存
	 */
	@SuppressWarnings("deprecation")
	protected void cleanCache(){
		HttpServletResponse response = getResponse();
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		setCacheSeconds(0);
		setAlwaysMustRevalidate(true);
	}
	
	/**
	 * 向客户端发送
	 * @param response
	 */
	protected void writeJson(Object object){
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(object, SerializerFeature.WriteEnumUsingToString));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将用户保存到session中
	 * @param user
	 */
	public void setUserToSession(MKCloudManageUser user){
		getSession().setAttribute(USER_KEY_IN_SESSION,user);
	}
	/**
	 * 从session中获取user
	 *
	 * @return
	 */
	public MKCloudManageUser getUserFromSession() {
		Object attribute = getSession().getAttribute(USER_KEY_IN_SESSION);
		return attribute == null ? null : (MKCloudManageUser) attribute;
	}

}
