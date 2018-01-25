package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
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

@Controller
@RequestMapping("login")
public class LoginController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/login.do")
    public String Login(HttpServletRequest request, Model model){

        return "login/login";
    }

    @RequestMapping(value = "/check.do")
    @ResponseBody
    public Map<String,Boolean> check(String username, String password){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        SysUserInfo info = new SysUserInfo();
        info.setYhmc(username);
        //用户名检查
        List<SysUserInfo> infoList = super.getFacade().getSysUserInfoService().getSysUserInfoList(info);
        if(infoList != null && infoList.size()>0 ){
            info.setPassword(MD5.stringMD5(password));
            info = super.getFacade().getSysUserInfoService().getSysUserInfo(info);
            if(info!=null && !"".equals(info)){
                map.put("status",true); //登陆成功
            }else {
                map.put("status",false);
            }
        }else {
            map.put("status", false);
        }
        return map;
    }


}
