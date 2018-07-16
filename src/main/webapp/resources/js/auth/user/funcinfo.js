/**
 * 功能信息js
 * @author chensj
 * @version 1.0.0
 */
$(function () {

    function queryParam(params) {
        var temp = {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            funName : $.trim($('#funQName').val())
        };
        return temp;
    }

    function SearchData() {
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
    }

    $('#query').on('click',SearchData);

    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/func/list.do',// 要请求数据的文件路径
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
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        //showToggle: true,                   // 是否显示详细视图和列表视图的切换按钮
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbar: '#funcbtntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        selectItemName: '单选框',
        singleSelect: true,
        /*showColumns:true,           //内容列下拉框  */
        // 得到查询的参数
        queryParams: queryParam,
        columns: [ {
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "funName",
            title: "功能名称",
            width: '40px',
            align: 'center'
        }, {
            field: "funCode",
            title: "功能码",
            width: '80px',
            align: 'center'
        }, {
            field: "iconPath",
            title: "图标路径",
            width: '40px',
            align: 'center'
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
        },],
    });

    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        $('#orderValue').val('');
        $('#funcForm').bootstrapValidator('destroy');
        validateForm();
        $('#funcModal').modal('show');
    });

    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        var id = $(this).attr('aid');
        $('#funcForm').bootstrapValidator('destroy');
        validateForm();
        $.ajax({
            url: Common.getRootPath() + '/admin/func/getById.do',
            data: {'id': id},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#funcForm').initForm(_result.data);
                    $('#funcModal').modal('show');
                }
            }
        });
    });

    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var id = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/func/deleteById.do',
                data: {"id": id},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
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

    $('#savefunc').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        $('#funcForm').bootstrapValidator('destroy');
        validateForm();
        var bootstrapValidator = $("#funcForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/func/add.do';
        } else {
            url = Common.getRootPath() + '/admin/func/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#funcForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#funcModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                },
            });
        }
    });

    function validateForm() {
        $('#funcForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                funName: {
                    message: '功能名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '功能名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '功能名称长度必须在2到18位之间'
                        },
                        regexp: {
                            regexp: /^[\u0391-\uFFE5A-Za-z0-9]+$/,
                            message: '功能名称只能包含汉字、大写、小写、数字和下划线'
                        }
                    }
                },
                funCode: {
                    message: '功能码验证失败',
                    validators: {
                        notEmpty: {
                            message: '功能码不能为空'
                        }
                    }
                },
                iconPath: {
                    message: '图标路径验证失败',
                    validators: {
                        notEmpty: {
                            message: '图标路径不能为空'
                        }
                    }
                }
            }
        });
    }
});
