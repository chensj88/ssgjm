package cn.com.winning.ssgj.ws.work.service;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.ws.work.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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

    /**
     * 测试环境导入工作底稿
     * @param info
     * @return
     */
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


    public QueryResult userLoginPmis(String userid,String password) throws Exception {
        LBEBusinessService lbeBusinessService = createLBEBusinessService();
        LoginResult loginResult = createLoginResult();
        QueryOption option = new QueryOption();
        List<LbParameter> params = new ArrayList<>();
        LbParameter param = new LbParameter();
        param.setName("Puserid");
        param.setValue(userid);
        params.add(param);
        param = new LbParameter();
        param.setName("Ppassword");
        param.setValue(password);
        params.add(param);
        param = new LbParameter();
        param.setName("Ptype");
        param.setValue("2");
        params.add(param);
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),
                Constants.PmisWSConstants.USER_LOGIN_WS_SERVICE_OBJECT_NAME
                ,params,"",option);
        int resultNum = Integer.parseInt(result.getRecords().get(0).getValues().get(0).toString());
        System.out.println(result.getRecords().get(0).getValues().get(0).toString());
        System.out.println(result.getRecords().get(0).getValues().get(1).toString());
        System.out.println(result.getRecords().get(0).getValues().get(2).toString());
        System.out.println(resultNum);

        createLogoutResult(loginResult);
        if(resultNum == -1){
            throw new Exception(result.getRecords().get(0).getValues().get(2).toString());
        }
        return  result;

    }
    /**
     * 测试环境
     * 查询当前底稿的处理状态
     * @param code
     * @return
     */
    public String[] queryWorkReport(String code){
        String[] attrs = {"",""};
        LBEBusinessService lbeBusinessService = createLBEBusinessService();
        LoginResult loginResult = createLoginResult();
        List<LbParameter> params = PmisWSUtil.createTestQueryLbParameter(code);
        QueryOption queryOption = createFirstCountValueOption();
        List<LbParameter> variables = new ArrayList<LbParameter>();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.QUERY_WORK_WS_SERVICE_OBJECT_NAME,params,"",queryOption);
        if (result.getResult() <= 0) {
            throw new SSGJException(result.getMessage());
        } else {
            int total = result.getCount();
            LbRecord records = result.getRecords().get(0);
            attrs[0] = records.getValues().get(0).toString();
            attrs[1] = records.getValues().get(1).toString();


        }
        createLogoutResult(loginResult);
        return attrs;
    }

    /**
     * 测试环境 第一个查询参数配置  主要为了获取总体数量
     * @return
     */
    public static cn.com.winning.ssgj.ws.work.client.QueryOption createFirstCountValueOption(){
        cn.com.winning.ssgj.ws.work.client.QueryOption option = new cn.com.winning.ssgj.ws.work.client.QueryOption();
        option.setBatchNo(1);
        option.setBatchSize(Constants.PmisWSConstants.QUERY_FIRST_BATCH_SIZE);
        option.setQueryCount(true);
        return option;
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
