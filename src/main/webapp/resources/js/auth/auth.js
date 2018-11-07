/**
 * 用户按钮权限检查
 * 根据访问URL和当前人员来查询按钮权限
 * @param btnType
 */
function checkUserButtonAuth(btnType){
    var ctrl = Common.getControllerPath();
    var rootUrl = Common.getRootPath();
    var pageUrl = ctrl.substring(rootUrl.length,ctrl.length-1);
    $.ajax({
        type: "POST",
        url: Common.getRootPath() + "/auth/initBtnList.do",
        data: {"modUrl": pageUrl},
        async:false,
        dataType: "json",
        cache: false,
        error: function (request) {
            toastr.error(request.responseText);
        },
        success: function (data) {
            $('.btn').attr('style','display:none');
            $.each(data.data,function (index,value,array) {
                var className = btnType+'.'+ value;
                $(className).attr("style","display:inline;");
            });
        }
    });
}