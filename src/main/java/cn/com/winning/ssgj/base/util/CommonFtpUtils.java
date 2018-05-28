package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author chenshijie
 * @title 通用FTP操作 包含ftp和sftp两种
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-04-10 16:32
 */
public class CommonFtpUtils {

   private static Logger logger = LoggerFactory.getLogger(CommonFtpUtils.class);
    /**
     * 通用上传处理
     * @param remotePath 文件存储在远程的全路径
     * @param newFile  文件
     * @return ftpStatus
     * @throws IOException
     */
    public static boolean  uploadFile(String remotePath, File newFile) throws IOException {
        String remoteDir = remotePath.substring(0,remotePath.lastIndexOf("/")+1);
        String filename = remotePath.substring(remotePath.lastIndexOf("/")+1);
        boolean ftpStatus = false;
        if (Constants.FTP_PORT == 21) {
            try {
                ftpStatus = FtpUtils.uploadFile(remotePath, newFile);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        } else if (Constants.FTP_PORT == 22) {
            try {
                SFtpUtils.uploadFile(newFile.getPath(), remoteDir, filename);
                ftpStatus = true;
            } catch (Exception e) {
                e.printStackTrace();
                ftpStatus = false;
                throw new IOException(e.getMessage());
            }
        }

        return ftpStatus;
    }

    /**
     * 删除FTP上面的文件
     * @param source
     * @return
     */
    public static boolean removeUploadFile(String source) {
        logger.info("====开启FTP服务器删除文件功能====");
        logger.info("删除FTP文件：[{}]",source);
        boolean ftpStatus = false;
        if (Constants.FTP_PORT == 21) {
            try {
                FtpUtils.deleteFtpFile(source);
                ftpStatus = true;
            } catch (IOException e) {
                e.printStackTrace();
                ftpStatus = false;
            }
        } else if (Constants.FTP_PORT == 22) {
            try {
                SFtpUtils.rmFile(source);
                ftpStatus = true;
            } catch (Exception e) {
                e.printStackTrace();
                ftpStatus = false;
            }
        }
        logger.info("删除FTP文件状态：[{}]",ftpStatus);
        return  ftpStatus;
    }


    /**
     * 通用上传处理
     * @param request 请求
     * @param msg 错误信息
     * @param remotePath 远程路径
     * @param file 上传文件
     * @return
     * @throws IOException
     */
    public static boolean  commonUploadInfo(HttpServletRequest request, String msg, String remotePath, MultipartFile file) throws IOException {
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
