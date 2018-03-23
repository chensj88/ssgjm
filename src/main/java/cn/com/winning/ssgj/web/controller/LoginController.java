package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.base.util.PasswordUtils;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login/login.do")
    public String Login(HttpServletRequest request, Model model) {
        return "login/login";
    }

    @RequestMapping("/login/error.do")
    public String unauthorizedUrlPage(HttpServletRequest request, Model model) {
        return "index/error";
    }

    @RequestMapping(value = "/login/check.do")
    @ResponseBody
    public Map<String, Object> check(HttpServletRequest request, String username, String password)  {
        String error = login(username,password);
        Map<String, Object> map = new HashMap<String, Object>();
        //用户名检查
        if (error != null) {
            map.put("status", false);
            map.put("message", error);
        } else {
            map.put("status", true);
        }
        return map;
    }

    @RequestMapping(value = "/vue/login.do")
    @ResponseBody
    public Map<String,Object> vueLoginUser(String userid,String password){

        String error = login(userid,password);
        Map<String, Object> map = new HashMap<String, Object>();
        //用户名检查
        if (error != null) {
            map.put("status", false);
            map.put("message", error);
        } else {
            map.put("status", true);
        }
        return map;

    }

    private String login(String userid,String password){
        String error = null;
        String decodePassword = null;
        try {
            decodePassword = PasswordUtils.decryptPasswordByBase64(password);
        } catch (Exception e) {
            e.printStackTrace();
            error = "密码解密失败";
        }

        Map<String, Object> map = new HashMap<String, Object>();
        UsernamePasswordToken token = new UsernamePasswordToken(userid, MD5.stringMD5(decodePassword));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            error = "其他错误：" + e.getMessage();
        }

        return error;
    }

}
