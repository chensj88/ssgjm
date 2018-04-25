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

    let  itemData = [];
    let  completeData = [];
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
        itemData.push(value.label);
        completeData.push(value.data);
    });
    let dataStyle = {
        normal: {
            label: {
                show: true,
                position: 'inside',
            }
        },
        borderColor: '#f00',
        borderWidth: 10,
        borderType: 'solid'
    };
    let option = {
        tooltip: {
            show: true,
            trigger: 'item'
        },
        legend: {
            data: ['数量'],
            textStyle: {
                color: '#666'
            }
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '10%',
            containLabel: true
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data:itemData,
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    color: '#666666',
                    fontFamily: 'Microsoft YaHei'
                },
                axisLine: {
                    show: false
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    color: '#93abba'
                },
                axisLine: {
                    lineStyle: {
                        color: '#93abba'
                    }
                }
            }
        ],
        series: [
            {
                name: '人员数量',
                type: 'bar',
                stack: '总量',
                itemStyle: dataStyle,
                data: completeData
            }
        ],
        color: ['#7537ed', '#ba9bf6']
    };
    setTimeout(_ => {
        let myChart = echarts.init(document.getElementById('main'));
        myChart.setOption(option);
    }, 0);
});