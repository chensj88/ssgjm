<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head> 
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>基础数据类型</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico">
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
<div class="row" id="queryDataScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-5 control-label text-right" for="dbQName">数据库名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="dbQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-4 control-label text-right" for="tableQName">表名：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="tableQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="tableQCnName">表中文名：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="tableQCnName"/>
            </div>
        </div>
        <button type="button" class="btn btn-success btn-sm" id="queryData" style="margin-left:20px">
            <span class="glyphicon glyphicon-search"></span>
            查询
        </button>
    </form>
</div>
<!--表格区域  -->
<table id="sysDataInfo" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="addSysDataInfo" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <!-- <button id="modifyUser" class="btn btn-default" type="button"><span class="glyphicon glyphicon-edit"></span>修改
    </button> 
    <button id="deleteProductInfo" class="btn btn-default" type="button"><span class="glyphicon glyphicon-remove"></span>删除
    </button>-->
</div>
<!--模态框  -->
<div class="modal fade" id="sysDataInfoModal" tabindex="-1" role="dialog" aria-labelledby="sysDataInfo">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">基础数据类型</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="sysDataInfoForm">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >数据库名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="dbName" name="dbName" placeholder="请输入数据库名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >数据库表名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tableName" name="tableName" placeholder="请输入数据库表名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >库表中文名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tableCnName" name="tableCnName"
                                           placeholder="请输入库表中文名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >数据类型</label>
                                <div class="col-sm-6">
	                                <select name="dataType" id="dataType">
										<option value="0">国标数据</option>
										<option value="1">行标数据</option>
										<option value="2">共享数据</option>
										<option value="3">易用数据</option>
									</select>        
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >注意事项</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tableAttention" name="tableAttention"
                                           placeholder="请输入注意事项">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >标准文号</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="standardCode" name="standardCode"
                                           placeholder="请输入标准文号">
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
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/sysDataInfo.js"></script>
</html>