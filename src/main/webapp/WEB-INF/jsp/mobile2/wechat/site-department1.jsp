<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/6/12
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/header.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/demo/css/style.css">
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${userId}">
<input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
<header class="fixed">
    <div class="header">
        科室名称
    </div>
</header>
<div id="letter" ></div>
<div class="sort_box" id="deptList">
    <c:forEach var="d" items="${depts}">
        <div class="sort_list" onclick="getSelected(${d.id},'${d.deptName}')">
            <div class="num_name" id="${d.id}">${d.deptName}</div>
        </div>
    </c:forEach>
</div>
<div class="initials">
    <ul></ul>
</div>
</body>
<script src="<%=basePath%>resources/demo/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/demo/js/jquery.charfirst.pinyin.js" type="text/javascript" charset="utf-8"></script>
<%--<script src="<%=basePath%>resources/demo/js/sort.js" type="text/javascript" charset="utf-8"></script>--%>
<script>
    $(function () {

        var Initials=$('.initials');
        var LetterBox=$('#letter');
        var firstInit = ${firstInit};
        var liList = '';
        $.each(firstInit,function (i,a) {
            liList +='<li>'+ a +'</li>';
        })

        Initials.find('ul').append(
            liList
        );
        initials();

        $(".initials ul li").click(function(){
            var _this=$(this);
            var LetterHtml=_this.html();
            LetterBox.html(LetterHtml).fadeIn();

            Initials.css('background','rgba(145,145,145,0.6)');

            setTimeout(function(){
                Initials.css('background','rgba(145,145,145,0)');
                LetterBox.fadeOut();
            },1000);

            var _index = _this.index()
            if(_index==0){
                $('html,body').animate({scrollTop: '0px'}, 300);//点击第一个滚到顶部
            }else if(_index==27){
                var DefaultTop=$('#default').position().top;
                $('html,body').animate({scrollTop: DefaultTop+'px'}, 300);//点击最后一个滚到#号
            }else{
                var letter = _this.text();
                if($('#'+letter).length>0){
                    var LetterTop = $('#'+letter).position().top;
                    $('html,body').animate({scrollTop: LetterTop-45+'px'}, 300);
                }
            }
        })

        var windowHeight=$(window).height();
        var InitHeight=windowHeight-45;
        Initials.height(InitHeight);
        var LiHeight=InitHeight/${firstInitLength};
        Initials.find('li').height(LiHeight);

    });
</script>
<script>

    function getSelected(id,name) {
        console.log(id);
        console.log(name);
        $('#productName').val(id);


    }

    function initials() {//公众号排序
        var SortList=$(".sort_list");
        var SortBox=$(".sort_box");
        SortList.sort(asc_sort).appendTo('.sort_box');//按首字母排序
        function asc_sort(a, b) {
            return makePy($(b).find('.num_name').text().charAt(0))[0].toUpperCase() < makePy($(a).find('.num_name').text().charAt(0))[0].toUpperCase() ? 1 : -1;
        }

        var initials = [];
        var num=0;
        SortList.each(function(i) {
            var initial = makePy($(this).find('.num_name').text().charAt(0))[0].toUpperCase();
            if(initial>='A'&&initial<='Z'){
                if (initials.indexOf(initial) === -1)
                    initials.push(initial);
            }else{
                num++;
            }

        });

        $.each(initials, function(index, value) {//添加首字母标签
            SortBox.append('<div class="sort_letter" id="'+ value +'">' + value + '</div>');
        });
        if(num!=0){SortBox.append('<div class="sort_letter" id="default">#</div>');}

        for (var i =0;i<SortList.length;i++) {//插入到对应的首字母后面
            var letter=makePy(SortList.eq(i).find('.num_name').text().charAt(0))[0].toUpperCase();
            switch(letter){
                case "A":
                    $('#A').after(SortList.eq(i));
                    break;
                case "B":
                    $('#B').after(SortList.eq(i));
                    break;
                case "C":
                    $('#C').after(SortList.eq(i));
                    break;
                case "D":
                    $('#D').after(SortList.eq(i));
                    break;
                case "E":
                    $('#E').after(SortList.eq(i));
                    break;
                case "F":
                    $('#F').after(SortList.eq(i));
                    break;
                case "G":
                    $('#G').after(SortList.eq(i));
                    break;
                case "H":
                    $('#H').after(SortList.eq(i));
                    break;
                case "I":
                    $('#I').after(SortList.eq(i));
                    break;
                case "J":
                    $('#J').after(SortList.eq(i));
                    break;
                case "K":
                    $('#K').after(SortList.eq(i));
                    break;
                case "L":
                    $('#L').after(SortList.eq(i));
                    break;
                case "M":
                    $('#M').after(SortList.eq(i));
                    break;
                case "O":
                    $('#O').after(SortList.eq(i));
                    break;
                case "P":
                    $('#P').after(SortList.eq(i));
                    break;
                case "Q":
                    $('#Q').after(SortList.eq(i));
                    break;
                case "R":
                    $('#R').after(SortList.eq(i));
                    break;
                case "S":
                    $('#S').after(SortList.eq(i));
                    break;
                case "T":
                    $('#T').after(SortList.eq(i));
                    break;
                case "U":
                    $('#U').after(SortList.eq(i));
                    break;
                case "V":
                    $('#V').after(SortList.eq(i));
                    break;
                case "W":
                    $('#W').after(SortList.eq(i));
                    break;
                case "X":
                    $('#X').after(SortList.eq(i));
                    break;
                case "Y":
                    $('#Y').after(SortList.eq(i));
                    break;
                case "Z":
                    $('#Z').after(SortList.eq(i));
                    break;
                default:
                    $('#default').after(SortList.eq(i));
                    break;
            }
        };
    }
</script>
</html>
