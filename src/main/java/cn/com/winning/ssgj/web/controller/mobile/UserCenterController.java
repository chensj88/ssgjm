package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.domain.SysOrganization;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业号-个人中心
 */
@Controller
@CrossOrigin
@RequestMapping("mobile/userCenter")
public class UserCenterController extends BaseController {

    /**
     * 个人中心
     * @param model
     * @param parameter
     * @return
     */
    @RequestMapping(value="/list.do")
    public String userCenter(Model model ,String parameter,String userId,String serialNo,String serialName){
        String access_token = super.getAccessToken();
        userId ="5823";
        serialNo ="11403";
        serialName ="通州市医院";
        StringBuffer stringBuffer = new StringBuffer(WxConstants.QY_USER_INFO2);
        stringBuffer.append("access_token=").append(access_token).append("&userid=").append(userId);
        try{
            JSONObject userInfo = WeixinUtil.getApiReturn(stringBuffer.toString());
            //获取部门信息
            SysOrganization organization = new SysOrganization();
            String departId = (String) userInfo.get("department").toString();
            departId =departId.replaceAll("\\[","");
            departId = departId.replaceAll("\\]","");
            organization.setId(Long.valueOf(departId));
            organization = super.getFacade().getSysOrganizationService().getSysOrganization(organization);

            String user_name = (String)userInfo.get("name");
            user_name =new String(user_name.getBytes(),"UTF-8");
            model.addAttribute("name",user_name);
            model.addAttribute("user_img",(String)userInfo.get("avatar"));
            model.addAttribute("department_name",organization.getJgqc());
            model.addAttribute("serialNo",serialNo);
            model.addAttribute("serialName",serialName);


        }catch (Exception e){
            e.printStackTrace();
        }


        return "mobile2/enterprise/user-center";
    }


}
