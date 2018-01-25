<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>功能信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css">
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="resources/img/logo.ico">
</head>
<body>
<!--表格区域  -->
<table id="funcTable"></table>
<!--toolbar区域  -->
<div class="btn-group" id="funcbtntoolbar">
    <button id="addfunc" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <button id="modifyfunc" class="btn btn-default" type="button"><span class="glyphicon glyphicon-edit"></span>修改
    </button>
    <button id="deletefunc" class="btn btn-default" type="button"><span class="glyphicon glyphicon-remove"></span>删除
    </button>
</div>
<!--模态框  -->
<div class="modal fade" id="funcModal" tabindex="-1" role="dialog" aria-labelledby="funcFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">功能信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="funcForm">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="funcName">功能名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="funcName" name="funcName"
                                           placeholder="请输入功能名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="funcType">功能类型</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="funcType" id="funcType"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="funcAction">功能路径</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="funcAction" name="funcAction"
                                           placeholder="请输入功能路径">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="funcDesc">功能描述</label>
                                <div class="col-sm-8">
                                    <input class="form-control" type="text" id="funcDesc" name="funcDesc"
                                           placeholder="功能信息描述">
                                </div>
                            </div>
                            <input type="hidden" id="funcId" name="funcId">
                            <input type="reset" style="display:none;"/>
                            <div class="text-center">
                                <button class="btn btn-primary" id="savefunc" type="submit">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/funcinfo.js"></script>
</html>