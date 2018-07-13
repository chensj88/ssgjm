/**
 * 流程问题信息
 * chensj
 * 2018-02-22
 */
function validateForm() {
    $('#flowQuestionForm').bootstrapValidator({
        message: '输入的值不符合规格',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            quesName: {
                message: '问题名称验证失败',
                validators: {
                    notEmpty: {
                        message: '问题名称不能为空'
                    }
                }
            },
            resultNum : {
                message: '答案数验证失败',
                validators: {
                    notEmpty: {
                        message: '答案数不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message : '答案数只能输入数字'
                    }
                }
            },
            quesDesc : {
                message: '问题描述验证失败',
                validators: {
                    notEmpty: {
                        message: '问题描述不能为空'
                    }
                }
            },
        }
    });
}


function editFQ(id,flowId,flowName,flowCode,quesCode,quesName,quesDesc,quesType,resultNum,status){
    $('#id').val(id);
    $('#flowId').val(flowId);
    $('#flowName').val(flowName);
    $('#flowCode').val(flowCode);
    $('#quesCode').val(quesCode);
    $('#quesName').val(quesName);
    $('#quesDesc').val(quesDesc);
    $('#quesType').val(quesType);
    $('#resultNum').val(resultNum);
    $('#status').val(status);
    $('#flowQuestionForm').bootstrapValidator("destroy");
    validateForm();
    $('#flowQuestionModal').modal('show');
}

function addA(type,ele,event){
    event.preventDefault();
    var $ele = $(ele);
    var eP = $ele.parent().parent();
    var pre = eP.prev();
    var span = '';
    var atype = type;
    if(type == '0'){
        span = '答案';
    }else if(type == '1'){
        span = '其它';
    }
    var content = "<div class=\"form-group\" qtype=\""+atype+"\"><label class=\"col-xs-2 control-label\">"+span+"</label><div class=\"col-sm-6\"><input  class=\"form-control\"></div> <span class=\"glyphicon glyphicon-remove\"  aria-hidden=\"true\"></span></div>";
    pre.append(content);
}

function resolveAnswer(type,info){
    var span = '';
    if(type == '0'){
        span = '答案';
    }else if(type == '1'){
        span = '其它';
    }
    var content = "<div class=\"form-group\" qtype=\""+type+"\"><label class=\"col-xs-2 control-label\">"+span+"</label><div class=\"col-sm-6\"><input  class=\"form-control\" value=\""+info+"\"></div> <span class=\"glyphicon glyphicon-remove\"  aria-hidden=\"true\"></span></div>";
    $('#answer_container').append(content);
}

$(function () {
    $.fn.typeahead.Constructor.prototype.blur = function () {
        var that = this;
        setTimeout(function () { that.hide() }, 250);
    };
    var objMap = {};//定义一个空的js对象

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
            flowId: $.trim($('#flowQId').val()).toUpperCase(),
            quesName: $.trim($('#quesQName').val()),
            quesCode: $.trim($('#quesQCode').val()).toUpperCase()
        };
    }

    /**
     * 初始化Table
     */
    $('#infoTable').bootstrapTable({
        url: Common.getRootPath() + '/admin/fq/list.do',// 要请求数据的文件路径
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
        showColumns: false,                  // 是否显示所有的列（选择显示的列）
        showRefresh: false,                  // 是否显示刷新按钮
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
        // 得到查询的参数
        queryParams: queryParams,
        columns: [ {
            field: "id",
            title: "序号",
            width: '20px',
            align: 'center'
        }, {
            field: "quesCode",
            title: "问题编号",
            width: '35px',
            align: 'center'
        }, {
            field: "quesName",
            title: "问题名称",
            width: '60px',
            align: 'center'
        },  {
            field: "quesType",
            title: "问题类型",
            width: '23px',
            align: 'center',
            formatter :function (value) {
                if(value == '0'){
                    return '单选';
                }else  if(value == '1'){
                    return '复选';
                }else  if(value == '2'){
                    return '联动单选';
                }else  if(value == '3'){
                    return '联动复选';
                }else  if(value == '4'){
                    return '问答';
                }
            }
        }, {
            field: "flowName",
            title: "流程名称",
            width: '60px',
            align: 'center'
        }, {
            field: "resultNum",
            title: "答案数",
            width: '18px',
            align: 'center'
        }, {
            field: "quesDesc",
            title: "问题描述",
            width: '60px',
            align: 'center'
        }, {
            field: "status",
            title: "状态",
            width: '14px',
            align: 'center',
            formatter :function (value) {
                if (value == '0') {
                    return '失效';
                } else if (value == '1') {
                    return '生效';
                }
            }
        }, {
            title: '操作',
            field: 'id',
            align: 'center',
            width: '80px',
            formatter: function (value, row, index) {
                var e = '<a href="#" class="btn btn-success btn-xs" name="edit" onclick="editFQ(\''+row.id+'\',\''+row.flowId+'\',\''+row.flowName+'\',\''+row.flowCode+'\',\''+row.quesCode+'\',\''+row.quesName+'\',\''+row.quesDesc+'\',\''+row.quesType+'\',\''+row.resultNum+'\',\''+row.status+'\')" ><span class="glyphicon glyphicon-edit"></span>编辑问题</a> ';
                var d = '<a href="#" class="btn btn-danger btn-xs" name="delete" mce_href="#" aid="' + row.id + '"><span class="glyphicon glyphicon-remove"></span>删除问题</a> ';
                var rowval = row.id + ',' +row.resultNum;
                var f = '<a href="#" class="btn btn-info btn-xs" name="view" mce_href="#" aid="' + rowval + '"><span class="glyphicon glyphicon-open"></span>查看答案</a> ';
                return e + f + d;
            }
        }],
    });

    /**
     * 新增流程
     * 需要清理表格数据
     */
    $('#add').on('click', function () {
        $("input[type=reset]").trigger("click");
        $('#id').val('');
        //清空验证信息
        $('#flowQuestionForm').bootstrapValidator("destroy");
        validateForm();
        $('#flowQuestionModal').modal('show');
    });
    /**
     * 列表中按钮
     *   编辑流程信息
     */
    $('#infoTable').on('click', 'a[name="view"]', function (e) {
        e.preventDefault();
        var quesId = $(this).attr('aid').split(',')[0];
        var resultNum = $(this).attr('aid').split(',')[1];
        var rNum = parseInt(resultNum);
        $('#quesId').val(quesId);
        $('#resultAns').val(resultNum);
        $.ajax({
            url: Common.getRootPath() +'/admin/fa/findByFQId.do',
            data: {'quesId':quesId},
            type: "post",
            dataType: 'json',
            async: false,
            cache : false,
            success: function (result) {
                var _result = eval(result);
                if (_result.status == Common.SUCCESS) {
                    $('#answer_container').empty();
                    var total = _result.total;
                    var data = _result.data;
                    if(rNum > total ){
                        for(var i=0;i<total;i++){
                            var item = data[i];
                            resolveAnswer(item.answerType,item.answerContent);
                        }
                        for(var i=0;i<rNum - total;i++){
                            resolveAnswer('0','');
                        }
                    }else {
                        for(var i=0;i<total;i++){
                            var item = data[i];
                            resolveAnswer(item.answerType,item.answerContent);
                        }
                    }
                    $('#answerModal').modal('show');
                }
            }
        });


    });

    /**
     * 删除图标绑定事件
     */
    $('body').on('click','#answerModal .form-group span',function(){
       var removeFlag = $(this);
       //console.log(removeFlag);
       removeFlag.parent().remove();
    });
    /**
     * 列表中按钮
     *   删除流程信息
     */
    $('#infoTable').on('click', 'a[name="delete"]', function (e) {
        e.preventDefault();
        var fqId = $(this).attr('aid');
        Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                type: "post",
                url: Common.getRootPath() + '/admin/fq/deleteById.do',
                data: {'id': fqId},
                dataType: 'json',
                cache : false,
                success: function (data, status) {
                    var result = eval(data);
                    if (result.status == Common.SUCCESS) {
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

    /**
     * 保存流程按钮
     * 通过隐藏域判断流程是否存在，而使用不同的方法进行新增或者修改
     */
    $('#saveQ').on('click', function (e) {
        //阻止默认行为
        e.preventDefault();
        var bootstrapValidator = $("#flowQuestionForm").data('bootstrapValidator');
        //修复记忆的组件不验证
        if (bootstrapValidator) {
            bootstrapValidator.validate();
        }
        var url = '';
        if ($('#id').val().length == 0) {
            url = Common.getRootPath() + '/admin/fq/add.do';
        } else {
            url = Common.getRootPath() + '/admin/fq/update.do';
        }
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: url,
                data: $("#flowQuestionForm").serialize(),
                type: "post",
                dataType: 'json',
                async: false,
                cache : false,
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        $('#flowQuestionModal').modal('hide');
                        $("#infoTable").bootstrapTable('refresh');
                    }
                }
            });
        }
    });

    $('#saveA').on('click', function (e) {
       var answerArr = $('#answer_container').find('.form-group');
       var msg = '';
       var info = '';
       var quesId = $('#quesId').val();
       var resultNum = parseInt($('#resultAns').val());
       if(resultNum > answerArr.length){
           Ewin.alert('当前答案数量比题目设定答案数少');
           return ;
       }else if(resultNum < answerArr.length ){
           Ewin.alert('当前答案数量比题目设定答案数多');
           return ;
       }
       for(var i=0;i<answerArr.length;i++){
           var item = answerArr[i];
           var type = $(item).attr('qtype');
           var input = $($(item).find('.form-control')[0]).val();
           if(input == ''){
               msg += '答案不能为空';
               Ewin.alert(msg);
               return;
           }
           if(i == answerArr.length -1){
               info += quesId +',' + type + ',' +input ;
           }else{
               info += quesId +',' + type + ',' +input+';' ;
           }
       }

       $.ajax({
           type: "post",
           url: Common.getRootPath() + '/admin/fa/add.do',
           data: {'info': info,'quesId':quesId},
           dataType: 'json',
           cache : false,
           success: function (data, status) {
               var result = eval(data);
               if (result.status == Common.SUCCESS) {
                   $('#answerModal').modal('hide');
               }
           },
           error: function (response) {
               toastr.error(response.responseText);
           }
       });
    });
    /**=================自动补全====================*/
    //查询栏--流程名称模糊查询
    $('#flowQName').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/fq/queryFlowName.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowName':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowName] = data[i].flowName + ',' + data[i].id + ',' +data[i].flowCode;
                            results.push(data[i].flowName);
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
            var selectItem = objMap[item];
            var selectItemId = selectItem.split(',')[1];
            var selectItemCode = selectItem.split(',')[2];
            $('#flowQId').val(selectItemId);
            $('#flowQCode').val(selectItemCode);
        },
        items : 10,
    });
    //查询栏--流程编号模糊查询
    $('#flowQCode').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/fq/queryFlowCode.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowCode':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowCode] = data[i].flowName + ',' + data[i].id ;
                            results.push(data[i].flowCode);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[0] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#flowQId').val(selectItemId);
            $('#flowQName').val(selectItemName);
        },
        items : 10,
    });
    //模态框--流程编号模糊查询
    $('#flowCode').typeahead({
        source : function (query,process) {
            var matchCount =this.options.items;//允许返回结果集最大数量
            $.ajax({
                url : Common.getRootPath() + '/admin/fq/queryFlowCode.do',
                type: "post",
                dataType: 'json',
                async: false,
                data: {'flowCode':query.toUpperCase(),'matchCount':matchCount},
                success: function (result) {
                    var _result = eval(result);
                    if (_result.status == Common.SUCCESS) {
                        var data = _result.data;
                        if (data == "" || data.length == 0) {
                            console.log("没有查询到相关结果");
                        };
                        var results = [];
                        for (var i = 0; i < data.length; i++) {
                            objMap[data[i].flowCode] = data[i].flowName + ',' + data[i].id ;
                            results.push(data[i].flowCode);
                        }
                        process(results);
                    }
                }
            });
        },
        highlighter: function (item) {
            return item +'['+objMap[item].split(',')[0] + ']';
        },
        afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
            var selectItem = objMap[item];
            var selectItemName = selectItem.split(',')[0];
            var selectItemId = selectItem.split(',')[1];
            $('#flowId').val(selectItemId);
            $('#flowName').val(selectItemName);
        },
        items : 10,
    });


    /**
     * 查询按钮
     */
    $('#query').on('click',SearchData);
});
