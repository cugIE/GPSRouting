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
					<th field="id" width="5"></th>
		        	<th field="shift" width="10">班次</th>
					<th field="time" width="10">时间点</th>
					<th field="all" width="40">全部区域</th>
		            <th field="done" width="40">已检查区域</th>
		        </tr>
		    </thead>

		</table>
		<div id="toolbar-1" style="padding:5px;height:auto">
			
			<div>
				选择时间: <input class="easyui-datebox" id="pick-date" style="width:80px">
				巡检表：
				<input class="easyui-combobox" id="sheet-list" style=" width:100px; margin-right: 5px"
					   method="get"
					   url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
					   valueField="id" textField="name">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showResults()">确定</a>
				<div style="float: right">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="showDetail()">查看报表</a>
				</div>
			</div>
		
		</div>
		<script type="text/javascript" src="js/result-data.js"></script>
</body>
</html>