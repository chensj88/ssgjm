package cn.com.winning.ssgj.ws.work.service;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.ws.work.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title  工作底稿导入功能 （测试环境使用）
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.work.service
 * @date 2018-05-09 21:27
 * @command wsdl2java -p cn.com.winning.ssgj.ws.work.service -d D:\\ws\\  http://203.110.176.178:9083/service/LBEBusiness?wsdl
 * 2018-05-17 chensj
 */
@Component
public class PmisWorkingPaperService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PmisWSUtil.class);

    private static LBEBusinessService lbeBusinessService = null;

    public BizProcessResult importWorkReport(EtSiteQuestionInfo info){


        LBEBusinessService lbeBusinessService = createLBEBusinessService();
        LoginResult loginResult = createLoginResult();
        List<LbParameter> params = PmisWSUtil.createTestLbParameter(info);
        List<LbParameter> variables = new ArrayList<LbParameter>();
        BizProcessResult result = lbeBusinessService.execBizProcess(loginResult.getSessionId(),
                Constants.PmisWSConstants.WORK_WS_SERVICE_OBJECT_NAME,"",
                params,variables);
        createLogoutResult(loginResult);
        return result;
    }


    /**
     * 获取登录结果，主要提取结果中sessionId用户后续查询使用
     * @return
     */
    private LoginResult createLoginResult() {
        LoginResult result = createLBEBusinessService().login(Constants.PmisWSConstants.WS_USER,
                Constants.PmisWSConstants.WS_PASSWORD,
                "",
                Constants.PmisWSConstants.WS_ALGORITHM,
                "");
        LOGGER.info("WebService用户"+Constants.PmisWSConstants.WS_USER+"登录系统："+result.getMessage());
        return result;
    }

    /**
     * 退出登录解决超时问题
     */
    private void createLogoutResult(LoginResult result) {
        LogoutResult logoutResult = createLBEBusinessService().logout(result.getSessionId());
        LOGGER.info("WebService用户"+Constants.PmisWSConstants.WS_USER+"退出登录："+logoutResult.getMessage());
    }

    /**
     * 获取WS服务 LBEBusinessService
     * @return
     */
    private LBEBusinessService createLBEBusinessService() {
        if (lbeBusinessService == null) {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(LBEBusinessService.class);
            factory.setAddress(Constants.PmisWSConstants.WS_TEST_URL);
            lbeBusinessService = factory.create(LBEBusinessService.class);
        }
        return lbeBusinessService;
    }

}
