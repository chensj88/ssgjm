package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.font.CIDSystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

/**
 * 站点问题与楼层问题汇报
 *
 * @author ChenKuai
 * @create 2018-03-16 上午 11:03
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/siteQuestionInfo")
public class SiteQuestionController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    /**
     * @author: Chen,Kuai
     * @Description: 站点问题信息
     */
    @RequestMapping(value = "/list.do")
    public String SiteQuestionList(Model model, String parameter) {
        //String parameter2 = "eyJXT1JLTlVNIjoiMTQyMCJ9"; //工号
        //String hospcode="11980";  //客户号
        //parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        EtSiteQuestionInfo entity = new EtSiteQuestionInfo();
        try{
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter), "UTF-8") + "]";
            List<JSONObject> userList = JSON.parseArray(userJsonStr,JSONObject.class);
            String work_num =null;
            String hospcode =null;
            if (userList != null && !userList.equals("")) {
                for (int i = 0; i < userList.size(); i++) { //  推荐用这个
                    JSONObject io = userList.get(i);
                    work_num =(String) io.get("WORKNUM");
                    hospcode=(String) io.get("HOSPCODE");
                }
            }


            entity.setSerialNo(hospcode);
            List<EtSiteQuestionInfo> siteQuestionInfoList=super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoList(entity);
            //获取项目成员信息
            EtUserInfo userInfo = new EtUserInfo();
            //项目少于15人注销
            //userInfo.setPositionName("1");//项目经理+主力工程师
            userInfo.getMap().put("position","'1','0'");//项目经理+主力工程师
            userInfo.setSerialNo(hospcode);
            userInfo.setUserType(1);
            List<EtUserInfo> infos = super.getFacade().getEtUserInfoService().getEtUserInfoList(userInfo);

            model.addAttribute("infos",infos);
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
    public String addAndUpdate(Model model, String userId,String serialNo,Long id) {
        EtSiteQuestionInfo siteQuestionInfo = new EtSiteQuestionInfo();
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
        //获取站点信息(科室名称，)
        install.setSerialNo(serialNo);
        List<EtSiteInstall> installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(install);

        //获取文件的类型
        SysDictInfo info1 = new SysDictInfo();
        info1.setDictCode("questionType");
        List<SysDictInfo> dictInfos =super.getFacade().getSysDictInfoService().getSysDictInfoList(info1);
        //获取站点科室
        model.addAttribute("dictInfos",dictInfos);
        model.addAttribute("installList",installList);
        model.addAttribute("userId",userId);
        model.addAttribute("serialNo",serialNo);
        return "/mobile/enterprise/site-question-write";
    }


    /**
     * @author: Chen,Kuai
     * @Description: 新增站点问题
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
                String pathLu= Constants.UPLOAD_PC_PREFIX+serialNo+"/site/";
                String path = request.getServletContext().getRealPath(pathLu);

                //上传文件名
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis()+"."+StringUtils.substringAfterLast(filename,".");
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
                String remotePath = pathLu+ filename;
                String remoteDir =pathLu ;
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
                EtSiteQuestionInfo info = new EtSiteQuestionInfo();
                if(StringUtils.isNotBlank(old_id)){
                    info.setId(Long.parseLong(old_id));
                    info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
                    if(StringUtils.isNotBlank(info.getImgPath())){
                        info.setImgPath(info.getImgPath()+";"+ remotePath);//拼接图片路径
                    }else{
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(super.user_id(userId,"1"));
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    map.put("id",String.valueOf(old_id));
                    map.put("status","1");
                }else{  //新增一个对象
                    if(ftpStatus){
                        //生成系统预设 ID
                        long id = ssgjHelper.createSiteQuestionIdService();
                        info.setId(id); //站点问题ID
                        info.setCId((long)-2); //11980游客
                        info.setPmId((long)-2);//11980游客
                        info.setSerialNo(serialNo);
                        info.setImgPath(remotePath);
                        info.setCreator(super.user_id(userId,"1"));
                        info.setCreateTime(new Timestamp(new Date().getTime()));
                        info.setOperator(super.user_id(userId,"1"));
                        info.setOperatorTime(new Timestamp(new Date().getTime()));
                        super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
                        map.put("status","1");
                        map.put("id",String.valueOf(id));

                    }else if(!StringUtil.isEmptyOrNull(msg)){
                        map.put("status","0");
                    }
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
 * @author: Chen,Kuai
 * @Description: 新增/修改站点问题
 */
    @RequestMapping(value="/saveData.do", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> saveData(HttpServletRequest request, EtSiteQuestionInfo info,String old_id,String userId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if(StringUtils.isNotBlank(old_id)){
                info.setId(Long.parseLong(old_id));
                info.setCreator(super.user_id(userId,"1"));
                info.setCreateTime(new Timestamp(new Date().getTime()));
                info.setOperator(super.user_id(userId,"1"));
                info.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                map.put("result","1");
            }else{ //新增
                info.setId(ssgjHelper.createSiteQuestionIdService()); //站点问题ID
                info.setCId((long)-2); //11980游客
                info.setPmId((long)-2);//11980游客
                info.setCreator(super.user_id(userId,"1"));
                info.setCreateTime(new Timestamp(new Date().getTime()));
                info.setOperator(super.user_id(userId,"1"));
                info.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
                map.put("result","1");

            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("result","0");
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
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setId(id);
        try{
            info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            if (port == 21) {
                FtpUtils.deleteFtpFile(imgPath);
            } else if (port == 22) {
                SFtpUtils.rmFile(imgPath);
            }
            if(StringUtils.isBlank(info.getSiteName())){
                super.getFacade().getEtSiteQuestionInfoService().removeEtSiteQuestionInfo(info);
            }else{

                String[] imgs=info.getImgPath().split(";");
                String str="";
                for(int i = 0; i < imgs.length; i++) {
                    if(imgPath.equals(imgs[i])){

                    }else{
                        str +=imgs[i]+";";
                    }
                }
                info.setImgPath(str.substring(0,str.length()-1));
                super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
            }
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }


    /**
     * Chen,Kuai 分配人员
     * @param id
     * @return
     */
    @RequestMapping("/allocateUser.do")
    @ResponseBody
    @ILog
    public Map<String,Boolean> allocateUser(Long id,Long allocateUser){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setId(id);
        info.setAllocateUser(allocateUser);
        try{
            super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }


    /**
     * Chen,Kuai 响应数据
     * @param
     * @return
     */
    @RequestMapping("/loadData.do")
    @ResponseBody
    @ILog
    public Map<String,Object> loadData(String type,String deptCode,String serialNo){
        Map<String,Object> map = new HashMap<String,Object>();
        PmisContractProductInfo contractProductInfo = new PmisContractProductInfo();
        try{
            if("1".equals(type)){  //系统站点
                //获取合同/任务单(系统名称，菜单名称)
                //从工作站点安装中直接获取使用系统
                EtSiteInstall info = new EtSiteInstall();
                info.setDeptCode(deptCode);
                info.setSerialNo(serialNo);
                List<EtSiteInstall> infoList = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(info);
                //List<EtSiteInstall> infoList = super.getFacade().getEtSiteInstallService().getEtSiteInstallNameList(info);
                //List<PmisProductLineInfo> infos = super.getFacade().getPmisProductLineInfoService().getPmisProductLineInfoMobileList(infoList.get(0).getPdId());
                PmisProductLineInfo lineInfo = new PmisProductLineInfo();
                lineInfo.getMap().put("softNameList",infoList.get(0).getPdId());
                List<PmisProductLineInfo> lineInfoList = super.getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(lineInfo);
                JSONArray xtJsons = JSONArray.fromObject(lineInfoList);
                map.put("xtJsons",xtJsons);
            }else{ //已停用
                contractProductInfo.setKhxx(Long.parseLong(serialNo));
                contractProductInfo.setZxtmc(deptCode);
                List<PmisContractProductInfo> contractProductInfos = super.getFacade().getPmisContractProductInfoService()
                        .getPmisContractProductInfoMkList(contractProductInfo);
                JSONArray xtJsons = JSONArray.fromObject( contractProductInfos );
                map.put("xtJsons",xtJsons);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


}
