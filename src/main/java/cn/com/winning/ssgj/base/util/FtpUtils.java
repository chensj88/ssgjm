package cn.com.winning.ssgj.base.util;

import java.io.*;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 2014-07-15
 * @add Ren, zhong
 */
public class FtpUtils {

    private static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);

    private static boolean isEnabledFtpUpload = Boolean.valueOf(FtpPropertiesLoader.getProperty("ftp.isenabledftpupload")).booleanValue();// required

    private static String server = FtpPropertiesLoader.getProperty("ftp.server");// required

    private static String username = FtpPropertiesLoader.getProperty("ftp.username"); // required

    private static String password = FtpPropertiesLoader.getProperty("ftp.password"); // required

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();// optional

    private static final String DEFAULT_ENCODER = "UTF-8";


    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }


    public static boolean uploadFile(final String remote, final File file) throws IOException {
        return uploadFile(remote, new FileInputStream(file));
    }

    public static boolean uploadFile(final String remote, final InputStream is) throws IOException {
        if (isEnabledFtpUpload) {
            logger.info("====开启FTP服务器上传功能====");
            boolean success = false; // 初始表示上传失败
            String ftp_remote = remote.replace(File.separator, "/");
            String filename = StringUtils.substringAfterLast(ftp_remote, "/");
            String dir = StringUtils.substringBeforeLast(ftp_remote, "/");

            FTPClient ftp = new FTPClient();// 创建FTPClient对象
            try {
                ftp.setControlEncoding(DEFAULT_ENCODER);
                FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
                conf.setServerLanguageCode("zh");
                // 连接FTP服务器
                // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
                ftp.connect(server, port);
                ftp.enterLocalPassiveMode();
                // 登录ftp
                ftp.login(username, password);

                int reply = ftp.getReplyCode();// 看返回的值是不是230，如果是，表示登陆成功

                if (!FTPReply.isPositiveCompletion(reply)) {// 以2开头的返回值就会为真
                    ftp.disconnect();
                    return success;
                }
                if (!ftp.changeWorkingDirectory(dir)) {
                    forceMkdir(dir, ftp);
                    ftp.changeWorkingDirectory(dir);
                }

                ftp.setFileType(FTP.BINARY_FILE_TYPE);// 上传类型 二进制
                // 还有ASCII_FILE_TYPE类型，区别在于ASCII_FILE_TYPE类型上传时，会对文件中的回车换行符做些必要的格式化

                ftp.storeFile(filename, is);// 将上传文件存储到指定目录

                is.close();// 关闭输入流

                ftp.logout();// 退出ftp

                success = true;// 表示上传成功
                logger.info("====FTP服务器上传文件成功,dir:[{}] name:[{}]", dir, filename);
            } catch (IOException e) {
                ftp.logout();// 退出ftp
                e.printStackTrace();
                throw e;
            } finally {
                if (is != null) {
                    is.close();
                }

                if (ftp.isConnected()) {
                    try {
                        ftp.disconnect();
                    } catch (IOException ioe) {
                    }
                }
            }
            return success;
        } else {// 不开启FTP服务器上传功能
            logger.info("====不开启FTP服务器上传功能====");
        }
        return false;

    }

    public static boolean forceMkdir(String remote, FTPClient ftpClient) throws IOException {
        if (!remote.endsWith("/")) {
            remote += "/";
        }
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = remote.substring(start, end);
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        return false;
                    }
                }

                start = end + 1;
                end = directory.indexOf("/", start);

                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return true;
    }


    public static boolean deleteFtpFile(String source) throws IOException {
        boolean flag = false;
        FTPClient ftp = new FTPClient();// 创建FTPClient对象
        try {
            ftp.setControlEncoding(DEFAULT_ENCODER);
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.connect(server, port);
            ftp.enterLocalPassiveMode();
            // 登录ftp
            ftp.login(username, password);
            flag = ftp.deleteFile(source);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }


    public static void downloadFile(String ftpFileName, String localPath) throws IOException {
        FTPClient ftp = new FTPClient();// 创建FTPClient对象
        // Download.
        OutputStream out = null;
        try {
            // Use passive mode to pass firewalls.
            ftp.enterLocalPassiveMode();

            // Get file info.
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
            if (fileInfoArray == null) {
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File " + ftpFileName + " is too large.");
            }

            String localFile = localPath + fileInfo.getName();

            // Download file.
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            if (!ftp.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file " + ftpFileName + " from FTP server. Check FTP permissions and path.");
            }

            out.flush();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * @param ftpFileName 文件名
     * @param ftpPath     文件地址
     * @return
     * @throws IOException
     */
    public static byte[] downloadFileAsByte(String ftpFileName, String ftpPath) throws IOException {
        FTPClient ftp = getFTPClient(FtpUtils.server, FtpUtils.username, FtpUtils.password, FtpUtils.port);// 创建FTPClient对象
        // Download.
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ftp.setControlEncoding("UTF-8"); // 中文支持
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(ftpPath);
            boolean flag = ftp.retrieveFile(new String(ftpFileName.getBytes("utf-8"), "iso-8859-1"), os);
            System.out.println(flag);
            os.flush();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                }
            }
        }
        return os.toByteArray();
    }
}
