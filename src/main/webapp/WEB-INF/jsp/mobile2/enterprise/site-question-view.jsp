<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/9
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>查看</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css"/>
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .span_font {
            color: #666666;
            font-family: 'Helvetica Neue', Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<input id="id" type="hidden" name="id" value="${siteQuestionInfo.id}">
<input id="source" type="hidden" name="source" value="${source}">
<div class="wrap">
    <div class="wrap-cnt">
        <div class="column-2 large-list">
            <strong>科室病区</strong>
            <span>${questionInfo.map.get("deptName")}</span>
        </div>
        <div class="column-2 large-list">
            <strong>系统名称</strong>
            <span>${questionInfo.map.get("plName")}</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题标题</strong>
            <span>${questionInfo.menuName.trim()}</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题描述</strong>
            <span>${questionInfo.questionDesc}</span>
        </div>
        <div class="space"></div>
        <div class="column-2 large-list">
            <strong>优先等级</strong>
            <span class="levelA"></span>
        </div>
        <p class="collect-list-level_p" id="remark"></p>
        <div class="space"></div>
        <div class="column-2 collect-list">
            <strong>影像资料</strong>
        </div>
        <div class="column-2 large-list">
					<span class="large-img">
                    <c:if test="${questionInfo.imgPath !=null && questionInfo.imgPath !=''}">
                        <c:forEach var="img" items="${questionInfo.imgs}">
                            <img style="width: 88px;height: 92px;" src="<%=Constants.FTP_SHARE_FLODER%>${img}" alt="">
                        </c:forEach>
                    </c:if>
					</span>
        </div>
        <div class="column-2 large-list" id="solutionResult">
            <strong>解决方案</strong>
            <span>${questionInfo.solutionResult}</span>
        </div>
        <div class="column-2 large-list" id="userMessage">
            <strong>院方意见</strong>
            <span>${questionInfo.userMessage}</span>
        </div>
        <div class="column-2 large-list" id="suggest">
            <strong>打回意见</strong>
            <span>${questionInfo.suggest}</span>
        </div>
    </div>
    <div class="wrap-foot large-btn">
        <c:if test="${questionInfo.processStatus==1&&isManager==0}">
            <a href="javascript:void(0);" onclick="goUpdate();"><span>编辑</span></a>
            <a href="javascript:void(0);" onclick="goDistribute();"><span>查看分配</span></a>
        </c:if>
    </div>
</div>
</body>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        enterprise.init();
        setListLevel(${questionInfo.priority});
        //工程师打回：7
        let processStatus =${questionInfo.processStatus};
        if (processStatus == 7) {
            $("#solutionResult").show();
            $("#userMessage").show();
            $("#suggest").show();
        } else {
            $("#solutionResult").hide();
            $("#userMessage").hide();
            $("#suggest").hide();
        }
    });

    // 跳转编辑页面
    function goUpdate() {
        let id =${questionInfo.id};
        let serialNo =${serialNo};
        let userId =${userId};
        window.location.href = "<%=basePath%>mobile/wechatSiteQuestion/goUpdate.do?id=" + id + "&serialNo=" + serialNo + "&userId=" + userId + "&type=" + 2;
    }

    // 跳转分配页面
    function goDistribute() {
        let id =${questionInfo.id};
        let serialNo =${serialNo};
        let userId =${userId};
        window.location.href = "<%=basePath%>mobile/wechatSiteQuestion/goDistribute.do?id=" + id + "&serialNo=" + serialNo + "&userId=" + userId + "&type=" + 2;
    }

    /**
     *优先级转换 从码值到ABCD
     *@param val
     *@returns {string}
     */
    function convertTypeToInt(val) {
        var valStr = '';
        switch (val) {
            case 1:
                valStr = 'A'
                break;
            case 2:
                valStr = 'B'
                break;
            case 3:
                valStr = 'C'
                break;
            case 4:
                valStr = 'D'
                break;
            default:
                'D'
        }
        return valStr;
    }


    // 问题等级加载
    function setListLevel(val) {
        var valStr = convertTypeToInt(val);
        $(".levelA").text(valStr);
    }
</script>
</html>
