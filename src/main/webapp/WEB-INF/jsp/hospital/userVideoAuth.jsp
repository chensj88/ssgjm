<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/24
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>医院用户视频权限</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-treeview.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/fileinput.min.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align {
            table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
            font-size: 12px;
        }

        .table-align tr td:nth-child(4) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }

        .table-align tr td:nth-child(2) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }

        .table-align tr td:nth-child(5) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }

        .hr {
            display: block;
            height: 0;
            overflow: hidden;
            font-size: 0;
            border-top: 1px solid #e3e3e3;
            margin: 12px 0
        }

        .hr10 {
            margin: 10px 0
        }

        .hr-dotted {
            border-top-style: dotted
        }
    </style>
</head>
<body>
<div class="row text-center" id="queryScope">
    <div class="row">
        <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3" role="group">
                <label class="col-sm-6 control-label text-right" for="serialName">客户名称：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control input-sm" style="width: 200px" id="serialName"
                           data-provide="typeahead"/>
                </div>
            </div>
            <input type="hidden" id="serialId">
            <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3" role="group">
                <label class="col-sm-6 control-label text-right" for="serialName">客户号：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control input-sm" style="width: 200px" id="serialNo" readonly/>
                </div>
            </div>
            <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3" role="group">
                <div class="btn-group" role="group" >
                <button type="button" class="btn btn-info btn-sm" id="queryCustomer">
                    <span class="glyphicon glyphicon-search"></span>
                    查询用户菜单
                </button>
                <button type="button" class="btn btn-success btn-sm" id="upload">
                    <span class="glyphicon glyphicon-upload"></span>
                    上传用户菜单
                </button>
                 <button type="button" class="btn btn-info btn-sm" id="queryUpload">
                   <span class="glyphicon glyphicon-search"></span>
                     查询上传结果
                 </button>
            </div>
            </div>
        </form>
    </div>
</div>
<div class="hr hr32 hr-dotted"></div>
<div class="row text-center">
    <form class="form-horizontal col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" id="uploadForm"  role="form">
        <div class="form-group" id="uploadFileDiv">
            <label class="col-sm-3 control-label" for="uploadFile">用户菜单信息：</label>
            <div class="col-sm-6">
                <input id="uploadFile" name="uploadFile" type="file">
            </div>
        </div>
    </form>
</div>
<!--表格区域  -->
<table id="infoTable" class="table-align"></table>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/hospital/videoAuth.js"></script>
</html>