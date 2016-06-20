<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<%
		System.out.println(request.getParameter("branch_id"));
	%>
		<table id="people-data" title="表管理" class="easyui-datagrid" style="max-height:600px;"
		        url="GetAllSheetServlet?branch_id=<%=request.getParameter("branch_id")%>&isWeb=0"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
		        	<th field="name" width="30">表名</th>
		            <th field="intro" width="50">表介绍</th>
		            <th field="gener" width="30">创建人</th>
		        </tr>
		    </thead>
		    <%--<tbody>--%>
		    	<%--<tr>--%>
		    		<%--<td>日常表</td>--%>
		    		<%--<td>这是一张日常表</td>--%>
		    		<%--<td>2012年11月5日</td>--%>
		    		<%--<td>--%>
		    		<%--<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="addTab('日常表','sheet-data-detail.html')"></a>--%>
		    		<%--</td>--%>
		    	<%--</tr>--%>
		    	<%--<tr>--%>
		    		<%--<td>临时</td>--%>
		    		<%--<td>这是一张临时表</td>--%>
		    		<%--<td>2012年11月5日</td>--%>
		    		<%--<td>--%>
		    		<%--<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="addTab('临时表','sheet-data-detail.html')"></a>--%>
		    		<%--</td>--%>
		    	<%--</tr>--%>
		    <%--</tbody>--%>
		</table>
		<div id="toolbar">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSheet()">添加表</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="checkSheet()">查看表</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSheet()">修改表</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroySheet()">删除表</a>
		</div>

	</div>
	<script type="text/javascript" src="js/sheet-data.js"></script>
</body>
</html>