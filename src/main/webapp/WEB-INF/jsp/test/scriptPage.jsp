<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/2/28
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/bootstrapValidator/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/bootstrap/file/css/fileinput.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>resources/assets/css/common.css"/>
    <%--<base href="<%=basePath%>">--%>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
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
        .table-align tr td:nth-child(2) {
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
            <label class="col-sm-6 control-label text-right" for="QName">脚本名称：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-sm" id="QName"/>
            </div>
        </div>
        <div class="input-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <button type="button" class="btn btn-success btn-sm" id="query">
                <span class="glyphicon glyphicon-search"></span>
                查询
            </button>
        </div>
    </form>
</div>
<!--表格区域  -->
<table id="infoTable" class="table-align"></table>
<!--toolbar区域  -->
<div class="btn-group" id="btntoolbar" >
    <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
</div>

<!--模态框  -->
<div class="modal fade" id="scriptModal" tabindex="-1" role="dialog" aria-labelledby="scriptFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">脚本信息</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="scriptForm">
                            <div class="form-group" >
                                <label class="col-sm-3 control-label" for="dataType">数据类型</label>
                                <div class="col-sm-6">
                                    <select class="form-control" id="dataType" name="dataType">
                                        <option value="0">基础数据</option>
                                        <option value="1">易用数据</option>
                                    </select>
                                </div>
                            </div>
                              <div class="form-group" id="videoNameDiv">
                                  <label class="col-sm-3 control-label" for="name" id="nameLabel">脚本名称</label>
                                  <div class="col-sm-6">
                                      <input type="text" class="form-control" id="name" name="name"
                                             placeholder="请输入脚本名称">
                                  </div>
                              </div>
                            <input type="hidden" id="appId" name="appId">


                            <input type="hidden" name="id" id="id">
                            <input type="hidden" name="vid" id="vid">
                            <input type="hidden" name="remotePath" id="remotePath">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="sDesc">校验内容</label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="6" id="sDesc" name="sDesc" placeholder="请输入校验内容"></textarea>
                                </div>
                            </div>
                              <div class="form-group" id="isModifyDiv">
                                  <label class="col-sm-3 control-label" for="isModify">文件替换</label>
                                  <div class="col-sm-6">
                                      <select class="form-control" id="isModify" name="isModify">
                                          <option value="0">否</option>
                                          <option value="1">是</option>
                                      </select>
                                  </div>
                              </div>
                            <div class="form-group" id="uploadFileDiv">
                                  <label class="col-sm-3 control-label" for="uploadFile">上传脚本</label>
                                  <div class="col-sm-6">
                                      <input name="uploadFile" type="file" class="file" id="uploadFile">
                                  </div>
                            </div>

                            <input type="reset" style="display:none;"/>
                            <div class="col-sm-8 text-center">
                                <button class="btn btn-primary" id="save" type="button">保存</button>
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
<!--[if !IE]> -->
<script src="<%=basePath%>resources/bootstrap/js/jquery-2.2.4.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="<%=basePath%>resources/bootstrap/js/jquery-1.12.4.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/bootstrapValidator/js/zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/file/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/bootstrap/file/js/zh.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/test/script.js"></script>
<script>
    initBootstrapFileInput();
    function initBootstrapFileInput(json){
        if(json){
            $('#uploadFile').fileinput({
                theme: 'zh',//设置语言
                language: 'zh',//设置语言
                uploadUrl: Common.getRootPath() + Common.url.script.uploadURL,//上传的地址
                allowedFileExtensions: ['sql','txt'], //接收的文件后缀
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
                autoReplace:true, //自动替换
                uploadAsync: true, //默认异步上传
                browseClass:"btn btn-primary", //按钮样式
                dropZoneEnabled: false,//是否显示拖拽区域
                maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
                maxFileCount:1, //表示允许同时上传的最大文件个数
                enctype:'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                fileActionSettings:{showUpload:false,showDownload:true},
                showDownload: true, //显示下载按钮
                downloadIcon: '<i class="glyphicon glyphicon-download"></i>', //按钮ICON
                downloadClass: 'btn btn-kv btn-default btn-outline-secondary',//按钮样式
                initialPreviewDownloadUrl:json.url, //文件下载路径
                downloadTitle: json.name,
                initialPreview: [
                    json.content
                ],
                initialPreviewAsData: true, // defaults markup
                initialPreviewConfig: [
                    {type: "text",previewAsData: true,caption: json.name,url: json.url, key: json.key,frameAttr: {style: 'height:80px'},},
                ],
                deleteUrl:json.deleteUrl+'?id='+json.key
            }).on("filebatchselected", function(event, files) {
                $("#uploadFile").fileinput("upload");
            });
        }else{
            $('#uploadFile').fileinput({
                theme: 'zh',//设置语言
                language: 'zh',//设置语言
                uploadUrl: Common.getRootPath() + Common.url.script.uploadURL,//上传的地址
                allowedFileExtensions: ['sql','txt'], //接收的文件后缀
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
                autoReplace:true, //自动替换
                uploadAsync: true, //默认异步上传
                browseClass:"btn btn-primary", //按钮样式
                dropZoneEnabled: false,//是否显示拖拽区域
                maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
                maxFileCount:1, //表示允许同时上传的最大文件个数
                enctype:'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                fileActionSettings:{showUpload:false,showDownload:false},
            }).on("filebatchselected", function(event, files) {
                $("#uploadFile").fileinput("upload");
            });
        }
    }


    $('#uploadFile').on('filebatchuploadsuccess', function(event, data) {
        $('#remotePath').val(data.response.path);
        isUpload = true;
    });
</script>
</html>
