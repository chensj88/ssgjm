package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/login.do")
    @ILog
    public String Login(HttpServletRequest request, Model model) {
        return "login/login";
    }

    @RequestMapping("/error.do")
    @ILog
    public String unauthorizedUrlPage(HttpServletRequest request, Model model) {
        return "index/error";
    }

    @RequestMapping(value = "/check.do")
    @ResponseBody
    @ILog
    public Map<String, Object> check(HttpServletRequest request, String username, String password) {

        System.out.println(username);
        System.out.println(password);
        Map<String, Object> map = new HashMap<String, Object>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.stringMD5(password));
        Subject subject = SecurityUtils.getSubject();
        String error = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        //用户名检查
        if (error != null) {
            map.put("status", false);
            map.put("message", error);
        } else {
            map.put("status", true);
        }
        return map;
    }


}
