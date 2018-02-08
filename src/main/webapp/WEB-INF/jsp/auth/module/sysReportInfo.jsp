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
<!--表格区域  -->
<table id="sysReportInfoTable" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="addSysReportInfo" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <!-- <button id="modifyUser" class="btn btn-default" type="button"><span class="glyphicon glyphicon-edit"></span>修改
    </button> 
    <button id="deleteProductInfo" class="btn btn-default" type="button"><span class="glyphicon glyphicon-remove"></span>删除
    </button>-->
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
                                    placeholder="请输入设备名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" >设备描述:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="repoetDesc" name="repoetDesc"
                                           placeholder="请输入设备描述">
                                </div>
                            </div>
							<div class="radio">
							    <label>
							        <input type="radio" name="reportType"  value="0" checked> 凭条|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="1" >  发票|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="2" > 缴款|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="3" > 缴款单据|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="4" > 单据|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="5" > 台账|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="6" > 处方医嘱
							    </label>
							</div>
							<div class="radio">
							    <label>
							        <input type="radio" name="reportType"  value="7">申请单 |
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="8" > 治疗单据|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="9" > 医疗文书|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="10" > 临时医嘱|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="11" > 报告调阅|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="12" > 巡视单|
							    </label>
							    <label>
							        <input type="radio" name="reportType"  value="13" > 报表
							    </label>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/language/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/sysReportInfo.js"></script>
</html>