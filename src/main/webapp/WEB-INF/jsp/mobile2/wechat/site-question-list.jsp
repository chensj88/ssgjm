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
    <title>采集列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
</head>
<body>
<div class="wrap">
    <%--<div class="wrap-header">--%>
        <%--<div class="header">--%>
            <%--<span class="mui-icon mui-icon-arrowleft" onclick="openIndexPage()"></span>--%>
            <%--<input id="userId" type="hidden" name="userId" value="${userId}">--%>
            <%--<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">--%>
            <%--<div>采集列表</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="wrap-cnt">
        <input id="userId" type="hidden" name="userId" value="${userId}">
        <input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
        <div>
            <form  action="javascript:search();">
                <div class="imple-work-search">
                    <i class="iconfont icon-search" onclick="search()"></i>
                    <input id="search_text" type="text" value="${search_text == null ? '' : search_text }" placeholder="请输入搜索内容" />
                </div>
            </form>
            <c:forEach var="vwr" items="${questionList}">
                <a href="#" class="row">
                    <i class="iconfont icon-time"></i>${vwr.groupName}<b>（${vwr.num}条）</b>
                </a>
                <ul class="mui-table-view OA_task_1">
                <c:forEach var="vwr1" items="${vwr.listQuery}">
                    <li class="mui-table-view-cell">
                        <div class="mui-slider-right mui-disabled">
                            <a vid="${vwr1.id}" vstatus="${vwr1.processStatus}" vrequirementNo="${vwr1.requirementNo}" class="mui-btn mui-btn-red">删除</a>
                        </div>
                        <div class="mui-slider-handle collect-item" onclick="detail(${vwr1.id},${vwr1.processStatus})">
                            <h3>${vwr1.map.deptName}-${vwr1.menuName}</h3>
                            <p>
                                <span class="status${vwr1.map.priorityString}">${vwr1.map.priorityString}</span>
                                <c:choose>
                                    <c:when test="${vwr1.processStatus==1}">
                                        <span class="distribution">${vwr1.map.processStr}</span>
                                    </c:when>
                                    <c:when test="${vwr1.processStatus == 2 || vwr1.processStatus == 3 || vwr1.processStatus == 4}">
                                        <span class="distributioned">${vwr1.map.processStr}</span>
                                    </c:when>
                                    <c:when test="${vwr1.processStatus == 5}">
                                        <span class="distributioned" style="color: #000;border-color: #000;">${vwr1.map.processStr}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="no-distribution" style="color: red;border-color: red;">${vwr1.map.processStr}</span>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </c:forEach>
        </div>
        <div class="hide" >

        </div>
        <div class="hide">
            正在建设中
        </div>
        <div class="hide">
            正在建设中
        </div>
    </div>
    <!--新增-->
    <a href="<%=basePath%>mobile/wechatSiteQuestion/addPage.do?serialNo=${serialNo}&userId=${userId}&source=2" class="wrap-add">
        <i class="iconfont icon-add"></i>
    </a>
    <!--底部菜单-->
    <div class="wrap-foot">
        <div class="active" onclick="openIndexPage()">
            <i class="iconfont icon-ck"></i>
            查看
        </div>
        <div onclick="videoLoad()">
            <i class="iconfont icon-sp"></i>
            视频
        </div>
        <div>
            <i class="iconfont icon-fx"></i>
            分析
        </div>
        <div>
            <i class="iconfont icon-wo"></i>
            我
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
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

    /**
     * 跳转问题详情页
     */
    function detail(id,status){
        if( status === 1 ){
            location.href="<%=basePath%>mobile/wechatSiteQuestion/addPage.do?questionId="+id+"&serialNo=${serialNo}&userId=${userId}&source=2";
        }else{
            location.href="<%=basePath%>mobile/tempSiteQuestion/addPage.do?questionId="+id+"&serialNo=${serialNo}&userId=${userId}&source=2";
        }
    }

    /**
     * 跳转首页
     */
    function openIndexPage() {
        location.href="<%=basePath%>mobile/tempSiteQuestion/laodList.do?processStatus=4&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();
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

    function  search() {
        location.href="<%=basePath%>/mobile/wechatSiteQuestion/list.do?searchText="+$("#search_text").val()+"&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();
    }

    function videoLoad() {
        location.href="<%=basePath%>/mobile/trainVideoList/list.do?openId="+$("#openId").val()+"&userId="+$("#userId").val()+
            "&serialNo="+$("#serialNo").val();
    }
</script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        IMS.menuTab();
    })
</script>
</html>
