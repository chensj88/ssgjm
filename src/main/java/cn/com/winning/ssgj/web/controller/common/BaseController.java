package cn.com.winning.ssgj.web.controller.common;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.WxConstants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.Base64Utils;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.base.util.WeixinUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.service.Facade;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.com.winning.ssgj.base.util.Base64Utils.decryptBASE64;

public class BaseController extends BaseSpringMvcMybatisController {
    @Resource
    private Facade facade;
    @Autowired
    private SSGJHelper ssgjHelper;

    protected Map<String, Object> resultMap = new HashMap<>();

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final static String _ENCODING = "UTF-8";
    private final static String _REG = "<col name=\"([^\"]*)\" [^>]*>([^<]*)</col>";
    private final static String _REG_EASY_UI = "<th data-options=\"field:'([^\"]*)',[^>]*>([^<]*)</th>";

    public Facade getFacade() {
        return facade;
    }


    /**
     * @param request
     * @return 获取请求项目地址
     */
    public String getCtxPath(HttpServletRequest request) {
        StringBuffer ctx = new StringBuffer();
        ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
        ctx.append(request.getContextPath());

        return ctx.toString();
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @param request
     * @return 获取用户信息
     */
    public SysUserInfo getUserInfoFromSession(HttpServletRequest request) {
        return (SysUserInfo) request.getSession().getAttribute(Constants.USER_INFO);
    }


    /**
     * @date 将一个 Map 对象转化为一个 JavaBean
     */
    public static void convertMap(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    /**
     * @author: Chen, Kuai
     * @Description: 将userId获取id
     */
    public Long user_id(String userId, String userType) {
        SysUserInfo info = new SysUserInfo();
        info.setUserid(userId);
        info.setStatus(1);
        info.setUserType("1");  //0医院1公司员工
        List<SysUserInfo> infoList = this.getFacade().getSysUserInfoService().getSysUserInfoList(info);
        return infoList.get(0).getId();
    }

    /**
     * @author: Chen, Kuai
     * @Description: 将id获取userId
     */
    public String id_user(Long id, String userType) {
        SysUserInfo info = new SysUserInfo();
        info.setId(id);
        info.setStatus(1);
        info.setUserType(userType);  //0医院1公司员工
        List<SysUserInfo> infoList = this.getFacade().getSysUserInfoService().getSysUserInfoList(info);
        return infoList.get(0).getUserid();
    }

    /**
     * 获取当前的用户信息
     *
     * @return
     */
    public SysUserInfo getCurrentUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUserInfo shiroUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        SysUserInfo sessionUser = (SysUserInfo) request.getSession().getAttribute(Constants.USER_INFO);
        return shiroUser != null ? shiroUser : sessionUser;
    }

    /**
     * 获取科室类别
     *
     * @return
     */
    public List<EtDepartment> getDepartmentTypeList(Long serialNo) {
        EtDepartment info = new EtDepartment();
        info.setIsDel(1);
        info.setSerialNo(serialNo);
        List<EtDepartment> departmentTypeList = getFacade().getEtDepartmentService().selectDepartmentTypeList(info);
        return departmentTypeList;
    }

    /**
     * 获取科室分类下的科室
     *
     * @return
     */
    public List<EtDepartment> getDepartmentList(Long serialNo, String deptType) {
        EtDepartment info = new EtDepartment();
        info.setIsDel(1);
        info.setSerialNo(serialNo);
        info.setDeptType(deptType);
        List<EtDepartment> departmentList = getFacade().getEtDepartmentService().getEtDepartmentList(info);
        return departmentList;
    }


    /**
     * 根据项目获取软硬件产品条线的系统集合
     *
     * @param
     * @return
     */
    public List<PmisProductLineInfo> getProductLineList() {
        //PmisContractProductInfo pmisContractProductInfo = new PmisContractProductInfo();
        //pmisContractProductInfo.setXmlcb(pmId);
        //pmisContractProductInfo.setHtcplb(1);
        //根据产品获取产品条线
        //List<PmisProductLineInfo> pmisProductLineInfos = getFacade().getPmisProductLineInfoService().selectPmisProductLineInfoByPmidAndType(pmisContractProductInfo);
        PmisProductLineInfo lineInfo = new PmisProductLineInfo();
        lineInfo.setLx(1);
        lineInfo.setZt(1);
        lineInfo.setCpdl("1");
        List<PmisProductLineInfo> pmisProductLineInfoList = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(lineInfo);
        return pmisProductLineInfoList;
    }

    /**
     * 根据项目获取产品条线的系统集合
     *
     * @param pmId
     * @return
     */
    public List<PmisProductLineInfo> getPdNameList(Long pmId, String pdId) {
//        PmisContractProductInfo pmisContractProductInfo = new PmisContractProductInfo();
//        pmisContractProductInfo.setHtcplb(1);
//        pmisContractProductInfo.setXmlcb(pmId);
        // pmisContractProductInfo.getMap().put("softNameList",pdId);
        PmisProductLineInfo lineInfo = new PmisProductLineInfo();
        lineInfo.setLx(1);
        lineInfo.setZt(1);
        lineInfo.setCpdl("1");
        lineInfo.getMap().put("softNameList", pdId);
        List<PmisProductLineInfo> pmisProductLineInfoList = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(lineInfo);
        //根据产品获取产品条线
        //List<PmisProductLineInfo> pmisProductLineInfos = getFacade().getPmisProductLineInfoService().selectPmisProductLineInfoByPmidAndType(pmisContractProductInfo);
        return pmisProductLineInfoList;
    }


    /**
     * 根据项目获取实施范围的硬件
     *
     * @param pmId
     * @return
     */
    public List<EtSoftHardware> getHardWareList(Long pmId) {
        EtSoftHardware info = new EtSoftHardware();
        info.setPmId(pmId);
        List<EtSoftHardware> hardwareList = getFacade().getEtSoftHardwareService().getEtSoftHardwareList(info);
        return hardwareList;
    }

    /**
     * 根据项目获取实施范围的硬件名称
     *
     * @param pmId
     * @return
     */
    public List<EtSoftHardware> getHardWareNameList(Long pmId, String hwId) {
        EtSoftHardware info = new EtSoftHardware();
        info.setPmId(pmId);
        info.getMap().put("hardNameList", hwId);
        List<EtSoftHardware> hardwareList = getFacade().getEtSoftHardwareService().getEtSoftHardwareList(info);
        return hardwareList;
    }

    /**
     * 根据项目ID获取可以分配的公司人员信息
     *
     * @param pmId
     * @return
     */
    public List<EtUserInfo> getEtUserInfo(long pmId) {
        EtUserInfo userInfo = new EtUserInfo();
        userInfo.getMap().put("position", "1,0");
        userInfo.setUserType(1);
        userInfo.setPmId(pmId);
        userInfo.getMap().put("admin", "100001");
        return getFacade().getEtUserInfoService().getEtUserInfoList(userInfo);
    }

    /**
     * 获取流程节点的情况
     * Chen,Kuai
     *
     * @param pmId
     * @return
     */
    public EtProcessManager getProcessManager(long pmId) {
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        return etProcessManager;
    }

    /**
     * 根据客户号获取产品信息
     *
     * @param serialNo 客户号
     * @return tasks
     */
    public List<EtContractTask> getProductDictInfo(String serialNo) {
        EtContractTask task = new EtContractTask();
        task.setSerialNo(serialNo);
        List<EtContractTask> tasks = getFacade().getEtContractTaskService().getEtContractTaskList(task);
        return tasks;
    }

    /**
     * @param serialNo   客户号
     * @param sourceType 操作数据来源
     * @param sourceId   操作数据id
     * @param content    日志内容
     * @param status     操作状态
     * @param operator   操作人
     * @description 新增日志
     */
    public void addEtLog(String serialNo, String sourceType, Long sourceId, String content, Integer status, Long operator) {
        EtLog etLog = new EtLog();
        etLog.setId(ssgjHelper.createEtLogIdService());
        etLog.setCId(-2l);
        etLog.setPmId(-2l);
        etLog.setSerialNo(serialNo);
        etLog.setSourceType(sourceType);
        etLog.setSourceId(sourceId);
        etLog.setContent(content);
        etLog.setStatus(status);
        etLog.setOperator(operator);
        etLog.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtLogService().createEtLog(etLog);
    }

    /**
     * 根据传递的服务号参数解析信息
     *
     * @param parameter
     */
    public SysUserInfo getUserInfo(String parameter) {
        SysUserInfo info = new SysUserInfo();
        try {
            System.out.println(parameter);
            String userJsonStr = "[" + new String(decryptBASE64(parameter), "UTF-8") + "]";
            ArrayList<JSONObject> userList = JSON.parseObject(userJsonStr, ArrayList.class);
            if (userList != null && !userList.equals("")) {
                for (int i = 0; i < userList.size(); i++) {
                    JSONObject io = userList.get(i);
                    info.setId(ssgjHelper.createUserId());
                    info.setUserType("0");
                    info.setOpenId((String) io.get("OPENID"));
                    info.setName(new String((String) io.get("USERNAME")) + "(" + (String) io.get("HOSPCODE") + ")");
                    info.setYhmc(new String((String) io.get("USERNAME")));
                    info.setUserid((String) io.get("HOSPCODE") + (String) io.get("WORKNUM"));
                    info.setPassword(MD5.stringMD5ForBarCode((String) io.get("WORKNUM")));
                    info.setMobile((String) io.get("USERPHONE"));
                    info.setSsgs(Long.parseLong((String) io.get("HOSPCODE")));
                }
                //判断用户 userId 是否存在
                SysUserInfo info_use = new SysUserInfo();
                info_use.setUserid(info.getUserid());
                List<SysUserInfo> userIfonList = getFacade().getSysUserInfoService().getSysUserInfoList(info_use);
                //真实用户
                if (userIfonList.size() == 0) {
                    info.setUserid(info.getUserid());
                    getFacade().getSysUserInfoService().createSysUserInfo(info);
                } else {
                    info = userIfonList.get(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * 获取主力工程师
     *
     * @param seriaNo
     * @param id
     * @return
     */
    public EtContractTask getAllocateUser(String seriaNo, Long id) {
        EtContractTask etContractTaskTemp = new EtContractTask();
        List<EtContractTask> etContractTaskList = null;
        EtContractTask etContractTask = null;
        etContractTaskTemp.setSerialNo(seriaNo);
        if (id != null) {
            etContractTaskTemp.setId(id);
            etContractTask = getFacade().getEtContractTaskService().getEtContractTask(etContractTaskTemp);
            return etContractTask;
        }
        etContractTaskList = getFacade().getEtContractTaskService().getEtContractTaskList(etContractTaskTemp);

        if (etContractTaskList == null || etContractTaskList.size() <= 0) {
            //当查询不到时，返回null
            return null;
        }
        //当无id参数时，循环遍历确认所有系统都有分配主力工程师
        for (EtContractTask contractTask : etContractTaskList) {
            Long allocateUser = contractTask.getAllocateUser();
            if (allocateUser == null) {
                return null;
            }
        }
        return etContractTaskTemp;
    }

    /**
     * 获取客户下所有工程师
     *
     * @param seriaNo
     * @param userType 用户类型 0客户 1公司
     * @return
     */
    public List<EtUserInfo> getAllocateUserBySeriaNo(String seriaNo, Integer userType) {
        EtUserInfo etUserInfo = new EtUserInfo();
        etUserInfo.setSerialNo(seriaNo);
        etUserInfo.setUserType(1);
        List<EtUserInfo> etUserInfoList = getFacade().getEtUserInfoService().getEtUserInfoList(etUserInfo);
        return etUserInfoList;
    }


    public int getPosition(String seriaNo, long userId) {
        int i =1;
        EtUserInfo etUserInfo = new EtUserInfo();
        etUserInfo.setSerialNo(seriaNo);
        etUserInfo.setUserId(userId);
        etUserInfo.setUserType(1);
        List<EtUserInfo> etUserInfoList = getFacade().getEtUserInfoService().getEtUserInfoList(etUserInfo);
        if(etUserInfoList.size()>0){
            etUserInfo = etUserInfoList.get(0);
            if(etUserInfo.getPositionName().equals("0")){
                i=0;
            }else{
                i=1;
            }

        }
        return i;
    }
    /**
     * 将状态数量加入map
     *
     * */
    public EtSiteQuestionInfo mapList(EtSiteQuestionInfo info,int type,int isManager){
        Map<String,Object> map =new HashMap<String,Object>();
        // 1.新建未接受  2.接受 3.已分配（未处理） 4.已处理（院方未确认）5.院方确认（完结）6.院方打回7.实施工程师打回
        //服务号
        info.setProcessStatus(null);
        if(type==1){
            info.getMap().put("process_status_no","4,5"); //采集列表
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info)!= null)
                 info.getMap().put("numList",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList"));
            info.getMap().put("process_status_no",null);
            info.setProcessStatus(4);   //已处理（院方未确认）
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info)!= null)
                info.getMap().put("numNo",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList"));
            info.setProcessStatus(5);   //院方未处理
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info)!= null)
                info.getMap().put("numOver",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatus(info).getMap().get("numList"));
        }else{
            if(isManager ==0){ //项目经理
                info.getMap().put("process_status_yes","1,7"); //待分配 与待接受
            }else{
                info.setProcessStatus(2);   //待接受
            }
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info)!= null)
                info.getMap().put("numList",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList")==null?0
                        :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList"));
            info.setProcessStatus(null);
            info.getMap().put("process_status_yes","3,6");
            //info.setProcessStatus(3);   //已处理（院方未确认）
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info)!= null)
            info.getMap().put("numNo",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList"));
            info.getMap().put("process_status_yes","4,5"); //待分配 与待接受
            info.setProcessStatus(null);
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info)!= null)
            info.getMap().put("numOver",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList"));
            info.getMap().put("process_status_yes",null);
            info.setProcessStatus(6);   //已处理（院方未确认）
            if(facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info)!= null)
            info.getMap().put("numReturn",facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList")==null?0
                    :facade.getEtSiteQuestionInfoService().getEtSiteQuestionProcessStatusService(info).getMap().get("numList"));
        }

        return info;
    }



    /**
     * 获取access_token 1.5小时访问一次
     * @return
     */
    public  String getAccessToken(){
        String access_token=null;
        try{
            JSONObject testToken= WeixinUtil.getApiReturn(WxConstants.ACCESS_TOKEN);
            access_token = (String)testToken.get("access_token");
            String expires_in = (String)testToken.get("expires_in");
            EtAccessToken entity = new EtAccessToken();
            entity.setId(ssgjHelper.createEtAccessTokenIdService());
            entity.setAccessToken(access_token);
            entity.setExpiresIn(expires_in);
            entity.setLastTime(new Timestamp(new Date().getTime()));
            entity.setType(1);//企业微信客户端
            facade.getEtAccessTokenService().createEtAccessToken(entity);

        }catch (Exception e){

        }

        return access_token;
    }

    /**
     * 定时任务 生成access_token
     */
    public void taskAccessToken(){
            new Timer().schedule(new TimerTask() {
                public void run() {
                    try{
                        JSONObject testToken= WeixinUtil.getApiReturn(WxConstants.ACCESS_TOKEN);
                        String access_token = (String)testToken.get("access_token");
                        String expires_in = (String)testToken.get("expires_in");
                        EtAccessToken entity = new EtAccessToken();
                        entity.setId(ssgjHelper.createEtAccessTokenIdService());
                        entity.setAccessToken(access_token);
                        entity.setExpiresIn(expires_in);
                        entity.setLastTime(new Timestamp(new Date().getTime()));
                        entity.setType(1);//企业微信客户端
                        facade.getEtAccessTokenService().createEtAccessToken(entity);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, 0,5400);
    }


}
