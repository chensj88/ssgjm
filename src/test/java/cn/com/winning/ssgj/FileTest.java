package cn.com.winning.ssgj;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.VideoUtil;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.Facade;
import cn.com.winning.ssgj.web.controller.admin.CommonUploadController;
import org.apache.shiro.SecurityUtils;
import org.bytedeco.javacv.FrameGrabber;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
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
    @Autowired
    private SSGJHelper ssgjHelper;

    @Resource
    private Facade facade;

    public void testFilePath() throws FrameGrabber.Exception {
        File file = new File("F:\\fileVideo\\CIS门诊医生站5.5培训视频");
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

                        //id ,视频名称,video_desc=1,video_type,type_label（文件夹名称）,远程路径，时长，图片路径
                        SysTrainVideoRepo repo = new SysTrainVideoRepo();
                        repo.setId(ssgjHelper.createSysTrainVideoRepoId());
                        repo.setVideoName(dirList[i].getName());//视频名称
                        repo.setVideoDesc("1");
                        repo.setTypeLabel(dirList[i].getParent());
                        String rPath="/video/"+dirList[i].getParent()+"/"+dirList[i].getName();//视频路径
                        String iPath = rPath.substring(0,rPath.lastIndexOf("."));
                        repo.setRemotePath(rPath);
                        repo.setVideoTime(videoTime);    ///video/门诊医生站/20.病人列表-查找病人.mp4
                        repo.setImgPath(iPath);
                        repo.setLastUpdateTime(DateUtil.getCurrentTimestamp());
                        repo.setStatus(1);
                        repo.setLastUpdator(((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
                        repo.setStatus(Constants.STATUS_USE);
                        facade.getSysTrainVideoRepoService().createSysTrainVideoRepo(repo);



                    }
                }
            }
        }
    }
}
