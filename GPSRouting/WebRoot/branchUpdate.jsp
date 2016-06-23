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
	<link rel="stylesheet" type="text/css" href="css/dialog.css">
	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
</head>				
<body>
	<form id="fm" action="ManageBranchServlet?action=update2" method="post">
	<table id="people-data" title="修改部门信息" class="easyui-datagrid" style="max-height:600px;"	
	rownumbers="true" fitColumns="true" singleSelect="true">
	<%
		Branch branch = (Branch) request.getAttribute("branch");		
	%>

	<div class="ftitle">修改部门信息</div>
		
			<div class="fitem">
				<label>部门名称</label>
				<input type="hidden" id="branch_id" name="branch_id" value=<%=branch.getId() %> >
				<input type="text" id="branch_name" name="branch_name" value=<%=branch.getBranchName() %>
									class="easyui-validatebox" />
			</div>
			<div class="fitem">
				<label>部门类型</label>
				<select id="branch_type" name="branch_type" class="easyui-validatebox">
					<option value=<%=branch.getBranchType() %>><%=branch.getBranchType() %></option>
					<option value="管理">管理</option>
					<option value="站场">站场</option>
				</select>
			</div>
			<div class="fitem">
				<label>部门编码</label>
				<input type="text" id="branch_code" name="branch_code" class="easyui-validatebox" value=<%=branch.getBranchCode() %>>
			</div>
			<div class="fitem">
				<label>分公司名称</label>
				<input type="text" id="com_name" name="com_name" value=<%=branch.getComName() %>
									class="easyui-validatebox" />	
			</div>
			<div class="fitem">
				<label>分公司编码</label>
				<input type="text" id="com_id" name="com_id" value=<%=branch.getComId() %>
									class="easyui-validatebox" />	
			</div>
			<div >							
				<input type="submit" 
				value="提交">
				<input type="reset" 
				value="重置">
			</div>
			<br>	
	</table>
</form>
<script type="text/javascript" src="js/people-data.js"></script>
</body>
</html>