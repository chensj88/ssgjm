<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <title>实施工具</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="wrap">
    <div class="wrap-cnt">
        <div>
            <div class="index-link">
                <a href="<%=basePath%>/mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=1&userType=0">
                    <i class="iconfont icon-dfp"></i>
                    待分配 (${process_num.map.get("numNo")==null?0:process_num.map.get("numList")})
                </a>
                <a href="<%=basePath%>/mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=3&userType=0">
                    <i class="iconfont icon-dcl"></i>
                    未处理 (${process_num.map.get("numNo")==null?0:process_num.map.get("numOver")})
                </a>
                <a href="<%=basePath%>/mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=4,5&userType=0">
                    <i class="iconfont icon-ycl"></i>
                    处理 (${process_num.map.get("numNo")==null?0:process_num.map.get("numNo")})
                </a>
                <a href="<%=basePath%>/mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=6&userType=0">
                    <i class="iconfont icon-ydh"></i>
                    已打回 (${process_num.map.get("numReturn")==null?0:process_num.map.get("numReturn")})
                </a>
            </div>
            <c:forEach var="vwr" items="${questionList}">
            <div class="index-date">

                    <p>${vwr.groupName}  （${vwr.num}条）</p>
                    <c:forEach var="vwr1" items="${vwr.listQuery}">
                    <a href='<%=basePath%>mobile/wechatSiteQuestion/goView.do?id=${vwr1.id}&serialNo=${serialNo}&userId=${userId}'>
                        <span class="index-date_txt">${vwr1.map.deptName}-${vwr1.menuName}</span>
                        <span class="index-date_status">
                                    <i class="index-${vwr1.map.priorityString}">${vwr1.map.priorityString}</i>
                                    <i class="index-wqr">${vwr1.map.processString}</i>
                                </span>
                    </a>
                    </c:forEach>
            </div>
            </c:forEach>

        </div>
        <div class="hide">
            站点
        </div>
        <div class="hide">
            分享
        </div>
        <div class="hide">
            我的
        </div>
    </div>
    <!--新增-->
    <a href="<%=basePath%>/mobile/wechatSiteQuestion/addPage.do?userId=${userId}&serialNo=${serialNo}&source=2" class="wrap-add" >
        <i class="iconfont icon-add"></i>
    </a>
    <!--底部菜单-->
    <div class="wrap-foot">
        <div class="active" onclick="openIndexPage()">
            <i class="iconfont icon-task"></i>
            任务
        </div>
        <div>
            <i class="iconfont icon-site"></i>
            站点
        </div>
        <div>
            <i class="iconfont icon-upload"></i>
            上传
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
    function openIndexPage() {
        location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo=${serialNo}";
    }
</script>
</body>
</html>

