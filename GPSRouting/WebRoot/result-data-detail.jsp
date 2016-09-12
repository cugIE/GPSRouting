<%@ page import="com.util.OutputHelper" %>
<%@ page import="com.util.TeamidtoName" %>
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
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <link rel="stylesheet" type="text/css" href="css/dialog.css">

    <script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
</head>
<body>
<%
	TeamidtoName teamidtoName = new TeamidtoName();
	String braName = teamidtoName.braid2name(""+session.getAttribute("SesBranchId"));
	String shift = request.getParameter("shift");
	String time = request.getParameter("time");
    String period_id = request.getParameter("period_id");
    String date = request.getParameter("date");
    String end = date + " 23:59";
    String start = date + " 00:00";
    List<Record> recordList2 = Record.getAllRecordFromPeriod(period_id, start, end);
%>
<div id="record" class="easyui-panel" title="报表" style="padding: 20px; height: 800px;" data-options="iconCls:'icon-save',tools:'#tt'">
		<div style="float: left; width: 15%;">
			<i class="fa fa-asterisk bk-padding-right-10"></i>站场： 
			<i class="bk-padding-right-15" id="eva-record-id"><b><%=braName %></b></i>
		</div>
		 <div style="float: left; width: 20%;">
                    巡检日期：
                    <i class="bk-padding-right-15" ><b><%=date %></b></i>
                </div>
         <div style="float: left; width: 15%;">
                   班次：
                    <i class="bk-padding-right-15" ><b><%=shift %></b></i>
                </div>
         <div style="float: left; width: 15%;">
                   时间：
                    <i class="bk-padding-right-15" ><b><%=time %></b></i>
                </div>
          <div style="float: left; width: 15%;">
                   巡检人：
                    <i class="bk-padding-right-15" ><b><%Record rcdRecord = recordList2.get(0);out.print(rcdRecord.getGener()); %></b></i>
                </div>
          <div style="float: left; width: 15%;">
                    <i class="fa fa-database bk-padding-right-10"></i>审核状态：
                    
                    <i class="bk-padding-right-15" ><b>
                    <%
                    		int k = 0;
                    		for(int i = 0; i < recordList2.size(); i++){
                    			Record rcd = recordList2.get(i);
                    			if(rcd.getStatus().equals("0")){
                    				k++;
                    			}
                    		}
                    		if(k > 0){
                    			out.print("未完成");
                    		}else{
                    			out.print("已完成");
                    		}
                    %></b></i>
                </div>
                <div id="export" style="float: left; width: 5%;">
                	<a data-type="xls" href="javascript:;">导出</a>
                </div>
    <!-- <div id="export">
		<a data-type="json" href="javascript:;">导出json</a>
		<a data-type="txt" href="javascript:;">导出txt</a>
		<a data-type="csv" href="javascript:;">导出csv</a>
		<a data-type="xls" href="javascript:;">导出excel</a>
		<a data-type="doc" href="javascript:;">导出word</a>
	</div>   -->          
	<table id="table2" class="imagetable"  cellspacing="0" style="margin-top:30px">
    <thead>
    <tr>
        <th>巡检区域</th>
        <th>巡检点</th>
        <th>巡检内容</th>
        <th>可选结果</th>
        <th>已测结果</th>
        <th>正常结果</th>



    </tr>
    </thead>
    <tbody>
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
					<%
						}
					%>
		<td><%=jso.get("title")%></td>
        <td><%=jso.get("possasws")%></td>
        <%
        	if(jso.get("error").equals("1")){
        %>
        <td><font color="red"><%=jso.get("choosedasws")%></td>
        <%
        	}else{
        %>
        <td><font color="blue"><%=jso.get("choosedasws")%></td>
        <%
        	}
        %>
        <td><font color="blue"><%=jso.get("normalasws")%></td>
    </tr>
    <%}}%>
    </tbody>
</table>

</div>
<div id="tt">
	<a href="javascript:void(0)" class="icon-map" onclick="mapShow('<%=rcdid%>')"></a> 
    <a href="javascript:void(0)" class="icon-edit" onclick="newCheck()"></a>
    <!-- <div id="export"> 
    	<a data-type="xls" href="javascript:;" class="icon-print" ></a> 
    </div>  -->     
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

<div id="region-map" class="easyui-panel" style="width:600px;height:400px;padding:10px 20px"
     closed="true">
     <div id="allmap"></div>
</div>

<script type="text/javascript" src="js/result-data-detail.js"></script>
<script type="text/javascript" src="js/Blob.js"></script>
<script type="text/javascript" src="js/FileSaver.js"></script>
<script type="text/javascript" src="js/tableExport.js"></script>


</body>
</html>
