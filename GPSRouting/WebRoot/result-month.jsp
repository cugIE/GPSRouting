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
	<div class="easyui-panel" style="width:max;height:40px;padding:5px;">

	
	<a href="#" class="easyui-linkbutton" iconCls="icon-search">确定</a>
	</div>
	<div class="easyui-panel" title="月巡检表" style="width:max;height:480px;padding:10px;"
		toolbar="#toolbar">
		
		<%-- <div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',split:true" style="width:100px;height:55px;padding:10px">
				选择时间：<input class="easyui-datebox" id="pick-date" style="width:80px">
				巡检表：
				<input class="easyui-combobox" id="sheet-list" style=" width:100px; margin-right: 5px"
					   method="get"
					   url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
					   valueField="id" textField="name">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">确定</a>
			</div>
			<div data-options="region:'center'" style="width:100px;padding:10px">
				<!-- <div id='calendar'></div> -->
			</div> --%>
					
			<div id='calendar'></div>
	</div>			
	
		<script type="text/javascript" src="js/result-month.js"></script>
</body>
</html>