package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-13 16:27
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue")
public class VueLoginController extends BaseController{


    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Map<String,Object> vueLoginUser(String userid,String password){

        String error = null;
        String decodePassword = null;
        try {
            decodePassword = new String(Base64Utils.decryptBASE64(password),"UTF-8");
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
