

jQuery(function($) {

    var height = window.screen.height;

    $('#jspContent').height( height*0.9);

    function initMenu() {
        $.ajax({
            url : Common.getRootPath() + '/home/loadMenu.do',
            type:'post',
            cache : false,
            dataType: 'json',
            async: false,
            success :function (result) {
                var _result = eval(result);
                if(_result.status == Common.SUCCESS){
                    var menu = _result.data;
                    generateMenu(menu);
                }
            }
        });

    }

    function generateMenu(data) {
        $.each(data,function (index,value,array) {
            var content = '';
            if( index == 0 ){
                content = "<li class=\"active\" >\n" +
                    "                    <a href=\""+Common.getRootPath()+value.urlPath+"\">\n" +
                    "                        <i class=\""+value.nodeIcon+"\"></i>\n" +
                    "                        <span class=\"menu-text\"> "+value.text+" </span>\n" +
                    "                    </a>\n" +
                    "                </li>";
            }else{
                content = "<li >\n" +
                    "                    <a href=\"#\" class=\"dropdown-toggle\">\n" +
                    "                        <i class=\""+value.nodeIcon+"\"></i>\n" +
                    "                        <span class=\"menu-text\"> "+value.text+" </span>\n" +
                    "                        <b class=\"arrow icon-angle-down\"></b>"+
                    "                    </a>\n"+
                    "                    <ul class=\"submenu\">";
                $.each(value.nodes,function (cindex,cvalue,carray) {
                    content += "<li><a href=\""+Common.getRootPath()+cvalue.urlPath+"\">" +
                        "        <i class=\""+cvalue.nodeIcon+"\"></i>"+cvalue.text+"</a></li>";
                });

                content +=    "</ul></li>";
            }
           /* console.log(content);*/
            $('#navlist').append(content);
        });

    }
    initMenu();
    $('#coniframe').attr('src',Common.getRootPath()+'/home/index.do');
    var content = '<li><i class="icon-home home-icon"></i><a href="#">首页</a></li> <li class="active">控制台</li>';
    $('#breadcrumbMenu').empty();
    $(content).appendTo($('#breadcrumbMenu'));
    $('#navlist li a').on('click',function(e){
        e.preventDefault();
        var src = $(this).attr('href');
        var title = $(this).text();
        var parenTitle = $(this).parents('.submenu').prev('a').find('span').text();
        var breadcrumbMenu = $('#breadcrumbMenu');
        /*console.log(src+'-'+parenTitle+'-'+title);*/
        if(parenTitle){
            var content = '<li><i class="icon-home home-icon"></i>'+parenTitle.trim()+'</li> <li class="active">'+title+'</li>'
            breadcrumbMenu.empty();
            $(content).appendTo(breadcrumbMenu);
            $('#coniframe').attr('src',src);
        }else {
            var content = '<li><i class="icon-home home-icon"></i>控制台</li> <li class="active">'+title+'</li>';
            breadcrumbMenu.empty();
            $(content).appendTo(breadcrumbMenu);
            if(src !== '#'){ //主页信息特殊处理
                $('#coniframe').attr('src',src);
            }
        }
    });

    $('#index').on('click',function (e) {
        e.preventDefault();
        var breadcrumbMenu = $('#breadcrumbMenu');
        $('#coniframe').attr('src',Common.getRootPath()+'/home/index.do');
        var content = '<li><i class="icon-home home-icon"></i>首页</li> <li class="active">控制台</li>';
        breadcrumbMenu.empty();
        $(content).appendTo(breadcrumbMenu);
    });


    $('#coniframe').on('load',function () {
        var source = $(this).attr('src');
        var url = $(this)[0].contentWindow.location.href;
        if(url.indexOf("login.do") != -1){
            window.location.href = Common.getRootPath() + "/login/login.do";
        }
    })

});