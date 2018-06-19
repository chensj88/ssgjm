package cn.com.winning.ssgj.base;

import com.fastweixin.api.enums.OauthScope;

import java.io.Serializable;

public class WxConstants implements Serializable {
    private static final long serialVersionUID = -1L;

    // token
    private static String token = "meeting";
    // 用户信息
    public static final String CORPID = "wxac9ca7b3c2c43e81";
    public static final String AGENTID = "1000014";
    public static final String SECRET = "X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7";
    public static final String URL = "http://ssgj.winning-health.com.cn:8081/ssgjm/mobile/tempSiteQuestion/index.do";
    public static final String STATE = "STATE";
    public static final OauthScope SCOPE =OauthScope.SNSAPI_USERINFO;


}
