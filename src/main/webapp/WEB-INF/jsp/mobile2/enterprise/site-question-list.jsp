<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/16
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        *{
            font-size: 12px;
        }
        .wap-tab{
            display: flex;
            margin-bottom: 0px;
            padding: 0px 0px;
        }
        .wap-tab>span{
            flex: 1;
            height: 30px;
            line-height: 30px;
            font-size: 12px;
            color: #666666;
            text-align: center;
            border: 1px solid #FFFFFF;
        }
        .wap-tab>span.active{
            color: #fff;
            background: #81B3FF;
            border: 1px solid #81B3FF;
        }
        .divTitle{
            margin: 0px;
            padding: 0px;
            line-height: 25px;
            height: 30px;
            line-height: 30px;
        }
        .divTitle > a{
            margin: 0px;
            padding: 0px;
            height: 30px;
            line-height: 30px;
            position: relative;
            display: block;
            color: #333333;
            position: relative;
        }
        .index-date-status{
            position: absolute;
            top:0px;
            right: 14px;
        }
        .index-date_text{
            display: inline-block;
            height: 30px;
            width: 70%;
            line-height: 30px;
            padding-left: 14px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .index-date_status{
            position: absolute;
            top:16px;
            right: 14px;
        }
        li.liTitle{
            /*padding: 0 10px;*/
            line-height: 30px;
            padding: 5px 10px;
        }

        li.liTitle:after{
            display: none;
        }
        a.row{
            font-size: 12px;
            color:#97AACC;
            background:#F3F5F7;


        }
        a.index-date{
            padding-left: 14px;
            line-height: 30px;
            height: 30px;
            background:#F3F5F7;
            color: #97AACC ;
            font-size: 12px;
            margin-bottom: 0px;
        }
        .collect-item{
            font-size: 12px;
            color: #333333;
            /*border-bottom: 1px solid #F3F5F7;*/
        }
    </style>
</head>
<body>
<%--数据传输隐藏域--%>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<input id="status" type="hidden" name="status" value="${status}">
<input id="searchType" type="hidden" name="searchType" value="${searchType}">
<input id="searchText" type="hidden" name="searchText" value="${searchText}">
<input id="isManager" type="hidden" name="isManager" value="${isManager}">
<input id="priority" type="hidden" name="priority" value="${priority}">
<%--主页面--%>
<div class="wrap">
    <div class="wrap-cnt">
        <div>
            <c:forEach var="vwr" items="${questionList}">
                <a href="#" class="row index-date">${vwr.groupName}（${vwr.num}条）</a>
                <ul class="mui-table-view OA_task_1">
                    <c:forEach var="vwr1" items="${vwr.listQuery}">
                        <li class="mui-table-view-cell liTitle">
                            <div class="mui-slider-right mui-disabled">
                                <a vid="${vwr1.id}" vstatus="${vwr1.processStatus}" vrequirementNo="${vwr1.requirementNo}" class="mui-btn mui-btn-red">删除</a>
                            </div>
                            <div class="mui-slider-handle collect-item index-date divTitle" onclick="detail(${vwr1.id},${vwr1.processStatus})">
                                <a href="<%=basePath%>mobile/wechatSiteQuestion/goView.do?id=${vwr1.id}&serialNo=${serialNo}&userId=${userId}&isManager=${isManager}&status=${status}">
                                    <span class="index-date_text">${vwr1.map.deptName}-${vwr1.menuName}</span>
                                    <span class="index-date-status">
                                        <i class="index-${vwr1.map.priorityString}">${vwr1.map.priorityString}</i>
                                        <i class="index-wqr">${vwr1.map.processStr}</i>
                                    </span>
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>

        </div>
        <div class="hide">
            站点
        </div>
        <div class="hide">
            分享
        </div>
        <%--<div class="hide">--%>
            <%--我的--%>
        <%--</div>--%>
    </div>
    <!--新增-->
   <%-- <a href="<%=basePath%>/mobile/wechatSiteQuestion/addPage.do?userId=${userId}&serialNo=${serialNo}&source=2" class="wrap-add" >
        <i class="iconfont icon-add"></i>
    </a>--%>
    <!--底部菜单-->
    <%@ include file="/commons/footer.jsp" %>
</div>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
<script type="text/javascript">
    mui.init();
    (function($) {
        //第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
        $('.OA_task_1').on('tap', '.mui-btn', function(event) {
            var elem = this;
            var li = elem.parentNode.parentNode;
            var questionId = this.getAttribute('vid');
            $.ajax({
                type: "POST",
                url:"<%=basePath%>mobile/wechatSiteQuestion/checkQuestion.do",
                data:{id:questionId},
                cache : false,
                dataType:"json",
                async: false,
                error: function(request) {
                    mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                },
                success: function(data) {
                    if (data.status == "success") {
                        if(data.data == 1){
                            mui.toast('当前问题已分配或已导入系统，不允许删除！',{ duration:'long', type:'div' });
                            setTimeout(function() {
                                $.swipeoutClose(li);
                            }, 0);
                        }else{
                            mui.confirm('', '确认删除该条记录？',  ['确认','取消'], function(e) {
                                console.log(e);
                                if (e.index == 0) {
                                    li.parentNode.removeChild(li);
                                    deleteQuestion(questionId);
                                } else {
                                    setTimeout(function() {
                                        $.swipeoutClose(li);
                                    }, 0);
                                }
                            });
                        }
                    }
                }
            });
        });
    })(mui);
    $(function () {
        IMS.menuTab();
        if(${priority != null}){
            setListLevel(${priority});
        }else{
            $('#all').attr('class','active');
        }
    })
    function openQuestionDetail(id){
        location.href = "<%=basePath%>mobile/wechatSiteQuestion/goView.do?id="+id+"&serialNo=${serialNo}&userId=${userId}&isManager=${isManager}";
    }

    function detail(id,status) {
        let statusVar = '';
        if(status == 1 ||status == 7){
            statusVar = '1,7';
        }else {
            statusVar = '6';
        }
        location.href="<%=basePath%>mobile/wechatSiteQuestion/goView.do?id="+id+"&serialNo=${serialNo}&userId=${userId}&isManager=${isManager}&status="+statusVar;
    }
    /**
     * 打开查询页面
     */
    function openQueryWindows() {
        location.href = "<%=basePath%>mobile/commons/query.do?serialNo=${serialNo}&userId=${userId}&status=${status}&searchType=${searchType}&searchText=${searchText}&isManager=${isManager}";
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

    function setListLevel(val) {
        var spanArr = $('.wap-tab>span');
        var valStr = convertTypeToInt(val);
        for (var i = 0; i < spanArr.length; i++) {
            var text = $(spanArr[i]).text().substr(0, 1);
            if (text === valStr) {
                $(spanArr[i]).addClass('active').siblings('span').removeClass('active');
            }
        }
    }
    /**
     * 点击优先级获取信息
     * @param priority
     */
    function openQuestionListByPriority(priority) {
        if(!priority){
            location.href = "<%=basePath%>mobile/commons/list.do?serialNo=${serialNo}&userId=${userId}&status=${status}&searchType=${searchType}&searchText=${searchText}&isManager=${isManager}";
        }else{
            location.href = "<%=basePath%>mobile/commons/list.do?serialNo=${serialNo}&userId=${userId}&status=${status}&searchType=${searchType}&searchText=${searchText}&isManager=${isManager}&priority="+priority;
        }

    }

    /**
     * 问题删除
     * @param id
     */
    function deleteQuestion(id) {
        $.ajax({
            type: "POST",
            url:"<%=basePath%>mobile/wechatSiteQuestion/delete.do",
            data:{id:id},
            cache : false,
            dataType:"json",
            async: false,
            error: function(request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
            },
            success: function(data) {
                mui.toast('问题删除成功！',{ duration:'long', type:'div' })
            }
        });
    }
</script>
</body>
</html>
