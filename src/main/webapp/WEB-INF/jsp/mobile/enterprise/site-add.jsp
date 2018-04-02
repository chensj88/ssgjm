<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>站点安装登记-新增</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_575705_sw0qbtlqf0hbmx6r.css"/>
	</head>
	<body>
		<div class="mui-content gray">
		    <!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>站点安装登记</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="site-install">
				<div class="part-one">
					<div class="left">
						<span>${siteInstall.deptName}</span>
					</div>
					<div class="right">
						<p>完成站点数：<span><i>0</i>/<i>${siteInstall.num}</i></span></p>
						<p>安装人：<span>${siteInstall.map.get("yhmc")}</span></p>
					</div>
				</div>
				<div class="part-item">
					<div class="part-two">
						<div>
							<span class="software">软件</span>
						</div>
						<div>${siteInstall.pdName}</div>
					</div>
					<div class="part-two">
						<div>
							<span class="hardware">硬件</span>
						</div>
						<div>${siteInstall.hdName}</div>
					</div>
				</div>
			</div>
			<div id="siteDiv">
				<input type="hidden" name="parentId" value="${siteInstall.id}">
				<input type="hidden" id="userId" name="userId" value="${userId}">
				<input type="hidden" id="serialNo" name="serialNo" value="${serialNo}">
				<input type="hidden" id="install_array" name="install_array" value="">
				<c:forEach var="vwr"  items="${siteInstallDetails}" varStatus="status">
					<div class="site-register">
						<span class="iconfont icon-close del-item"><input type="hidden" name="id" value="${vwr.id}"></span>
						<div class="register-one">
							<div><span>站点</span></div>
							<div>
								<span class="install">已安装</span>
								<i class="iconfont icon-arrow-down"></i>
							</div>
						</div>
						<div class="register-cnt">
							<div class="register-item">
								<div class="register-item-title">站点名称</div>
								<input type="text" name="siteName" value="${vwr.siteName}" placeholder="请输入站点名称"/>
							</div>
							<div class="register-item">
								<div class="register-item-title">IP地址</div>
								<input type="text" name="ip" value="${vwr.ip}" placeholder="请输ip地址"/>
							</div>
							<div class="register-item">
								<div class="register-item-title">楼宇</div>
								<input type="text" name="building" value="${vwr.building}" placeholder="请输入站点名称"/>
							</div>
							<div class="register-item">
								<div class="register-item-title">楼层</div>
								<input type="text" name="floorNum" value="${vwr.floorNum}" placeholder="请输楼层名称"/>
							</div>
							<div class="register-item">
								<div class="register-item-title">PC机型号</div>
								<input type="text" name="pcModel" value="${vwr.pcModel}" placeholder="请输入站点名称"/>
							</div>
							<div class="register-item">
								<div class="register-item-title">安装情况</div>
								<div class="register-radio">
									<c:if test="${vwr.install ==1}">
										<div class="mui-input-row mui-radio mui-left">
											<label>已安装</label>
											<input name="install${status.index}" type="radio" value="1" checked="checked">
										</div>
										<div class="mui-input-row mui-radio mui-left">
											<label>未安装</label>
											<input name="install${status.index}" type="radio" value="0">
										</div>
									</c:if>
									<c:if test="${vwr.install !=1}">
										<div class="mui-input-row mui-radio mui-left">
											<label>已安装</label>
											<input name="install${status.index}" type="radio" value="1" >
										</div>
										<div class="mui-input-row mui-radio mui-left">
											<label>未安装</label>
											<input name="install${status.index}" type="radio" value="0" checked="checked">
										</div>
									</c:if>
								</div>
							</div>

							<form id="file${status.index}" action="" method="post" enctype="multipart/form-data">
								<div class="datum-report-item">
									<span class="align-self">拍照上传</span>
									<div class="datum-upload site-width${status.index}">
										<div id="img_upload">
											<i class="iconfont icon-plus"></i>
											<input type="file" id="uploadFile${status.index}" name="uploadFile" onchange="fileSelected2(${vwr.id},${status.index});" />
										</div>
										<c:if test="${vwr.imgPath !=null && vwr.imgPath !=''}">
											<c:forEach var="img" items="${vwr.imgs}">
												<div>
													<img src="<%=basePathNuName%>shareFolder${img}" />
													<span class="iconfont icon-close" onclick="closeImg('${vwr.id}','${img}');"></span>
													<input type="hidden" />
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</form>

						</div>
					</div>
					<div class="large-img">
						<img src="../images/video.png"/>
						<span class="iconfont icon-close"></span>
					</div>
				</c:forEach>
			</div>
		<form id="installId" action="<%=basePath%>mobile/siteInstall/save.do" method="post" >

		</form>
			<div class="register-button">
				<i class="iconfont icon-increase" style="color: #81B3FF;"></i>
				<i class="iconfont icon-reduce"></i>
			</div>

			<div class="fix-hole"></div>
		    <div class="check-distribuion-btn">
		    	<input type="button" onclick="save();" value="保存" />
		    	<input type="button" value="取消" />
		    </div>
		</div>

        <script type="text/javascript" src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>resources/mobile/js/ims.js" ></script>
		<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/js/common.js"></script>

		<script type="text/javascript">
			$(function(){
				enterprise.siteDelIetm();
                enterprise.init();
				$('#installId').append($('#siteDiv')[0]);
            });

			//图片添加
            function fileSelected2(imgId,i){
                //获取文件的内容
                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var uploadFile = new FormData($("#file"+i)[0]);
                //判断上传的只能是图片
                var f=document.getElementById("uploadFile"+i).value;
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
                        url:"<%=basePath%>mobile/siteInstall/saveAndUpdate.do?userId="+userId+"&serialNo="+serialNo+"&old_id="+imgId,
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
                                var imgs = "<div><img src='<%=basePathNuName%>shareFolder"+obj.path+"'></img><span class=\"iconfont icon-close\" onclick=\"closeImg('"+obj.id+"','"+obj.path+"');\"></span>\n</div>";
                                $(".datum-upload.site-width"+i).append(imgs);
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
                    url:"<%=basePath%>mobile/siteInstall/deleteImg.do",
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
                        } else {
                            mui.toast('删除失败',{ duration:'long(3500ms)', type:'div' });

                        }
                    }
                });

            }

            //保存数据
			function save(){
			    var num = ${siteInstall.num};
                var install_array=new Array();
                for (var i = 0;i < num;i++) {
                    var install ="install"+i+''
                    var ii = $("input[name='"+install+"']:checked").val();
                    if("undefined" != typeof(ii)&& ii != null && ii != ""){
                        install_array.push(ii);
					}
                }
				$("#install_array").val(install_array);
				$("#installId").submit();
			}

			//删除站点信息
			function delItem(itemId){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>mobile/siteInstall/deleteItem.do",
                    data:{id:itemId},
                    cache : false,
                    dataType:"json",
                    async: false,
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                    },
                    success: function(data) {
                        if(data.status) {
                            mui.toast('删除成功',{ duration:'long(3500ms)', type:'div' });
                        } else {
                            mui.toast('删除失败',{ duration:'long(3500ms)', type:'div' });

                        }
                    }
                });


			}


		</script>
	</body>
</html>
