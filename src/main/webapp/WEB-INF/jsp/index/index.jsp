<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <title>实施工具后台管理系统</title>
        <meta name="keywords" content="实施工具后台管理系统"/>
        <meta name="description" content="实施工具后台管理系统"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- basic styles -->
        <link href="<%=basePath%>resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome.min.css"/>
        <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
        <!--[if IE 7]>
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome-ie7.min.css"/>
        <![endif]-->
        <!-- fonts -->
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/Open_Sans.min.css" />
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace.min.css"/>
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-rtl.min.css"/>
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-skins.min.css"/>
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="<%=basePath%>resources/assets/css/ace-ie.min.css"/>
        <![endif]-->
        <script src="<%=basePath%>resources/assets/js/ace-extra.min.js"></script>
        <!--[if lt IE 9]>
        <script src="<%=basePath%>resources/assets/js/html5shiv.js"></script>
        <script src="<%=basePath%>resources/assets/js/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
            .main-content_fixed {
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.check('main-container', 'fixed')
                } catch (e) {
                }
            </script>
            <div class="main-container-inner">
                <div class="main-content main-content_fixed">
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="row">

                                    <div class="vspace-sm"></div>

                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="widget-box">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5>
                                                    <i class="icon-signal"></i>
                                                    PMIS数据统计
                                                </h5>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main">
                                                    <div class="clearfix" id="userContent">
                                                        <div id="main" style="width: 100%;height: 500px;"></div>
                                                    </div>
                                                </div><!-- /widget-main -->
                                            </div><!-- /widget-body -->
                                        </div><!-- /widget-box -->
                                    </div><!-- /span -->
                                </div><!-- /row -->

                                <div class="hr hr32 hr-dotted"></div>
                                <!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div><!-- /.main-content -->
            </div><!-- /.main-container-inner -->
        </div><!-- /.main-container -->
        <!--[if !IE]> -->
        <script src="<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js"></script>
        <!-- <![endif]-->
        <!--[if IE]>
        <script src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
        <![endif]-->
        <!--[if !IE]> -->
        <script type="text/javascript">
            window.jQuery || document.write("<script src='<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js'>" + "<" + "script>");
        </script>
        <!-- <![endif]-->
        <!--[if IE]>
        <script type="text/javascript">
            window.jQuery || document.write("<script src='<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js'>" + "<" + "script>");
        </script>
        <![endif]-->
        <script type="text/javascript">
            if ("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
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
        <script src="<%=basePath%>resources/assets/js/echarts.min.js"></script>
        <script src="<%=basePath%>resources/assets/js/macarons.js"></script>

        <!-- ace scripts -->

        <script src="<%=basePath%>resources/assets/js/ace-elements.min.js"></script>
        <script src="<%=basePath%>resources/assets/js/ace.min.js"></script>
        <script src="<%=basePath%>resources/js/common.js"></script>
        <script src="<%=basePath%>resources/js/index/index.js"></script>
    </body>
</html>


