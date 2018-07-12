<%@ page import="cn.com.winning.ssgj.base.Constants" %>
<%@ page import="cn.com.winning.ssgj.domain.SysUserInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //检查用户是否登录
   /* SysUserInfo user = (SysUserInfo) request.getSession().getAttribute(Constants.USER_INFO);
    if(user == null ){ request.getServerName()
        request.getRequestDispatcher("/login/login.do").forward(request,response);
    }*/

    String basePathNuName = request.getScheme() + "://" +request.getServerName()+":" + request.getServerPort() + "/";

%>

<script>
    /**
     * 从本地缓存中读取缓存数据
     * @param name
     */
    function getCookie(name)
    {
        // return window.localStorage.getItem(name);
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(name + "=")
            if (c_start!=-1)
            {
                c_start=c_start + name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return ""
    }

    /**
     * 保存数据到本地缓存
     * @param name
     * @param value
     */
    function setCookie(name,value)
    {
        //window.localStorage.setItem(name, value);
        var exdate=new Date()
        exdate.setDate(exdate.getDate()+30*24*60*60*1000);
        document.cookie=name+ "=" +escape(value)+ ";expires="+exdate.toGMTString();
    }
</script>