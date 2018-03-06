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
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
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
        .main-content_fixed {
            margin-left: 10px;
        }
    </style>
</head>

<body>


<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <div class="main-content main-content_fixed" >
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="row">

                            <div class="vspace-sm"></div>

                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-flat widget-header-small">
                                        <h5>
                                            <i class="icon-signal"></i>
                                            用户来源信息
                                        </h5>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div id="piechart-placeholder"></div>

                                            <div class="hr hr8 hr-double"></div>

                                            <div class="clearfix" id="userContent">

                                            </div>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /widget-box -->
                            </div><!-- /span -->
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-flat widget-header-small">
                                        <h5>
                                            <i class="icon-signal"></i>
                                            菜单信息
                                        </h5>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <ul class="nav nav-list" id="nav">

                                            </ul>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /widget-box -->
                            </div><!-- /span -->
                        </div><!-- /row -->

                        <div class="hr hr32 hr-dotted"></div>

                        <div class="row">
                            <div class="col-sm-5">
                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="lighter">
                                            <i class="icon-star orange"></i>
                                            热门域名
                                        </h4>

                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="icon-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table table-bordered table-striped">
                                                <thead class="thin-border-bottom">
                                                <tr>
                                                    <th>
                                                        <i class="icon-caret-right blue"></i>
                                                        名称
                                                    </th>

                                                    <th>
                                                        <i class="icon-caret-right blue"></i>
                                                        价格
                                                    </th>

                                                    <th class="hidden-480">
                                                        <i class="icon-caret-right blue"></i>
                                                        状态
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <tr>
                                                    <td>internet.com</td>

                                                    <td>
                                                        <small>
                                                            <s class="red">$29.99</s>
                                                        </small>
                                                        <b class="green">$19.99</b>
                                                    </td>

                                                    <td class="hidden-480">
                                                        <span class="label label-info arrowed-right arrowed-in">销售中</span>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>online.com</td>

                                                    <td>
                                                        <small>
                                                            <s class="red"></s>
                                                        </small>
                                                        <b class="green">$16.45</b>
                                                    </td>

                                                    <td class="hidden-480">
                                                        <span class="label label-success arrowed-in arrowed-in-right">可用</span>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>newnet.com</td>

                                                    <td>
                                                        <small>
                                                            <s class="red"></s>
                                                        </small>
                                                        <b class="green">$15.00</b>
                                                    </td>

                                                    <td class="hidden-480">
                                                        <span class="label label-danger arrowed">待定</span>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>web.com</td>

                                                    <td>
                                                        <small>
                                                            <s class="red">$24.99</s>
                                                        </small>
                                                        <b class="green">$19.95</b>
                                                    </td>

                                                    <td class="hidden-480">
																	<span class="label arrowed">
																		<s>无货</s>
																	</span>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>domain.com</td>

                                                    <td>
                                                        <small>
                                                            <s class="red"></s>
                                                        </small>
                                                        <b class="green">$12.00</b>
                                                    </td>

                                                    <td class="hidden-480">
                                                        <span class="label label-warning arrowed arrowed-right">售完</span>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /widget-box -->
                            </div>

                            <div class="col-sm-7">
                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="lighter">
                                            <i class="icon-signal"></i>
                                            销售统计
                                        </h4>

                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="icon-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-4">
                                            <div id="sales-charts"></div>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /widget-box -->
                            </div>
                        </div>
                       <%-- <div class="hr hr32 hr-dotted"></div>--%>



                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
           </div><!-- /.page-content -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container-inner -->

   <%-- <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>--%>
</div><!-- /.main-container -->
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
<script src="<%=basePath%>resources/js/common.js"></script>
<script src="/resources/js/index/index.js"></script>
</body>
</html>


