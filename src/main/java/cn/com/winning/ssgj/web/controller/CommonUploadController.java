package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.util.FtpPropertiesLoader;
import cn.com.winning.ssgj.base.util.FtpUtils;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-28 14:08
 */
@Controller
@RequestMapping(value = "/admin/upload")
public class CommonUploadController extends BaseController {

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    @RequestMapping(value = "/tvideo.do")
    @ResponseBody
    public Map<String,Object> uploadTrainVideo(HttpServletRequest request,
                                               SysTrainVideoRepo repo,
                                               MultipartFile uploadFile) throws IOException {
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        String parentFile = repo.generateRemoteFile();
        Map<String,Object> result = new HashMap<String,Object>();
        //如果文件不为空，写入上传路径
        if(!uploadFile.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/video/");
            System.out.println(path);
            path +=  parentFile + File.separator;
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
            repo.setVideoTime(getVideoTime(newFile));
            String remotePath = "/video/" + parentFile + "/" + filename;
            String remoteDir ="/video/" + parentFile + "/";
            boolean ftpStatus = false;
            String msg = "";
            if (port == 21){
                try {
                    ftpStatus = FtpUtils.uploadFile(remotePath, newFile);
                }catch (IOException e){
                    msg = e.getMessage();
                }
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
                newFile.delete();
                result.put("status", "success");
                repo.setRemotePath(remotePath);
                super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
            }else if(!StringUtil.isEmptyOrNull(msg)){
                newFile.delete();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+msg);
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }

    /**
     * 获取上传视频的时间，取毫秒数
     * @param file
     * @return
     */
    private Long getVideoTime(File file) {
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return m.getDuration();
    }
}
