/**
 * 用户按钮权限检查
 * 根据访问URL和当前人员来查询按钮权限
 * @param btnType
 */
function checkUserButtonAuth(){
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
            if(data.status === 'success'){
                //将已配置按钮全部消失
                $.each(data.allData,function (index,value,array) {
                    var className = '.'+ value;
                    $(className).attr("style","display:none;");
                });
                //显示具有权限按钮
                $.each(data.authData,function (index,value,array) {
                    var className = '.'+ value;
                    $(className).attr("style","display:inline;");
                });
            }

        }
    });
}