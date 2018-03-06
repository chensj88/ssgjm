/**
 * 基础数据类型信息js
 * @author chensj
 * @version 1.0.0
 */
$(function () {
    /**
     * 查询
     * @constructor
     */
    function SearchData(){
        $('#productLineInfo').bootstrapTable('refresh', { pageNumber: 1 });
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
            name: $.trim($('#productLineName').val())
        };
    }

    /**
     * 初始化Table
     */
    $('#productLineInfo').bootstrapTable({
        url: Common.getRootPath() + '/admin/productLineInfo/list.do',// 要请求数据的文件路径
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
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbar: '#btntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        singleSelect: true,
        // 得到查询的参数
        queryParams: queryParams,
        columns: [
            {
                field: "id",
                title: "ID",
                width: '10px',
                align: 'center'
            }, {
                field: "name",
                title: "产品条线名称",
                width: '55px',
                align: 'center'
            }, {
                field: "yxtnm",
                title: "原系统内码",
                width: '20px',
                align: 'center'
            }, {
                field: "cpxl",
                title: "产品小类",
                width: '20px',
                align: 'center'
            },
            {
                field: "cpdl",
                title: "产品大类",
                width: '20px',
                align: 'center'
            },
            {
                field: "cpdm",
                title: "产品部门",
                width: '20px',
                align: 'center'
            },
            {
                field: "hsdy",
                title: "核算单元",
                width: '20px',
                align: 'center'
            },
            {
                field: "lx",
                title: "类型",
                width: '20px',
                align: 'center'
            },
            {
                field: "mklx",
                title: "模块类型",
                width: '20px',
                align: 'center'
            },
            {
                field: "cpz",
                title: "产品组",
                width: '20px',
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
            }
        ],
    });

    $('#query').on('click',SearchData);
});
