package cn.com.winning.ssgj.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.FlotDataInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;

/**
 * 用户信息处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping(value = "/admin/user")
public class SysUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/userinfo.do")
    public String userinfoPage(HttpServletRequest request, Model model) {
        return "auth/user/userinfo";
    }

    /**
     * 用户信息列表
     *
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, SysUserInfo userInfo) {
        System.out.println(row);
        System.out.println(userInfo);
        userInfo.setRow(row);
        userInfo.setStatus(Constants.PMIS_STATUS_USE);
        List<SysUserInfo> userInfos = getFacade().getSysUserInfoService().getSysUserInfoQueryPaginatedList(userInfo);
        int total = getFacade().getSysUserInfoService().getSysUserInfoQueryCount(userInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", userInfos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过用户ID查询用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String, Object> getUserByUserId(SysUserInfo user) {
        user = super.getFacade().getSysUserInfoService().getSysUserInfo(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", user);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过用户ID删除用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteUserById(SysUserInfo user) {
        user.setStatus(Integer.valueOf(Constants.User.USER_STATUS_LOCKED));
        getFacade().getSysUserInfoService().modifySysUserInfo(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(value = "/add.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> addUser(SysUserInfo user) {
        /*System.out.println(email);*/
        user.setId(ssgjHelper.createUserId());
        //医院用户需要新建时候需要重置密码
        if (Constants.User.USER_TYPE_HOSPITAL.equals(user.getUserType())) {
            user.setPassword(MD5.stringMD5(user.getUserid()));
        }
        user.setName(user.getYhmc() + "(" + user.getUserid() + ")");
        super.getFacade().getSysUserInfoService().createSysUserInfo(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "修改用户信息", operationType = "updateUser")
    public Map<String, Object> updateUser(SysUserInfo user) {
        getFacade().getSysUserInfoService().modifySysUserInfo(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    @RequestMapping(value = "/count.do")
    @ResponseBody
    public Map<String, Object> countUserInfo() {

        List<FlotDataInfo> flotDataInfos = super.getFacade().getSysUserInfoService().countUserInfoByType();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", flotDataInfos);
        return result;

    }
}
