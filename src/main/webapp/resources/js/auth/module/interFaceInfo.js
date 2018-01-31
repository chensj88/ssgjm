/**
 * 第三方接口类型信息js
 * @author chensj
 * @version 1.0.0
 */
$(function () {
    new Page();
}); 

function Page() {
    _self = this;
    this.init();
}

Page.prototype.init = function () {
    this.editObj = null;
    this.status = {};
    this.sex = {};
    this.userType = {};
    this.initDataGrid();
    this.bindEvent();
//    this.validateForm();
}
/**
 * 初始化Table
 */
Page.prototype.initDataGrid = function () {
    $('#interFaceInfoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/thirx/list.do',// 要请求数据的文件路径
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
        showColumns: true,                  // 是否显示所有的列（选择显示的列）
        showRefresh: true,                  // 是否显示刷新按钮
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
        queryParams: function (params) {
            // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                count: params.limit,    // 每页显示条数
                first: params.offset,   // 显示条数
                sort: params.sort,      // 排序列名
                order: params.order     // 排位命令（desc，asc）
            };
            return temp;
        },

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
            field: "interName",
            title: "接口名称",
            width: '35px',
            align: 'center'
        }, {
            field: "interCode",
            title: "接口代码",
            width: '35px',
            align: 'center'
        }, {
            field: "refProductName",
            title: "适用产品名称",
            width: '30px',
            align: 'center'
        }, {
            field: "interDesc",
            title: "接口描述",
            width: '45px',
            align: 'center'
        },
        {
            field: "lastUpdateTime",
            title: "维护时间",
            width: '40px',
            align: 'center'
        },{
            field: "status",
            title: "状态",
            width: '20px',
            formatter: function (value) {
                if (value == '1') {
                    return '生效';
                } else if (value = '0') {
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
                var e = "<a  class='btn btn-info btn-xs' onclick=edit('"+ row.id +"','"+row.interName +"','"+row.interCode +"','"+row.refProductName + "','"+row.interDesc+"') >编辑</a> ";
                var d = '<a href="####" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '">删除</a> ';
                return e + d ;
            }
        }],
    });
}

function edit(id,interName,interCode,refProductName,interDesc) {
    $('#interName').val(interName);
    $('#interCode').val(interCode);
    $('#refProductName').val(refProductName);
    $('#interDesc').val(interDesc);
    $('#id').val(id);
    $('#interFaceInfoModal').modal('show');
}
/**
 * 按钮绑定事件
 */
Page.prototype.bindEvent = function () {
    /**
     * 新增用户
     * 需要清理表格数据
     */
    $('#addInterFaceInfo').on('click', function () {
        $("input[type=reset]").trigger("click");
        $("#id").val("");
        $('#interName').val("");
        $('#interCode').val("");
        $('#refProductName').val("");
        $('#interDesc').val("");
        $('#interFaceInfoModal').modal('show');
    });
//  

    /**
     * 列表中按钮
     *   删除用户信息
     */
    $('#interFaceInfoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var productInfoId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/thirx/deleteById.do',
                data: {"id": productInfoId},
                dataType: 'json',
                success: function (data, status) {
                    if (status == Common.SUCCESS) {
                        toastr.success('提交数据成功');
                        $("#interFaceInfoTable").bootstrapTable('refresh');
                    }
                },
                error: function () {
                    toastr.error('Error');
                },
                complete: function () {
                }
            });
        });
    });

    /**
     * 保存用户按钮
     * 通过隐藏域判断用户是否存在，而使用不同的方法进行新增或者修改
     */
    $('#save').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
//        var bootstrapValidator = $("#sysDataInfo").data('bootstrapValidator');
//        //修复记忆的组件不验证
//        if (bootstrapValidator) {
//            bootstrapValidator.validate();
//        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/thirx/addInterFaceInfo.do';
        } else {
            url = Common.getRootPath() + '/admin/thirx/update.do';
        }
//        if (bootstrapValidator.isValid()) {
        	console.log($("#interFaceInfoForm").serialize());
            $.ajax({
                url: url,
                data: $("#interFaceInfoForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#interFaceInfoModal').modal('hide');
                        $("#interFaceInfoTable").bootstrapTable('refresh');
                    }

                }
            });
//        }
    });
}
///**
// * 表单验证
// */
//Page.prototype.validateForm = function () {
//    //表单验证
//    //this._changeEvent = (ieVersion === 9 || !('oninput' in el)) ? 'keyup' : 'input'; 源码修改
//    //this._changeEvent = (ieVersion === 9 || !('onblur' in el)) ? 'keyup' : 'blur'; 
//    $('#interFaceInfoForm').bootstrapValidator({
//        message: '输入的值不符合规格',
//        feedbackIcons: {
//            valid: 'glyphicon glyphicon-ok',
//            invalid: 'glyphicon glyphicon-remove',
//            validating: 'glyphicon glyphicon-refresh'
//        },
//        fields: {
//            userid: {
//                message: '登录名验证失败',
//                validators: {
//                    notEmpty: {
//                        message: '登录名不能为空'
//                    },
//                    stringLength: {
//                        min: 2,
//                        max: 18,
//                        message: '登录名长度必须在2到18位之间'
//                    },
//                    regexp: {
//                        regexp: /^[a-zA-Z0-9_]+$/,
//                        message: '登录名只能包含大写、小写、数字和下划线'
//                    }
//                }
//            },
//            yhmc: {
//                validators: {
//                    notEmpty: {
//                        message: '用户名称不能为空'
//                    },
//                    stringLength: {
//                        min: 2,
//                        max: 10,
//                        message: '用户名称长度必须在2到10位之间'
//                    }
//                }
//            },
//            mobile : {
//                validators: {
//                    notEmpty: {
//                        message: '手机号码不能为空'
//                    },
//                    stringLength: {
//                        min: 11,
//                        max: 11,
//                        message: '手机号码长度必须为11位'
//                    },
//                    regexp: {
//                        regexp: /^1[3|4|5|8][0-9]\d{4,8}$/,
//                        message: '手机号码只能包含数字'
//                    }
//                }
//            },
//            email: {
//                validators: {
//                    notEmpty: {
//                        message: '邮箱地址不能为空'
//                    },
//                    emailAddress: {
//                        message: '邮箱地址格式有误'
//                    }
//                }
//            }
//        }
//    });
//}
///**
//* 删除用户
//* 只能删除一条数据
//*/
//$('#deleteProductInfo').on('click', function () {
// var arrselections = $("#userTable").bootstrapTable('getSelections');
// if (arrselections.length > 1) {
//    // toastr.warning('只能选择一行进行编辑');
//     Ewin.alert('只能选择一行进行编辑');
//     return;
// }
// if (arrselections.length <= 0) {
//     //toastr.warning('请选择有效数据');
//     Ewin.alert('只能选择一行进行编辑');
//     return;
// }
// var userId = arrselections[0].userId;
// Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
//     if (!e) {
//         return;
//     }
//     $.ajax({
//         type: "post",
//         url: Common.getRootPath() + '/admin/thirx/deleteById.do',
//         data: {"userId": userId},
//         dataType: 'json',
//         success: function (data, status) {
//             if (status == Common.SUCCESS) {
//                 toastr.success('提交数据成功');
//                 $("#userTable").bootstrapTable('refresh');
//             }
//         },
//         error: function () {
//             toastr.error('Error');
//         },
//         complete: function () {
//         }
//     });
// });
//});
/**
//* 修改用户
//* 只能修改一条数据
//*/
//$('#modifyUser').on('click', function () {
//  var arrselections = $("#userTable").bootstrapTable('getSelections');
//  if (arrselections.length > 1) {
//      //toastr.warning('只能选择一行进行编辑');
//      Ewin.alert('只能选择一行进行编辑');
//      return;
//  }
//  if (arrselections.length <= 0) {
//      //toastr.warning('请选择有效数据');
//      Ewin.alert('请选择有效数据');
//      return;
//  }
//  var userId = arrselections[0].id;
//  $.ajax({
//      url: Common.getRootPath() + '/admin/thirx/getById.do',
//      data: {'userId': userId},
//      type: "post",
//      dataType: 'json',
//      async: false,
//      success: function (result) {
//          var _result = eval(result);
//          if (_result.status == Common.SUCCESS) {
//              $('#userForm').initForm(_result.data);
//              $('#orgid').val(_result.data.orgid);
//              $('#password').val(_result.data.password);
//              $('#cpfl').val(_result.data.password);
//              $('#syfw').val(_result.data.password);
//              $('#sysDataInfo').modal('show');
//          }
//
//      }
//  });
//});