<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/11
  Time: 20:30
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
    <title>系统参数信息</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <base href="<%=basePath%>"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body class="container-fluid">
<div class="row text-center" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="paramQName">参数名：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="paramQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="paramQValue">参数值：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="paramQValue"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3 text-center">
            <button type="button" class="btn btn-success btn-sm" id="query">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>
    </form>
</div>
<!--表格区域  -->
<table id="infoTable"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>
<!--模态框  -->
<div class="modal fade" id="paramModal" tabindex="-1" role="dialog" aria-labelledby="dparamModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">参数信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="paramForm">

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="paramName">参数名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="paramName" name="paramName"
                                           placeholder="请输入参数名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="paramValue">参数值</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="paramValue" name="paramValue"
                                           placeholder="请输入参数值">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="paramDesc">参数描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="paramDesc" name="paramDesc"
                                           placeholder="请输入参数描述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="isStop">是否停用</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isStop" id="isStop">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="paramType">参数类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="paramType" id="paramType">
                                        <option value="2">正式&测试</option>
                                        <option value="1">正式</option>
                                        <option value="0">测试</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="id" name="id"/>
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-12 text-center">
                                <button class="btn btn-primary" id="saveParam" type="button">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/config/paramInfo.js"></script>
</html>
