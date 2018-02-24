/**
 * 流程信息
 * chensj
 * 2018-01-25
 */
$(function () {
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
            flowName: $.trim($('#flowQName').val()),
            flowCode: $.trim($('#flowQCode').val()).toUpperCase()
        };
    }

    /**
     * 初始化Table
     */
    $('#flowTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/flow/list.do',// 要请求数据的文件路径
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
        toolbar: '#btntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        singleSelect: true,
        selectItemName: '单选框',
        // 得到查询的参数
        queryParams: queryParams,
        columns: [ {
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "flowType",
            title: "流程类型",
            width: '40px',
            align: 'center',
            formatter :function (value) {
                if( value == '0'){
                    return '流程大类';
                }else {
                    return '流程小类';
                }
            }
        }, {
            field: "flowCode",
            title: "流程编号",
            width: '40px',
            align: 'center'
        }, {
            field: "flowName",
            title: "流程名称",
            width: '40px',
            align: 'center'
        }, {
            field: "flowDesc",
            title: "流程描述",
            width: '40px',
            align: 'center'
        }, {
            field: "flowParentCode",
            title: "上级流程编号",
            width: '40px',
            align: 'center'
        }, {
            field: 'flowParentName',
            title: '上级流程名称',
            width: '40px',
            align: 'center'
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '80px',
            formatter: function (value, row, index) {
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d;
            }
        }],
    });

    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };
    var objMap = {};//定义一个空的js对象

    /**
     * 新增流程
     * 需要清理表格数据
     */
    $('#addFlow').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        $('#flowPid').val('');
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
       /* $('#flowForm').bootstrapValidator(null);*/
        validateForm();
        $('#flowParent').show();
        $('#flowCodeDiv').show();
        $('#flowCodeDiv').attr('readonly',true);
        $('#flowModal').modal('show');
    });
    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#flowTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        //清空验证信息
        $('#flowForm').bootstrapValidator("destroy");
        /*$('#flowForm').bootstrapValidator(null);*/
        validateForm();
        var flowId = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/flow/getById.do',
            data: {'id': flowId},
            type: "post",
            dataType: 'json',
            async: false,
            cache: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#flowForm').initForm(_result.data);
                    $('#flowCode').attr('readonly','true');
                    if(_result.data.flowType == "0"){
                        $('#flowParent').hide();
                    }else{
                        $('#flowParent').show();
                    }
                    $('#flowModal').modal('show');
                }

            }
        });
    });
    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#flowTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var flowId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/flow/deleteById.do',
                data: {'id': flowId},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                        Ewin.alert('提交数据成功');
                        $("#flowTable").bootstrapTable('refresh');
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
    $('#saveFlow').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#flowForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/flow/add.do';
        } else {
            url = Common.getRootPath() + '/admin/flow/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#flowForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#flowModal').modal('hide');
                        $("#flowTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });
    //流程类型切换
    $('#flowType').on('change',function () {
        var selEle = $(this).val();
        console.log(selEle);
        if(selEle == '1'){
            $('#flowParent').show();
            $('#flowCodeDiv').show();
        }else{
            $('#flowParent').hide();
            $('#flowCodeDiv').hide();
        }
    });

    //自动补全
    $('#flowParentCode').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/flow/queryFlowCode.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowCode':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowCode] = data[i].flowName + ',' + data[i].id ;
                            results.push(data[i].flowCode);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[0] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#flowParentName').val(selectItemName);
            $('#flowPid').val(selectItemId);
            $.ajax({
                url: Common.getRootPath() + '/admin/flow/createFlowCode.do',
                data:{
                    'flowType': $('#flowType').val(),
                    'flowCode': $('#flowParentCode').val()
                },
                type: "post",
                dataType: 'json',
                async: false,
                success :function (result) {
                    var _result = eval(result );
                    if(_result.status == Common.SUCCESS){
                        $('#flowCode').attr('readonly','true');
                        $('#flowCode').val(_result.data);
                    }
                }
            });
        },
        items : 8,
    });
    function validateForm() {
        $('#flowForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                flowName: {
                    message: '流程名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '流程名称不能为空'
                        }
                    }
                },
                flowDesc : {
                    message: '流程描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '流程描述不能为空'
                        }
                    }
                },
            }
        });
    }

    /**
     * 查询按钮
     */
    $('#queryFlow').on('click',SearchData);
});
