/**
 * 角色信息js
 * @author chensj
 * @version 1.0.0
 */
$(function () {
    new Page();
});

function Page() {
    _self = this;
    this.init();
}

Page.prototype.init = function () {
    this.editObj = null;
    this.roleType = {};
    this.initCodes();
    this.initDataGrid();
    this.bindEvent();
    this.validateForm();
};
/**
 * 初始化下拉列表值
 */
Page.prototype.initCodes = function () {
    Common.getCodes(Common.CODETYPE_ID_ROLE_TYPE, _self.roleType, $('#roleType'), '公司角色');
};
/**
 * 初始化Table
 */
Page.prototype.initDataGrid = function () {
    $('#roleTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/role/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法
        // contentType: "application/x-www-form-urlencoded",//必须要有！！！！ POST必须有
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   // 是否显示分页（*）
        sortable: true,                     // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     // 每页的记录行数（*）
        pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
        showPaginationSwitch: false,			//显示 数据条数选择框
        search: false,                      // 是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  // 是否显示所有的列（选择显示的列）
        showRefresh: true,                  // 是否显示刷新按钮
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'roleId',
        sortName: 'roleId',
        uniqueId: "roleId",                 // 每一行的唯一标识，一般为主键列
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
        /*showColumns:true,           //内容列下拉框  */
        // 得到查询的参数
        queryParams: function (params) {
            var temp = {
                count: params.limit,    // 每页显示条数
                first: params.offset,   // 显示条数
                sort: params.sort,      // 排序列名
                order: params.order     // 排位命令（desc，asc）
            };
            return temp;
        },
        columns: [{
            checkbox: true,
            align: 'center',
            title: '单选框',
            valign: 'middle',
            halign: 'middle',
            width: '10px',
        }, {
            field: "roleId",
            title: "ID",
            width: '40px',
            align: 'center'
        }, {
            field: "roleName",
            title: "角色名称",
            width: '40px',
            align: 'center'
        }, {
            field: "roleCode",
            title: "角色代码",
            width: '40px',
            align: 'center'
        }, {
            field: "roleType",
            title: "角色类型",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '管理员角色';
                } else if (value == '1') {
                    return '公司角色';
                } else {
                    return '医院角色';
                }
            }
        }, {
            field: "roleDesc",
            title: "角色描述",
            width: '40px',
            align: 'center'
        }, {
            field: 'lastUpdateTime',
            title: '最后维护时间',
            width: '40px',
            formatter: function (value) {
                var date = new Date();
                date.setTime(value);
                return Common.getDateTime(date);
            },
            align: 'center'
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.roleId + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.roleId + '">删除</a> ';
                return e + d;
            }
        },],
    });
};
/**
 * 按钮绑定事件
 */
Page.prototype.bindEvent = function () {
    /**
     * 新增用户
     * 需要清理表格数据
     */
    $('#addRole').on('click', function () {
        $("input[type=reset]").trigger("click");
        $.ajax({
            url: Common.getRootPath() + '/common/code/getRoleCode.do',
            data: {'roleType': $('#roleType').val()},
            cache: false,
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var $result = eval(result);
                if ($result.status == Common.SUCCESS) {
                    $('#roleCode').val($result.data);
                }
            }

        });
        $('#roleId').val('');
        $('#roleType').attr('readonly', false);
        $('#roleModal').modal('show');
    });
    /**
     * 修改用户
     * 只能修改一条数据
     */
    $('#modifyRole').on('click', function () {
        var arrselections = $("#roleTable").bootstrapTable('getSelections');
        $('#roleType').attr('readonly', true);
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');
            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }
        var roleId = arrselections[0].roleId;
        $.ajax({
            url: Common.getRootPath() + '/admin/role/getById.do',
            data: {'roleId': roleId},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#roleForm').initForm(_result.data);
                    $('#roleModal').modal('show');
                }

            }
        });
    });
    /**
     * 列表中按钮
     *   编辑用户信息
     */
    $('#roleTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        var roleId = $(this).attr('aid');
        $('#roleType').attr('readonly', true);
        $.ajax({
            url: Common.getRootPath() + '/admin/role/getById.do',
            data: {'roleId': roleId},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#roleForm').initForm(_result.data);
                    $('#roleModal').modal('show');
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
                        toastr.success('提交数据成功');
                        $("#roleTable").bootstrapTable('refresh');
                    }
                },
                error: function () {
                    toastr.error('Error');
                },
                complete: function () {
                }
            });
        });
    });
    /**
     * 删除用户
     * 只能删除一条数据
     */
    $('#deleteRole').on('click', function () {
        var arrselections = $("#roleTable").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');
            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }
        var roleId = arrselections[0].roleId;
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
                        toastr.success('提交数据成功');
                        $("#roleTable").bootstrapTable('refresh');
                    }
                },
                error: function () {
                    toastr.error('Error');
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
        if ($('#roleId').val().length == 0) {
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
                    tostr.error(result);
                }
            });
        }
    });

    $('#roleType').on('change', function () {
        var roleTypeCode = $(this).val();
        $.ajax({
            url: Common.getRootPath() + '/common/code/getRoleCode.do',
            data: {'roleType': roleTypeCode},
            cache: false,
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var $result = eval(result);
                if ($result.status == Common.SUCCESS) {
                    $('#roleCode').val($result.data);
                }
            }
        });
    });
};
/**
 * 表单验证
 */
Page.prototype.validateForm = function () {
    //表单验证
    //this._changeEvent = (ieVersion === 9 || !('oninput' in el)) ? 'keyup' : 'input'; 源码修改
    //this._changeEvent = (ieVersion === 9 || !('onblur' in el)) ? 'keyup' : 'blur'; 
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
                    },
                    remote: {
                        url: Common.getRootPath() + '/admin/role/validateRoleNameExist.do',
                        message: '角色名称已经存在',
                        /**自定义提交数据，默认值提交当前input value*/
                        data: function (validator) {
                            return {
                                roleType: $('#roleType').val(),
                                roleName: $('#roleName').val()
                            };
                        }
                    }
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
};