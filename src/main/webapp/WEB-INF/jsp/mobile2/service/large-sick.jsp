<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>院方确认</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
</head>
<body>
<div class="wrap">
    <div class="wrap-header">
        <div class="header">
            <span class="mui-icon mui-icon-arrowleft"  onclick="javascript:history.go(-1)"></span>
            <div>院方确认</div>
            <a href="#">更新记录</a>
        </div>
    </div>
    <div class="wrap-cnt">
        <div class="column-2 large-list">
            <strong>科室病区</strong>
            <span>${siteQuestionInfo.map.get("deptName")}</span>
        </div>
        <div class="column-2 large-list">
            <strong>系统名称</strong>
            <span>${siteQuestionInfo.map.get("plName")}</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题标题</strong>
            <span>${siteQuestionInfo.menuName.trim()}</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题描述</strong>
            <span>${siteQuestionInfo.questionDesc}</span>
        </div>
        <div class="column-2 large-list">
            <strong>优先等级</strong>
            <span class="levelA"></span>
        </div>
        <div class="column-2 large-list">
            <strong>影像资料</strong>
        </div>
        <div class="column-2 large-list">
					<span class="large-img">
                    <c:if test="${siteQuestionInfo.imgPath !=null && siteQuestionInfo.imgPath !=''}">
                        <c:forEach var="img" items="${siteQuestionInfo.imgs}">
						<img src="<%=Constants.FTP_SHARE_FLODER%>${img}" alt="">
                        </c:forEach>
                    </c:if>
					</span>
        </div>
        <div class="column-2 large-list">
            <strong>解决方案</strong>
            <span>${siteQuestionInfo.solutionResult}</span>
        </div>
        <c:if test="${siteQuestionInfo.processStatus == 4}">
        <div class="space"></div>
        <div class="column-2 large-list">
            <strong>院方意见</strong>
        </div>
        <div class="column-2 large-list">
					<span>
						<textarea id="suggestDesc">${siteQuestionInfo.suggest}</textarea>
					</span>
        </div>
    </div>
    <div class="wrap-foot large-btn">
        <a href="#" id="refuse"><span>打回</span></a>
        <a href="#" id="complete"><span>确认完成</span></a>
    </div> </c:if>
</div>
<script type="text/javascript" src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/mobile/js/mui.min.js"></script>
<script type="text/javascript">

    $(function () {
        $('#refuse').click(function () {
            mui.confirm('','确认是否打回该工作',function (obj) {
                if(obj.index === 0){
                    console.log('取消');
                }else if(obj.index === 1){
                    var suggestDesc = $("#suggestDesc").val();
                    if(suggestDesc !=null && suggestDesc !=''){
                        status(6,suggestDesc);//院方退回
                    }else{
                        console.log('确认');
                        mui.toast('请输入您的意见!',{ duration:2000, type:'div' });
                    }

                }
            })
        });
        $('#complete').click(function () {
            status(5,null);//院方确认完成
             });
        setListLevel(${siteQuestionInfo.priority});

    });


    function status(val,suggest){
        var id = ${siteQuestionInfo.id};
        var userId = ${userId};
        var serialNo = ${serialNo};
        $.ajax({
            type: "POST",
            url:"<%=basePath%>/mobile/tempSiteQuestion/processStatus.do",
            data:{id:id,val:val,suggest:suggest,serialNo:serialNo,userId:userId},
            dataType:"json",
            error: function(request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
            },
            success: function() {
                if(val==5){
                    mui.toast('确认成功',{ duration:600, type:'div' });
                    setTimeout(location.href="<%=basePath%>/mobile/tempSiteQuestion/list.do?processStatus=1",600);
                }else{
                    mui.toast('打回成功',{ duration:600, type:'div' });
                    setTimeout(location.href="<%=basePath%>/mobile/tempSiteQuestion/list.do?processStatus=0",600);
                }
            }
        });
    }




    function setListLevel(val){
        var valStr = convertTypeToInt(val);
        $(".levelA").text(valStr);
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
            default: 'D'
        }
        return valStr;
    }

</script>
</body>
</html>

