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
	</head>

	<body>
		<div class="mui-content bridge">
			<div class="mui-row bridge-item">
				<c:forEach var="repoType" items="${repoTypeList}" >
					<a href="<%=basePath%>mobile/trainVideoList/video.do?video_type=${repoType.videoType}&OPENID=${OPENID}" class="mui-col-xs-12">${repoType.videoType}</a>
				</c:forEach>

			    <a class="mui-col-xs-12">门诊类视频</a>
			    <a class="mui-col-xs-12">护理类视频</a>

			</div>
		</div>
	</body>

</html>