package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mobile/tempSiteQuestion")
public class MobileTempSiteQuestionController  extends BaseController {

    /**
     *  院方信息主页面
     * @param model
     * @param parameter
     * @param processStatus
     * @param search_text
     * @return
     */
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


    /**
     * 采集中心新增/修改问题客户问题
     * @param model model  主要用来传输参数
     * @param userId 用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/addPage.do")
    public String add(Model model,Long questionId,Long userId,String serialNo) {
        if(questionId != null){
            EtSiteQuestionInfo info = new EtSiteQuestionInfo();
            info.setId(questionId);
            info=super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            if(StringUtils.isNotBlank(info.getImgPath())){
                String[] imgs=info.getImgPath().split(";");
                List<String> lists= Arrays.asList(imgs);
                info.setImgs(lists);
            }
            model.addAttribute("siteQuestionInfo",info);
        }else{
            model.addAttribute("siteQuestionInfo",null);
        }
        model.addAttribute("deptList", this.getDepartmentList(Long.parseLong(serialNo),null));
        model.addAttribute("appList", this.getProductDictInfo(serialNo));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        return "mobile2/service/large-sick";
    }




}
