<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/1/24
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>业务流程信息</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico">
</head>
<body>
<!--表格区域  -->
<table id="userTable"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar">
    <button id="addFlow" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <button id="modifyFlow" class="btn btn-default" type="button"><span class="glyphicon glyphicon-edit"></span>修改
    </button>
    <button id="deleteFlow" class="btn btn-default" type="button"><span class="glyphicon glyphicon-remove"></span>删除
    </button>
</div>
<!--模态框  -->
<div class="modal fade" id="flowModal" tabindex="-1" role="dialog" aria-labelledby="FlowFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">流程信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="userForm">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="userid">登录名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="userid" name="userid"
                                           placeholder="请输入登录名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="yhmc">用户名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="yhmc" name="yhmc" placeholder="请输入用户名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="email">邮箱</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="email" name="email"
                                           placeholder="例如:123@123.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="mobile">手机号码</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="mobile" name="mobile"
                                           placeholder="例如:13012345678">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="status">允许登陆</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="status" id="status">
                                        <option value="1">是</option>
                                        <option value="2">否</option>
                                    </select>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="userType">用户类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="userType" id="userType">
                                        <option value="0">医院</option>
                                        <option value="1">公司</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" name="orgid" id="orgid">
                            <input type="hidden" name="password" id="password">
                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="saveUser" type="submit">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/userinfo.js"></script>
</html>