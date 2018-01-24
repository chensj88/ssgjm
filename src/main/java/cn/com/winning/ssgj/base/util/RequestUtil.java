package cn.com.winning.ssgj.base.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * http的request工具类。
 *
 * @author chensj@winning.com.cn
 */
public final class RequestUtil {

    private RequestUtil() {
    }

    /**
     * 获取request中的参数。
     *
     * @param request HttpServletRequest
     * @return 参数的map形式
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRequestParams(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> keys = request.getParameterNames();

        for (; keys.hasMoreElements(); ) {
            String key = keys.nextElement();
            String value = request.getParameter(key);
            if (value != null && "null".equalsIgnoreCase(value)) {
                value = "";
            }
            params.put(key, value);
        }
        return params;
    }

    /**
     * 获取访问的真实IP。
     * <p>过滤代理等的影响。
     *
     * @param request 请求对象
     * @return String IP
     */
    public static String ip(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static final String AJAX_TYPE = "XMLHttpRequest";

    /**
     * 判断请求是否为ajax请求。
     *
     * @param request 请求对象
     * @return true，是ajax请求。
     */
    public static boolean isAjax(HttpServletRequest request) {
        String type = request.getHeader("X-Requested-With");
        return AJAX_TYPE.equals(type);
    }
}
