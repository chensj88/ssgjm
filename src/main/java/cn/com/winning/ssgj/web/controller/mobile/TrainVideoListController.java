package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/list.do")
    public String TrainVideoList(String OPENID,String HOSPCODE,String WORKNUM){
        //展示




        return null;
    }




}
