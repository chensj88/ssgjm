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
        <meta charset="UTF-8" />
        <title>新增采集</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/mui.min.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/common.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/mobile/css/service.css" />
        <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_575705_kyiw62yjuy6nu3di.css"/>
    </head>
    <body>
    <input id="userId" type="hidden" name="userId" value="${userId}">
    <input id="serialNo" type="hidden" name="serialNo" value="${serialNo}">
    <input id="id" type="hidden" name="id" value="${siteQuestionInfo.id}">
        <div class="wrap">
            <div class="wrap-header">
                <div class="header">
                    <span class="mui-icon mui-icon-arrowleft"></span>
                    <div>新增采集</div>
                    <a href="<%=basePath%>mobile/wechatSiteQuestion/list.do?serialNo=${serialNo}&userId=${userId}">采集列表</a>
                </div>
            </div>
            <div class="wrap-cnt">
                <div class="column-2 collect-list">
                    <strong>科室病区</strong>
                    <div>
                        <i class="iconfont icon-fanhui-copy"></i>
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>系统名称</strong>
                    <div>
                        <input type="text">
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>影响资料</strong>
                </div>
                <div class="column-2 collect-list">
                    <div class="large-img">
                        <img src="<%=basePath%>resources/mobile/images/sick.png" alt="">
                        <img src="<%=basePath%>resources/mobile/images/sick.png" alt="">
                        <img src="<%=basePath%>resources/mobile/images/sick.png" alt="">
                        <img src="<%=basePath%>resources/mobile/images/sick.png" alt="">
                    </div>
                </div>
                <div class="column-2 collect-list">
                    <strong>解决方案</strong>
                    <div>使用过程中打印机异常，保存使用异常，导出文件异常，结束操作异常，系统闪退</div>
                </div>
                <div class="space"></div>
                <div class="column-2 collect-list">
                    <strong>院方意见</strong>
                </div>
                <div class="column-2 collect-list">
                    <div>
                        <textarea></textarea>
                    </div>
                </div>
            </div>
            <div class="wrap-foot large-btn">
                <a href="#"><span>保存</span></a>
            </div>
        </div>
    </body>
</html>
<%--    <div class="wrap">
        <div class="wrap-header">
            <div class="header">
                <span class="mui-icon mui-icon-arrowleft" onclick="history.go(-1)"></span>
                <div>新增采集</div>
                <a href="<%=basePath%>mobile/wechatSiteQuestion/list.do?serialNo=${serialNo}&userId=${userId}">采集列表</a>
            </div>
        </div>
        <div class="wrap-cnt">
            <div>
                <!--search-->
                <div class="imple-work-search">
                    <i class="iconfont icon-search"></i>
                    <input type="text" placeholder="请输入搜索内容"/>
                </div>
                &lt;%&ndash;<div class="wap-tab">&ndash;%&gt;
                    &lt;%&ndash;<span class="active">未确认（23）</span>&ndash;%&gt;
                    &lt;%&ndash;<span>已确认（27）</span>&ndash;%&gt;
                &lt;%&ndash;</div>&ndash;%&gt;
                <div class="space"></div>
                <div class="wap-tab-cnt">
                    <div class="datum-report padding-btm-20 padding-top-15">
                        <div class="datum-report-item">
                            <span>科室病区</span>
                            <div id="siteId" class="select">
                                <input id="siteName" name="siteName" value="${siteQuestionInfo.siteName}" type="hidden"/>
                                <c:if test="${siteQuestionInfo.siteName != null}">
                                    <a href="#"><span>${siteQuestionInfo.map.get("deptName")}</span><i class="arrow"></i></a>
                                </c:if>
                                <c:if test="${siteQuestionInfo.siteName == null}">
                                    <a href="#"><span>--请选择--</span><i class="arrow"></i></a>
                                </c:if>
                                <ul>
                                    <c:forEach var="vwr" items="${deptList}">
                                        <li data-val="${vwr.id}">${vwr.deptName}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="datum-report padding-btm-20">
                        <div class="datum-report-item">
                            <span>系统名称</span>
                            <div id="productId" class="select">
                                <input id="productName" name="productName" value="${siteQuestionInfo.productName}" type="hidden"/>
                                <c:if test="${siteQuestionInfo.productName != null}">
                                    <a href="#"><span>${siteQuestionInfo.map.get("plName")}</span><i class="arrow"></i></a>
                                </c:if>
                                <c:if test="${siteQuestionInfo.productName == null}">
                                    <a href="#"><span>--请选择--</span><i class="arrow"></i></a>
                                </c:if>
                                <ul>
                                    <c:forEach var="vwr" items="${appList}">
                                        <li data-val="${vwr.id}">${vwr.zxtmc}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="datum-report padding-btm-20">
                        <div class="datum-report-item">
                            <span>问题标题</span>
                            <input id="menuName" type="text" name="menuName" value="${siteQuestionInfo.menuName}" style="border: 1px solid #5ff" />
                        </div>
                    </div>
                    <div class="datum-report">
                        <div class="datum-report-item">
                            <span class="align-self">问题描述</span>
                            <textarea id="questionDesc" name="questionDesc" class="margin-left">${siteQuestionInfo.questionDesc}</textarea>
                        </div>
                    </div>
                    <div class="datum-report padding-btm-20">
                        <p></p>
                    </div>
                    <div class="datum-report padding-btm-20">
                        <div class="datum-report-item site-radio">
                            <span>优先等级</span>
                        </div>
                        <div class="datum-report-item site-radio">
                            <div>
                                <div class="mui-input-row mui-radio mui-left">
                                    <label>A级</label>
                                    <input name="radio" type="radio" value="1" >
                                </div>
                                <div class="mui-input-row mui-radio mui-left">
                                    <label>B级</label>
                                    <input name="radio" type="radio" value="2" >
                                </div>
                                <div class="mui-input-row mui-radio mui-left">
                                    <label>C级</label>
                                    <input name="radio" type="radio" value="3" checked="checked">
                                </div>
                                <div class="mui-input-row mui-radio mui-left">
                                    <label>D级</label>
                                    <input name="radio" type="radio" value="4" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="datum-report padding-btm-20">
                        注：C等级项目完成时间为7个工作日（仅供项目经理参考）
                    </div>
                    <div class="datum-report padding-btm-20">
                        <p></p>
                    </div>
                    <form id="file" action="" method="post" enctype="multipart/form-data">
                        <div class="datum-report padding-btm-20">
                            <div class="datum-report-item">
                                <span class="align-self">影像资料</span>
                            </div>
                        </div>
                        <div class="datum-report padding-btm-20">
                            <div class="datum-report-item">
                                <div class="datum-upload site-width">
                                    <div id="img_upload">
                                        <i class="iconfont icon-plus"></i>
                                        <input type="file" id="uploadFile" name="uploadFile" onchange="fileSelected2();" />
                                    </div>
                                    <c:if test="${siteQuestionInfo.imgPath !=null && siteQuestionInfo.imgPath !=''}">
                                        <c:forEach var="img" items="${siteQuestionInfo.imgs}">
                                            <div id="close_id">
                                                <img src="<%=basePathNuName%>shareFolder${img}" />
                                                <span class="iconfont icon-close" onclick="closeImg('${siteQuestionInfo.id}','${img}');"></span>
                                                <input type="hidden" />
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="site-button">
                        <input type="button" name="" onclick="save();" value="保存" />
                    </div>
                </div>
            </div>
            <div class="hide">
                视频内容
            </div>
            <div class="hide">
                分享
            </div>
            <div class="hide">
                我的
            </div>
        </div>
        <!--底部菜单-->
        <div class="wrap-foot">
            <div class="active">
                <i class="iconfont icon-ck"></i>
                查看
            </div>
            <div>
                <i class="iconfont icon-sp"></i>
                视频
            </div>
            <div>
                <i class="iconfont icon-fx"></i>
                分析
            </div>
            <div>
                <i class="iconfont icon-wo"></i>
                我
            </div>
        </div>
    </div>
    <script src="<%=basePath%>resources/mobile/js/ims.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath%>resources/mobile/js/mui.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function () {
            enterprise.init();

            $(".select").on("click", "a", function () {
                $(this).find("i").toggleClass("reverse");
                $(this).next("ul").slideToggle();
            });

            $("#siteId").on("click", "ul>li", function () {
                var _this = $(this), _dropd = _this.parent("ul"), _val = _this.data("val"), _txt = _this.text();
                _dropd.slideToggle();
                _dropd.siblings("[type='hidden']").val(_val);
                _dropd.siblings("a").find("span").text(_txt);
                _dropd.siblings("a").find("i").toggleClass("reverse");

                //联动方法 切换二级菜单的值
                var siteName = '${siteQuestionInfo.siteName}';
                if (siteName != null || siteName != '') {  //不为空时 比较值是否真的改变
                    if (_val == siteName) {

                    } else {
                        $("#productName").val("");
                        $("#productId a").find("span").text("--请选择--");
                        $("#ul2").empty();
                        $("#menuName").val("");
                    }
                    //$("#menuId a").find("span").text("--请选择--") ;
                    //alert(_val);
                    //加载菜单数据
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>/mobile/siteQuestionInfo/loadData.do",
                        data: {type: 1, deptCode: _val, serialNo: serialNo},
                        dataType: "json",
                        cache: false,
                        error: function (request) {
                            mui.toast('服务端错误，或网络不稳定，本次操作被终止。', {duration: 'long', type: 'div'})
                            console.log(request);
                        },
                        success: function (data) {
                            str_html = "";
                            $("#ul2").empty();
                            console.log(data);
                            var json = eval(data.xtJsons);
                            $.each(json, function (i, item) {
                                console.log("sss==" + json[i])
                                str_html = str_html + "<li data-val='" + json[i].id + "'>" + json[i].name + "</li>";

                            });
                            $("#ul2").html(str_html);
                        }
                    });

                }
            });

            $("#productId").on("click","ul>li",function(){

                var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
                _dropd.slideToggle();
                _dropd.siblings("[type='hidden']").val(_val);
                _dropd.siblings("a").find("span").text(_txt);
                _dropd.siblings("a").find("i").toggleClass("reverse");

                //联动方法 切换二级菜单的值
                var productName=_val;
                if(productName != null || productName != ''){  //不为空时 比较值是否真的改变

                    $("#menuName").val("");
                    $("#menuId a").find("span").text("--请选择--") ;
                    $("#ul3").html("");

                    //加载菜单数据
                    &lt;%&ndash;$.ajax({&ndash;%&gt;
                    &lt;%&ndash;type: "POST",&ndash;%&gt;
                    &lt;%&ndash;url:"<%=basePath%>/mobile/siteQuestionInfo/loadData.do",&ndash;%&gt;
                    &lt;%&ndash;data:{type:2,name:productName,serialNo:serialNo},&ndash;%&gt;
                    &lt;%&ndash;dataType:"json",&ndash;%&gt;
                    &lt;%&ndash;cache : false,&ndash;%&gt;
                    &lt;%&ndash;error: function(request) {&ndash;%&gt;
                    &lt;%&ndash;mui.toast('服务端错误，或网络不稳定，本次操作被终止。',{ duration:'long', type:'div' })&ndash;%&gt;
                    &lt;%&ndash;console.log(request);&ndash;%&gt;
                    &lt;%&ndash;},&ndash;%&gt;
                    &lt;%&ndash;success: function(data) {&ndash;%&gt;
                    &lt;%&ndash;console.log(data);&ndash;%&gt;
                    &lt;%&ndash;var json=eval(data.xtJsons);&ndash;%&gt;
                    &lt;%&ndash;$.each(json,function(i,item){&ndash;%&gt;
                    &lt;%&ndash;str_html3=str_html3+"<li data-val='"+json[i].cpmc+"'>"+json[i].cpmc+"</li>";&ndash;%&gt;

                    &lt;%&ndash;});&ndash;%&gt;
                    &lt;%&ndash;$("#ul3").html(str_html3);&ndash;%&gt;
                    &lt;%&ndash;}&ndash;%&gt;
                    &lt;%&ndash;});&ndash;%&gt;
                }
            });

            function fileSelected2(){
                //获取文件的内容

                var userId = $("#userId").val();
                var serialNo = $("#serialNo").val();
                var old_id = $("#id").val();
                var uploadFile = new FormData($("#file")[0]);
                //判断上传的只能是图片
                var f=document.getElementById("uploadFile").value;
                if(f=="") { alert("请上传图片");return false;}
                else {
                    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                        mui.toast('图片类型必须是.gif,jpeg,jpg,png中的一种',{ duration:'long(3500ms)', type:'div' });
                        return false;
                    }
                }

                //预览图片
                // var objUrl = getObjectURL($("#file")[0]); //获取图片的路径，该路径不是图片在本地的路径
                // if (objUrl) {
                //     $("#uu").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                // }

                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>mobile/siteQuestionInfo/saveAndUpdate.do?userId="+userId+"&serialNo="+serialNo+"&old_id="+old_id,
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
                                var imgs = "<div id=\"close_id\"><img src='<%=basePathNuName%>shareFolder"+obj.path+"'></img><span class=\"iconfont icon-close\" onclick=\"closeImg('+"+obj.id+"',"+"'"+obj.path+"');\"></span>\n</div>";
                                $(".datum-upload.site-width").append(imgs);
                                //$("#img_upload").append("<div id='close_id'><img src='<%=basePathNuName%>shareFolder${siteQuestionInfo.imgPath}' /></div>");
                                $("#id").val(obj.id);
                                //setTimeout("location.reload()",0);

                            } else {
                                mui.toast('上传失败',{ duration:'long(3500ms)', type:'div' });
                                //追加图片预览
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
                    url:"<%=basePath%>mobile/siteQuestionInfo/deleteImg.do",
                    data:{id:e,imgPath:imgPath},
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

        });
    </script>--%>

