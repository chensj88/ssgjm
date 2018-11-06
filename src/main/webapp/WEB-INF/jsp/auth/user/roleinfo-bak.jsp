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
    <link rel="stylesheet" href="<%=basePath%>resources/assets/ztree/css/zTreeStyle/zTreeStyle.css">
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
<body class="container-fluid">
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 tree-container">
        <p>病历导航树</p>
            <div id="menuTree" class="ztree">
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
<script type="text/javascript" src="<%=basePath%>resources/assets/ztree/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/ztree/js/jquery.ztree.exedit.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/ztree/js/jquery.ztree.exhide.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/user/roleinfo-bak.js"></script>
<script type="text/javascript">
    /**
     * 数据转换
     * @param treeId
     * @param parentNode
     * @param childNodes
     * @returns {*}
     */
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        var result = childNodes.data;
        for (var i=0, l=result.length; i<l; i++) {
            result[i].nodeName = result[i].nodeName.replace(/\.n/g, '.');
        }
        return result;
    }

    $(function () {
        var menuTree = $('#menuTree');
        var menuSetting = {
            async: {
                enable: true,
                url:Common.getRootPath() + "/admin/ztree/moduleParent.do",
                autoParam:["nodeId"],
                dataType:'json',
                dataFilter: filter
            },
            view: {
                checkable:true,
                dblClickExpand: false,
                showLine: true,
                selectedMulti: true,
                showIcon:false
            },
            expandSpeed: "",
            data: {
                keep:{
                    parent:true,
                },
                key:{
                    name:'nodeName',
                    isParent: "parentFlag",
                    isHidden: "hiddenFlag",
                    checked: "checked"
                },
                simpleData: {
                    enable: true,
                    idKey: "nodeId",
                    pIdKey: "nodePid",
                    rootPId: ""
                }
            },
            check: {
                enable: true,
                chkboxType: { "Y" : "ps", "N" : "ps" }
            },
            callback: {
                onClick: menuTreeOnOnClick,
                onExpand:menuTreeOnOnClick
            }
        };

        menuTree = $.fn.zTree.init($("#menuTree"), menuSetting);


        /**
         * templateTree延迟加载
         * @param event
         * @param treeId
         * @param treeNode
         */
        function menuTreeOnOnClick(event, treeId, treeNode) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var node = treeObj.getNodeByTId(treeNode.tId);
            treeObj.expandNode(node, true, true, true);
            //没有子节点才去查询
            if (node.children.length < 1 || node.children == null || node.children == "undefined") {
                var queryJson = {
                    'parId':treeNode.nodeId
                };
                $.ajax({
                    type: "POST",
                    async: false,
                    url: Common.getRootPath() + "/admin/ztree/moduleChild.do",
                    data:queryJson,
                    cache:false,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.data != null && data.data != "") {
                            //添加新节点
                            newNode = treeObj.addNodes(node, data.data);
                            // $.each(data.data,function(index,value){
                            //     treeObj.addNodes(node, value)
                            // });
                        }
                    },
                    error: function ( response) {
                        result = true;
                        toastr.error(response.responseText);
                    }
                });
            }
        }

    });

</script>
</html>