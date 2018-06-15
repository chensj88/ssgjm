    <%--
      Created by IntelliJ IDEA.
      User: LENOVO
      Date: 2018/6/9
      Time: 14:38
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/commons/header.jsp" %>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>新增采集</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/enterprise.css" />
        <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
        <style type="text/css">
            .span_font {
                color :#666666;
                font-family: 'Helvetica Neue',Helvetica,sans-serif;
            }
        </style>
    </head>
    <body>
    <input id="userId" type="hidden" name="userId" value="${userId}">
    <input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
    <input id="id" type="hidden" name="id" value="${siteQuestionInfo.id}">
    <input id="source" type="hidden" name="source" value="${source}">
        <div class="wrap">
            <div class="wrap-header">
                <div class="header">
                    <span class="mui-icon mui-icon-arrowleft" onclick="checkQuestion()" ></span>
                    <div>新增采集</div>
                    <a href="<%=basePath%>mobile/wechatSiteQuestion/list.do?serialNo=${serialNo}&userId=${userId}">采集列表</a>
                </div>
            </div>
            <div class="wrap-cnt">
                <div class="column-2 collect-list">
                    <strong>科室病区</strong>
                    <div class="collect-list-dp">
                        <input type="hidden" id="siteName" name="siteName" value="${siteQuestionInfo.siteName}" >
                        <c:if test="${siteQuestionInfo.siteName != null}" >
                            <a href="#" onclick="openDeptOrSysWindow(1)"><span class="span_font">${siteQuestionInfo.map.get("deptName")}</span><i class="iconfont icon-fanhui-copy"></i></a>
                        </c:if>
                        <c:if test="${siteQuestionInfo.siteName == null}" >
                            <a href="#" onclick="openDeptOrSysWindow(1)"><span class="span_font">--请选择--</span><i class="iconfont icon-fanhui-copy"></i></a>
                        </c:if>
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>系统名称</strong>
                    <div class="collect-list-dp">
                        <input id="productName" name="productName" value="${siteQuestionInfo.productName}" type="hidden"/>
                        <c:if test="${siteQuestionInfo.productName != null}" >
                            <a href="#" onclick="openDeptOrSysWindow(2)"><span class="span_font">${siteQuestionInfo.map.get("plName")}</span><i class="iconfont icon-fanhui-copy"></i></a>
                        </c:if>
                        <c:if test="${siteQuestionInfo.productName == null}" >
                            <a href="#" onclick="openDeptOrSysWindow(2)"><span class="span_font">--请选择--</span><i class="iconfont icon-fanhui-copy"></i></a>
                        </c:if>
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>问题标题</strong>
                    <div class="collect-list-text">
                        <input type="text" title="${siteQuestionInfo.menuName}" value="${ menuName != null ? menuName : siteQuestionInfo.menuName.trim() }" id="menuName">
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>问题描述</strong>
                    <div>
                        <textarea id="questionDesc" name="questionDesc">${questionDesc != null ? questionDesc : siteQuestionInfo.questionDesc}</textarea>
                    </div>
                </div>
                <div class="space"></div>
                <div class="column-2 collect-list">
                    <strong>优先等级</strong>
                </div>
                <div class="column-2 collect-list">
                    <div class="collect-list-level">
                        <span onclick="changeListLevel(1)">A(紧急)</span>
                        <span onclick="changeListLevel(2)">B(急)</span>
                        <span onclick="changeListLevel(3)" class="level">C(一般)</span>
                        <span onclick="changeListLevel(4)">D(暂缓)</span>
                    </div>
                </div>
                <p class="collect-list-level_p" id="remark"></p>
                <div class="space"></div>
                <div class="column-2 collect-list">
                    <strong>影像资料</strong>
                </div>
                <form id="file" action="" method="post" enctype="multipart/form-data">
                    <div class="column-2 collect-list">
                        <div class="datum-upload site-width">
                            <div>
                                <i class="iconfont icon-plus"></i>
                                <input type="file" id="uploadFile" name="uploadFile" onchange="fileSelected2();"  />
                            </div>
                            <c:if test="${siteQuestionInfo.imgPath !=null && siteQuestionInfo.imgPath !=''}">
                                <c:forEach var="img" items="${siteQuestionInfo.imgs}">
                                    <div id="close_id">
                                        <img src="<%=Constants.FTP_SHARE_FLODER%>${img}" />
                                        <span class="iconfont icon-close" onclick="closeImg('${siteQuestionInfo.id}','${img}');"></span>
                                        <input type="hidden" />
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
            <div class="wrap-foot large-btn">
                <a href="#"  onclick="checkQuestion();"><span>取消</span></a>
                <a href="#"  onclick="save();"><span>保存</span></a>

            </div>
        </div>
    </body>
    <script src="<%=basePath%>resources/mobile/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/mobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function(){
            enterprise.init();
            /*优先等级切换*/
            $('.collect-list-level>span').click(function () {
                $(this).addClass('level').siblings('span').removeClass('level');
            });
            setListLevel(${priority != null ? priority : siteQuestionInfo.priority});
            changeListLevel(${priority != null ? priority : siteQuestionInfo.priority});
        })
        /**
         * 初始化优先级
         *@param val
         */
        function setListLevel(val){
            var spanArr = $('.collect-list-level>span');
            var valStr = convertTypeToInt(val);
            for(var i= 0;i<spanArr.length ; i++){
                var text = $(spanArr[i]).text().substr(0,1);
                if(text ===  valStr){
                    $(spanArr[i]).addClass('level').siblings('span').removeClass('level');
                }
            }
        }
        /**
         * 获取优先级的选择值
         *@returns {numbers}
         */
        function getListLevelValue() {
            return convertStringToCodeValue($('.collect-list-level').find('span.level').text());
        }
        /**
         * 检查问题的状态
         *
         */
        function checkQuestion() {
            var questionId = $('#id').val();
            if(questionId !== ''){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>mobile/wechatSiteQuestion/checkQuestionStatus.do",
                    data:{id:questionId},
                    cache : false,
                    dataType:"json",
                    async: false,
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                    },
                    success: function(data) {
                        if(data.status == 'success') {
                            if(data.data == 0){
                                deleteQuestion(questionId);
                                goToIndexPage();
                            }else{
                                goToIndexPage();
                            }
                        }
                    }
                });
            }else{
                goToIndexPage();
            }

        }

        function goToIndexPage() {
            console.log(${source});
            console.log(${source == 1});
            console.log(${source == 2});
            if(${source == 1}){
                location.href="<%=basePath%>mobile/tempSiteQuestion/laodList.do?processStatus=4&userId=${userId}&serialNo=${serialNo}";
            }else if(${source == 2}){
                location.href="<%=basePath%>mobile/wechatSiteQuestion/list.do?userId=${userId}&serialNo=${serialNo}";
            }

        }
        /**
         * 提交数据到后台
         */
        function save() {
            var siteName = $('#siteName').val();
            var productName = $('#productName').val();
            var menuName = $('#menuName').val();
            var questionDesc = $('#questionDesc').val();

            if(siteName == null || siteName ==''){
                mui.toast('科室病区不能为空',{ duration:'long(3500ms)', type:'div' });
                return false;
            }
            if(productName == null || productName ==''){
                mui.toast('系统名称不能为空',{ duration:'long(3500ms)', type:'div' });
                return false;
            }
            if(menuName == null || menuName ==''){
                mui.toast('问题标题不能为空',{ duration:'long(3500ms)', type:'div' });
                return false;
            }
            if(questionDesc == null || questionDesc ==''){
                mui.toast('问题描述不能为空',{ duration:'long(3500ms)', type:'div' });
                return false;
            }


            var queryJson = {
                id : $('#id').val(),
                serialNo : $('#serialNo').val(),
                priority:getListLevelValue(),
                siteName:$('#siteName').val(),
                productName:$('#productName').val(),
                menuName: $('#menuName').val(),
                questionDesc:$('#questionDesc').val().trim(),
                creator: $('#userId').val()

            };

            $.ajax({
                type: "POST",
                url:"<%=basePath%>mobile/wechatSiteQuestion/addOrUpdate.do",
                data:queryJson,
                cache : false,
                dataType:"json",
                async: false,
                error: function(request) {
                    mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                },
                success: function(data) {
                    if (data.status) {
                        mui.toast('问题提交成功', {duration: 'long(3500ms)', type: 'div'});
                        location.href="<%=basePath%>mobile/tempSiteQuestion//laodList.do?processStatus=1&userId="+$("#userId").val()+ "&serialNo="+$("#serialNo").val();
                    }
                }
            });
        }
        /**
         * 文件选择
         */
        function fileSelected2(){
            //获取文件的内容
            var userId = $("#userId").val();
            var serialNo = $("#serialNo").val();
            var old_id = $("#id").val();
            var uploadFile = new FormData($("#file")[0]);
            //判断上传的只能是图片
            var f=document.getElementById("uploadFile").value;
            if(f=="") {
                alert("请上传图片");return false;
            } else {
                if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                    mui.toast('图片类型必须是.gif,jpeg,jpg,png中的一种',{ duration:'long(3500ms)', type:'div' });
                    return false;
                }
            }

            if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>mobile/wechatSiteQuestion/saveAndUpdate.do?userId="+userId+"&serialNo="+serialNo+"&old_id="+old_id,
                    data:uploadFile,
                    cache : false,
                    async: false,
                    contentType: false, //不设置内容类型
                    processData: false, //不处理数据
                    error: function(request) {
                        mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                        console.log(request);
                    },
                    success: function(data) {
                        var obj = JSON.parse(data);
                        if(obj.status == "1") {
                            mui.toast('上传成功',{ duration:'long(3500ms)', type:'div' });
                            //追加图片预览 <span class="iconfont icon-close" onclick="closeImg('${siteQuestionInfo.id}','${img}');"></span>
                            //									<input type="hidden" />
                            var imgs = "<div id=\"close_id\"><img src='<%=Constants.FTP_SHARE_FLODER%>"+obj.path+"'></img><span class=\"iconfont icon-close\" onclick=\"closeImg('+"+obj.id+"',"+"'"+obj.path+"');\"></span>\n</div>";
                            $(".datum-upload.site-width").append(imgs);
                            $("#id").val(obj.id);

                        } else {
                            mui.toast('上传失败',{ duration:'long(3500ms)', type:'div' });
                        }
                    }
                });


            }else{
                alert("选择的文件无效！请重新选择");
            }

        }

        //删除图片
        function closeImg(e,imgPath){
            if(e==null || e ==''){
                return false;
            }
            $.ajax({
                type: "POST",
                url:"<%=basePath%>mobile/wechatSiteQuestion/deleteImg.do",
                data:{id:e,imgPath:imgPath,serialNo:${serialNo},userId:${userId}},
                cache : false,
                dataType:"json",
                async: false,
                error: function(request) {
                    mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                },
                success: function(data) {
                    if(data.status) {
                        mui.toast('删除成功',{ duration:'long(3500ms)', type:'div' });
                        //追加图片预览
                        //setTimeout("location.reload()",3500);
                    } else {
                        mui.toast('删除失败',{ duration:'long(3500ms)', type:'div' });
                        //追加图片预览
                        //setTimeout("location.reload()",3500);

                    }
                }
            });

        }
        /**
         * 变更优先级文字
         */
        function changeListLevel(val) {
            switch (val){
                case 1 :
                    $('#remark').text("注：A等级项目完成时间为1个工作日");
                    break;
                case 2 :
                    $('#remark').text("注：B等级项目完成时间为3个工作日");
                    break;
                case 3 :
                    $('#remark').text("注：C等级项目完成时间为7个工作日");
                    break;
                case 4 :
                    $('#remark').text("注：D等级项目完成时间为15个工作日");
                    break;
                default :
                    $('#remark').text("注：C等级项目完成时间为7个工作日");
            }
        }
        /**
         *优先级转换 从码值到ABCD
         *@param val
         *@returns {string}
         */
        function convertTypeToInt(val) {
                var valStr = '';
                switch (val) {
                    case 1:
                        valStr = 'A'
                        break;
                    case 2:
                        valStr = 'B'
                        break;
                    case 3:
                        valStr = 'C'
                        break;
                    case 4:
                        valStr = 'D'
                        break;
                    default: 'D'
                }
                return valStr;
        }

        /**
         * 优先级转换，从ABCD转换到码值
         * @param selectedVal
         * @returns {number}
         */
        function convertStringToCodeValue(selectedVal) {
            //截取获得第一位字母
            var selectedString = selectedVal.substr(0,1);
            var codeVal = -1;
            switch (selectedString) {
                case 'A':
                    codeVal = 1
                    break;
                case 'B':
                    codeVal = 2
                    break;
                case 'C':
                    codeVal = 3
                    break;
                case 'D':
                    codeVal = 4
                    break;
                default: -1
            }
            return codeVal;
        }

        /**
         * 问题删除
         * @param id
         */
        function deleteQuestion(id) {
            $.ajax({
                type: "POST",
                url:"<%=basePath%>mobile/wechatSiteQuestion/delete.do",
                data:{id:id},
                cache : false,
                dataType:"json",
                async: false,
                error: function(request) {
                    mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })
                },
                success: function(data) {
//                    mui.toast('问题删除成功！',{ duration:'long', type:'div' })
                }
            });
        }

        function openDeptOrSysWindow(type) {
            var menuName = $('#menuName').val();
            var questionDesc = $('#questionDesc').val();
            var priority = getListLevelValue();
            location.href = "<%=basePath%>mobile/wechatSiteQuestion/openDept.do?serialNo=${serialNo}&userId=${userId} "+
                            "&questionId=${siteQuestionInfo.id}&type="+type+"&source=${source}&menuName="+encodeURI(menuName)+"&questionDesc="+encodeURI(questionDesc)+"&priority="+priority;
        }
    </script>
</html>
