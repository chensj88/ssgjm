<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/4/26
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <meta name="author" content="卫宁实施工具">
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-treeview.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <div class="col-sm-10">
                <div id="tree" style="height: 300px;overflow:auto;"></div>
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
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script>
    $(function () {
        function initTreeView() {
            $.ajax({
                type: "post",
                url: Common.getRootPath() + "/test/menu.do",
                dataType: "json",
                cache : false,
                async: false,
                success: function (result) {
                    $('#tree').treeview({
                        data: result.data,         // 数据源
                        showCheckbox: true,   //是否显示复选框
                        highlightSelected: true,    //是否高亮选中
                        icon:'',                                  //列表树节点上的图标，通常是节点左边的图标。
                        uncheckedIcon:"",                         //设置图标为未选择状态的checkbox图标。
                        nodeIcon:'glyphicon glyphicon-unchecked', //设置所有列表树节点上的默认图标。
                        selectedIcon:"glyphicon glyphicon-check", //设置所有被选择的节点上的默认图标。
                        color:"#000000",
                        backColor:"#FFFFFF",
                        emptyIcon: '',    //设置列表树中没有子节点的节点的图标。
                        multiSelect: true     //多选
                    });
                },
                error: function () {
                    alert("树形结构加载失败！")
                }
            });
        }
        initTreeView();
    });
</script>
</html>
