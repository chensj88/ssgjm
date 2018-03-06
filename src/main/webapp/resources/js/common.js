(function (window, undefined) {
    var Common = {};
    window.Common = Common;

    /** 码表缓存，TOP窗口中有效。 */
    Common.codes = {};
    /** 码表类型常量 */
    /** 用户类型 */
    Common.CODE_ID_USER_TYPE = 'userType';
    /** 用户状态 */
    Common.CODE_ID_USER_STATUS = 'userStatus';
    /** 用户性别 */
    Common.CODE_ID_SEX = 'sex';
    /** 角色类型 */
    Common.CODETYPE_ID_ROLE_TYPE = "roleType";
    /** 角色类型 */
    Common.CODETYPE_ID_FUNC_TYPE = "funcType";
    /**成功标志*/
    Common.SUCCESS = "success";

    /**
     * 获取项目根路径
     */
    Common.getRootPath = function () {
        // 获取当前网址
        var curWwwPath = window.document.location.href;
        // 获取主机地址之后的目录
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        // 获取主机地址
        var localhostPaht = curWwwPath.substring(0, pos);
        if (pathName.substr(1).indexOf("ssgj") == -1) { // 无工程名，则直接访问
            return localhostPaht;
        } else {
            // 获取带"/"的项目名
            var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
            return (localhostPaht + projectName);
        }

    };

    /**登录页面*/
    Common.LOGIN_URL = Common.getRootPath() + "/login/login.do";
    /**
     * 获取Controller的路径 根据请求的路径获取来获取路径信息
     */
    Common.getControllerPath = function () {
        var urlPath = window.document.location.pathname;
        var start = urlPath.indexOf("/views/");
        start = -1 == start ? 6 : start + 6;
        var end = urlPath.indexOf(".");

        var controller = urlPath.substring(start, end) + "/";
        return Common.getRootPath() + controller;
    };
    /**
     * 动态设置下拉框的内容
     * @param $parent 父级元素对象
     * @param data 数据
     */
    Common.setSelectOption = function ($parent, data) {
        if (!$parent || !data) {
            return;
        }
//        debugger
        $parent.empty();
        for (var key in data) {
            $('<option value="' + key + '">' + data[key] + '</option>').appendTo($parent);
        }
    };
    /**
     * 设置下拉框选项，数据从码表获取
     *
     * @param type
     *            码表数据类型
     * @param data
     *            选项数据对象
     * @param dropdownBox
     *            下拉框的jQuery对象
     * @param dropdownValue
     *            选项默认值，选填
     */
    Common.getCodes = function (type, data, dropdownBox, dropdownValue) {
        if (top.Common.codes[type]) {
            var codes = top.Common.codes[type];
            for (var i = 0; i < codes.length; i++) {
                data[codes[i].dictValue] = codes[i].dictLabel;
            }
            if (dropdownBox) {
                Common.setSelectOption(dropdownBox, data);
                if (dropdownValue) {
                    var options = dropdownBox.find("option");
                    for (var j = 0; j < options.length; j++) {
                        if ($(options[j]).val() == dropdownValue) {
                            $(options[j]).attr("selected", "selected");
                        }
                    }
                }
            }
            return;
        }
        $.ajax({
            url: Common.getRootPath() + "/admin/dict/getCodes.do",
            data: {
                dictCode: type
            },
            type: "post",
            dataType: 'json',
            async: false,
            success: function (result) {
                var codeInfo = result.data;
                top.Common.codes[type] = type;
                for (var i = 0; i < codeInfo.length; i++) {
                    data[codeInfo[i].dictValue] = codeInfo[i].dictLabel;
                }
                if (dropdownBox) {
                    Common.setSelectOption(dropdownBox, data);
                    if (dropdownValue) {
                        var options = dropdownBox.find("option");
                        for (var j = 0; j < options.length; j++) {
                            if ($(options[j]).val() == dropdownValue) {
                                $(options[j]).attr("selected", "selected");
                            }
                        }
                    }
                }
            }
        });
    };

    Common.getHHMMSSDate = function (longTime) {
        var date = new Date();
        date.setTime(longTime);
        var dateType = "";
        dateType += "  " + getHours(date); // 时
        dateType += ":" + getMinutes(date); // 分
        dateType += ":" + getSeconds(date); // 分
        return dateType;
    }
    Common.getDate = function (longTime) {
        var date = new Date();
        date.setTime(longTime);
        return Common.getDateTime(date);
    }
    Common.getDateTime = function (date) {
        var dateType = "";
        dateType += date.getFullYear(); // 年
        dateType += "-" + getMonth(date); // 月
        dateType += "-" + getDay(date); // 日
        dateType += "  " + getHours(date); // 时
        dateType += ":" + getMinutes(date); // 分
        dateType += ":" + getSeconds(date); // 分
        return dateType;
    };

    // 返回 01-12 的月份值
    function getMonth(date) {
        var month = "";
        month = date.getMonth() + 1; // getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    // 返回01-30的日期
    function getDay(date) {
        var day = "";
        day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

    // 返回小时
    function getHours(date) {
        var hours = "";
        hours = date.getHours()-8;
        if (hours < 10) {
            hours = "0" + hours;
        }
        return hours;
    }

    // 返回分
    function getMinutes(date) {
        var minute = "";
        minute = date.getMinutes();
        if (minute < 10) {
            minute = "0" + minute;
        }
        return minute;
    }

    // 返回秒
    function getSeconds(date) {
        var second = "";
        second = date.getSeconds();
        if (second < 10) {
            second = "0" + second;
        }
        return second;
    }
})(window);
(function ($) {
        $.fn.extend({
            //给表单设置值
            initForm: function (options) {
                // 默认参数
                var defaults = {
                    jsonValue: options,
                    isDebug: false
                    // 是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name
                    // value打印出来
                };

                // 设置参数
                var setting = defaults;
                var form = this;
                jsonValue = setting.jsonValue;
                // 如果传入的json字符串，将转为json对象
                if ($.type(setting.jsonValue) === "string") {
                    jsonValue = $.parseJSON(jsonValue);
                }
                // 如果传入的json对象为空，则不做任何操作
                if (!$.isEmptyObject(jsonValue)) {
                    var debugInfo = "";
                    $.each(jsonValue,
                        function (key, value) {
                            // 是否开启调试，开启将会把name value打印出来
                            if (setting.isDebug) {
                                alert("name:" + key + "; value:" + value);
                                debugInfo += "name:" + key + "; value:" + value + " || ";
                            }
                            var formField = form.find("[name='" + key + "']");
                            if ($.type(formField[0]) === "undefined") {
                                if (setting.isDebug) {
                                    alert("can not find name:[" + key + "] in form!!!"); // 没找到指定name的表单
                                }
                            } else {
                                var fieldTagName = formField[0].tagName.toLowerCase();
                                if (fieldTagName == "input") {
                                    if (formField.attr("type") == "radio") {
                                        $("input:radio[name='" + key + "'][value='" + value
                                            + "']").attr("checked",
                                            "checked");
                                    } else {
                                        formField.val(value);
                                    }
                                    //下拉框
                                } else if (fieldTagName == "select") {
                                    var options = formField.find('option');
                                    for (var j = 0; j < options.length; j++) {
                                        if ($(options[j]).val() == value) {
                                            $(options[j]).attr("selected", "selected");
                                        }
                                    }
                                } else if (fieldTagName == "textarea") {
                                    formField.val(value);
                                } else {
                                    formField.val(value);
                                }
                            }
                        });
                    if (setting.isDebug) {
                        alert(debugInfo);
                    }
                }
                return form; // 返回对象，提供链式操作
            }
        });
        window.Ewin = function () {
            var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                '<div class="modal-dialog modal-sm">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
                '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                '</div>' +
                '<div class="modal-body">' +
                '<p>[Message]</p>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
                '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';


            var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
                '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                '</div>' +
                '<div class="modal-body">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
            var generateId = function () {
                var date = new Date();
                return 'mdl' + date.valueOf();
            };
            var init = function (options) {
                options = $.extend({}, {
                    title: "操作提示",
                    message: "提示内容",
                    btnok: "确定",
                    btncl: "取消",
                    width: 200,
                    auto: false
                }, options || {});
                var modalId = generateId();
                var content = html.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title,
                        Message: options.message,
                        BtnOk: options.btnok,
                        BtnCancel: options.btncl
                    }[key];
                });
                $('body').append(content);
                $('#' + modalId).modal({
                    width: options.width,
                    backdrop: 'static'
                });
                $('#' + modalId).on('hide.bs.modal', function (e) {
                    $('body').find('#' + modalId).remove();
                });
                return modalId;
            };

            return {
                alert: function (options) {
                    if (typeof options == 'string') {
                        options = {
                            message: options
                        };
                    }
                    var id = init(options);
                    var modal = $('#' + id);
                    modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                    modal.find('.cancel').hide();

                    return {
                        id: id,
                        on: function (callback) {
                            if (callback && callback instanceof Function) {
                                modal.find('.ok').click(function () {
                                    callback(true);
                                });
                            }
                        },
                        hide: function (callback) {
                            if (callback && callback instanceof Function) {
                                modal.on('hide.bs.modal', function (e) {
                                    callback(e);
                                });
                            }
                        }
                    };
                },
                confirm: function (options) {
                    var id = init(options);
                    var modal = $('#' + id);
                    modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                    modal.find('.cancel').show();
                    return {
                        id: id,
                        on: function (callback) {
                            if (callback && callback instanceof Function) {
                                modal.find('.ok').click(function () {
                                    callback(true);
                                });
                                modal.find('.cancel').click(function () {
                                    callback(false);
                                });
                            }
                        },
                        hide: function (callback) {
                            if (callback && callback instanceof Function) {
                                modal.on('hide.bs.modal', function (e) {
                                    callback(e);
                                });
                            }
                        }
                    };
                },
                dialog: function (options) {
                    options = $.extend({}, {
                        title: 'title',
                        url: '',
                        width: 800,
                        height: 550,
                        onReady: function () {
                        },
                        onShown: function (e) {
                        }
                    }, options || {});
                    var modalId = generateId();

                    var content = dialogdHtml.replace(reg, function (node, key) {
                        return {
                            Id: modalId,
                            Title: options.title
                        }[key];
                    });
                    $('body').append(content);
                    var target = $('#' + modalId);
                    target.find('.modal-body').load(options.url);
                    if (options.onReady())
                        options.onReady.call(target);
                    target.modal();
                    target.on('shown.bs.modal', function (e) {
                        if (options.onReady(e))
                            options.onReady.call(target, e);
                    });
                    target.on('hide.bs.modal', function (e) {
                        $('body').find(target).remove();
                    });
                }
            }
        }();
    }
)(jQuery);