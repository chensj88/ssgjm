package cn.com.winning.ssgj.base.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-03-09 15:32
 */
public class VideoUtil {
    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    public static Long getVideoTime(File file) {
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return m.getDuration();
    }

    /**
     * 产生视频第一帧的图片
     * @param remotePath 上传的远程地址
     * @param filePath   本地(服务器)上文件地址
     * @param targerFilePath 生成文件后 本地(服务器)上文件地址
     * @param targetFileName 生成文件后 本地(服务器)上文件名称
     * @throws FrameGrabber.Exception
     */
    public static void grabberFFmpegImage(String remotePath,String filePath, String targerFilePath, String targetFileName) throws FrameGrabber.Exception {
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            if( i == 1){
                doExecuteFrame(f, remotePath,targerFilePath, targetFileName);
                break;
            }
            i++;
        }
        ff.stop();
    }

    public static void doExecuteFrame(Frame f, String remotePath,String targerFilePath, String targetFileName) {
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        String imageMat = "jpg";
        String FileName = targerFilePath + File.separator + targetFileName + "." + imageMat;
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(FileName);
        try {
            ImageIO.write(bi, imageMat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        uploadImage(remotePath,output);
        output.delete();
    }

    /**
     * 根据文件路径获取时间长度
     * @param path
     * @return
     */
    public static Long getVideoTimeByFilepath(String path) {
        File file = new File(path);
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return m.getDuration();
    }

    /**
     * 生成视频第一帧
     * @param filePath  文件路径
     * @param targerFilePath 保存路径
     * @param targetFileName  文件名称
     * @throws FrameGrabber.Exception
     */
    public static void grabberFFmpegImageWithoutUpload(String filePath, String targerFilePath, String targetFileName) throws FrameGrabber.Exception {
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            if( i == 1){
                doExecuteFrameWithoutUpload(f,targerFilePath, targetFileName);
                break;
            }
            i++;
        }
        ff.stop();
    }

    public static void doExecuteFrameWithoutUpload(Frame f, String targerFilePath, String targetFileName) {
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        String imageMat = "jpg";
        String FileName = targerFilePath + File.separator + targetFileName + "." + imageMat;
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(FileName);
        try {
            ImageIO.write(bi, imageMat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void uploadImage(String remotePath,File newFile){

        String remoteDir = remotePath.substring(0,remotePath.lastIndexOf("/")+1);
        String filename = remotePath.substring(remotePath.lastIndexOf("/")+1);
        if (port == 21){
            try {
              FtpUtils.uploadFile(remotePath, newFile);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if(port == 22){
            try {
                SFtpUtils.uploadFile(newFile.getPath(),remoteDir,filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
