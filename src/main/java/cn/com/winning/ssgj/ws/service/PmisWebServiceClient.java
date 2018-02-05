package cn.com.winning.ssgj.ws.service;

import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.ws.client.LoginResult;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.service
 * @date 2018-02-05 13:09
 */
public class PmisWebServiceClient {

    public void getPmisUserInfoData(){
        LoginResult loginResult = PmisWSUtil.createLoginResult();

    }


}
