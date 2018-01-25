package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenshijie
 * @title ${file_name}
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-01-25 17:59
 */
@Controller
@RequestMapping(value = "/admin/flow")
public class FlowInfoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping("/flowInfo.do")
    public String gotoPage(HttpServletRequest request, Model model) {
        return "auth/module/flowinfo";
    }


}