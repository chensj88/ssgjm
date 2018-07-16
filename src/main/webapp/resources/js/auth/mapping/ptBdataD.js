/**
 *
 */
$(function () {
    /**==========================方法区========================================*/
    /**
     * 产品查询按钮查询方法
     * @constructor
     */
    function SearchData() {
        $('#productTable').bootstrapTable('refresh', {pageNumber: 1});
    }
    /**
     *  产品查询参数信息
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


    /**=================================变量定义==============================================*/
    var pdTable =  $('#productTable');
    var productId = 0;
    var nonSelectData = [];
    var selectData = [];
    /**=================================事件绑定==============================================*/

    /**
     * 初始化产品Table
     */
    pdTable.bootstrapTable({
        url: Common.getRootPath() + '/admin/productInfo/list.do',// 要请求数据的文件路径
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
        search: false,                      // 是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  // 是否显示所有的列（选择显示的列）
        showRefresh: false,                  // 是否显示刷新按钮
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        singleSelect: true,    //每次只允许选择一条
        selectItemName: '单选框',
        queryParams: queryParams, // 得到查询的参数
        columns: [{
            field: "id",
            title: "序号",
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
            field: "cptxName",
            title: "产品条线",
            width: '45px',
            align: 'center'
        }, {
            field: "zt",
            title: "状态",
            width: '20px',
            align: 'center',
            formatter: function (value) {
                if (value == '1') {
                    return '生效';
                } else if (value == '2') {
                    return '失效';
                }
            }

        }],
    });


    /**
     *  查询按钮事件 产品查询按钮
     */
    $('#queryPD').on('click', SearchData);
    /**
     *  产品信息表单击事件
     */
    pdTable.on('click-row.bs.table', function (event, row, element, field) {
        $('#productTable .success').removeClass('success');//去除之前选中的行的，选中样式
        $(element).addClass('success');//添加当前选中的 success样式用于区别
        productId = row.id;
        var url = Common.getRootPath() + "/admin/pBdataD/queryById.do";
        initDoubleBoxData(productId);
    });

    initDoubleBoxData();

    function initDoubleBoxData(pId){
        var url = Common.getRootPath() + "/admin/pBdataD/queryById.do";
        var vpId = -1;
        if(pId){
            vpId = pId;
        }
        $.ajax({
            type: "post",
            url: url,
            data: {"pdId": vpId},
            dataType: 'json',
            cache:false,
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    initDoublebox(data.nsData,data.sData);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });

    }

    var doublebox = $('#doublebox').doublebox({
        nonSelectedListLabel: '基础数据',
        selectedListLabel: '已选择基础数据',
        filterPlaceHolder: '请输入表名',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
        nonSelectedList: nonSelectData,
        selectedList: selectData,
        optionValue: "id",
        optionText: "tableContent",
        doubleMove: true,
    });
    function initDoublebox(nonSelectData,selectData) {
        doublebox = $('#doublebox').doublebox({
            nonSelectedListLabel: '基础数据',
            selectedListLabel: '已选择基础数据',
            filterPlaceHolder: '请输入表名',
            preserveSelectionOnMove: 'moved',
            moveOnSelect: false,
            nonSelectedList: nonSelectData,
            selectedList: selectData,
            optionValue: "id",
            optionText: "tableContent",
            doubleMove: true,
        });
    }
    $('#doublebox').on("change",function (event) {

        console.log(doublebox.getSelectedOptions());
    });



});






