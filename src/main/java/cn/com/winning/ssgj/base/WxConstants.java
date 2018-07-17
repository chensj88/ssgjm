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
    public static final String URL = "http://ssgj.winning-health.com.cn:8081/ssgjm/mobile/tempSiteQuestion/wxStart.do";
    public static final String STATE = "STATE";
    public static final OauthScope SCOPE =OauthScope.SNSAPI_USERINFO;
    public static  final String SUITE_ACCESS_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
    public static  final String TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8";
    //第三方根据code获取企业成员信息
    public static  final String QY_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?";
    //第三方使用user_ticket获取成员详情
    public static  final String USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserdetail3rd?access_token=SUITE_ACCESS_TOKEN";
    //获取access_token
    public static  final String  ACCESS_TOKEN="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac9ca7b3c2c43e81&corpsecret=X8KHKKb0O3yR7qcnQSFDzBGiPhc8urJBK5sAnUE7-j8" ;
    //读取成员
    public static  final String QY_USER_INFO2 ="https://qyapi.weixin.qq.com/cgi-bin/user/get?";


}
