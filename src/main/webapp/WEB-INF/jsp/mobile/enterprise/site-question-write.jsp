<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<%String ref = request.getHeader("REFERER");%>
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
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_o710wavlb78n0zfr.css"/>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="mui-content datum gray site-question-write">
		    <!--header-->
				<input id="userId" type="hidden" name="userId" value="${userId}">
				<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
			    <input id="id" type="hidden" name="id" value="${siteQuestionInfo.id}">

			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="javascript:window.location='<%=ref%>'"></span>
				<div>站点问题汇报</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="datum-report padding-btm-20 padding-top-15">
				<div class="datum-report-item">
					<span>站点名称</span>
					<div id="siteId" class="select">
						<input id="siteName" name="siteName" value="${siteQuestionInfo.siteName}" type="hidden" />
						<c:if test="${siteQuestionInfo.siteName != null}" >
							<a href="#"><span>${siteQuestionInfo.siteName}</span><i class="arrow"></i></a>
						</c:if>
						<c:if test="${siteQuestionInfo.siteName == null}" >
							<a href="#"><span>--请选择--</span><i class="arrow"></i></a>
						</c:if>
						<ul>
							<c:forEach var="vwr" items="${installList}">
								<li data-val="${vwr.deptName}">${vwr.deptName}</li>
							</c:forEach>
		                </ul>
					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>系统名称</span>
					<div id="productId" class="select">
						<input id="productName" name="productName" value="${siteQuestionInfo.productName}" type="hidden"/>
						<c:if test="${siteQuestionInfo.productName != null}" >
							<a href="#"><span>${siteQuestionInfo.productName}</span><i class="arrow"></i></a>
						</c:if>
						<c:if test="${siteQuestionInfo.productName == null}" >
							<a href="#"><span>--请选择--</span><i class="arrow"></i></a>
						</c:if>
		                <ul id="ul2">
		                </ul>
					</div>
				</div>
			</div>
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>问题类型</span>
					<div id="questionTypeId" class="select">
						<input id="questionType" name="questionType" value="${siteQuestionInfo.questionType}" type="hidden"/>
						<c:if test="${siteQuestionInfo.questionType != null}" >
							<a href="#"><span>${siteQuestionInfo.map.get("dict_label")}</span><i class="arrow"></i></a>
						</c:if>
						<c:if test="${siteQuestionInfo.questionType == null}" >
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
			<div class="datum-report padding-btm-20">
				<div class="datum-report-item">
					<span>菜单名称</span>
					<input id="menuName" type="text" name="menuName" value="${siteQuestionInfo.menuName}" />
					<%--<div id="menuId" class="select">--%>
					<%--<input id="menuName" type="text" name="menuName" value="${siteQuestionInfo.menuName}" />--%>
					<%--&lt;%&ndash;<c:if test="${siteQuestionInfo.menuName != null}" >&ndash;%&gt;--%>
					<%--&lt;%&ndash;<a href="#"><span>${siteQuestionInfo.menuName}</span><i class="arrow"></i></a>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<c:if test="${siteQuestionInfo.menuName == null}" >&ndash;%&gt;--%>
					<%--&lt;%&ndash;<a href="#"><span>--请选择--</span><i class="arrow"></i></a>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
					<%--<ul id="ul3">--%>
					<%--&lt;%&ndash;<c:forEach var="vwr" items="${contractProductInfos}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li data-val="${vwr.map.get("meunName")}">${vwr.map.get("meunName")}</li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
					<%--</ul>--%>


					<%--</div>--%>
				</div>
			</div>
			<div class="datum-report">
				<div class="datum-report-item">
					<span class="align-self">问题描述</span>
					<textarea id="questionDesc" name="questionDesc" class="margin-left">${siteQuestionInfo.questionDesc}</textarea>
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
							<c:if test="${siteQuestionInfo.imgPath !=null && siteQuestionInfo.imgPath !=''}">
								<c:forEach var="img" items="${siteQuestionInfo.imgs}">
								<div id="close_id">
									<img src="<%=basePathNuName%>shareFolder${img}" />
									<span class="iconfont icon-close" onclick="closeImg('${siteQuestionInfo.id}','${img}');"></span>
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
							<input name="radio" type="radio" value="0" checked="checked">
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
            var str_html="";
            var str_html3="";
			$(function(){
				//IMS.dropDown();
				enterprise.init();
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                $("input:radio[name='radio'][value='${siteQuestionInfo.isOperation}']").attr("checked",'checked');

                //三级联动方法
                $(".select").on("click","a",function(){
                    $(this).find("i").toggleClass("reverse");
                    $(this).next("ul").slideToggle();
                });
                $("#siteId").on("click","ul>li",function(){
                    var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
                    _dropd.slideToggle();
                    _dropd.siblings("[type='hidden']").val(_val);
                    _dropd.siblings("a").find("span").text(_txt);
                    _dropd.siblings("a").find("i").toggleClass("reverse");

                    //联动方法 切换二级菜单的值
					var siteName='${siteQuestionInfo.siteName}';
					if(siteName != null || siteName != ''){  //不为空时 比较值是否真的改变
						if(_val==siteName){
						    return false;
						}
						$("#productName").val("");
                        $("#productId a").find("span").text("--请选择--") ;
                        $("#menuName").val("");
                        $("#menuId a").find("span").text("--请选择--") ;
                        $("#ul2").empty();


                        //加载菜单数据
                        $.ajax({
                            type: "POST",
                            url:"<%=basePath%>/mobile/siteQuestionInfo/loadData.do",
                            data:{type:1,name:siteName,serialNo:serialNo},
                            dataType:"json",
                            cache : false,
                            error: function(request) {
                                mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                                console.log(request);
                            },
                            success: function(data) {
                                str_html="";
                                $("#ul2").empty();
                                console.log(data);
                                var json=eval(data.xtJsons);
                                $.each(json,function(i,item){
                                    str_html=str_html+"<li data-val='"+json[i].map['zxtmc']+"'>"+json[i].map['zxtmc']+"</li>";

                                });
                                $("#ul2").html(str_html);
                            }
                        });

                    }
                });

                //系统信息
                $("#productId").on("click","ul>li",function(){

                    var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
                    _dropd.slideToggle();
                    _dropd.siblings("[type='hidden']").val(_val);
                    _dropd.siblings("a").find("span").text(_txt);
                    _dropd.siblings("a").find("i").toggleClass("reverse");

                    //联动方法 切换二级菜单的值
                    var productName=_val;
                    if(productName != null || productName != ''){  //不为空时 比较值是否真的改变

                        $("#menuName").val("");
                        $("#menuId a").find("span").text("--请选择--") ;
                        $("#ul3").html("");

                        //加载菜单数据
                        $.ajax({
                            type: "POST",
                            url:"<%=basePath%>/mobile/siteQuestionInfo/loadData.do",
                            data:{type:2,name:productName,serialNo:serialNo},
                            dataType:"json",
                            cache : false,
                            error: function(request) {
                                mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                                console.log(request);
                            },
                            success: function(data) {
                                console.log(data);
                                var json=eval(data.xtJsons);
                                $.each(json,function(i,item){
                                    str_html3=str_html3+"<li data-val='"+json[i].cpmc+"'>"+json[i].cpmc+"</li>";

                                });
                                $("#ul3").html(str_html3);
                            }
                        });
                    }
                });

                // $("#menuId").on("click","ul>li",function(){
                //     var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
                //     _dropd.slideToggle();
                //     _dropd.siblings("[type='hidden']").val(_val);
                //     _dropd.siblings("a").find("span").text(_txt);
                //     _dropd.siblings("a").find("i").toggleClass("reverse");
                //
                // });

				$("#questionTypeId").on("click","ul>li",function(){
                    var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
                    _dropd.slideToggle();
                    _dropd.siblings("[type='hidden']").val(_val);
                    _dropd.siblings("a").find("span").text(_txt);
                    _dropd.siblings("a").find("i").toggleClass("reverse");

				});



            });



			function save(){
			    //判断是否为空
				var siteName =$("#siteName").val();
                var productName =$("#productName").val();
                var menuName =$("#menuName").val();
                var questionDesc =$("#questionDesc").val();
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var old_id = $("#id").val();
                var questionType =$("#questionType").val();
                var isOperation = $("input[name='radio']:checked").val();

                if(siteName == null || siteName ==''){
                    mui.toast('站点不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }
                if(productName == null || productName ==''){
                    mui.toast('系统不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }
                if(menuName == null || menuName ==''){
                    mui.toast('菜单不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }
                if(questionType == null || questionType ==''){
                    mui.toast('问题类型不能为空',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }


                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>/mobile/siteQuestionInfo/saveData.do",
                    data:{siteName:siteName,productName:productName,menuName:menuName,questionType:questionType,questionDesc:questionDesc,isOperation:isOperation,old_id:old_id,userId:userId,serialNo:serialNo},
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
                });

			};

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

                //预览图片
                // var objUrl = getObjectURL($("#file")[0]); //获取图片的路径，该路径不是图片在本地的路径
                // if (objUrl) {
                //     $("#uu").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                // }

                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>mobile/siteQuestionInfo/saveAndUpdate.do?userId="+userId+"&serialNo="+serialNo+"&old_id="+old_id,
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
                                //追加图片预览 <span class="iconfont icon-close" onclick="closeImg('${siteQuestionInfo.id}','${img}');"></span>
                                //									<input type="hidden" />
                                var imgs = "<div id=\"close_id\"><img src='<%=basePathNuName%>shareFolder"+obj.path+"'></img><span class=\"iconfont icon-close\" onclick=\"closeImg('+"+obj.id+"',"+"'"+obj.path+"');\"></span>\n</div>";
                                $(".datum-upload.site-width").append(imgs);
                                //$("#img_upload").append("<div id='close_id'><img src='<%=basePathNuName%>shareFolder${siteQuestionInfo.imgPath}' /></div>");
                                $("#id").val(obj.id);
                                //setTimeout("location.reload()",0);

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
                    url:"<%=basePath%>mobile/siteQuestionInfo/deleteImg.do",
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

            //建立一個可存取到該file的url
            function getObjectURL(file) {
                var url = null ;
                if (window.createObjectURL!=undefined) { // basic
                    url = window.createObjectURL(file) ;
                } else if (window.URL!=undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file) ;
                } else if (window.webkitURL!=undefined) { // webkit or chrome
                    url = window.webkitURL.createObjectURL(file) ;
                }
                return url ;
            }


		</script>
	</body>
</html>
