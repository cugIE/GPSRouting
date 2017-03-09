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
		<table id="alarm-data" title="警报信息" class="easyui-datagrid" style="max-height:600px;"
		       
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
		        	<th field="id" width="10" hidden="hidden">#</th>
		        	<th field="title" width="18">警报标题</th>
		        	<th field="time" width="18">警报时间</th>
		        	<th field="address" width="18">地点</th>
		        	<th field="generName" width="18">提交人</th>
		        	<th field="stateH" width="18">处理状态</th>
		        </tr>
		    </thead>
		    
		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="alarmPOI()">查看警报位置</a>
			</div>
			<!-- <div>
				从 <input class="easyui-datebox" style="width:80px">
				到 <input class="easyui-datebox" style="width:80px">
				选择巡检员：
				<input class="easyui-combobox" style="width:100px"
						url="data/combobox_data.json"
						valueField="id" textField="text">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">确定</a>
			</div> -->
		
		</div>
	<script type="text/javascript" src="js/alarm-msg.js"></script>
</body>
</html>