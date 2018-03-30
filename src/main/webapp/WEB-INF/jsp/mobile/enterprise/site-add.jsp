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

		<form >
			<c:forEach var="vwr"  begin="1" end="${siteInstall.num}"  >
			<div class="site-register">
				<span class="iconfont icon-close del-item"></span>
				<div class="register-one">
					<div><span>站点1</span></div>
					<div>
						<span class="install">已安装</span>
						<i class="iconfont icon-arrow-down"></i>
					</div>
				</div>
				<div class="register-cnt">
					<div class="register-item">
						<div class="register-item-title">站点名称</div>
						<input type="text" name="siteName" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">IP地址</div>
						<input type="text" name="ip" placeholder="请输ip地址"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">楼宇</div>
						<input type="text" name="building" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">楼层</div>
						<input type="text" name="floorNum" placeholder="请输楼层名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">PC机型号</div>
						<input type="text" name="pcModel" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">安装情况</div>
						<div class="register-radio">
							<div class="mui-input-row mui-radio mui-left">
								<label>已安装</label>
								<input name="install" type="radio" value="1">
							</div>
							<div class="mui-input-row mui-radio mui-left">
								<label>未安装</label>
								<input name="install" type="radio" value="0">
							</div>
						</div>
					</div>
					<div class="register-item">
						<div class="register-item-title">上传图片</div>
						<div class="datum-upload site-width">
							<div>
								<i class="iconfont icon-plus"></i>
								<input type="file" name="" id="" value="" />
							</div>
							<div>
								<img src="../images/1.jpg"/>
								<span class="iconfont icon-close"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
                <div class="large-img">
                    <img src="../images/video.png"/>
                    <span class="iconfont icon-close"></span>
                </div>
			</c:forEach>
		</form>
			<div class="fix-hole"></div>
		    <div class="check-distribuion-btn">
		    	<input type="button" value="保存" />
		    	<input type="button" value="取消" />
		    </div>
		</div>


        <div class="register-button">
            <i class="iconfont icon-increase" style="color: #81B3FF;"></i>
            <i class="iconfont icon-reduce"></i>
        </div>

        <script type="text/javascript" src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>resources/mobile/js/ims.js" ></script>
		<script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>

		<script type="text/javascript">
			$(function(){
				enterprise.siteDelIetm();
                enterprise.init();
			})
		</script>
	</body>
</html>
