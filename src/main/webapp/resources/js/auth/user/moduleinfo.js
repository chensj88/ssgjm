function queryParams(params) {
    return {
        count: params.limit,    // 每页显示条数
        first: params.offset,   // 显示条数
        sort: params.sort,      // 排序列名
        order: params.order,     // 排位命令（desc，asc）
        modName: $.trim($('#modQName').val())
    };
}
/*
  表单校验
 */
function validataForm($form) {
    $form.bootstrapValidator({
        live: 'enabled',
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            modName: {
                message: '菜单名称验证失败',
                validators: {
                    notEmpty: {
                        message: '菜单名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 18,
                        message: '菜单名称长度必须在2到18位之间'
                    },
                    regexp: {
                        regexp: /^[\u0391-\uFFE5A-Za-z0-9-]+$/,
                        message: '菜单名称只能包含汉字、大写、小写、数字和下划线'
                    }
                }
            },
            modDesc: {
                message: '菜单说明验证失败',
                validators: {
                    notEmpty: {
                        message: '菜单说明不能为空'
                    }
                }
            },
            iconPath :{
                message: '菜单图标验证失败',
                validators: {
                    notEmpty: {
                        message: '菜单图标不能为空'
                    }
                }
            },
            modUrl :{
                message: '链接地址验证失败',
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    }
                }
            }
        }
    });
}
//=========================数据转换==============================
function modLevelFormatter(value){
    if (value == '1') {
        return '一级菜单';
    } else if (value == '2') {
        return '二级菜单';
    } else if (value == '3') {
        return '三级菜单';
    } else {
        return '按钮信息';
    }
}
function isLeafFormatter(value){
    if (value == '1') {
        return '是';
    } else {
        return '否';
    }
}
function isManagerFormatter(value){
    if (value == '1') {
        return '是';
    } else {
        return '否';
    }
}
function btnGroupFormatter(value, row, index) {
    var content = '';
    if(row.modLevel == 9){
        var b = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.modId + '">编辑</a> ';
        var c = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.modId + '">删除</a> ';
        content = b + c;
    }else{
        var a = '<a href="####" class="btn btn-primary btn-xs" name="add" mce_href="#" aid="' + row.modId + '" aname="' + row.modName + '" aLevel="' + row.modLevel + '">增加子级</a> ';
        var b = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.modId + '">编辑</a> ';
        var c = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.modId + '">删除</a> ';
        content =  a + b + c;
    }
    return content;
}
//=========================数据转换==============================
$(function () {
    //toastr 参数配置
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
    //定义参数
    var $table = $('#infoTable');
    var $subTable;
    var $form = $('#moduleForm');
    //table初始化
    $table.bootstrapTable({
        url: Common.getRootPath() + '/admin/modulebak/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        height: 800,
        pagination: true,                   // 是否显示分页（*）
        sortable: true,                     // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
        pageSize: 15,                     // 每页的记录行数（*）
        pageList: [15, 30, 50, 100],        // 可供选择的每页的行数（*）
        showPaginationSwitch: false,			//显示 数据条数选择框
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        cardView: false,                    // 是否显示详细视图
        detailView: true,                  // 是否显示父子表
        toolbar: '#modulebtntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        selectItemName: '单选框',
        singleSelect: true,
        queryParams: queryParams,
        columns: [
            {
                field: "modName",
                title: "菜单名称",
                width: '40px',
                align: 'center'
            }, {
                field: "modDesc",
                title: "菜单说明",
                width: '50px',
                align: 'center'
            }, {
                field: "modLevel",
                title: "菜单等级",
                width: '15px',
                align: 'center',
                formatter:modLevelFormatter

            }, {
                field: "modUrl",
                title: "链接地址",
                width: '40px',
                align: 'center'
            }, {
                field: "iconPath",
                title: "菜单图标",
                width: '33px',
                align: 'center'
            }, {
                field: "isLeaf",
                title: "叶子节点",
                width: '20px',
                align: 'center',
                formatter:isLeafFormatter
            }, {
                field: "isManager",
                title: "管理员功能",
                width: '20px',
                align: 'center',
                formatter: isManagerFormatter
            }, {
                title: '操作',
                field: 'id',
                align: 'center',
                width: '38px',
                formatter:btnGroupFormatter
            },
        ],
        onExpandRow:function(index,row,$detail){
            InitSubTable(index, row, $detail);
        }
    });

    //父子表
    function InitSubTable(index, row, $detail){
        var parId = row.modId;
        var cur_table = $detail.html('<table class="table-align"></table>').find('table');
        function initParam(params) {
            var queryJson = {
                parId:parId
            };
            queryJson['count'] = params.limit;    // 每页显示条数
            queryJson['first'] = params.offset;   // 显示条数
            queryJson['sort'] = params.sort;      // 排序列名
            queryJson['order'] = params.order;     // 排位命令（desc，asc）
            return queryJson;
        }
        $subTable = $(cur_table);
        $subTable.bootstrapTable({
            url: Common.getRootPath()+'/admin/modulebak/childList.do',
            striped: true,
            method: 'GET', // 请求方法
            queryParams: initParam,
            cache: false,  // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   // 是否显示分页（*）
            sortable: true,                     // 是否启用排序
            sortOrder: "asc",                   // 排序方式
            sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
            pageSize: 10,                     // 每页的记录行数（*）
            pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
            uniqueId: "id",
            idField: 'id',
            sortName: 'id',
            uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
            detailView: true,                  // 是否显示父子表
            columns: [
                {
                    field: "modName",
                    title: "菜单名称",
                    width: '40px',
                    align: 'center'
                }, {
                    field: "modDesc",
                    title: "菜单说明",
                    width: '50px',
                    align: 'center'
                }, {
                    field: "modLevel",
                    title: "菜单等级",
                    width: '15px',
                    align: 'center',
                    formatter: modLevelFormatter

                }, {
                    field: "modUrl",
                    title: "链接地址",
                    width: '40px',
                    align: 'center'
                }, {
                    field: "iconPath",
                    title: "菜单图标",
                    width: '33px',
                    align: 'center'
                }, {
                    field: "isLeaf",
                    title: "叶子节点",
                    width: '20px',
                    align: 'center',
                    formatter: isLeafFormatter
                }, {
                    field: "isManager",
                    title: "管理员功能",
                    width: '20px',
                    align: 'center',
                    formatter: isManagerFormatter
                }, {
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    width: '38px',
                    formatter:btnGroupFormatter
                }
            ],
            onExpandRow:function(index,row,$detail){
                InitSubTable(index, row, $detail);
            }
        });

    }
    //主页面查询
    function SearchData(){
        $table.bootstrapTable('refresh', { pageNumber: 1 });
    }
    //查询信息
    $('#query').on('click',SearchData);
    //增加按钮
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#modId').val('');
        $('#modLevel').val('1');
        $('#isLeaf').val('0');
        $('#modLevel').val('1');
        $('#pModule').hide();
        $('#moduleForm').bootstrapValidator('destroy');
        validataForm($form);
        changeModelLevelLabel(1);
        $('#moduleModal').modal('show');
    });
    /**
     * 增加子菜单
     */
    $table.on('click', 'a[name="add"]', function (e) {
        e.preventDefault();
        $form.bootstrapValidator('destroy');
        validataForm($form);
        $("input[type=reset]").trigger("click");
        var modId = $(this).attr('aid');
        var modName = $(this).attr('aname');
        var modLevel = $(this).attr('aLevel');
        $('#parId').val(modId);
        $('#modPName').val(modName);
        $('#modId').val('');
        $('#modLevel').val('2');
        $('#isLeaf').val('1');
        $('#modLevel').val(parseInt(modLevel)+1);
        $('#pModule').show();
        changeModelLevelLabel(parseInt(modLevel)+1);
        $('#moduleModal').modal('show');
    });
    /**
     * 编辑当前菜单
     */
    $table.on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $form.bootstrapValidator('destroy');
        $("input[type=reset]").trigger("click");
        validataForm($form);
        var modId = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/module/getById.do',
            data: {'modId': modId},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#moduleForm').initForm(_result.data);
                    if(_result.data.modLevel == '1'){
                        $('#pModule').hide();
                    }else{
                        $('#pModule').show();
                    }
                    changeModelLevelLabel(_result.data.modLevel);
                    $('#moduleModal').modal('show');
                }
            }
        });
    });
    /**
     * 列表中按钮
     *   删除用户信息
     */
    $table.on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var modId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/module/deleteById.do',
                data: {"modId": modId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
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

    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };
    var objMap = {};//定义一个空的js对象

    $('#modPName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            var modLevel = $('#modLevel').val() - 1;
            $.ajax({
                url : Common.getRootPath() + '/admin/module/queryModule.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'modName':query,'modLevel':modLevel,'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].modName] = data[i].modId;
                            results.push(data[i].modName);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item;
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItemId = objMap[item];
            $('#parId').val(selectItemId);
        },
        items : 10,
    });

    $('#modLevel').on('change',function () {
        var selVal = $(this).val();
        if(selVal == '9'){
            $('#modNameLabel').text('按钮名称');
            $('#modNameLabel').attr('placeholder','请输入按钮名称');
            $('#modDescLabel').text('按钮描述');
            $('#modDescLabel').attr('placeholder','请输入按钮描述');
            $('#modPNameLabel').text('菜单名称');
            $('#modPNameLabel').attr('placeholder','请输入菜单名称');
            $('#modUrlDiv').hide();
            $('#iconPathDiv').hide();
            $('#isLeafDiv').hide();
            $('#isManagerDiv').hide();
        }else{
            $('#modNameLabel').text('菜单名称');
            $('#modNameLabel').attr('placeholder','请输入菜单名称');
            $('#modDescLabel').text('菜单说明');
            $('#modDescLabel').attr('placeholder','请输入菜单说明');
            $('#modPNameLabel').text('上级菜单名称');
            $('#modPNameLabel').attr('placeholder','请输入上级菜单名称');
            $('#modUrlDiv').show();
            $('#iconPathDiv').show();
            $('#isLeafDiv').show();
            $('#isManagerDiv').show();
        }
    });

    $('#savemodule').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $form.data('bootstrapValidator');
        //修复记忆的组件不验证
        debugger
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#modId').val().length == 0) {
            url = Common.getRootPath() + '/admin/module/add.do';
        } else {
            url = Common.getRootPath() + '/admin/module/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#moduleForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $('#moduleModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                fail: function (result) {
                    toastr.error(result);
                }
            });
        }
    });

    function changeModelLevelLabel(value){
        if(value == '9'){
            $('#modNameLabel').text('按钮名称');
            $('#modNameLabel').attr('placeholder','请输入按钮名称');
            $('#modDescLabel').text('按钮描述');
            $('#modDescLabel').attr('placeholder','请输入按钮描述');
            $('#modPNameLabel').text('菜单名称');
            $('#modPNameLabel').attr('placeholder','请输入菜单名称');
            $('#modUrlDiv').hide();
            $('#iconPathDiv').hide();
            $('#isLeafDiv').hide();
            $('#isManagerDiv').hide();
        }else{
            $('#modNameLabel').text('菜单名称');
            $('#modNameLabel').attr('placeholder','请输入菜单名称');
            $('#modDescLabel').text('菜单说明');
            $('#modDescLabel').attr('placeholder','请输入菜单说明');
            $('#modPNameLabel').text('上级菜单名称');
            $('#modPNameLabel').attr('placeholder','请输入上级菜单名称');
            $('#modUrlDiv').show();
            $('#iconPathDiv').show();
            $('#isLeafDiv').show();
            $('#isManagerDiv').show();
        }
    }
});