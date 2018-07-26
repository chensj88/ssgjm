/**
 * 流程信息
 * chensj
 * 2018-01-25
 */
function initFlowConfig(data){
    $('#flowPCode').val(data.flowParentCode);
    $('#flowPName').val(data.flowParentName);
    $('#configCode').val(data.flowCode);
    $('#configName').val(data.flowName);
    $('#configDesc').val(data.flowDesc);

}
/**
 * 表单校验规则
 */
function validateForm() {
    $('#flowForm').bootstrapValidator({
        live: 'enabled',
        //submitButtons:'#saveFlow',
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            flowName: {
                message: '流程名称验证失败',
                validators: {
                    notEmpty: {
                        message: '流程名称不能为空'
                    },
                    // threshold: 6, //有1字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    // remote: {
                    //     url: Common.getRootPath() + Common.url.flow.existName,//验证地址
                    //     message: '流程名称已存在',//提示消息
                    //     delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                    //     type: 'POST'/*,//请求方式
                    //         data: function (validator) { //自定义提交数据，默认值提交当前input value
                    //             return {
                    //                 flowName: $('#flowName').val(),
                    //                 id: $('#vid').val()
                    //             };
                    //         }*/
                    // }
                }
            },
            flowDesc : {
                message: '流程描述验证失败',
                validators: {
                    notEmpty: {
                        message: '流程描述不能为空'
                    }
                }
            },
            flowPCode : {
                message: '流程编号验证失败',
                validators: {
                    notEmpty: {
                        message: '流程编号不能为空'
                    }
                }
            },
            configName : {
                message: '配置名称验证失败',
                validators: {
                    notEmpty: {
                        message: '配置名称不能为空'
                    }
                }
            },
            configDesc : {
                message: '配置说明验证失败',
                validators: {
                    notEmpty: {
                        message: '配置说明不能为空'
                    }
                }
            },
            contentDesc : {
                message: '明细说明验证失败',
                validators: {
                    notEmpty: {
                        message: '明细说明不能为空'
                    }
                }
            },
            configSQL : {
                message: '配置SQL验证失败',
                validators: {
                    notEmpty: {
                        message: '配置SQL不能为空'
                    }
                }
            },
            procName : {
                message: '存储名称验证失败',
                validators: {
                    notEmpty: {
                        message: '存储名称不能为空'
                    }
                }
            }
        }
    });
}

/**
 * 新增子节点
 * @param id
 * @param flowType
 * @param flowName
 * @param flowCode
 */
function editFlowInfo(id,flowType,flowName,flowCode){
    $("input[type=reset]").trigger("click");
    $('#id').val('');
    $('#flowPid').val(id);
    $('#vid').val('');
    $('#remotePath').val('');
    //清空验证信息
    $('#flowForm').bootstrapValidator("destroy");
    validateForm();
    $('#flowParent').show();
    $('#flowCodeDiv').show();
    $('#uploadFileDiv').show();
    $('#isModifyDiv').hide();
    $('#flowInfo').show();
    $('#configDiv').hide();
    $('#flowType').val(parseInt(flowType)+1+"");
    $('#flowType').attr('disabled',true);
    $('#flowParentName').val(flowName);
    $('#flowParentCode').attr('readonly',true);
    $('#flowParentCode').val(flowCode);
    changeDivShow(parseInt(flowType)+1+"")
    $('#flowModal').modal('show');
}

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
            uploadUrl: Common.getRootPath() +"/admin/upload/flow.do",//上传的地址
            allowedFileExtensions: ['xls','xlsx','docx','doc','pdf'], //接收的文件后缀
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
            fileActionSettings:{showUpload:false,showDownload:true,showZoom:false},
            showDownload: true, //显示下载按钮
            downloadIcon: '<i class="glyphicon glyphicon-download"></i>', //按钮ICON
            downloadClass: 'btn btn-kv btn-default btn-outline-secondary',//按钮样式
            initialPreviewDownloadUrl:json.url, //文件下载路径
            downloadTitle: json.name, //下载文件名称
            deleteUrl:json.deleteUrl+'?id='+json.key, //删除url
            initialPreview: [ //预览内容
                json.url
            ],
            initialPreviewAsData: true, // defaults markup
            initialPreviewConfig: [ //预览配置
                {
                    type: "office",
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
            uploadUrl: Common.getRootPath() +"/admin/upload/flow.do",//上传的地址
            allowedFileExtensions: ['xls','xlsx','docx','doc','pdf'], //接收的文件后缀
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
            fileActionSettings:{showUpload:false,showDownload:false,showZoom:false},
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

/**
 * 获取FTP共享路径
 * @returns {*}
 */
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


function changeDivShow(selEle,type){
    if(type){
       // console.log(type);
        $('#flowCodeDiv').show();
        if(selEle == '1'){
            $('#flowParent').show(); //上级流程
           // $('#flowCodeDiv').hide();//流程编码
            $('#uploadFileDiv').show(); //上传文件
            $('#isMustDiv').show(); //前端初始化
            $('#isMust').val('1');
            $('#procDiv').hide();
            $('#dbTypeDiv').hide();
            $('#flowNameLabel').text('流程名称');
            $('#flowName').attr('placeholder','请输入流程名称');
            $('#flowDescLabel').text('流程描述');
            $('#flowDesc').attr('placeholder','请输入流程描述');
            $('#contentDescDiv').hide();
        }else if(selEle == '2'){
            $('#flowParent').show();
            $('#isMustDiv').hide();
            //$('#flowCodeDiv').hide();
            $('#uploadFileDiv').hide();
            $('#dbTypeDiv').hide();
            $('#flowNameLabel').text('方案名称');
            $('#flowName').attr('placeholder','请输入配置方案名称');
            $('#flowDescLabel').text('方案简介');
            $('#flowDesc').attr('placeholder','请输入方案简介');
            $('#procDiv').hide();
            $('#contentDescDiv').show();
        }else if(selEle == '3'){
            $('#flowParent').show();
            $('#isMustDiv').hide();
            $('#uploadFileDiv').hide();
            $('#dbTypeDiv').show();
            $('#flowNameLabel').text('SQL名称');
            $('#flowName').attr('placeholder','请输入SQL名称');
            $('#flowDescLabel').text('存储SQL');
            $('#flowDesc').attr('placeholder','请输入存储SQL');
            $('#procDiv').show();
            $('#contentDescDiv').hide();
        }else{
            $('#dbTypeDiv').hide();
            $('#flowParent').hide();
            $('#uploadFileDiv').hide();
            $('#isMustDiv').hide();
            $('#procDiv').hide();
            $('#flowNameLabel').text('流程名称');
            $('#flowName').attr('placeholder','请输入流程名称');
            $('#flowDescLabel').text('流程描述');
            $('#flowDesc').attr('placeholder','请输入流程描述');
            $('#contentDescDiv').hide();
        }
    }else{
        $('#flowCodeDiv').hide();
        if(selEle == '1'){
            $('#dbTypeDiv').hide();
            $('#flowParent').show(); //上级流程
            $('#flowCodeDiv').hide();//流程编码
            $('#uploadFileDiv').show(); //上传文件
            $('#isMustDiv').show(); //前端初始化
            $('#isMust').val('1');
            $('#procDiv').hide();
            $('#flowDescLabel').text('流程描述');
            $('#flowDesc').attr('placeholder','请输入流程描述');
            $('#contentDescDiv').hide();
        }else if(selEle == '2'){
            $('#dbTypeDiv').hide();
            $('#flowParent').show();
            $('#isMustDiv').hide();
            $('#flowCodeDiv').hide();
            $('#uploadFileDiv').hide();
            $('#flowNameLabel').text('方案名称');
            $('#flowName').attr('placeholder','请输入配置方案名称');
            $('#flowDescLabel').text('方案简介');
            $('#flowDesc').attr('placeholder','请输入方案简介');
            $('#contentDescDiv').show();
            $('#procDiv').hide();
        }else if(selEle == '3'){
            $('#dbTypeDiv').show();
            $('#flowParent').show();
            $('#flowCodeDiv').show();
            $('#isMustDiv').hide();
            $('#flowCodeDiv').hide();
            $('#uploadFileDiv').hide();
            $('#flowNameLabel').text('SQL名称');
            $('#flowName').attr('placeholder','请输入SQL名称');
            $('#flowDescLabel').text('存储SQL');
            $('#flowDesc').attr('placeholder','请输入存储SQL');
            $('#procDiv').show();
            $('#contentDescDiv').hide();
        }else{
            $('#dbTypeDiv').hide();
            $('#flowParent').hide();
            $('#flowCodeDiv').hide();
            $('#uploadFileDiv').hide();
            $('#isMustDiv').hide();
            $('#procDiv').hide();
            $('#flowNameLabel').text('流程名称');
            $('#flowName').attr('placeholder','请输入流程名称');
            $('#flowDescLabel').text('流程描述');
            $('#flowDesc').attr('placeholder','请输入流程描述');
            $('#contentDescDiv').hide();
        }
    }

}

initBootstrapFileInput();

function initDbTypeCode(){
    $.ajax({
        url: Common.getRootPath() + "/admin/dict/getCodes.do",
        data: {
            dictCode: 'dbType'
        },
        type: "post",
        dataType: 'json',
        async: false,
        success: function (result) {
            var codeInfo = result.data;
            var $parent = $('#dbType');
            $parent.empty()
            for (var i = 0; i < codeInfo.length; i++) {
                $('<option value="' + codeInfo[i].dictValue + '">' + codeInfo[i].dictLabel+ '</option>').appendTo($parent);
            }
        }
    });
}

initDbTypeCode();
$(function () {
    //toastr 插件全局配置
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;

    //获取FTP共享路径
    getShareURL();
    /**
     * 查询
     * @constructor
     */
    function SearchData(){
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
    }

    /**
     * 查询参数信息
     * @param params
     * @returns {{count: *|number, first, sort, order, dbName: string, tableName: *|string, tableCnName: *|string}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            flowName: $.trim($('#flowQName').val()),
            flowCode: $.trim($('#flowQCode').val()).toUpperCase()
        };
    }
    /**
     * 初始化Table
     */
    let $table = $('#infoTable');
    $table.bootstrapTable({
        url: Common.getRootPath() + '/admin/flow/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   // 是否显示分页（*）
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
        // 得到查询的参数
        queryParams: queryParams,
        columns: [{
            field: "flowName",
            title: "流程名称",
            width: '60px',
            align: 'left'
        },  {
            field: "flowType",
            title: "流程类型",
            width: '20px',
            align: 'center',
            formatter :function (value) {
                if( value == '0'){
                    return '流程大类';
                }else  if( value == '1') {
                    return '流程小类';
                }else if(value == '2'){
                    return '流程方案';
                }else if(value == '3'){
                    return '配置脚本';
                }else{
                    return '未定义';
                }
            }
        },{
            field: "flowCode",
            title: "流程编号",
            width: '35px',
            align: 'left'
        },  {
            field: "flowDesc",
            title: "流程描述",
            width: '80px',
            align: 'center'
        },  {
            field: 'isMust',
            title: '是否必须',
            width: '20px',
            align: 'center',
            formatter:function (value) {
                if (value == '0') {
                    return '否';
                } else if (value == '1') {
                    return '是';
                }
            }
        }, {
                field: 'procName',
                title: '存储名称',
                width: '20px',
                align: 'center'
            },{
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                let title = '';
                let flowType = row.flowType;
                if(flowType == 0){
                    title = '添加流程';
                }else if(flowType == 1){
                    title = '添加方案';
                }else if(flowType == 2){
                    title = '添加脚本';
                }
                if(row.flowType < 3){
                    var f = '<a href="####" class="btn btn-primary btn-xs" name="add" mce_href="#" onclick="editFlowInfo(\''+row.id+'\',\''+row.flowType+'\',\''+row.flowName+'\',\''+row.flowCode+'\')">'+title+'</a> ';
                    var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                    var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                    return f + e + d;
                }else{
                    var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                    var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                    return  e + d;
                }

            }
        }],
        striped:true,
        treeShowField: 'flowName',
        parentIdField: 'flowPid',
        onLoadSuccess: function(data) {
            $table.treegrid({
                initialState: 'collapsed',//收缩
                treeColumn: 0,//指明第几列数据改为树形
                expanderExpandedClass: 'glyphicon glyphicon-chevron-down',
                expanderCollapsedClass: 'glyphicon glyphicon-chevron-right',
                onChange: function() {
                    $table.bootstrapTable('resetWidth');
                }
            });
        }

    });
    //自动提示
    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };
    var objMap = {};//定义一个空的js对象，主要用于记录查询的上级流程信息
    /**
     * 新增流程
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        $('#flowPid').val('0');
        $('#vid').val('');
        $('#remotePath').val('');
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
        validateForm();
        $('#uploadFile').fileinput('destroy');
        initBootstrapFileInput();
        changeDivShow('0');
        $('#flowType').attr('disabled',true);
        $('#flowModal').modal('show');
    });
    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $table.on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#isModify').val('0');
        $('#remotePath').val('');
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
        validateForm();
        var flowId = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/flow/getById.do',
            data: {'id': flowId},
            type: "post",
            dataType: 'json',
            async: false,
            cache: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#flowForm').initForm(_result.data);
                    $('#flowCode').attr('readonly','true');
                    $('#isMust').val(_result.data.isMust);
                    $('#remotePath').val(_result.data.remotePath);
                    $('#flowType').val(_result.data.flowType);
                    $('#flowType').attr('disabled',true);
                    changeDivShow(_result.data.flowType,true);
                    let remotePath = _result.data.remotePath;
                    if(remotePath && remotePath !== ''){
                        let fileJson = {
                            name : remotePath.substring(remotePath.lastIndexOf('/')+1),
                            content : getShareURL() + remotePath,
                            key:_result.data.id,
                            deleteUrl: Common.getRootPath() +'/admin/flow/deleteFile.do',
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
                    $('#vid').val(_result.data.id);
                    $('#flowModal').modal('show');
                }

            }
        });
    });

    /**
     * 列表中按钮
     *   删除流程信息
     */
    $table.on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var flowId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/flow/deleteById.do',
                data: {'id': flowId},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                        toastr.info('提交数据成功');
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
    $('#saveFlow').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#flowForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        if(isUpload){
            toastr.info('文件正在上传,请稍候！');
            return ;
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/flow/add.do';
        } else {
            url = Common.getRootPath() + '/admin/flow/update.do';
        }

        let paramJson = {
            id:$('#id').val(),
            flowType:$('#flowType').val(),
            flowPid:$('#flowPid').val(),
            flowParentCode:$('#flowParentCode').val(),
            flowParentName:$('#flowParentName').val(),
            flowCode:$('#flowCode').val(),
            flowName:$('#flowName').val(),
            dbType:$('#dbType').val(),
            procName:$('#procName').val(),
            isMust:$('#isMust').val(),
            flowDesc:$('#flowDesc').val(),
            contentDesc:$('#contentDesc').val(),
            remotePath:$('#remotePath').val(),
        };
        console.log(paramJson);
        if (bootstrapValidator.isValid()) {
            // console.log(paramJson);
            $.ajax({
                url: url,
                data: paramJson,
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        toastr.info('提交数据成功');
                        $('#vid').val(_result.data);
                        $('#flowModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                }
            });
        }
    });
    //流程类型切换
    $('#flowType').on('change',function () {
        var selEle = $(this).val();
        changeDivShow(selEle);
    });

    //自动补全
    $('#flowParentCode').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/flow/queryFlowCode.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowCode':query.toUpperCase(),
                    'flowType':parseInt($('#flowType option:selected').val())-1,
                    'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            toastr.info('没有查询到相关结果');
                          //  console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowCode] = data[i].flowName + ',' + data[i].id ;
                            results.push(data[i].flowCode);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[0] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#flowParentName').val(selectItemName);
            $('#flowPid').val(selectItemId);
        },
        items : 8,
    });

    $('#flowPCode').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/flow/queryFlowCode.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowCode':query.toUpperCase(),
                    'flowType':parseInt($('#flowType option:selected').val())-1,
                    'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            toastr.info('没有查询到相关结果');
                          //  console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowCode] = data[i].flowName + ',' + data[i].id ;
                            results.push(data[i].flowCode);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[0] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#flowPName').val(selectItemName);
            $('#flowPid').val(selectItemId);
            $('#flowPCode').parent().find('div.error_font').remove();

            $.ajax({
                url: Common.getRootPath() + '/admin/flow/createFlowCode.do',
                data:{
                    'flowType': $('#flowType').val(),
                    'flowCode': $('#flowPCode').val()
                },
                type: "post",
                dataType: 'json',
                async: false,
                success :function (result) {
                    var _result = eval(result );
                    if(_result.status == Common.SUCCESS){
                        $('#configCode').attr('readonly','true');
                        $('#configCode').val(_result.data);
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                }
            });
        },
        items : 8,
    });

    /**
     * 查询按钮
     */
    $('#query').on('click',SearchData);
    /**
     * 是否变更文件
     */
    $('#isModify').on('change',function () {
        var selectedOption = $(this).val();
        if(selectedOption == "1"){
            $('#uploadFileDiv').show();
            var fileInfo = Common.getFileInfo($('#remotePath').val());
           // console.log(fileInfo);
            if(fileInfo){
                showFlowFileInfo(fileInfo.name,fileInfo.url);
                hideUploadDiv();
            }else{
                showFlowFileInfo('','');
                showUploadDiv();
            }
        }else {
            $('#uploadFileDiv').hide();
        }
    });


});
