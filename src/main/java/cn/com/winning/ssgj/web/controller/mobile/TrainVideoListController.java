package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtTrainVideoList;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private SSGJHelper ssgjHelper;

    @RequestMapping(value ="/list.do")
    public String TrainVideoTypeList(String OPENID,String HOSPCODE,String WORKNUM){
        //绑定注册用户信息
        //parameter 后面base64解密结果是：
        //{"OPENID":"oyDyLxBcj0rTd9rVWyV5vTOD_Np4","HOSPCODE":"9972","WORKNUM":"张克福","USERNAME":"张克福","USERPHONE":"医院管理"}

        SysUserInfo info = new SysUserInfo();
        String userId = HOSPCODE+WORKNUM;
        info.setUserid(userId);
        List<SysUserInfo> infos = super.getFacade().getSysUserInfoService().getSysUserInfoList(info);
        if(infos== null || infos.size()==0){
            //插入

            info.setId(ssgjHelper.createUserId());
            info.setUserType("0");
        }
        //openid 是接口传输中唯一值代替sessionId
        //获取全部视频
        SysTrainVideoRepo repo = new SysTrainVideoRepo();
        List<SysTrainVideoRepo> repoTypeList=super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoTypeList(repo);

        return "/mobile/video/video_type";
    }


    /**
     * @author: Chen,Kuai
     * @Description: 获取全部的视频
     * @param video_type'
     */
    @RequestMapping("/video.do")
    public String TrainVideoList(String video_type,String OPENID,String HOSPCODE,String WORKNUM){
        SysTrainVideoRepo repo = new SysTrainVideoRepo();
        repo.setVideoType(video_type); //根据分类
        List<SysTrainVideoRepo> trainVideoProList = super.getFacade().getSysTrainVideoRepoService()
                .getSysTrainVideoRepoList(repo);

//        EtTrainVideoList trainVideo = new EtTrainVideoList();
//        trainVideo.setUserId(HOSPCODE+WORKNUM); //登陆用户ID
//        trainVideo.setOpenId(OPENID);           //微信登陆唯一标识
//        List<EtTrainVideoList> trainVideoList = super.getFacade().getEtTrainVideoListService()
//                .getEtTrainVideoListList(trainVideo);


        return "mobile/video/video_list";
    }


    /**
     * @author: Chen,Kuai
     * @Description: 视频播放记录信息
     */
    @RequestMapping(value = "/videoRecord.do")
    public String clickVideoListRecord(String OPENID,String HOSPCODE,String WORKNUM){
        //点击播放  存储播放信息
        EtTrainVideoList trainVideo = new EtTrainVideoList();
        trainVideo.setId(ssgjHelper.createVideoIdService());
        trainVideo.setCId((long) -2);//游客类型
        trainVideo.setPmId((long)-2);
        trainVideo.setPdId((long)-2);
        trainVideo.setUserId(HOSPCODE+WORKNUM); //登陆用户ID
        trainVideo.setOpenId(OPENID);           //微信登陆唯一标识
        trainVideo.setVideoTime(new Date());
        List<EtTrainVideoList> trainVideoList = super.getFacade().getEtTrainVideoListService()
                .getEtTrainVideoListList(trainVideo);

        return "mobile/video/video_paly";
    }



}
