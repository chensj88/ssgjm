package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.commons.lang.StringUtils;
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
    public String list(Model model, String parameter,String processStatus,String search_text) {
       // parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        parameter = "eyJPUEVOSUQiOiJveUR5THhCY2owclRkOXJWV3lWNXZUT0RfTnA0IiwiSE9TUENPREUiOiIxMTk4MCIsIldPUktOVU0iOiIxNDIwIiwiVVNFUk5BTUUiOiLlvKDlhYvnpo8iLCJVU0VSUEhPTkUiOiIxMzMxMjM0NTY3OCJ9";
        try{
            SysUserInfo info = super.getUserInfo(parameter);
            EtSiteQuestionInfo qinfo = new EtSiteQuestionInfo();
            qinfo.setCreator((long)7110);
            qinfo.setSerialNo("11403");
            qinfo.getMap().put("search_text",search_text);
            if("1".equals(processStatus)){
                qinfo.getMap().put("process_status_yes","4,5");
            }else{
                qinfo.getMap().put("process_status_no","4,5");
            }

            model.addAttribute("questionList", super.getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(qinfo));
            model.addAttribute("process_num",super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(qinfo));
            model.addAttribute("userId", info.getUserid());
            model.addAttribute("serialNo", info.getSsgs());
            model.addAttribute("openId",info.getOpenId());
            model.addAttribute("active",processStatus);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "mobile2/service/implement-work";
    }




}
