<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>楼层问题汇报填写</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css"/>
	</head>
	<body>
		<div class="mui-content datum gray site-question-write">
			<input id="userId" type="hidden" name="userId" value="${userId}">
			<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
			<input id="id" type="hidden" name="id" value="${floorQuestionInfo.id}">
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>楼层问题汇报</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-report padding-btm-20 padding-top-15">
				<div class="datum-report-item">
					<span>楼层名称</span>
					<div class="select">
						<c:if test="${floorQuestionInfo.questionType != null}" >
							<input id="floorName" name="floorName" value="${floorQuestionInfo.floorName}" type="hidden"/>
							<a href="#"><span>${floorQuestionInfo.map.get("f_name")}</span><i class="arrow"></i></a>
						</c:if>
						<c:if test="${floorQuestionInfo.floorName == null}" >
							<input id="floorName" name="floorName" type="hidden"/>
							<a href="#"><span>--请选择--</span><i class="arrow"></i></a>
						</c:if>
						<ul>
							<c:forEach var="vwr" items="${floorsList}">
								<li data-val="${vwr.floorCode}">${vwr.floorName}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>问题分类</span>
					<div class="select">
						<c:if test="${floorQuestionInfo.questionType != null}" >
							<input id="questionType" name="questionType" value="${floorQuestionInfo.questionType}" type="hidden"/>
							<a href="#"><span>${floorQuestionInfo.map.get("dict_label")}</span><i class="arrow"></i></a>
						</c:if>
						<c:if test="${floorQuestionInfo.questionType == null}" >
							<input id="questionType" name="questionType" type="hidden"/>
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

			<div class="datum-report">
				<div class="datum-report-item">
					<span class="align-self">问题描述</span>
					<textarea id="questionDesc" name="questionDesc" class="margin-left">${floorQuestionInfo.questionDesc}</textarea>
				</div>
			</div>
			<form id="file" action="" method="post" enctype="multipart/form-data">
				<div class="datum-report padding-btm-20">
					<div class="datum-report-item">
						<span class="align-self">拍照上传</span>
						<div class="datum-upload site-width">
							<div id="img_upload">
								<i class="iconfont icon-plus"></i>
								<input type="file" id="uploadFile" name="uploadFile" onchange="fileSelected2();" />
							</div>
							<c:if test="${floorQuestionInfo.imgPath !=null && floorQuestionInfo.imgPath !=''}">
								<c:forEach var="img" items="${floorQuestionInfo.imgs}">
									<div id="close_id">
										<img src="<%=basePathNuName%>shareFolder${img}" />
										<span class="iconfont icon-close" onclick="closeImg('${floorQuestionInfo.id}','${img}');"></span>
										<input type="hidden" />
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</form>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item site-radio">
					<span>处理情况</span>
					<div>
						<div class="mui-input-row mui-radio mui-left">
							<label>已解决</label>
							<input name="radio" type="radio" value="1" >
						</div>
						<div class="mui-input-row mui-radio mui-left">
							<label>未解决</label>
							<input name="radio" type="radio" value="0">
						</div>
					</div>
				</div>
			</div>

			<div class="site-button">
				<input type="button" name="" onclick="save();" value="保存" />
				<input type="button" name="" onclick="history.go(-1)" value="取消" />
			</div>
		</div>
		<div class="large-img">
			<img src="../images/video.png"/>
			<span class="iconfont icon-close"></span>
		</div>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				IMS.dropDown();
				enterprise.init();
                $("input:radio[name='radio'][value='${floorQuestionInfo.isOperation}']").attr("checked",'checked');
            });


			//保存
            function save(){
                //判断是否为空
                var floorName =$("#floorName").val();
                var questionType =$("#questionType").val();
                var questionDesc =$("#questionDesc").val();
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var old_id = $("#id").val();
                var isOperation = $("input[name='radio']:checked").val();

                if(floorName == null || floorName ==''){
                    mui.toast('楼层不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }

                if(questionType == null || questionType ==''){
                    mui.toast('问题分类不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }

                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>/mobile/floorQuestionUpload/saveData.do",
                    data:{floorName:floorName,questionType:questionType,questionDesc:questionDesc,isOperation:isOperation,old_id:old_id,userId:userId,serialNo:serialNo},
                    dataType:"json",
                    cache : false,
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                        console.log(request);
                    },
                    success: function(data) {
                        if(data.result=='1') {
                            mui.toast('保存成功',{ duration:'long(3500ms)', type:'div' });

                        } else {
                            mui.toast('保存失败',{ duration:'long(3500ms)', type:'div' })
                        }
                    }
                })

            };

			//图片管理
            function fileSelected2(){
                //获取文件的内容

                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var old_id = $("#id").val();
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

                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>mobile/floorQuestionUpload/saveAndUpdate.do?userId="+userId+"&serialNo="+serialNo+"&old_id="+old_id,
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
                            var obj = JSON.parse(data);
                            if(obj.status == "1") {
                                mui.toast('上传成功',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                                var imgs = "<div><img src='<%=basePathNuName%>shareFolder${obj.path}'></img><span class=\"iconfont icon-close\" onclick=\"closeImg('${obj.id}');\"></span>\n</div>";
                                $(".datum-upload.site-width").append(imgs);
                                //$("#img_upload").append("<div id='close_id'><img src='<%=basePathNuName%>shareFolder${siteQuestionInfo.imgPath}' /></div>");
                                $("#id").val(obj.id);
                            } else {
                                mui.toast('上传失败',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
                            }
                        }
                    });


                }else{
                    alert("选择的文件无效！请重新选择");
                }

            }

            //删除图片
            function closeImg(e,imgPath){
                if(e==null || e ==''){
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>mobile/floorQuestionUpload/deleteImg.do",
                    data:{id:e,imgPath:imgPath},
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
                            //setTimeout("location.reload()",3500);
                        } else {
                            mui.toast('删除失败',{ duration:'long(3500ms)', type:'div' });
                            //追加图片预览
                            //setTimeout("location.reload()",3500);

                        }
                    }
                });

            }



		</script>
	</body>
</html>
