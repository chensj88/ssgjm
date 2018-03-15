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
			<!--header-->
			<input type="hidden" name="" value="">
				<input id="fileType" type="hidden" name="fileType" value="${fileType}">
				<input id="userId" type="hidden" name="userId" value="${userId}">
				<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">

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
						<input id="dataType" name="dataType" type="hidden" onchange=""/>
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
			<form id="file" action="" method="post" enctype="multipart/form-data">
			<div class="datum-upload">
				<div>
					<i class="iconfont icon-plus"></i>
					<input type="file" id="uploadFile" name="uploadFile" onchange="fileSelected2();" />
				</div>

				<c:forEach var="vul" items="${onlineFiles}">
					<div><img src="<%=basePathNuName%>shareFolder${vul.imgPath}"></div>
				</c:forEach>

			</div>
			</form>

		</div>
		<script src="<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
			<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>

			<script type="text/javascript">
			$(function(){
				IMS.dropDown();
			})

            function fileSelected2(){
			    //获取文件的内容
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
                var fileType = $("#fileType").val();
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var uploadFile = new FormData($("#file")[0]);
				//判断上传的只能是图片
                var f=document.getElementById("uploadFile").value;
                if(f=="") { alert("请上传图片");return false;}
                else {
                    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                        mui.toast('图片类型必须是.gif,jpeg,jpg,png中的一种',{ duration:'long(3500ms)', type:'div' });
                        return false;
                    }
                }

                console.log(uploadFile);
                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>/mobile/implementData/uploadImgAjax.do?dataName="+dataName+"&dataType="
						+dataType+"&fileType="+fileType+"&userId="+userId+"&serialNo="+serialNo,
						data:uploadFile,
                        cache : false,
                        async: false,
                        contentType: false, //不设置内容类型
                        processData: false, //不处理数据
                        error: function(request) {
                            mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                            console.log(request);
                        },
                        success: function(data) {
                            alert(data.status);
                            if(data.status) {
                                mui.toast('修改成功',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);
                            } else {
                                mui.toast('修改失败',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);

                            }
                        }
                    });


                }else{
                    alert("选择的文件无效！请重新选择");
                }




            }


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
                <%--var fileType = $("#fileType").val();--%>
                <%--var userId = $("#userId").val();--%>
                <%--var serialNo = $("#serialNo").val();--%>

                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--url:"<%=basePath%>/mobile/implementData/uploadImg.do",--%>
                    <%--data:{dataName:dataName,dataType:dataType,fileType:fileType,userId:userId,serialNo:serialNo},--%>
                    <%--dataType:"json",--%>
                    <%--cache : false,--%>
                    <%--error: function(request) {--%>
                        <%--mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })--%>
                        <%--console.log(request);--%>
                    <%--},--%>
                    <%--success: function(data) {--%>
                        <%--if(data.status) {--%>
                            <%--mui.toast('修改成功',{ duration:'long(3500ms)', type:'div' });--%>
                            <%--setTimeout("location.reload()",3500);--%>

                        <%--} else {--%>
                            <%--mui.toast('修改失败',{ duration:'long(3500ms)', type:'div' })--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
               //document.getElementById("myForm").submit();


            }
		</script>
	</body>
</html>
