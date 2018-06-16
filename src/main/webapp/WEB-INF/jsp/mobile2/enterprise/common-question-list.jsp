<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/16
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
    <style type="text/css">
        .wap-tab{
            display: flex;
            margin-bottom: 10px;
            padding: 0px 0px;
        }
        .wap-tab>span{
            flex: 1;
            height: 30px;
            line-height: 30px;
            font-size: 12px;
            color: #666666;
            text-align: center;
            border: 1px solid #FFFFFF;
        }
        .wap-tab>span.active{
            color: #fff;
            background: #81B3FF;
            border: 1px solid #81B3FF;
        }
        /*.wap-tab>span:first-child{*/
            /*border-radius: 4px 0 0 4px;*/
        /*}*/
        /*.wap-tab>span:last-child{*/
            /*border-radius:0 4px 4px 0;*/
        /*}*/
    </style>
</head>
<body>
<div class="wrap">
    <div class="wrap-cnt">
        <div>
            <div class="wap-tab">
                <span class="active">全部</span>
                <span >A</span>
                <span >B</span>
                <span >C</span>
                <span >D</span>
                <span ><i class="iconfont icon-search"></i></span>
            </div>

            <div class="index-date">
                <p>06-04（4条）</p>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号</span>
                    <span class="index-date_status">
								<i class="index-A">A</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号</span>
                    <span class="index-date_status">
								<i class="index-B">B</i>
								<i class="index-wqr">已打回</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特病号</span>
                    <span class="index-date_status">
								<i class="index-C">C</i>
								<i class="index-yqr">已确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号病号</span>
                    <span class="index-date_status">
								<i class="index-D">D</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
            </div>
            <div class="index-date">
                <p>06-04（4条）</p>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号</span>
                    <span class="index-date_status">
								<i class="index-A">A</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号</span>
                    <span class="index-date_status">
								<i class="index-B">B</i>
								<i class="index-wqr">已打回</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特病号</span>
                    <span class="index-date_status">
								<i class="index-C">C</i>
								<i class="index-yqr">已确认</i>
							</span>
                </a>
                <a href='#'>
                    <span class="index-date_txt">五楼护士站-NIS部署-特大病号病号特大病号病号特大病号病号</span>
                    <span class="index-date_status">
								<i class="index-D">D</i>
								<i class="index-wqr">未确认</i>
							</span>
                </a>
            </div>
        </div>
        <div class="hide">
            站点
        </div>
        <div class="hide">
            分享
        </div>
        <div class="hide">
            我的
        </div>
    </div>
    <!--新增-->
    <a href="#" class="wrap-add">
        <i class="iconfont icon-add"></i>
    </a>
    <!--底部菜单-->
    <div class="wrap-foot">
        <div class="active">
            <i class="iconfont icon-task"></i>
            任务
        </div>
        <div>
            <i class="iconfont icon-site"></i>
            站点
        </div>
        <div>
            <i class="iconfont icon-upload"></i>
            上传
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
</script>
</body>
</html>
