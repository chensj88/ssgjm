package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

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
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/list.do")
    @ILog
    public String floorQuestionList(Model model, String parameter) {
        //进行中的项目
        //parameter = "eyJPUEVOSUQiOiJveUR5THhCY2owclRkOXJWV3lWNXZUT0RfTnA0IiwiSE9TUENPREUiOiIxMTk4MCIsIldPUktOVU0iOiIxNDIwIiwiVVNFUk5BTUUiOiLlvKDlhYvnpo8iLCJVU0VSUEhPTkUiOiIxMzMxMjM0NTY3OCJ9";
        try {
            byte[] byteArray = Base64Utils.decryptBASE64(parameter);
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter), "UTF-8") + "]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            SysUserInfo info = new SysUserInfo();
            if (userList != null && !userList.equals("")) {
                for (int i = 0; i < userList.size(); i++) {
                    JSONObject io = userList.get(i);
                    info.setId(ssgjHelper.createUserId());
                    info.setUserType("0");
                    info.setOpenId((String) io.get("OPENID"));
                    info.setName((String) io.get("USERNAME"));
                    info.setUserid((String) io.get("HOSPCODE") + (String) io.get("WORKNUM"));
                    info.setPassword(MD5.stringMD5ForBarCode((String) io.get("WORKNUM")));
                    info.setMobile((String) io.get("USERPHONE"));
                    info.setSsgs(Long.parseLong((String) io.get("HOSPCODE")));
                }
                //判断用户 userId 是否存在
                SysUserInfo info_use = new SysUserInfo();
                info_use.setUserid(info.getUserid());
                List<SysUserInfo> userIfonList = super.getFacade().getSysUserInfoService().getSysUserInfoList(info_use);
                //真实用户
                if (userIfonList.size() == 0 || userIfonList == null) {
                    super.getFacade().getSysUserInfoService().createSysUserInfo(info);
                }
            }
            EtFloorQuestionInfo questionInfo = new EtFloorQuestionInfo();
            //questionInfo.getMap().put("Ssgs", info.getSsgs());
            questionInfo.setSerialNo(String.valueOf(info.getSsgs()));
            List<EtFloorQuestionInfo> infoList = super.getFacade().getEtFloorQuestionInfoService()
                    .getEtFloorQuestionInfoSummaryList(questionInfo);
            model.addAttribute("infoList", infoList);
            model.addAttribute("userId", info.getUserid());
            model.addAttribute("serialNo", questionInfo.getSerialNo());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mobile/service/floor-question";
    }

    @RequestMapping("/floorQuestionReport.do")
    @ILog
    public String floorQuestionReport(Model model, String floorName, String serialNo,String userId) {
        EtFloorQuestionInfo questionInfo = new EtFloorQuestionInfo();
        questionInfo.setSerialNo(serialNo);
        questionInfo.setFloorName(floorName);
        List<EtFloorQuestionInfo> infoList = super.getFacade().getEtFloorQuestionInfoService()
                .getEtFloorQuestionInfoWithHospitalList(questionInfo);
        model.addAttribute("infoList", infoList);
        model.addAttribute("user_id", userId);

        return "/mobile/service/floor-report";
    }

    @RequestMapping("/causeFloorData.do")
    @ResponseBody
    @ILog
    public Map<String, Object> causeFloorData(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        EtFloorQuestionInfo info = new EtFloorQuestionInfo();
        info.setId(id);
        info = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfo(info);
        map.put("info", info);
        return map;
    }


    @RequestMapping("/causeFloor.do")
    @ResponseBody
    @ILog
    public Map<String, Object> causeFloor(EtFloorQuestionInfo info) {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserid(info.getOperator() + "");
        userInfo.setStatus(1);
        userInfo.setUserType("0");//0医院
        List<SysUserInfo> userInfoList = super.getFacade().getSysUserInfoService().getSysUserInfoList(userInfo);
        info.setOperator(userInfoList.get(0).getId());
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            super.getFacade().getEtFloorQuestionInfoService().modifyEtFloorQuestionInfo(info);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        return map;
    }

}
