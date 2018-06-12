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
        parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        try{
            SysUserInfo info = super.getUserInfo(parameter);
            EtSiteQuestionInfo qinfo = new EtSiteQuestionInfo();
            //qinfo.setCreator(Long.parseLong(info.getUserid()));
            //qinfo.setSerialNo(String.valueOf(info.getSsgs()));
            qinfo.setCreator((long)100008);
            qinfo.setSerialNo("11980");
            model.addAttribute("questionList", getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(qinfo));
            model.addAttribute("userId", info.getUserid());
            model.addAttribute("serialNo", info.getSsgs());

        }catch (Exception e){
            e.printStackTrace();
        }

        return "mobile2/service/implement-work";
    }




}
