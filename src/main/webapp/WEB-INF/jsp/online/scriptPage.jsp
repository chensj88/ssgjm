<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/28
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
            font-size:12px;
        }
        .table-align tr td:nth-child(4) {
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size:12px;
        }
        .table-align tr td:nth-child(2) {
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size:12px;
        }
        .table-align tr td:nth-child(5) {
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size:12px;
        }
    </style>
</head>
<body>
<div class="row text-center" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="QName">脚本名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="QName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <button type="button" class="btn btn-success btn-sm" id="query">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>
    </form>
</div>
<!--表格区域  -->
<table id="infoTable" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar" >
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>

<!--模态框  -->
<div class="modal fade" id="scriptModal" tabindex="-1" role="dialog" aria-labelledby="scriptFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">脚本信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="scriptForm">
                            <div class="form-group" id="videoNameDiv">
                                <label class="col-sm-3 control-label" for="name">脚本名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="请输入脚本名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="sDesc">脚本描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="sDesc" name="sDesc" placeholder="请输入脚本描述">
                                </div>
                            </div>
                                <div class="form-group" >
                                    <label class="col-sm-3 control-label" for="appName">适用系统</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="appName" name="appName" appName="请输入适用系统" data-provide="typeahead">
                                    </div>
                                </div>
                            <div class="form-group" >
                                <label class="col-sm-3 control-label" for="dataType">数据类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" id="dataType" name="dataType">
                                        <option value="0">基础数据</option>
                                        <option value="1">易用数据</option>
                                    </select>
                                </div>
                            </div>
                                <input type="hidden" id="appId" name="appId">
                            <div class="form-group" id="isModifyDiv">
                                <label class="col-sm-3 control-label" for="isModify">文件替换</label>
                                <div class="col-sm-6">
                                    <select class="form-control" id="isModify" name="isModify">
                                        <option value="0">否</option>
                                        <option value="1">是</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" id="uploadFileDiv">
                                <label class="col-sm-3 control-label" for="uploadFile">上传文件</label>
                                <div class="col-sm-6">
                                    <input id="uploadFile" name="uploadFile" type="file">
                                </div>
                            </div>
                            <input type="hidden" name="id" id="id">
                            <input type="hidden" name="vid" id="vid">
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/online/script.js"></script>
</html>
