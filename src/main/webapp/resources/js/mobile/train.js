/**
 * 培训视频js
 */
$(function () {

    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            videoName: $.trim($('#videoQName').val())
        };
    }

    function SearchData() {
        $('#infoTable').bootstrapTable('refresh', {pageNumber: 1});
    }

    function queryInfoByDataId(id) {
        var data = {};
        $.ajax({
            url: Common.getRootPath() + '/admin/train/getById.do',
            data: {'id': id},
            type: "post",
            dataType: 'json',
            async: false,
            cache: false,
            success: function (result) {
                data = result.data;
            }
        });
        return data;
    }

    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/train/list.do',// 要请求数据的文件路径
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
        singleSelect: true,
        selectItemName: '单选框',
        queryParams: queryParams,  // 得到查询的参数
        columns: [{
            field: "videoName",
            title: "视频名称",
            width: '40px',
            align: 'center'
        }, {
            field: "typeLabel",
            title: "视频分类",
            width: '20px',
            align: 'center'
        }, {
            field: "remotePath",
            title: "远程地址",
            width: '80px',
            align: 'center'
        }, {
            field: "status",
            title: "状态",
            width: '20px',
            align: 'center',
            formatter:function (value) {
                if(value == '0'){
                    return '失效';
                }else{
                    return '生效';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '40px',
            formatter: function (value, row, index) {
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return  d ;
            }
        }],
    });

    $('#query').on('click',SearchData);


    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var vid = $(this).attr('aid');
        var data = queryInfoByDataId(vid);
        var videoName = data.videoName;
        var  alterName = '确认要删除视频['+videoName+']吗？';
        Ewin.confirm({message: alterName }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/train/delete.do',
                data: {'id': vid,'status':status},
                dataType: 'json',
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
                       /* Ewin.alert('提交数据成功');*/
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


});