<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/7
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品基础Demo</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/font-awesome.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/doublebox-bootstrap.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align {
            table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
        }

        .table-align tr td:nth-child(5) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }

        .table-align td, .table-align th {
            font-size: 12px;
        }

        #queryBData tr td:nth-child(1) {
            font-size: 12px;
        }

        #infoTable tr td:nth-child(1) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }

        .center {
            MARGIN-RIGHT: auto;
            MARGIN-LEFT: auto;
            height: 200px;
            vertical-align: middle;
            line-height: 200px;
        }

        .align-center {
            margin: 0 auto; /* 居中 这个是必须的，，其它的属性非必须 */
            width: 500px; /* 给个宽度 顶到浏览器的两边就看不出居中效果了 */
            background: red; /* 背景色 */
            text-align: center; /* 文字等内容居*/
        }

        #infoTable {
            font-size: 12px;
        }

        .success {
            background-color: #337AB7;
        }

        .ue-container {
            width: 81.5%;
            margin: 0 auto;
            margin-top: 4.5%;
            padding: 20px 40px;
            vertical-align: middle;
            /* border: 1px solid #ddd;*/
            background: #fff;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row text-center" id="queryScope">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
            <label class="col-sm-4 form-text" for="productName">产品名称：</label>
            <input type="text" class="input-style" id="productName"/>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
            <label class="col-sm-4 form-text" for="productCode">产品编码：</label>
            <input type="text" class="input-style" id="productCode"/>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
            <button type="button" class="btn btn-success btn-sm" id="queryPD">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>
    </div>
</div>
<div class="row text-center" style="height: 300px;">
    <div class="col-sm-1"></div>
    <div class="col-sm-10">
        <!--表格区域  -->
        <table id="productTable" class="table-align"></table>
    </div>
    <div class="col-sm-1"></div>
</div>
<div class="row" style="height: 300px;">
    <div class="ue-container">
        <select multiple="multiple" size="10" name="doublebox" class="demo" id="doublebox"></select>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/doublebox-bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/mapping/ptBdataD.js"></script>
<script>
</script>
</html>
