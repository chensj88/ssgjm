<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head> 
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品信息</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<style>
	.table-align{
	    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        font-size:12px;
	}
	.table-align tr td:nth-child(2),
    .table-align tr td:nth-child(3),
    .table-align tr td:nth-child(5){
  	word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
 	font-size:12px;
 	}

    #queryScope label{
        font-size:12px;
        width: 90px;
        vertical-align: center;
        padding: 0px;
    }
    #queryScope input{
        font-size:12px;
        width: 100px;
    }
	
</style>
<body>
<div class="row" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" >
        <div class="input-group col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <label class="col-sm-6 control-label text-right" for="flowQName">流程名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="flowQName" data-provide="typeahead"/>
            </div>
        </div>
        <div class="input-group col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <label class="col-sm-6 control-label text-right" for="flowQCode">流程编号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="flowQCode" data-provide="typeahead"/>
            </div>
        </div>
        <input type="hidden" id="flowQId" name="flowQId">
        <div class="input-group col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <label class="col-sm-6 control-label text-right" for="quesQName">问题名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="quesQName"/>
            </div>
        </div>
        <div class="input-group col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <label class="col-sm-6 control-label text-right" for="quesQCode">问题编号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="quesQCode" />
            </div>
        </div>
        <div class="input-group col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <div class="col-sm-12">
                <button type="button" class="btn btn-success btn-sm" id="query">
                    <span class="glyphicon glyphicon-search"></span>
                    查询
                </button>
            </div>
        </div>

    </form>
</div>
<!--表格区域  -->
<table id="infoTable" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>
<!--模态框  问题信息-->
<div class="modal fade" id="flowQuestionModal" tabindex="-1" role="dialog" aria-labelledby="flowQuestionModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">流程问题信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="flowQuestionForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label control-label" for="flowCode" >流程编号</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="flowCode" name="flowCode" data-provide="typeahead" placeholder="请输入流程编号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="flowName">流程名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="flowName" name="flowName" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"  for="quesName">问题名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="quesName" name="quesName"  placeholder="请输入问题名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="quesType">问题类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="quesType" id="quesType">
                                        <option value="0">单选</option>
                                        <option value="1">复选</option>
                                        <option value="2">联动单选</option>
                                        <option value="3">联动复选</option>
                                        <option value="4">问答题</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3  control-label" for="status">问题状态</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="status" id="status">
                                        <option value="1">生效</option>
                                        <option value="0">失效</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3  control-label" for="resultNum">答案数</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="resultNum" name="resultNum"  placeholder="请输入答案数">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="quesDesc">问题描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="quesDesc" name="quesDesc"
                                           placeholder="请输入问题描述">
                                </div>
                            </div>
                            <%--隐藏域 保留流程Id信息--%>
                            <input type="hidden" id="flowId" name="flowId">
                            <input type="hidden" name="id" id="id">
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="saveQ" type="button">保存</button>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--模态框  问题信息-->
<div class="modal fade" id="answerModal" tabindex="-1" role="dialog" aria-labelledby="answerModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="answerModalLabel">答案信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="answerForm">
                            <div class="row" id="answer_container">
                                <div class="form-group" qtype="0">
                                  <label class="col-xs-2 control-label q">答案</label>
                                    <div class="col-sm-6">
                                        <input class="form-control">
                                    </div>
                                </div>
                                <div class="form-group" qtype="0">
                                    <label class="col-xs-2 control-label q">答案</label>
                                    <div class="col-sm-6">
                                        <input class="form-control">
                                    </div>
                                    <span class="glyphicon glyphicon-remove" ></span>
                                </div>
                            </div>
                            <div class="row" >
                               <div class="col-sm-10 btn-group" role="group">
                                 <button class="btn btn-default" onclick="addA('0',this,event)">
                                     <span class="glyphicon glyphicon-plus"></span>添加
                                 </button>
                                 <button class="btn btn-default" onclick="addA('1',this,event)">
                                     <span class="glyphicon glyphicon-plus"></span>其他
                                 </button>
                               </div>
                            </div>
                            <input type="hidden" name="quesId" id="quesId">
                            <input type="hidden" name="resultAns" id="resultAns">
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="saveA" type="button">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/module/flowQuestionInfo.js"></script>
</html>