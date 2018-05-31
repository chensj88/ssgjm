package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 企业微信访问操作
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wxInfo")
public class WxController extends BaseController {

    @RequestMapping(value = "/list.do")
    @ILog
    public String list(Model model, String floorName, String serialNo, String userId) {
        try{
            URL url = new URL( "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8");
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l);
            }
            //获取access_token
            JSONObject firstWx = JSONObject.parseObject(bs.toString());
            String ss = (String)firstWx.get("access_token");
            System.out.println(firstWx);


        }catch (Exception e){
            e.printStackTrace();
        }


        return "/mobile/wx-home";
    }





}
