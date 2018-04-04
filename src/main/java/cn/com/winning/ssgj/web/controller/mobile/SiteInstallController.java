package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

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
        //parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
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
        }else{
            //存在集合 获取图片信息
            for (EtSiteInstallDetail detail: siteInstallDetails) {
                if(StringUtils.isNotBlank(detail.getImgPath())){
                    String[] imgs=detail.getImgPath().split(";");
                    List<String> lists= Arrays.asList(imgs);
                    detail.setImgs(lists);
                }
            }
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
    public ModelAndView save(EtSiteInstallDetailForm siteInstallDetails,Long parentId,String[] install_array) throws Exception{
        List<EtSiteInstallDetail> etSiteInstallDetailList = siteInstallDetails.getEtSiteInstallDetails();
        if(etSiteInstallDetailList.size() > 0 && etSiteInstallDetailList != null){
            for(int i=0; i < etSiteInstallDetailList.size();i++){
                etSiteInstallDetailList.get(i).setInstall(Integer.parseInt(install_array[i]));
                super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(etSiteInstallDetailList.get(i));

            }
        }
        EtSiteInstall install = new EtSiteInstall();
        install.setId(parentId);
        install = super.getFacade().getEtSiteInstallService().getEtSiteInstall(install);
        String userId= super.id_user(install.getCreator(),"1");
        return  new ModelAndView("redirect:addAndUpdate.do?id="+parentId+"&userId="+userId+"&serialNo="+install.getSerialNo());

    }

    /**
     * @author: Chen,Kuai
     * @Description: 上传图片
     */
    @RequestMapping(value="/saveAndUpdate.do", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveAndUpdate(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        Map<String,Object> map = new HashMap<String,Object>();
        String serialNo = request.getParameter("serialNo");
        String userId = request.getParameter("userId");
        String old_id = request.getParameter("old_id");

        try{
            if(!uploadFile.isEmpty()) {
                String path = request.getServletContext().getRealPath("/onlineFile/");
                //上传文件名
                String filename = uploadFile.getOriginalFilename();
                File filepath = new File(path,filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                //将上传文件保存到一个目标文件当中
                File newFile = new File(path + File.separator + filename);
                if(newFile.exists()){
                    newFile.delete();
                }
                uploadFile.transferTo(newFile);
                String remotePath = "/onlineFile/"+ filename;
                String remoteDir ="/onlineFile/" ;
                boolean ftpStatus = false;
                String msg = "";
                if (port == 21){
                    ftpStatus = FtpUtils.uploadFile(remotePath, newFile);
                }else if(port == 22){
                    try {
                        SFtpUtils.uploadFile(newFile.getPath(),remoteDir,filename);
                        ftpStatus = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        ftpStatus = false;
                        msg = e.getMessage();
                    }
                }
                EtSiteInstallDetail info = new EtSiteInstallDetail();
                if(StringUtils.isNotBlank(old_id)){
                    info.setId(Long.parseLong(old_id));
                    info = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(info);
                    if(StringUtils.isNotBlank(info.getImgPath())){
                        info.setImgPath(info.getImgPath()+";"+ remotePath);//拼接图片路径
                    }else{
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(super.user_id(userId,"1"));
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(info);
                    map.put("id",String.valueOf(old_id));
                    map.put("status","1");
                }

                map.put("path",remotePath);
            }else {
                map.put("status","0");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("status","0");
        }
        return map;
    }


    /**
     * Chen,Kuai 删除图片
     * @param id
     * @return
     */
    @RequestMapping("/deleteImg.do")
    @ResponseBody
    @ILog
    public Map<String,Boolean> deleteImg(Long id,String imgPath){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        EtSiteInstallDetail info = new EtSiteInstallDetail();
        info.setId(id);
        try{
            info = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(info);
            if (port == 21) {
                FtpUtils.deleteFtpFile(imgPath);
            } else if (port == 22) {
                SFtpUtils.rmFile(imgPath);
            }
            String[] imgs=info.getImgPath().split(";");
            String str="";
            for(int i = 0; i < imgs.length; i++) {
                if(imgPath.equals(imgs[i])){
                    info.setImgPath("");
                }else{
                    str +=imgs[i]+";";
                    info.setImgPath(str.substring(0,str.length()-1));
                }
            }

            super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(info);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }

    /**
     * @author: Chen,Kuai
     * @Description: 删除节点
     */
    @RequestMapping("/deleteItem.do")
    @ResponseBody
    @ILog
    public Map<String,Boolean> deleteItem(Long id){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        EtSiteInstallDetail info = new EtSiteInstallDetail();
        info.setId(id);
        try{
            super.getFacade().getEtSiteInstallDetailService().removeEtSiteInstallDetail(info);
            map.put("status",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }

}
