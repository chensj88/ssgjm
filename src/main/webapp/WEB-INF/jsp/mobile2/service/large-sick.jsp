<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/13
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <span class="mui-icon mui-icon-arrowleft"></span>
            <div>特大号病号</div>
            <a href="#">更新记录</a>
        </div>
    </div>
    <div class="wrap-cnt">
        <div class="column-2 large-list">
            <strong>科室病区</strong>
            <span>耳鼻喉科第二病区</span>
        </div>
        <div class="column-2 large-list">
            <strong>系统名称</strong>
            <span>NIS护士站部署</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题标题</strong>
            <span>部署结束网络异常，打印机异常</span>
        </div>
        <div class="column-2 large-list">
            <strong>问题描述</strong>
            <span>使用过程中打印机异常，保存使用异常，导出文件异常，结束操作异常，系统闪退</span>
        </div>
        <div class="column-2 large-list">
            <strong>优先等级</strong>
            <span class="levelA">A级</span>
        </div>
        <div class="column-2 large-list">
            <strong>影响资料</strong>
        </div>
        <div class="column-2 large-list">
					<span class="large-img">
						<img src="../images/sick.png" alt="">
						<img src="../images/sick.png" alt="">
						<img src="../images/sick.png" alt="">
						<img src="../images/sick.png" alt="">
					</span>
        </div>
        <div class="column-2 large-list">
            <strong>解决方案</strong>
            <span>使用过程中打印机异常，保存使用异常，导出文件异常，结束操作异常，系统闪退</span>
        </div>
        <div class="space"></div>
        <div class="column-2 large-list">
            <strong>院方意见</strong>
        </div>
        <div class="column-2 large-list">
					<span>
						<textarea></textarea>
					</span>
        </div>
    </div>
    <div class="wrap-foot large-btn">
        <a href="#" id="refuse"><span>打回</span></a>
        <a href="#" id="complete"><span>确认完成</span></a>
    </div>
</div>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/mui.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#refuse').click(function () {
            mui.confirm('','确认是否打回该工作',function (obj) {
                if(obj.index === 0){
                    console.log('取消');
                }else if(obj.index === 1){
                    console.log('确认');
                }
            })
        });
        $('#complete').click(function () {
            mui.toast('确认成功',{ duration:600, type:'div' })
        });
    });
</script>
</body>
</html>

