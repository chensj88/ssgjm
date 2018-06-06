package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastweixin.api.enums.OauthScope;
import com.fastweixin.company.api.QYOauthAPI;
import com.fastweixin.company.api.config.QYAPIConfig;
import com.fastweixin.util.BeanUtil;
import com.fastweixin.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 企业微信访问操作
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wxInfo")
public class WxController extends BaseController {

    @RequestMapping(value = "/list.do")
    @ILog
    public String list(Model model,String code, String floorName, String serialNo, String userId) {
        try{
            //获取企业的access_token
            String token = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8";
            JSONObject apiAccessToken= WeixinUtil.getApiReturn(token);
            String access_token = (String)apiAccessToken.get("access_token");


//            URL url = new URL( "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8");
//            //AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
//            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
//            InputStream is = urlcon.getInputStream();
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
//            StringBuffer bs = new StringBuffer();
//            String l = null;
//            while((l=buffer.readLine())!=null){
//                bs.append(l);
//            }
//            //获取access_token
//            JSONObject firstWx = JSONObject.parseObject(bs.toString());
//            String access_token = (String)firstWx.get("access_token");
            //QYOauthAPI qYOauthAPI= new QYOauthAPI(QYAPIConfig);
            //获取成员信息
            String redirectUrl = WxConstants.URL;
            OauthScope snsapi_base = WxConstants.SCOPE;
            String state =WxConstants.STATE;
            String userInfo= WeixinUtil.getOauthPageUrl(redirectUrl,snsapi_base,state);
            JSONObject apiUserInfo= WeixinUtil.getApiReturn(userInfo);
            String code_info = (String)apiUserInfo.get("code");



//            URL url2 = new URL(sss );
//            HttpURLConnection urlcon2 = (HttpURLConnection)url.openConnection();
//            InputStream is2 = urlcon.getInputStream();
//            BufferedReader buffer2 = new BufferedReader(new InputStreamReader(is));
//            StringBuffer bs2 = new StringBuffer();
//            String ll = null;
//            while((ll=buffer2.readLine())!=null){
//                bs2.append(ll);
//            }
//            //获取access_token
//            JSONObject firstWx2 = JSONObject.parseObject(bs2.toString());
//            String code2 = (String)firstWx2.get("code");

            //缓存acctss_token
            //获取登录成员信息
            model.addAttribute("code",code_info);

        }catch (Exception e){
            e.printStackTrace();
        }


        return "/mobile/wx-home";
    }





}
