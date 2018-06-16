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
</head>
<body>
<div class="wrap">
    <div class="wrap-cnt">
        <div>
            <div class="index-link">
                <a href="#">
                    <i class="iconfont icon-dfp"></i>
                    待分配 (9)
                </a>
                <a href="#">
                    <i class="iconfont icon-dcl"></i>
                    未处理 (10)
                </a>
                <a href="#">
                    <i class="iconfont icon-ycl"></i>
                    处理 (10)
                </a>
                <a href="#">
                    <i class="iconfont icon-ydh"></i>
                    已打回 (90)
                </a>
            </div>
            <div class="index-date">
                <c:forEach var="vwr" items="${questionList}">
                    <p>${vwr.groupName}  （${vwr.num}条）</p>
                    <a href='#'>
                        <span class="index-date_txt">五楼护士站-NIS部署-特大病号</span>
                        <span class="index-date_status">
                                    <i class="index-${vwr1.map.priorityString}">${vwr1.map.priorityString}</i>
                                    <i class="index-wqr">未确认</i>
                                </span>
                    </a>


                </c:forEach>
            </div>
            <div class="index-date">
                <p>06-04（4条）</p>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号</span>
                    <span class="index-date_status">
								<i class="index-A">A</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号</span>
                    <span class="index-date_status">
								<i class="index-B">B</i>
								<i class="index-wqr">已打回</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特病号</span>
                    <span class="index-date_status">
								<i class="index-C">C</i>
								<i class="index-yqr">已确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号病号</span>
                    <span class="index-date_status">
								<i class="index-D">D</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
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
    <!--新增-->
    <a href="#" class="wrap-add">
        <i class="iconfont icon-add"></i>
    </a>
    <!--底部菜单-->
    <div class="wrap-foot">
        <div class="active">
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
<script src="../js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="../js/ims.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        IMS.menuTab();
    })
</script>
</body>
</html>

