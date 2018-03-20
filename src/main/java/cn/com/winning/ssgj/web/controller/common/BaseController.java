package cn.com.winning.ssgj.web.controller.common;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.Facade;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class BaseController extends BaseSpringMvcMybatisController{
    @Resource
    private Facade facade;

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
     * @author: Chen,Kuai
     * @Description: 将userId获取id
     */
    public Long user_id(String userId,String userType){
        SysUserInfo info = new SysUserInfo();
        info.setUserid(userId);
        info.setStatus(1);
        info.setUserType("1");  //0医院1公司员工
        info = this.getFacade().getSysUserInfoService().getSysUserInfo(info);
        return info.getId();
    }



}
