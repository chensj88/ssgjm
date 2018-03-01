/**
 * 培训视频js
 */
$(function () {

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
        $('#table').bootstrapTable('refresh', {pageNumber: 1});
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
                        }
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
                videoType : {
                    message: '视频分类验证失败',
                    validators: {
                        notEmpty: {
                            message: '视频分类不能为空'
                        }
                    }
                },
            }
        });
    }

    function initFileInput(ele,url) {
        var console = ele;
        console.fileinput({
            language: "zh",//配置语言
            uploadUrl: url,
            showUpload : true,
            showRemove : true,
            showPreview : false,
            showCaption: false,//是否显示标题
            uploadAsync: true,
            dropZoneEnabled:false,
            uploadLabel: "上传",//设置上传按钮的汉字
            uploadClass: "btn btn-primary",//设置上传按钮样式
            maxFileSize : 0,
            maxFileCount: 1,/*允许最大上传数，可以多个，当前设置单个*/
            enctype: 'multipart/form-data',
            allowedPreviewTypes : [ 'video' ], //allowedFileTypes: ['image', 'video', 'flash'],
            allowedFileExtensions : ["avi", "mp4","wmv","rm","rmvb"],/*上传文件格式*/
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            showBrowse: false,
            browseOnZoneClick: true,
            slugCallback : function(filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        });
    }

    $('#table').bootstrapTable({
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
        columns: [ {
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "videoName",
            title: "视频名称",
            width: '40px',
            align: 'center'
        }, {
            field: "videoDesc",
            title: "视频描述",
            width: '40px',
            align: 'center'
        }, {
            field: "videoType",
            title: "视频分类",
            width: '40px',
            align: 'center'
        }, {
            field: "remotePath",
            title: "远程地址",
            width: '40px',
            align: 'center'
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '80px',
            formatter: function (value, row, index) {
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                var f = '<a href="####" class="btn btn-success btn-xs" name="upload" mce_href="#" aid="' + row.id + '">上传视频</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + f + d;
            }
        }],
    });


    $('#query').on('click',SearchData);

    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        //清空验证信息
        $('#trainForm').bootstrapValidator("destroy");
        validateForm();
        initFileInput($('#uploadFile'),Common.getRootPath() +"/admin/upload/tvideo.do");
        $('#trainModal').modal('show');
    });
    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#table').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        //清空验证信息
        $('#trainForm').bootstrapValidator("destroy");
        validateForm();
        initFileInput($('#uploadFile'),Common.getRootPath() +"/admin/upload/tvideo.do");
        var vid = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/train/getById.do',
            data: {'id': vid},
            type: "post",
            dataType: 'json',
            async: false,
            cache: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#trainForm').initForm(_result.data);
                    $('#trainModal').modal('show');
                }
            }
        });
    });
    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#table').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var vid = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/train/deleteById.do',
                data: {'id': vid},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                        Ewin.alert('提交数据成功');
                        $("#table").bootstrapTable('refresh');
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
                        $('#trainModal').modal('hide');
                        $("#table").bootstrapTable('refresh');
                    }
                }
            });
        }
    });
     //初始化上传文件框


    $('#upload').on('click',function (){// 提交图片信息 //
        //先上传文件，然后在回调函数提交表单
        $('#videoUp').fileinput('upload');

    });

});