<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
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
		<div class="easyui-panel" title= "巡检轨迹查看" toolbar="#tb" style="background-color: #eee">
			<jsp:useBean id="peopService" class="com.service.PeopleService" scope="session"></jsp:useBean>
			<%
				JSONArray JA = new JSONArray();
				JA = (JSONArray)request.getAttribute("recordlist");
				/* List<Record> recordList = (List<Record>)request.getAttribute("recordlist"); */
				String rcdName = null;
				String rcdGps = null;
				for(int i = 0; i<JA.size();i++){
					JSONObject js = new JSONObject();
					js= JA.getJSONObject(i);
					rcdName = rcdName + ";" + js.getString("rcdName");
					rcdGps = rcdGps + ";" + js.getString("rcdGps");
				}
				/* Record rcd = null;
				String[] rcdName = null;
				String[] rcdGps = null;
				for(int i = 0;i<JA.size();i++){
					rcd= recordList.get(i);
					rcdName[i] = rcd.getRegion();
					rcdGps[i] = rcd.getGps();
				} */

			%>
		
		<div id="a" display:none>
			<%-- <a class="btn Alarm-longitude" value="<%=longitude %>"></a>
			<a class="btn Alarm-latitde" value="<%=latitude %>"></a> --%>
			<a class="btn recordGps" value="<%=rcdGps %>"></a>
			<a class="btn recordName" value="<%=rcdName %>"></a>
			
		</div>	
		
		<div style="height: 1px;background-color: #ccc;"></div>
		<div id="allmap" style="height: 500px;">
			
		</div>								
		
	</div>
	
	<script type="text/javascript" src="js/records-position.js"></script>

</body>
</html>