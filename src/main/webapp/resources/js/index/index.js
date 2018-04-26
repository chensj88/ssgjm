$(function () {

    $.ajax({
        url: Common.getRootPath() + '/home/count.do',
        type: "post",
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
            if(data.status == 'success'){
                initEcharts(data.itemData,data.numData);
            }
        }
    });
    function initEcharts(itemData,numData){
        var dataStyle = {
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
        var option = {
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
                    name: '数量',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: dataStyle,
                    data: numData
                }
            ],
            color: ['#7537ed', '#ba9bf6']
        };
        setTimeout(_ => {
            var myChart = echarts.init(document.getElementById('main'),'macarons');
            myChart.setOption(option);
        }, 0);
    }

});