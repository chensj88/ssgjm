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
    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap/css/bootstrap-datepicker.min.css"/>--%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/jquery-ui/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .span_font {
            color: #666666;
            font-family: 'Helvetica Neue', Helvetica, sans-serif;
        }

        .large-list>input{
            color: #666;
            flex: 1;
            width:40%;
            height:30px;
        }
    </style>
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<input id="id" type="hidden" name="id" value="${questionInfo.id}">
<div class="wrap">
    <div class="wrap-cnt">
        <%--<div class="column-3 large-list row">--%>
            <%--<div class="col-xs-4 " style="padding-right: 0">--%>
                <%--<strong>期望完成时间</strong>--%>
            <%--</div>--%>
            <%--<div id="datePicker" class="col-xs-6 input-group date" style="height: 30px">--%>
                <%--<input id="hopeDate" type="text" class="form-control"><span class="input-group-addon"><i--%>
                    <%--class="glyphicon glyphicon-th"></i></span>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="column-3 large-list">
            <strong>期望完成时间</strong>
            <input type="text" id="hopeDate"/>
        </div>
        <div class="column-3 large-list" style="margin-top: 0">
            <strong>问题完成人</strong>
            <span>${sysUserInfo.yhmc}</span>
        </div>

        <div class="column-3 large-list">
            <strong>科室病区</strong>
            <span>${questionInfo.map.get("deptName")}</span>
        </div>
        <div class="column-3 large-list">
            <strong>系统名称</strong>
            <span>${questionInfo.map.get("plName")}</span>
        </div>
        <div class="column-3 large-list">
            <strong>问题标题</strong>
            <span>${questionInfo.menuName.trim()}</span>
        </div>
        <div class="column-3 large-list">
            <strong>问题描述</strong>
            <span>${questionInfo.questionDesc}</span>
        </div>
        <div class="space"></div>
        <div class="column-3 large-list">
            <strong>优先等级</strong>
            <span class="levelA"></span>
        </div>
        <p class="collect-list-level_p" id="remark"></p>
        <div class="space"></div>
        <div class="column-3 collect-list">
            <strong>影像资料</strong>
        </div>
        <div class="column-3 large-list">
					<span class="large-img">
                    <c:if test="${questionInfo.imgPath !=null && questionInfo.imgPath !=''}">
                        <c:forEach var="img" items="${questionInfo.imgs}">
                            <img style="width: 88px;height: 92px;" src="<%=Constants.FTP_SHARE_FLODER%>${img}" alt="">
                        </c:forEach>
                    </c:if>
					</span>
        </div>
        <%--<div class="column-3 large-list" id="solutionResult">--%>
        <%--<strong>解决方案</strong>--%>
        <%--<span>${questionInfo.solutionResult}</span>--%>
        <%--</div>--%>
        <%--<div class="column-3 large-list" id="userMessage">--%>
        <%--<strong>院方意见</strong>--%>
        <%--<span>${questionInfo.userMessage}</span>--%>
        <%--</div>--%>
        <%--<div class="column-3 large-list" id="suggest">--%>
        <%--<strong>打回意见</strong>--%>
        <%--<span>${questionInfo.suggest}</span>--%>
        <%--</div>--%>
    </div>
    <div class="wrap-foot large-btn">
        <a href="javascript:void(0)" onclick="goBack()"><span>取消</span></a>
        <a href="#" onclick="distrbuteConfirm();"><span>保存</span></a>

    </div>
</div>
</body>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<%--<script src="<%=basePath%>resources/bootstrap/js/bootstrap-datepicker.min.js" type="text/javascript"></script>--%>
<%--<script src="<%=basePath%>resources/bootstrap/js/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>--%>
<script src="<%=basePath%>resources/jquery-ui/js/jquery-ui.min.js"></script>
<script type="text/javascript">
    $(function () {
        enterprise.init();
        setListLevel(${questionInfo.priority});
        <%--//工程师打回：7--%>
        <%--let processStatus =${questionInfo.processStatus};--%>
        <%--if (processStatus == 7) {--%>
        <%--$("#solutionResult").show();--%>
        <%--$("#userMessage").show();--%>
        <%--$("#suggest").show();--%>
        <%--} else {--%>
        <%--$("#solutionResult").hide();--%>
        <%--$("#userMessage").hide();--%>
        <%--$("#suggest").hide();--%>
        <%--}--%>
//        $('#datePicker').datepicker({
//            format: "yyyy-mm-dd",
//            clearBtn: true,
//            language: "zh-CN",
//            autoclose: true,
//            todayHighlight: true,
//            toggleActive: true
//        });
        $( "#hopeDate" ).datepicker({
            showAnim:'slideDown', //显示动画样式
            dateFormat: 'yy-mm-dd',//时间样式
            dayNamesMin: ['日','一','二','三','四','五','六'], //指定星期中天的最小格式
            monthNames : ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],//指定月份的长格式
            monthNamesShort  : ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],//指定月份的简写
            changeMonth: true, //显示月份 可以选择
            changeYear: true ,//显示年份 可以选择
            showOtherMonths: true,
            selectOtherMonths: true,
            minDate: 0,
            autoSize: true,
            regional:'zh'
        });
    });

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

    //返回上一页
    function goBack() {
        history.go(-1);
    }

    // 确认分配
    function distrbuteConfirm() {
        let hopeDate = $("#hopeDate").val();
        //校验
        if (hopeDate == null || hopeDate == "") {
            mui.toast('请选择期望完成时间！', {duration: 'long', type: 'div'});
            return;
        }
        let allocateUser = ${sysUserInfo.id};
        let serialNo =${questionInfo.serialNo};
        let id = ${questionInfo.id};
        let userId =${userId};
        $.ajax({
            type: "POST",
            url: "<%=basePath%>mobile/wechatSiteQuestion/saveDistribution.do",
            data: {id: id, allocateUser: allocateUser, userId: userId, serialNo: serialNo, hopeDate: hopeDate},
            cache: false,
            dataType: "json",
            async: false,
            error: function (request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。', {duration: 'long', type: 'div'})
            },
            success: function (data) {
                if (data.status) {
                    mui.toast('分配成功', {duration: 'long(3500ms)', type: 'div'});
                    window.location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=" + userId + "&serialNo=" + serialNo;

                    //追加图片预览
                    // setTimeout("location.reload()", 3500);
                } else {
                    mui.toast('分配失败', {duration: 'long(3500ms)', type: 'div'});
                    //追加图片预览
                    //setTimeout("location.reload()",3500);

                }
            }
        });


    }


    // 问题等级加载
    function setListLevel(val) {
        var valStr = convertTypeToInt(val);
        $(".levelA").text(valStr);
    }
</script>
</html>
