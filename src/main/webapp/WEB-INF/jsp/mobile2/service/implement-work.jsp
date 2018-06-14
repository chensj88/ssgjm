<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>实施工具</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_8s9fwys71yxmvx6r.css"/>
	</head>
	<body>

		<div class="wrap">
			<div class="wrap-header">
				<div class="header">
                    <span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
                    <input id="userId" type="hidden" name="userId" value="${userId}">
                    <input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
                    <input id="openId" type="hidden" name="openId" value="${openId}">
					<div>实施工具</div>
					<a href="<%=basePath%>/mobile/wechatSiteQuestion/list.do?userId=${userId}&serialNo=${serialNo}">采集列表</a>
				</div>
			</div>
			<div class="wrap-cnt">

				<div>
					<!--search-->
					<div class="imple-work-search">
						<i class="iconfont icon-search" onclick="search();"></i>
						<input id="search_text" type="text" placeholder="请输入搜索内容"/>
					</div>
					<!--tab-->
					<div class="wap-tab">
						<span <c:if test="${active != '1'}"> class="active"</c:if> onclick="processStatus(4);">未确认（${process_num.map.get("numNo")==null?0:process_num.map.get("numNo")}）</span>
						<span <c:if test="${active == '1'}"> class="active"</c:if> onclick="processStatus(5);">已确认（${process_num.map.get("numOver")==null?0:process_num.map.get("numOver")}）</span>
					</div>
					<div class="space"></div>
					<div class="wap-tab-cnt">
						<div id="item0">

								<c:forEach var="vwr" items="${questionList}">
								<div>
									<a href="#" class="row">
										<i class="iconfont icon-time"></i>${vwr.groupName}<b>（${vwr.num}条）</b>
									</a>
									<c:forEach var="vwr1" items="${vwr.listQuery}">
										<a href="<%=basePath%>/mobile/tempSiteQuestion/addPage.do?questionId=${vwr1.id}&userId=${vwr1.creator}&serialNo=${vwr1.serialNo}" class="row active">

												${vwr1.map.deptName}-${vwr1.menuName}
											<span class="${vwr1.map.priorityString}">${vwr1.map.priorityString}</span>
										</a>
									</c:forEach>
									<div class="space"></div>
								</div>
								</c:forEach>

						</div>


					</div>
				</div>


				<div class="hide" >

				</div>
				<div class="hide">
					正在建设中
				</div>
				<div class="hide">
					正在建设中
				</div>
			</div>
			<!--新增-->
			<a href="<%=basePath%>/mobile/wechatSiteQuestion/addPage.do?userId=${userId}&serialNo=${serialNo}&source=1" class="wrap-add" >
				<i class="iconfont icon-add"></i>
			</a>
			<!--底部菜单-->
			<div class="wrap-foot">
				<div class="active">
					<a href="#" onclick="processStatus(0);">  </a>
					<i class="iconfont icon-ck"></i>
					查看
				</div>
				<div onclick="videoLoad();">
					<i class="iconfont icon-sp"></i>
					视频
				</div>
				<div>
					<i class="iconfont icon-fx"></i>
					分析
				</div>
				<div>
					<i class="iconfont icon-wo"></i>
					我
				</div>
			</div>
		</div>
		<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function () {
                IMS.menuTab();
            })

			function processStatus(val){
			        location.href="<%=basePath%>/mobile/tempSiteQuestion/laodList.do?processStatus="+val+"&openId="+$("#openId").val()
                        +"&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();
			}

            function videoLoad() {
                location.href="<%=basePath%>/mobile/trainVideoList/list.do?openId="+$("#openId").val()+"&userId="+$("#userId").val()+
                    "&serialNo="+$("#serialNo").val();
            }

            function  search() {
				location.href="<%=basePath%>/mobile/tempSiteQuestion/laodList.do?search_text="+$("#search_text").val()+"&openId="+$("#openId").val()
                    +"&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();;
            }

		</script>
	</body>
</html>
