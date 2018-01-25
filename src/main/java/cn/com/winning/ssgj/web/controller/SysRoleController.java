package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ChenKuai
 * @create 2018-01-24 上午 9:27
 **/
@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping("/list.do")
    @ILog(operationName="角色列表",operationType="list")
    public String sysRoleList(HttpServletRequest request, Model model, SysRoleInfo roleInfo) {
        try {
            List<SysRoleInfo> roleInfoList = super.getFacade().getSysRoleInfoService().getSysRoleInfoList(roleInfo);
            model.addAttribute("name","chenkuai");
        }catch (Exception e){
            e.printStackTrace();
        }


        return "role/role";
    }





}
