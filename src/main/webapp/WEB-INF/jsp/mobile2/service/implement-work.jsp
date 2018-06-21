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
		<link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
	</head>
	<body>

		<div class="wrap">
			<%--<div class="wrap-header">--%>
				<%--<div class="header">--%>
                    <%--<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>--%>
                    <input id="userId" type="hidden" name="userId" value="${userId}">
                    <input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
                    <input id="openId" type="hidden" name="openId" value="${openId}">
					<%--<div>实施工具</div>--%>
					<%--<a href="<%=basePath%>/mobile/wechatSiteQuestion/list.do?userId=${userId}&serialNo=${serialNo}">采集列表</a>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="wrap-cnt">

				<div>
					<!--search-->
					<form  action="javascript:search(${active});">
					<div class="imple-work-search">
						<i class="iconfont icon-search" onclick="search(${active})"></i>
						<input style="background-color:rgba(0,0,0,0);text-align:left;" id="search_text" type="search" value="${search_text}" placeholder="请输入搜索内容"/>
					</div>
					</form>
					<!--tab-->
					<div class="wap-tab">
						<span <c:if test="${active == '4'}"> class="active"</c:if> onclick="processStatus(4);">未确认（${process_num.map.get("numNo")==null?0:process_num.map.get("numNo")}）</span>
						<span <c:if test="${active == '5'}"> class="active"</c:if> onclick="processStatus(5);">已确认（${process_num.map.get("numOver")==null?0:process_num.map.get("numOver")}）</span>
						<span <c:if test="${active < '4' || active > '5'}"> class="active"</c:if> onclick="processStatus(1);">采集列表（${process_num.map.get("numList")==null?0:process_num.map.get("numList")}）</span>

					</div>
					<div class="space"></div>
					<div class="wap-tab-cnt">
						<div id="item0">
								<%----%>
								<%--<c:forEach var="vwr" items="${questionList}">--%>
								<%--<div>--%>
									<%--<a href="#" class="row">--%>
										<%--<i class="iconfont icon-time"></i>${vwr.groupName}<b>（${vwr.num}条）</b>--%>
									<%--</a>--%>
									<%--<c:forEach var="vwr1" items="${vwr.listQuery}">--%>
										<%--<a href="<%=basePath%>/mobile/tempSiteQuestion/addPage.do?questionId=${vwr1.id}&userId=${vwr1.creator}&serialNo=${vwr1.serialNo}" class="row">--%>

												<%--${vwr1.map.deptName}-${vwr1.menuName}--%>
											<%--<span class="${vwr1.map.priorityString}">${vwr1.map.priorityString}</span>--%>
										<%--</a>--%>
									<%--</c:forEach>--%>
									<%--<div class="space"></div>--%>
								<%--</div>--%>
								<%--</c:forEach>--%>


									<c:forEach var="vwr" items="${questionList}">
										<a href="#" class="row">
											<i class="iconfont icon-time"></i>${vwr.groupName}<b>（${vwr.num}条）</b>
										</a>
										<ul class="mui-table-view OA_task_1">
											<c:forEach var="vwr1" items="${vwr.listQuery}">
												<li class="mui-table-view-cell">
													<div class="mui-slider-right mui-disabled">
														<a vid="${vwr1.id}" vstatus="${vwr1.processStatus}" vrequirementNo="${vwr1.requirementNo}" class="mui-btn mui-btn-red">删除</a>
													</div>
													<div class="mui-slider-handle collect-item" onclick="detail(${vwr1.id},${vwr1.processStatus})">
														<h3>${vwr1.map.deptName}-${vwr1.menuName}</h3>
														<p>
															<span class="status${vwr1.map.priorityString}">${vwr1.map.priorityString}</span>
															<c:choose>
																<c:when test="${vwr1.processStatus==1}">
																	<span class="distribution">${vwr1.map.processStr}</span>
																</c:when>
																<c:when test="${vwr1.processStatus == 2 || vwr1.processStatus == 3 || vwr1.processStatus == 4}">
																	<span class="distributioned">${vwr1.map.processStr}</span>
																</c:when>
																<c:when test="${vwr1.processStatus == 5}">
																	<span class="distributioned" style="color: #000;border-color: #000;">${vwr1.map.processStr}</span>
																</c:when>
																<c:otherwise>
																	<span class="no-distribution" style="color: red;border-color: red;">${vwr1.map.processStr}</span>
																</c:otherwise>
															</c:choose>
														</p>
													</div>
												</li>
											</c:forEach>
										</ul>
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
			<a href="<%=basePath%>/mobile/wechatSiteQuestion/addPage.do?userId=${userId}&serialNo=${serialNo}&source=2" class="wrap-add" >
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
		<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
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

            function  search(active) {
				location.href="<%=basePath%>/mobile/tempSiteQuestion/laodList.do?search_text="+$("#search_text").val()+"&processStatus="+active
                    +"&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();
            }

		</script>
		<script type="text/javascript">
            mui.init();
            (function($) {
                //第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
                $('.OA_task_1').on('tap', '.mui-btn', function(event) {
                    var elem = this;
                    var li = elem.parentNode.parentNode;
                    var questionId = this.getAttribute('vid');
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>mobile/wechatSiteQuestion/checkQuestion.do",
                        data:{id:questionId},
                        cache : false,
                        dataType:"json",
                        async: false,
                        error: function(request) {
                            mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                        },
                        success: function(data) {
                            if (data.status == "success") {
                                if(data.data == 1){
                                    mui.toast('当前问题已分配或已导入系统，不允许删除！',{ duration:'long', type:'div' });
                                    setTimeout(function() {
                                        $.swipeoutClose(li);
                                    }, 0);
                                }else{
                                    mui.confirm('', '确认删除该条记录？',  ['确认','取消'], function(e) {
                                        console.log(e);
                                        if (e.index == 0) {
                                            li.parentNode.removeChild(li);
                                            deleteQuestion(questionId);
                                        } else {
                                            setTimeout(function() {
                                                $.swipeoutClose(li);
                                            }, 0);
                                        }
                                    });
                                }
                            }
                        }
                    });
                });
            })(mui);

            /**
             * 跳转问题详情页
             */
            function detail(id,status){
                if( status === 1 ){
                    location.href="<%=basePath%>mobile/wechatSiteQuestion/addPage.do?questionId="+id+"&serialNo=${serialNo}&userId=${userId}&source=2";
                }else{
                    location.href="<%=basePath%>mobile/tempSiteQuestion/addPage.do?questionId="+id+"&serialNo=${serialNo}&userId=${userId}&source=2";
                }
            }

            /**
             * 问题删除
             * @param id
             */
            function deleteQuestion(id) {
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>mobile/wechatSiteQuestion/delete.do",
                    data:{id:id},
                    cache : false,
                    dataType:"json",
                    async: false,
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                    },
                    success: function(data) {
                        mui.toast('问题删除成功！',{ duration:'long', type:'div' })
                    }
                });
            }

		</script>
	</body>
</html>
