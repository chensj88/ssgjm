<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>楼层问题汇报</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>resources/mobile/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css" />
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/js/common.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<!--header-->
		<div class="header">
			<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
			<div>楼层问题汇报</div>
			<span class="mui-icon mui-icon-more"></span>
		</div>
		<div class="hole"></div>
		<div class="site-question">

			<a href="<%=basePath%>mobile/floorQuestionUpload/addAndUpdate.do?serialNo=${hospcode}&userId=${work_num}">
			<div class="site-add">
				<i class="iconfont icon-plus"></i>楼层问题汇报
			</div>
			</a>

			<c:forEach var="vwr" items="${infoList}">
				<div class="site-item" onclick="detail(${vwr.id})">
					<div class="top">
						<div class="top-left" >
<<<<<<< HEAD
							<div>${vwr.floorName}</div>
=======
							<div>${vwr.map.get("f_name")}</div>
>>>>>>> a340590b36085a7325c63510bc48d0535149fc66
							<span>${vwr.map.get("dict_label")}</span>
						</div>
						<div class="top-right">
							<p class="state"><span class="solve">${vwr.isOperation==1?'已解决':'待处理'}</span></p>
							<p>处理人:<span>${vwr.map.get("operator_name")}</span></p>
						</div>
					</div>
					<div class="btm">
						<p>${vwr.questionDesc}</p>
						<p>汇报时间 <script>
                            document.write(Common.getDateSubStr("${vwr.createTime}"));
						</script><span>汇报人${vwr.map.get("yhmc")}</span></p>
					</div>
				</div>
			</c:forEach>

		</div>
		<div class="select-user">
			<div>
				<div class="select-user-close">
					<span>转给</span>
					<i class="iconfont icon-close"></i>
				</div>
				<div class="select-user-cnt">
					<p data-key='11'><span>本人</span>实施工程师</p>
					<p data-key='22' class="active"><span>李峰</span>研发工程师</p>
					<p data-key='33'><span>李芬</span>实施工程师</p>
					<p data-key='44'><span>刘辉</span>研发工程师</p>
					<p data-key='55'><span>本人</span>实施工程师</p>
					<p data-key='66'><span>本人</span>实施工程师</p>
					<p data-key='77'><span>本人</span>实施工程师</p>
				</div>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				enterprise.selectUser()
			})

            function detail(id){
                location.href="<%=basePath%>mobile/floorQuestionUpload/addAndUpdate.do?id="+id+"&serialNo=${hospcode}&userId=${work_num}";
            }

		</script>
	</body>

</html>