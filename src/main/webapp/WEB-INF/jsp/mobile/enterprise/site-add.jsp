<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>站点安装登记-新增</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="../css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="../css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="../css/common.css" />
		<link rel="stylesheet" type="text/css" href="../css/enterprise.css" />
		<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_el6x0tcq3ufecdi.css"/>
	</head>
	<body>
		<div class="mui-content gray">
		    <!--header-->
			<div class="header">
				<span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
				<div>挂号处站点安装</div>
				<span class="mui-icon mui-icon-more"></span>
			</div>
			<div class="hole"></div>
			<div class="site-install">
				<div class="part-one">
					<div class="left">
						<span>挂号处</span>
					</div>
					<div class="right">
						<p>完成站点数：<span><i>0</i>/<i>5</i></span></p>
						<p>安装人：<span>张三</span></p>
					</div>
				</div>
				<div class="part-item">
					<div class="part-two">
						<div>
							<span class="software">软件</span>
						</div>
						<div>HIS前台</div>
					</div>
					<div class="part-two">
						<div>
							<span class="hardware">硬件</span>
						</div>
						<div>台式机、发票打印机、凭单打印机、报价器、 多屏、多种刷卡器</div>
					</div>
				</div>
			</div>
			<div class="site-register">
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
						<input type="text" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">IP地址</div>
						<input type="text" placeholder="请输ip地址"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">楼宇</div>
						<input type="text" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">楼层</div>
						<input type="text" placeholder="请输楼层名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">PC机型号</div>
						<input type="text" placeholder="请输入站点名称"/>
					</div>
					<div class="register-item">
						<div class="register-item-title">安装情况</div>
						<div class="register-radio">
							<div class="mui-input-row mui-radio mui-left">
								<label>未解决</label>
								<input name="radio1" type="radio">
							</div>
							<div class="mui-input-row mui-radio mui-left">
								<label>未解决</label>
								<input name="radio1" type="radio">
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
			<div class="register-button">
				<i class="iconfont icon-increase"></i>
				<i class="iconfont icon-reduce"></i>
			</div>
			<div class="fix-hole"></div>
		    <div class="check-distribuion-btn">
		    	<input type="button" value="保存" />
		    	<input type="button" value="取消" />
		    </div>
		</div>
		<script type="text/javascript" src="../js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript">
			$(function(){
				$('.register-one').click(function(){
					let _this= $(this);
					_this.find('i').toggleClass('up');
					$(this).next('.register-cnt').slideToggle();
				})
			})
		</script>
	</body>
</html>
