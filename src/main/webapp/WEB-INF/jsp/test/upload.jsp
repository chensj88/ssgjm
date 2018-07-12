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
    <%--<div class="row">
        <form class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" role="form" id="fileForm">
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
    </div>--%>
    <div class="row">
        <div class="btn-group" id="btntoolbar" >
            <button id="add" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
            <button id="modify" class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>修改</button>
        </div>
    </div>
</div>
<div class="modal fade" id="textModal" tabindex="-1" role="dialog" aria-labelledby="scriptFormModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">测试信息</h4>
            </div>
            <div class="modal-body">
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
            </div>
        </div>
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
    var isUpload = false;
    $(function (){
        $('#save').on('click',function(){
            var userid = $("#userid").val();
            var password = $("#password").val();
            $('#save').on('click',function(){
                console.log(isUpload);
                if(isUpload){
                    $("#fileInput").fileinput("upload");
                }
                var userid = $("#userid").val();
                var password = $("#password").val();

            });
        });

        $('#add').on('click',function () {
            $('#fileInput').fileinput('destroy');
            initFileInput();
            isUpload = false;
            $('#textModal').modal('show');
        });


        $('#modify').on('click',function () {
            $('#fileInput').fileinput('destroy');
            let json = {
                filename:'易用性数据维护验证脚本.sql',
                content:"---------门诊部分：\n" +
                "SELECT '已经维护的个人协定方信息统计:' '医生','' '协定方数量（套）'\n" +
                "union all\n" +
                "select c.NAME '医生',COUNT(a.XH) '协定方数量'\n" +
                "from OUTP_CZYZK a,SYS_ZGDMK c\n" +
                "where a.CZYH=c.ID and c.ZGLB IN(0,1)\n" +
                "group by c.NAME;\n" +
                "\n" +
                "select '' '工号','未维护个人协定方的医生：' '姓名', '' '科室代码', '' '科室名称', '' '职称'\n" +
                "union all\n" +
                "select ID '工号',NAME '姓名',KSDM '科室代码',KS_MC '科室名称',ZCMC '职称' from SYS_ZGDMK \n" +
                "where ZGLB IN(0,1) and ID not in(select distinct CZYH from OUTP_CZYZK where MBBZ=0)\n" +
                "order by 科室名称;\n" +
                "\n" +
                "SELECT '已经维护的科室协定方信息统计:' '科室','' '协定方数量（套）'\n" +
                "union all\n" +
                "select c.NAME '科室',COUNT(a.XH) '协定方数量'\n" +
                "from OUTP_CZYZK a,SYS_KSDMK c\n" +
                "where a.KSDM=c.ID and c.KSLB=0\n" +
                "group by c.NAME;\n" +
                "\n" +
                "select '' '科室代码','未维护科室协定方的科室：' '科室'\n" +
                "union all\n" +
                "select ID '科室代码',NAME '科室名称' from SYS_KSDMK \n" +
                "where KSLB=0 and ID not in(select distinct KSDM from OUTP_CZYZK where MBBZ=1);\n" +
                "\n" +
                "\n" +
                "---------住院部分：\n" +
                "---查询有效的住院成套医嘱数量\n" +
                "select a.KSDM,b.NAME,COUNT(*) sl\n" +
                "from CPOE_CZYZK a,SYS_KSDMK b\n" +
                "where a.KSDM=b.ID and a.JLZT=1 \n" +
                "group by a.KSDM,b.NAME;\n" +
                "\n" +
                "\n" +
                "--查询住院常用诊断数量\n" +
                "select b.KS_MC,a.ZGDM,b.NAME,COUNT(*) sl\n" +
                "from EMR_ZGCYZDK a,SYS_ZGDMK b \n" +
                "where a.ZGDM=b.ID\n" +
                "group by b.KS_MC,a.ZGDM,b.NAME;\n" +
                "\n" +
                "\n" +
                "--查询住院段落维护的数量\n" +
                "select b.KS_MC,a.YSDM,b.NAME,COUNT(*) sl\n" +
                "from EMR_GRMBK a,SYS_ZGDMK b \n" +
                "where a.YSDM=b.ID and SSXT=1 and YXJL=1 and YSDM not in ('00','zxs','666666')\n" +
                "group by b.KS_MC,a.YSDM,b.NAME;\n" +
                "\n" +
                "--查询住院段落组维护的数量\n" +
                "select b.KS_MC,a.YSDM,b.NAME,COUNT(*) sl\n" +
                "from EMR_GRCTMBK a,SYS_ZGDMK b \n" +
                "where a.YSDM=b.ID and MXFLDM like 'B-%' and YXJL='1' and YSDM not in ('00','zxs','666666')\n" +
                "group by b.KS_MC,a.YSDM,b.NAME;",
                url:'http://172.16.0.200:8081/shareFolder/upload/web/template/1531294688303/易用性数据维护验证脚本.sql',
            };
            initFileInput(json);
            isUpload = false;
            $('#textModal').modal('show');
        });

    });
//
    //上传框的初始化
    function initFileInput(json){
        if(json){
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
                autoReplace:true, //自动替换
                uploadAsync: true, //默认异步上传
                browseClass:"btn btn-primary", //按钮样式
                dropZoneEnabled: false,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount:1, //表示允许同时上传的最大文件个数
                enctype:'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                initialPreview: [
                    json.content
                ],
                initialPreviewAsData: true, // defaults markup
                initialPreviewConfig: [
                    {type: "text",previewAsData: true,caption: json.name, deleteUrl:json.url,url: json.url, key: 20},
                ],

            }).on("filebatchselected", function(event, files) {
                isUpload = true;
            }).on('filepreupload', function() {
                $('#kv-success-box').html('');
            }).on('fileuploaded', function(event, data) {
                $('#kv-success-box').append(data.response.path+""+data.response.path);
                $('#kv-success-modal').modal('show');
            });
        }else{
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
                autoReplace:true, //自动替换
                uploadAsync: true, //默认异步上传
                browseClass:"btn btn-primary", //按钮样式
                dropZoneEnabled: false,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount:1, //表示允许同时上传的最大文件个数
                enctype:'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            }).on("filebatchselected", function(event, files) {
                isUpload = true;
            }).on('filepreupload', function() {
                $('#kv-success-box').html('');
            }).on('fileuploaded', function(event, data) {
                $('#kv-success-box').append(data.response.url+""+data.response.path);
                console.log(data.response.url+""+data.response.path);
                $('#kv-success-modal').modal('show');
            });
        }

    }


</script>
</html>
