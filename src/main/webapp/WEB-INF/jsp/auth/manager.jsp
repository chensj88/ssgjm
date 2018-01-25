<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-theme.min.css">
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="resources/img/logo.ico">
    <style type="text/css">
        /*   #iframe-wrap { height: 100%; overflow: visible; position: relative; top: 54px; z-index: 50 } */
    </style>
</head>
<body>
<div class="container-fluid" id="maincontainer">
    <div id="nav">
        <nav class="navbar navbar-inverse ">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#menutar" aria-expanded="false">
                        <span class="sr-only">菜单</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="views/admin/manager.jsp" class="navbar-brand">菜单</a>
                </div>
                <div class="collapse navbar-collapse" id="menutar">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">用户权限信息 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a title="views/admin/user/userinfo.jsp" href="#">用户信息</a></li>
                                <li><a title="views/admin/user/roleinfo.jsp" href="#">角色信息</a></li>
                                <li><a title="views/admin/user/functioninfo.jsp" href="#">功能信息</a></li>
                                <li><a title="views/admin/user/userauthinfo.jsp" href="#">用户权限信息</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-left">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">项目信息 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">合同信息</a></li>
                                <li><a href="#">系统信息</a></li>
                                <li><a href="#">模块信息</a></li>
                                <li><a href="#">基础数据信息</a></li>
                                <li><a href="#">流程信息</a></li>
                                <li><a href="#">接口信息</a></li>
                                <li><a href="#">软硬件信息</a></li>
                                <li><a href="#">单据&报表信息</a></li>
                                <li><a href="#">流程调研问卷信息</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-left">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">项目映射关系信息 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">合同系统信息</a></li>
                                <li><a href="#">系统模块信息</a></li>
                                <li><a href="#">系统基础数据信息</a></li>
                                <li><a href="#">模块流程信息</a></li>
                                <li><a href="#">模块接口接口信息</a></li>
                                <li><a href="#">模块软硬件信息</a></li>
                                <li><a href="#">模块单据&报表信息</a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="nav navbar-nav navbar-right">
                        <p class="navbar-text">欢迎<span id="loginUser"><strong
                                style="color:white">admin</strong>登录系统</span></p>
                        <a href="#" class="navbar-text navbar-link">修改密码</a>
                        <a href="#" class="navbar-text navbar-link">退出系统</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
<div class="container-fluid">
    <div id="iframe-wrap" class="embed-responsive embed-responsive-4by3">
        <iframe id="iframe" height="100%" width="100%" frameborder="0" scrolling="yes"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript">
    $(function () {
        $('li a[title]').on('click', function (e) {
            e.preventDefault();
            var title = $(this).attr('title');
            $('#iframe').attr('src', title);
        });
        var title = Common.getRootPath() + '/views/admin/user/userinfo.jsp';
        $('#iframe').attr('src', title);
    });
</script>
</html>