<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>问题汇总</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_yjo1hb0ldpnidx6r.css"/>
	</head>
	<body>
		<div class="mui-content floor">
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft"></span>
				<div>问题汇报</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="floor-report">


				<c:forEach var="vwr" items="${infoList}">
				<div class="floor-item">
					<div class="title">
						<h1>${vwr.floorName}</h1>
						<div class="select-down">
							<span class="solve">${vwr.isOperation=='1'?'已解决':'未解决'}</span>
							<input type="hidden" name="id" value="${vwr.id}">
							<i class="mui-icon mui-icon-arrowdown"></i>
						</div>
					</div>
					<div class="type">
						<span>${vwr.questionType}</span>
					</div>
					<div class="floor-line"></div>
					<div class="text">
						${vwr.questionDesc}
					</div>
					<div class="btn">
						<span>更新记录</span>
					</div>
					<div class="timeline">
						<dl>
							<dt>2018.01.03</dt>
							<dd><strong>更新人：</strong>${vwr.operator}</dd>
							<dd><strong>状态变更：</strong>${vwr.isOperation=='1'?'已解决':'未解决'}</dd>
						</dl>

						<div class="open">
							<span>收起</span>
						</div>
					</div>
					
				</div>
				<div class="null"></div>
				</c:forEach>




			</div>
			<!--mask-->
			<div class="mask">
				<div class="mask-content">
					<div class="mui-row">
					    <div class="mui-col-xs-3 label">处理情况</div>
					    <div class="mui-col-xs-9 radio">
					    	<div class="mui-input-row mui-radio mui-left mui-inline">
							    <label>未解决</label>
							    <input name="radio" type="radio" value="0" checked >
							</div>
							<div class="mui-input-row mui-radio mui-left mui-inline">
							    <label>已安装</label>
							    <input name="radio" type="radio" value="1" >
							</div>
					    </div>
					</div>
					<div class="mui-row">
					    <div class="mui-col-xs-3">变更原因</div>
					    <div class="mui-col-xs-9">
					    	<textarea id ="reason" name="reason"></textarea>
					    </div>
					</div>
					<div class="mask-btn">
						<button type="button" class="mui-btn">取消</button>
						<button id="btn_save" type="button" class="mui-btn mui-btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				mui.init();
				$('.btn span').click(function(){
					$(this).parent().next('.timeline').slideDown();
				});
				$('.open span').click(function(){
					$(this).parents('.timeline').slideUp();
				});
				$('.select-down i').click(function(){
					$('.mask').fadeIn();
				})
				$('.mask-btn').click(function(){
					$('.mask').fadeOut();
				})
			});

			$("#btn_save").click(function(){
			    //获取值
				var radio = $("input[name='radio']:checked").val();
                var reason = $("#reason").val();
                $.ajax({
                    type: "POST",
                    url:Common.getRootPath()+"/login/check.do",
                    data:{"radio":radio,"reason":reason},
                    dataType:"json",
                    cache : false,
                    error: function(request) {
                        alert("服务端错误，或网络不稳定，本次操作被终止。");
                        console.log(request);
                    },
                    success: function(data) {
                        if(data.status) {
                            $("#loginSub").submit();
                        } else {
                            alert(data.message);
                        }
                    }
                });

            });



		</script>
	</body>
</html>
