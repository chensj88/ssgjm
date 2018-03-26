<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>站点问题汇报填写</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_lfzo7jv66zjjor.css"/>
		<script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="mui-content datum gray site-question-write">
		    <!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>${siteQuestionInfo.siteName}</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-report padding-btm-20 padding-top-15">
				<div class="datum-report-item">
					<span>处理人</span>
					<div class="select">
						<input type="hidden"/>
						<a href="<%=basePath%>mobile/siteInstallSet/setPerson.do?id=${siteQuestionInfo.id}&serialNo=${hospcode}&userId=${work_num}&siteName=${siteQuestionInfo.siteName}">
							<span>${siteQuestionInfo.map.get("allocate_name")==null?"待分配":siteQuestionInfo.map.get("allocate_name")}</span>
							<i class="iconfont icon-fanhui"></i></a>

					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>汇报人</span>
					<div class="check-distribuion-item">
						<span>${siteQuestionInfo.map.get("create_name")}</span>
						<div>
							汇报时间
							<time><script>
                                document.write(Common.getDateSubStr("${siteQuestionInfo.createTime}"));
							</script></time>
						</div>
					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>系统名称</span>
					<div class="select">
						<input type="hidden"/>
						<a href="#"><span>${siteQuestionInfo.siteName}</span></a>
					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>菜单名称</span>
					<div class="select">
						<input type="hidden"/>
						<a href="#"><span>${siteQuestionInfo.menuName}</span></a>
					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>问题分类</span>
					<div class="select">
						<input type="hidden"/>
						<a href="#"><span>${siteQuestionInfo.map.get("dict_label")}</span></a>

					</div>
				</div>
			</div>
			<div class="datum-report">
				<div class="datum-report-item">
					<span class="align-self">问题描述</span>
					<textarea class="margin-left">${siteQuestionInfo.questionDesc}</textarea>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span class="align-self">拍照上传</span>
					<div class="datum-upload site-width">
						<c:if test="${siteQuestionInfo.imgPath !=null && siteQuestionInfo.imgPath !=''}">
							<c:forEach var="img" items="${siteQuestionInfo.imgs}">
								<div id="close_id">
									<img src="<%=basePathNuName%>shareFolder${img}" />
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>

		</div>
		<div class="large-img">
			<img src="../images/video.png"/>
			<span class="iconfont icon-close"></span>
		</div>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				IMS.dropDown();
				enterprise.init();
			})
		</script>
	</body>
</html>
