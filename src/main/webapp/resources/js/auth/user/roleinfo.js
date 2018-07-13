/**
 * 角色信息js
 * @author chensj
 * @version 1.0.0
 */


// 选中父节点时，选中所有子节点
function getChildNodeIdArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

/**
 * 遍历子节点中被选中的节点
 * @param node
 * @returns {Array}
 */
function getChildNodeIdSelectArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            if (node.nodes[x].state.selected) {
                ts.push(node.nodes[x].nodeId);
            }
        }
    }
    return ts;
}

$(function () {

    /**
     * 角色编辑信息
     * @param data
     */
    function editRole(data) {
        $('#id').val(data.id);
        $('#roleName').val(data.roleName);
        $('#roleDesc').val(data.roleDesc);
        $('#isDel').val(data.isDel);
        $('#isLock').val(data.isLock);
        $('#roleModal').modal('show');
    }
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
     * @returns {{count: *|number, first, sort, order, dbName: string, tableName: *|string, tableCnName: *|string}}
     */
    function queryParams(params) {
        return {
            count: params.limit,    // 每页显示条数
            first: params.offset,   // 显示条数
            sort: params.sort,      // 排序列名
            order: params.order,     // 排位命令（desc，asc）
            roleName: $.trim($('#roleQName').val())
        };
    }

    function validateForm() {
        $('#roleForm').bootstrapValidator({
            message: '输入的值不符合规格',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                roleName: {
                    message: '角色名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '角色名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '角色名称长度必须在2到18位之间'
                        },
                        regexp: {
                            regexp: /^[\u0391-\uFFE5A-Za-z0-9]+$/,
                            message: '角色名称只能包含汉字、大写、小写、数字和下划线'
                        }
                    }
                },
                roleDesc: {
                    message: '角色信息描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '角色信息描述不能为空'
                        }
                    }
                }
            }
        });

    }

    function initTreeView() {
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/module/tree.do",
            dataType: "json",
            data:{'modName':$('#modName').val()},
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
                    multiSelect: true,      //多选
                    onNodeSelected:function (event,node) {
                        var selectNodes = getChildNodeIdArr(node);
                        console.log(selectNodes);
                        console.log(node);
                        if (selectNodes) { //子节点不为空，则选中所有子节点
                            $('#tree').treeview('selectNode', [selectNodes, { silent: true }]);
                        }
                        $('#tree').treeview('selectNode', [node.nodeId, { silent: true }]);
                        $('#tree').treeview('selectNode', [node.parentId, { silent: true }]);
                    },
                    onNodeUnselected:function (event,node) {
                        var selectNodes = getChildNodeIdArr(node);
                        console.log(selectNodes);
                        console.log(node);
                        if (selectNodes) { //子节点不为空，则取消选中所有子节点
                            $('#tree').treeview('unselectNode', [selectNodes, { silent: true }]);
                        }
                        $('#tree').treeview('unselectNode', [node.nodeId, { silent: true }]);
                        var pNode = $('#tree').treeview('getNode', node.parentId);
                        var cNodes = getChildNodeIdSelectArr(pNode);
                        console.log(cNodes);
                        if(cNodes.length == 0){
                            $('#tree').treeview('unselectNode', [node.parentId, { silent: true }]);
                        }
                    }

                });
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });

    }

    function generateTreeGrid(data) {

    }
    
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/role/list.do',// 要请求数据的文件路径
        method: 'GET', // 请求方法<b class="arrow icon-angle-down"></b>
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
        //showToggle: true,                   // 是否显示详细视图和列表视图的切换按钮
        cardView: false,                    // 是否显示详细视图
        detailView: false,                  // 是否显示父子表
        toolbar: '#rolebtntoolbar',
        toolbarAlign: 'right',
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        paginationLoop: false, //分页条无限循环的功能
        selectItemName: '单选框',
        singleSelect: true,
        queryParams:queryParams,
        columns: [{
            field: "id",
            title: "序号",
            width: '40px',
            align: 'center'
        }, {
            field: "roleName",
            title: "角色名称",
            width: '40px',
            align: 'center'
        }, {
            field: "roleDesc",
            title: "角色描述",
            width: '40px',
            align: 'center'
        },{
            field: "isLock",
            title: "是否锁定",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '否';
                } else {
                    return '是';
                }
            }
        }, {
            field: "isDel",
            title: "是否删除",
            width: '40px',
            align: 'center',
            formatter: function (value) {
                if (value == '0') {
                    return '否';
                } else {
                    return '是';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '60px',
            formatter: function (value, row, index) {
                var e = '<a href="#" class="btn btn-info btn-xs" name="edit" mce_href="#" aid="' + row.id +'" >编辑</a> ';
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '" aname="' + row.roleName + '">删除</a> ';
                var f = '<a href="####" class="btn btn-success btn-xs" name="tree" mce_href="#" aid="' + row.id + '">配置菜单</a> ';
                var g = '<a href="####" class="btn btn-success btn-xs" name="button" mce_href="#" aid="' + row.id + '" aname="' + row.roleName + '">配置按钮</a> ';
                return e + d + f + g;
            }
        },],
    });
    /**
     * 新增用户
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#roleForm').bootstrapValidator("destroy");
        validateForm();
        $('#roleId').val('');
        $('#roleModal').modal('show');
    });

    $('#infoTable').on('click', 'a[name="edit"]', function (e) {
        e.preventDefault();
        $('#roleForm').bootstrapValidator('destroy');
        validateForm();
        var roleId = $(this).attr('aid');
        $.ajax({
            url: Common.getRootPath() + '/admin/role/getById.do',
            data: {'id': roleId},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                var data = _result.data;
                if (_result.status == Common.SUCCESS) {
                    editRole(data);
                }
            }
        });
    });
    /**
     * 列表中按钮
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var roleId = $(this).attr('aid');
        var roleName = $(this).attr('aname');
        Ewin.confirm({message: "确认要删除["+roleName+"]的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/role/deleteById.do',
                data: {"id": roleId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        Ewin.alert('提交数据成功');
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
        var roleId = $(this).attr('aid');
        initTreeView();
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/rolemodule/query.do",
            dataType: "json",
            data:{'roleId':roleId},
            cache : false,
            async: false,
            success: function (result) {
                if(result.status == Common.SUCCESS){
                    $('#roleIdQ').val(roleId);
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

    function configButtonTable(roleId) {
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/rolemodule/queryRolePopedom.do",
            dataType: "json",
            data:{'roleId':roleId},
            cache : false,
            async: false,
            success: function (result) {
                if(result.status == Common.SUCCESS) {
                    var data = result.data;
                    $.each(data, function (index, value, array) {
                        var aid = value.id +":" + value.modId;
                        var popedomCode = value.popedomCode.split(",");
                        $.each(popedomCode,function (ii,v) {
                          var e = $('input[aid=\''+aid+'\']');
                          for(var i=0;i<e.length;i++){
                              if($(e[i]).val() == v){
                                  $(e[i]).attr("checked",true);
                              }
                          }
                        });
                    });
                }
            }
        });
    }

    $('#infoTable').on('click', 'a[name="button"]', function (e) {
        e.preventDefault();
        var roleId = $(this).attr('aid');
        var roleName = $(this).attr('aname');
        $.ajax({
            type: "post",
            url: Common.getRootPath() + "/admin/rolemodule/queryBtn.do",
            dataType: "json",
            data:{'roleId':roleId},
            cache : false,
            async: false,
            success: function (result) {
                if(result.status == Common.SUCCESS){
                    $('#roleQId').val(roleId);
                    if(result.data.length <= 0){
                        Ewin.alert('角色['+roleName+']配置的菜单中<span style="background-color: red;">没有</span>需要配置的按钮');
                    }else{
                        $('#gridModalLabel').text(roleName+'按钮信息');
                        initModuleButton(result.data);
                        configButtonTable(roleId);
                        $('#gridModal').modal('show');
                    }
                }
            }
        });


    });

    /**
     * 保存按钮
     * 通过隐藏域判断用户是否存在，而使用不同的方法进行新增或者修改
     */
    $('#saveRole').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#roleForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/role/add.do';
        } else {
            url = Common.getRootPath() + '/admin/role/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#roleForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#roleModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                },
            });
        }
    });

    /**
     * 查询按钮
     */
    $('#query').on('click',SearchData);
    /**
     * 保存角色模块信息
     */
    $('#saveRoleModule').on('click',function (e) {
        //阻止默认行为
        e.preventDefault();
        var selectNode = $('#tree').treeview('getSelected');
        var nodeLength = selectNode.length;
        console.log(selectNode);
        var roleId = $('#roleIdQ').val();
        var roleModule = '';
        if(nodeLength > 0){
            $.each(selectNode,function (index,value,array) {
                if(index == nodeLength -1 ){
                    roleModule += roleId +','+value.id;
                }else{
                    roleModule += roleId +','+value.id +';';
                }
            });
            $.ajax({
                url: Common.getRootPath() + '/admin/rolemodule/add.do',
                data: {'idList':roleModule},
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#treeModal').modal('hide');
                    }
                },
                error :function (msg) {
                    alert(msg.statusText);
                    console.log(msg);
                }
            });
        }else {
            $.ajax({
                url: Common.getRootPath() + '/admin/rolemodule/delete.do',
                data: {'roleId': roleId},
                type: "post",
                dataType: 'json',
                async: false,
                cache: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#treeModal').modal('hide');
                    }
                },
                error: function (response) {
                    toastr.error(response.responseText);
                },
            });
        }
    });

    /**
     * 生成一级菜单
     * @param data
     */
    function initModuleButton(data) {
        $('#gridTable').bootstrapTable("destroy").bootstrapTable({
            data: data,
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
            toolbarAlign: 'right',
            paginationLoop: false, //分页条无限循环的功能
            singleSelect: false,
            columns: [{
                field: "text",
                title: "菜单信息",
                width: '55px',
                align: 'center'
            }, {
                field: "funInfo",
                title: "按钮信息",
                width: '45px',
                align: 'center',
                formatter: generateMenuButton
            }],
        });
    }

    function generateMenuButton(value,row) {
        var funArr = value.split(';');
        var modId = row.id +':' + row.nodeId;
        var content = "";
        for( var i=0;i<funArr.length - 1;i++){
            var btn = funArr[i];
            var btnArr = btn.split(":");
            content +="<input type='checkbox' aid="+modId+" value='"+btnArr[0]+"'>"+btnArr[1]+"";
        }
        return content;
    }

    $('#saveP').on('click',function (e) {
        e.preventDefault();
        var checkedArr = $('#gridTable').find('input[type=checkbox]:checked');
        var roleId = $('#roleQId').val();
        var btnMap = {};
        $.each(checkedArr,function (index,value,array) {
            var mid = $(value).attr('aid');
            var val = $(value).val();
            if(btnMap[mid]){
                btnMap[mid] += val +',';
            }else{
                btnMap[mid] = val +',';
            }
        });

        var idList = '';
        for( var key in btnMap){
            var value = Common.substr(btnMap[key],',');
            idList += key +':' +roleId +':' + value +';';
        }
        idList = Common.substr(idList,';')
        console.log(idList);
        $.ajax({
            url: Common.getRootPath() + '/admin/rolemodule/addPopedom.do',
            data: {"idList":idList},
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#gridModal').modal('hide');
                    $("#infoTable").bootstrapTable('refresh');
                }
            },
            error: function (response) {
                toastr.error(response.responseText);
            },
        });
    })
});
