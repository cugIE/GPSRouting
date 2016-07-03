<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 
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
	<link rel="stylesheet" type="text/css" href="css/dialog.css">
	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
</head>				
<body>
		<%
			String datetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); 
		%>
		<table id="announce-data" title="公告" class="easyui-datagrid" style="max-height:600px;"        
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		  
		    <thead>
		        <tr>
		        	<th field="id" width="20" >#</th>
		            <th field="title" width="20" >标题</th>
		            <th field="content" width="20" >内容</th>
		            <th field="time" width="20" >发布时间</th>
		            <th field="generName" width="20" >发布人</th>
		        </tr>
		    </thead>
		    
		</table>
		<div id="toolbar">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMsg()">添加公告</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMsg()">修改公告</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyMsg()">删除公告</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="pushMsg()">发布公告</a>
		</div>

	<div id="dlg-add" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">添加公告</div>
		<form id="fm-msg" method="post">
			<div class="fitem">
				<label>标题</label>
				<input name="title" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>内容</label>
				<input name="content" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>发布时间</label>
				<input type="text" name="timeB" value=<%=datetime %> class="easyui-validatebox" disabled="disabled" >
				<input type="hidden" name="time" value=<%=datetime %> class="easyui-validatebox" >
			</div>
			<div class="fitem">
				<label>发布人员</label>
				<input type="text"  name="generName" value=<%=session.getAttribute("SesName") %> class="easyui-validatebox" disabled="disabled"  >
				<input name="generId" value=<%=session.getAttribute("SesId") %> class="easyui-validatebox" type="hidden">
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMsg()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-add').dialog('close')">取消</a>
	</div>
	<script type="text/javascript" src="js/announce-data.js"></script>
</body>
</html>