<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%><html>
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
		<table id="time-data" title="班次时间点管理" class="easyui-datagrid" style="max-height:600px;"
		        url="GetAllPeriodServlet?sheet_id=<%=request.getParameter("sheet_id")%>"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
					<th field="id" width="20">班次</th>
		        	<th field="shift" width="30">班次</th>
		            <th field="time" width="40">时间点</th>
		            <th field="gener" width="50">创建者</th>
		        </tr>
		    </thead>
		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
	 			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newShift()">添加班次</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTime()">添加时间点</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTime()">操作时间点</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyTime()">删除时间点</a>
			</div>
			<div>
				<div style="margin-left:5px;">
		    	选择班次：
				<input class="easyui-combobox" style=" width:100px"
						url="data/combobox_data.json"
						valueField="id" textField="text">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">查看</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="destroyShift()">删除</a>
			</div>
			</div>
		
		</div>

</body>
</html>