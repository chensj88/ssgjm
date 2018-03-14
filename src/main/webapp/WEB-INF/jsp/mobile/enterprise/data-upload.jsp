<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>实施资料上传</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_l39bx41qun2ke29.css"/>
		<style type="text/css">
		</style>
	</head>

	<body>
		<div class="mui-content datum">
		    <!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>实施资料上传</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>调研报告</span>
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=1&serialNo=${serialNo}&userId=${userId}">
						<i class="mui-icon mui-icon-arrowright" ></i></a>
				</div>
				<ul class="datum-row-img clearfix">
					<li class="datum-row-add">
						<i class="iconfont icon-plus"></i>
						<input type="file" name="" id="" value="" />
					</li>
					<c:forEach var="vul" items="${onlineFileList_one}">
					<li>
						<h3>${vul.map.get("dictLabel")}</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>${vul.dataName}</span>
								</p>
							</div>
						</div>
					</li>
					</c:forEach>

				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>单据</span>
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=2&serialNo=${serialNo}&userId=${userId}&">
						<i class="mui-icon mui-icon-arrowright" ></i></a>
				</div>
				<ul class="datum-row-img clearfix">
					<li class="datum-row-add"><i class="iconfont icon-plus"></i></li>
					<c:forEach var="vul" items="${onlineFileList_two}">
						<li>
							<h3>${vul.dataType}</h3>
							<div class="datum-middle">
								<div>
									<p>
										<span>${vul.dataName}</span>
									</p>
								</div>
							</div>
						</li>
					</c:forEach>
				
				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>上线联调报告</span>
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=3&serialNo=${serialNo}&userId=${userId}">
						<i class="mui-icon mui-icon-arrowright" ></i></a>
				</div>
				<ul class="datum-row-img clearfix">
					<li class="datum-row-add"><i class="iconfont icon-plus"></i></li>
					<li>
						<h3>报告</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>签字确认单</span>
								</p>
							</div>
						</div>
					</li>
					<li>
						<h3>确认单</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>上线联调报告</span>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>上线切换方案</span>
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=4&serialNo=${serialNo}&userId=${userId}">
						<i class="mui-icon mui-icon-arrowright" ></i></a>
				</div>
				<ul class="datum-row-img clearfix">
					<li class="datum-row-add"><i class="iconfont icon-plus"></i></li>
					<li>
						<h3>报告</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>上线联调报告</span>
								</p>
							</div>
						</div>
					</li>
					<li>
						<h3>确认单</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>签字确认单</span>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
		</script>
	</body>

</html>