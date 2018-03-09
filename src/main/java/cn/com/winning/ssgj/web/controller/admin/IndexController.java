package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-23 16:47
 */
@Controller
@RequestMapping(value = "/home")
public class IndexController extends BaseController {

    @RequestMapping(value = "/index.do")
    public String indexPageInfo(HttpServletRequest request, Model model){
        return "index/index";
    }
}
