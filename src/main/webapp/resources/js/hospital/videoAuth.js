/**
 * @title 视频权限
 * @author chenshijie
 * @email chensj@winning.com.cm
 * @date  2018-04-20 8:51
 */
$(function () {

    let objMap = {};
    $('#uploadForm').hide();
    /**
     * 客户姓名
     */
    $('#serialName').typeahead({
        source : function (query,process) {
            let matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/train/queryCustomerName.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'name':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    let _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        let data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        let results = [];
                        for (let i = 0; i < data.length; i++) {
                            objMap[data[i].name] = data[i].name + ',' + data[i].id + ',' +data[i].code;
                            results.push(data[i].name);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[2] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            let selectItem = objMap[item];
            let selectItemId = selectItem.split(',')[1];
            let selectItemCode = selectItem.split(',')[2];
            $('#serialId').val(selectItemId);
            $('#serialNo').val(selectItemCode);

        },
        items : 10,
    });

    /**
     * 已配置权限用户信息
     * @param params
     * @returns {{count: (*|number), first, sort, order, ssgs, userType: string, status: number}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            ssgs: $.trim($('#serialId').val()),
            userType:'0',
            status:1
        };
    }

    /**
     * 上传用户配置信息
     * @param params
     * @returns {{count: (*|number), first, sort, order, serialNo}}
     */
    function queryUploadParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            serialNo: $.trim($('#serialId').val())
        };
    }

    function initUserBootstrapTable() {
        $('#btntoolbar').hide();
        $('#infoTable').bootstrapTable("destroy").bootstrapTable({
            url: Common.getRootPath() + '/admin/user/list.do',// 要请求数据的文件路径
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
            toolbar: '#btntoolbar',
            toolbarAlign: 'right',
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            paginationLoop: false, //分页条无限循环的功能
            singleSelect: true,
            selectItemName: '单选框',
            queryParams: queryParams,  // 得到查询的参数
            columns: [{
                field: "map.orgName",
                title: "客户名称",
                width: '60px',
                align: 'center'
            },{
                field: "userid",
                title: "工号",
                width: '20px',
                align: 'center'
            }, {
                field: "yhmc",
                title: "用户姓名",
                width: '20px',
                align: 'center'
            }, {
                field: "name",
                title: "姓名",
                width: '20px',
                align: 'center'
            },  {
                field: "clo4",
                title: "视频权限",
                width: '80px',
                align: 'center'
            }, {
                field: "status",
                title: "状态",
                width: '20px',
                align: 'center',
                formatter:function (value) {
                    if(value == '1'){
                        return '生效';
                    }else{
                        return '失效';
                    }
                }
            }],
        });
    }

    function initUploadUserBootstrapTable() {
        $('#btntoolbar').show();
        $('#infoTable').bootstrapTable("destroy").bootstrapTable({
            url: Common.getRootPath() + '/admin/videoAuth/list.do',// 要请求数据的文件路径
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
            toolbar: '#btntoolbar',
            toolbarAlign: 'right',
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            paginationLoop: false, //分页条无限循环的功能
            singleSelect: true,
            selectItemName: '单选框',
            queryParams: queryUploadParams,  // 得到查询的参数
            columns: [{
                field: "map.orgName",
                title: "客户名称",
                width: '60px',
                align: 'center'
            },{
                field: "userCode",
                title: "工号",
                width: '20px',
                align: 'center'
            },{
                field: "suserCode",
                title: "系统工号",
                width: '30px',
                align: 'center'
            }, {
                field: "userName",
                title: "姓名",
                width: '20px',
                align: 'center'
            },  {
                field: "menuName",
                title: "视频权限",
                width: '80px',
                align: 'center'
            }, {
                field: "isExist",
                title: "用户是否存在",
                width: '50px',
                align: 'center',
                formatter:function (value) {
                    if(value == '0'){
                        return '否';
                    }else{
                        return '是';
                    }
                }
            }],
        });
    }

    function checkSerialNo() {
        let serialNo =  $('#serialId').val();
        if(!serialNo){
            Ewin.alert('请先选择客户');
            return false;
        }
        return true;
    }
    $('#queryCustomer').on('click',function () {
        let status = checkSerialNo();
        if(status){
            initUserBootstrapTable();
        }else{
            return ;
        }
    });


    function initFileInput() {
        $('#uploadFile').fileinput({
            language: "zh",//配置语言
            uploadUrl: Common.getRootPath() +"/admin/upload/videoAuth.do",
            showUpload : true,
            showRemove : true,
            showPreview : false,
            showCaption: true,//是否显示标题
            uploadAsync: true,
            dropZoneEnabled:false,
            uploadLabel: "上传",//设置上传按钮的汉字
            uploadClass: "btn btn-primary",//设置上传按钮样式
            maxFileSize : 0,
            maxFileCount: 1,/*允许最大上传数，可以多个，当前设置单个*/
            enctype: 'multipart/form-data',
            allowedFileExtensions : ["doc", "docx","xls","xlsx"],/*上传文件格式*/
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            showBrowse: false,
            browseOnZoneClick: true,
            uploadExtraData:function (previewId, index) {
                return {'serialNo':$('#serialId').val()};
            },
            slugCallback : function(filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        }).on("filebatchselected",function(event, files){
        }).on('filepreupload', function(event, data, previewId, index) {     //上传中
            Ewin.alert('数据正在入库，请勿执行其他操作，等待上传完成。');
        }).on('fileuploaded',function(event, data, previewId, index){    //一个文件上传成功
            var _data = data.response;
            if(_data.status == Common.SUCCESS){
                $('#uploadForm').hide();
                Ewin.alert('数据入库完成，点击查询上传结果可以查询数据。');
                initUploadUserBootstrapTable();
            }else{
                Ewin.alert(_data.msg)
            }
        });
    }

    $('#upload').on('click',function () {
        initFileInput();
        var status = checkSerialNo();
        if(status){
            $('#uploadForm').show();
        }else{
            return ;
        }

    });

    $('#queryUpload').on('click',function () {
        initFileInput();
        var status = checkSerialNo();
        if(status){
            initUploadUserBootstrapTable();
        }else{
            return ;
        }
    });

    $('#closeForm').on('click',function () {
        $('#uploadForm').hide();
    });

    $('#importAuth').on('click',function () {
        Ewin.confirm({message: "本操作将导入系统中<span style='color: red;font-style:inherit;font-weight:bold;'>已经存在的用户</span>的视频权限信息。<br><span style='font-weight:bold;'>未导入系统的用户，请先导入用户。</span>"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/videoAuth/modify.do',
                data: {"serialNo": $('#serialId').val() },
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        Ewin.alert('数据导入成功');
                        initUserBootstrapTable();
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