/**
 * 软硬件设备类型表
 * @author huwanli
 * @version 1.0.0
 */

function initVliadate() {
    // 表单验证
    $('#sysSoftHardwareInfoForm').bootstrapValidator({
        live: 'enabled',
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            shName: {
                message: '设备名称验证失败',
                validators: {
                    notEmpty: {
                        message: '设备名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 18,
                        message: '设备名称长度必须在2到18位之间'
                    }

                }
            },
            shDesc: {
                validators: {
                    notEmpty: {
                        message: '设备描述不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 50,
                        message: '设备描述长度必须在2到50位之间'
                    }
                }
            },
            shBrand : {
                validators: {
                    notEmpty: {
                        message: '推荐品牌不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 50,
                        message: '推荐品牌必须在2到50位之间'
                    }
                }
            },
            shBrandType : {
                validators: {
                    notEmpty: {
                        message: '推荐型号不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 50,
                        message: '推荐型号必须在2到50位之间'
                    }
                }
            }
        }
    });
}

function editSH(id,shCode,shName,shDesc,shBrand,shBrandType) {
    console.log(id)
    $("#code").show();
    $("#id").val(id);
    $('#shCode').val(shCode);
    $('#shName').val(shName);
    $('#shDesc').val(shDesc);
    $('#shBrand').val(shBrand);
    $('#shBrandType').val(shBrandType);
    $('#sysSoftHardwareInfoForm').bootstrapValidator("destroy");
    initVliadate();
    $('#sysSoftHardwareInfoModal').modal('show');
}

$(function () {

    /**
     * 查询
     * @constructor
     */
    function SearchData(){
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
    }

    /**
     * 查询参数信息
     * @param params
     * @returns {{count: *|number, first, sort, order, shName: *|string, shCode: string, shType: *|jQuery}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            shName: $.trim($('#shQName').val()),
            shCode: $.trim($('#shQCode').val()).toUpperCase(),
            shType: $('#shQType option:selected').val()
        };
    }

    /**
     * 初始化Table
     */
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/hardware/list.do',// 要请求数据的文件路径
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
        // showColumns: true,                  // 是否显示所有的列（选择显示的列）
        // showRefresh: true,                  // 是否显示刷新按钮
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
        /*showColumns:true,           //内容列下拉框  */
        // 得到查询的参数
        queryParams:queryParams,
        columns: [ {
            field: "id",
            title: "序号",
            width: '30px',
            align: 'center'
        }, {
            field: "shName",
            title: "软硬件名称",
            width: '35px',
            align: 'center'
        }, {
            field: "shCode",
            title: "软硬件代码",
            width: '35px',
            align: 'center'
        }, {
            field: "shType",
            title: "设备分类",
            width: '30px',
            formatter: function (value) {
                if (value == '1') {
                    return '硬件';
                } else if (value == '0') {
                    return '软件';
                }
            },
            align: 'center'
        }, {
            field: "shDesc",
            title: "设备描述",
            width: '45px',
            align: 'center'
        },
            {
                field: "shBrand",
                title: "推荐品牌",
                width: '40px',
                align: 'center'
            },{
                field: "shBrandType",
                title: "推荐型号",
                width: '40px',
                align: 'center'
            },{
                field: "shEnvType",
                title: "应用环境",
                width: '40px',
                formatter: function (value) {
                    if (value == '1') {
                        return '正式';
                    } else if (value == '0') {
                        return '测试';
                    } else if (value == '2') {
                        return '测试&正式';
                    }
                },
                align: 'center'
            },{
                field: "status",
                title: "状态",
                width: '20px',
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
                    var e = "<a mce_href='#' class='btn btn-info btn-xs' onclick=editSH('"+ row.id +"','"+row.shCode +"','"+row.shName +"','"+row.shDesc + "','"+row.shBrand+"','"+row.shBrandType+"') >编辑</a> ";
                    var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                    return e + d ;
                }
            }],
    });
    //查询
    $('#query').on('click',SearchData);
    /**
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $("#id").val("");
        $('#shCode').val("");
        $('#shName').val("");
        $('#shDesc').val("");
        $('#shBrand').val("");
        $('#shBrandType').val("");
        $('#sysSoftHardwareInfoForm').bootstrapValidator("destroy");
        initVliadate();
        $('#sysSoftHardwareInfoModal').modal('show');
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
                url: Common.getRootPath() + '/admin/hardware/deleteById.do',
                data: {"id": productInfoId},
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


    $('#save').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#sysSoftHardwareInfoForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/hardware/addSysSoftHardwareInfo.do';
        } else {
            url = Common.getRootPath() + '/admin/hardware/update.do';
        }
        $.ajax({
            url: url,
            data: $("#sysSoftHardwareInfoForm").serialize(),
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    toastr.success('提交数据成功');
                    $('#sysSoftHardwareInfoModal').modal('hide');
                    $("#infoTable").bootstrapTable('refresh');
                }

            }
        });
    });


})
