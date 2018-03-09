package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("home")
public class HomeController extends BaseController {
    @RequestMapping(value = "/home.do")
    public String Login(HttpServletRequest request, Model model){

        return "home/home";
    }

    @RequestMapping(value = "/loadMenu.do")
    @ResponseBody
    public Map<String,Object> loadUserMenu(){

        SysUserInfo sysUserInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        List<NodeTree> menuNodeTree = super.getFacade().getSysModuleService().getUserMenu(sysUserInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",menuNodeTree);
        return result;

    }

}
