/**
 * 功能信息js
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
    this.funcType = {};
    this.initCodes();
    this.initDataGrid();
    this.bindEvent();
    this.validateForm();
};
/**
 * 初始化下拉列表值
 */
Page.prototype.initCodes = function () {
    Common.getCodes(Common.CODETYPE_ID_FUNC_TYPE, _self.funcType, $('#funcType'), '前台功能');
};
/**
 * 初始化Table
 */
Page.prototype.initDataGrid = function () {
    $('#funcTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/func/list.do',// 要请求数据的文件路径
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
            field: "id",
            title: "ID",
            width: '40px',
            align: 'center'
        }, {
            field: "funcName",
            title: "功能名称",
            width: '40px',
            align: 'center'
        }, {
            field: "funcAction",
            title: "功能路径",
            width: '80px',
            align: 'center'
        }, {
            field: "funcType",
            title: "功能类型",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '前台功能';
                } else if (value == '1') {
                    return '后台功能';
                }
            }
        }, {
            field: "funcDesc",
            title: "功能描述",
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
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.funcId + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.funcId + '">删除</a> ';
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
    $('#addfunc').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#funcId').val('');
        $('#funcType').attr('readonly', false);
        $('#funcModal').modal('show');
    });
    /**
     * 修改用户
     * 只能修改一条数据
     */
    $('#modifyfunc').on('click', function () {
        var arrselections = $("#funcTable").bootstrapTable('getSelections');
        $('#funcType').attr('readonly', true);
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');
            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }
        var funcId = arrselections[0].funcId;
        $.ajax({
            url: Common.getRootPath() + '/admin/func/getById.do',
            data: {'funcId': funcId},
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
    /**
     * 列表中按钮
     *   编辑用户信息
     */
    $('#funcTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        var funcId = $(this).attr('aid');
        $('#funcType').attr('readonly', true);
        $.ajax({
            url: Common.getRootPath() + '/admin/func/getById.do',
            data: {'funcId': funcId},
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
    /**
     * 列表中按钮
     *   删除用户信息
     */
    $('#funcTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var funcId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/func/deleteById.do',
                data: {"funcId": funcId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $("#funcTable").bootstrapTable('refresh');
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
    $('#deletefunc').on('click', function () {
        var arrselections = $("#funcTable").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');
            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }
        var funcId = arrselections[0].funcId;
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/func/deleteById.do',
                data: {"funcId": funcId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $("#funcTable").bootstrapTable('refresh');
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
    $('#savefunc').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#funcForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#funcId').val().length == 0) {
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
                        $("#funcTable").bootstrapTable('refresh');
                    }
                },
                fail: function (result) {
                    tostr.error(result);
                }
            });
        }
    });
};
/**
 * 表单验证
 */
Page.prototype.validateForm = function () {
    //表单验证
    //this._changeEvent = (ieVersion === 9 || !('oninput' in el)) ? 'keyup' : 'input'; 源码修改
    //this._changeEvent = (ieVersion === 9 || !('onblur' in el)) ? 'keyup' : 'blur'; 
    $('#funcForm').bootstrapValidator({
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            funcName: {
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
                    },
                    remote: {
                        url: Common.getRootPath() + '/admin/func/validatefuncNameExist.do',
                        message: '功能名称已经存在',
                        /**自定义提交数据，默认值提交当前input value*/
                        data: function (validator) {
                            return {
                                funcName: $('#funcName').val()
                            };
                        }
                    }
                }
            },
            funcDesc: {
                message: '功能信息描述验证失败',
                validators: {
                    notEmpty: {
                        message: '功能信息描述不能为空'
                    }
                }
            }
        }
    });
};