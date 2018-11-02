<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/28
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>500 Error Page - Ace Admin</title>

    <meta name="description" content="500 Error Page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="<%=basePath%>resources/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome.min.css" />
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- fonts -->
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/Open_Sans.min.css" />
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

<body class="no-skin">

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>
    <div class="main-content">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
                                    出现了一些问题
                                </h1>

                                <hr />
                                <h3 class="lighter smaller">
                                    But we are working
                                    <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                                    on it!
                                </h3>

                                <div class="space"></div>

                                <div>
                                    <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Read the faq
                                        </li>

                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Give us more info on how this specific error occurred!
                                        </li>
                                    </ul>
                                </div>

                                <hr />
                                <div class="space"></div>

                                <div class="center">
                                    <a href="javascript:history.back()" class="btn btn-grey">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        Go Back
                                    </a>

                                    <a href="#" class="btn btn-primary">
                                        <i class="ace-icon fa fa-tachometer"></i>
                                        Dashboard
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

                &nbsp; &nbsp;
                <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
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
</body>
</html>

