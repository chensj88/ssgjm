<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>实施工作</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_8s9fwys71yxmvx6r.css"/>
	</head>
	<body>
		<div class="wrap">
			<div class="wrap-header">
				<div class="header">
					<span class="mui-icon mui-icon-arrowleft"></span>
					<div>实施工作</div>
					<a href="#">采集列表</a>
				</div>
			</div>
			<div class="wrap-cnt">
				<div>
					<!--search-->
					<div class="imple-work-search">
						<i class="iconfont icon-search"></i>
						<input type="text" placeholder="请输入搜索内容"/>
					</div>
					<!--tab-->
					<div class="wap-tab">
						<span class="active">未确认（23）</span>
						<span>已确认（27）</span>
					</div>
					<div class="space"></div>
					<div class="wap-tab-cnt">
						<div id="item0">
							<c:forEach var="vwr" items="${questionList}">
								<div>
								<a href="#" class="row">
								<i class="iconfont icon-time">${vwr.timeMmdd}</i>
								<b>（ ${vwr.num} 条）</b>
								</a>
								<c:forEach var="vwr1" items="${vwr.listQuery}">
									<a href="#" class="row">
												${vwr1.questionDesc}
											<span class="${vwr1.map.priorityString}">
													${vwr1.map.priorityString}
											</span>
									</a>
								</c:forEach>
								</div>
								</c:forEach>
						</div>
					</div>
				</div>
				<div class="hide">
					视频内容
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
					<i class="iconfont icon-ck"></i>
					查看
				</div>
				<div>
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
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function () {
                IMS.menuTab();
            })
		</script>
	</body>
</html>
