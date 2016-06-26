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
<div class="easyui-panel" title="报表" style="padding: 20px; height: 800px;" data-options="iconCls:'icon-save',closable:true,tools:'#tt'">
<table style="width: 100%" border="1" bordercolor="black" cellspacing="0">
    <tr>
        <td>巡检区域</td>
        <td>巡检点</td>
        <td>巡检内容</td>
        <td>可选结果</td>
        <td style="color:'#e4a555'">已测结果</td>
        <td>正常结果</td>



    </tr>
    <%
        List<Record> recordList = Record.getAllRecordFromPeriod(period_id, start, end);
        String rcdid = "";
        for(int i = 0; i < recordList.size(); i++){
            Record rcd = recordList.get(i);
            if(i==0){
                rcdid = rcd.getId();
            }
            else {
                rcdid = rcdid + "," + rcd.getId();
            }
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
                String isCheck="已审核";
                if(rcd.getStatus().equals("0")){
                    isCheck="未审核";
                }
        %>


        <td rowspan="<%=jalist.size()%>"><a href="#" onclick="parent.addTab('<%=rcd.getRegion()+'-'+rcd.getId()%>','result-data-detail-line.jsp?record_id=<%=rcd.getId()%>')"><%=rcd.getRegion()%></a><%=isCheck%></td>

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
<div id="tt">
    <a href="javascript:void(0)" class="icon-edit" onclick="newCheck()"></a>
</div>
<div id="add-check-dlg" class="easyui-dialog" style="width:300px;height:300px;padding:10px 20px"
     closed="true" buttons="#sheet-dlg-buttons">
    <div class="ftitle">审核</div>
    <form id="add-question-form" method="post">
        <div class="fitem">
            <label>评论</label>
            <textarea id="record-comment" name="intro" rows="4" class="form-control" placeholder="输入评论内容"></textarea>
        </div>

        <%--<div class="fitem">--%>
        <%--<label>所在部门</label>--%>
        <%--<input name="branch_id" value="<%=request.getParameter("branch_id")%>" class="easyui-validatebox" >--%>
        <%--</div>--%>

    </form>
</div>
<div id="sheet-dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkAllRecord('<%=rcdid%>')">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-check-dlg').dialog('close')">取消</a>
</div>

<script type="text/javascript" src="js/result-data-detail.js"></script>
</body>
</html>
