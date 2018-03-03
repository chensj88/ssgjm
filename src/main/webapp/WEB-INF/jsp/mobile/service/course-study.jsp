<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>课程学习</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
	</head>

	<body>
		<div class="mui-content course">
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft"></span>
				<div>站点安装登记</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<!--study-->
			<div class="course-study">
				<div class="left">
					<p>学习耗时(小时) </p>
					<strong>${timeNum}</strong>
				</div>
				<div class="right">
					<p>学习视频数</p>
					<strong><i>${study_num}</i>/${size}</strong>
				</div>
			</div>
			<!--tab-->
			<div class="course-tab">
				<div class="top">
					<ul class="clearfix">
						<li class="active">全部（32）</li>
						<li>已学习（18）</li>
						<li>未学习（14）</li>
					</ul>
				</div>
				<div class="btm">
					<div class="current">
						<c:forEach var="vwr" items="${videoWithRecoed}"  >
							<a href="<%=basePath%>mobile/trainVideoList/videoPlay.do?id=${vwr.id}">
								<dl class="item">
									<dt><img src="<%=basePath%>resources/mobile/images/video.png"/></dt>
									<dd class="item-title">(全部)医生接诊及诊断录入<span>-门诊医生站</span></dd>
									<dd class="item-time">视频时长 <span>04:06</span></dd>
									<dd class="item-count">
										<span>学习  <strong>${NUM == null ?0:NUM}</strong>次</span>
									</dd>
								</dl>
							</a>

						</c:forEach>

						<a href="course-detail.jsp">
							<dl class="item">
								<dt><img src="<%=basePath%>resources/mobile/images/video.png"/></dt>
								<dd class="item-title">医生接诊及诊断录入<span>-门诊医生站</span></dd>
								<dd class="item-time">视频时长 <span>04:06</span></dd>
								<dd class="item-count">
									<span>学习  <strong>1</strong>次</span>
								</dd>
							</dl>
						</a>
						<a href="course-detail.jsp">
							<dl class="item">
								<dt><img src="<%=basePath%>resources/mobile/images/video.png"/></dt>
								<dd class="item-title">医生接诊及诊断录入<span>-门诊医生站</span></dd>
								<dd class="item-time">视频时长 <span>04:06</span></dd>
								<dd class="item-count">
									<span>学习  <strong>0</strong>次</span>
								</dd>
							</dl>
						</a>
					</div>
					<div>
						<a href="course-detail.jsp">
							<dl class="item">
								<dt><img src="<%=basePath%>resources/mobile/images/video.png"/></dt>
								<dd class="item-title">（已学习）医生接诊及诊断录入<span>-门诊医生站</span></dd>
								<dd class="item-time">视频时长 <span>04:06</span></dd>
								<dd class="item-count">
									<span>学习  <strong>2</strong>次</span>
								</dd>
							</dl>
						</a>
					</div>
					<div>
						<a href="course-detail.jsp">
							<dl class="item">
								<dt><img src="<%=basePath%>resources/mobile/images/video.png"/></dt>
								<dd class="item-title">（未学习）医生接诊及诊断录入<span>-门诊医生站</span></dd>
								<dd class="item-time">视频时长 <span>04:06</span></dd>
								<dd class="item-count">
									<span>学习  <strong>2</strong>次</span>
								</dd>
							</dl>
						</a>
					</div>
				</div>
			</div>
		</div>
		<ul>
		</ul>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				IMS.init();
			})
		</script>
	</body>

</html>