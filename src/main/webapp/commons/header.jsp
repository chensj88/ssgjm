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

    String basePathNuName = request.getScheme() + "://" +"47.97.170.21" +":" + request.getServerPort() + "/";

%>