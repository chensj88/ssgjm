<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<title>楼层问题汇总</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
		<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
		<link rel="stylesheet" href="//at.alicdn.com/t/font_575705_0j8jn55q5hc6usor.css" />

		<script type="text/javascript">
            $(document).ready(function(){
                $("button").click(function(){
                    $("p").hide();

                });



            });
		</script>
	</head>
	<body>
		<div class="mui-content floor">
			<!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>楼层问题汇报</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="floor-table">
				<table>
					<tr class="floor-head">
						<th>楼层问题</th>
						<th>全部</th>
						<th>未解决</th>
						<th>已解决</th>
					</tr>
					<c:forEach var="vwr" items="${infoList}" >

						<tr>
							<td><a style="text-decoration:underline" href="<%=basePath%>/mobile/floorQuestion/floorQuestionReport.do?serialNo=${serialNo}&userId=${userId}&floorName=${vwr.map.get("code")}">${vwr.map.get("f_name")}</a></td>
							<td><span class="td1">${vwr.map.get("num")}</span></td>
							<td><span class="td2">${vwr.map.get("num_no")}</span></td>
							<td><span class="td3">${vwr.map.get("num_use")}</span></td>
						</tr>

					</c:forEach>

				</table>
				<!--<div class="floor-line"></div> -->

				<table>
					<tr class="floor-count">
						<th><i class="iconfont icon-total"></i><strong>合计</strong></th>
						<th><span id="t1"></span></th>
						<th><span id="t2"></span></th>
						<th><span id="t3"></span></th>
					</tr>
				</table>
			</div>
		</div>
	</body>

	<script type="text/javascript">
        $(function () {

            //将页面的值合计
            //var td1s =$(".td1").val();
            //alert(td1s);
	    	var total_1 =0;
            var total_2 =0;
            var total_3 =0;
            $(".td1").each(function(){
                var num = parseInt($(this).text());
                total_1 += num;
            });
            $(".td2").each(function(){
                var num = parseInt($(this).text());
                total_2 += num;
            });
            $(".td3").each(function(){
                var num = parseInt($(this).text());
                total_3 += num;
            });
            $("#t1").html(total_1);
            $("#t2").html(total_2);
            $("#t3").html(total_3);
		});
	</script>
</html>
