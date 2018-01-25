package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("home")
public class HomeController extends BaseController {
    @RequestMapping(value = "/home.do")
    public String Login(HttpServletRequest request, Model model){

        return "home/home";
    }

}
