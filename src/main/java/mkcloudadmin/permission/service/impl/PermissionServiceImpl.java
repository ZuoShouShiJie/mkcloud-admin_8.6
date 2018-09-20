//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import mkcloudadmin.permission.core.PermissionAuthor;
import mkcloudadmin.permission.service.PermissionService;
import mkcloudadmin.permission.utils.ErrorCode;
import mkcloudadmin.permission.vo.BaseResponse;
import mkcloudadmin.permission.vo.LoginQuitReq;
import mkcloudadmin.permission.vo.MenuPermissionReq;
import mkcloudadmin.permission.vo.RoleUserListReq;
import mkcloudadmin.permission.vo.UserLoginReq;
import mkcloudadmin.permission.vo.UserMenuReq;

@Service
public class PermissionServiceImpl implements PermissionService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    
    @Value("${permission.domain.url}")
    private String domainUrl;
    
    @Value("${user.login}")
    private String userLoginUrl;
    
    @Value("${ignore.global.status}")
    private String strStaus;
    
    @Value("${ignore.permission.url}")
    private String ignorePermissionUrl;
    
    @Value("${user.login.quit}")
    private String loginQuitUrl;
    
    @Value("${permission.menu.child}")
    private String childUrl;
    
    
    @Value("${menu.permission}")
    private String menuPermissionUrl;
    
    @Value("${role.code.user.list}")
    private String roleCodeUserList;
    
    public PermissionServiceImpl() {
    }

    public BaseResponse login(UserLoginReq userInfoReq) {
        this.logger.debug("权限用户登录开始:req={}", userInfoReq);
        BaseResponse baseResponse = null;
        try {
        		HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity(userInfoReq == null ? null : JSON.toJSONString(userInfoReq), headers);
	        baseResponse = restTemplate.postForObject(domainUrl + loginQuitUrl, entity, BaseResponse.class, new Object[0]);
	        
           // baseResponse = (BaseResponse)restHttpClient.postForObjectJson(domainUrl + userLoginUrl, userInfoReq, BaseResponse.class);
        } catch (Exception var8) {
            this.logger.error("权限用户登录异常:{}", var8);
            baseResponse = BaseResponse.newInstance();
            baseResponse.setErrorCode(ErrorCode.NETWORK_ERROR.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.NETWORK_ERROR.getErrorMessage());
            return baseResponse;
        }

        this.logger.debug("权限用户登录结束:res={}", baseResponse);
        return baseResponse;
    }

    public BaseResponse loginQuit(LoginQuitReq req) {
        this.logger.debug("用户登出开始:req={}", req);
        BaseResponse baseResponse = null;

        try {
        		HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity(req == null ? null : JSON.toJSONString(req), headers);
	        baseResponse = restTemplate.postForObject(domainUrl + loginQuitUrl, entity, BaseResponse.class, new Object[0]);
        	
            //baseResponse = (BaseResponse)restHttpClient.postForObjectJson(domainUrl + loginQuitUrl, req, BaseResponse.class);
        } catch (Exception var8) {
            this.logger.error("用户登出异常:{}", var8);
            baseResponse = BaseResponse.newInstance();
            baseResponse.setErrorCode(ErrorCode.NETWORK_ERROR.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.NETWORK_ERROR.getErrorMessage());
            return baseResponse;
        }

        PermissionAuthor.getSesssion().setPermissionRes(baseResponse);
        this.logger.debug("用户登出结束:res={}", baseResponse);
        return baseResponse;
    }

    public BaseResponse menuChird(UserMenuReq req) {
        this.logger.debug("权限子菜单查询开始:req={}", req);
        BaseResponse baseResponse = null;
        try {
        		HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity(req == null ? null : JSON.toJSONString(req), headers);
	        baseResponse = restTemplate.postForObject(domainUrl + childUrl, entity, BaseResponse.class, new Object[0]);
	        
            //baseResponse = (BaseResponse)restHttpClient.postForObjectJson(domainUrl + childUrl, req, BaseResponse.class);
        } catch (Exception var8) {
            this.logger.error("权限子菜单查询异常:{}", var8);
            baseResponse = BaseResponse.newInstance();
            baseResponse.setErrorCode(ErrorCode.NETWORK_ERROR.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.NETWORK_ERROR.getErrorMessage());
            return baseResponse;
        }

        PermissionAuthor.getSesssion().setPermissionRes(baseResponse);
        this.logger.debug("权限子菜单查询结束:res={}", baseResponse);
        return baseResponse;
    }

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    
    public BaseResponse methodPermission(MenuPermissionReq req) {
        this.logger.debug("权限URL校验开始:req={}", req);
        BaseResponse baseResponse = null;
        try {
        		HttpHeaders headers = new HttpHeaders();
    	        headers.setContentType(MediaType.APPLICATION_JSON);
    	        HttpEntity<String> entity = new HttpEntity(req == null ? null : JSON.toJSONString(req), headers);
    	        baseResponse = restTemplate.postForObject(domainUrl + menuPermissionUrl, entity, BaseResponse.class, new Object[0]);
            //baseResponse = (BaseResponse)restHttpClient.postForObjectJson(domainUrl + menuPermissionUrl, req, BaseResponse.class);
        } catch (Exception var8) {
            this.logger.error("权限URL校验异常:{}", var8);
            baseResponse = BaseResponse.newInstance();
            baseResponse.setErrorCode(ErrorCode.NETWORK_ERROR.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.NETWORK_ERROR.getErrorMessage());
            return baseResponse;
        }

        this.logger.debug("权限URL校验结束:res={}", baseResponse);
        return baseResponse;
    }

    public BaseResponse getRoleUserList(RoleUserListReq req) {
        this.logger.debug("权限获取角色用户信息列表:req={}", req);
        BaseResponse baseResponse = null;

        try {
        		HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity(req == null ? null : JSON.toJSONString(req), headers);
	        baseResponse = restTemplate.postForObject(domainUrl + roleCodeUserList, entity, BaseResponse.class, new Object[0]);
        } catch (Exception var8) {
            this.logger.error("权限获取角色用户信息列表异常:{}", var8);
            baseResponse = BaseResponse.newInstance();
            baseResponse.setErrorCode(ErrorCode.NETWORK_ERROR.getErrorCode());
            baseResponse.setErrorMessage(ErrorCode.NETWORK_ERROR.getErrorMessage());
            return baseResponse;
        }

        this.logger.debug("权限获取角色用户信息列表结束:res={}", baseResponse);
        return baseResponse;
    }
}
