package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.base.Constants;

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
        return  ftpStatus;
    }

}
