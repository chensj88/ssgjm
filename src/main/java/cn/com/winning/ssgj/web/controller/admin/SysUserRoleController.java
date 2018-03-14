package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysRoleUser;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title ${file_name}
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-01-31 8:52
 */
@Controller
@RequestMapping(value = "/admin/userrole")
public class SysUserRoleController extends BaseController {

    @RequestMapping(value = "/query.do")
    @ResponseBody
    @ILog
    public Map<String, Object> queryUserRoleMaping(Long userId) {
        SysRoleUser roleUser = new SysRoleUser();
        roleUser.setUserId(userId);
        List<Long> roleIdList = super.getFacade().getSysRoleUserService().getRoleIdList(roleUser);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", roleIdList);
        return result;
    }


    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addUserRoleMaping(String idStr) {
        super.getFacade().getSysRoleUserService().createSysRoleUserByIdString(idStr);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteUserRoleMaping(SysRoleUser roleUser) {
        super.getFacade().getSysRoleUserService().removeSysRoleUser(roleUser);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
