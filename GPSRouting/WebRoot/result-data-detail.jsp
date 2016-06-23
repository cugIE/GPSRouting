<%@ page import="com.util.OutputHelper" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.bean.Record" %>
<%@ page import="java.util.List" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.util.JAHelper" %><%--
  Created by IntelliJ IDEA.
  User: XinLi
  Date: 16/6/21
  Time: 下午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>燃气巡检系统</title>
    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/color.css">

    <script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
</head>
<body>
<%
    String period_id = request.getParameter("period_id");
    String date = request.getParameter("date");
    String end = date + " 23:59";
    String start = date + " 00:00";

%>
<div class="easyui-panel" title="报表" style="padding: 20px; height: 800px;">
<table style="width: 100%" border="1" bordercolor="black" cellspacing="0">
    <tr>
        <td>巡检点</td>
        <td>巡检内容</td>
        <td>可选结果</td>
        <td style="color:'#e4a555'">已测结果</td>
        <td>正常结果</td>



    </tr>
    <%
        List<Record> recordList = Record.getAllRecordFromPeriod(period_id, start, end);
        for(int i = 0; i < recordList.size(); i++){
            Record rcd = recordList.get(i);
            rcd.getRegion();
            String RegionName = "";
            String TypeName="";
            JSONArray jalist = JSONArray.fromObject(rcd.getAsws());
            System.out.println(jalist.toString());
            for(int j = 0; j<jalist.size();j++){
                JSONObject jso =  jalist.getJSONObject(j);

    %>
    <tr>
        <%
            if (!RegionName.equals(rcd.getRegion())){
                RegionName = rcd.getRegion();


        %>

        <td rowspan="<%=jalist.size()%>"><a href="#" onclick="parent.addTab('<%=rcd.getRegion()+'-'+rcd.getId()%>','result-data-detail-line.jsp?record_id=<%=rcd.getId()%>')"><%=rcd.getRegion()%></a></td>

    <%

        }

        if (!TypeName.equals(jso.getString("type"))){
            TypeName = jso.getString("type");


    %>

        <td rowspan="<%=JAHelper.GetCount(TypeName,jalist)%>"><%=TypeName%></td>
        <%}

        %>
        <td><%=jso.get("title")%></td>
        <td><%=jso.get("possasws")%></td>
        <td><%=jso.get("choosedasws")%></td>
        <td><%=jso.get("normalasws")%></td>
    </tr>
    <%}}%>

</table>
</div>
</body>
</html>
