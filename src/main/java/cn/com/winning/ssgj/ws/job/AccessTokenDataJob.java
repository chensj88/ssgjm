package cn.com.winning.ssgj.ws.job;

import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.domain.EtAccessToken;
import cn.com.winning.ssgj.service.EtAccessTokenService;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 微信缓存定时器
 */
public class AccessTokenDataJob {
    @Autowired
    private SSGJHelper ssgjHelper;
    @Autowired
    private EtAccessTokenService etAccessTokenService;

    public void execute() {
        try{
            JSONObject testToken= WeixinUtil.getApiReturn(WxConstants.ACCESS_TOKEN);
            String access_token = (String)testToken.get("access_token");
            String expires_in = String.valueOf(testToken.get("expires_in"));
            EtAccessToken entity = new EtAccessToken();
            entity.setId(ssgjHelper.createEtAccessTokenIdService());
            entity.setAccessToken(access_token);
            entity.setExpiresIn(expires_in);
            entity.setLastTime(new Timestamp(new Date().getTime()));
            entity.setType(1);//企业微信客户端
            etAccessTokenService.createEtAccessToken(entity);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
