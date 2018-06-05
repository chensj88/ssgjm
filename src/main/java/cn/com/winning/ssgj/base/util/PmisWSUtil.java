package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.ws.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-02-05 13:19
 */
public class PmisWSUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmisWSUtil.class);

    private static LBEBusinessService lbeBusinessService = null;

    /**
     * 获取登录结果，主要提取结果中sessionId用户后续查询使用
     * @return
     */
    public static LoginResult createLoginResult() {

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
    public static void createLogoutResult(LoginResult result) {

       LogoutResult logoutResult = createLBEBusinessService().logout(result.getSessionId());
       LOGGER.info("WebService用户"+Constants.PmisWSConstants.WS_USER+"退出登录："+logoutResult.getMessage());


    }

    /**
     * 获取WS服务 LBEBusinessService
     * @return
     */
    public static LBEBusinessService createLBEBusinessService() {
        if (lbeBusinessService == null) {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(LBEBusinessService.class);
            factory.setAddress(Constants.PmisWSConstants.WS_URL);
            lbeBusinessService = factory.create(LBEBusinessService.class);
        }
        return lbeBusinessService;
    }

    /**
     * 创建查询需要的参数
     * @param quertType 参考Constansts.PmisWSConstants.WS_SERVICE_TYPE_*
     * @return params
     */
    public static List<LbParameter> createLbParameter(String quertType){
        LbParameter param = new LbParameter();
        param.setName(Constants.PmisWSConstants.QUERY_TYPE_NAME);
        param.setValue(quertType);
        List<LbParameter> params = new ArrayList<LbParameter>();
        params.add(param);
        return params;
    }

    /**
     *  根据queryName创建查询参数
     * @param queryName
     * @param quertType
     * @return
     */
    public static List<LbParameter> createQueryLbParameter(String queryName,String quertType){
        LbParameter param = new LbParameter();
        param.setName(queryName);
        param.setValue(quertType);
        List<LbParameter> params = new ArrayList<LbParameter>();
        params.add(param);
        return params;
    }


    /**
     *  第一次查询的QueryOption
     * @return option
     */
    public static QueryOption createFirstCountValueOption(){
        QueryOption option = new QueryOption();
        option.setBatchNo(1);
        option.setBatchSize(Constants.PmisWSConstants.QUERY_FIRST_BATCH_SIZE);
        option.setQueryCount(true);
        return option;
    }

    /**
     * 分页查询的QueryOption
     * @param page 页码
     * @return option
     */
    public static QueryOption createQueryValueOption(int page){
        QueryOption option = new QueryOption();
        option.setBatchNo(page);
        option.setBatchSize(Constants.PmisWSConstants.QUERY_BATCH_SIZE);
        option.setQueryCount(false);
        return option;
    }

    /**
     * 分页查询的QueryOption
     * @param page  页码
     * @param pageSize 每页显示值
     * @return option
     */
    public static QueryOption createQueryValueOption(int page,int pageSize){
        QueryOption option = new QueryOption();
        option.setBatchNo(page);
        option.setBatchSize(pageSize);
        option.setQueryCount(false);
        return option;
    }

    /**
     * 创建工作底稿的参数
     * @param info
     * @return
     */
    public static List<LbParameter> createLbParameter(EtSiteQuestionInfo info) {
        List<LbParameter> params = new ArrayList<LbParameter>();
        LbParameter param = new LbParameter();
        param.setName("xmlcb"); //项目ID
        param.setValue(info.getPmId()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("pc"); //批次
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtyxj"); //优先级
        param.setValue(info.getPriority()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("cdmc"); //系统名称
        param.setValue(info.getProductName()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("djr"); //菜单名称
        param.setValue(info.getMenuName()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtms"); //问题描述
        param.setValue(info.getQuestionDesc()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("dglx"); //底稿类型
        param.setValue(info.getQuestionType()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yyfl"); //原因分类
        param.setValue(info.getReasonType()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("wtzt"); //问题状态
        param.setValue(info.getManuscriptStatus()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjgcs"); //工程师工号
        param.setValue(info.getDevUser()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("gsfjsr"); //公司方接收人
        param.setValue(info.getDevUserName()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yfglr"); //联系人
        param.setValue(info.getLinkman()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yflxdh"); //联系电话
        param.setValue(info.getMobile()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("tcr"); //提出人
        param.setValue(info.getCreateNo()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("tcrq");//提出日期
        param.setValue(info.getMap().get("createTimeString").toString());
        params.add(param);
        param = new LbParameter();
        param.setName("xwwcrq"); //希望完成日期
        param.setValue(info.getHopeFinishDate());
        params.add(param);
        param = new LbParameter();
        param.setName("jjrq"); //解决日期
        param.setValue(info.getResolveDate());
        params.add(param);
        param = new LbParameter();
        param.setName("jjfs"); //解决方式
        param.setValue(info.getOperType()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("jjjg"); //解决结果
        param.setValue(info.getSolutionResult()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("yggzl"); //预估工作量
        param.setValue(info.getWorkLoad()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("nd"); //难度
        param.setValue(info.getDiffcultLevel()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("qrqk"); //用户方确认人签名及确认意见
        param.setValue(info.getUserMessage()+"");
        params.add(param);
        param = new LbParameter();
        param.setName("lrrgh"); //登记人工号
        param.setValue(info.getCreateNo()+"");
        params.add(param);
        return  params;
    }

    /**
     * 创建测试环境查询参数，用于在测试环境
     * @param code
     * @return
     */
    public static List<cn.com.winning.ssgj.ws.work.client.LbParameter> createTestQueryLbParameter(String code) {
        List<cn.com.winning.ssgj.ws.work.client.LbParameter> params = new ArrayList<cn.com.winning.ssgj.ws.work.client.LbParameter>();
        cn.com.winning.ssgj.ws.work.client.LbParameter param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("code"); //项目ID
        param.setValue(code);
        params.add(param);
        return params;
    }
    /**
     * 创建测试工作底稿参数
     * @param info
     * @return
     */
    public static List<cn.com.winning.ssgj.ws.work.client.LbParameter> createTestLbParameter(EtSiteQuestionInfo info) {
        List<cn.com.winning.ssgj.ws.work.client.LbParameter> params = new ArrayList<cn.com.winning.ssgj.ws.work.client.LbParameter>();
        cn.com.winning.ssgj.ws.work.client.LbParameter param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("xmlcb"); //项目ID
        param.setValue(info.getPmId()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("pc"); //批次
        param.setValue(info.getBatchNo()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("wtyxj"); //优先级
        param.setValue(info.getPriority()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("cdmc"); //系统名称
        param.setValue(info.getProductName()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("djr"); //菜单名称
        param.setValue(info.getMenuName()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("wtms"); //问题描述
        param.setValue(info.getQuestionDesc()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("dglx"); //底稿类型
        param.setValue(info.getQuestionType()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("yyfl"); //原因分类
        param.setValue(info.getReasonType()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("wtzt"); //问题状态
        param.setValue(info.getManuscriptStatus()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("jjgcs"); //工程师工号
        param.setValue(info.getDevUser()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("gsfjsr"); //公司方接收人
        param.setValue(info.getDevUserName()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("yfglr"); //联系人
        param.setValue(info.getLinkman()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("yflxdh"); //联系电话
        param.setValue(info.getMobile()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("tcr"); //提出人
        param.setValue(info.getCreateNo()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("tcrq");//提出日期
        param.setValue(info.getMap().get("createTimeString").toString());
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("xwwcrq"); //希望完成日期
        param.setValue(info.getHopeFinishDate());
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("jjrq"); //解决日期
        param.setValue(info.getResolveDate());
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("jjfs"); //解决方式
        param.setValue(info.getOperType()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("jjjg"); //解决结果
        param.setValue(info.getSolutionResult()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("yggzl"); //预估工作量
        param.setValue(info.getWorkLoad()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("nd"); //难度
        param.setValue(info.getDiffcultLevel()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("qrqk"); //用户方确认人签名及确认意见
        param.setValue(info.getUserMessage()+"");
        params.add(param);
        param = new cn.com.winning.ssgj.ws.work.client.LbParameter();
        param.setName("lrrgh"); //登记人工号
        param.setValue(info.getCreateNo()+"");
        params.add(param);
        return  params;
    }
}
