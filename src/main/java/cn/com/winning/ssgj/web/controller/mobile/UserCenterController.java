package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.domain.SysOrganization;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONObject;

import net.sf.json.JSONArray;
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
    public String userCenter(Model model ,String parameter,Long userId,String serialNo,String serialName,String isManager){
        String access_token = super.getAccessToken();
        userId =(long)7110;
        serialNo ="11403";
        serialName ="通州市医院";

        String userId_str = super.id_user(userId,"1");
        StringBuffer stringBuffer = new StringBuffer(WxConstants.QY_USER_INFO2);
        stringBuffer.append("access_token=").append(access_token).append("&userid=").append(userId_str);
        try{
            JSONObject userInfo = WeixinUtil.getApiReturn(stringBuffer.toString());
            //获取部门信息
            SysOrganization organization = new SysOrganization();
            JSONArray departIdJson = JSONArray.fromObject(userInfo.get("department") );
            if(departIdJson.size()>0){
                int departId = (int) departIdJson.get(0);
//              departId =departId.replaceAll("\\[","");
//              departId = departId.replaceAll("\\]","");
                organization.setId((long)departId);
                organization = super.getFacade().getSysOrganizationService().getSysOrganization(organization);

            }

            String user_name = (String)userInfo.get("name");
            //user_name =new String(user_name.getBytes(),"UTF-8");
            model.addAttribute("name",user_name);
            model.addAttribute("user_img",(String)userInfo.get("avatar"));
            model.addAttribute("department_name",organization.getJgqc());
            model.addAttribute("serialNo",serialNo);
            model.addAttribute("serialName",serialName);
            model.addAttribute("isManager",isManager);
            model.addAttribute("userId",userId);

        }catch (Exception e){
            e.printStackTrace();
        }


        return "mobile2/enterprise/user-center";
    }


}
