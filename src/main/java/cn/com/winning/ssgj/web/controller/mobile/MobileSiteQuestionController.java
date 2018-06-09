package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title 微信服务号问题采集中心
 * @package cn.com.winning.ssgj.web.controller.mobile
 * @date 2018-06-09 13:57
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wechatSiteQuestion")
public class MobileSiteQuestionController  extends BaseController {


    @RequestMapping(value = "/add.do")
    public String add(Model model,long userid) {
        model.addAttribute("userid", userid);
        return "/mobile/service/site-question";
    }
}
