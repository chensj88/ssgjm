$(function () {
    function drawPieChart(placeholder, data, position) {
        $.plot(placeholder, data, {
            series: {
                pie: {
                    show: true,
                    tilt:0.8,
                    highlight: {
                        opacity: 0.25
                    },
                    stroke: {
                        color: '#fff',
                        width: 2
                    },
                    startAngle: 2
                }
            },
            legend: {
                show: true,
                position: position || "ne",
                labelBoxBorderColor: null,
                margin:[-30,15]
            }
            ,
            grid: {
                hoverable: true,
                clickable: true
            }
        })
    }
    var data = [];
    var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
    $.ajax({
        url : Common.getRootPath() + '/admin/user/count.do',
        type:'post',
        cache : false,
        dataType: 'json',
        async: false,
        success :function (result) {
            var _result = eval(result);
            if(_result.status == Common.SUCCESS){
                data = _result.data;
                drawPieChart(placeholder,_result.data);
                placeholder.data('chart', _result.data);
                placeholder.data('draw', drawPieChart);
            }
        }
    });

    $.ajax({
        url : Common.getRootPath() + '/admin/user/count.do',
        type:'post',
        cache : false,
        dataType: 'json',
        async: false,
        success :function (result) {
            var _result = eval(result);
            if(_result.status == Common.SUCCESS){
                data = _result.data;
                drawPieChart(placeholder,_result.data);
                placeholder.data('chart', _result.data);
                placeholder.data('draw', drawPieChart);
            }
        }
    });
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
                initMenu(menu);
            }
        }
    });

    function initMenu(data) {
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
            $('#nav').append(content);
        });

    }
    $.each(data,function (index,value,array) {
        var content =
            "<div class=\"grid3\">\n" +
            "                <span class=\"grey\">\n" +
            "                <i class=\"icon-facebook-sign icon-2x blue\"></i>\n" +
            "                &nbsp; "+value.label+"\n" +
            "            </span>\n" +
            "            <h4 class=\"bigger pull-right\">"+value.data+"</h4>\n" +
            "            </div>";
        $('#userContent').append(content);
        /*if(index == cLength - 1){
            bdids += value.id;
        }else{
            bdids += value.id +',';
        }*/
    });



    var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
    var previousPoint = null;

    placeholder.on('plothover', function (event, pos, item) {
        if(item) {
            if (previousPoint != item.seriesIndex) {
                previousPoint = item.seriesIndex;
                var tip = item.series['label'] + " : " + item.series['percent']+'%';
                $tooltip.show().children(0).text(tip);
            }
            $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
        } else {
            $tooltip.hide();
            previousPoint = null;
        }

    });






    var d1 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.5) {
        d1.push([i, Math.sin(i)]);
    }

    var d2 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.5) {
        d2.push([i, Math.cos(i)]);
    }

    var d3 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.2) {
        d3.push([i, Math.tan(i)]);
    }


    var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
    $.plot("#sales-charts", [
        { label: "Domains", data: d1 },
        { label: "Hosting", data: d2 },
        { label: "Services", data: d3 }
    ], {
        hoverable: true,
        shadowSize: 0,
        series: {
            lines: { show: true },
            points: { show: true }
        },
        xaxis: {
            tickLength: 0
        },
        yaxis: {
            ticks: 10,
            min: -2,
            max: 2,
            tickDecimals: 3
        },
        grid: {
            backgroundColor: { colors: [ "#fff", "#fff" ] },
            borderWidth: 1,
            borderColor:'#555'
        }
    });


    $('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
    function tooltip_placement(context, source) {
        var $source = $(source);
        var $parent = $source.closest('.tab-content')
        var off1 = $parent.offset();
        var w1 = $parent.width();

        var off2 = $source.offset();
        var w2 = $source.width();

        if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
        return 'left';
    }


    $('.dialogs,.comments').slimScroll({
        height: '300px'
    });


    //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
    //so disable dragging when clicking on label
    var agent = navigator.userAgent.toLowerCase();
    if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
        $('#tasks').on('touchstart', function(e){
            var li = $(e.target).closest('#tasks li');
            if(li.length == 0)return;
            var label = li.find('label.inline').get(0);
            if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
        });

    $('#tasks').sortable({
            opacity:0.8,
            revert:true,
            forceHelperSize:true,
            placeholder: 'draggable-placeholder',
            forcePlaceholderSize:true,
            tolerance:'pointer',
            stop: function( event, ui ) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                $(ui.item).css('z-index', 'auto');
            }
        }
    );
    $('#tasks').disableSelection();
    $('#tasks input:checkbox').removeAttr('checked').on('click', function(){
        if(this.checked) $(this).closest('li').addClass('selected');
        else $(this).closest('li').removeClass('selected');
    });
});