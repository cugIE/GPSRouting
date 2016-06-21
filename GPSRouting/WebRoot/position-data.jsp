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
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />

</head>				
	<body>
	
	<div class="easyui-panel" title= "地图显示" toolbar="#tb" style="background-color: #eee">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="displayEquip()">查看巡站点</a>
				&nbsp; 选择线路：<select size="1" id="select_route_list" style="width: 100px"></select>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchRoute()">确定</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="refreshMap()">刷新</a>
			</div>
			<div hidden="hidden" id = "search-trace">
				从 <input id="start-date" class="easyui-datetimebox" style="width:150px">
				到 <input id="end-date" class="easyui-datetimebox" style="width:150px">

				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showTrace()">确定</a>
			</div>

		</div>
		<div style="height: 1px;background-color: #ccc;"></div>
		<div id="allmap" style="height: 500px;">
			
		</div>
	</div>

	<script type="text/javascript" src="js/position-data.js"></script>
</body>
</html>