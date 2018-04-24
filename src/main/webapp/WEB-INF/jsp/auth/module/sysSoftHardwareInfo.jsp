<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head> 
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>软硬件设备类型表</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<style>
	.table-align{
	    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        font-size:12px;
	}
	.table-align tr td:nth-child(3) {
  	word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
 	font-size:12px;
 	}
 		.table-align tr td:nth-child(4) {
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
<body>
<div class="row" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3" >
            <label class="col-sm-6 control-label text-right" for="shQCode">软硬件编号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="shQCode"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="shQName">软硬件名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="shQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="shQType">设备分类：</label>
            <div class="col-sm-4">
                <select name="shType" id="shQType" class="form-control">
                    <option value="-1"></option>
                    <option value="0">软件</option>
                    <option value="1">硬件</option>
                </select>
            </div>
        </div>
        <button type="button" class="btn btn-success btn-sm" id="query" style="margin-left:20px">
            <span class="glyphicon glyphicon-search"></span>
            查询
        </button>
    </form>
</div>
<!--表格区域  -->
<table id="infoTable" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>
<!--模态框  -->
<div class="modal fade" id="sysSoftHardwareInfoModal" tabindex="-1" role="dialog" aria-labelledby="sysSoftHardwareInfoModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:550px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">软硬件设备类型表</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="sysSoftHardwareInfoForm">
                            <div class="form-group" id="code" style="display:none;">
                                <label class="col-sm-4 control-label" >软硬件代码:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shCode" 
                                    name="shCode" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >设备分类</label>
                                <div class="col-sm-6">
	                                <select name="shType" id="shType" class="form-control" >
										<option value="0">软件</option>
										<option value="1">硬件</option>
									</select>        
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >应用环境</label>
                                <div class="col-sm-6">
	                                <select name="shEnvType" id="shEnvType" class="form-control" >
										<option value="0">测试</option>
										<option value="1">正式</option>
										<option value="2">测试&正式</option>
									</select>        
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >设备名称:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shName" name="shName" 
                                    placeholder="请输入设备名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >设备描述:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shDesc" name="shDesc"
                                           placeholder="请输入设备描述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >推荐品牌:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shBrand" name="shBrand"
                                           placeholder="请输入推荐品牌">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >推荐型号:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shBrandType" name="shBrandType"
                                           placeholder="请输入推荐型号">
                                </div>
                            </div>
                            <input type="hidden" name="id" id="id">
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="save" type="submit">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/sysSoftHardwareInfo.js"></script>
</html>