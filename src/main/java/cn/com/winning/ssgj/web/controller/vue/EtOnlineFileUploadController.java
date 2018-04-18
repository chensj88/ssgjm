package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.UrlContent;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 上线评估报告和切换方案上传文件处理Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-18 10:13
 */
@Controller
@RequestMapping( value = "/vue/onLineReport")
@CrossOrigin
public class EtOnlineFileUploadController extends BaseController {


    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 上线评估报告
     * @param request
     * @param onlineFile
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadOnline.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> uploadOnline(HttpServletRequest request, EtOnlineFile onlineFile, MultipartFile file) throws IOException {
        long operator = onlineFile.getOperator();
        String parentFile = "online_"+System.currentTimeMillis();
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String remotePath = "/online/" + parentFile + "/" + filename;
            String msg = "";
            boolean ftpStatus =commonUploadInfo(request,msg,remotePath,file);
            //上传文件处理
            UrlContent urlContent = generateUrlContent(remotePath,onlineFile,filename);
            JSONArray jsonArray = new JSONArray();
            if (ftpStatus) {
                onlineFile.setOperator(null);
                onlineFile = super.getFacade().getEtOnlineFileService().getEtOnlineFile(onlineFile);
                String  filePath = onlineFile.getFileSuggestPath();
                if(StringUtil.isEmptyOrNull(filePath) ){
                    jsonArray.add(urlContent);
                    onlineFile.setFileSuggestPath(jsonArray.toJSONString());
                }else{
                    List<UrlContent> array = JSONArray.parseArray(filePath,UrlContent.class);
                    array.add(urlContent);
                    onlineFile.setFileSuggestPath(JSON.toJSONString(array));
                }
                onlineFile.setOperator(operator);
                onlineFile.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(onlineFile);
                result.put("status", "success");
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + msg);
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;

    }
    /**
     * 切换方案
     * @param request
     * @param onlineFile
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadSwitch.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> uploadSwitch(HttpServletRequest request, EtOnlineFile onlineFile, MultipartFile file) throws IOException {
        long operator = onlineFile.getOperator();
        String parentFile = "switch"+System.currentTimeMillis();
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String remotePath = "/switch/" + parentFile + "/" + filename;
            String msg = "";
            //上传文件
            boolean ftpStatus =commonUploadInfo(request,msg,remotePath,file);
            //上传文件处理
            UrlContent urlContent = generateUrlContent(remotePath,onlineFile,filename);
            JSONArray jsonArray = new JSONArray();
            if (ftpStatus) {
                onlineFile.setOperator(null);
                onlineFile = super.getFacade().getEtOnlineFileService().getEtOnlineFile(onlineFile);
                String  filePath = onlineFile.getFileChangePath();
                if(StringUtil.isEmptyOrNull(filePath) ){
                    jsonArray.add(urlContent);
                    onlineFile.setFileChangePath(jsonArray.toJSONString());
                }else{
                    List<UrlContent> array = JSONArray.parseArray(filePath,UrlContent.class);
                    array.add(urlContent);
                    onlineFile.setFileChangePath(JSON.toJSONString(array));
                }
                onlineFile.setOperator(operator);
                onlineFile.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(onlineFile);
                result.put("status", "success");
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + msg);
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }

    /**
     * 切换方案图片
     * @param request
     * @param onlineFile
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadSwitchImg.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> uploadSwitchImg(HttpServletRequest request, EtOnlineFile onlineFile, MultipartFile file) throws IOException {
        long operator = onlineFile.getOperator();
        String parentFile = "switchImg"+System.currentTimeMillis();
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String remotePath = "/switchImg/" + parentFile + "/" + filename;
            String msg = "";
            //上传文件
            boolean ftpStatus =commonUploadInfo(request,msg,remotePath,file);
            //上传文件处理
            UrlContent urlContent = generateUrlContent(remotePath,onlineFile,filename);
            JSONArray jsonArray = new JSONArray();
            if (ftpStatus) {
                onlineFile.setOperator(null);
                onlineFile = super.getFacade().getEtOnlineFileService().getEtOnlineFile(onlineFile);
                String  filePath = onlineFile.getImgPath();
                if(StringUtil.isEmptyOrNull(filePath) ){
                    jsonArray.add(urlContent);
                    onlineFile.setImgPath(jsonArray.toJSONString());
                }else{
                    List<UrlContent> array = JSONArray.parseArray(filePath,UrlContent.class);
                    array.add(urlContent);
                    onlineFile.setImgPath(JSON.toJSONString(array));
                }
                onlineFile.setOperator(operator);
                onlineFile.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(onlineFile);
                result.put("status", "success");
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + msg);
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }
    /**
     * 生成新的URL内容
     * @param remotePath
     * @param onlineFile
     * @param filename
     * @return
     */
    private  UrlContent generateUrlContent(String remotePath,EtOnlineFile onlineFile,String filename){
        String url = Constants.FTP_SHARE_FLODER + remotePath;
        SysUserInfo user = super.getFacade().getSysUserInfoService().getSysUserInfoById(onlineFile.getOperator());
        return new UrlContent(ssgjHelper.createUrlContentIdService(),onlineFile.getId(),filename,url,user.getName(), DateUtil.getCurrentDayByFormatter());
    }

    private boolean  commonUploadInfo(HttpServletRequest request,String msg,String remotePath,MultipartFile file) throws IOException {
        boolean ftpStatus = false;
        //上传文件路径
        String path = request.getServletContext().getRealPath("/temp/");
        //上传文件名
        String filename = file.getOriginalFilename();
        File filepath = new File(path, filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        File newFile = new File(path + File.separator + filename);
        if (newFile.exists()) {
            newFile.delete();
        }
        file.transferTo(newFile);
        try {
            ftpStatus = CommonFtpUtils.uploadFile(remotePath,newFile);
        }catch (IOException e){
            msg = e.getMessage();
            ftpStatus = false;
        }
        newFile.delete();
        return ftpStatus;
    }

}
