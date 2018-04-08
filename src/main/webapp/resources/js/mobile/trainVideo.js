/**
 * 培训视频js
 */
$(function () {

    var videoType = [];
    var videoCType = [];
    var objMap = {};
    var selectedFlie = false;
    var uploadComplete = false;
    $('#customer').hide();
    $('#isModifyDiv').hide();
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            videoName: $.trim($('#videoQName').val())
        };
    }

    function SearchData() {
        $('#infoTable').bootstrapTable('refresh', {pageNumber: 1});
    }

    function validateForm() {
        $('#trainForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                videoName: {
                    message: '视频名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '视频名称不能为空'
                        },
                        threshold :  2 , //有2字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: Common.getRootPath() + '/admin/train/existVideoName.do',//验证地址
                            message: '视频名称已存在',//提示消息
                           /* delay :  1000,*///每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'// ,请求方式
                            /**自定义提交数据，默认值提交当前input value*/
                             /*data: function(validator) {
                               return {
                                   id: $('#id').val(),
                                   videoName: $('#videoName').val()
                               };
                            }*/
                        },
                    }
                },
                videoDesc : {
                    message: '视频描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '视频描述不能为空'
                        }
                    }
                },
                custName : {
                    message: '客户名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '客户名称不能为空'
                        }
                    }
                },
                videoType:{
                    message: '视频分类验证失败',
                    validators: {
                        notEmpty: {
                            message: '视频分类不能为空'
                        }
                    }
                }
            }
        });
    }

    function initFileInput(ele,url) {
        ele.fileinput({
            language: "zh",//配置语言
            uploadUrl: Common.getRootPath() +"/admin/upload/tvideo.do",
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
            allowedPreviewTypes : [ 'video' ],
            allowedFileExtensions : ["mp4","flv","rmvb"],/*上传文件格式*/
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
            if(files.length > 0){
                var name = Common.substr(files[0].name,'.');
                $('#videoName').val(name);
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
            url: Common.getRootPath() + '/admin/train/getById.do',
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

    function initCodes() {
        Common.getCodes('videoType',videoType,$('#videoType'));
        Common.getCodes('videoCType',videoCType,$('#videoCType'));
    }

    initCodes();

    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/train/list.do',// 要请求数据的文件路径
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
            field: "videoName",
            title: "视频名称",
            width: '40px',
            align: 'center'
        }, {
            field: "typeLabel",
            title: "视频分类",
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
        $('#cId').val('');
        $('#videoType').val('');
        $('#videoCType').val('');
        $('#customer').hide();
        $('#videoNameDiv').hide();
        $('#isModifyDiv').hide();
        //清空验证信息
        $('#trainForm').bootstrapValidator("destroy");
        validateForm();
        initFileInput($('#uploadFile'));
        // $("#uploadFile").fileinput('reset');
        $('#uploadFileDiv').show();
        $('#trainModal').modal('show');
    });

    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#customer').hide();
        //清空验证信息
        $('#trainForm').bootstrapValidator("destroy");
        validateForm();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        initFileInput($('#uploadFile'));
        //取消默认选中
        $('#videoType').find('option:selected').attr('selected',false);
        $('#isModifyDiv').show();
        $('#uploadFileDiv').hide();
        $('#videoNameDiv').hide();
        $('#isModify').find('option:selected').attr('selected',false);
        //赋值
        $('#trainForm').initForm(data);
        if(data.videoType == Common.VIDEO_TYPE_CUSTOMER){
            $('#customer').show();
            $('#cId').val(data.cId);
        }
        $('#trainModal').modal('show');
    });
    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        var videoName = data.videoName;
        var status = data.status;
        var alterName = '';
        if(status == '0'){
            alterName = '确认要启用视频['+videoName+']吗？';
        }else{
            alterName = '确认要停用视频['+videoName+']吗？';
        }
        Ewin.confirm({message: alterName }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/train/modifyById.do',
                data: {'id': vid,'status':status},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                       /* Ewin.alert('提交数据成功');*/
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
        var bootstrapValidator = $("#trainForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/train/add.do';
        } else {
            url = Common.getRootPath() + '/admin/train/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#trainForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#vid').val(_result.data);
                        if(selectedFlie){
                            Ewin.alert('文件正在后台上传，请稍候，页面中表格中字段【<span style="color:red;">远程地址</span>】刷新后，上传完成！')
                            $("#uploadFile").fileinput("upload");
                        }
                        $('#trainModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });

    /**
     * 客户姓名
     */
    $('#custName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/train/queryCustomerName.do',
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
                            objMap[data[i].name] = data[i].name + ',' + data[i].id + ',' +data[i].code;
                            results.push(data[i].name);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[2] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemId = selectItem.split(',')[1];
            $('#cId').val(selectItemId);

        },
        items : 10,
    });

    $('#videoType').on('change',function () {
       var selectedOption = $(this).val();
       if(selectedOption == Common.VIDEO_TYPE_CUSTOMER){
           $('#customer').show();
       }else {
           $('#customer').hide();
       }
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