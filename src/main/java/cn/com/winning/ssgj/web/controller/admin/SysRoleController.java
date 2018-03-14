package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenKuai
 * @create 2018-01-24 上午 9:27
 **/
@Controller
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/roleInfo.do")
    @ILog
    public String getRoleInfoPage(HttpServletRequest request, Model model) {
        return "auth/user/roleinfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    @ILog(operationName = "角色列表", operationType = "list")
    public Map<String, Object> sysRoleList(SysRoleInfo role, Row row) {
        role.setRow(row);
        role.setIsDel(Constants.STATUS_UNUSE);
        List<SysRoleInfo> roleInfos = super.getFacade().getSysRoleInfoService().getSysRoleInfoPaginatedListForName(role);
        int total = super.getFacade().getSysRoleInfoService().getSysRoleInfoCountForName(role);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("total", total);
        result.put("rows", roleInfos);
        return result;
    }


    @RequestMapping("/getById.do")
    @ResponseBody
    @ILog(operationName = "使用ID查询角色", operationType = "queryRoleById")
    public Map<String, Object> queryRoleById(SysRoleInfo role) {
        role = super.getFacade().getSysRoleInfoService().getSysRoleInfo(role);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", role);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/deleteById.do")
    @ResponseBody
    @ILog(operationName = "使用ID删除角色", operationType = "deleteRoleById")
    public Map<String, Object> deleteRoleById(SysRoleInfo role) {
        role.setIsDel(Constants.STATUS_USE);
        super.getFacade().getSysRoleInfoService().modifySysRoleInfo(role);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping("/add.do")
    @ResponseBody
    @ILog(operationName = "添加角色", operationType = "addRole")
    public Map<String, Object> addRole(SysRoleInfo roleInfo) {
        roleInfo.setId(ssgjHelper.createRoleId());
        roleInfo.setIsDel(Constants.STATUS_UNUSE);
        super.getFacade().getSysRoleInfoService().createSysRoleInfo(roleInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    @ILog(operationName = "更新角色", operationType = "updateRole")
    public Map<String, Object> updateRole(SysRoleInfo roleInfo) {
        super.getFacade().getSysRoleInfoService().modifySysRoleInfo(roleInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/tree.do")
    @ResponseBody
    @ILog
    public Map<String, Object> queryRoleTree(String roleName) {

        List<NodeTree> roleInfoList = super.getFacade().getSysRoleInfoService().getRoleInfoTree(roleName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", roleInfoList);
        return result;

    }
}
