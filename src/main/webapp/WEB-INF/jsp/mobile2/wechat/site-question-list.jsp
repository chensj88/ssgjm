<%--
  Created by IntelliJ IDEA.
  User: chenshijie
  Date: 2018/6/9
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>新增采集</title>
    <meta name="viewport"  content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<%=basePath%>resources/mobile/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css" />
    <script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
    <div class="header">
        <span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
        <div>采集列表</div>
    </div>
    <div class="hole"></div>
    <div class="site-question">
        <c:forEach var="vwr" items="${questionList}">
            <div class="site-item">
                <div class="top">
                    <div class="top-left">
                        <div>${vwr.createDate}<span>${vwr.countNum}</span></div>
                    </div>
                </div>
            </div>

            <c:forEach var="vwr1" items="${vwr.rows}">
                <div class="site-item" onclick="detail(${vwr1.id})">
                    <div class="top-left">
                        <div>${vwr1.map.get("deptName")}<span>${vwr1.map.get("plName")}</span></div>
                        <span>${vwr1.menuName}</span>
                    </div>
                    <div class="btm">
                        <p>${vwr1.priority} 状态<script>
                            document.write(Common.getDateSubStr("${vwr.createTime}"));
                        </script>
                    </div>
                </div>
            </c:forEach>

        </c:forEach>
    </div>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>

    <script >
        function detail(id){
            location.href="<%=basePath%>mobile/wechatSiteQuestion/add.do?questionId="+id+"&serialNo=${serialNo}&userId=${userId}";
        }

    </script>
</body>

</html>
