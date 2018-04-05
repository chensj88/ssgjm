/**
 * 培训视频js
 */
$(function () {

    var videoType = {};
    var videoCType = {};
    var objMap = {};
    var selectedFlie = false;
    $('#isModifyDiv').hide();

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
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '脚本名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '脚本名称不能为空'
                        },
                        threshold :  2 , //有2字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: Common.getRootPath() + '/admin/script/existName.do',//验证地址
                            message: '脚本名称已存在',//提示消息
                            type: 'POST'//请求方式
                        },
                    }
                },
                sDesc : {
                    message: '脚本描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '脚本描述不能为空'
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
                uploadFile:{
                    message: '上传文件验证失败',
                    validators: {
                        notEmpty: {
                            message: '上传文件不能为空'
                        }
                    }
                }
            }
        });
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
            title: "脚本描述",
            width: '20px',
            align: 'center'
        }, {
            field: "appName",
            title: "适用系统",
            width: '20px',
            align: 'center'
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
        $('#videoNameDiv').hide();
        $('#isModifyDiv').hide();
        //清空验证信息
        $('#scriptForm').bootstrapValidator("destroy");
        validateForm();
        initFileInput($('#uploadFile'));
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
        $('#scriptForm').bootstrapValidator("destroy");
        validateForm();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        initFileInput($('#uploadFile'));
        //取消默认选中
        $('#isModifyDiv').show();
        $('#uploadFileDiv').hide();
        $('#videoNameDiv').hide();
        //赋值
        $('#scriptForm').initForm(data);
        $('#appId').val(data.appId);
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
                        if(selectedFlie){
                            $("#uploadFile").fileinput("upload");
                        }
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
    $('#appName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/script/queryAppName.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'name':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].name] = data[i].name + ',' + data[i].id ;
                            results.push(data[i].name);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item ;
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemId = selectItem.split(',')[1];
            $('#appId').val(selectItemId);

        },
        items : 10,
    });

    $('#isModify').on('change',function () {
        var selectedOption = $(this).val();
        if(selectedOption == "1"){
            $('#uploadFileDiv').show();
        }else {
            $('#uploadFileDiv').hide();
        }
    });

});