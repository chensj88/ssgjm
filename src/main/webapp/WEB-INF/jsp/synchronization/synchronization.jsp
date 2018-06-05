<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>基础数据类型</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="container-fluid">
    <div class="widget-box">
        <div class="widget-header">
            <h4 class="smaller">
                手动同步
                <small></small>
            </h4>
        </div>

        <div class="widget-body">
            <div class="widget-main">
                <p class="muted">
                    手动同步PMIS系统数据耗时较长，请耐心等待！
                </p>

                <hr/>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    同步所有数据
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,0)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>

                                <label class="col-sm-3 control-label no-padding-right">
                                    同步用户信息
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,1)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>
                            </div>
                        </form>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    同步用户信息库
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,2)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>

                                <label class="col-sm-3 control-label no-padding-right">
                                    同步项目基本信息
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,3)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>
                            </div>
                        </form>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    同步项目成员信息
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,4)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>

                                <label class="col-sm-3 control-label no-padding-right">
                                    同步合同信息
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,5)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>
                            </div>
                        </form>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    同步产品信息库
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-info" data-rel="tooltip"
                                            onclick="synchronization(this,6)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>

                                <label class="col-sm-3 control-label no-padding-right">
                                    同步合同产品清单
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,7)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>
                            </div>
                        </form>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    同步组织机构
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,8)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>

                                <label class="col-sm-3 control-label no-padding-right">
                                    同步产品条线信息
                                </label>

                                <div class="col-sm-1">
                                    <button class="btn btn-primary btn-sm tooltip-error" data-rel="tooltip"
                                            onclick="synchronization(this,9)"
                                            data-placement="同步"
                                            title="同步">同步
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.col -->


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
<script type="text/javascript">
    function synchronization(tag, type) {
        toastr.options.progressBar = false;
        toastr.options.positionClass = 'toast-top-center';
        toastr.options.timeOut = 6000000;
        toastr.options.extendedTimeOut = 6000000;
        toastr.options.closeButton=true;
        toastr.clear();
        toastr.info("正在同步...");
        tag.disabled = true;
        $.ajax({
            type: "post",
            url: "${ctx}/admin/synchronization/synchronization.do",
            data: {type: type},
            success: function (data) {
                toastr.clear();
                tag.disabled=false;
                if (data.msg = "success") {
                    toastr.success("同步完成！");
                }
            },
            complete:function (XMLHttpRequest,textStatus) {
                if(textStatus=="error"){
                    toastr.clear();
                    toastr.error("同步异常！",function () {
                        tag.disabled = false;
                    });
                }
            },
        });

    }

    <%--久违的js，舒服--%>
    $(function () {

    });
</script>
</html>