package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.web.controller.mobile.WxController;
import com.alibaba.fastjson.JSONObject;
import com.fastweixin.api.enums.OauthScope;
import com.fastweixin.company.api.QYOauthAPI;
import com.fastweixin.util.BeanUtil;
import com.fastweixin.util.StrUtil;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeixinUtil implements Serializable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WeixinUtil.class);

    /**
     * 根据api 获取内容
     */
    public static JSONObject getApiReturn(String apiUrl) throws Exception{
        java.net.URL url = new URL(apiUrl);
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
        return firstWx;
    }

    /**
     *  获取第三方接口授权用户code信息
     * @param redirectUrl
     * @param scope
     * @param state
     * @return
     */
    public static String getOauthPageUrl(String redirectUrl, OauthScope scope, String state) {
        if (StrUtil.isBlank(redirectUrl)) {
            throw new NullPointerException("redirectUrl is null");
        } else {
            BeanUtil.requireNonNull(scope, "scope is null");
            String userstate = StrUtil.isBlank(state) ? "STATE" : state;
            String url = null;
            try {
                url = URLEncoder.encode(redirectUrl, "UTF-8");
            } catch (UnsupportedEncodingException var7) {
                //logger.error("异常", var7);
            }

            StringBuffer stringBuffer = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?");
            stringBuffer.append("appid=").append(WxConstants.CORPID).append("&redirect_uri=").append(url).append("&response_type=code&scope=").append(scope.toString()).append("&state=").append(userstate).append("#wechat_redirect");
            return stringBuffer.toString();
        }
    }



}
