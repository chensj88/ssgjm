<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/header.jsp" %>
<%String ref = request.getHeader("REFERER");%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>站点问题统计</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css"/>
    <link rel="shortcut icon" href="<%=basePath%>resources/img/logo.ico"/>
</head>

<body>
<input id="id" type="hidden" name="id" value="${info_old.id}">
<div class="mui-content check-distribuion-chart">
    <!--header-->
    <%--<div class="header">--%>
    <%--<span class="mui-icon mui-icon-arrowleft" onclick="javascript:history.go(-1)"></span>--%>
    <%--<div>${siteName}</div>--%>
    <%--<span class="mui-icon mui-icon-more"></span>--%>
    <%--</div>--%>
    <div class="hole"></div>
    <div id='chart'></div>
    <div class="check-distribuion-list">
    <c:forEach var="vwr" items="${infoList}">
    <div class="mui-input-row mui-radio">
    <label>
    <strong>${vwr.map.get("c_name")} </strong><span style="margin-left: 20px;">${vwr.map.get("position_name")}&nbsp;&nbsp;  处理问题数</span><i>${vwr.map.get("num")}</i>
    </label>
    <input name="radio1" type="radio" value="${vwr.allocateUser}">
    </div>
    </c:forEach>

    </div>
    <div class="fix-hole"></div>
    <div class="check-distribuion-btn">
    <input type="button" onclick="save();" value="保存" />
    <input type="button" onclick="history.go(-1)" value="取消" />
    </div>
</div>
<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/echarts.simple.min.js"></script>
<script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        IMS.dropDown();
        enterprise.init();
        $("input:radio[name='radio1'][value='${info_old.allocateUser}']").attr("checked", 'checked');

    });

    var myChart = echarts.init(document.getElementById('chart'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            show: true,
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            top: '10',
            left: '0%',
            right: '0%',
            bottom: '0%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#666'
                }
            },
            splitLine: {
                lineStyle: {
                    width: '0.8',
                    color: '#E2E2E2',
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            show: true,
            axisLabel: {
                interval: 0,
                fontSize: 8
            },
            axisLine: {
                lineStyle: {
                    color: '#666'
                }
            },
            type: 'category',
            data: ${jsonName}
        },
        series: [{
            type: 'bar',
            data: ${numList},
            label: {
                normal: {
                    show: true,
                    padding: [2, 5],
                    position: 'right',
                    color: '#fff',
                    fontSize: '12',
                    offset: [5, 0],
                    backgroundColor: '#66C9FF'
                }
            },
            itemStyle: {
                color: '#d6f1ff'
            }

        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.on('click', function (params) {
        // window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
        console.info(params);
    });

    //保存修改的分配人
    function save() {

        var id = $("#id").val();
        var allocateUser = $("input[name='radio1']:checked").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>mobile/siteQuestionInfo/allocateUser.do",
            data: {id: id, allocateUser: allocateUser},
            cache: false,
            dataType: "json",
            async: false,
            error: function (request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。', {duration: 'long', type: 'div'})
            },
            success: function (data) {
                if (data.status) {
                    mui.toast('分配成功', {duration: 'long(3500ms)', type: 'div'});
                    //追加图片预览
                    setTimeout("location.reload()", 3500);
                } else {
                    mui.toast('分配失败', {duration: 'long(3500ms)', type: 'div'});
                    //追加图片预览
                    //setTimeout("location.reload()",3500);

                }
            }
        });

    }


</script>
</body>

</html>