<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.bean.People"
    pageEncoding="UTF-8"%>
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
	<style type="text/css">
	body{text-align: center;}
	.login-pannel{position: absolute;
		left: 50%;
		top: 50%;
		width:200px;
		height:100px;
		margin-left:-100px;
		margin-top:-50px;}
		</style>
	</head>
	<body>
		<div id="login-pannel" class="easyui-layout" style="left:450px;width:700px;height:350px;">
			<div style="text-align:center">
				<div id="login" class="easyui-panel" title="用&nbsp;户&nbsp;登&nbsp;录" data-options="footer:'#ft'" style="left:250px;width:400px;height:200px;padding:10px;">
					<form action="LoginServlet" method="post">
						<div class="fitem">
							<label>用户名</label>
							<div></div>
							<input type="text" id="username" name="username" class="easyui-validatebox" required="true">
						</div>
						<div class="fitem">
							<label>密码</label>
							<div></div>
							<input type="password" id="password" name="password" class="easyui-validatebox" required="true">
						</div>
						<div>
							<input type="submit" value="登录">
							<input type="reset" value="取消">
						</div>
						<hr style="height:1px;border:none;border-top:1px solid #555555;" />
						
						<div id="ft" style="padding:5px;" text-align="center">
							GPSRouting
						</div>
					</form>
				</div>
			</div>
		</div>

	</body>
	</html>