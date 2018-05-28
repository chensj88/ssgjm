package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查看与分配
 *
 * @author ChenKuai
 * @create 2018-03-24 上午 9:57
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/siteInstallSet")
public class siteInstallSetController  extends BaseController {

    @RequestMapping(value = "/list.do")
    @ILog
    public String siteInstallSetList(Model model, String parameter) {
        //parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";

        EtSiteQuestionInfo entity = new EtSiteQuestionInfo();
        try{
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter), "UTF-8") + "]";
            List<JSONObject> userList = JSON.parseArray(userJsonStr,JSONObject.class);
            String work_num = null;
            String hospcode = null;
            if (userList != null && !userList.equals("")) {
                for (int i = 0; i < userList.size(); i++) { //  推荐用这个
                    JSONObject io = userList.get(i);
                    hospcode=(String) io.get("HOSPCODE");
                    work_num =(String) io.get("WORKNUM");
                }
            }

            entity.setSerialNo(hospcode);
            int num = 0; //已解决数量
            List<EtSiteQuestionInfo> siteQuestionInfoList=super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoList(entity);
            List<EtSiteQuestionInfo> siteQuestionInfoFinish = new ArrayList<EtSiteQuestionInfo>();//已解决
            List<EtSiteQuestionInfo> siteQuestionInfoUn = new ArrayList<EtSiteQuestionInfo>();//未解决

            if (siteQuestionInfoList != null) {
                for (int i = 0; i < siteQuestionInfoList.size(); i++) {
                    if(siteQuestionInfoList.get(i).getIsOperation() == 1){
                        num += 1;
                        siteQuestionInfoFinish.add(siteQuestionInfoList.get(i));//已解决
                    }else{
                        siteQuestionInfoUn.add(siteQuestionInfoList.get(i));
                    }
                }


            }
            //获取项目成员信息
            EtUserInfo userInfo = new EtUserInfo();
            //项目少于15人注销
            userInfo.setPositionName("1");
            userInfo.setSerialNo(hospcode);
            userInfo.setUserType(1);
            List<EtUserInfo> infos = super.getFacade().getEtUserInfoService().getEtUserInfoList(userInfo);
            //全部视频内容
            model.addAttribute("siteQuestionInfoList",siteQuestionInfoList);
            model.addAttribute("size", siteQuestionInfoList.size());//全部数量
            model.addAttribute("siteQuestionInfoFinish",siteQuestionInfoFinish);//已解决
            model.addAttribute("siteQuestionInfoUn",siteQuestionInfoUn);//未解决
            model.addAttribute("num",num);//已解决数量
            model.addAttribute("hospcode",hospcode);
            model.addAttribute("work_num",work_num);
            model.addAttribute("infos",infos);

        }catch (Exception e){

        }
        return "/mobile/enterprise/check-distribution01";

    }


    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
     */
    @RequestMapping(value = "/addAndUpdate.do")
    @ILog
    public String addAndUpdate(Model model, String userId,String serialNo,Long id) {
        EtSiteQuestionInfo siteQuestionInfo = new EtSiteQuestionInfo();
        PmisContractProductInfo contractProductInfo = new PmisContractProductInfo();
        EtSiteInstall install = new EtSiteInstall();

        if(id != null && id != 0){
            siteQuestionInfo.setId(id);
            siteQuestionInfo=super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(siteQuestionInfo);
            if(StringUtils.isNotBlank(siteQuestionInfo.getImgPath())){
                String[] imgs=siteQuestionInfo.getImgPath().split(";");
                List<String> lists= Arrays.asList(imgs);
                siteQuestionInfo.setImgs(lists);
            }
            model.addAttribute("siteQuestionInfo",siteQuestionInfo);
        }

        //获取文件的类型
        SysDictInfo info1 = new SysDictInfo();
        info1.setDictCode("questionType");
        List<SysDictInfo> dictInfos =super.getFacade().getSysDictInfoService().getSysDictInfoList(info1);
        model.addAttribute("dictInfos",dictInfos);
        model.addAttribute("userId",userId);
        model.addAttribute("serialNo",serialNo);
        return "/mobile/enterprise/check-distribution02";
    }


    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
     */
    @RequestMapping(value = "/setPerson.do")
    @ILog
    public String setPerson(Model model, String userId,String serialNo,Long id,String siteName) {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setSerialNo(serialNo);
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoUserTotal(info);
        //调出对应的信息
        EtSiteQuestionInfo info_old = new EtSiteQuestionInfo();
        info_old.setId(id);
        info_old = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info_old);


        //人员信息
        List<String> nameList= new ArrayList<String>();
        List<Integer> numList= new ArrayList<Integer>();
        if(infoList != null && infoList.size() > 0){
            for (EtSiteQuestionInfo en:infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        String jsonName = JSON.toJSONString(nameList);
        model.addAttribute("numList",numList);
        model.addAttribute("jsonName",jsonName);
        model.addAttribute("infoList",infoList);
        model.addAttribute("siteName",siteName);
        model.addAttribute("info_old",info_old);

        return "/mobile/enterprise/check-distribution03";
    }



}
