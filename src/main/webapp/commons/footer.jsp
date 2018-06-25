<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/22 0022
  Time: 下午 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!--底部菜单-->
<div class="wrap-foot">
    <div class="active" onclick="openIndexPage()">
        <i class="iconfont icon-task"></i>
        任务
    </div>
    <div onclick="siteLoad();">
        <i class="iconfont icon-site"></i>
        站点
    </div>
    <div onclick="onlineLoad();">
        <i class="iconfont icon-upload"></i>
        上传
    </div>
    <div>
        <a href="#popover" id="openPopover" class="iconfont icon-wo"
           style="color: #A4A5AB;">
            <span style="color: #A4A5AB; display: block;">我</span>
        </a>
    </div>
</div>
<%--弹出菜单--%>
<div id="popover" class="mui-popover">
    <ul class="mui-table-view">
        <li class="mui-table-view-cell"><a href="<%=basePath%>/mobile/tempSiteQuestion/wxStart.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}">注销：${serialName}</a></li>
        <li class="mui-table-view-cell"><a href="<%=basePath%>/mobile/tempSiteQuestion/qrCode.do?userId=${userId}&serialNo=${serialNo}">推广二维码</a></li>

    </ul>
</div>
<script type="text/javascript">
    function openIndexPage() {
        location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo=${serialNo}";
    }
    function siteLoad() {
        location.href="<%=basePath%>/mobile/siteInstall/list.do?userId=${userId}&serialNo=${serialNo}";
    }

    function onlineLoad() {
        location.href="<%=basePath%>/mobile/implementData/list.do?userId=${userId}&serialNo=${serialNo}"
    }
</script>