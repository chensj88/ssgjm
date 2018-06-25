<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${serialName}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>

    <style>
        .qrCodeOne{
            position: relative;
            margin: 20px 20px 20px 20px;
            height: 60px;
            font-size: 22px;
            text-align: center;
            font-family: 微软雅黑;
            color:#849ECD;
        }

        .qrCodeTwo{
            width:80%;
            text-align: center;
            vertical-align: middle;
            display: table-cell;
        }

    </style>
</head>
<body>
    <div >
        <div class="qrCodeOne">
            ${serialName}
        </div>

        <div class="qrCodeTwo">
            <img style="width: 80%" src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${Headimgurl}" />
        </div>

    </div>
</body>
</html>
