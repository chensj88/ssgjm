package cn.com.winning.ssgj.web.controller.common;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.service.Facade;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param sourceId 数据源id
     * @return 日志集合
     */
    public List<EtLog> getEtLog(Long sourceId) {
        EtLog etLog = new EtLog();
        etLog.setSourceId(sourceId);
        List<EtLog> etLogList = getFacade().getEtLogService().getEtLogList(etLog);
        return etLogList;
    }
}
