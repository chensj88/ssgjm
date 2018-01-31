package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenshijie
 * @title ${file_name}
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-01-31 8:52
 */
@Controller
@RequestMapping(value = "/admin/auth")
public class SysUserRoleController extends BaseController {

    @RequestMapping(value = "/authInfo.do")
    public String getAuthInfoPage(HttpServletRequest request, Model model){
        return "auth/user/userauthinfo";
    }
}
