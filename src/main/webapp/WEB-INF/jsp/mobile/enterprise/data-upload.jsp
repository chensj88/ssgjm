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
				</div>
				<ul class="datum-row-img clearfix">
					<!--
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=1&serialNo=${serialNo}&userId=${userId}">
					<li class="datum-row-add">
						<i class="iconfont icon-plus"></i>
					</li>
					</a> -->
					<c:forEach var="vul" items="${onlineFileList_one}">
					<a href="<%=basePath%>mobile/implementData/details.do?fileType=1&serialNo=${serialNo}&userId=${userId}&id=${vul.id}">
					<li>
						<h3>调研</h3>
						<div class="datum-middle">
							<div>
								<p>
									<span>${vul.flowName}</span>
								</p>
							</div>
						</div>
					</li>
					</a>
					</c:forEach>

				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>单据</span>
				</div>
				<ul class="datum-row-img clearfix">
					<c:forEach var="vul" items="${onlineFileList_two}">
                    <a href="<%=basePath%>mobile/implementData/details.do?fileType=2&serialNo=${serialNo}&userId=${userId}&id=${vul.id}">
                    <li>
							<h3>单据</h3>
							<div class="datum-middle">
								<div>
									<p>
										<span>${vul.reportName}</span>
									</p>
								</div>
							</div>
						</li>
                    </a>
					</c:forEach>
				
				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>上线联调报告</span>
				</div>
				<ul class="datum-row-img clearfix">

					<c:forEach var="vul" items="${onlineFileList_three}">
                    <a href="<%=basePath%>mobile/implementData/details.do?fileType=3&serialNo=${serialNo}&userId=${userId}&id=${vul.id}">
						<li>
							<h3>调研</h3>
							<div class="datum-middle">
								<div>
									<p>
										<span>${vul.dataName}</span>
									</p>
								</div>
							</div>
						</li>
                    </a>
					</c:forEach>
				</ul>
			</div>
			<div class="interval"></div>
			<div class="datum-item">
				<div class="datum-row">
					<span>上线切换方案</span>
				</div>
				<ul class="datum-row-img clearfix">
					<c:forEach var="vul" items="${onlineFileList_four}">
                    <a href="<%=basePath%>mobile/implementData/details.do?fileType=4&serialNo=${serialNo}&userId=${userId}&id=${vul.id}">
                    <li>
							<h3>方案</h3>
							<div class="datum-middle">
								<div>
									<p>
										<span>${vul.dataName}</span>
									</p>
								</div>
							</div>
						</li>
                    </a>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();

			function fileDatail(id) {
				alert(id);
            }

		</script>
	</body>

</html>