package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 楼层问题汇报
 *
 * @author ChenKuai
 * @create 2018-03-06 上午 11:06
 **/
@Controller
@CrossOrigin
@RequestMapping("/mobile/floorQuestion")
public class FloorQuestionController extends BaseController {
    @RequestMapping(value = "/list.do")
    public String TrainVideoTypeList(Model model, String parameter) {
        //进行中的项目


        return null;
    }



}
