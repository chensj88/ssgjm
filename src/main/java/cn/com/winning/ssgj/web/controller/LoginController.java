package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("login")
public class    LoginController extends BaseController{

    @RequestMapping(value = "/login.do")
    public String Login(HttpServletRequest request, Model model){

        return "login/login";
    }

    @RequestMapping(value = "/home.do")
    public String LoginUser(String username,String password){
        Map<String, Object> map = new HashMap<>();
        SysUserInfo info = new SysUserInfo();
        info.setYhmc(username);
        //用户名检查
        List<SysUserInfo> infoList = super.getFacade().getSysUserInfoService().getSysUserInfoList(info);
        if(infoList != null && infoList.size()>0 ){
            info.setPassword(MD5.stringMD5(password));
            info = super.getFacade().getSysUserInfoService().getSysUserInfo(info);
            if(info!=null && !"".equals(info)){
                map.put("status",0); //登陆成功
            }else {
                map.put("status",2);
            }
        }else {
            map.put("status", 1);
        }
        System.out.println("曹尼玛111");
        return "index/index";
    }
}
