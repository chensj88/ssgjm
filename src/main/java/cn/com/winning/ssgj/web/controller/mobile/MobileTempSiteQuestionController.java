package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/mobile/tempSiteQuestion")
public class MobileTempSiteQuestionController  extends BaseController {

    /**
     *  院方信息主页面
     * @param model
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/list.do")
    public String list(Model model, String parameter) {
       // parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        //parameter = "eyJPUEVOSUQiOiJveUR5THhCY2owclRkOXJWV3lWNXZUT0RfTnA0IiwiSE9TUENPREUiOiIxMTk4MCIsIldPUktOVU0iOiIxNDIwIiwiVVNFUk5BTUUiOiLlvKDlhYvnpo8iLCJVU0VSUEhPTkUiOiIxMzMxMjM0NTY3OCJ9";
        try{
            SysUserInfo info = super.getUserInfo(parameter);
            EtSiteQuestionInfo qInfo = new EtSiteQuestionInfo();
            qInfo.setCreator(info.getId());
            qInfo.setSerialNo(String.valueOf(info.getSsgs()));
            qInfo.setProcessStatus(4);//未确认
            //qinfo.getMap().put("process_status_no","5");//未确认
            model.addAttribute("questionList", super.getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(qInfo));
            model.addAttribute("process_num",mapList(qInfo,1,-1));
            model.addAttribute("userId", qInfo.getCreator());
            model.addAttribute("serialNo", qInfo.getSerialNo());
            model.addAttribute("openId",info.getOpenId());
            model.addAttribute("active",4);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "mobile2/service/implement-work";
    }

    @RequestMapping(value = "/laodList.do")
    public String laodList(Model model,Long userId,String serialNo,String openId,int processStatus,String search_text) {
        try{
            EtSiteQuestionInfo qinfo = new EtSiteQuestionInfo();
            qinfo.setCreator(userId);
            qinfo.setSerialNo(serialNo);
            qinfo.getMap().put("search_text",search_text);
//            if("1".equals(processStatus)){
//                qinfo.getMap().put("process_status_yes","5");//已确认完成
//            }else{
//                qinfo.getMap().put("process_status_no","5");
//            }
            if(processStatus ==1){
                qinfo.getMap().put("process_status_no","4,5");
            }else{
                qinfo.setProcessStatus(processStatus);
            }
            model.addAttribute("questionList", super.getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(qinfo));
            model.addAttribute("process_num", mapList(qinfo,1,-1));
            model.addAttribute("userId", userId);
            model.addAttribute("serialNo", serialNo);
            model.addAttribute("openId",openId);
            model.addAttribute("active",processStatus);
            model.addAttribute("search_text",search_text);
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
    public String add(Model model,Long questionId,Long userId,String serialNo,String openId) {
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
        model.addAttribute("openId",openId);
        return "mobile2/service/large-sick";
    }


    /**
     *  院方打回与确认完成
     * @param id
     * @param val
     */
    @RequestMapping(value = "/processStatus.do", method ={RequestMethod.POST})
    @ResponseBody
    public Map<String,String> processStatus(Long id,int val,String suggest,Long userId,String serialNo) {
        Map<String,String> map = new HashMap<String,String>();
        String msg ="";
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setId(id);
        info.setProcessStatus(val);
        info.setSuggest(suggest);
        if(val == 5){
            msg ="状态更改：院方确认完成";
        }else{
            msg ="状态更改：院方打回"+"院方意见："+suggest;
        }
        int i = super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        addEtLog(serialNo,"ET_SITE_QUESTION_INFO",info.getId(),msg,val,userId);
        return map;
    }

    /**
     * 微信开始
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/wxStart.do")
    public String wxStart(Model model,String code) {
        try{
//            String access_token = super.getAccessToken();
//            Cookie cookie = new Cookie("access_token",access_token);//将登录信息加入cookie中
//            cookie.setMaxAge(60*60*24*3);   //设置cookie最大失效时间 3天　　　
//            StringBuffer stringBuffer = new StringBuffer(WxConstants.QY_USER_INFO);
//            stringBuffer.append("access_token=").append(access_token).append("&code=").append(code);
//            JSONObject userInfo = WeixinUtil.getApiReturn(stringBuffer.toString());
//            String user_id = (String)userInfo.get("UserId"); //员工工号
//            logger.info("UserId=="+user_id);


            String user_id="5823";
            Long userId =super.user_id(user_id,"1"); //登录员工的ID
            model.addAttribute("userId",userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "mobile2/enterprise/start";
    }

    @RequestMapping(value = "/wxSearch.do")
    public String wxSearch(Model model,Long userId) {
        model.addAttribute("userId",userId);
        return "mobile2/enterprise/wx_search_hospital";
    }




    @RequestMapping(value = "/index.do")
    public String index(Model model,Long questionId,Long userId,String serialNo,String openId,String code) {
        try{
            int isManager =0;//0：项目经理 1：非项目经理　
            //userId =(long)7284;
            isManager =super.getPosition("11403",userId);

            EtSiteQuestionInfo qInfo = new EtSiteQuestionInfo();
            if(isManager > 0){
                qInfo.getMap().put("process_status_no","1,7");
                qInfo.setAllocateUser(userId);
                qInfo.setSerialNo(String.valueOf(11403));
            }else{
                qInfo.setCreator(null);
                qInfo.setSerialNo(String.valueOf(11403));
            }
            qInfo.getMap().put("isManager",isManager);
            model.addAttribute("questionList", super.getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(qInfo));
            model.addAttribute("process_num",mapList(qInfo,2,isManager));
            model.addAttribute("userId",userId);
            model.addAttribute("serialNo", qInfo.getSerialNo());
            //model.addAttribute("openId",info.getOpenId());
            model.addAttribute("active",0);
            model.addAttribute("isManager",isManager);//0 项目经理 1实施工程师
        }catch (Exception e){
            e.printStackTrace();
        }

        return "mobile2/enterprise/index";
    }


}
