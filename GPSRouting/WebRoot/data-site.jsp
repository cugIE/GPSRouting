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
	<style type="text/css">
		body,html{
			margin:0px;
			height:100%;
		}
		.font-bold{
			font-weight:bold;
		}
	</style>
</head>				
<body>
	<jsp:useBean id="siteService" class="com.service.SystemService"
								scope="session">
	</jsp:useBean>
	<%
		Site site = siteService.fillsite("1");
	%>
	<div class="easyui-panel" title="修改网站参数" style="max-height:500px;padding:30px 250px">
		<form action="ManageSystemServlet?action=updatesite2" method="post">
			<div style="margin-bottom:20px">
				<div class="font-bold">网站窗口标题：</div>
				<input type="text" id="site_title" name="site_title" class="easyui-textbox" style="width:100%;height:32px" required="true"
				value=<%=site.getWebtitle() %>>
			</div>
			<div style="margin-bottom:20px">
				<div class="font-bold">网站广告标题：</div>
				<input type="text" id="site_ad" name="site_ad" class="easyui-textbox" style="width:100%;height:32px" required="true"
				value=<%=site.getWebad() %>>
			</div>
			<div style="margin-bottom:20px">
				<div class="font-bold">网站页脚文字：</div>
				<input type="text" id="site_word" name="site_word" class="easyui-textbox" style="width:100%;height:32px" required="true"
				value=<%=site.getWebword() %>>
			</div>
			<div style="margin-bottom:20px">
				<div class="font-bold">网站所属单位：</div>
				<input type="text" id="site_com" name="site_com" class="easyui-textbox" style="width:100%;height:32px" required="true"
				value=<%=site.getComname() %>>
			</div>

			<div>
				<input type="submit" value="确认修改" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">
			</div>
		</form>
	</div>
	
</body>
</html>