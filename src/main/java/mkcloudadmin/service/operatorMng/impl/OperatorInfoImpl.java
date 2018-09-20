package mkcloudadmin.service.operatorMng.impl;

import mkcloudadmin.controller.base.SessionApi;
import mkcloudadmin.enums.MenuTypeEnum;
import mkcloudadmin.enums.Status;
import mkcloudadmin.mapper.mkcloud.*;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.*;
import mkcloudadmin.model.mkcloud.vo.*;
import mkcloudadmin.service.operatorMng.OperatorInfo;
import mkcloudadmin.service.userinfo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: mkcloud-admin_8.6
 * @description: 用户相关操作
 * @author: MORUIHAI
 * @create: 2018-08-05 00:07
 **/
@Service
public class OperatorInfoImpl extends SessionApi implements OperatorInfo {
    private static Logger logger = LoggerFactory.getLogger(OperatorInfoImpl.class);
    @Autowired
    private MKCloudUserInfoMapper mkCloudUserInfoMapper;
    @Autowired
    private MKCloudRoleInfoMapper mkCloudRoleInfoMapper;
    @Autowired
    private MKCloudMenuInfoMapper mkCloudMenuInfoMapper;
    @Autowired
    private MKCloudUserRoleMapper mkCloudUserRoleMapper;
    @Autowired
    private MKCloudRoleMenuMapper mkCloudRoleMenuMapper;


    /**
     * 通过用户id查询菜单
     *
     * @return
     */
    @Override
    public Map<String, Object> queryUserMenu(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            MKCloudMenuInfo record = new MKCloudMenuInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setOrgCode(param.get("orgCode"));
            //查询该用户拥有的所有菜单
            List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryUserMenu(record);

            MKCloudMenuInfo record1 = new MKCloudMenuInfo();
            record1.setStatus("1");
            record1.setOrgCode(param.get("orgCode"));
            //查询所有有效菜单
            List<MKCloudMenuInfo> allMenuInfo = mkCloudMenuInfoMapper.queryMenuInfo(record1);
            MKCloudQueryUserMenuVO menuList = new MKCloudQueryUserMenuVO();
            menuList.setMkCloudMenuInfos(mkCloudMenuInfos);
            menuList.setAllMenuInfo(allMenuInfo);
            resultMap.put("data", menuList);
            resultMap.put("code", 0);
            resultMap.put("msg", "");
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryUserMenu:", e);
        }
        return resultMap;
    }

    /**
     * 登录查询用户是否存在
     *
     * @param username
     * @param password
     * @param orgCode
     * @return
     */
    @Override
    public MKCloudUserInfo queryOperatorByNamePa(String username, String password, String orgCode) {
        MKCloudUserInfo result = new MKCloudUserInfo();
        try {
            MKCloudUserInfo info = new MKCloudUserInfo();
            info.setUserLoginName(username);
            info.setOrgCode(orgCode);
            info.setPassword(password);
            result = mkCloudUserInfoMapper.queryOperatorByNamePa(info);
        } catch (Exception e) {
            logger.error("queryOperatorByNamePa:", e);
        }
        return result;
    }

    /**
     * 添加新用户
     *
     * @param param
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> addOperator(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            param.put("orgCode", "1000");
            MKCloudUserInfo info = new MKCloudUserInfo();
            info.setUserLoginName(param.get("userLoginName"));
            info.setOrgCode(param.get("orgCode"));
            //校验新增用户是否存在
            MKCloudUserInfo mkCloudManageUser = mkCloudUserInfoMapper.queryOperatorByNamePa(info);
            if (mkCloudManageUser != null) {
                resultMap.put("code", 1);
                resultMap.put("msg", "用户名已存在！");
            } else {
                MKCloudUserInfo userInfo = new MKCloudUserInfo();
                userInfo.setUserLoginName(param.get("userLoginName"));
                userInfo.setPassword(param.get("password"));
                userInfo.setOrgCode(param.get("orgCode"));
                userInfo.setUserName(param.get("userName"));
                userInfo.setMobile(param.get("mobile"));
                userInfo.setEmail(param.get("email"));
                userInfo.setStatus(param.get("statusCode"));
                userInfo.setCreator(user.getUserLoginName());
                mkCloudUserInfoMapper.insertSelective(userInfo);
                resultMap.put("code", 0);
                resultMap.put("msg", "添加成功！");
            }
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("addOperator:", e);

        }
        return resultMap;
    }

    /**
     * 查询用户信息
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryOperatorInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String pageSize = param.get("limit");
            String pageNum = param.get("page");
            String userId = param.get("userId");
            Long id = null;
            if (StringUtils.isNotBlank(userId)) {
                id = Long.valueOf(userId);
            }
            String userLoginName = param.get("userLoginName");
            String status = param.get("statusCode");
            String orgCode = param.get("orgCode");
            Page<MKCloudUserInfo> mkCloudManageUserPage = new Page<MKCloudUserInfo>(Integer.valueOf(pageSize), Long.valueOf(pageNum));
            Long total = mkCloudUserInfoMapper.queryOperatorInfoCount(id, userLoginName, status, orgCode);
            List<MKCloudOperatorListVO> mkCloudCommissionSearchDetailVOList = new ArrayList<>();
            if (total > 0) {
                //查询用户信息
                List<MKCloudUserInfo> mkCloudUserInfoList = mkCloudUserInfoMapper.queryOperatorInfo(id, userLoginName, status, orgCode, mkCloudManageUserPage);

                if (mkCloudUserInfoList != null && mkCloudUserInfoList.size() > 0) {
                    for (MKCloudUserInfo mkm : mkCloudUserInfoList) {
                        MKCloudOperatorListVO mkCloudOperatorListVO = new MKCloudOperatorListVO();
                        mkCloudOperatorListVO.setId(mkm.getId());
                        mkCloudOperatorListVO.setUserId(mkm.getId().toString());
                        mkCloudOperatorListVO.setStatusCode(mkm.getStatus());
                        mkCloudOperatorListVO.setStatusName(Status.parser(mkm.getStatus()).getMsg());
                        mkCloudOperatorListVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkm.getCreateTime()));
                        mkCloudOperatorListVO.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkm.getUpdateTime()));
                        mkCloudOperatorListVO.setCreator(mkm.getCreator());
                        mkCloudOperatorListVO.setUserLoginName(mkm.getUserLoginName());
                        mkCloudOperatorListVO.setUserName(mkm.getUserName());
                        mkCloudOperatorListVO.setEmail(mkm.getEmail());
                        mkCloudOperatorListVO.setOrgCode(mkm.getOrgCode());
                        mkCloudCommissionSearchDetailVOList.add(mkCloudOperatorListVO);
                    }
                }
            }
            resultMap.put("code", 0);
            resultMap.put("msg", "");
            resultMap.put("count", total);
            resultMap.put("data", mkCloudCommissionSearchDetailVOList);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryOperatorInfo:", e);
        }
        return resultMap;
    }

    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> updateOperator(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            MKCloudUserInfo record = new MKCloudUserInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setUserName(param.get("userName"));
            record.setPassword(param.get("password"));
            record.setEmail(param.get("email"));
            record.setMobile(param.get("mobile"));
            record.setStatus(param.get("statusCode"));
            record.setUpdator(user.getUserLoginName());
            mkCloudUserInfoMapper.updateByPrimaryKeySelective(record);

            resultMap.put("code", 0);
            resultMap.put("msg", "更新成功");
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("updateOperator:", e);
        }
        return resultMap;
    }

    /**
     * 修改当前用户密码
     *
     * @param param
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> updateOperatorPass(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            if (user != null) {
                MKCloudUserInfo info = new MKCloudUserInfo();
                info.setUserLoginName(user.getUserLoginName());
                info.setOrgCode(param.get("orgCode"));
                info.setPassword(param.get("oldPassword"));
                MKCloudUserInfo userInfo = mkCloudUserInfoMapper.queryOperatorByNamePa(info);
                if (userInfo == null) {
                    resultMap.put("code", '1');
                    resultMap.put("msg", "旧密码不正确！");
                } else {
                    MKCloudUserInfo record = new MKCloudUserInfo();
                    record.setId(user.getId());
                    record.setPassword(param.get("newPassword"));
                    record.setUpdator(user.getUserLoginName());
                    mkCloudUserInfoMapper.updateByPrimaryKeySelective(record);
                    resultMap.put("code", '0');
                    resultMap.put("msg", "修改成功！");
                }
            } else {
                resultMap.put("code", '2');
                resultMap.put("msg", "请重新登录！");
            }
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("updateOperator:", e);
        }
        return resultMap;


    }

    /**
     * 通过userId查询角色
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryRoleByUserId(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            MKCloudRoleInfo record = new MKCloudRoleInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setOrgCode(param.get("orgCode"));
            //查询该用户已选角色
            List<MKCloudRoleInfo> mkCloudRoleInfoList = mkCloudRoleInfoMapper.queryRoleByUserId(record);
            MKCloudRoleInfo record1 = new MKCloudRoleInfo();
            record1.setStatus("1");
            record1.setOrgCode(param.get("orgCode"));
            //查询所有有效角色
            List<MKCloudRoleInfo> allRoleInfo = mkCloudRoleInfoMapper.queryRoleInfo(record1);
            MKCloudQueryRoleVO roleVO = new MKCloudQueryRoleVO();
            roleVO.setMkCloudRoleInfoList(mkCloudRoleInfoList);
            roleVO.setAllRoleInfo(allRoleInfo);
            resultMap.put("code", 0);
            resultMap.put("msg", "");
            resultMap.put("data", roleVO);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryRoleByUserId:", e);
        }
        return resultMap;
    }

    /**
     * 用户配置角色
     *
     * @param param
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> userAlloRole(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            MKCloudUserRole record = new MKCloudUserRole();
            record.setOrgCode(param.get("orgCode"));
            record.setUserId(param.get("userId"));
            String roleIds = param.get("roleIds");
            String[] ss = roleIds.split(",");
            List<String> roleIdList = new ArrayList<>();
            for (String s : ss) {
                roleIdList.add(s);
            }
            if (roleIdList != null) {
                //1.先把该用户的用户角色关系状态置为无效
                mkCloudUserRoleMapper.updateStatus(record);
                //2.重新插入用户角色对应关系表
                for (int i = 0; i < roleIdList.size(); i++) {
                    MKCloudUserRole record1 = new MKCloudUserRole();
                    record1.setOrgCode(param.get("orgCode"));
                    record1.setUserId(param.get("userId"));
                    record1.setRoleId(roleIdList.get(i));
                    mkCloudUserRoleMapper.insertSelective(record1);
                }
            }

            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("userAlloRole:", e);
        }
        return resultMap;


    }

    //-----------------------------------------角色管理----------------------------------------------------------------

    /**
     * 查询所有角色
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryRoleInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            List<MKCloudRoleListVO> mkCloudRoleListVOS = new ArrayList<>();
            MKCloudRoleInfo record = new MKCloudRoleInfo();
            record.setOrgCode(param.get("orgCode"));
            if (StringUtils.isNotBlank(param.get("roleId"))) {
                record.setId(Long.valueOf(param.get("roleId")));
            }
            record.setRoleName(param.get("roleName"));
            List<MKCloudRoleInfo> mkCloudRoleInfoList = mkCloudRoleInfoMapper.queryRoleInfo(record);
            if (mkCloudRoleInfoList != null && mkCloudRoleInfoList.size() > 0) {
                for (MKCloudRoleInfo mkCloudRoleInfo : mkCloudRoleInfoList) {
                    MKCloudRoleListVO mkCloudRoleListVO = new MKCloudRoleListVO();
                    mkCloudRoleListVO.setId(mkCloudRoleInfo.getId().toString());
                    mkCloudRoleListVO.setRoleId(mkCloudRoleInfo.getId().toString());
                    mkCloudRoleListVO.setRoleName(mkCloudRoleInfo.getRoleName());
                    mkCloudRoleListVO.setCreateTime(mkCloudRoleInfo.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mkCloudRoleInfo.getCreateTime()));
                    mkCloudRoleListVO.setCreator(mkCloudRoleInfo.getCreator());
                    mkCloudRoleListVO.setStatusName(Status.parser(mkCloudRoleInfo.getStatus()).getMsg());
                    mkCloudRoleListVO.setStatusCode(mkCloudRoleInfo.getStatus());
                    mkCloudRoleListVO.setDescribe(mkCloudRoleInfo.getDescribe());
                    mkCloudRoleListVOS.add(mkCloudRoleListVO);
                }
            }
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
            resultMap.put("data", mkCloudRoleListVOS);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryRoleInfo:", e);
        }
        return resultMap;
    }

    /**
     * 新增角色
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> addRoleInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            param.put("orgCode", "1000");
            MKCloudRoleInfo record = new MKCloudRoleInfo();
            record.setOrgCode(param.get("orgCode"));
            record.setRoleId(param.get("roleId"));
            record.setRoleName(param.get("roleName"));
            //新增角色前判断角色名是否已存在
            MKCloudRoleInfo mkCloudRoleInfo = mkCloudRoleInfoMapper.queryRoleByName(record);
            if (mkCloudRoleInfo != null) {
                resultMap.put("code", 1);
                resultMap.put("msg", "角色名称已存在！");
            } else {
                record.setStatus(param.get("statusCode"));
                record.setDescribe(param.get("describe"));
                record.setCreator(user.getUserLoginName());
                mkCloudRoleInfoMapper.insertSelective(record);
                resultMap.put("code", 0);
                resultMap.put("msg", "新增成功！");
            }
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("addRoleInfo:", e);

        }
        return resultMap;
    }

    /**
     * 修改角色
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> updateRoleInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            MKCloudRoleInfo record = new MKCloudRoleInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setRoleId(param.get("roleId"));
            record.setRoleName(param.get("roleName"));
            record.setStatus(param.get("statusCode"));
            record.setDescribe(param.get("describe"));
            record.setUpdator(user.getUserLoginName());
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
            mkCloudRoleInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("updateRoleInfo:", e);
        }
        return resultMap;
    }

    /**
     * 通过角色Id查询菜单
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryMenuByRoleId(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<MKCloudMenuListVO> mkCloudMenuListVOS = new ArrayList<>();
            List<MKCloudMenuListVO> allMenuFirstListVo = new ArrayList<>();
            List<MKCloudMenuListVO> allMenuSecondListVo = new ArrayList<>();
            MKCloudMenuInfo record = new MKCloudMenuInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setOrgCode("1000");
            //查询该角色下的有效菜单
            List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryMenuByRoleId(record);
            for (MKCloudMenuInfo menuInfo : mkCloudMenuInfos) {
                MKCloudMenuListVO listVO = new MKCloudMenuListVO();
                listVO.setId(menuInfo.getId().toString());
                listVO.setMenuId(menuInfo.getMenuId());
                listVO.setMenuName(menuInfo.getMenuName());
                listVO.setParentMenuId(menuInfo.getParentMenuId());
                mkCloudMenuListVOS.add(listVO);
            }
            MKCloudMenuInfo record1 = new MKCloudMenuInfo();
            record1.setStatus("1");
            record1.setOrgCode("1000");
            //查询所有有效菜单
            List<MKCloudMenuInfo> allMenuList = mkCloudMenuInfoMapper.queryMenuInfo(record1);

            for (MKCloudMenuInfo menuInfo : allMenuList) {
                if ("1".equals(menuInfo.getLevel())) {
                    MKCloudMenuListVO listVO = new MKCloudMenuListVO();
                    listVO.setId(menuInfo.getId().toString());
                    listVO.setMenuId(menuInfo.getMenuId());
                    listVO.setMenuName(menuInfo.getMenuName());
                    listVO.setParentMenuId(menuInfo.getParentMenuId());
                    allMenuFirstListVo.add(listVO);
                }
            }
            for (MKCloudMenuListVO menuListVO : allMenuFirstListVo) {
                String menuId = menuListVO.getMenuId();
                for (MKCloudMenuInfo menuInfo : allMenuList) {
                    if (menuId.equals(menuInfo.getParentMenuId())) {
                        MKCloudMenuListVO listVO = new MKCloudMenuListVO();
                        listVO.setId(menuInfo.getId().toString());
                        listVO.setMenuId(menuInfo.getMenuId());
                        listVO.setMenuName(menuInfo.getMenuName());
                        listVO.setParentMenuId(menuInfo.getParentMenuId());
                        listVO.setParentMenuName(menuInfo.getParentMenuName());
                        allMenuSecondListVo.add(listVO);
                    }
                }
            }

            MKCloudMenuAllListVO mkCloudMenuAllListVO = new MKCloudMenuAllListVO();
            mkCloudMenuAllListVO.setMkCloudMenuListVOS(mkCloudMenuListVOS);//通过角色id查询菜单
//            mkCloudMenuAllListVO.setAllMenuFirstListVo(allMenuFirstListVo);//查询所有一级菜单
            mkCloudMenuAllListVO.setAllMenuSecondListVo(allMenuSecondListVo);//查询所有二级菜单
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
            resultMap.put("data", mkCloudMenuAllListVO);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryMenuByRoleId:", e);
        }
        return resultMap;
    }

    /**
     * 角色分配菜单
     *
     * @param param
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> roleAlloMenu(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            param.put("orgCode", "1000");
            String orgCode = param.get("orgCode");
            String roleId = param.get("roleId");
            String menuIds = param.get("menuIds");
            String[] ss = menuIds.split(",");
            List<String> menuIdList = new ArrayList<>();
            for (String s : ss) {
                menuIdList.add(s);
            }
            List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryParentMenu(orgCode, menuIdList);

            for (MKCloudMenuInfo menuInfo : mkCloudMenuInfos) {
                if (menuInfo.getId() != null) {
                    menuIdList.add(menuInfo.getId().toString());
                }
            }
            //1.修改该角色对应的菜单id状态为0;
            MKCloudRoleMenu record = new MKCloudRoleMenu();
            record.setOrgCode("1000");
            record.setRoleId(roleId);
            mkCloudRoleMenuMapper.updateStatus(record);

            //2.重新插入角色菜单对应关系表
            for (int i = 0; i < menuIdList.size(); i++) {
                MKCloudRoleMenu record1 = new MKCloudRoleMenu();
                record1.setOrgCode("1000");
                record1.setRoleId(roleId);
                record1.setMenuId(menuIdList.get(i));
                mkCloudRoleMenuMapper.insertSelective(record1);
            }
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("roleAlloMenu:", e);
        }
        return resultMap;
    }

    //------------------------------------菜单管理-------------------------------------------------------

    /**
     * 查询菜单
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryMenuInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<MKCloudMenuListVO> mkCloudMenuListVOS = new ArrayList<>();
            MKCloudMenuInfo record = new MKCloudMenuInfo();
            record.setOrgCode(param.get("orgCode"));
            if (StringUtils.isNotBlank(param.get("menuId"))) {
                record.setId(Long.valueOf(param.get("menuId")));
            }
            record.setMenuName(param.get("menuName"));
            record.setStatus(param.get("statusCode"));
            List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryMenuInfo(record);
            for (MKCloudMenuInfo menuInfo : mkCloudMenuInfos) {
                MKCloudMenuListVO listVO = new MKCloudMenuListVO();
                listVO.setId(menuInfo.getId().toString());
                listVO.setMenuId(menuInfo.getMenuId());
                listVO.setMenuName(menuInfo.getMenuName());
                listVO.setTypeCode(menuInfo.getType());
                listVO.setTypeName(MenuTypeEnum.parser(menuInfo.getType()).getMsg());
                listVO.setParentMenuId(menuInfo.getParentMenuId());
                listVO.setParentMenuName(menuInfo.getParentMenuName());
                listVO.setStatusCode(menuInfo.getStatus());
                listVO.setStatusName(Status.parser(menuInfo.getStatus()).getMsg());
                listVO.setUrl(menuInfo.getUrl());
                listVO.setLevel(menuInfo.getLevel());
                listVO.setCreateTime(menuInfo.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(menuInfo.getCreateTime()));
                listVO.setCreator(menuInfo.getCreator());
                mkCloudMenuListVOS.add(listVO);
            }
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
            resultMap.put("data", mkCloudMenuListVOS);
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryMenuInfo:", e);
        }
        return resultMap;
    }

    /**
     * 修改菜单
     *
     * @param param
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> updateMenuInfo(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            MKCloudUserInfo user = (MKCloudUserInfo) getSession().getAttribute(USER_KEY_IN_SESSION);
            MKCloudMenuInfo record = new MKCloudMenuInfo();
            record.setId(Long.valueOf(param.get("id")));
            record.setMenuId(param.get("menuId"));
            record.setMenuName(param.get("menuName"));
            record.setType(param.get("typeCode"));
            record.setParentMenuId(param.get("parentMenuId"));
            record.setStatus(param.get("statusCode"));
            record.setUrl(param.get("url"));
            record.setUpdator(user.getUserLoginName());

            MKCloudMenuInfo query = new MKCloudMenuInfo();
            if (StringUtils.isNotBlank(param.get("parentMenuId"))) {
                query.setOrgCode("1000");
                query.setMenuId(param.get("parentMenuId"));
                query.setStatus("1");
                List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryMenuInfo(query);
                String parentMenuName = null;
                if (mkCloudMenuInfos != null && mkCloudMenuInfos.size() > 0) {
                    parentMenuName = mkCloudMenuInfos.get(0).getMenuName();
                }
                record.setParentMenuName(parentMenuName);
            }

            mkCloudMenuInfoMapper.updateByPrimaryKeySelective(record);
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("updateMenuInfo:", e);
        }
        return resultMap;
    }

    /**
     * 查询一级菜单
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryFirstMenu(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<MKCloudMenuListVO> mkCloudMenuListVOS = new ArrayList<>();
            MKCloudMenuInfo record = new MKCloudMenuInfo();
            record.setOrgCode(param.get("orgCode"));
            List<MKCloudMenuInfo> mkCloudMenuInfos = mkCloudMenuInfoMapper.queryFirstMenu(record);
            for (MKCloudMenuInfo menuInfo : mkCloudMenuInfos) {
                MKCloudMenuListVO menuListVO = new MKCloudMenuListVO();
                menuListVO.setId(menuInfo.getId().toString());
                menuListVO.setMenuId(menuInfo.getMenuId());
                menuListVO.setMenuName(menuInfo.getMenuName());
                mkCloudMenuListVOS.add(menuListVO);
            }
            resultMap.put("code", 0);
            resultMap.put("msg", "成功");
            resultMap.put("data", mkCloudMenuListVOS);

        } catch (Exception e) {
            resultMap.put("code", 1);
            resultMap.put("msg", "系统异常");
            logger.error("queryFirstMenu:", e);
        }
        return resultMap;
    }
}
