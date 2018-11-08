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
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/fileinput.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/js/fileapi/css/jquery.Jcrop.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/toastr.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/summernote/summernote.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <%--<base href="<%=basePath%>">--%>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div id="file-upload" class="file-api-bootstrap">
            <div class="row">
                <div id="uploadFile" style="display: none">
                    <div class="row">
                        <span class="js-name b-upload__name" id="uploadFileName"></span>
                    </div>
                    <div class="row">
                        <button class="btn btn-info btn-small" id="downLoadFile">下载</button>
                        <button class="btn btn-danger btn-small" id="deleteFile">删除</button>
                    </div>
                </div>
                <div id="fileInfo">

                </div>
                <div class="col-sm-5" id="jsInfo">
                         <span class="js-info">
                             <span class="js-name b-upload__name" id="fileName"
                                   style="width: 150px;overflow:hidden;text-overflow:ellipsis;"></span>
                             <span class="b-upload__size">(<span id="fileSize" class="js-size"></span>) 正在上传</span>
                        </span>
                </div>
            </div>
            <br/>
            <div class="row" id="fileUploadDiv">
                <div class="col-sm-3">
                    <div class="btn btn-success btn-small js-fileapi-wrapper" id="fileUpload">
                        <span>选择文件</span>
                        <input type="file" name="file">
                    </div>
                    <button class="js-reset btn-small btn btn-warning" id="reset" type="reset">重置</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="scriptForm">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="userid">工号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="userid" name="userid"
                           placeholder="请输入工号">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="password">密码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="请输入密码">
                </div>
            </div>
            <div class="col-sm-8 text-center">
                <button class="btn btn-primary save-flag" id="save" type="button">保存</button>
                <button class="btn btn-danger cancel-flag" data-dismiss="modal">取消</button>
            </div>
        </form>
    </div>
    <div class="row">
        <div id="summernote"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/FileAPI/FileAPI.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/FileAPI/FileAPI.exif.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/jquery.fileapi.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/toastr.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/summernote/summernote.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/summernote/lang/summernote-zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/auth/auth.js"></script>
<script type="text/javascript">
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
    jQuery(function ($) {

        checkUserButtonAuth();

        initFileApiUpload('file-upload', '/admin/upload/test.do');

        function initFileApiUpload(ele, url) {
            var $ele = $('#' + ele);
            $ele.fileapi({
                clearOnComplete: true,
                url: Common.getRootPath() + url,
                autoUpload: true,
                multiple: false,
                paramName: 'file',
                maxSize: FileAPI.MB * 10, // max file size
                maxFiles: 1,
                elements: {
                    ctrl: {reset: '#reset'},
                    name: '#fileName',
                    size: '#fileSize',
                    empty: {hide: '#jsInfo'}
                },
                onSelect: function (evt, data) {
                    $('#fileInfo').html('');
                    var fileInfo = data.all;
                    var fileName = data.all[0].name;
                    var suffix = fileName.substring(fileName.lastIndexOf('.'));
                    var isAllow = /(\.|\/)(xls?x|doc?x|pdf)$/i.test(suffix);
                    if (!isAllow) {
                        data.all = [];
                        data.files = [];
                        $('#fileInfo').html('当前文件【' + fileName + '】文件格式不支持，<br>只支持doc,docx,xls,xlsx,pdf').css('color', 'red');
                        return;
                    }
                    $('#reset').show();
                    $('#fileUpload').hide();
                },
                onFileComplete: function (evt, uiEvt) {
                    var file = uiEvt.file;
                    var json = uiEvt.result;
                    var index = Common.getRootPath().lastIndexOf('/');
                    console.log(json);
                    var url = json.url + json.path;
                    $('#uploadFileName').text(file.name);
                    $('#downLoadFile').attr('path', url);
                    $('#deleteFile').attr('path', url);
                    hideUploadDiv();
                }
            });
            showUploadDiv();
        }

        $('#downLoadFile').on('click', function () {
            window.open($(this).attr('path'));
        });

        $('#deleteFile').on('click', function () {
            $('#uploadFileName').text('');
            $('#downLoad').attr('path', '');
            $('#delete').attr('path', '');
            showUploadDiv();
        });

        $('#reset').on('click', function () {
            $('#fileUpload').show();
            $('#reset').hide();
        });

        $('#reset').hide();

        function hideUploadDiv() {
            $('#uploadFile').show();
            $('#fileUploadDiv').hide();
        }

        function showUploadDiv() {
            $('#fileUploadDiv').show();
            $('#uploadFile').hide();
            $('#reset').hide();
            $('#fileUpload').show();
        }

        $('#save').on('click', function () {
            var userid = $("#userid").val();
            var password = $("#password").val();
            $.ajax({
                type: "POST",
                url: Common.getRootPath() + "/test/userLogin.do",
                data: {"userid": userid, "password": password},
                dataType: "json",
                cache: false,
                error: function (request) {
                    toastr.error(request.responseText);
                },
                success: function (data) {
                    toastr.success(data.data.records[0].values[1] + ":" + data.data.records[0].values[2]);
                }
            });
        });
    });
    //
    $('#summernote').summernote({
        placeholder: '请输入存储SQL', //提示名称
        tabsize: 2,
        height: 150,
        minHeight: 50,             // 编辑器最小的高度
        maxHeight: 150,             // 编辑器最大的高度
        focus: true,               // 初始化完成后立即获取焦点
        lang: 'zh-CN',             //中文支持
        //自定义参数
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']], //样式
            ['font', ['strikethrough', 'superscript', 'subscript']], //标记 上标、下标、删除线
            ['fontsize', ['fontsize']], //字体大小
            ['color', ['color']], //字体颜色
            ['para', ['ul', 'ol', 'paragraph']], //列表
            ['height', ['height']], //设置行高
        ]
    });

</script>
</html>
