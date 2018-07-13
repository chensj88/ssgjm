/**
 * 报表类信息表
 * @author chensj
 * @version 1.0.0
 */

function validateForm() {
    $('#sysReportInfoForm').bootstrapValidator({
        live: 'enabled',
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            reportName: {
                message: '名称验证失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
            },
            reportDesc: {
                validators: {
                    notEmpty: {
                        message: '描述不能为空'
                    }
                }
            },
            pdName:{
                validators: {
                    notEmpty: {
                        message: '系统名称不能为空'
                    }
                }
            }
        }
    });
}

function edit(id,reportCode,reportName,reportDesc,reportType,pdId,pdName) {
    $("#code").show();
    $('#reportCode').val(reportCode);
    $('#reportName').val(reportName);
    $('#reportDesc').val(reportDesc);
    $('#id').val(id);
    $('#reportType').val(reportType);
    $('#pdId').val(pdId);
    $('#pdName').val(pdName);
    $('#sysReportInfoForm').bootstrapValidator("destroy");
    validateForm();
    $('#sysReportInfoModal').modal('show');
}
$(function () {

    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
    function SearchData(){
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
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
            reportName: $.trim($('#reportQName').val()),
            reportCode: $.trim($('#reportQCode').val()).toUpperCase(),
            reportType: $('#reportQType option:selected').val()
        };
    }



    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/report/list.do',// 要请求数据的文件路径
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
        queryParams: queryParams,
        columns: [ {
            field: "id",
            title: "序号",
            width: '15px',
            align: 'center'
        }, {
            field: "reportName",
            title: "名称",
            width: '60px',
            align: 'center'
        }, {
            field: "reportCode",
            title: "代码",
            width: '20px',
            align: 'center'
        }, {
            field: "pdName",
            title: "产品名称",
            width: '40px',
            align: 'center'
        }, {
            field: "reportType",
            title: "模板分类",
            width: '20px',
            formatter: function (value) {
                if (value == '0') {
                    return '单据';
                } else if (value == '1') {
                    return '发票';
                }else if (value == '2') {
                    return '报表';
                }
            },
            align: 'center'
        }, {
            field: "reportDesc",
            title: "描述",
            width: '50px',
            align: 'center'
        },{
            field: "status",
            title: "状态",
            width: '10px',
            formatter: function (value) {
                if (value == '1') {
                    return '生效';
                } else if (value == '0') {
                    return '失效';
                }
            },
            align: 'center'
        },{
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var e = "<a  class='btn btn-info btn-xs' onclick=edit('"+ row.id +"','"+row.reportCode +"','"+row.reportName +"','"+row.reportDesc + "','"+row.reportType + "','"+row.pdId + "','"+row.pdName + "') >编辑</a> ";
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d ;
            }
        }],
    });

    /**
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $("#code").hide();
        $("#id").val("");
        $("#pdId").val("");
        $('#reportCode').val("");
        $('#reportName').val("");
        $('#reportDesc').val("");
        $('#sysReportInfoForm').bootstrapValidator("destroy");
        validateForm();
        $('#sysReportInfoModal').modal('show');
    });

    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var productInfoId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/report/deleteById.do',
                data: {"id": productInfoId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        toastr.info('提交数据成功');
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


    $('#save').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#sysReportInfoForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/report/addSysReportInfo.do';
        } else {
            url = Common.getRootPath() + '/admin/report/update.do';
        }
      if (bootstrapValidator.isValid()) {
        $.ajax({
            url: url,
            data: $("#sysReportInfoForm").serialize(),
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    toastr.info('提交数据成功');
                    $('#sysReportInfoModal').modal('hide');
                    $("#infoTable").bootstrapTable('refresh');
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            }
        });
        }
    });

    $('#query').on('click',SearchData);
    var objMap = {};//定义一个空的js对象

    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };
    //自动补全
    $('#pdName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/productInfo/list.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'name':query.toUpperCase(),'count':matchCount,'first':0},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.rows;
                        if (data == "" || data.length == 0) {
                            toastr.info('没有查询到相关结果');
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            var name = data[i].name +'('+ data[i].id +')'+'['+ data[i].cptxName+']';
                            objMap[name] = data[i].name + ',' + data[i].id + ',' + data[i].cptxName ;
                            results.push(name);
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
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#pdName').val(selectItemName);
            $('#pdId').val(selectItemId);
        },
        items : 8,
    });
});
