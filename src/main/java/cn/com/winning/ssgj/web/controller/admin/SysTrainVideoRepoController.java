package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.VideoUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 培训视频
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-28 10:39
 */
@Controller
@RequestMapping(value = "/admin/train")
public class SysTrainVideoRepoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model) {
        return "mobile/trainVideoPage";
    }

    @RequestMapping(value = "/video.do")
    public String getDeletePageInfo(HttpServletRequest request, Model model) {
        return "mobile/trainVideo";
    }


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> getTrainVideoList(SysTrainVideoRepo repo, Row row) {
        repo.setRow(row);
        /*repo.setStatus(Constants.STATUS_USE);*/
        List<SysTrainVideoRepo> repoList = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoPageListBySelective(repo);
        int total = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoCountBySelective(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", repoList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> addTrainVideo(SysTrainVideoRepo repo) {
        repo.setId(ssgjHelper.createSysTrainVideoRepoId());
        repo.setLastUpdateTime(DateUtil.getCurrentTimestamp());
        repo.setLastUpdator(((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
        repo.setStatus(Constants.STATUS_USE);
        super.getFacade().getSysTrainVideoRepoService().createSysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", repo.getId());
        return result;
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> updateTrainVideo(SysTrainVideoRepo repo) {
        repo.setLastUpdator(((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
        repo.setLastUpdateTime(DateUtil.getCurrentTimestamp());
        super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", repo.getId());
        return result;
    }


    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String, Object> getTrainVideoById(SysTrainVideoRepo repo) {
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", repo);
        return result;
    }


    @RequestMapping(value = "/modifyById.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> modifyTrainVideoById(SysTrainVideoRepo repo) {
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        if (repo.getStatus() == Constants.STATUS_UNUSE) {
            repo.setStatus(Constants.STATUS_USE);
        } else {
            repo.setStatus(Constants.STATUS_UNUSE);
        }
        repo.setLastUpdateTime(DateUtil.getCurrentTimestamp());
        repo.setLastUpdator(((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
        super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/existVideoName.do")
    @ResponseBody
    public Map<String, Object> existVideoName(SysTrainVideoRepo repo) {
        boolean exists = super.getFacade().getSysTrainVideoRepoService().existVideoName(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("valid", exists);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/queryCustomerName.do")
    @ResponseBody
    public Map<String, Object> queryCustomerNameInfo(String name, int matchCount) {
        PmisCustomerInformation customer = new PmisCustomerInformation();
        customer.setName(name);
        customer.setZt(Constants.PMIS_STATUS_USE);
        Row row = new Row(0, matchCount);
        customer.setRow(row);
        List<PmisCustomerInformation> customerInformationList = super.getFacade().getPmisCustomerInformationService().getPmisCustomerInformationPageListFuzzy(customer);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", matchCount);
        result.put("status", Constants.SUCCESS);
        result.put("data", customerInformationList);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteTrainVideo(SysTrainVideoRepo repo) {
        super.getFacade().getSysTrainVideoRepoService().deleteSysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 文件删除
     * @param repo
     * @return
     */
    @RequestMapping(value = "/deleteFile.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteFile(SysTrainVideoRepo repo) {
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        CommonFtpUtils.removeUploadFile(repo.getRemotePath());
        repo.setRemotePath("");
        super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/listFilePath.do")
    @ResponseBody
    public void listFilePath()  {
        //File file = new File("F:\\fileVideo2\\中心药房");
        //String fatherFile ="中心药房";
        //File file = new File("F:\\fileVideo2\\病区管理");
        //String fatherFile ="病区管理";
        //File file = new File("F:\\fileVideo2\\病区医生站");
        //String fatherFile ="病区医生站";
        //File file = new File("F:\\fileVideo2\\出入院管理");
        //String fatherFile ="出入院管理";
        //File file = new File("F:\\fileVideo2\\门诊挂号");
        //String fatherFile ="门诊挂号";
        //File file = new File("F:\\fileVideo2\\门诊收费");
        //String fatherFile ="门诊收费";
        //File file = new File("F:\\fileVideo2\\门诊输液室");
        //String fatherFile ="门诊输液室";
        //File file = new File("F:\\fileVideo2\\门诊药房");
        //String fatherFile ="门诊药房";
        //File file = new File("F:\\fileVideo2\\门诊医生站");
        //String fatherFile ="门诊医生站";
        File file = new File("F:\\fileVideo2\\药库管理");
        String fatherFile ="药库管理";



        SysDictInfo dictInfo = new SysDictInfo();
        dictInfo.setDictLabel(fatherFile);
        dictInfo.setDictCode("videoType");
        dictInfo = super.getFacade().getSysDictInfoService().getSysDictInfo(dictInfo);
        try{
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
                            repo.setVideoType(dictInfo.getDictValue());
                            repo.setTypeLabel(fatherFile);
                            String rPath="/video/"+fatherFile+"/"+dirList[i].getName();//视频路径
                            String iPath = "/image/"+fatherFile+"/"+targetFileName+".jpg";
                            repo.setRemotePath(rPath);
                            repo.setVideoTime(videoTime);    ///video/门诊医生站/20.病人列表-查找病人.mp4
                            repo.setImgPath(iPath);
                            repo.setLastUpdateTime(DateUtil.getCurrentTimestamp());
                            repo.setStatus(1);
                            repo.setLastUpdator(((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
                            repo.setDictDesc(dictInfo.getDictDesc());
                            super.getFacade().getSysTrainVideoRepoService().createSysTrainVideoRepo(repo);

                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
