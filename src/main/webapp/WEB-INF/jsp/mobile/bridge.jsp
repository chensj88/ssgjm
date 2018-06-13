<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8" />
		<title>课程学习-详情页</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/fonts/iconfont.css"/>
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_8s9fwys71yxmvx6r.css"/>
	</head>

	<body>
		<!--header-->
		<div class="header">
			<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
			<div>课程视频分类</div>
			<span class="mui-icon mui-icon-more"></span>
		</div>
		<div class="hole"></div>

		<div class="bridge">
			<div class="bridge-item">
				<c:forEach var="repoType" items="${repoTypeList}" >
					<a href="<%=basePath%>mobile/trainVideoList/video.do?video_type=${repoType.videoType}&OPENID=${OPENID}" >
						<i class="${repoType.dictDesc}"></i>
						<span>${repoType.typeLabel}</span>
					</a>


				</c:forEach>

			</div>
			<!--底部菜单-->
			<div class="wrap-foot">
				<div class="active">
					<a href="<%=basePath%>/mobile/trainVideoList/list.do">  </a>
					<i class="iconfont icon-ck"></i>
					查看
				</div>
				<div onclick="videoLoad();">
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

</html>