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
    <title>Insert title here</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-treeview.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/fileinput.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
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
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row text-center" id="queryScope">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="serialQName">客户名称：</label>
                <input type="text" class="input-style" id="serialQName" data-provide="typeahead"/>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="deptQName">科室名称：</label>
                <input type="text" class="input-style" id="deptQName"/>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <button type="button" class="btn btn-success btn-sm" id="query">
                    <span class="glyphicon glyphicon-search"></span>
                    查询
                </button>
            </div>
        </div>
    </div>
    <!--表格区域  -->
    <table id="infoTable" class="table-align"></table>
    <!--toolbar区域  -->
    <div class="btn-group" id="btntoolbar">
        <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    </div>

    <!--模态框  -->
    <div class="modal fade" id="deptModal" tabindex="-1" role="dialog" aria-labelledby="deptFormModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:450px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">科室信息</h4>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="deptForm">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="serialName">客户名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="serialName" name="serialName"
                                               placeholder="请输入客户名称" data-provide="typeahead">
                                    </div>
                                </div>
                                <input type="hidden" id="serialNo" name="serialNo">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="deptName">科室名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="deptName" name="deptName"
                                               placeholder="请输入科室名称">
                                    </div>
                                </div>
                                <input type="hidden" name="id" id="id">
                                <input type="reset" style="display:none;"/>
                                <div class="col-sm-8 text-center">
                                    <button class="btn btn-primary" id="save" type="button">保存</button>
                                    <button class="btn btn-danger" data-dismiss="modal">取消</button>
                                </div>
                            </form>
                        </div>
                    </div>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/hospital/dept.js"></script>
</html>