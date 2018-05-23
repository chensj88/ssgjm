<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<%String ref = request.getHeader("REFERER");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>资料信息</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css"/>
	</head>
	<body>
	<div class="mui-content datum gray">
			<!--header-->
			<input type="hidden" name="" value="">
				<input id="fileType" type="hidden" name="fileType" value="${fileType}">
				<input id="userId" type="hidden" name="userId" value="${userId}">
				<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">

			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="javascript:history.go(-1);"></span>
					<div>实施资料上传</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-report">
				<div class="datum-report-item">
					<span>资料名称</span>
					<input id="dataName" name="dataName" value="${dataName}" type="text"  autocomplete="off" />
				</div>
			</div>

		    <c:if test="${fileType =='2'}">
				<div class="datum-report padding-btm-20">
					<div class="datum-report-item">
					<span>资料类别</span>


						<div class="select">
							<c:if test="${onlineFiles.reportType != null}" >
								<input id="dataType" name="dataType" value="${onlineFiles.reportType}" type="hidden" onchange=""/>
								<a href="#"><span>${onlineFiles.map.get("type")}</span><i class="arrow"></i></a>
							</c:if>
							<c:if test="${onlineFiles.reportType == null}" >
								<input id="dataType" name="dataType" type="hidden" onchange=""/>
								<a href="#"><span>--请选择--</span><i class="arrow"></i></a>
							</c:if>
							<ul>
							<c:forEach var="dict" items="${dictInfos}">
							<li data-val="${dict.dictValue}">${dict.dictLabel}</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
        <c:if test="${fileType == '1' || fileType == '2'}">
			<form id="file" action="" method="post" enctype="multipart/form-data">
			<div  class="datum-upload">
				<div>
					<i class="iconfont icon-plus"></i>
					<input type="file" id="uploadFile" name="uploadFile" onchange="fileSelected2(${onlineFiles.id});" />
				</div>

				<c:if test="${onlineFiles.imgPath !=null && onlineFiles.imgPath !=''}">
				<c:forEach var="img" items="${onlineFiles.imgs}">
				<div id="close_id">
					<img src="<%=basePathNuName%>shareFolder${img}" />
					<span class="iconfont icon-close" onclick="closeImg('${onlineFiles.id}','${img}');"></span>
					<input type="hidden" />
				</div>
				</c:forEach>
				</c:if>
			</div>
			</form>
        </c:if>
        <c:if test="${fileType == '3' || fileType == '4'}">
            <form id="file" action="" method="post" enctype="multipart/form-data">
                <div  class="datum-upload">
                    <div>
                        <i class="iconfont icon-plus"></i>
                        <input type="file" id="uploadFile_two" name="uploadFile" onchange="fileSelected();" />
                    </div>

                        <c:forEach var="img" items="${onlineFiles}">
                            <div id="close_id">
                                <img src="<%=basePathNuName%>shareFolder${img.imgPath}" />
                                <span class="iconfont icon-close" onclick="closeImg('${img.id}','${img.imgPath}');"></span>
                                <input type="hidden" />
                            </div>
                        </c:forEach>
                </div>
            </form>

        </c:if>



		</div>
	</div>

		<div class="large-img">
			<img src="../images/video.png"/>
			<span class="iconfont icon-close"></span>
		</div>

		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
			<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>

			<script type="text/javascript">
			$(function(){
				IMS.dropDown();
                enterprise.init();
			})
			//添加图片
            function fileSelected2(id){
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
                var f= $("#uploadFile").val();
                if(f=="") { alert("请上传图片");return false;}
                else {
                    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                        mui.toast('图片类型必须是.gif,jpeg,jpg,png中的一种',{ duration:'long(3500ms)', type:'div' });
                        return false;
                    }
                }

                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>/mobile/implementData/uploadImgAjax.do?id="+id+"&fileType="+fileType+"&userId="+userId+"&serialNo="+serialNo+"&dataName="+dataName+"&dataType="+dataType,
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
                            console.log(data);
                            if(data.status) {
                                //mui.toast('修改成功',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);
                            } else {
                                //mui.toast('修改失败',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);

                            }
                        }
                    });


                }else{
                    alert("选择的文件无效！请重新选择");
                }

            }

            //可行性报告  切换方案

            function fileSelected(){

                var fileType = $("#fileType").val();
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var uploadFile = new FormData($("#file")[0]);
                //判断上传的只能是图片
                var f= $("#uploadFile_two").val();
                if(f=="") { alert("请上传图片");return false;}
                else {
                    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                        mui.toast('图片类型必须是.gif,jpeg,jpg,png中的一种',{ duration:'long(3500ms)', type:'div' });
                        return false;
                    }
                }

                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>/mobile/implementData/uploadImgAjax.do?fileType="+fileType+"&userId="+userId+"&serialNo="+serialNo,
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
                            console.log(data);
                            if(data.status) {
                                //mui.toast('修改成功',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);
                            } else {
                                //mui.toast('修改失败',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                setTimeout("location.reload()",3500);

                            }
                        }
                    });


                }else{
                    alert("选择的文件无效！请重新选择");
                }

            }



            function closeImg(e,imgPath){
	    	    if(e==null || e ==''){
	    	       return false;
				}
                var fileType =$("#fileType").val();
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>/mobile/implementData/deleteImg.do",
                    data:{id:e,imgPath:imgPath,fileType:fileType},
                    cache : false,
                    dataType:"json",
                    async: false,
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                    },
                    success: function(data) {
                        if(data.status) {
                            mui.toast('删除成功',{ duration:'long(3500ms)', type:'div' });
                            //追加图片预览
                            setTimeout("location.reload()",3500);
                        } else {
                            mui.toast('删除失败',{ duration:'long(3500ms)', type:'div' });
                            //追加图片预览
                            setTimeout("location.reload()",3500);

                        }
                    }
                });

			}


		</script>
	</body>
</html>
