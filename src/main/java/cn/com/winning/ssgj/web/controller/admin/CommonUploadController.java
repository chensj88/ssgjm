package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.SysDataCheckScript;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.domain.SysUserVideoAuth;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 通用上传信息
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-28 14:08
 */
@Controller
@RequestMapping(value = "/admin/upload")
public class CommonUploadController extends BaseController {
    /**
     * 培训视频上传
     *
     * @param request
     * @param repo
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/tvideo.do")
    @ResponseBody
    @ILog
    public Map<String, Object> uploadTrainVideo(HttpServletRequest request,
                                                SysTrainVideoRepo repo,
                                                MultipartFile uploadFile) throws IOException {
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        String parentFile = repo.generateRemoteFile();
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!uploadFile.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/video/");
            //图片文件路径-
            String imgPath = request.getServletContext().getRealPath("/image/");
            System.out.println(path);
            path += parentFile + File.separator;
            //上传文件名
           /* String filename = FileUtis.generateFileName(uploadFile.getOriginalFilename());*/
            String filename = uploadFile.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //判断路径是否存在，如果不存在就创建一个
            filepath = new File(imgPath, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            File newFile = new File(path + File.separator + filename);
            //删除已经存在文件重新上传
            if (newFile.exists()) {
                newFile.delete();
            }
            uploadFile.transferTo(newFile);
            //图片文件名称
            String img = filename.substring(0, filename.lastIndexOf("."));
            //图片路径
            String imgUploadPath = "/image/" + parentFile + "/" + FileUtis.generateFileName() + ".jpg";
            repo.setImgPath(imgUploadPath);
            //第一帧获取
            VideoUtil.grabberFFmpegImage(imgUploadPath, newFile.getPath(), imgPath, repo.getVideoName());
            repo.setVideoTime(VideoUtil.getVideoTime(newFile));
            String remotePath = "/video/" + parentFile + "/" + filename;
            boolean ftpStatus = false ;
            String msg = "";

            try {
                ftpStatus =CommonFtpUtils.uploadFile(remotePath,newFile);
            } catch (IOException e) {
                msg = e.getMessage();
            }
            if (ftpStatus) {
                newFile.delete();
                result.put("status", "success");
                repo.setRemotePath(remotePath);
                super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                newFile.delete();
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
     * 流程问卷上传
     *
     * @param request
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/flow.do")
    @ResponseBody
    @ILog
    public Map<String, Object> uploadFlowTemplate(HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!uploadFile.isEmpty()) {
            String filename = uploadFile.getOriginalFilename();
            String remotePath = Constants.UPLOAD_PC_PREFIX + "web/template/flow/" + DateUtil.getTimstamp()  + "/" + filename;
            String msg = "";
            boolean ftpStatus =CommonFtpUtils.commonUploadInfo(request,msg,remotePath,uploadFile);
            if (ftpStatus) {
                result.put("status", "success");
                result.put("path", remotePath);
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
     * 数据校验脚本
     *
     * @param request
     * @param script
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/script.do")
    @ResponseBody
    @ILog
    public Map<String, Object> uploadScriptTemplate(HttpServletRequest request,
                                                    SysDataCheckScript script,
                                                    MultipartFile uploadFile) throws IOException {
        script = super.getFacade().getSysDataCheckScriptService().getSysDataCheckScript(script);
        String parentFile = script.getAppName();
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!uploadFile.isEmpty()) {
            // String filename = FileUtis.generateFileName(uploadFile.getOriginalFilename());
            String filename = uploadFile.getOriginalFilename();
            String remotePath =  Constants.UPLOAD_PC_PREFIX + "/web/script/" + parentFile + "/" + filename;
            String msg = "";
            boolean ftpStatus =CommonFtpUtils.commonUploadInfo(request,msg,remotePath,uploadFile);
            if (ftpStatus) {
                result.put("status", "success");
                script.setRemotePath(remotePath);
                super.getFacade().getSysDataCheckScriptService().modifySysDataCheckScript(script);
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

    @RequestMapping(value = "/videoAuth.do")
    @ResponseBody
    @ILog
    public Map<String, Object> uploadHosptial(HttpServletRequest request,
                                                    SysUserVideoAuth auth,
                                                    MultipartFile uploadFile) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!uploadFile.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/temp/");
            //上传文件名
            String filename = FileUtis.generateFileName(uploadFile.getOriginalFilename());
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
            uploadFile.transferTo(newFile);

            try {
                List<List<Object>> userAuth = ExcelUtil.importExcel(newFile.getPath());
                super.getFacade().getSysUserVideoAuthService().generateUserVideoAuthInfo(userAuth,auth);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }


    @RequestMapping(value = "/test.do")
    @ResponseBody
    @ILog
    public Map<String, Object> testUpload(HttpServletRequest request,
                                              MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String msg = "";
            try {
                String remotePath = Constants.UPLOAD_PC_PREFIX+"web/template/" + DateUtil.getTimstamp() + "/" + filename;
                boolean ftpStatus =CommonFtpUtils.commonUploadInfo(request,msg,remotePath,file);
                result.put("status", "success");
                result.put("path", remotePath);
                result.put("url", Constants.FTP_SHARE_FLODER);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }
}
