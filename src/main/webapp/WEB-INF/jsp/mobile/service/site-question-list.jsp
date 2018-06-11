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
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/??sm.min.css,sm-extend.min.css">
</head>
<body>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav">
        <a class="button button-link button-nav pull-left" href="<%=basePath%>mobile/wechatSiteQuestion/list.do?serialNo=${serialNo}&userId=${userId}" data-transition='slide-out'>
            <span class="icon icon-left"></span>
            返回
        </a>
        <h1 class="title">我的生活</h1>
    </header>
        <nav class="bar bar-tab">
            <a class="tab-item active" href="#">
                <span class="icon icon-home"></span>
                <span class="tab-label">首页</span>
            </a>
            <a class="tab-item" href="#">
                <span class="icon icon-me"></span>
                <span class="tab-label">我</span>
            </a>
            <a class="tab-item" href="#">
                <span class="icon icon-star"></span>
                <span class="tab-label">收藏</span>
            </a>
            <a class="tab-item" href="#">
                <span class="icon icon-settings"></span>
                <span class="tab-label">设置</span>
            </a>
        </nav>
        <div class="content">
            <div class="page-index">
                <div class="card">
                    <div  valign="bottom" class="card-header color-white no-border">旅途的山</div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <p class="color-gray">发表于 2015/01/15</p>
                            <p>此处是内容...</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="link">赞</a>
                        <a href="#" class="link">更多</a>
                    </div>
                </div>
                <div class="card">
                    <div  valign="bottom" class="card-header color-white no-border">旅途的山</div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <p class="color-gray">发表于 2015/01/15</p>
                            <p>此处是内容...</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="link">赞</a>
                        <a href="#" class="link">更多</a>
                    </div>
                </div>
                ... 可以多放几张卡片
            </div>
        </div>
    </div>
</div>
<%--<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>--%>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/??sm.min.js,sm-extend.min.js' charset='utf-8'></script>
</body>

</html>
