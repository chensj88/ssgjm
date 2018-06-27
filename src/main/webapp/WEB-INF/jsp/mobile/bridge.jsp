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
		<div class="wrap">

			<%--<div class="wrap-header">--%>
				<%--<div class="header">--%>
				<%--<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>--%>
				<%--<div>课程视频分类</div>--%>
				<%--<span class="mui-icon mui-icon-more"></span>--%>
			<%--</div>
			</div>--%>
			<div class="wrap-cnt">
				<div class="bridge">
					<div class="bridge-item">
						<c:forEach var="repoType" items="${repoTypeList}" >
							<a href="<%=basePath%>mobile/trainVideoList/video.do?video_type=${repoType.videoType}&OPENID=${OPENID}" >
								<i class="${repoType.dictDesc}"></i>
								<span>${repoType.typeLabel}</span>
							</a>
						</c:forEach>
					</div>
				</div>
				<div class="hide">
					正在建设中
				</div>
				<div class="hide">
					正在建设中
				</div>
			</div>

			<!--底部菜单-->
			<div class="wrap-foot">
				<div  onclick="openIndexPage()">
					<i class="iconfont icon-ck"></i>
					查看
				</div>
				<div onclick="videoLoad();" class="active">
					<i class="iconfont icon-sp"></i>
					视频
				</div>
				<div>
					<i class="iconfont icon-fx"></i>
					分析
				</div>
				<div>
					<a href="#popover" id="openPopover" class="iconfont icon-wo"
					   style="color: #A4A5AB;">
						<span style="color: #A4A5AB; display: block;">我</span>
					</a>
				</div>
			</div>
				<%--弹出菜单--%>
				<div id="popover" class="mui-popover">
					<ul class="mui-table-view" style="line-height:30px;">
						<li class="mui-table-view-cell" style="margin: 10px 10px 10px 10px;">
							<a href="<%=basePath%>/mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo=${serialNo}&service=1">进入管理端</a>
						</li>
					</ul>
				</div>
		</div>
	</body>
	<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
	<script type="text/javascript">
        $(function () {
            IMS.menuTab();
        })
		function openIndexPage() {
            location.href="<%=basePath%>mobile/tempSiteQuestion/laodList.do?processStatus=4&userId=${userId}&serialNo=${serialNo}";
        }
	</script>
</html>