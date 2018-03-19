package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.base.util.FtpPropertiesLoader;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点问题与楼层问题汇报
 *
 * @author ChenKuai
 * @create 2018-03-16 上午 11:03
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/siteQuestion")
public class SiteQuestionController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    /**
     * @author: Chen,Kuai
     * @Description: 站点问题信息
     */
    @RequestMapping(value = "/list.do")
    @ILog
    public String SiteQuestionList(Model model, String parameter) {
        String parameter2 = "eyJXT1JLTlVNIjoiMTQyMCJ9"; //工号
        String hospcode="11980";  //客户号
        EtSiteQuestionInfo entity = new EtSiteQuestionInfo();
        try{
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter2), "UTF-8") + "]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            String work_num=(String) userList.get(0).get("WORKNUM");
            entity.setSerialNo(hospcode);
            List<EtSiteQuestionInfo> siteQuestionInfoList=super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoList(entity);
            model.addAttribute("siteQuestionInfoList",siteQuestionInfoList);
            model.addAttribute("hospcode",hospcode);
            model.addAttribute("work_num",work_num);

        }catch (Exception e){

        }
        return "/mobile/enterprise/site-question";
    }

    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
     */
    @RequestMapping(value = "/addAndUpdate.do")
    @ILog
    public String addAndUpdate(Model model, String userId,String serialNo) {

        EtSiteInstall install = new EtSiteInstall();
        PmisContractProductInfo contractProductInfo = new PmisContractProductInfo();
        //获取站点信息(科室名称，)
        install.setSerialNo(serialNo);
        List<EtSiteInstall> installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(install);
        //菜单名称
        //获取合同/任务单(系统名称，菜单名称)
        contractProductInfo.setKhxx(Long.parseLong(serialNo));
        List<PmisContractProductInfo> contractProductInfos = super.getFacade().getPmisContractProductInfoService()
                .getPmisContractProductInfoMkList(contractProductInfo);

        model.addAttribute("contractProductInfos",contractProductInfos);
        model.addAttribute("installList",installList);

        return "/mobile/enterprise/site-question-write";
    }





}
