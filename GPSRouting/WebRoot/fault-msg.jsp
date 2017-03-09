<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>燃气巡检系统</title>
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="assets/easyui/themes/color.css">

	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
</head>				
<body>
		<table id="fault-data" title="故障信息" class="easyui-datagrid" style="max-height:600px;"
		        <%-- url="ManageFaultmsgServlet?action=list3&userBranchid=<%=session.getAttribute("SesBranchId") %>" --%>
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
		        	<th field="id" width="25" hidden="hidden">#</th>
		        	<th field="title" width="25">故障标题</th>
		        	<th field="time" width="25">故障时间</th>
		        	<th field="generName" width="25">上报人</th>
		        </tr>
		    </thead>
		    
		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			
			<!-- <div>
				从 <input class="easyui-datebox" style="width:80px">
				到 <input class="easyui-datebox" style="width:80px">
				选择巡检员：
				<input class="easyui-combobox" style="width:100px"
						url="data/combobox_data.json"
						valueField="id" textField="text">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">确定</a>
			</div> -->
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="checkFault()">审核</a>
			</div>
		
		</div>
	<script type="text/javascript" src="js/fault-msg.js"></script>
</body>
</html>