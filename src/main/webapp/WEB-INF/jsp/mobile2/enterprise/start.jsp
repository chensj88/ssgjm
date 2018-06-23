<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>管理_搜索</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
</head>

<body>

<div class="wrap">
    <div class="wrap-start-header">
    </div>
    <div class="wrap-start-cnt">
        <!--首次登陆-->
        <div class="start_tit">欢迎使用实施工具</div>
        <!--后台登陆-->
        <%--<div class="start-desc__choose">请选择医院</div>--%>
        <%--<div class="start-desc__Selected">鄂尔多斯市第一人民医院</div>--%>
        <div class="start-button" onclick="searchHospital();">
            请选择医院
        </div>
    </div>
</div>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function(){
        IMS.init();
    })

    function  searchHospital(active) {
        location.href="<%=basePath%>/mobile/tempSiteQuestion/wxSearch.do?userId=${userId}";
    }
</script>
</body>

</html>
