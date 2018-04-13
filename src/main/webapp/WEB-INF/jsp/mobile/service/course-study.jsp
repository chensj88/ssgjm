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
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
		<script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="mui-content course">
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>课程学习</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="course-study">
				<div class="left">
					<p>学习耗时(小时) </p>
					<strong><script>
                        document.write(Common.getHHMMSSDate(${timeNum}));
					</script></strong>
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
						<li class="active">全部（${size}）</li>
						<li>已学习（${study_num}）</li>
						<li>未学习（${size-study_num}）</li>
					</ul>
				</div>
				<div class="btm">
					<div class="current">
						<c:forEach var="vwr" items="${videoWithRecoed}"  >

							<a href="<%=basePath%>mobile/trainVideoList/videoPlay.do?id=${vwr.id}&OPENID=${OPENID}">
								<dl class="item">
									<dt><img src="<%=basePathNuName%>shareFolder${vwr.imgPath}"/></dt>
									<dd class="item-title"><span>${vwr.videoName}</span></dd>
									<dd class="item-time">视频时长 <span><script>
										document.write(Common.getHHMMSSDate(${vwr.videoTime}));
									</script></span></dd>
									<dd class="item-count">
										<span>学习  <strong>${vwr.map.get("num")==null ?0:vwr.map.get("num")}</strong>次</span>
									</dd>
								</dl>
							</a>

						</c:forEach>

					</div>


                    <div>
						<c:forEach var="studied" items="${sysTrainVideoStudied}"  >
							<a href="<%=basePath%>mobile/trainVideoList/videoPlay.do?id=${studied.id}&OPENID=${OPENID}">
								<dl class="item">
									<dt><img src="<%=basePathNuName%>shareFolder${studied.imgPath}"/></dt>
									<dd class="item-title"><span>${studied.videoName}</span></dd>
									<dd class="item-time">视频时长 <span><script>
										document.write(Common.getHHMMSSDate(${studied.videoTime}));
									</script></span></dd>
									<dd class="item-count">
										<span>学习  <strong>${studied.map.get("num")==null ?0:studied.map.get("num")}</strong>次</span>
									</dd>
								</dl>
							</a>

						</c:forEach>
                    </div>

					<div>
						<c:forEach var="studied" items="${sysTrainVideoUnStudy}"  >
							<a href="<%=basePath%>mobile/trainVideoList/videoPlay.do?id=${studied.id}&OPENID=${OPENID}">
								<dl class="item">
									<dt><img src="<%=basePathNuName%>shareFolder${studied.imgPath}"/></dt>
									<dd class="item-title"><span>${studied.videoName}</span></dd>
									<dd class="item-time">视频时长 <span><script>
										document.write(Common.getHHMMSSDate(${studied.videoTime}));
									</script></span></dd>
									<dd class="item-count">
										<span>学习  <strong>${studied.map.get("num")==null ?0:studied.map.get("num")}</strong>次</span>
									</dd>
								</dl>
							</a>

						</c:forEach>
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