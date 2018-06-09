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

    /**
     * 采集中心新增客户问题
     * @param model model  主要用来传输参数
     * @param userid 用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/add.do")
    public String add(Model model,long userid,String serialNo) {
        model.addAttribute("siteQuestionInfo",null);
        model.addAttribute("deptList", this.getDepartmentList(Long.parseLong(serialNo),null));
        model.addAttribute("appList", this.getProductDictInfo(serialNo));
        model.addAttribute("userid", userid);
        model.addAttribute("serialNo", serialNo);
        return "/mobile/service/site-question";
    }

    @RequestMapping(value = "/list.do")
    public String list(Model model,long userid,String serialNo) {
        return "/mobile/service/site-question-list";
    }
}
