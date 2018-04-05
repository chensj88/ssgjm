<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>课程学习-详情页</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
		<script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="mui-content detail">
			<!--header-->
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>课程学习-详情页</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>

			<div class="detail-play">
				<video width="100%" height="200" controls>

					<source src="<%=basePathNuName%>shareFolder${repo.remotePath}" type="video/mp4">
					<source src="<%=basePathNuName%>shareFolder${repo.remotePath}" type="video/avi">
					<source src="<%=basePathNuName%>shareFolder${repo.remotePath}" type="video/wmv">

					您的浏览器不支持 HTML5 video 标签。
				</video>
				<div class="course-tab">
					<div class="btm">
						<dl class="item">
							<dd class="item-title">${repo.videoName}<span>-${repo.typeLabel}</span></dd>
							<dd class="item-time">视频时长 <span><script>
										document.write(Common.getHHMMSSDate(${repo.videoTime}));
									</script></span></dd>
							<dd class="item-count">
								<span>学习  <strong>${num}</strong>次</span>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			<div class="detail-support">相关视频</div>
			<!--tab-->
			<div class="course-tab">
				<div class="btm">
					<c:forEach var="vwr" items="${videoWithRecoed}"  >
					<a href="<%=basePath%>mobile/trainVideoList/videoPlay.do?id=${vwr.id}&OPENID=${OPENID}">
						<dl class="item">
							<dt><img src="<%=basePathNuName%>shareFolder${vwr.imgPath}"/></dt>
							<dd class="item-title">${vwr.videoName}<span>-${vwr.typeLabel}</span></dd>
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
			</div>
		</div>
		<ul>
		</ul>
		<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			mui.init()
		</script>
	</body>

</html>