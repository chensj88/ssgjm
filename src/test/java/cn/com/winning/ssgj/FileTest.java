package cn.com.winning.ssgj;

import cn.com.winning.ssgj.base.util.VideoUtil;
import org.bytedeco.javacv.FrameGrabber;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj
 * @date 2018-04-12 12:34
 */
public class FileTest {

    @Test
    public void testFilePath() throws FrameGrabber.Exception {
        File file = new File("F:\\FFOutput");
        if(file.isDirectory()){
            File[]  dirList = file.listFiles();
            if(dirList.length > 0 ){
                for (int i = 0; i < dirList.length; i++) {
                    System.out.println("Path:"+dirList[i].getPath() +
                                        "   Name:"+dirList[i].getName()
                    );
                    if(dirList[i].getName().endsWith(".mp4")){
                        long videoTime = VideoUtil.getVideoTimeByFilepath(dirList[i].getPath());
                        String targetFile = dirList[i].getParent();
                        String targetFileName = dirList[i].getName().substring(0,dirList[i].getName().lastIndexOf(".mp4"));
                        VideoUtil.grabberFFmpegImageWithoutUpload(dirList[i].getPath(),targetFile,targetFileName);
                    }
                }
            }
        }
    }
}
