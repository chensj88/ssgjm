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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/zoomify/css/zoomify.min.css"/>
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <%--photoSwipe--%>
    <link rel="stylesheet prefetch" href="<%=basePath%>resources/photoSwipe/css/photoswipe.css">
    <link rel="stylesheet prefetch" href="<%=basePath%>resources/photoSwipe/css/default-skin.css">
    <script src="<%=basePath%>resources/photoSwipe/js/photoswipe.min.js"></script>
    <script src="<%=basePath%>resources/photoSwipe/js/photoswipe-ui-default.min.js"></script>
    <script src="<%=basePath%>resources/photoSwipe/js/myPhoto.js" type="text/javascript" charset="utf-8"></script>

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
        <%--<div class="column-2 large-list">--%>
        <%--<span class="large-img">--%>
        <%--<c:if test="${questionInfo.imgPath !=null && questionInfo.imgPath !=''}">--%>
        <%--<c:forEach var="img" items="${questionInfo.imgs}">--%>
        <%--<img style="width: 88px;height: 92px;" src="<%=Constants.FTP_SHARE_FLODER%>${img}"--%>
        <%--class="zoomify">--%>
        <%--</c:forEach>--%>
        <%--</c:if>--%>
        <%--</span>--%>
        <%--</div>--%>
        <div class="column-2 large-list">
					<span class="large-img" id="imgs">
                    <c:if test="${questionInfo.imgPath !=null && questionInfo.imgPath !=''}">
                        <c:forEach var="img" items="${questionInfo.imgs}" varStatus="status">
                            <img style="width: 88px;height: 92px;" src="<%=Constants.FTP_SHARE_FLODER%>${img}"
                                 onclick="toBigPic(${status.index})">
                        </c:forEach>
                    </c:if>
					</span>
        </div>
        <div class="column-2 large-list" id="solutionResult" style="display: none;">
            <strong>解决方案</strong>
            <span>${questionInfo.solutionResult}</span>
        </div>
        <div class="column-2 large-list" id="userMessage" style="display: none;">
            <strong>院方意见</strong>
            <span>${questionInfo.userMessage}</span>
        </div>
        <div class="column-2 large-list" id="suggest" style="display: none;">
            <strong>打回意见</strong>
            <span>${questionInfo.suggest}</span>
        </div>
        <c:if test="${(questionInfo.processStatus==3||questionInfo.processStatus==6)&&(questionInfo.allocateUser==userId||isManager==0)&&status!='6'}">
            <div class="column-2 large-list" id="solutionResultEditDiv">
                <strong>解决方案</strong>
                <div class="collect-list-text" style="width: 80%;">
                    <textarea id="solutionResultEdit" style="font-size:12px">${questionInfo.solutionResult}</textarea>
                </div>
            </div>
        </c:if>
        <jsp:include page="img.jsp"></jsp:include>
        <%--<c:if test="${(questionInfo.allocateUser==userId||isManager==0)}">--%>
        <%--<div class="column-2 large-list" id="userMessageEditDiv">--%>
        <%--<strong>院方意见</strong>--%>
        <%--<div class="collect-list-text" style="width: 80%;">--%>
        <%--<textarea id="userMessageEdit">${questionInfo.solutionResult}</textarea>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</c:if>--%>
        <c:if test="${(questionInfo.processStatus==3||questionInfo.processStatus==6)&&(questionInfo.allocateUser==userId||isManager==0)&&status!='6'}">
            <div class="column-2 large-list" id="suggestEditDiv">
                <strong>打回意见</strong>
                <div class="collect-list-text" style="width: 80%;">
                    <textarea id="suggestEdit" style="font-size:12px">${questionInfo.solutionResult}</textarea>
                </div>
            </div>
        </c:if>
    </div>
    <div class="wrap-foot large-btn">
        <c:if test="${(questionInfo.processStatus==1||questionInfo.processStatus==7)&&isManager==0}">
            <a href="javascript:void(0);" onclick="goUpdate();"><span>编辑</span></a>
            <a href="javascript:void(0);" onclick="goDistribute();"><span>查看分配</span></a>
        </c:if>
        <%--项目经理未处理--%>
        <c:if test="${(questionInfo.processStatus==3||questionInfo.processStatus==6)&&isManager==0&&status=='3,6'}">
            <a href="javascript:void(0);" onclick="changeStatus(0,7,'打回');"><span>打回</span></a>
            <a href="javascript:void(0);" onclick="changeStatus(0,4,'确认');"><span>确认完成</span></a>
        </c:if>
        <%--工程师待接受--%>
        <c:if test="${questionInfo.processStatus==2&&questionInfo.allocateUser==userId&&isManager==1}">
            <a href="javascript:void(0);" onclick="changeStatus(1,7,'拒绝');"><span>拒绝</span></a>
            <a href="javascript:void(0);" onclick="changeStatus(1,3,'接受');"><span>接受</span></a>
        </c:if>
        <%--工程师未处理--%>
        <c:if test="${(questionInfo.processStatus==3||questionInfo.processStatus==6)&&isManager==1&&status!='3,6'}">
            <a href="javascript:void(0);" onclick="changeStatus(1,7,'打回');"><span>打回</span></a>
            <a href="javascript:void(0);" onclick="changeStatus(1,4,'确认');"><span>确认完成</span></a>
        </c:if>
    </div>
</div>
<div class="modal fade text-center" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-xs">
        <div class="modal-content">
            <img id="imgInModalID" src="" onclick="closeModal()">
        </div>
    </div>
</div>
<input id="status" type="hidden" value="${status}"/>
</body>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/zoomify/js/zoomify.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        enterprise.init();
        setListLevel(${questionInfo.priority});
        //工程师打回：7
        let processStatus =${questionInfo.processStatus};
        let status = $("#status").val();
        if (status == "1,7") {
            //待分配
            if (processStatus == 7) {
                $("#solutionResult").show();
                $("#userMessage").show();
                $("#suggest").show();
            }

        } else if (status == "2") {
            //待接受
            $("#solutionResult").hide();
            $("#userMessage").hide();
            $("#suggest").hide();
        } else if (status == "3,6") {
            //已接收未处理
            if (processStatus == 3) {
                $("#userMessage").hide();
            } else {
                $("#userMessage").show();
            }
        } else if (status == "4,5") {
            //已处理
            if (processStatus == 4) {
                $("#solutionResult").show();
            } else {
                $("#solutionResult").show();
                $("#userMessage").show();
            }

        } else if (status == "6") {
            //已打回
            $("#solutionResult").show();
            $("#userMessage").show();

        } else if( status == "5"){
            $("#solutionResult").show();
            $("#userMessage").show();
            $("#suggest").show();
        }else {
            $("#solutionResult").hide();
            $("#userMessage").hide();
            $("#suggest").hide();
        }

        $('.zoomify').zoomify();
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

    function changeStatus(isManager, option, optionName) {
        let processStatus =${questionInfo.processStatus};
        let solutionResult = $("#solutionResultEdit").val();
        let suggest = $("#suggestEdit").val();
        if (option == 4) {
            if (solutionResult == null || solutionResult == "") {
                mui.toast('请输入解决方案', {duration: 'long', type: 'div'});
                return;
            }
        }
        if (option == 7 && processStatus != 2) {
            if (suggest == null || suggest == "") {
                mui.toast('请输入打回意见', {duration: 'long', type: 'div'});
                return;
            }
        }
        let serialNo =${questionInfo.serialNo};
        let id = ${questionInfo.id};
        let userId =${userId};
        $.ajax({
            type: "POST",
            url: "<%=basePath%>mobile/wechatSiteQuestion/changeStatus.do",
            data: {
                id: id,
                suggest: suggest,
                solutionResult: solutionResult,
                userId: userId,
                serialNo: serialNo,
                option: option
            },
            cache: false,
            dataType: "json",
            async: false,
            error: function (request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。', {duration: 'long', type: 'div'})
            },
            success: function (data) {
                if (data.status) {
                    if (option == 0) {
                        mui.toast(optionName + '成功', {duration: 'long(3500ms)', type: 'div'});
                    } else {
                        mui.toast(optionName + '成功', {duration: 'long(3500ms)', type: 'div'});
                    }
                    //追加图片预览
                    // setTimeout("location.reload()", 3500);
                } else {
                    if (option == 0) {
                        mui.toast(optionName + '失败', {duration: 'long(3500ms)', type: 'div'});
                    } else {
                        mui.toast(optionName + '失败', {duration: 'long(3500ms)', type: 'div'});

                    }
                    //追加图片预览
                    //setTimeout("location.reload()",3500);
                }
                window.location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=" + userId + "&serialNo=" + serialNo;
            }
        });
    }

    /**
     * 图片预览
     * @param url
     */
    function showImage(url) {
        var height = document.body.offsetHeight * 0.9;
        var width = document.body.offsetWidth * 0.9;
        $('#imgInModalID').attr('src', url);
        $('#imgInModalID').attr('height', height);
        $('#imgInModalID').attr('width', width);
        $('#imgModal').modal('show');
    }

    /**
     * 图片点击关闭
     */
    function closeModal() {
        $('#imgModal').modal('hide');
    }
</script>
</html>
