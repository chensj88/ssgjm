<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<%@ include file="/commons/header.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>流程信息</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css">
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico">
</head>
<body>
<div class="row" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="flowQName">流程名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="flowQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="flowQCode">流程编码：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="flowQCode"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3 text-center">
            <button type="button" class="btn btn-success btn-sm" id="queryFlow">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>

    </form>
</div>
<!--表格区域  -->
<table id="flowTable"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="addFlow" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <button id="modifyFlow" class="btn btn-default" type="button"><span class="glyphicon glyphicon-edit"></span>修改</button>
    <button id="deleteFlow" class="btn btn-default" type="button"><span class="glyphicon glyphicon-remove"></span>删除</button>
</div>
<!--模态框  -->
<div class="modal fade" id="flowModal" tabindex="-1" role="dialog" aria-labelledby="flowFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">流程信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="flowForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="flowType">流程类型</label>
                                <div class="col-sm-6">
                                  <select class="form-control" name="flowType" id="flowType">
                                      <option value="1">流程小类</option>
                                      <option value="0">流程大类</option>
                                  </select>
                                </div>
                            </div>
                            <div id='flowParent'>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="flowParentCode">上级流程编号</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="flowParentCode" name="flowParentCode" data-provide="typeahead" placeholder="请输入上级流程编号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="flowParentName">上级流程名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="flowParentName" name="flowParentName" readonly="true"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="flowCode">流程编号</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="flowCode" name="flowCode"
                                           placeholder="请输入流程编号">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="flowName">流程名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="flowName" name="flowName"
                                           placeholder="请输入流程名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="flowDesc">流程描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="flowDesc" name="flowDesc"
                                           placeholder="请输入流程描述">
                                </div>
                            </div>
                            <input type="hidden" name="id" id="id">
                            <input type="hidden" name="flowPid" id="flowPid">
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="saveFlow" type="submit">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/flowinfo.js"></script>
</html>