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
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/fileinput.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/webuploader/webuploader.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style type="text/css">
        .table-align{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            font-size:12px;
        }
        .table-align tr td:nth-child(3),
        .table-align tr td:nth-child(4),
        .table-align tr td:nth-child(6) {

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
<div class="container-fluid">
    <div class="row text-center" id="queryScope">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="flowQName">流程名称：</label>
                <input type="text" class="input-style" id="flowQName"/>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 div-style text-center">
                <label class="col-sm-4 form-text" for="flowQCode">流程编码：</label>
                <input type="text" class="input-style" id="flowQCode"/>
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
                                <div id="flowCodeDiv">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="flowCode">流程编号</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="flowCode" name="flowCode"
                                                   placeholder="请输入流程编号" readonly>
                                        </div>
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
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="isMust">是否必须</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" id="isMust" name="isMust">
                                            <option value="0">否</option>
                                            <option value="1">是</option>
                                        </select>
                                    </div>
                                </div>
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
                                    <label class="col-sm-3 control-label" for="uploadFile">调研问卷</label>
                                    <div class="col-sm-6">
                                        <input id="uploadFile" name="uploadFile" type="file">
                                    </div>
                                    <span style="margin-left:160px;font-weight: bold;color: red;">
                                     上传文件格式支持:doc,docx,xls,xlsx
                                    </span>
                                </div>
                                <input type="hidden" name="id" id="id">
                                <input type="hidden" name="vid" id="vid">
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
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/flowinfo.js"></script>
</html>