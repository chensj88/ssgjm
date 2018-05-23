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
    <link rel="stylesheet" href="<%=basePath%>resources/uploader/css/jquery.dm-uploader.min.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
    <style>

        .demo{min-width:360px;margin:30px auto;padding:10px 20px}
        .demo h3{line-height:40px; font-weight: bold;}
        .file-item{float: left; position: relative; width: 110px;height: 110px; margin: 0 20px 20px 0; padding: 4px;}
        .file-item .info{overflow: hidden;}
        .uploader-list{width: 100%; overflow: hidden;}
    </style>
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
    <div class="row">
        <div id="drag-and-drop-zone" class="dm-uploader p-5">
             <div class="col-sm-3">
                <ul class="list-unstyled p-2 d-flex flex-column col" id="files">
                    <li class="text-muted text-center empty">请先选择文件</li>
                </ul>
             </div>
            <div class="col-sm-2" style="width:120px;">
                <div class="btn btn-primary btn-block mb-5" >
                    <span>选择文件</span>
                    <input type="file" title='Click to add Files' />
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
<script type="text/javascript" src="<%=basePath%>resources/uploader/js/jquery.dm-uploader.min.js"></script>
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
//        initTreeView();

        function initUploadComponent() {
            var template = '<li class="text-muted text-center empty">请先选择文件</li>';
            $('#files').find('li.empty').fadeOut();
            $('#files').prepend(template);
        }

        $('#drag-and-drop-zone').dmUploader({
            url:Common.getRootPath() + '/admin/upload/test.do',
            onNewFile: function(id, file){
                uploadFile(id, file);
            },
            onUploadSuccess: function(id, data){
                changeFileStatus(id, 'success', '上传成功');
            }
        });
    });

    function uploadFile(id, file) {
        var template ='<li class="media">\n' +
            '        <div class="media-body mb-1">\n' +
            '        <p class="mb-2">\n' +
            '           <strong>'+file.name+'</strong> - 当前状态: <span id="uploaderFile'+id+'" class="text-muted">正在上传</span>\n' +
            '         </p> </div></li>';
        $('#files').find('li.empty').fadeOut();
        $('#files').prepend(template);
    }
    function changeFileStatus(id, status, message) {
        console.log('#uploaderFile' + id);
        $('#uploaderFile' + id).text(message).prop('class', 'text-' + status);
    }

</script>
<script type="text/html" id="files-template">
    <li class="media">
        <div class="media-body mb-1">
            <p class="mb-2">
                <strong>%%filename%%</strong> - Status: <span class="text-muted">Waiting</span>
            </p>
            <div class="progress mb-2">
                <div class="progress-bar progress-bar-striped progress-bar-animated bg-primary"
                     role="progressbar"
                     style="width: 0%"
                     aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                </div>
            </div>
            <hr class="mt-1 mb-1" />
        </div>
    </li>
</script>
</html>
