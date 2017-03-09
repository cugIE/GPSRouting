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
		<table id="result-data" title="按照巡检班次" class="easyui-datagrid" style="max-height:600px;"
		        url="GetAllPeriodServlet"
			   	method="get"
		        toolbar="#toolbar-1"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
					<th field="id" width="0" hidden="hidden"></th>
					<th field="date" width="10">日期</th>
		        	<th field="shift" width="8">班次</th>
					<th field="time" width="8">时间点</th>
					<th field="geners" width="14">巡检人</th>
					<th field="startTime" width="18">创建时间</th>
		            <th field="submitTime" width="18">提交时间</th>
					<th field="all" width="12">全部区域</th>
		            <th field="done" width="12">已检查区域</th>
		        </tr>
		    </thead>

		</table>
		<div id="toolbar-1" style="padding:5px;height:auto">
			
			<div>
				选择时间: <input class="easyui-datebox" id="pick-date" style="width:100px">
				巡检表：
				<input class="easyui-combobox" id="sheet-list" style=" width:100px; margin-right: 5px"
					   method="get"
					   url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
					   valueField="id" textField="name">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showResults()">确定</a>
				<div style="float: right">
					<a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteRecord()">删除记录</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="showDetail()">查看报表</a>
				</div>
			</div>
		
		</div>
		<script type="text/javascript" src="js/result-data.js"></script>
</body>
</html>