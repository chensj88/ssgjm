package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;

/**
 * 实施资料汇总上线可行性方案表
 *
 * @author ChenKuai
 * @create 2018-03-10 上午 11:37
 **/
@Controller
@CrossOrigin
@RequestMapping("mobile/implementData")
public class OnlineFileController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();


    @RequestMapping(value = "/list.do")
    @ILog
    public String floorQuestionList(Model model, String parameter) {

        EtOnlineFile onlineFile = new  EtOnlineFile();
        //parameter="eyJXT1JLTlVNIjoiMTQyMCwiSE9TUENPREUiOiIxMTk4MCJ9";
        try{
            byte[] byteArray = Base64Utils.decryptBASE64(parameter);
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
            //获取用户的信息
            SysUserInfo info = new SysUserInfo();
            info.setUserid(work_num);
            info.setStatus(1);
            info.setUserType("1");  //0医院1公司员工
            info = super.getFacade().getSysUserInfoService().getSysUserInfo(info);
            if(info !=null){
                //根据客户编号 找出对应的全部
                onlineFile.setSerialNo(hospcode);
                onlineFile.setFileType("1");
                List<EtOnlineFile> onlineFileList_one = super.getFacade().getEtOnlineFileService().getEtOnlineFileList(onlineFile);
                onlineFile.setFileType("2");
                List<EtOnlineFile> onlineFileList_two = super.getFacade().getEtOnlineFileService().getEtOnlineFileList(onlineFile);
                onlineFile.setFileType("3");
                List<EtOnlineFile> onlineFileList_three = super.getFacade().getEtOnlineFileService().getEtOnlineFileList(onlineFile);
                onlineFile.setFileType("4");
                List<EtOnlineFile> onlineFileList_four = super.getFacade().getEtOnlineFileService().getEtOnlineFileList(onlineFile);
                model.addAttribute("onlineFileList_one",onlineFileList_one);
                model.addAttribute("onlineFileList_two",onlineFileList_two);
                model.addAttribute("onlineFileList_three",onlineFileList_three);
                model.addAttribute("onlineFileList_four",onlineFileList_four);
            }else{

            }
            model.addAttribute("userId",work_num);
            model.addAttribute("serialNo",hospcode);

        }catch (Exception e){
            e.printStackTrace();
        }


        return "/mobile/enterprise/data-upload";
    }

    @RequestMapping("/details.do")
    @ILog
    public String fileDetails(Model model, String fileType,String serialNo,String userId) {
        EtOnlineFile info = new EtOnlineFile();
        info.setFileType(fileType);
        info.setSerialNo(serialNo);
        List<EtOnlineFile> onlineFiles =super.getFacade().getEtOnlineFileService().getEtOnlineFileList(info);
        //获取文件的类型
        SysDictInfo info1 = new SysDictInfo();
        info1.setDictCode("FileType");
        List<SysDictInfo> dictInfos =super.getFacade().getSysDictInfoService().getSysDictInfoList(info1);
        model.addAttribute("dictInfos",dictInfos);
        model.addAttribute("onlineFiles",onlineFiles);
        model.addAttribute("serialNo",serialNo);
        model.addAttribute("userId",userId);
        model.addAttribute("fileType",fileType);
        return "/mobile/enterprise/data-upload-report";
    }


    /**
     * Chen,Kuai 上传图片
     */
    @RequestMapping(value="/uploadImgAjax.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Boolean> uploadImgAjax (HttpServletRequest request,@RequestParam MultipartFile uploadFile){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        String serialNo = request.getParameter("serialNo");
        String userId = request.getParameter("userId");
        String fileType = request.getParameter("fileType");
        String dataName = request.getParameter("dataName");
        String dataType = request.getParameter("dataType");
        EtOnlineFile info = new EtOnlineFile();

        try{
            if(!uploadFile.isEmpty()) {
                //上传文件路径
                String path = request.getServletContext().getRealPath("/onlineFile/");
                System.out.println(path);

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
                if(ftpStatus){
                    SysUserInfo userInfo = new SysUserInfo();
                    userInfo.setUserid(userId+"");//员工编码
                    userInfo.setStatus(1);
                    userInfo.setUserType("0");//0医院
                    List<SysUserInfo> userInfoList = super.getFacade().getSysUserInfoService().getSysUserInfoList(userInfo);
                    //上传资料 1.生成一条记录
                    //2.修改原来图片路径
                    info.setId(ssgjHelper.createOnlineFileIdService());
                    info.setcId((long)-2);    //移动端
                    info.setPmId((long)-2);
                    info.setSerialNo(serialNo);//客户编码
                    info.setImgPath(remotePath);//图片路径
                    info.setCreator((long)100193);
                    info.setCreateTime(new Timestamp(new Date().getTime()));
                    info.setFileType(fileType);
                    info.setDataName(dataName);
                    info.setDataType(dataType);
                    super.getFacade().getEtOnlineFileService().createEtOnlineFile(info);
                    map.put("status",true);

                }else if(!StringUtil.isEmptyOrNull(msg)){
                    map.put("status",false);

                }
            } else {
                map.put("status",false);
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
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
    public Map<String,Boolean> deleteImg(Long id){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        EtOnlineFile info = new EtOnlineFile();
        info.setId(id);
        try{
            info = super.getFacade().getEtOnlineFileService().getEtOnlineFile(info);
            if (port == 21) {
                    FtpUtils.deleteFtpFile(info.getImgPath());
            } else if (port == 22) {
                    SFtpUtils.rmFile(info.getImgPath());
            }
            super.getFacade().getEtOnlineFileService().removeEtOnlineFile(info);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }


}

