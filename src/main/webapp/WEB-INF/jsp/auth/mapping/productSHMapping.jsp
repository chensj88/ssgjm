<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/11
  Time: 08:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品软硬件信息映射</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <%--<base href="<%=basePath%>">--%>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
        }
        .table-align tr td:nth-child(5) {
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size:12px;
        }
        .table-align td,.table-align th{
            font-size: 12px;
        }
        #queryBData tr td:nth-child(1){
            font-size: 12px;
        }
        #infoTable{
            font-size:12px;
        }
        #infoTable tr td:nth-child(1) {
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size:12px;
        }

        .center{
            MARGIN-RIGHT: auto;
            MARGIN-LEFT: auto;
            height:200px;
            vertical-align:middle;
            line-height:200px;
        }

        .success {
            background-color:#337AB7;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row text-center" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-xs-6 control-label text-right" for="productName">产品名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="productName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-xs-6 control-label text-right" for="productCode">产品编码：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="productCode"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3 text-center">
            <button type="button" class="btn btn-success btn-sm" id="queryPD">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>
    </form>
</div>
<div class="row" style="height: 200px;">
    <div class="col-sm-12">
        <!--表格区域  -->
        <table id="productTable" class="table-align"></table>
    </div>
</div>

<div class="row" style="height: 300px;">
    <div class="col-sm-5">
        <table id="query" class="table-align">
        </table>
    </div>
    <div class="col-sm-2 text-center center">
        <div class="row" style="height: 150px;"></div>
        <p>
            <a class="btn btn-info btn-lg" id="moveLeft">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
        </p>

        <p>
            <a class="btn btn-info btn-lg"  id="moveRight">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </p>
    </div>
    <div class="col-sm-5">
         <table id="config" class="table-align">

        </table>
    </div>
</div>

<div id="toolbarA">
    <div class="columns pull-right search-button"><button class="btn btn-default"  style="height:34px" type="button" name="refresh" title="查询" id="infoAbtn"><i class="glyphicon glyphicon-search icon-search"></i></button></div>
    <div class="columns pull-right search-input"><input class="form-control" type="text" placeholder="请输入软硬件名称或编号" id="infoA"></div>
</div>
<div id="toolbarB">
    <div class="columns pull-right search-button"><button class="btn btn-default"  style="height:34px" type="button" name="refresh" title="查询" id="infoBbtn"><i class="glyphicon glyphicon-search icon-search"></i></button></div>
    <div class="columns pull-right search-input"><input class="form-control" type="text" placeholder="请输入软硬件名称或编号"  id="infoB"></div>
</div>
<%--弹出框--%>
<div class="modal fade" id="pdModal" tabindex="-1" role="dialog" aria-labelledby="pdModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:600px;overflow:auto;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="pdModalLabel">列表</h4>
            </div>
            <div class="modal-body" style="height: 200px;overflow:auto;">
                <table class="table-hover table-align"  id="infoTable"></table>
            </div>
            <div class="modal-footer">
                <div class="col-sm-12 text-center">
                    <button class="btn btn-primary" id="remove" type="button">提交</button>
                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/mapping/productSH.js"></script>
</html>
