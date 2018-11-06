<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>菜单信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="卫宁实施工具">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>

    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/jquery.treegrid.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/bootstrap3-editable/css/bootstrap-editable.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/bootstrap-treeview/bootstrap-treeview.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/google-font/google.fonts.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/toastr/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/iconfont/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/bootstrapValidator/css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="<%=basePath%>resources/assets/js/ace-extra.min.js"></script>
    <style>
        .tree-container {
            height: 500px;
            overflow: scroll;
        }

        .table-align {
            table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            font-size: 12px;
        }

        .table-align tr th:nth-child(1),
        .table-align tr td:nth-child(1) {
            width: 5px;
        }

        .table-align tr td:nth-child(7) {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="main-container" style="margin: 10px 0px;">
    <div class="row" id="queryScope">
        <div class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
            <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <label class="col-sm-6 control-label text-right" for="modQName">菜单名称：</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-sm" id="modQName"/>
                </div>
            </div>
            <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3 text-center">
                <button type="button" class="btn btn-success btn-sm" id="query">
                    <span class="glyphicon glyphicon-search"></span>
                    查询
                </button>
            </div>
        </div>
    </div>
    <div id="config" class="tab-pane in active">
        <table id="infoTable" class="table-align"></table>
    </div>
</div>
<!--toolbar区域  -->
<div class="btn-group" id="modulebtntoolbar">
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>

<%--modal--%>
<div class="modal fade" id="moduleModal" tabindex="-1" role="dialog" aria-labelledby="moduleFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:550px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">菜单信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="moduleForm">
                            <div class="form-group" id="btnLevel1">
                                <label class="col-sm-3 control-label" for="modLevel">菜单等级</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="modLevel" id="modLevel">
                                        <option value="1">一级菜单</option>
                                        <option value="2">二级菜单</option>
                                        <option value="3">三级菜单</option>
                                        <option value="9">按钮信息</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" id="modNameLabel" for="modName">菜单名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="modName" name="modName"
                                           placeholder="请输入菜单名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" id="modDescLabel" for="modDesc">菜单说明</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="modDesc" name="modDesc"
                                           placeholder="请输入菜单说明">
                                </div>
                            </div>

                            <div id="pModule" style="display: none">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" id="modPNameLabel" for="modPName">上级菜单名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="modPName" name="modPName"
                                               placeholder="请输入上级菜单名称" data-provide="typeahead" >
                                    </div>
                                </div>
                                <input type="hidden" id="parId" name="parId">
                            </div>
                            <div class="form-group" id="modUrlDiv">
                                <label class="col-sm-3 control-label" for="modUrl">链接地址</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="modUrl" name="modUrl"
                                           placeholder="请输入链接地址">
                                </div>
                            </div>
                            <div class="form-group" id="iconPathDiv">
                                <label class="col-sm-3 control-label" for="iconPath">菜单图标</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="iconPath" name="iconPath"
                                           placeholder="请输入菜单图标">
                                </div>
                            </div>
                            <div class="form-group" id="isLeafDiv">
                                <label class="col-sm-3 control-label" for="isLeaf">叶子节点</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isLeaf" id="isLeaf">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" id="isManagerDiv">
                                <label class="col-sm-3 control-label" for="isManager">管理员功能</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isManager" id="isManager">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="modId" name="modId">
                            <input type="hidden" id="isDel" name="isDel">
                            <input type="hidden" id="orderValue" name="orderValue">
                            <input type="reset" style="display:none;"/>
                            <div class="text-center">
                                <button class="btn btn-primary" id="savemodule" type="button">保存</button>
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
<!--[if !IE]> -->
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<![endif]-->
<!--bootstrap-->
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-treegrid.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/toastr/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrapValidator/js/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/toastr/js/toastr.min.js"></script>
<!--ace-->
<script type="text/javascript" src="<%=basePath%>resources/assets/js/ace-elements.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/ace.min.js"></script>
<%--user script--%>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/moduleinfo.js"></script>
</html>