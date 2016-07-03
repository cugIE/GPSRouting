<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改密码" style="max-height:500px;width:400px;padding:30px 60px">
		<form action="ModPwdServlet" method="post">
			<div style="margin-bottom:20px">
				<div></div>
				<input type="text" id="username" name="username" disabled="true"
				value=<%=request.getSession().getAttribute("SesUser") %> class="easyui-textbox" data-options="prompt:'Enter a email address...',validType:'email'" style="width:100%;height:32px">
			</div>
			<div style="margin-bottom:20px">
				<div>请输入旧密码：</div>
				<input type="password" id="old_pwd" name="old_pwd" class="easyui-textbox" style="width:100%;height:32px">
			</div>
			<div style="margin-bottom:20px">
				<div>请输入新密码：</div>
				<input type="password" id="new_pwd" name="new_pwd" class="easyui-textbox" style="width:100%;height:32px">
			</div>
			<div style="margin-bottom:20px">
				<div>重复新密码：</div>
				<input type="password" id="new_pwd2" name="new_pwd2" class="easyui-textbox" style="width:100%;height:32px">
			</div>

			<div>
				<input type="submit" value="确认修改" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">
			</div>
		</form>
	</div>
	
</body>
</html>