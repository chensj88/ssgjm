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
 * @date 2018-03-09 16:39
 */
@Controller
@RequestMapping(value = "/admin/online")
public class SysDataScriptController extends BaseController {

    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return  "online/scriptPage";
    }


}
