package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.base.util.FtpPropertiesLoader;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.bytedeco.javacpp.presets.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 站点安装登记
 *
 * @author ChenKuai
 * @create 2018-03-16 上午 9:36
 **/
@Controller
    @RequestMapping("mobile/siteInstall")
public class SiteInstallController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    /**
     * @author: Chen,Kuai
     * @Description: 站点安装登记
     */
    @RequestMapping(value = "/list.do")
    @ILog
    public String siteInstall(Model model, String parameter) {
        EtSiteInstall entity = new EtSiteInstall();
        //String parameter2 = "eyJXT1JLTlVNIjoiMTQyMCJ9"; //工号
        //String hospcode="11980";  //客户号
        parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        try{
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter), "UTF-8") + "]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            String work_num=(String) userList.get(0).get("WORKNUM");
            String hospcode=(String) userList.get(0).get("HOSPCODE ");

            SysUserInfo info = new SysUserInfo();
            info.setUserid(work_num);
            info.setStatus(1);
            info.setUserType("1");  //0医院1公司员工
            info = super.getFacade().getSysUserInfoService().getSysUserInfo(info);
            List<EtSiteInstall> installList = new ArrayList<EtSiteInstall>();
            if(info !=null){
                //根据客户编号 找出对应的全部
                entity.setSerialNo(hospcode);
                installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(entity);


            }else{

            }
            model.addAttribute("installList",installList);
            model.addAttribute("userId",work_num);
            model.addAttribute("serialNo",hospcode);
        }catch (Exception e){

        }

        return "/mobile/enterprise/site-install";
    }

    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
     */
    @RequestMapping(value = "/addAndUpdate.do")
    @ILog
    public String addAndUpdate(Model model, String userId,String serialNo,Long id) {
        EtSiteInstall siteInstall = new EtSiteInstall();

        //根据客户编号 找出对应的全部
        siteInstall.setSerialNo(serialNo);
        siteInstall.setId(id);
        siteInstall = super.getFacade().getEtSiteInstallService().getEtSiteInstall(siteInstall);
        EtSiteInstallDetail installDetail = new EtSiteInstallDetail();
        //特殊处理
        EtSiteInstall install = new EtSiteInstall();
        install.setId(id);
        install = super.getFacade().getEtSiteInstallService().getEtSiteInstall(install);
        //获取安装站点的信息
        installDetail.setSourceId(siteInstall.getId());
        List<EtSiteInstallDetail> siteInstallDetails=new ArrayList<EtSiteInstallDetail>();
        siteInstallDetails = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetailList(installDetail);
        if((siteInstallDetails.size()==0 || siteInstallDetails==null) && siteInstall.getNum() > 0){
           for(int i =0 ;i<siteInstall.getNum();i++){
                EtSiteInstallDetail detail = new EtSiteInstallDetail();
                detail.setId(ssgjHelper.createSiteInstallIdService());
                detail.setSourceId(id);
                super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
           }
           //当为空的时候重新获取 初始化的安装明细信息
            siteInstallDetails=super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetailList(installDetail);
        }

        model.addAttribute("siteInstallDetails",siteInstallDetails);
        model.addAttribute("siteInstall",siteInstall);
        model.addAttribute("userId",userId);
        model.addAttribute("serialNo",serialNo);
        return "/mobile/enterprise/site-add";
    }

    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
     */
    @RequestMapping(value = "/save.do", method ={RequestMethod.POST})
    @ResponseBody
    public void save(EtSiteInstallDetailForm siteInstallDetails) throws Exception{
        List<EtSiteInstallDetail> etSiteInstallDetailList = siteInstallDetails.getEtSiteInstallDetails();
        System.out.println(etSiteInstallDetailList);

    }




}
