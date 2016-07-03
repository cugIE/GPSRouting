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
	<table id="log-data" title="系统日志" class="easyui-datagrid" style="max-height:600px;"
	rownumbers="true" fitColumns="true" singleSelect="true">
	<jsp:useBean id="service7" class="com.service.SystemService"
		scope="session">
	</jsp:useBean>
	<%
	List<LogInfo> projectlist7 = service7.fill();
		Iterator<LogInfo> iterproject7 = projectlist7.iterator();
	%>
	<thead>
		<tr>
			<th field="id" width="25" >#</th>
			<th field="msg" width="25">操作内容</th>
			<th field="time" width="25">时间</th>
			<th field="user" width="25">操作人</th>
		</tr>
	</thead>
	<tbody>
		<%
		while (iterproject7.hasNext()) {
			LogInfo logInfo2 = iterproject7.next();
			%>
			<tr>
				<td><%=logInfo2.getId() %></td>
				<td><%=logInfo2.getMsg() %></td>
				<td><%=logInfo2.getCreattime() %></td>
				<td><%=logInfo2.getUsername() %>
				</tr>
				<%
			}
		%>

	</tbody>
		</table>
		
	
	<script type="text/javascript" src="js/people-data.js"></script>
</body>
</html>