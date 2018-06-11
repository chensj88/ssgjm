package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mobile/tempSiteQuestion")
public class MobileTempSiteQuestionController  extends BaseController {


    @RequestMapping(value = "/list.do")
    public String list(Model model, String parameter) {
        SysUserInfo info = super.getUserInfo(parameter);
        EtSiteQuestionInfo questionInfo = new EtSiteQuestionInfo();
        questionInfo.setCreator(info.getId());
        questionInfo.setSerialNo(String.valueOf(info.getSsgs()));
        List<EtSiteQuestionInfo> etSiteQuestionInfoList = super.getFacade().getEtSiteQuestionInfoService().selectMobileEtSiteQuestionInfo(questionInfo);




        return "mobile2/wechat/site-question-first";
    }




}
