package cn.com.winning.ssgj.base.util;

import com.jcraft.jsch.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-03-01 21:05
 */
public class SFtpUtils {

    private static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);

    private static boolean isEnabledFtpUpload = Boolean.valueOf(FtpPropertiesLoader.getProperty("ftp.isenabledftpupload")).booleanValue();// required

    private static String server = FtpPropertiesLoader.getProperty("ftp.server");// required

    private static String username = FtpPropertiesLoader.getProperty("ftp.username"); // required

    private static String password = FtpPropertiesLoader.getProperty("ftp.password"); // required

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();// optional

    private static final String DEFAULT_ENCODER = "UTF-8";

    /**
     * 文件路径前缀. /ddit-remote
     */
    private static final String PRE_FIX = "";

    /**
     * 获取sftp协议连接.
     * @return 连接对象
     * @throws JSchException 异常
     */
    private static ChannelSftp getSftpConnect() throws JSchException {
        ChannelSftp sftp = null;
        JSch jsch = new JSch();
        jsch.getSession(username, server, port);
        Session sshSession = jsch.getSession(username, server, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        return sftp;
    }

    /**
     * 下载文件-sftp协议.
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp sftp连接
     * @return 文件
     * @throws Exception 异常
     */
    public static File download(final String downloadFile, final String saveFile, final ChannelSftp sftp)
            throws Exception {
        FileOutputStream os = null;
        File file = new File(saveFile);
        try {
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            List<String> list = formatPath(downloadFile);
            sftp.get(list.get(0) + list.get(1), os);
        } catch (Exception e) {
            exit(sftp);
            throw e;
        } finally {
            os.close();
        }
        return file;
    }

    /**
     * 下载文件-sftp协议.
     * @param downloadFile 下载的文件
     * @param sftp sftp连接
     * @return 文件 byte[]
     * @throws Exception 异常
     */
    public static byte[] downloadAsByte(final String downloadFile, final ChannelSftp sftp) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            List<String> list = formatPath(downloadFile);
            sftp.get(list.get(0) + list.get(1), os);
        } catch (Exception e) {
            exit(sftp);
            throw e;
        } finally {
            os.close();
        }
        return os.toByteArray();
    }

    /**
     * 删除文件-sftp协议.
     * @param deleteFile 要删除的文件
     * @throws Exception 异常
     */
    public static void rmFile(final String deleteFile) throws Exception {
        final ChannelSftp sftp = getSftpConnect();
        try {
            sftp.rm(deleteFile);
        } catch (Exception e) {
            exit(sftp);
            throw e;
        }
    }

    /**
     * 删除文件夹-sftp协议.
     * @param sftp sftp连接
     * @throws Exception 异常
     */
    public static void rmDir(final String pathString, final ChannelSftp sftp) throws Exception {
        try {
            sftp.rmdir(pathString);
        } catch (Exception e) {
            exit(sftp);
            throw e;
        }
    }

    /**
     * 上传文件-sftp协议.
     * @param srcFile 源文件
     * @param dir 保存路径
     * @param fileName 保存文件名
     * @param sftp sftp连接
     * @throws Exception 异常
     */
    public static void uploadFile(final String srcFile, final String dir,
                                   final String fileName,ChannelSftp sftp)
            throws Exception {
        mkdir(dir, sftp);
        sftp.cd(dir);
        sftp.put(srcFile, fileName);
    }

    public static void uploadFile(final String srcFile, final String dir,
                                  final String fileName)
            throws Exception {
        logger.info("====开启SFTP服务器上传功能====");
        ChannelSftp sftp = getSftpConnect();
        mkdir(dir, sftp);
        sftp.cd(dir);
        sftp.put(srcFile, fileName);
        logger.info("====SFTP服务器上传文件成功,dir:[{}] name:[{}]", dir, fileName);
        exit(sftp);
    }

    /**
     * 上传文件-sftp协议.
     * @param srcFile 源文件路径，/xxx/xxx.zip 或 x:/xxx/xxx.zip;
     * @param sftp sftp连接
     * @throws Exception 异常
     */
    public static void uploadFile(final String srcFile, final ChannelSftp sftp) throws Exception {
        try {
            File file = new File(srcFile);
            if (file.exists()) {
                List<String> list = formatPath(srcFile);
                uploadFile(srcFile, list.get(0), list.get(1), sftp);
            }
        } catch (Exception e) {
            exit(sftp);
            throw e;
        }
    }

    /**
     * 根据路径创建文件夹.
     * @param dir 路径 必须是 /xxx/xxx/xxx/ 不能就单独一个/
     * @param sftp sftp连接
     * @throws Exception 异常
     */
    public static boolean mkdir(final String dir, final ChannelSftp sftp) throws Exception {
        try {
            if (StringUtils.isBlank(dir))
                return false;
            String md = dir.replaceAll("\\\\", "/");
            if (md.indexOf("/") != 0 || md.length() == 1)
                return false;
            return mkdirs(md, sftp);
        } catch (Exception e) {
            exit(sftp);
            throw e;
        }
    }

    /**
     * 递归创建文件夹.
     * @param dir 路径
     * @param sftp sftp连接
     * @return 是否创建成功
     * @throws SftpException 异常
     */
    private static boolean mkdirs(final String dir, final ChannelSftp sftp) throws SftpException {
        String dirs = dir.substring(1, dir.length() - 1);
        String[] dirArr = dirs.split("/");
        String base = "";
        for (String d : dirArr) {
            base += "/" + d;
            if (dirExist(base + "/", sftp)) {
                continue;
            } else {
                sftp.mkdir(base + "/");
            }
        }
        return true;
    }

    /**
     * 判断文件夹是否存在.
     * @param dir 文件夹路径， /xxx/xxx/
     * @param sftp sftp协议
     * @return 是否存在
     */
    public static boolean dirExist(final String dir, final ChannelSftp sftp) {
        try {
            Vector<?> vector = sftp.ls(dir);
            if (null == vector)
                return false;
            else
                return true;
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 格式化路径.
     * @param srcPath 原路径. /xxx/xxx/xxx.yyy 或 X:/xxx/xxx/xxx.yy
     * @return list, 第一个是路径（/xxx/xxx/）,第二个是文件名（xxx.yy）
     */
    public static List<String> formatPath(final String srcPath) {
        List<String> list = new ArrayList<String>(2);
        String dir = "";
        String fileName = "";
        String repSrc = srcPath.replaceAll("\\\\", "/");
        int firstP = repSrc.indexOf("/");
        int lastP = repSrc.lastIndexOf("/");
        fileName = repSrc.substring(lastP + 1);
        dir = repSrc.substring(firstP, lastP);
        dir = PRE_FIX + (dir.length() == 1 ? dir : (dir + "/"));
        list.add(dir);
        list.add(fileName);
        return list;
    }

    /**
     * 关闭协议-sftp协议.
     * @param sftp sftp连接
     */
    public static void exit(final ChannelSftp sftp) {
        sftp.exit();
    }

    public static void main(String[] args) throws Exception {
        ChannelSftp sftp = getSftpConnect();
       /* String pathString = "E:\\winning_svn\\交付\\3、用户培训视频\\CIS门诊医生站5.5培训视频\\EMR病历-设置模板区分取中西医诊断.wmv";*/
       /* File file = new File(pathString);
        System.out.println("上传文件开始...");
        uploadFile(pathString, sftp);
        System.out.println("上传成功，开始删除本地文件...");
        file.delete();
        System.out.println("删除完成，开始校验本地文件...");
        if (!file.exists()) {
            System.out.println("文件不存在，开始从远程服务器获取...");
            download(pathString, pathString, sftp);
            System.out.println("下载完成");
        } else {
            System.out.println("在本地找到文件");
        }*/
        String dir ="/video/test/";
        String filename = "EMR病历-设置模板区分取中西医诊断.wmv";
        String pathString = "E:\\winning_svn\\交付\\3、用户培训视频\\CIS门诊医生站5.5培训视频\\EMR病历-设置模板区分取中西医诊断.wmv";
        uploadFile(pathString,dir,filename);

        exit(sftp);
        System.exit(0);
    }

}
