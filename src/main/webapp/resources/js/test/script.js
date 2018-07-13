/**
 * 培训视频js
 */
let isUpload = false;
$(function () {

    var videoType = {};
    var videoCType = {};
    var objMap = {};
    var selectedFlie = false;
    $('#isModifyDiv').hide();
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
                        threshold: 2, //有2字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: Common.getRootPath() + '/admin/script/existName.do',//验证地址
                            message: '脚本名称/存储名称已存在',//提示消息
                            type: 'POST'//请求方式
                        },
                    }
                },
                sDesc: {
                    message: '校验内容验证失败',
                    validators: {
                        notEmpty: {
                            message: '校验内容不能为空'
                        }
                    }
                },
                dataType: {
                    message: '数据类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '数据类型不能为空'
                        }
                    }
                }
            }
        })
    }

    function initFileInput(ele,url) {
        ele.fileinput({
            language: "zh",//配置语言
            uploadUrl: Common.getRootPath() +"/admin/upload/script.do",
            showUpload : false,
            showRemove : true,
            showPreview : false,
            showCaption: true,//是否显示标题
            uploadAsync: true,
            dropZoneEnabled:false,
            uploadLabel: "上传",//设置上传按钮的汉字
            uploadClass: "btn btn-primary",//设置上传按钮样式
            maxFileSize : 0,
            maxFileCount: 1,/*允许最大上传数，可以多个，当前设置单个*/
            enctype: 'multipart/form-data',
            /*allowedPreviewTypes : [ 'video' ],*/
            allowedFileExtensions : ["sql", "txt"],/*上传文件格式*/
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            showBrowse: false,
            browseOnZoneClick: true,
            uploadExtraData:function (previewId, index) {
                return {'id':$('#vid').val()};
            },
            slugCallback : function(filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        }).on("filebatchselected",function(event, files){
            if(files.length > 0) {
                var name = Common.substr(files[0].name, '.');
                $('#name').val(name);
                $('#videoNameDiv').show();
                selectedFlie = true;
            }
        }).on('filepreupload', function(event, data, previewId, index) {     //上传中
        }).on('fileuploaded',function(event, data, previewId, index){    //一个文件上传成功
            var _data = data.response;
            if(_data.status == Common.SUCCESS){
                $("#infoTable").bootstrapTable('refresh');
            }else{
                Ewin.alert(_data.msg)
            }
            $('#vid').val('');
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
        $('#appId').val('');
        $('#videoNameDiv').show();
        $('#isModifyDiv').hide();
        //清空验证信息
        $("#scriptForm").bootstrapValidator('resetForm');
        validateForm();
        $('#dataType').val('0');
        $('#uploadFile').fileinput('destroy');
        initBootstrapFileInput();
        isUpload = false;
        $('#uploadFileDiv').show();
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
        //$('#scriptForm').bootstrapValidator("destroy");
        $("#scriptForm").bootstrapValidator('resetForm');
        validateForm();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        $('#remotePath').val(data.remotePath);
        // initFileApiUpload('file-upload');
        var remotePath = data.remotePath;
        if(remotePath && remotePath !== ''){
            let fileJson = {
                name : remotePath.substring(remotePath.lastIndexOf('/')+1),
                content : data.sDesc,
                key:data.id,
                deleteUrl: Common.getRootPath() +'/admin/script/deleteFile.do',
                url:Common.getShareURL() +remotePath
            };
            $('#uploadFile').fileinput('destroy');
            initBootstrapFileInput(fileJson);
            $('#isModify').val('0');
            $('#isModifyDiv').show();
            $('#uploadFileDiv').hide();
            isUpload = false;
        }else {
            $('#uploadFile').fileinput('destroy');
            initBootstrapFileInput();
            $('#isModifyDiv').hide();
            $('#uploadFileDiv').show();
            isUpload = false;
        }
        //取消默认选中
        $('#videoNameDiv').show();
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
            console.log(isUpload);
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

    /**
     * 客户姓名
     */
    // $('#appName').typeahead({
    //     source : function (query,process) {
    //         var matchCount =this.options.items;//允许返回结果集最大数量
    //         $.ajax({
    //             url : Common.getRootPath() + '/admin/script/queryAppName.do',
    //             type: "post",
    //             dataType: 'json',
    //             async: false,
    //             data: {'name':query.toUpperCase(),'matchCount':matchCount},
    //             success: function (result) {
    //                 var _result = eval(result);
    //                 if (_result.status == Common.SUCCESS) {
    //                     var data = _result.data;
    //                     if (data == "" || data.length == 0) {
    //                         console.log("没有查询到相关结果");
    //                     };
    //                     var results = [];
    //                     for (var i = 0; i < data.length; i++) {
    //                         objMap[data[i].name] = data[i].name + ',' + data[i].id ;
    //                         results.push(data[i].name);
    //                     }
    //                     process(results);
    //                 }
    //             }
    //         });
    //     },
    //     highlighter: function (item) {
    //         return item ;
    //     },
    //     afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
    //         var selectItem = objMap[item];
    //         var selectItemId = selectItem.split(',')[1];
    //         $('#appId').val(selectItemId);
    //
    //     },
    //     items : 10,
    // });

    $('#isModify').on('change',function () {
        var selectedOption = $(this).val();
        if(selectedOption == "1"){
            var fileInfo = Common.getFileInfo($('#remotePath').val());
            if(fileInfo){
                showFlowFileInfo(fileInfo.name,fileInfo.url);
                hideUploadDiv();
            }else{
                showFlowFileInfo('','');
                showUploadDiv();
            }
            $('#uploadFileDiv').show();
        }else {
            $('#uploadFileDiv').hide();
        }
    });


    var uploadStatus =  Common.STATUS_BEFORE_UPLOAD; //0 初始化  1 上传  2 完成
    var url = Common.getShareURL();

    function initFileApiUpload(ele) {
        uploadStatus = Common.STATUS_BEFORE_UPLOAD;
        var $ele = $('#'+ele);
        $ele.fileapi({
            clearOnComplete: false,
            url:Common.getRootPath() + Common.url.script.uploadURL,
            autoUpload: true,
            multiple:false,
            paramName:'uploadFile',
            // paramName:'file',
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
                var isAllow = /(\.|\/)(sql|txt)$/i.test(suffix);
                if(!isAllow){
                    data.all = [];
                    data.files = [];
                    $('#fileInfo').html('当前文件【'+fileName+'】文件格式不支持，<br>只支持sql,txt').css('color','red');
                    return ;
                }
                uploadStatus = Common.STATUS_PROCESS_UPLOAD;
                /*$('#reset').show();*/
                $('#fileUpload').hide();
                $('#save').attr('disabled',true);
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
                $('#save').attr('disabled',false);
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
            url: Common.getRootPath() +'/admin/script/deleteFile.do',
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