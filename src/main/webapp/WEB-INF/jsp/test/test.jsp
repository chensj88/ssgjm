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
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/upload/css/style.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/js/fileapi/css/jquery.Jcrop.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div id="file-upload" class="file-api-bootstrap">
                <div class="row" >
                    <div id="uploadFile" style="display: none">
                        <div class="row" >
                            <span class="js-name b-upload__name" id="uploadFileName"></span>
                        </div>
                        <div class="row" >
                            <button class="btn btn-info btn-small" id="downLoadFile">下载</button>
                            <button class="btn btn-danger btn-small" id="deleteFile">删除</button>
                        </div>
                    </div>
                    <div id="fileInfo">

                    </div>
                    <div class="col-sm-5" id="jsInfo">
                         <span class="js-info">
                             <span class="js-name b-upload__name" id="fileName" style="width: 150px;overflow:hidden;text-overflow:ellipsis;"></span>
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
</div>
</body>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/FileAPI/FileAPI.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/FileAPI/FileAPI.exif.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/assets/js/fileapi/jquery.fileapi.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>resources/js/test/test.js"></script>--%>
<script type="text/javascript">
    jQuery(function ($){

        initFileApiUpload('file-upload','/admin/upload/test.do');
        function initFileApiUpload(ele,url) {
            var $ele = $('#'+ele);
            $ele.fileapi({
                clearOnComplete: true,
                url:Common.getRootPath() + url,
                autoUpload: true,
                multiple:false,
                paramName:'file',
                maxSize: FileAPI.MB*10, // max file size
                maxFiles:1,
                elements: {
                    ctrl: {  reset: '#reset' },
                    name: '#fileName',
                    size: '#fileSize',
                    empty: { hide: '#jsInfo' }
                },
                onSelect:function (evt, data) {
                    $('#fileInfo').html('');
                    var fileInfo = data.all;
                    var fileName = data.all[0].name;
                    var suffix = fileName.substring(fileName.lastIndexOf('.'));
                    var isAllow = /(\.|\/)(xls?x|doc?x|pdf)$/i.test(suffix);
                    if(!isAllow){
                        data.all = [];
                        data.files = [];
                        $('#fileInfo').html('当前文件【'+fileName+'】文件格式不支持，<br>只支持doc,docx,xls,xlsx,pdf').css('color','red');
                        return ;
                    }
                    $('#reset').show();
                    $('#fileUpload').hide();
                },
                onFileComplete: function (evt, uiEvt){
                    var file = uiEvt.file;
                    var json = uiEvt.result;
                    var index = Common.getRootPath().lastIndexOf('/');
                    console.log(json);
                    var url = json.url + json.path;
                    $('#uploadFileName').text(file.name);
                    $('#downLoadFile').attr('path',url);
                    $('#deleteFile').attr('path',url);
                    hideUploadDiv();
                }
            });
            showUploadDiv();
        }

        $('#downLoadFile').on('click',function () {
            window.open($(this).attr('path'));
        });
        
        $('#deleteFile').on('click',function () {
            $('#uploadFileName').text('');
            $('#downLoad').attr('path','');
            $('#delete').attr('path','');
            showUploadDiv();
        });

        $('#reset').on('click',function () {
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
    });
//
</script>
</html>
