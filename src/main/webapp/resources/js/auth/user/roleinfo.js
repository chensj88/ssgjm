/**
 * 角色信息js
 * @author chensj
 * @version 1.0.0
 */



$(function () {
    function editRole(data) {
        $('#id').val(data.id);
        $('#roleName').val(data.roleName);
        $('#roleDesc').val(data.roleDesc);
        $('#isDel').val(data.isDel);
        $('#isLock').val(data.isLock);
        $('#roleModal').modal('show');
    }
    /**
     * 查询
     * @constructor
     */
    function SearchData(){
        $('#flowTable').bootstrapTable('refresh', { pageNumber: 1 });
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
            roleName: $.trim($('#roleQName').val())
        };
    }

    function validateForm() {
        $('#roleForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                roleName: {
                    message: '角色名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '角色名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '角色名称长度必须在2到18位之间'
                        },
                        regexp: {
                            regexp: /^[\u0391-\uFFE5A-Za-z0-9]+$/,
                            message: '角色名称只能包含汉字、大写、小写、数字和下划线'
                        }
                        /*,remote: {
                            url: Common.getRootPath() + '/admin/role/validateRoleNameExist.do',
                            message: '角色名称已经存在',
                            /!**自定义提交数据，默认值提交当前input value*!/
                            data: function (validator) {
                                return {
                                    roleType: $('#roleType').val(),
                                    roleName: $('#roleName').val()
                                };
                            }
                        }*/
                    }
                },
                roleDesc: {
                    message: '角色信息描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '角色信息描述不能为空'
                        }
                    }
                }
            }
        });

    }

    $('#roleTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/role/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法<b class="arrow icon-angle-down"></b>
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
        toolbar: '#rolebtntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        selectItemName: '单选框',
        singleSelect: true,
        queryParams:queryParams,
        columns: [{
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "roleName",
            title: "角色名称",
            width: '40px',
            align: 'center'
        }, {
            field: "roleDesc",
            title: "角色描述",
            width: '40px',
            align: 'center'
        },{
            field: "isLock",
            title: "是否锁定",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '否';
                } else {
                    return '是';
                }
            }
        }, {
            field: "isDel",
            title: "是否删除",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '否';
                } else {
                    return '是';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var e = '<a href="#" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id +'" >编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d;
            }
        },],
    });
    /**
     * 新增用户
     * 需要清理表格数据
     */
    $('#addRole').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#roleForm').bootstrapValidator("destroy");
        validateForm();
        $('#roleId').val('');
        $('#roleModal').modal('show');
    });

    $('#roleTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        var roleId = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/role/getById.do',
            data: {'id': roleId},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                var data = _result.data;
                if (_result.status == Common.SUCCESS) {
                    editRole(data);
                }
            }
        });
    });
    /**
     * 列表中按钮
     *   删除用户信息
     */
    $('#roleTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var roleId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/role/deleteById.do',
                data: {"roleId": roleId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        Ewin.alert('提交数据成功');
                        $("#roleTable").bootstrapTable('refresh');
                    }
                },
                error: function () {
                    Ewin.alert('Error');
                },
                complete: function () {
                }
            });
        });
    });

    /**
     * 保存用户按钮
     * 通过隐藏域判断用户是否存在，而使用不同的方法进行新增或者修改
     */
    $('#saveRole').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#roleForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/role/add.do';
        } else {
            url = Common.getRootPath() + '/admin/role/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#roleForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#roleModal').modal('hide');
                        $("#roleTable").bootstrapTable('refresh');
                    }
                },
                fail: function (result) {
                    Ewin.alert(result);
                }
            });
        }
    });

    /**
     * 查询按钮
     */
    $('#query').on('click',SearchData);
});
