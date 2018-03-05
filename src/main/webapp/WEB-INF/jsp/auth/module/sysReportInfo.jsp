<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head> 
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>报表类信息表</title>
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
<div class="row" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3" >
            <label class="col-sm-5 control-label text-right" for="reportQCode">编号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="reportQCode"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-5 control-label text-right" for="reportQName">名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="reportQName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="reportQType">类型：</label>
            <div class="col-sm-4">
                <select class="form-control" id="reportQType" name="reportQType">
                    <option value="0" >凭条</option>
                    <option value="1" >发票</option>
                    <option value="2" >缴款</option>
                    <option value="3" >缴款单据</option>
                    <option value="4" >单据</option>
                    <option value="5" >台账</option>
                    <option value="6" >处方医嘱</option>
                    <option value="7">申请单</option>
                    <option value="8" >治疗单据</option>
                    <option value="9" >医疗文书</option>
                    <option value="10" >临时医嘱</option>
                    <option value="11" >报告调阅</option>
                    <option value="12" >巡视单</option>
                    <option value="13" >报表</option>
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
<div class="modal fade" id="sysReportInfoModal" tabindex="-1" role="dialog" aria-labelledby="sysReportInfoModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:650px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">报表类信息表</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-7 col-sm-7 col-xs-7" role="form" id="sysReportInfoForm">
                           
                            <div class="form-group" id="code" style="display:none;">
                                <label class="col-sm-4 control-label" >代码:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="reportCode" 
                                    name="reportCode" readonly="readonly" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >名称:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="reportName" name="reportName" 
                                    placeholder="请输入名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >描述:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="reportDesc" name="reportDesc"
                                           placeholder="请输入描述">
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-4 control-label" for="reportType">类型:</label>
                                <div class="col-sm-6">
                                    <select class="form-control" id="reportType" name="reportType">
                                        <option value="0" >凭条</option>
                                        <option value="1" >发票</option>
                                        <option value="2" >缴款</option>
                                        <option value="3" >缴款单据</option>
                                        <option value="4" >单据</option>
                                        <option value="5" >台账</option>
                                        <option value="6" >处方医嘱</option>
                                        <option value="7">申请单</option>
                                        <option value="8" >治疗单据</option>
                                        <option value="9" >医疗文书</option>
                                        <option value="10" >临时医嘱</option>
                                        <option value="11" >报告调阅</option>
                                        <option value="12" >巡视单</option>
                                        <option value="13" >报表</option>
                                    </select>
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
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/sysReportInfo.js"></script>
</html>