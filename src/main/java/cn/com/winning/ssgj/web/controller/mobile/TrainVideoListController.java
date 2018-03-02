package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.domain.EtTrainVideoList;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @author: Chen, Kuai
     * @Description: 获取视频分类
     */
    @Resource
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/list.do")
    public String TrainVideoTypeList(Model model, String parameter) {

        //绑定注册用户信息  parameter 后面base64解密结果是：
        //{"OPENID":"oyDyLxBcj0rTd9rVWyV5vTOD_Np4","HOSPCODE":"11980","WORKNUM":"1420","USERNAME":"张克福","USERPHONE":"13312345678"}
        byte[] bt = null;
        try {
            String parameter2 = "eyJPUEVOSUQiOiJveUR5THhCY2owclRkOXJWV3lWNXZUT0RfTnA0IiwiSE9TUENPREUiOiIxMTk4MCIsIldPUktOVU0iOiIxNDIwIiwiVVNFUk5BTUUiOiLlvKDlhYvnpo8iLCJVU0VSUEhPTkUiOiIxMzMxMjM0NTY3OCJ9";
            byte[] byteArray = Base64Utils.decryptBASE64(parameter2);
            String userJsonStr = "["+new String(Base64Utils.decryptBASE64(parameter2),"UTF-8")+"]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            SysUserInfo info = new SysUserInfo();
            if(userList !=null && !userList.equals("")){
                for (int i = 0; i < userList.size(); i++) { //  推荐用这个
                    JSONObject io = userList.get(i);
                    info.setId(ssgjHelper.createUserId());
                    info.setUserType("0");
                    info.setOpenId((String)io.get("OPENID"));
                    info.setName((String)io.get("USERNAME"));
                    info.setUserid((String)io.get("HOSPCODE")+(String)io.get("WORKNUM"));
                    info.setPassword((String)io.get("WORKNUM"));
                    info.setMobile((String)io.get("USERPHONE"));
                }
                SysUserInfo info_old = new SysUserInfo();
                info_old.setOpenId(info.getOpenId());
                List<SysUserInfo> userIfonList =super.getFacade().getSysUserInfoService().getSysUserInfoList(info_old);
                if(userIfonList.size() ==0 || userIfonList ==null){
                    super.getFacade().getSysUserInfoService().createSysUserInfo(info);
                }
            }
            //获取全部视频
            SysTrainVideoRepo repo = new SysTrainVideoRepo();
            List<SysTrainVideoRepo> repoTypeList = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoTypeList(repo);
            model.addAttribute("repoTypeList", repoTypeList);
            model.addAttribute("OPENID", info.getOpenId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mobile/bridge";
    }


    /**
     *
     * @author: Chen, Kuai
     * @Description: 获取全部的视频
     * @param video_type
     */
    @RequestMapping("/video.do")
    public String TrainVideoList(Model model, String video_type, String OPENID) {
        SysTrainVideoRepo repo = new SysTrainVideoRepo();
        repo.setVideoType(video_type); //根据分类
        repo.getMap().put("OPENID",OPENID);
        List<SysTrainVideoRepo> SysTrainVideoWithRecoed =super.getFacade().getSysTrainVideoRepoService()
                .getSysTrainVideoWithRecoedList(repo);

        model.addAttribute("videoWithRecoed", SysTrainVideoWithRecoed);
        model.addAttribute("OPENID", OPENID);
        return "mobile/service/course-study";
    }


    /**
     * @author: Chen, Kuai
     * @Description: 视频播放记录信息
     */
    @RequestMapping(value = "/videoPlay.do")
    public String clickVideoListRecord(String OPENID, Long id) {
        //点击播放  存储播放信息
        EtTrainVideoList trainVideo = new EtTrainVideoList();
        trainVideo.setId(ssgjHelper.createVideoIdService());
        trainVideo.setCId((long) -2);//微信游客类型
        trainVideo.setPmId((long) -2);
        trainVideo.setPdId((long) -2);
        trainVideo.setUserId("yk0001");
        trainVideo.setOpenId(OPENID);           //微信登陆唯一标识
        //trainVideo.setVideoTime(new Date());
       // super.getFacade().getEtTrainVideoListService().createEtTrainVideoList(trainVideo);
        //获取链接地址
        SysTrainVideoRepo repo = new SysTrainVideoRepo();
        repo.setId((long)id);
        repo.setStatus(1);
        repo =super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);

        return "mobile/service/course-detail";
    }

}