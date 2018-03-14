<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>住院流程调研报告</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_l39bx41qun2ke29.css"/>
	</head>
	<body>
			<form id="myForm" action="<%=basePath%>/mobile/implementData/uploadImg.do" method="post" enctype="multipart/form-data">
			<!--header-->
			<input type="hidden" name="" value="">
				<input type="hidden" name="fileType" value="${fileType}">
				<input type="hidden" name="userId" value="${userId}">
				<input type="hidden" name="serialNo" value="${serialNo}">

				<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>实施资料上传</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-report">
				<div class="datum-report-item">
					<span>资料名称</span>
					<input id="dataName" name="dataName" type="text"  autocomplete="off" />
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>资料类别</span>
					<div class="select">
						<input id="dataType" name="dataType" type="hidden"/>
						<a href="#"><span>--请选择--</span><i class="arrow"></i></a>
		                <ul>
		                	<!--<li data-val="-1">--请选择--</li>-->
							<c:forEach var="dict" items="${dictInfos}">
								<li data-val="${dict.dictValue}">${dict.dictLabel}</li>
							</c:forEach>
		                </ul>
					</div>
				</div>
			</div>
			<div class="datum-upload">
				<div>
					<i class="iconfont icon-plus"></i>
					<input type="file" name="uploadFile" value="" onchange="fileSelected();" id="" value="" />
				</div>
				<c:forEach var="vul" items="${onlineFiles}">
					<div><img src="<%=basePathNuName%>shareFolder${vul.imgPath}"></div>
				</c:forEach>


			</div>
			</form>
		</div>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				IMS.dropDown();
			})


            function fileSelected(worknum,val_type) {
                var dataName = $("#dataName").val();
                var dataType = $("#dataType").val();
                if(dataName == null || dataName ==''){
                    mui.toast('名称不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
				}
                if(dataType == null || dataType ==''){
                    mui.toast('请选择类型',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }
                document.getElementById("myForm").submit();

            }
		</script>
	</body>
</html>
