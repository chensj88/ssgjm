<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/12
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.indexedlist.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<input id="questionId" type="hidden" name="questionId" value="${questionId}">
<div class="wrap">
    <%--<div class="wrap-header">--%>
        <%--<div class="header">--%>
            <%--&lt;%&ndash;<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)" ></span>&ndash;%&gt;--%>
            <%--<div>${title}</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="wrap-cnt">
        <div id='list' class="mui-indexed-list">
            <div class="mui-indexed-list-search mui-input-row mui-search">
                <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索${title}">
            </div>
            <div class="mui-indexed-list-bar">
                <c:forEach var="d1" items="${firstInit}">
                        <a>${d1}</a>
                </c:forEach>
            </div>
            <div class="mui-indexed-list-alert"></div>
            <div class="mui-indexed-list-inner">
                <div class="mui-indexed-list-empty-alert">没有数据</div>
                <ul class="mui-table-view">
                    <c:if test="${type == 1}">
                        <c:forEach var="vmr" items="${depts}">
                            <li data-group="${vmr.groupName}" class="mui-table-view-divider mui-indexed-list-group">${vmr.groupName}</li>
                            <c:forEach var="vmr1" items="${vmr.listQuery}">
                                <li data-value="${vmr1.id }" data-tags="${vmr1.serialName}" class="mui-table-view-cell mui-indexed-list-item"
                                    onclick="selectSiteName(${vmr1.id})">${vmr1.deptName}</li>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    <c:if test="${type == 2}">
                        <c:forEach var="vmr" items="${depts}">
                            <li data-group="${vmr.groupName}" class="mui-table-view-divider mui-indexed-list-group">${vmr.groupName}</li>
                            <c:forEach var="vmr1" items="${vmr.listQuery}">
                                <li data-value="${vmr1.id }" data-tags="${vmr1.mx}" class="mui-table-view-cell mui-indexed-list-item"
                                    onclick="selectSiteName(${vmr1.id})">${vmr1.zxtmc}</li>
                            </c:forEach>
                        </c:forEach>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js"></script>
<script src="<%=basePath%>resources/mobile/js/mui.indexedlist.js"></script>
<script type="text/javascript">
    mui.init();
    mui.ready(function() {
        var header = document.querySelector('.wrap-header');
        var list = document.getElementById('list');
        //list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        list.style.height = (document.body.offsetHeight - 0) + 'px';
        window.indexedList = new mui.IndexedList(list);
    });

    function selectSiteName(id){
            location.href = "<%=basePath%>mobile/wechatSiteQuestion/changeDept.do?questionId=${questionId}&serialNo=${serialNo}&userId=${userId}&type=${type}&source=${source}&siteName="+id +"&menuName=${menuName}&questionDesc=${questionDesc}&priority=${priority}";
    }
</script>
</html>
