<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <title>实施工作</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css"/>
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_8s9fwys71yxmvx6r.css"/>
    <script src="<%=basePath%>resources/assets/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/assets/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="wrap">
    <div class="wrap-header">
        <div class="header">
            <span class="mui-icon mui-icon-arrowleft"></span>
            <div>更新记录</div>
        </div>
    </div>
    <div class="wrap-cnt">
        <div class="wap-tab-cnt">
            <div class="container-fluid row">
                <c:forEach var="log" items="${logs}">
                    <div class="col-xs-12">
                        <div class="col-xs-8" style="text-align: left;">
                            <span>
                                    <fmt:formatDate value="${log.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </span>
                        </div>
                        <div class="col-xs-4" style="text-align: right;"><span>${log.map.userName}</span>
                        </div>
                        <div class="col-xs-12"
                             style="line-height:25px;font-weight: bold;border-bottom: 1px dashed grey;">${log.content}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!--底部菜单-->
    <div class="wrap-foot">
        <div class="active">
            <i class="iconfont icon-ck"></i>
            查看
        </div>
        <div>
            <i class="iconfont icon-sp"></i>
            视频
        </div>
        <div>
            <i class="iconfont icon-fx"></i>
            分析
        </div>
        <div>
            <i class="iconfont icon-wo"></i>
            我
        </div>
    </div>
</div>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        IMS.menuTab();
    })
</script>
</body>
</html>
