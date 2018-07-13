/**
 * 产品信息js
 * @author chensj
 * @version 1.0.0
 */

$(function () {
    function validateForm() {
        $('#productInfoForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                cpmc: {
                    message: '产品名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '产品名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '产品名称长度必须在2到18位之间'
                        }

                    }
                },
                cptxName: {
                    validators: {
                        notEmpty: {
                            message: '产品条线不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 12,
                            message: '产品条线长度必须在2到12位之间'
                        }
                    }
                },
                gnms : {
                    validators: {
                        notEmpty: {
                            message: '功能描述不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 50,
                            message: '功能描述必须在2到50位之间'
                        }
                    }
                }
            }
        });
    }

    var objMap = {};//定义一个空的js对象
    /**
     * 查询
     * @constructor
     */
    function SearchData(){
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
    }

    /**
     *  查询参数信息
     * @param params
     * @returns {{count: *|number, first, sort, order, yhmc: *|string, userid: *|string, mobile: *|string}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            name: $.trim($('#productName').val()),
            code: $.trim($('#productCode').val())
        };
    }

    //初始化Table
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/productInfo/list.do',// 要请求数据的文件路径
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
       /* showColumns: true,                  // 是否显示所有的列（选择显示的列）
        showRefresh: true,                  // 是否显示刷新按钮*/
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
        /*showColumns:true,           //内容列下拉框  */
        // 得到查询的参数
        queryParams: queryParams,
        columns: [{
            checkbox: true,
            align: 'center',
            valign: 'middle',
            title: '单选框',
            halign: 'middle',
            width: '13px',
        }, {
            field: "id",
            title: "ID",
            width: '30px',
            align: 'center'
        }, {
            field: "name",
            title: "产品名称",
            width: '55px',
            align: 'center'
        }, {
            field: "code",
            title: "产品编号",
            width: '45px',
            align: 'center'
        }, {
            field: "gnms",
            title: "功能描述",
            width: '50px',
            align: 'center'
        }, {
            field: "cptxName",
            title: "产品条线",
            width: '45px',
            align: 'center'
        },{
            field: "zt",
            title: "状态",
            width: '20px',
            formatter: function (value) {
                if (value == '1') {
                    return '生效';
                } else if (value = '2') {
                    return '失效';
                }
            },
            align: 'center'
        }/*,{
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var e = "<a  class='btn btn-info btn-xs' onclick=edit('"+ row.id +"','"+row.name +"','"+row.code +"','"+row.gnms + "','"+row.cptx +"','"+row.cptxName+"')  mce_href='#' >编辑</a> ";
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d;
            }
        }*/],
    });


    /**
     * 新增产品
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $("#codeDiv").hide();
        $("#id").val("");
        $('#cpmc').val("");
        $('#gnms').val("");
        $('#cptx').val("");
        $('#productInfoForm').bootstrapValidator("destroy");
        validateForm();
        $('#productInfoModal').modal('show');
    });

    /**
     * 列表中按钮
     *   删除产品信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var productInfoId = $(this).attr('aid');
        // alert(productInfoId);
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/productInfo/deleteById.do',
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

    /**
     * 保存产品按钮
     * 通过隐藏域判断产品是否存在，而使用不同的方法进行新增或者修改
     */
    $('#saveProduct').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#productInfoForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/productInfo/addProductInfo.do';
        } else {
            url = Common.getRootPath() + '/admin/productInfo/update.do';
        }
        console.log($("#productInfoForm").serialize());
        $.ajax({
            url: url,
            data: $("#productInfoForm").serialize(),
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#productInfoModal').modal('hide');
                    $("#infoTable").bootstrapTable('refresh');
                }

            }
        });
    });

    $('#query').on('click',SearchData);

    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };

    $('#cptxName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/productInfo/queryProductLineName.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'cptxName':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].name] = data[i].name + ',' + data[i].id ;
                            results.push(data[i].name);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item ;
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemId = selectItem.split(',')[1];
            $('#cptx').val(selectItemId);
        },
        items : 8,
    });

    $('#query').on('click',SearchData);


})

/**
 * 产品基础信息编辑
 * @param id 产品ID
 * @param name 产品名称
 * @param code 产品编码
 * @param gnms 功能描述
 * @param cptx 产品ID
 * @param cptxName 产品名称
 */
function edit(id,name,code,gnms,cptx,cptxName) {
	$("#codeDiv").show();
    $('#cpmc').val(name);
    $('#code').val(code);
    $('#gnms').val(gnms);
    $('#cptxName').val(cptxName);
    $('#cptx').val(cptx);
    $('#id').val(id);
    $('#productInfoModal').modal('show');
}

// 表单验证
