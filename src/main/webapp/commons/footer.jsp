<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/22 0022
  Time: 下午 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!--底部菜单-->
<script type="text/javascript">
    function openIndexPage() {
        location.href = "<%=basePath%>mobile/tempSiteQuestion/index.do?userId=${userId}&serialNo=${serialNo}";
    }
    function siteLoad() {
        location.href="<%=basePath%>/mobile/siteInstall/list.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}";
    }

    function onlineLoad() {
        location.href="<%=basePath%>/mobile/implementData/list.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}"
    }

    function userCenter() {
        location.href="<%=basePath%>/mobile/userCenter/list.do?userId=${userId}&serialNo=${serialNo}&serialName=${serialName}&isManager=${isManager}";
    }
</script>