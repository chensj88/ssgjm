package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysRoleInfo;
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
    public String getRoleInfoPage(HttpServletRequest request, Model model) {
        return "auth/user/roleinfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    @ILog(operationName="角色列表",operationType="list")
    public Map<String,Object> sysRoleList(Row row) {
        SysRoleInfo role = new SysRoleInfo();
        role.setRow(row);
        List<SysRoleInfo> roleInfos = super.getFacade().getSysRoleInfoService().getSysRoleInfoPaginatedList(role);
        int total = super.getFacade().getSysRoleInfoService().getSysRoleInfoCount(role);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("total", total);
		result.put("rows", roleInfos);
        return result;
    }





}
