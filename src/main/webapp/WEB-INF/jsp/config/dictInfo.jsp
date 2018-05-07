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
    <title>字典信息</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <base href="<%=basePath%>"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
            font-size:12px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row text-center" id="queryScope">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="dictQCode">字典编码：</label>
                <input type="text" class="input-style" id="dictQCode"/>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="dictQLabel">显示文本：</label>
                <input type="text" class="input-style" id="dictQLabel"/>
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
    <div class="modal fade" id="dictModal" tabindex="-1" role="dialog" aria-labelledby="dictModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:500px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">字典信息</h4>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="dictForm">

                                <div class="form-group" id="dictCodeDiv">
                                    <label class="col-sm-3 control-label" for="dictCode">字典编码</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="dictCode" name="dictCode"
                                               placeholder="请输入字典编码">
                                    </div>
                                </div>
                                <div class="form-group" id="dictValueDiv">
                                    <label class="col-sm-3 control-label" for="dictValue">字典值</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="dictValue" name="dictValue"
                                               placeholder="请输入字典值">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="dictLabel">显示文本</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="dictLabel" name="dictLabel"
                                               placeholder="请输入显示文本">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="dictDesc">字典描述</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="dictDesc" name="dictDesc"
                                               placeholder="请输入字典描述">
                                    </div>
                                </div>
                                <input type="hidden" id="dictSort" name="dictSort"/>
                                <input type="reset" style="display:none;"/>
                                <div class="col-sm-12 text-center">
                                    <button class="btn btn-primary" id="saveDict" type="button">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/config/dictInfo.js"></script>
</html>
