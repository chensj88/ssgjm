<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%--<base href="<%=basePath%>">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-treeview.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/jquery.treegrid.css"/>
    <%--<base href="<%=basePath%>">--%>
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
<div class="row" id="queryScope">
    <form class="form-inline col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" >
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <label class="col-sm-6 control-label text-right" for="roleQName">角色名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="roleQName"/>
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
<table id="infoTable" class="table-align">

</table>
<!--toolbar区域  -->
<div class="btn-group" id="rolebtntoolbar">
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>
<!--模态框  -->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="roleFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:450px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">角色信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="roleForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="roleName">角色名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="roleName" name="roleName"
                                           placeholder="请输入角色名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="roleDesc">角色描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="roleDesc" name="roleDesc"
                                           placeholder="请输入角色信息描述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="isLock">是否锁定</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isLock" id="isLock">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="isDel">是否删除</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isDel" id="isDel">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="id" name="id">
                            <input type="reset" style="display:none;"/>
                            <div class="text-center">
                                <button class="btn btn-primary" id="saveRole" type="submit">保存</button>
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
        <div class="modal-content" style="width:500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="treeModalLabel">模块信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row"  style="font-size: 12px">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="input-group col-sm-10">
                                <label class="col-sm-3 control-label text-left" for="modName">模块名称：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control input-sm" id="modName" name="modName" placeholder="请输入角色名称"/>
                                </div>
                                <button type="button" class="btn btn-success btn-sm" id="queryMod">
                                    <span class="glyphicon glyphicon-search"></span>
                                    查询
                                </button>
                            </div>
                        </div>
                        <input type="hidden" id="roleIdQ">
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
                            <button class="btn btn-primary" id="saveRoleModule" type="button">保存</button>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="gridModal" tabindex="-1" role="dialog" aria-labelledby="gridFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="gridModalLabel">按钮信息</h4>
            </div>
            <div class="modal-body" >
                <div class="container">
                    <input type="hidden" id="roleQId">
                    <div class="row" style="height: 200px;overflow:auto;">
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <div class="col-sm-10">
                               <table id="gridTable" class="table-align" >
                               </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5 text-center">
                            <button class="btn btn-primary" id="saveP" type="button">保存</button>
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
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/roleinfo.js"></script>
</html>