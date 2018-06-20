package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.job.ImportPmisDataJob;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastweixin.api.enums.OauthScope;
import com.fastweixin.company.api.QYOauthAPI;
import com.fastweixin.company.api.config.QYAPIConfig;
import com.fastweixin.util.BeanUtil;
import com.fastweixin.util.StrUtil;
import org.slf4j.LoggerFactory;
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
import java.util.logging.Logger;

/**
 * 企业微信访问操作
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wxInfo")
public class WxController extends BaseController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WxController.class);


    @RequestMapping(value = "/list.do")
    @ILog
    public String list(Model model,String code, String floorName, String serialNo, String userId) {
        String userInfo = null;
        try{
            //获取企业的access_token
//            String token = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8";
//            JSONObject apiAccessToken= WeixinUtil.getApiReturn(token);
//            String access_token = (String)apiAccessToken.get("access_token");
            //获取成员信息
            String redirectUrl = WxConstants.URL;
            OauthScope snsapi_base = WxConstants.SCOPE;
            String state =WxConstants.STATE;
            userInfo= WeixinUtil.getOauthPageUrl(redirectUrl,snsapi_base,state);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:" + userInfo;
    }


}
