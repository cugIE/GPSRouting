<%@ page import="com.util.OutputHelper" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.bean.Record" %>
<%@ page import="java.util.List" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.util.JAHelper" %>
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
	<!-- fullCalendar包 -->
	<link rel="stylesheet" type="text/css" href="assets/css/fullcalendar.css"> 
	<script src="assets/js/jquery.min.js"></script> 
	<script src="assets/js/jquery-ui-1.10.2.custom.min.js"></script> 
	<script src="assets/js/fullcalendar.min.js"></script> 
	
</head>
<body>
	<!-- <div class="easyui-panel" style="width:max;height:40px;padding:5px;">

	
	<a href="#" class="easyui-linkbutton" iconCls="icon-search">确定</a>
	</div> -->
	<div id="panelCal" class="easyui-panel" title="月巡检表" style="width:max;height:475px;padding:10px;"
		data-options="iconCls:'icon-save',tools:'#tt'">
					
			<div id='calendar'></div>
	</div>
	<div id="tt">
		<%-- <select id="sheet-list" class="easyui-combobox" name="dept"
			style=" width:150px;height:17px; margin-right: 5px" method="get"
			url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
			valueField="id" textField="name">
		</select> --%>
	<input class="easyui-combobox" id="sheet-list" style=" width:150px;height:17px; margin-right: 5px"
		method="get"
		url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
		valueField="id" textField="name">
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showResults()">确定</a>
	</div>			
	
		<script type="text/javascript" src="js/result-month.js"></script>
</body>
</html>