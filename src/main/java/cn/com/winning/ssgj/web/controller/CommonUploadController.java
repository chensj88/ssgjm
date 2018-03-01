package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.util.FtpUtils;
import cn.com.winning.ssgj.domain.SysOrganization;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
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
public class CommonUploadController {

    @RequestMapping(value = "/tvideo.do")
    @ResponseBody
    public Map<String,Object> uploadTrainVideo(HttpServletRequest request,
                                               SysTrainVideoRepo repo,
                                               MultipartFile uploadFile) throws IOException {
        String parentFile = repo.getVideoType();
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
            uploadFile.transferTo(newFile);
            String remoteFile = "/video/" + parentFile + "/" + filename;
            FtpUtils.uploadFile(remoteFile, newFile);
            newFile.delete();
            result.put("status", "success");
            result.put("filePath", remoteFile);
            result.put("data", repo);
        } else {
            result.put("status", "error");
        }
        return result;
    }
}
