jQuery(function($) {
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
        }
    });

    $('#index').on('click',function (e) {
        e.preventDefault();
        $('#coniframe').attr('src',Common.getRootPath()+'/home/index.do');
        var content = '<li><i class="icon-home home-icon"></i>首页</li> <li class="active">控制台</li>';
        breadcrumbMenu.empty();
        $(content).appendTo(breadcrumbMenu);
    });
});