function editParam(paramName,paramValue,paramDesc,isStop,paramType,id){
    $('#paramName').val(paramName);
    $('#paramValue').val(paramValue);
    $('#paramDesc').val(paramDesc);
    $('#isStop').val(isStop);
    $('#paramType').val(paramType);
    $('#id').val(id);
    $('#paramModal').modal('show');
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
            paramValue: $.trim($('#paramQValue').val()),
            paramName: $.trim($('#paramQName').val())
        };
    }

    /**
     * 校验规则
     */
    function validateForm() {
        $('#paramForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                paramName: {
                    message: '参数名验证失败',
                    validators: {
                        notEmpty: {
                            message: '参数名不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '参数名只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                dictValue: {
                    message: '参数值验证失败',
                    validators: {
                        notEmpty: {
                            message: '参数值不能为空'
                        }, regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '参数值只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                paramDesc: {
                    message: '参数描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '参数描述不能为空'
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
        $("#paramForm").bootstrapValidator('destroy');
        /* 不能使用，会造成表单不验证
        $('#dictForm').bootstrapValidator(null);*/
        validateForm();
    }

    /**
     * 初始化Table
     */
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/param/list.do',// 要请求数据的文件路径
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
      /*  strictSearch: true,
        showColumns: true,                  // 是否显示所有的列（选择显示的列）*/
        showRefresh: false,                  // 是否显示刷新按钮
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
            field: "paramName",
            title: "参数名",
            width: '40px',
            align: 'center'
        }, {
            field: "paramValue",
            title: "参数值",
            width: '40px',
            align: 'center'
        }, {
            field: "paramDesc",
            title: "参数描述",
            width: '40px',
            align: 'center'
        }, {
            field: "isStop",
            title: "是否停用",
            width: '40px',
            align: 'center',
            formatter :function(value){
                if(value == "0"){
                    return '否';
                }else{
                    return '是';
                }
            }
        }, {
            field: "paramType",
            title: "参数类型",
            width: '40px',
            align: 'center',
            formatter :function(value){
                if(value == "0"){
                    return '测试';
                }else if(value == "1"){
                    return '正式';
                }else {
                    return '正式&测试';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '80px',
            formatter: function (value, row, index) {
                var e = '<a class="btn btn-info btn-xs" onclick="editParam(\''+ row.paramName + '\',\'' + row.paramValue + '\',\'' + row.paramDesc + '\',\'' + row.isStop + '\',\'' + row.paramType + '\',\''+row.id+'\')" >编辑</a>';
                var d = '<a href="#" class="btn btn-danger btn-xs" name="delete" mce_href="#"  aid="' + row.id +'">删除</a> ';
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
        resetValidate();
        $('#id').val('');
        $('#paramModal').modal('show');
    });


    $('#saveParam').on('click', function (e) {
        e.preventDefault();
        $("#paramForm").bootstrapValidator('validate');
        var bootstrapValidator = $("#paramForm").data('bootstrapValidator');
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/param/add.do';
        } else {
            url = Common.getRootPath() + '/admin/param/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#paramForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#paramModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });

    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var paramId = $(this).attr('aid');

        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/param/delete.do',
                data: {'id': paramId},
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