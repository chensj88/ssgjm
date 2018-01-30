<%@ page import="cn.com.winning.ssgj.domain.SysUserInfo" %>
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
    <link href="<%=basePath%>resources/assets/css/bootstrap.min.css" rel="stylesheet" />
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
                <li class="grey">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-tasks"></i>
                        <span class="badge badge-grey">4</span>
                    </a>

                    <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-ok"></i>
                            还有4个任务完成
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">软件更新</span>
                                    <span class="pull-right">65%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:65%" class="progress-bar "></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">硬件更新</span>
                                    <span class="pull-right">35%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">单元测试</span>
                                    <span class="pull-right">15%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">错误修复</span>
                                    <span class="pull-right">90%</span>
                                </div>

                                <div class="progress progress-mini progress-striped active">
                                    <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                查看任务详情
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-bell-alt icon-animated-bell"></i>
                        <span class="badge badge-important">8</span>
                    </a>

                    <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-warning-sign"></i>
                            8条通知
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												新闻评论
											</span>
                                    <span class="pull-right badge badge-info">+12</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="btn btn-xs btn-primary icon-user"></i>
                                切换为编辑登录..
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												新订单
											</span>
                                    <span class="pull-right badge badge-success">+8</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
												粉丝
											</span>
                                    <span class="pull-right badge badge-info">+11</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                查看所有通知
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-envelope-alt"></i>
                            5条消息
                        </li>

                        <li>
                            <a href="#">
                                <img src="<%=basePath%>resources/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Alex:</span>
												不知道写啥 ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>1分钟以前</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <img src="<%=basePath%>resources/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Susan:</span>
												不知道翻译...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>20分钟以前</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <img src="<%=basePath%>resources/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Bob:</span>
												到底是不是英文 ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>下午3:15</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="inbox.html">
                                查看所有消息
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<%=basePath%>resources/assets/avatars/user.jpg" alt="Jason's Photo" />
                        <span class="user-info">
									<small>欢迎光临,</small>
									<%--<%=((SysUserInfo)request.getAttribute("user")).getYhmc()%>--%>
                                    admin
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="icon-cog"></i>
                                设置
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="icon-user"></i>
                                个人资料
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
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
                <li class="active">
                    <a href="#">
                        <i class="icon-dashboard"></i>
                        <span class="menu-text"> 控制台 </span>
                    </a>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-text-width"></i>
                        <span class="menu-text"> 项目信息 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li><a href="<%=basePath%>admin/productInfo/product.do"><i class="icon-double-angle-right"></i>产品信息</a></li>
                        <li><a href="<%=basePath%>admin/productLineInfo/productLine.do"><i class="icon-double-angle-right"></i>产品条线信息</a></li>
                        <li><a href="<%=basePath%>admin/basicData/dataInfo.do"><i class="icon-double-angle-right"></i>基础数据类型信息</a></li>
                        <li><a href="<%=basePath%>admin/flow/flowInfo.do"><i class="icon-double-angle-right"></i>业务流程信息</a></li>
                        <li><a href="<%=basePath%>admin/thirx/interfaceInfo.do"><i class="icon-double-angle-right"></i>第三方接口类型信息</a></li>
                        <li><a href="<%=basePath%>admin/hardware/shInfo.do"><i class="icon-double-angle-right"></i>软硬件设备类型信息</a></li>
                        <li><a href="<%=basePath%>admin/report/reportInfo.do"><i class="icon-double-angle-right"></i>报表单据信息</a></li>
                        <li><a href="<%=basePath%>admin/fq/fqInfo.do"><i class="icon-double-angle-right"></i>流程调研问卷清单</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-desktop"></i>
                        <span class="menu-text"> 项目映射关系信息 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li><a href="#"><i class="icon-double-angle-right"></i>产品与基础数据信息</a></li>
                        <li><a href="#"><i class="icon-double-angle-right"></i>产品与业务流程信息</a></li>
                        <li><a href="#"><i class="icon-double-angle-right"></i>产品与第三方接口信息</a></li>
                        <li><a href="#"><i class="icon-double-angle-right"></i>产品与软硬件设备信息</a></li>
                        <li><a href="#"><i class="icon-double-angle-right"></i>产品与报表单据信息</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-list"></i>
                        <span class="menu-text"> 用户管理 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li><a href="<%=basePath%>admin/user/userinfo.do"><i class="icon-double-angle-right"></i>用户信息</a></li>
                        <li><a href="views/admin/user/roleinfo.jsp"><i class="icon-double-angle-right"></i>角色信息</a></li>
                        <li><a href="views/admin/user/functioninfo.jsp"><i class="icon-double-angle-right"></i>功能信息</a></li>
                        <li><a href="views/admin/user/userauthinfo.jsp"><i class="icon-double-angle-right"></i>角色权限配置</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-edit"></i>
                        <span class="menu-text"> 基础资料 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li><a href="#"><i class="icon-double-angle-right"></i>字典管理</a></li>
                        <li><a href="#"><i class="icon-double-angle-right"></i>参数管理</a></li>
                    </ul>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle"><i class="icon-list-alt"></i>Z<span class="menu-text"> 系统设置 </span></a>
                    <ul class="submenu">
                        <li><a href="#"> <i class="icon-double-angle-right"></i>菜单设置</a></li>
                        <li><a href="form-wizard.html"><i class="icon-double-angle-right"></i>功能点设置</a></li>
                    </ul>
                </li>
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
                <ul class="breadcrumb">
                    <li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
                    <li class="active">控制台</li>
                </ul><!-- .breadcrumb -->
                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>控制台<small><i class="icon-double-angle-right"></i>查看</small></h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row" id="jspContent" style="height: 500px">
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
</div><!-- /.main-container -->
<!--[if !IE]> -->
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js'>"+"<"+"script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js'>"+"<"+"script>");
</script>
<![endif]-->
<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="<%=basePath%>resources/assets/js/bootstrap.min.js"></script>
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
<script type="text/javascript">
    //首页加载方法 = 定义全局左边树JSON字符串
    /*var _menus;
    var flag = 0;
    var _context_session_value = "JSESSIONID=${pageContext.request['session']['id']};Path=${pageContext.request['contextPath']}";
    $(function() {
        if('${needDialog}' === 'true'){
            selectBook();
        } else if('${needDialog}' === 'false'){
            init("${sessionScope.bookInfo.book_code}");
        }
    });*/
</script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/index/index.js"></script>
<%--<script type="text/javascript">--%>
    <%--$(function () {--%>
        <%--console.log(<%=request.getSession().getAttribute("user") %>);--%>
    <%--});
</script>--%>
</body>
</html>


