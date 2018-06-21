package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtAccessToken;
import cn.com.winning.ssgj.service.Facade;
import cn.com.winning.ssgj.web.controller.common.BaseSpringMvcMybatisController;
import cn.com.winning.ssgj.web.controller.mobile.WxController;
import com.alibaba.fastjson.JSONObject;
import com.fastweixin.api.enums.OauthScope;
import com.fastweixin.company.api.QYOauthAPI;
import com.fastweixin.util.BeanUtil;
import com.fastweixin.util.StrUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

public class WeixinUtil extends BaseSpringMvcMybatisController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
    @Autowired
    private SSGJHelper ssgjHelper;
    @Resource
    private Facade facade;
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


    /**
     * 返回access_token 一个半小时执行一次
     */
//    public static String getAccessToken(){
//        String access_token=null;
//        try{
//            JSONObject testToken= getApiReturn(WxConstants.ACCESS_TOKEN);
//            access_token = (String)testToken.get("access_token");
//            String expires_in = (String)testToken.get("expires_in");
//
//            EtAccessToken entity = new EtAccessToken();
//            entity.setId(ssgjHelper.createEtAccessTokenIdService());
//            entity.setAccessToken(access_token);
//            entity.setExpiresIn(expires_in);
//            entity.setLastTime(new Timestamp(new Date().getTime()));
//            entity.setType(1);//企业微信客户端
//            facade.getEtAccessTokenService().createEtAccessToken(entity);
//
//        }catch (Exception e){
//
//        }
//
//         return access_token;
//    }



}
