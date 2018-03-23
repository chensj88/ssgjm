package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bytedeco.javacpp.presets.opencv_core;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点安装登记
 *
 * @author ChenKuai
 * @create 2018-03-16 上午 9:36
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/siteInstall")
public class SiteInstallController extends BaseController {

    /**
     * @author: Chen,Kuai
     * @Description: 站点安装登记
     */
    @RequestMapping(value = "/list.do")
    @ILog
    public String siteInstall(Model model, String parameter) {
        EtSiteInstall entity = new EtSiteInstall();
        String parameter2 = "eyJXT1JLTlVNIjoiMTQyMCJ9"; //工号
        String hospcode="11980";  //客户号
        try{
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter2), "UTF-8") + "]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            String work_num=(String) userList.get(0).get("WORKNUM");
            SysUserInfo info = new SysUserInfo();
            info.setUserid(work_num);
            info.setStatus(1);
            info.setUserType("1");  //0医院1公司员工
            info = super.getFacade().getSysUserInfoService().getSysUserInfo(info);
            if(info !=null){
                //根据客户编号 找出对应的全部
                entity.setSerialNo(hospcode);
                List<EtSiteInstall> siteInstalls = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(entity);
                model.addAttribute("siteInstalls",siteInstalls);
            }else{

            }
            model.addAttribute("userId",work_num);
            model.addAttribute("serialNo",hospcode);


        }catch (Exception e){

        }

        return "/mobile/enterprise/site-install";
    }



}
