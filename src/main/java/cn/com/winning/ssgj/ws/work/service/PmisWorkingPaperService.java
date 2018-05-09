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
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.work.service
 * @date 2018-05-09 21:27
 */
@Component
public class PmisWorkingPaperService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PmisWSUtil.class);

    private static LBEBusinessService lbeBusinessService = null;

    public void importWorkReport(EtSiteQuestionInfo info){
        LBEBusinessService lbeBusinessService = createLBEBusinessService();
        LoginResult loginResult = createLoginResult();
        List<LbParameter> params = createLbParameter(info);
        List<LbParameter> variables = new ArrayList<LbParameter>();
        BizProcessResult result = lbeBusinessService.execBizProcess(loginResult.getSessionId(),
                Constants.PmisWSConstants.WORK_WS_SERVICE_OBJECT_NAME,"",
                params,variables);

    }

    private List<LbParameter> createLbParameter(EtSiteQuestionInfo info) {
        List<LbParameter> params = new ArrayList<LbParameter>();
        LbParameter param = new LbParameter();
        param.setName("xmlcb");
        param.setValue(info.getPmId()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("pc");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtyxj");
        param.setValue(info.getPriority()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("cdmc");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("djr");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtms");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("dglx");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yyfl");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtzt");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjgcs");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("gsfjsr");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yfglr");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yflxdh");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("tcr");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("tcr");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("tcrq");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("xwwcrq");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjrq");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjfs");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjjg");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yggzl");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("nd");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("qrqk");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("lrrgh");
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        return  params;
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
            factory.setAddress(Constants.PmisWSConstants.WORK_WS_URL);
            lbeBusinessService = factory.create(LBEBusinessService.class);
        }
        return lbeBusinessService;
    }

}
