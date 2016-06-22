<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />

</head>				
	<body>
		<table id="region-data" title="区域管理" class="easyui-datagrid" style="max-height:500px;"
		        url="GetAllRegionServlet?index=branch&branch_id=<%=request.getParameter("branch_id")%>"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
					<th field="id" width="30"></th>
		        	<th field="name" width="40">区域名称</th>
					<th field="intro" width="80">区域介绍</th>
		            <th field="branch" width="40">所属部门</th>
		            <th field="type" width="15">区域类型</th>
		        </tr>
		    </thead>

		</table>
		<div id="toolbar">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRegion()">添加区域</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRegion()">修改区域</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkRegion()">查看区域</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkQRcode()">查看二维码</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyRegion()">删除区域</a>
		</div>

	</div>
		<div id="add-region-dlg" class="easyui-dialog" style="width:300px;height:240px;padding:10px 20px"
			 closed="true" buttons="#sheet-dlg-buttons">
			<div class="ftitle">添加表</div>
			<form id="add-region-form" method="post">
				<div class="fitem">
					<label>区域名</label>
					<input name="name" class="easyui-validatebox" required="true" style=" width:160px">
				</div>
				<div class="fitem">
					<label>区域介绍</label>
					<input name="intro" class="easyui-validatebox" style=" width:160px" >
				</div>
                <div class="fitem">
                    <label>区域类型</label>
					<select id="cc" class="easyui-combobox" name="type" style="width:160px;">
						<option value="site">巡站点</option>
						<option value="route">巡线点</option>
					</select>
                </div>
				<%--<div class="fitem">--%>
				<%--<label>所在部门</label>--%>
				<%--<input name="branch_id" value="<%=request.getParameter("branch_id")%>" class="easyui-validatebox" >--%>
				<%--</div>--%>

			</form>
		</div>
		<div id="sheet-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRegion()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-region-dlg').dialog('close')">取消</a>
		</div>
        <div id="show-qrcode" class="easyui-dialog" style="width:300px;height:120px;padding:10px 20px"
             closed="true" buttons="#show-qrcode-button">
            <div class="fitem">
                <label>输入尺寸</label>
                <input id="qrcode-size" type="number" name="size" class="easyui-validatebox" required="true" style=" width:160px">
            </div>
        </div>
        <div id="show-qrcode-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="showQRcode()">确定</a>
        </div>

	</br>

	<div class="easyui-panel" title= "地图显示" toolbar="#tb" style="background-color: #eee">
		<div id="tb" style="padding: 1px">
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看巡站点</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-tip" plain="true">所有线路</a>
			</div>
		</div>
		<div style="height: 1px;background-color: #ccc;"></div>
		<div id="allmap" style="height: 500px;">
			
		</div>
	</div>


	<script type="text/javascript" src="js/region-data.js"></script>
</body>
</html>