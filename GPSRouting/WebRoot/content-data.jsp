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
</head>				
<body>
		<table id="question-data" title="巡检内容" class="easyui-datagrid" style="max-height:600px;"
		        url="GetAllQuestionServlet"
		        toolbar="#toolbar"
                method="get"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
                    <th field="id" width="10"></th>
		        	<th field="region" width="20">检查区域</th>
		            <th field="title" width="60">巡检内容</th>
		            <th field="possasws" width="40">内容答案</th>
		            <th field="normalasws" width="30">正确答案</th>
		        </tr>
		    </thead>

		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			
			<div>
				<div style="margin-left:5px;">
					选择巡检表表：
					<input class="easyui-combobox" id="sheet-list" style=" width:100px; margin-right: 5px"
						   method="get"
						   url="GetAllSheetServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
						   valueField="id" textField="name">
                    选择班次和时间点：
                    <input class="easyui-combobox" id="period-list" style=" width:100px">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showContent()">查看</a>
			    </div>
			</div>
		
		</div>
        <script type="text/javascript" src="js/content-data.js"></script>
</body>
</html>