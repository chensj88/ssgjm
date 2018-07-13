function editDict(dictCode,dictValue,dictLabel,dictDesc,dictSort){
    $('#dictCode').val(dictCode);
    $('#dictValue').val(dictValue);
    $('#dictValueDiv').hide();
    $('#dictCodeDiv').hide();
    $('#dictLabel').val(dictLabel);
    $('#dictDesc').val(dictDesc);
    $('#dictSort').val(dictSort);
    $('#dictModal').modal('show');
}

$(function () {
    /**
     * 查询
     * @constructor
     */
    function SearchData() {
        $('#infoTable').bootstrapTable('refresh', {pageNumber: 1});
    }

    /**
     * 查询参数信息
     * @param params
     * @returns {{count: *|number, first, sort, order, dictLabel: *|string, dictCode: string}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            dictLabel: $.trim($('#dictQLabel').val()),
            dictCode: $.trim($('#dictQCode').val())
        };
    }

    /**
     * 校验规则
     */
    function validateForm() {
        $('#dictForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                dictCode: {
                    message: '字典编码验证失败',
                    validators: {
                        notEmpty: {
                            message: '字典编码不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '字典编码只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                dictValue: {
                    message: '字典值验证失败',
                    validators: {
                        notEmpty: {
                            message: '字典值不能为空'
                        }, regexp: {
                            regexp: /^[0-9]+$/,
                            message: '字典值只能是数字'
                        },
                        threshold: 1, //有1字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {
                            url: Common.getRootPath() + '/admin/dict/existDictValue.do',//验证地址
                            message: '字典值已存在',//提示消息
                            delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST',//请求方式
                            data: function (validator) { //自定义提交数据，默认值提交当前input value
                                return {
                                    dictCode: $('#dictCode').val(),
                                    dictValue: $('#dictValue').val()
                                };
                            }
                        }

                    }
                },
                dictLabel: {
                    message: '显示文本验证失败',
                    validators: {
                        notEmpty: {
                            message: '显示文本不能为空'
                        }
                    }
                },
                dictDesc: {
                    message: '字典描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '字典描述不能为空'
                        }
                    }
                }
            }
        });
    }

    /**
     * 重置校验规则
     */
    function resetValidate() {
        $("#dictForm").bootstrapValidator('destroy');
        /* 不能使用，会造成表单不验证
        $('#dictForm').bootstrapValidator(null);*/
        validateForm();
    }

    /**
     * 初始化Table
     */
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/dict/list.do',// 要请求数据的文件路径
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
       /* strictSearch: true,
        showColumns: true,                  // 是否显示所有的列（选择显示的列）
        showRefresh: true,                  // 是否显示刷新按钮*/
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
        columns: [{
            field: "dictCode",
            title: "字典编码",
            width: '40px',
            align: 'center'
        }, {
            field: "dictValue",
            title: "字典值",
            width: '40px',
            align: 'center'
        }, {
            field: "dictLabel",
            title: "显示文本",
            width: '40px',
            align: 'center'
        }, {
            field: "dictSort",
            title: "排序值",
            width: '40px',
            align: 'center'
        }, {
            field: "dictDesc",
            title: "字典描述",
            width: '40px',
            align: 'center'
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '80px',
            formatter: function (value, row, index) {
                var e = '<a class="btn btn-info btn-xs" onclick="editDict(\''+ row.dictCode + '\',\'' + row.dictValue + '\',\'' + row.dictLabel + '\',\'' + row.dictDesc + '\',\'' + row.dictSort + '\')" >编辑</a>';
                var d = '<a href="#" class="btn btn-danger btn-xs" name="delete" mce_href="#"  aid="' + row.dictCode +','+ row.dictValue +'">删除</a> ';
                return e + d;
            }
        }],
    });




    /**
     * 新增流程
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#dictValueDiv').show();
        $('#dictCodeDiv').show();
        resetValidate();
        $('#dictModal').modal('show');
    });

    $('#saveDict').on('click', function (e) {
        e.preventDefault();
        $("#dictForm").bootstrapValidator('validate');
        var bootstrapValidator = $("#dictForm").data('bootstrapValidator');
        var url = '';
        if ($('#dictSort').val().length == 0) {
            url = Common.getRootPath() + '/admin/dict/add.do';
        } else {
            url = Common.getRootPath() + '/admin/dict/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#dictForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#dictModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });

    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var dict = $(this).attr('aid');
        var dictCode = dict.split(',')[0];
        var dictValue = dict.split(',')[1];

        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/dict/delete.do',
                data: {'dictCode': dictCode,'dictValue':dictValue},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                        Ewin.alert('提交数据成功');
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

    $('#query').on('click',SearchData);
});