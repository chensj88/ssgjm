/**
 * 流程信息
 * chensj
 * 2018-01-25
 */
$(function () {
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
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
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/flow/list.do',// 要请求数据的文件路径
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
        // showColumns: true,                  // 是否显示所有的列（选择显示的列）
        // showRefresh: true,                  // 是否显示刷新按钮
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        //showToggle: true,                   // 是否显示详细视图和列表视图的切换按钮
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
        columns: [ {
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "flowType",
            title: "流程类型",
            width: '40px',
            align: 'center',
            formatter :function (value) {
                if( value == '0'){
                    return '流程大类';
                }else {
                    return '流程小类';
                }
            }
        }, {
            field: "flowCode",
            title: "流程编号",
            width: '50px',
            align: 'center'
        }, {
            field: "flowName",
            title: "流程名称",
            width: '60px',
            align: 'center'
        }, {
            field: "flowDesc",
            title: "流程描述",
            width: '60px',
            align: 'center'
        }, {
            field: "flowParentCode",
            title: "上级流程编号",
            width: '40px',
            align: 'center'
        }, {
            field: 'flowParentName',
            title: '上级流程名称',
            width: '40px',
            align: 'center'
        }, {
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
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d;
            }
        }],
    });

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
        $('#flowPid').val('');
        $('#vid').val('');
        $('#remotePath').val('');
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
        validateForm();
        initFileApiUpload('file-upload','/admin/upload/test.do');
        $('#flowParent').show();
        $('#flowCodeDiv').show();
        $('#uploadFileDiv').show();
        $('#isModifyDiv').hide();
        $('#flowCodeDiv').attr('readonly',true);
        $('#flowModal').modal('show');
    });
    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#isModify').val('0');
        $('#remotePath').val('');
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
        /*$('#flowForm').bootstrapValidator(null);*/
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
                    if(_result.data.flowType == "0"){
                        $('#flowParent').hide();
                        $('#uploadFileDiv').hide();
                        $('#isModifyDiv').hide();
                    }else{
                        $('#flowParent').show();
                        $('#isModifyDiv').show();
                        $('#uploadFileDiv').hide();
                    }
                    //initFileInput();
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
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
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
                        Ewin.alert('提交数据成功');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                error: function (msg) {
                    Ewin.alert(msg);
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
        if(!checkUploadStatus()){
            toastr.info('文件正在上传,请稍候！');
            return ;
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/flow/add.do';
        } else {
            url = Common.getRootPath() + '/admin/flow/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#flowForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#vid').val(_result.data);
                        $('#flowModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });
    //流程类型切换
    $('#flowType').on('change',function () {
        var selEle = $(this).val();
        if(selEle == '1'){
            $('#flowParent').show();
            $('#flowCodeDiv').show();
            $('#uploadFileDiv').show();
        }else{
            $('#flowParent').hide();
            $('#flowCodeDiv').hide();
            $('#uploadFileDiv').hide();
        }
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
                data: {'flowCode':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
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
            $.ajax({
                url: Common.getRootPath() + '/admin/flow/createFlowCode.do',
                data:{
                    'flowType': $('#flowType').val(),
                    'flowCode': $('#flowParentCode').val()
                },
                type: "post",
                dataType: 'json',
                async: false,
                success :function (result) {
                    var _result = eval(result );
                    if(_result.status == Common.SUCCESS){
                        $('#flowCode').attr('readonly','true');
                        $('#flowCode').val(_result.data);
                    }
                }
            });
        },
        items : 8,
    });
    /**
     * 表单校验规则
     */
    function validateForm() {
        $('#flowForm').bootstrapValidator({
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
                        threshold: 6, //有1字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {
                            url: Common.getRootPath() + Common.url.flow.existName,//验证地址
                            message: '流程名称已存在',//提示消息
                            delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'/*,//请求方式
                            data: function (validator) { //自定义提交数据，默认值提交当前input value
                                return {
                                    flowName: $('#flowName').val(),
                                    id: $('#vid').val()
                                };
                            }*/
                        }
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
            }
        });
    }
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
            console.log(fileInfo);
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

    //================================== 文件上传框处理 =====================================================//
    var uploadStatus =  Common.STATUS_BEFORE_UPLOAD; //0 初始化  1 上传  2 完成
    var url = Common.getShareURL();

    initFileApiUpload('file-upload',Common.url.flow.uploadURL,Common.UPLOAD_TYPE_FLOW);
    /**
     * 初始化上传
     * @param ele 元素
     * @param url 远程URL
     * @param uploadType 上传文件类型 Common.UPLOAD_TYPE_*
     */
    function initFileApiUpload(ele,url,uploadType) {
        uploadStatus = Common.STATUS_BEFORE_UPLOAD;
        var $ele = $('#'+ele);
        $ele.fileapi({
            clearOnComplete: false,
            url:Common.getRootPath() + url,
            autoUpload: true,
            multiple:false,
            paramName:'uploadFile',
            maxSize: FileAPI.MB*10, // max file size
            maxFiles:1,
            elements: {
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
                uploadStatus = Common.STATUS_PROCESS_UPLOAD;
                /*$('#reset').show();*/
                $('#fileUpload').hide();
            },
            onFileComplete: function (evt, uiEvt){
                var file = uiEvt.file;
                var json = uiEvt.result;
                var url = json.url + json.path;
                $('#uploadFileName').text(file.name);
                $('#downLoadFile').attr('path',url);
                $('#deleteFile').attr('path',url);
                $('#jsInfo').html('');
                hideUploadDiv();
                uploadStatus = Common.STATUS_FINISH_UPLOAD;

                $('#remotePath').val(json.path);
            }
        });
        showUploadDiv();
    }

    $('#downLoadFile').on('click',function () {
        window.open($(this).attr('path'));
    });

    $('#deleteFile').on('click',function (e)  {
        //阻止默认行为
        e.preventDefault();
        $('#uploadFileName').text('');
        $('#downLoad').attr('path','');
        $('#delete').attr('path','');
        $.ajax({
            url: Common.getRootPath() +'/admin/flow/deleteFile.do',
            data: {'id':$('#vid').val()},
            type: "post",
            dataType: 'json',
            async: false,
            cache : false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#remotePath').val('');
                    showFlowFileInfo('','');
                    showUploadDiv();
                }
            }
        });
        showUploadDiv();
    });

    $('#reset').on('click',function () {
        $('#fileUpload').show();
        $('#reset').hide();
    });

    /**
     * 已经上传的文件展示
     * @param name
     * @param url
     */
    function showFlowFileInfo(name,url) {
        $('#uploadFileName').text(name);
        $('#downLoadFile').attr('path',url);
        $('#deleteFile').attr('path',url);
        hideUploadDiv();
    }

    /**
     * 隐藏上传区域
     */
    function hideUploadDiv() {
        uploadStatus = Common.STATUS_FINISH_UPLOAD;
        $('#uploadFile').show();
        $('#fileUploadDiv').hide();
    }
    /**
     * 显示上传区域
     */
    function showUploadDiv() {
        uploadStatus = Common.STATUS_BEFORE_UPLOAD ;
        $('#fileUploadDiv').show();
        $('#uploadFile').hide();
        $('#reset').hide();
        $('#fileUpload').show();
    }

    /**
     * 检查上传的状态
     * @returns {boolean}
     */
    function checkUploadStatus(){
        if(uploadStatus === Common.STATUS_BEFORE_UPLOAD  || uploadStatus === Common.STATUS_FINISH_UPLOAD){
            return true;
        }else {
            return false;
        }
    }
});
