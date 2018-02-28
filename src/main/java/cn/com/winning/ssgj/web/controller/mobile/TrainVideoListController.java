package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.domain.EtTrainVideoList;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 服务号视频学习
 *
 * @author ChenKuai
 * @create 2018-02-27 下午 3:53
 **/
@Controller
@CrossOrigin
@RequestMapping("/mobile/trainVideoList")
public class TrainVideoListController extends BaseController {
    //参数：OPENID:公众的唯一ID   HOSPCODE:医院代码  WORKNUM:用户工号
    /**
     * @author: Chen,Kuai
     * @Description: 获取视频分类
     */
    @RequestMapping("/list.do")
    public List<SysTrainVideoRepo> TrainVideoTypeList(String OPENID,String HOSPCODE,String WORKNUM){
        //获取全部视频
        SysTrainVideoRepo repo = new SysTrainVideoRepo();
        List<SysTrainVideoRepo> repoTypeList=super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoTypeList(repo);

        return repoTypeList;
    }


    /**
     * @author: Chen,Kuai
     * @Description: 获取全部的视频
     * @param video_type'
     */
        public List<SysTrainVideoRepo> TrainVideoList(String video_type,String OPENID,String HOSPCODE,String WORKNUM){
            SysTrainVideoRepo repo = new SysTrainVideoRepo();
            repo.setVideoType(video_type);
            //视频信息
            List<SysTrainVideoRepo> trainVideoProList = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoList(repo);
            //视频播放记录

            List<EtTrainVideoList> trainVideoList = super.getFacade().getEtTrainVideoListService().getEtTrainVideoListList(null);

            return null;
        }

}
