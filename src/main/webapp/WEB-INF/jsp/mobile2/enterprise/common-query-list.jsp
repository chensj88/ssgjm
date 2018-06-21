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
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
   <%-- <style>
        .index-date>a{
            position: relative;
            display: block;
            height: 50px;
            font-size: 14px;
            color: #333333;
            border-bottom: 1px solid #F3F5F7;
        }
        .index-date_txt{
            display: inline-block;
            width: 70%;
            line-height: 50px;
            padding-left: 14px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>--%>
</head>
<body>
<%--数据传输隐藏域--%>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<input id="status" type="hidden" name="status" value="${status}">
<input id="searchType" type="hidden" name="searchType" value="${searchType}">
<input id="searchText" type="hidden" name="searchText" value="${searchText}">
<input id="isManager" type="hidden" name="isManager" value="${isManager}">
<%--主页面--%>
<div class="wrap">
    <div class="wrap-cnt">
        <%--<div>
            <form  action="javascript:search();">
            <div class="imple-work-search">
                <i class="iconfont icon-search" onclick="search()"></i>
                <input id="search_text" type="text" placeholder="请输入搜索内容" />
            </div>
            </form>
            <div class="wap-tab">
                <span type="1" onclick="openQuestionQueryList(1)">系统名称</span>
                <span type="2" onclick="openQuestionQueryList(2)" class="active">关键字</span>
                <span type="3" onclick="openQuestionQueryList(3)">科室病区</span>
            </div>
            <div class="index-date">
                <p style="margin-left:20px;font-weight: bold; ">历史记录</p>
                <c:forEach var="vwr" items="${logList}">
                    <a href="<%=basePath%>mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=${vwr.processStatus}&searchType=${vwr.sourceType}&searchText=${vwr.content}&userType=${userType}">
                        <span class="index-date_txt">${vwr.content}</span>
                    </a>
                </c:forEach>
            </div>
        </div>--%>
            <div class="space"></div>
            <form  action="javascript:search();">
                <div class="management-search">
                    <i class="iconfont icon-search" onclick="search()"></i>
                    <input type="text" placeholder="请输入搜索内容" id="search_text">
                </div>
            </form>
            <div class="site-question">
                <!--tab-->
                <div class="tab">
                    <div class="tab-nav">
                        <div type="1">
                            <a  onclick="openQuestionQueryList(1)" href="#">系统名称</a>
                        </div>
                        <div class="active" type="2">
                            <a  onclick="openQuestionQueryList(2)" href="#">关键词</a>
                        </div>
                        <div type="3">
                            <a  onclick="openQuestionQueryList(3)" href="#">科室病区</a>
                        </div>
                    </div>
                    <div class="tab-cntent">
                        <c:forEach var="vwr" items="${logList}">
                            <a href="<%=basePath%>mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=${vwr.processStatus}&searchType=${vwr.sourceType}&searchText=${vwr.content}&isManager=${isManager}">
                                <span class="tab_txt">${vwr.content}</span>
                            </a>
                        </c:forEach>
                    </div>
                </div>
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
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        IMS.init();
        IMS.menuTab();
        $('#search_text').val('');
    })

    /**
     * 修改搜索问题类型的类型
     * @param questionType
     */
    function openQuestionQueryList(searchType) {
            var searchText = $('#search_text').val();
            if (searchText == null || searchText == '') {
                mui.toast('搜索内容不能为空', {duration: 'long(3500ms)', type: 'div'});
                return false;
            }
            location.href = "<%=basePath%>mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=${status}&searchType="+searchType+"&searchText="+searchText+"&isManager=${isManager}";
    }
    
    function search() {
        var searchText = $('#search_text').val();
        if (searchText == null || searchText == '') {
            mui.toast('搜索内容不能为空', {duration: 'long(3500ms)', type: 'div'});
            return false;
        }
        var searchType = $($('.tab-nav').find('.active')[0]).attr('type');

        location.href = "<%=basePath%>mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=${status}&searchType="+searchType+"&searchText="+searchText+"&isManager=${isManager}";
    }

    function openIndexPage() {
        location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo=${serialNo}";
    }
</script>
</body>
</html>
