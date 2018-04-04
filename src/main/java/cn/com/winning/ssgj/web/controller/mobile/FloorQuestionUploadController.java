package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
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
 * 楼层问题汇报
 *
 * @author ChenKuai
 * @create 2018-03-22 下午 2:09
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/floorQuestionUpload")
public class FloorQuestionUploadController extends BaseController {

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
        parameter = "eyJXT1JLTlVNIjoiNTgyMyIsIkhPU1BDT0RFIjoiMTE5ODAifQ==";
        EtFloorQuestionInfo info = new EtFloorQuestionInfo();
        try {
            String userJsonStr = "[" + new String(Base64Utils.decryptBASE64(parameter), "UTF-8") + "]";
            List<JSONObject> userList = JSON.parseArray(userJsonStr, JSONObject.class);
            String work_num = null;
            String hospcode = null;
            if (userList != null && !userList.equals("")) {
                for (int i = 0; i < userList.size(); i++) { //  推荐用这个
                    JSONObject io = userList.get(i);
                    work_num = (String) io.get("WORKNUM");
                    hospcode = (String) io.get("HOSPCODE");
                }
            }
            info.setSerialNo(hospcode);
            List<EtFloorQuestionInfo> infoList = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfoList(info);
            model.addAttribute("infoList",infoList);
            model.addAttribute("hospcode",hospcode);
            model.addAttribute("work_num",work_num);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "/mobile/enterprise/floor-upload";
    }


    /**
     * @author: Chen,Kuai
     * @Description: 新增楼层问题
     */
    @RequestMapping(value = "/addAndUpdate.do")
    @ILog
    public String addAndUpdate(Model model, String userId,String serialNo,Long id) {
        EtFloorQuestionInfo floorQuestionInfo = new EtFloorQuestionInfo();
        if(id != null && id != 0){
            floorQuestionInfo.setId(id);
            floorQuestionInfo=super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfo(floorQuestionInfo);
            if(StringUtils.isNotBlank(floorQuestionInfo.getImgPath())){
                String[] imgs=floorQuestionInfo.getImgPath().split(";");
                List<String> lists= Arrays.asList(imgs);
                floorQuestionInfo.setImgs(lists);
            }
            model.addAttribute("floorQuestionInfo",floorQuestionInfo);
        }
        SysDictInfo info1 = new SysDictInfo();
        info1.setDictCode("questionType");
        List<SysDictInfo> dictInfos =super.getFacade().getSysDictInfoService().getSysDictInfoList(info1);
        //楼层问题
        SysFloors floors = new SysFloors();
        floors.setSerialNo(Long.parseLong(serialNo));
        List<SysFloors> floorsList= super.getFacade().getSysFloorsService().getSysFloorsList(floors);

        model.addAttribute("floorsList",floorsList);
        model.addAttribute("dictInfos",dictInfos);
        model.addAttribute("floorQuestionInfo",floorQuestionInfo);
        model.addAttribute("userId",userId);
        model.addAttribute("serialNo",serialNo);

        return "/mobile/enterprise/floor-upload-report";
    }

    /**
     * @author: Chen,Kuai
     * @Description: 新增/修改楼层问题
     */
    @RequestMapping(value="/saveData.do", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> saveData(HttpServletRequest request, EtFloorQuestionInfo info, String old_id, String userId) {
        Map<String, String> map = new HashMap<String, String>();
        if(StringUtils.isNotBlank(old_id)){
            info.setId(Long.parseLong(old_id));
            info.setOperator(super.user_id(userId,"1"));
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            info.setCreator(super.user_id(userId,"1"));
            info.setCreateTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtFloorQuestionInfoService().modifyEtFloorQuestionInfo(info);
            map.put("result","1");
        }else{ //新增
            info.setId(ssgjHelper.createFloorQuestionIdService()); //站点问题ID
            info.setCId((long)-2); //11980游客
            info.setPmId((long)-2);//11980游客
            info.setOperator(super.user_id(userId,"1"));
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            info.setCreator(super.user_id(userId,"1"));
            info.setCreateTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtFloorQuestionInfoService().createEtFloorQuestionInfo(info);
            map.put("result","1");
        }

        return map;
    }

    /**
     * @author: Chen,Kuai
     * @Description: 图片上传 新增站点问题
     */
    @RequestMapping(value="/saveAndUpdate.do", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveAndUpdate(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        Map<String,Object> map = new HashMap<String,Object>();
        String userId = request.getParameter("userId");
        String old_id = request.getParameter("old_id");
        String serialNo = request.getParameter("serialNo");
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
                EtFloorQuestionInfo info = new EtFloorQuestionInfo();
                if(StringUtils.isNotBlank(old_id)){
                    info.setId(Long.parseLong(old_id));
                    info = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfo(info);
                    if(StringUtils.isNotBlank(info.getImgPath())){
                        info.setImgPath(info.getImgPath()+";"+ remotePath);//拼接图片路径
                    }else{
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(super.user_id(userId,"1"));
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtFloorQuestionInfoService().modifyEtFloorQuestionInfo(info);
                    map.put("id",String.valueOf(old_id));
                    map.put("status","1");
                }else{  //新增一个对象
                    if(ftpStatus){
                        //生成系统预设 ID
                        long id = ssgjHelper.createFloorQuestionIdService();
                        info.setId(id); //站点问题ID
                        info.setCId((long)-2); //11980游客
                        info.setPmId((long)-2);//11980游客
                        info.setSerialNo(serialNo);
                        info.setImgPath(remotePath);
                        info.setCreator(super.user_id(userId,"1"));
                        info.setCreateTime(new Timestamp(new Date().getTime()));
                        info.setOperator(super.user_id(userId,"1"));
                        info.setOperatorTime(new Timestamp(new Date().getTime()));
                        super.getFacade().getEtFloorQuestionInfoService().createEtFloorQuestionInfo(info);
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
     * Chen,Kuai 删除图片
     * @param id
     * @return
     */
    @RequestMapping("/deleteImg.do")
    @ResponseBody
    @ILog
    public Map<String,Boolean> deleteImg(Long id,String imgPath){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        EtFloorQuestionInfo info = new EtFloorQuestionInfo();
        info.setId(id);
        try{
            info = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfo(info);
            if (port == 21) {
                FtpUtils.deleteFtpFile(imgPath);
            } else if (port == 22) {
                SFtpUtils.rmFile(imgPath);
            }
            if(StringUtils.isBlank(info.getFloorName())){
                super.getFacade().getEtFloorQuestionInfoService().removeEtFloorQuestionInfo(info);
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
                super.getFacade().getEtFloorQuestionInfoService().modifyEtFloorQuestionInfo(info);
            }
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }


}
