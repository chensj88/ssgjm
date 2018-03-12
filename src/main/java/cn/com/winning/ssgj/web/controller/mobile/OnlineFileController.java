package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 实施资料汇总上线可行性方案表
 *
 * @author ChenKuai
 * @create 2018-03-10 上午 11:37
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/implementData")
public class OnlineFileController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/list.do")
    public String floorQuestionList(Model model, String parameter) {




        return "/mobile/enterprise/data-upload";
    }

    @RequestMapping("/details.do")
    public String floorQuestionDetailList(Model model, String val_type) {



        return "/mobile/enterprise/data-upload-report";
    }


}
