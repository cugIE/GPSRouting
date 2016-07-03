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

	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
</head>				
<body>
		<div class="easyui-panel" title= "警报位置" toolbar="#tb" style="background-color: #eee">
			<jsp:useBean id="peopService" class="com.service.PeopleService" scope="session"></jsp:useBean>
			<%
				AlarmMsg alarmMsg = (AlarmMsg)request.getAttribute("alarmMsg");
				String gps = alarmMsg.getAlarmAddress();
				String[] split = gps.split(",");
				String longitude = split[0].toString();
				String latitude = split[1].toString();  
			%>
		<!-- <div id="tb" style="padding: 1px">
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看巡站点</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-tip" plain="true">所有线路</a>
			</div>
		</div> -->
		<div id="a" display:none>
			<a class="btn Alarm-longitude" value="<%=longitude %>"></a>
			<a class="btn Alarm-latitde" value="<%=latitude %>"></a>
			<a class="btn Alarm-time" value="<%=alarmMsg.getAlarmTime() %>"></a>
			<a class="btn Alarm-genername" value="
			<%
			People p = peopService.fill(alarmMsg.getGener_id());
			String generName = p.getName();
			out.print(generName);
			%>"></a>
		</div>	
		
		<div style="height: 1px;background-color: #ccc;"></div>
		<div id="allmap" style="height: 500px;">
			
		</div>								
		
	</div>
	
	<script type="text/javascript" src="js/alarm-position.js"></script>

</body>
</html>