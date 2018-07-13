/**
 *
 */
toastr.options.positionClass = 'toast-top-center';
toastr.options.timeOut = 30;
toastr.options.extendedTimeOut = 60;
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
     * 可配置列表查询方法
     * @constructor
     */
    function SearchQueryData() {
        $('#queryBData').bootstrapTable('refresh');
    }

    /**
     * 已配置列表查询方法
     * @constructor
     */
    function SearchConfigData() {
        var url = Common.getRootPath() + "/admin/pBdata/queryById.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"pdId": productId,'tableName': $.trim($('#bdataB').val()),'tableCnName': $.trim($('#bdataB').val())},
            dataType: 'json',
            cache:false,
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    initConfigtable(data);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });
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

    /**
     * 可配置表参数
     * @param params
     * @returns {{count: *|number, first, sort, order, tableName: *|string, tableCnName: *|string}}
     */
    function queryBDataParams(params) {
        return {
            tableName: $.trim($('#bdataA').val()),
            tableCnName: $.trim($('#bdataA').val())
        };
    }

    /**
     * 已配置表查询参数
     * @param params
     * @returns {{count: *|number, first, sort, order, tableName: *|string, tableCnName: *|string}}
     */
    function configBDataParams(params) {
        return {
            tableName: $.trim($('#bdataB').val()),
            tableCnName: $.trim($('#bdataB').val())
        };
    }

    /**=================================变量定义==============================================*/
    var pdTable =  $('#productTable');
    var queryTable =  $('#queryBData');
    var configTable =  $('#configBData');
    var infoTable = $('#infoTable');
    var productId = 0;
    /**=================================事件绑定==============================================*/
    /**
     * 初始化已配置数据Table
     * @param data 后台json
     */
    function initConfigtable(data) {
        if (data) {
            configTable.bootstrapTable("destroy").bootstrapTable({
                data: data.data,
                cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   // 是否显示分页（*）
                sortable: true,                     // 是否启用排序
                sortOrder: "asc",                   // 排序方式
                sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
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
                toolbar: '#toolbarB',
                toolbarAlign: 'right',
                paginationLoop: false, //分页条无限循环的功能
                singleSelect: false,
                selectItemName: '多选框',
                queryParams: configBDataParams, // 得到查询的参数
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    title: '多选',
                    halign: 'middle',
                    width: '13px',
                }, {
                    field: "dbName",
                    title: "库名",
                    width: '55px',
                    align: 'center'
                }, {
                    field: "tableName",
                    title: "数据库表名",
                    width: '45px',
                    align: 'center'
                }, {
                    field: "tableCnName",
                    title: "库表中文名",
                    width: '50px',
                    align: 'center'
                }],
            });
        } else {
            configTable.bootstrapTable({
                cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   // 是否显示分页（*）
                sortable: true,                     // 是否启用排序
                sortOrder: "asc",                   // 排序方式
                sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
                pageSize: 10,                     // 每页的记录行数（*）
                pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
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
                toolbar: '#toolbarB',
                toolbarAlign: 'right',
                paginationLoop: false, //分页条无限循环的功能
                singleSelect: false,
                selectItemName: '多选框',
                queryParams: configBDataParams, // 得到查询的参数
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    title: '多选',
                    halign: 'middle',
                    width: '13px',
                }, {
                    field: "dbName",
                    title: "库名",
                    width: '55px',
                    align: 'center'
                }, {
                    field: "tableName",
                    title: "数据库表名",
                    width: '45px',
                    align: 'center'
                }, {
                    field: "tableCnName",
                    title: "库表中文名",
                    width: '50px',
                    align: 'center'
                }],
            });
        }
    }

    function initInfotable(data) {
        if (data) {
            infoTable.bootstrapTable("destroy").bootstrapTable({
                data: data.data,
                cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   // 是否显示分页（*）
                sortable: true,                     // 是否启用排序
                sortOrder: "asc",                   // 排序方式
                sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
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
                paginationLoop: false, //分页条无限循环的功能
                singleSelect: false,
                useCurrentPage : true,
                columns: [ {
                    field: "pdCode",
                    title: "产品编码",
                    width: '20px',
                    align: 'center'
                }, {
                    field: "pdName",
                    title: "产品名称",
                    width: '15px',
                    align: 'center'
                }, {
                    field: "bdName",
                    title: "数据库表名",
                    width: '15px',
                    align: 'center'
                },{
                    field: "dbName",
                    title: "数据库名称",
                    width: '15px',
                    align: 'center'
                }, {
                    field: "bdCnName",
                    title: "库表中文名",
                    width: '15px',
                    align: 'center'
                }, {
                    field: "lastUpdate",
                    title: "操作人",
                    width: '15px',
                    align: 'center'
                }, {
                    field: "lastUpdateTime",
                    title: "操作时间",
                    width: '15px',
                    align: 'center'
                }
                ],
            });
        } else {
            infoTable.bootstrapTable("destroy").bootstrapTable({
                cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   // 是否显示分页（*）
                sortable: true,                     // 是否启用排序
                sortOrder: "asc",                   // 排序方式
                sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
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
                paginationLoop: false, //分页条无限循环的功能
                singleSelect: false,
                columns: [ {
                    field: "pdCode",
                    title: "产品编码",
                    width: '55px',
                    align: 'center'
                }, {
                    field: "pdName",
                    title: "产品名称",
                    width: '45px',
                    align: 'center'
                }, {
                    field: "bdName",
                    title: "数据库表名",
                    width: '45px',
                    align: 'center'
                },{
                    field: "dbName",
                    title: "数据库名称",
                    width: '55px',
                    align: 'center'
                }, {
                    field: "bdCnName",
                    title: "库表中文名",
                    width: '50px',
                    align: 'center'
                }, {
                    field: "lastUpdate",
                    title: "操作人",
                    width: '50px',
                    align: 'center'
                }, {
                    field: "lastUpdateTime",
                    title: "操作时间",
                    width: '50px',
                    align: 'center'
                }
                ],
            });
        }
    }

    function showModalWindow(title,data){
        $('#pdModalLabel').text(title);
        initInfotable(data);
        $('#pdModal').modal('show');
    }

    function refreshConfigTable(productId){
        var url = Common.getRootPath() + "/admin/pBdata/queryById.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"pdId": productId},
            dataType: 'json',
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    /*Ewin.alert('提交数据成功');*/
                    initConfigtable(data);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });
    }
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
            radio: true,
            align: 'center',
            valign: 'middle',
            title: '单选框',
            halign: 'middle',
            width: '13px'
        }, {
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
                } else if (value = '2') {
                    return '失效';
                }
            }

        }],
    });
    /**
     * 初始化可配置数据Table
     */
    queryTable.bootstrapTable({
        url: Common.getRootPath() + '/admin/basicData/listNoPage.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   // 是否显示分页（*）
        sortable: true,                     // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     // 每页的记录行数（*）
        pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
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
        toolbar: '#toolbarA',
        toolbarAlign: 'right',
        paginationLoop: false, //分页条无限循环的功能
        singleSelect: false,
        selectItemName: '多选框',
        queryParams: queryBDataParams, // 得到查询的参数
        columns: [{
            checkbox: true,
            align: 'center',
            valign: 'middle',
            title: '多选',
            halign: 'middle',
            width: '13px',
        }, {
            field: "dbName",
            title: "库名",
            width: '25px',
            align: 'center'
        }, {
            field: "tableName",
            title: "数据库表名",
            width: '55px',
            align: 'center'
        }, {
            field: "tableCnName",
            title: "库表中文名",
            width: '55px',
            align: 'center'
        }],
    });

    /**
     * 初始化已配置数据table
     */
    initConfigtable();
    /**
     *  查询按钮事件 产品查询按钮
     */
    $('#queryPD').on('click', SearchData);
    /**
     * 查询按钮事件  可配置查询按钮
     */
    $('#bdataAbtn').on('click', SearchQueryData);
    /**
     * 查询按钮事件  已配置查询按钮
     */
    $('#bdataBbtn').on('click', SearchConfigData);
    /**
     *  产品信息表单击事件
     */
    pdTable.on('click-row.bs.table', function (event, row, element, field) {
        $('#productTable .success').removeClass('success');//去除之前选中的行的，选中样式
        $(element).addClass('success');//添加当前选中的 success样式用于区别

        console.log(row);
        productId = row.id;
        var url = Common.getRootPath() + "/admin/pBdata/queryById.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"pdId": productId},
            dataType: 'json',
            cache:false,
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    /*Ewin.alert('提交数据成功');*/
                    initConfigtable(data);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });

    });

    /**
     * 左移点击事件 删除
     */
    $('#moveLeft').on('click', function (event) {
        event.preventDefault();
        var productSelections = pdTable.bootstrapTable('getSelections');
        if(!productSelections || productSelections.length <= 0){
            toastr.error('请选择至少一条产品信息');
            return ;
        }
        var cBdataSelections = configTable.bootstrapTable('getSelections');
        if(!cBdataSelections || cBdataSelections.length <= 0){
            toastr.error('请先选择至少一条需要删除的基础数据信息');
            return ;
        }

        var pdId = productSelections.id;
        var bdids = '';
        var cLength = cBdataSelections.length;
        $.each(cBdataSelections,function (index,value,array) {
            if(index == cLength - 1){
                bdids += value.id;
            }else{
                bdids += value.id +',';
            }
        });
        console.log(bdids);
        var url = Common.getRootPath() + "/admin/pBdata/queryProductDataInfoById.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"pdId": pdId,'bdIds':bdids},
            dataType: 'json',
            cache : false,
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    showModalWindow('需要删除的基础数据信息',data);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });



    });



    /**
     * 右移点击事件 添加
     */
    $('#moveRight').on('click', function (event) {
        event.preventDefault();
        var productSelections = pdTable.bootstrapTable('getSelections');
        if(!productSelections || productSelections.length <= 0){
            toastr.error('请选择至少一条产品信息');
            return ;
        }
        console.log(productSelections);
        var queryTableSelections = queryTable.bootstrapTable('getSelections');
        if(!queryTableSelections || queryTableSelections.length <= 0){
            toastr.error('请先选择至少一条需要添加的基础数据信息');
            return ;
        }

        var pdId = productSelections[0].id;
        console.log(pdId);
        var bdids = '';
        var cLength = queryTableSelections.length;
        $.each(queryTableSelections,function (index,value,array) {
            if(index == cLength - 1){
                bdids += pdId +','+value.id;
            }else{
                bdids += pdId +','+value.id +';';
            }
        });
        console.log(bdids);
        var url = Common.getRootPath() + "/admin/pBdata/addMapping.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"idList":bdids},
            dataType: 'json',
            cache : false,
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    refreshConfigTable(pdId);
                    toastr.success('映射关系添加成功');
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });
    });

    $('#remove').on('click',function () {
        var tdata = infoTable.bootstrapTable('getData');
        var ids = '';
        var tLength = tdata.length;
        $.each(tdata,function (index,value,array) {
            if(index == tLength - 1){
                ids += value.pdId +','+value.bdId;
            }else{
                ids += value.pdId +','+value.bdId +';';
            }
        });
        var pdId = tdata[0].pdId;
        var url = Common.getRootPath() + '/admin/pBdata/removeMapping.do';
        $.ajax({
            type: "post",
            url: url,
            data: {'idList':ids},
            cache : false,
            dataType: 'json',
            success: function (data, status) {
                if (data.status == Common.SUCCESS) {
                    $('#pdModal').modal('hide');
                    toastr.success('映射关系删除成功');
                    refreshConfigTable(pdId);
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });
    });

});






