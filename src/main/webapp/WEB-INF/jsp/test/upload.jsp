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
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/file/css/fileinput.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <%--<base href="<%=basePath%>">--%>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="container">
    <div class="row">
        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="scriptForm">
            <div class="form-group" >
                <label class="col-sm-3 control-label" for="userid">工号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="userid" name="userid"
                           placeholder="请输入工号">
                </div>
            </div>
            <div class="form-group" >
                <label class="col-sm-3 control-label" for="password">密码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="请输入密码">
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-3 control-label" for="fileInput">文件上传</label>
                <div class="col-sm-6">
                    <input name="file" type="file" class="file" id="fileInput">
                </div>
            </div>
            <div class="col-sm-8 text-center">
                <button class="btn btn-primary" id="save" type="button">保存</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/file/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/file/js/zh.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript">
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
    jQuery(function ($){
        $('#save').on('click',function(){
            var userid = $("#userid").val();
            var password = $("#password").val();
            $.ajax({
                type: "POST",
                url:Common.getRootPath()+"/test/userLogin.do",
                data:{"userid":userid,"password":password},
                dataType:"json",
                cache : false,
                error: function(request) {
                    toastr.error(request.responseText);
                },
                success: function(data) {
                    toastr.success(data.data.records[0].values[1]+":"+data.data.records[0].values[2]);
                }
            });
        });
    });
//

    //上传框初始化
    $('#fileInput').fileinput({
        theme: 'zh',//设置语言
        language: 'zh',//设置语言
        uploadUrl: Common.getRootPath() +'/admin/upload/test.do',//上传的地址
        allowedFileExtensions: ['jpg', 'png','sql','txt', 'gif'], //接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'}, 额外参数
        hideThumbnailContent:true, //不显示内容信息，只显示文件名称和大小
        showCaption:true,//是否显示标题
        showPreview :true, //是否显示预览
        showRemove :false, //显示移除按钮
        showUpload:false, //是否显示上传按钮
        showCancel:true, //ajax上传显示取消按钮 仅在ajax时候
        showClose:false, //文件预览情况下显示关闭按钮
        showUploadedThumbs:true, //显示上传文件
        showBrowse:true, //显示浏览按钮
        browseOnZoneClick:false, //点击浏览按钮或者点击上传区域
        autoReplace:false,
        uploadAsync: true, //默认异步上传






        showUploadedThumbs:false,
        browseClass:"btn btn-primary", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });

    $("#fileInput").on("filebatchselected", function(event, files) {
        console.log(event);
        console.log(files);
    });
</script>
</html>
