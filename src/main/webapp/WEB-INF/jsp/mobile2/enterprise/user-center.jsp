<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>用户中心</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_f9wiufvt6zf.css"/>
</head>
<body>
<!--用户中心-->
<div class="wrap">
        <div class="user-center">
            <div class="user-center_inform">
                <div><img src="${user_img}" alt=""></div>
                <div class="user-center_name">
                    <p><span>${name}</span></p>
                    <p>${department_name}</p>
                </div>
            </div>
            <a href="<%=basePath%>/mobile/tempSiteQuestion/wxStart.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}" class="user-row first">
                <img src="<%=basePath%>resources/mobile/images/hospital.png">
                <span>${serialName}</span>
            </a>
            <a href="<%=basePath%>/mobile/commons/colList.do?userId=7110&serialNo=11403&isManager=1" class="user-row first">
                <img src="<%=basePath%>resources/mobile/images/collect-list.png">
                    <span>采集列表</span>
            </a>

            <a href="<%=basePath%>/mobile/tempSiteQuestion/qrCode.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}" class="user-row first">
                <img src="<%=basePath%>resources/mobile/images/qr-code.png">
                <span>推广二维码</span>
            </a>
            <!-- 以下注释的内容 是扩展使用  -->
            <%--<a href="#" class="user-row-group">--%>
                <%--<img src="<%=basePath%>resources/mobile/images/hospital.png">--%>
                <%--<span>北京京都儿童医院</span>--%>
            <%--</a>--%>
            <%--<a href="#" class="user-row-group">--%>
                <%--<img src="<%=basePath%>resources/mobile/images/hospital.png">--%>
                <%--<span>采集列表</span>--%>
            <%--</a>--%>
            <%--<a href="#" class="user-row-group last">--%>
                <%--<img src="<%=basePath%>resources/mobile/images/hospital.png">--%>
                <%--<span>推广二维码</span>--%>
            <%--</a>--%>
        </div>
<%@ include file="/commons/footer.jsp" %>
</div>


</body>
</html>
