<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>医院_搜索</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_9raiir53539.css"/>
</head>

<body>

        <div class="space"></div>
        <form  action="javascript:search();">
            <div class="management-search">
                <i class="iconfont icon-search" onclick="search();"></i>
                <input type="text" placeholder="请输入搜索内容" id="search_text">
            </div>
        </form>
        <div class="space">
            <ul id="hospital" class="mui-table-view OA_task_1">
                <li></li>
            </ul>

        </div>



<script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js"></script>
<script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function(){
        IMS.init();
        $('#search_text').val('');
    });
    function search() {
        var str_html="";
        var searchText = $('#search_text').val();
        if (searchText == null || searchText == '') {
            mui.toast('搜索内容不能为空', {duration: 'long(3500ms)', type: 'div'});
            return false;
        }

        $.ajax({
            type: "POST",
            url:"<%=basePath%>/mobile/commons/queryCustomerName.do",
            data:{name:searchText,count:30},
            cache : false,
            dataType:"json",
            async: false,
            error: function(request) {
                mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
            },
            success: function(data) {
                str_html="";
                $("#hospital").empty();
                //console.log(data.data);
                //var json=eval(data.data.xtJsons);
                $.each(data.data,function(i,item){
                    console.info("sss=="+data.data[i].name);
                    str_html=str_html+"<li data-val='"+data.data[i].id+"'  onclick=goIndex('"+data.data[i].id+"')>"+data.data[i].name+"</li>";
                });
                $("#hospital").html(str_html);


            }
        });

        <%--location.href = "<%=basePath%>mobile/commons/list.do?userId=${userId}&serialNo=${serialNo}&status=${status}&searchType="+searchType+"&searchText="+searchText+"&isManager=${isManager}";--%>
    }

    /** 选中医院  */
    function goIndex(id){
        location.href = "<%=basePath%>/mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo="+id;

    }


    // $("#hospital").bind("input oninput",function(){
    //    //     alert($(this).val());
    //    //     console.log($(this).val().length);//打印输入框字符长度
    //    //
    //    // });

    // $(document).on('focusin', function () {
    //     //软键盘弹出的事件处理
    //     alert(1);
    // });

    <%--$(document).on('focusout', function () {--%>
        <%--//软键盘收起的事件处理--%>
        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url:"<%=basePath%>/mobile/commons/queryCustomerName.do",--%>
            <%--data:{name:searchText,count:30},--%>
            <%--cache : false,--%>
            <%--dataType:"json",--%>
            <%--async: false,--%>
            <%--error: function(request) {--%>
                <%--mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })--%>
            <%--},--%>
            <%--success: function(data) {--%>
                <%--str_html="";--%>
                <%--$("#hospital").empty();--%>
                <%--//console.log(data.data);--%>
                <%--//var json=eval(data.data.xtJsons);--%>
                <%--$.each(data.data,function(i,item){--%>
                    <%--console.info("sss=="+data.data[i].name);--%>
                    <%--str_html=str_html+"<li data-val='"+data.data[i].id+"'>"+data.data[i].name+"</li>";--%>
                <%--});--%>
                <%--$("#hospital").html(str_html);--%>


            <%--}--%>
        <%--});--%>
    <%--});--%>


</script>
</body>

</html>
