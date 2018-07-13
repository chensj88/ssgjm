/**
 * 培训视频js
 */
let isUpload= false;

/**
 * 初始化上传框
 * @param json
 */
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
            downloadTitle: json.name, //下载文件名称
            deleteUrl:json.deleteUrl+'?id='+json.key, //删除url
            initialPreview: [ //预览内容
                json.content
            ],
            initialPreviewAsData: true, // defaults markup
            initialPreviewConfig: [ //预览配置
                {
                    type: "text",
                    previewAsData: true,
                    caption: json.name,
                    url: json.deleteUrl+'?id='+json.key,
                    key: json.key,
                    frameAttr: {
                        style: 'height:80px'
                    },
                },
            ],

        }).on("filebatchselected", function(event, files) {
            $("#uploadFile").fileinput("upload");
            isUpload = true;
        }).on('filepreupload', function() {
        }).on('fileuploaded', function(event, data) {
            isUpload = false;
            $('#remotePath').val(data.response.path);
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
            isUpload = true;
        }).on('filepreupload', function() {
        }).on('fileuploaded', function(event, data) {
            isUpload = false;
            $('#remotePath').val(data.response.path);
        });
    }
}

//上传框事件
$('#uploadFile').on('filebatchuploadsuccess', function(event, data) {
    $('#remotePath').val(data.response.path);
    isUpload = false;
});

function getShareURL() {
    if(Common.FTP_SHARE_URL === ''){
        $.ajax({
            url: Common.getRootPath() + "/admin/common/getShareURL.do",
            data: {},
            type: "post",
            dataType: 'json',
            cache:false,
            async: false,
            success: function (result) {
                if(result.status === 'success'){
                    Common.FTP_SHARE_URL = result.url;
                }
                return  Common.FTP_SHARE_URL;
            }
        });
    }else {
        return  Common.FTP_SHARE_URL;
    }
}

$(function () {
    getShareURL();
    var selectedFlie = false;
    $('#nameLabel').text('存储名称');
    $('#name').attr('placeholder','请输入存储名称');

    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            name: $.trim($('#QName').val())
        };
    }

    function SearchData() {
        $('#infoTable').bootstrapTable('refresh', {pageNumber: 1});
    }

    function validateForm() {
        $('#scriptForm').bootstrapValidator({
            live: 'enabled',
            submitButtons:'#save',
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '脚本名称/存储名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '脚本名称/存储名称不能为空'
                        },
                        threshold :  2 , //有2字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: Common.getRootPath() + '/admin/script/existName.do',//验证地址
                            message: '脚本名称/存储名称已存在',//提示消息
                            type: 'POST'//请求方式
                        },
                    }
                },
                sDesc : {
                    message: '校验内容验证失败',
                    validators: {
                        notEmpty: {
                            message: '校验内容不能为空'
                        }
                    }
                },
                appName : {
                    message: '适用系统验证失败',
                    validators: {
                        notEmpty: {
                            message: '适用系统不能为空'
                        }
                    }
                },
                dataType:{
                    message: '数据类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '数据类型不能为空'
                        },
                        threshold :  2 , //有2字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: Common.getRootPath() + '/admin/script/existScript.do',//验证地址
                            message: '该条线的校验脚本已经存在,请删除或者更新原有校验脚本信息。',//提示消息
                            type: 'POST',//请求方式
                            data: function(validator) {
                                return {
                                    appId: $('#appId').val(),
                                    dataType: $('#dataType').val()
                                };
                            }
                        }
                    }
                }
            }
        });
    }

    function queryInfoByDataId(id) {
        var data = {};
        $.ajax({
            url: Common.getRootPath() + '/admin/script/getById.do',
            data: {'id': id},
            type: "post",
            dataType: 'json',
            async: false,
            cache: false,
            success: function (result) {
               data = result.data;
            }
        });
        return data;
    }

    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/script/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   // 是否显示分页（*）
        sortable: true,                     // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     // 每页的记录行数（*）
        pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
        showPaginationSwitch: false,			//显示 数据条数选择框
        search: false,                       // 是否显示表格搜索
        strictSearch: true,
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbar: '#btntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        singleSelect: true,
        selectItemName: '单选框',
        queryParams: queryParams,  // 得到查询的参数
        columns: [{
            field: "name",
            title: "脚本名称",
            width: '40px',
            align: 'center'
        }, {
            field: "sDesc",
            title: "校验内容",
            width: '120px',
            align: 'center'
        },{
            field: "dataType",
            title: "数据类型",
            width: '20px',
            align: 'center',
            formatter:function (value) {
                if(value == '0'){
                    return '基础数据';
                }else{
                    return '易用数据';
                }
            }
        }, {
            field: "remotePath",
            title: "远程地址",
            width: '80px',
            align: 'center'
        }, {
            field: "status",
            title: "状态",
            width: '20px',
            align: 'center',
            formatter:function (value) {
                if(value == '0'){
                    return '失效';
                }else{
                    return '生效';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var  title = '';
                if(row.status =='0'){
                    title ='启用';
                }else{
                    title ='停用';
                }
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">'+title+'</a> ';
                return e + d ;
            }
        }],
    });

    $('#query').on('click',SearchData);

    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        $('#vid').val('');
        //清空验证信息
        $("#scriptForm").bootstrapValidator('destroy');
        validateForm();
        $('#dataType').val('0');
        $('#uploadFile').fileinput('destroy');
        initBootstrapFileInput();
        $('#scriptModal').modal('show');
    });

    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#customer').hide();
        //清空验证信息
        $("#scriptForm").bootstrapValidator('destroy');
        validateForm();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        $('#remotePath').val(data.remotePath);
        var remotePath = data.remotePath;
        if(remotePath && remotePath !== ''){
            let fileJson = {
                name : remotePath.substring(remotePath.lastIndexOf('/')+1),
                content : data.sDesc,
                key:data.id,
                deleteUrl: Common.getRootPath() +'/admin/script/deleteFile.do',
                url: getShareURL() + remotePath
            };
            $('#uploadFile').fileinput('destroy');
            initBootstrapFileInput(fileJson);
            isUpload = false;
        }else {
            $('#uploadFile').fileinput('destroy');
            initBootstrapFileInput();
            isUpload = false;
        }
        //取消默认选中

        //赋值
        $('#scriptForm').initForm(data);
        $('#vid').val(data.id);
        $('#scriptModal').modal('show');

    });
    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        var videoName = data.name;
        var status = data.status;
        var alterName = '';
        if(status == '0'){
            alterName = '确认要启用脚本['+videoName+']吗？';
        }else{
            alterName = '确认要停用脚本['+videoName+']吗？';
        }
        Ewin.confirm({message: alterName }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/script/modifyById.do',
                data: {'id': vid,'status':status},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                },
                complete: function () {
                }
            });
        });
    });

    /**
     * 保存流程按钮
     * 通过隐藏域判断流程是否存在，而使用不同的方法进行新增或者修改
     */
    $('#save').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#scriptForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/script/add.do';
        } else {
            url = Common.getRootPath() + '/admin/script/update.do';
        }
        if (bootstrapValidator.isValid()) {
            if(isUpload){
                toastr.info('文件正在上传请稍后！');
                return ;
            }
            $.ajax({
                url: url,
                data: $("#scriptForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#vid').val(_result.data);
                        $('#scriptModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });


    $('#dataType').on('change',function () {
        var selVal = $(this).val();
        if(selVal === '0'){
            $('#nameLabel').text('存储名称');
            $('#name').attr('placeholder','请输入存储名称');
        }else{
            $('#nameLabel').text('脚本名称');
            $('#name').attr('placeholder','请输入脚本名称');
        }
    });
});