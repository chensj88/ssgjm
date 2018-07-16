/**
 * 功能信息js
 * @author chensj
 * @version 1.0.0
 */

$(function () {
    toastr.options.positionClass = 'toast-top-center';
    toastr.options.timeOut = 30;
    toastr.options.extendedTimeOut = 60;
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            modName :$.trim($('#modQName').val())
        };
    }

    function SearchData(){
        $('#infoTable').bootstrapTable('refresh', { pageNumber: 1 });
    }
    function initTreeView() {
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/func/tree.do",
            dataType: "json",
            data:{'funName':$('#funName').val()},
            cache : false,
            async: false,
            success: function (result) {
                $('#tree').treeview({
                    data: result.data,         // 数据源
                    showCheckbox: true,   //是否显示复选框
                    highlightSelected: true,    //是否高亮选中
                    icon:'',                                  //列表树节点上的图标，通常是节点左边的图标。
                    uncheckedIcon:"",                         //设置图标为未选择状态的checkbox图标。
                    nodeIcon:'glyphicon glyphicon-unchecked', //设置所有列表树节点上的默认图标。
                    selectedIcon:"glyphicon glyphicon-check", //设置所有被选择的节点上的默认图标。
                    color:"#000000",
                    backColor:"#FFFFFF",
                    emptyIcon: '',    //设置列表树中没有子节点的节点的图标。
                    multiSelect: true      //多选

                });
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });

    }

    function validataForm() {
        $('#moduleForm').bootstrapValidator({
            live: 'enabled',
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                modName: {
                    message: '模块名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '模块名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '模块名称长度必须在2到18位之间'
                        },
                        regexp: {
                            regexp: /^[\u0391-\uFFE5A-Za-z0-9]+$/,
                            message: '模块名称只能包含汉字、大写、小写、数字和下划线'
                        }
                    }
                },
                modDesc: {
                    message: '模块说明验证失败',
                    validators: {
                        notEmpty: {
                            message: '模块说明不能为空'
                        }
                    }
                },
                iconPath :{
                    message: '模块图标验证失败',
                    validators: {
                        notEmpty: {
                            message: '模块图标不能为空'
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

    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/module/list.do',// 要请求数据的文件路径
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
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        idField: 'id',
        sortName: 'id',
        uniqueId: "id",                 // 每一行的唯一标识，一般为主键列
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbar: '#modulebtntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        selectItemName: '单选框',
        singleSelect: true,
        queryParams: queryParams,
        columns: [{
            field: "modName",
            title: "模块名称",
            width: '40px',
            align: 'center'
        },{
            field: "modDesc",
            title: "模块说明",
            width: '50px',
            align: 'center'
        },  {
            field: "modPName",
            title: "上级模块名称",
            width: '20px',
            align: 'center'
        }, {
            field: "modLevel",
            title: "模块等级",
            width: '15px',
            align: 'center',
            formatter: function (value) {
                if (value == '1') {
                    return '一级模块';
                } else if (value == '2') {
                    return '二级模块';
                } else {
                    return '三级模块';
                }
            }

        }, {
            field: "modUrl",
            title: "链接地址",
            width: '40px',
            align: 'center'
        }, {
            field: "iconPath",
            title: "模块图标",
            width: '33px',
            align: 'center'
        }, {
            field: "isLeaf",
            title: "叶子节点",
            width: '20px',
            align: 'center',
            formatter: function (value) {
                if (value == '1') {
                    return '是';
                } else {
                    return '否';
                }
            }
        }, {
            field: "isManager",
            title: "管理员功能",
            width: '20px',
            align: 'center',
            formatter: function (value) {
                if (value == '1') {
                    return '是';
                } else {
                    return '否';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '38px',
            formatter: function (value, row, index) {
                var e = '<a href="####" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.modId + '">编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.modId + '">删除</a> ';
                var f = '<a href="####" class="btn btn-success btn-xs" name="tree" mce_href="#" aid="' + row.modId + '">配置功能</a> ';
                return e + d + f;
            }
        },],
    });

    $('.fixed-table-body').removeAttr('height');
    
    $('#query').on('click',SearchData);

    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#modId').val('');
        $('#modLevel').val('1');
        $('#isLeaf').val('1');
        $('#pModule').hide();
        $('#moduleForm').bootstrapValidator('destroy');
        validataForm();
        $('#moduleModal').modal('show');
    });

    $('#modLevel').on('change',function () {
       var selVal = $(this).val();
       if(selVal == '1'){
           $('#pModule').hide();
       }else{
           $('#pModule').show();
       }
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
    /**
     * 列表中按钮
     *   编辑用户信息
     */
    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#moduleForm').bootstrapValidator('destroy');
        validataForm();
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
                    if(_result.data.modLevel == '2'){
                        $('#pModule').show();
                    }else{
                        $('#pModule').hide();
                    }
                    $('#moduleModal').modal('show');
                }
            }
        });
    });
    /**
     * 列表中按钮
     *   删除用户信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
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


    $('#infoTable').on('click', 'a[name="tree"]', function (e) {
        e.preventDefault();
        var modId = $(this).attr('aid');
        initTreeView();
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/moduleFun/query.do",
            dataType: "json",
            data:{'modId':modId},
            cache : false,
            async: false,
            success: function (result) {
                if(result.status == Common.SUCCESS){
                    $('#modIdQ').val(modId);
                    var data = result.data;
                    var enableNode = $('#tree').treeview('getEnabled');
                    $.each(data,function (index,value,array) {
                        $.each(enableNode,function (eindex,evalue,earray) {
                            if(value == evalue.id){
                                $('#tree').treeview('selectNode',
                                    [ evalue.nodeId, { silent: true }]);
                            }
                        })
                    });
                }
            }
        });
        $('#treeModal').modal('show');
    });


    $('#savemodule').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#moduleForm").data('bootstrapValidator');
        //修复记忆的组件不验证
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

    $('#queryFun').on('click',initTreeView);

    $('#saveRoleModule').on('click',function (e) {
        //阻止默认行为
        e.preventDefault();
        var selectNode = $('#tree').treeview('getSelected');
        var nodeLength = selectNode.length;
        console.log(selectNode);
        var moduleId = $('#modIdQ').val();
        var moduleFun = '';
        if(nodeLength > 0){
            $.each(selectNode,function (index,value,array) {
                if(index == nodeLength -1 ){
                    moduleFun += moduleId +','+value.id;
                }else{
                    moduleFun += moduleId +','+value.id +';';
                }
            });
            $.ajax({
                url: Common.getRootPath() + '/admin/moduleFun/add.do',
                data: {'idList':moduleFun},
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $('#treeModal').modal('hide');
                    }
                },
                error :function (msg) {
                    toastr.error(msg);
                    console.log(msg);
                }
            });
        }else{
            $.ajax({
                url: Common.getRootPath() + '/admin/moduleFun/delete.do',
                data: {'modId':moduleId},
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $('#treeModal').modal('hide');
                    }
                },
                error :function (msg) {
                    toastr.error(msg);
                    console.log(msg);
                }
            });
        }

    });
});
