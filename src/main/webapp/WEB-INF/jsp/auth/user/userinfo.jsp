<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-treeview.min.css">
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="resources/img/logo.ico">
    <style type="text/css">
        .table-align{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
            width: 100%;
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
</head>
<body>
<div class="row text-center" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-4 control-label text-right" for="cName">姓名：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="cName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-4 control-label text-right" for="userCard">员工号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="userCard"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-4 control-label text-right" for="telephone">手机号：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="telephone"/>
            </div>
        </div>
        <button type="button" class="btn btn-success btn-sm" id="queryUser">
            <span class="glyphicon glyphicon-search"></span>
            查询
        </button>
    </form>
</div>
<!--表格区域  -->
<table id="userTable" class="table-align table-hover"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar" >
    <button id="addUser" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
    <%--<button id="modifyUser" class="btn btn-warning" type="button"><span class="glyphicon glyphicon-edit"></span>修改</button>
    <button id="deleteUser" class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>删除 </button>--%>
</div>

<!--模态框  -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">用户信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="userForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="userid">登录名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="userid" name="userid"
                                           placeholder="请输入登录名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="yhmc">用户名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="yhmc" name="yhmc" placeholder="请输入用户名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="email">邮箱</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="email" name="email"
                                           placeholder="例如:123@123.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="mobile">手机号码</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="mobile" name="mobile"
                                           placeholder="例如:13012345678">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="status">允许登陆</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="status" id="status">
                                        <option value="1">是</option>
                                        <option value="2">否</option>
                                    </select>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="userType">用户类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="userType" id="userType">
                                        <option value="0">医院</option>
                                        <option value="1">公司</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" name="id" id="id">
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

<div class="modal fade" id="treeModal" tabindex="-1" role="dialog" aria-labelledby="treeFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="treeModalLabel">角色信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row"  style="font-size: 12px">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="input-group col-sm-10">
                                <label class="col-sm-3 control-label text-left" for="roleName">角色名称：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control input-sm" id="roleName" name="roleName" placeholder="请输入角色名称"/>
                                </div>
                                <button type="button" class="btn btn-success btn-sm" id="queryRole">
                                    <span class="glyphicon glyphicon-search"></span>
                                    查询
                                </button>
                            </div>
                        </div>
                        <input type="hidden" id="userIdQ">
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="col-sm-10">
                                <div id="tree" style="height: 300px;overflow:auto;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                            <div class="col-sm-5 text-center">
                                <button class="btn btn-primary" id="saveUserRole" type="button">保存</button>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-treeview.min.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/userinfo.js"></script>
</html>