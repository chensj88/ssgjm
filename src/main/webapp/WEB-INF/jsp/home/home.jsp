<%@ page import="cn.com.winning.ssgj.domain.SysUserInfo" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>实施工具后台管理系统</title>
    <meta name="keywords" content="实施工具后台管理系统" />
    <meta name="description" content="实施工具后台管理系统" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <%--<link href="<%=basePath%>resources//css/bootstrap.min.css" rel="stylesheet" />--%>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome.min.css" />
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico">
    <!--[if IE 7]>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- fonts -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace.min.css" />
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-skins.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="<%=basePath%>resources/assets/js/ace-extra.min.js"></script>
    <!--[if lt IE 9]>
    <script src="<%=basePath%>resources/assets/js/html5shiv.js"></script>
    <script src="<%=basePath%>resources/assets/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .content {
            background-color: #cc85d9;
            width: 100%;
            position: absolute;
        }
    </style>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    实施工具后台管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                 <li class="green">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<%=basePath%>resources/assets/avatars/user.jpg" alt="Jason's Photo" />
                        <span class="user-info">
									<small>欢迎光临,</small>
                                   <%=((SysUserInfo)SecurityUtils.getSubject().getPrincipal()).getName()%>
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="/logout">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <button class="btn btn-success">
                        <i class="icon-signal"></i>
                    </button>

                    <button class="btn btn-info">
                        <i class="icon-pencil"></i>
                    </button>

                    <button class="btn btn-warning">
                        <i class="icon-group"></i>
                    </button>

                    <button class="btn btn-danger">
                        <i class="icon-cogs"></i>
                    </button>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div><!-- #sidebar-shortcuts -->

            <ul class="nav nav-list" id="navlist">

            </ul><!-- /.nav-list -->
            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
            </div>
            <script type="text/javascript">
                try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
            </script>
        </div>
        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb" id="breadcrumbMenu">
                    <li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
                    <li class="active">控制台</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                       <div class="row" id="jspContent" >
                            <iframe id="coniframe" height="100%" width="100%" frameborder="0" scrolling="yes"></iframe>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; 选择皮肤</span>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                    <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                    <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                    <label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                    <label class="lbl" for="ace-settings-rtl">切换到左边</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                    <label class="lbl" for="ace-settings-add-container">
                        切换窄屏
                        <b></b>
                    </label>
                </div>
            </div>
        </div><!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!--[if !IE]> -->
<script src="<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js'>"+"<"+"script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js'>"+"<"+"script>");
</script>
<![endif]-->
<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>resources/assets/js/typeahead-bs2.min.js"></script>
<!--[if lte IE 8]>
<script src="<%=basePath%>resources/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="<%=basePath%>resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=basePath%>resources/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=basePath%>resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>resources/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="<%=basePath%>resources/assets/js/jquery.sparkline.min.js"></script>
<script src="<%=basePath%>resources/assets/js/flot/jquery.flot.min.js"></script>
<script src="<%=basePath%>resources/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="<%=basePath%>resources/assets/js/flot/jquery.flot.resize.min.js"></script>
<!-- ace scripts -->
<script src="<%=basePath%>resources/assets/js/ace-elements.min.js"></script>
<script src="<%=basePath%>resources/assets/js/ace.min.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/home/home.js"></script>
</body>
</html>


