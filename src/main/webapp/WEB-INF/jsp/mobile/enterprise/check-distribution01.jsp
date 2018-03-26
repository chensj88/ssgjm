<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>查看_分配</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>resources/mobile/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css" />
        <script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js"></script>
        <script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<!--header-->
		<div class="header">
			<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
			<div>问题管理</div>
			<span class="mui-icon mui-icon-more"></span>
		</div>
		<div class="hole"></div>
		<div class="site-question">
			<!--tab-->
			<div class="tab">
				<div class="tab-nav">
					<div class="active">
						<a href="#">全部(${size})</a>
					</div>
					<div>
						<a href="#">未解决(${size-num})</a>
					</div>
					<div>
						<a href="#">已解决(${num})</a>
					</div>
				</div>
				<div class="tab-cnt">
					<div class="active">
						<c:forEach var="vwr" items="${siteQuestionInfoList}">

							<div class="site-item" onclick="detail(${vwr.id})">
								<div class="top">
									<div class="top-left">
										<div>${vwr.siteName}<span>${vwr.productName}</span></div>
										<span>${vwr.menuName}</span>
									</div>
									<div class="top-right">
										<c:if test="${vwr.isOperation==1}">
											<p class="state"><span class="solve">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p>处理人:<span>${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span></p>
										</c:if>
										<c:if test="${vwr.isOperation!=1}">
											<p class="state"><span class="processed">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p class="dealer">
												<input class="this_id" type="hidden" value="${vwr.id}">
												处理人:<span class="pro-down">${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span>
											</p>
										</c:if>
									</div>
								</div>
								<div class="btm">
									<p>${vwr.questionDesc}</p>
									<p>汇报时间<script>
                                        document.write(Common.getDateSubStr("${vwr.createTime}"));
									</script> <span>汇报人${vwr.map.get("create_name")}</span></p>
								</div>
							</div>

						</c:forEach>

					</div>
					<!-- 未解决 -->
					<div>
						<c:forEach var="vwr" items="${siteQuestionInfoUn}">

							<div class="site-item" onclick="detail(${vwr.id})">
								<div class="top">
									<div class="top-left">
										<div>${vwr.siteName}<span>${vwr.productName}</span></div>
										<span>${vwr.menuName}</span>
									</div>
									<div class="top-right">
										<c:if test="${vwr.isOperation==1}">
											<p class="state"><span class="solve">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p>处理人:<span>${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span></p>
										</c:if>
										<c:if test="${vwr.isOperation!=1}">
											<p class="state"><span class="processed">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p class="dealer">
												<input class="this_id" type="hidden" value="${vwr.id}">
												处理人:<span class="pro-down">${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span>
											</p>
										</c:if>
									</div>
								</div>
								<div class="btm">
									<p>${vwr.questionDesc}</p>
									<p>汇报时间<script>
                                        document.write(Common.getDateSubStr("${vwr.createTime}"));
									</script> <span>汇报人${vwr.map.get("create_name")}</span></p>
								</div>
							</div>

						</c:forEach>
					</div>
					<!-- 已解决 -->
					<div>
						<c:forEach var="vwr" items="${siteQuestionInfoFinish}">

							<div class="site-item" onclick="detail(${vwr.id})">
								<div class="top">
									<div class="top-left">
										<div>${vwr.siteName}<span>${vwr.productName}</span></div>
										<span>${vwr.menuName}</span>
									</div>
									<div class="top-right">
										<c:if test="${vwr.isOperation==1}">
											<p class="state"><span class="solve">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p>处理人:<span>${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span></p>
										</c:if>
										<c:if test="${vwr.isOperation!=1}">
											<p class="state"><span class="processed">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
											<p class="dealer">
												<input class="this_id" type="hidden" value="${vwr.id}">
												处理人:<span class="pro-down">${vwr.map.get("allocate_name")==null?"待分配":vwr.map.get("allocate_name")}</span>
											</p>
										</c:if>
									</div>
								</div>
								<div class="btm">
									<p>${vwr.questionDesc}</p>
									<p>汇报时间<script>
                                        document.write(Common.getDateSubStr("${vwr.createTime}"));
									</script> <span>汇报人${vwr.map.get("create_name")}</span></p>
								</div>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="select-user">
			<div>
				<div class="select-user-close">
					<span>转给</span>
					<i class="iconfont icon-close"></i>
				</div>
				<div class="select-user-cnt">
					<p><span>本人</span>实施工程师</p>
					<p class="active"><span>李峰</span>研发工程师</p>
					<p><span>李芬</span>实施工程师</p>
					<p><span>刘辉</span>研发工程师</p>
					<p><span>本人</span>实施工程师</p>
					<p><span>本人</span>实施工程师</p>
					<p><span>本人</span>实施工程师</p>
				</div>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				IMS.init();
				enterprise.selectUser();
			})

            function detail(id){
                location.href="<%=basePath%>mobile/siteInstallSet/addAndUpdate.do?id="+id+"&serialNo=${hospcode}&userId=${work_num}";
            }


		</script>
	</body>

</html>