<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%><html>
<head>
	<meta charset="UTF-8">
	<title>燃气巡检系统</title>
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="css/dialog.css">
	<link rel="stylesheet" type="text/css" href="css/checkbox.css">

	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>

</head>				
<body>
		<table id="time-data" title="班次时间点管理" class="easyui-datagrid" style="max-height:600px;"
		        url="GetAllPeriodServlet?sheet_id=<%=request.getParameter("sheet_id")%>"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
					<th field="id" width="10"></th>
		        	<th field="shift" width="30">班次</th>
		            <th field="time" width="40">时间点</th>
		            <th field="gener" width="50">创建者</th>
		        </tr>
		    </thead>
		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
	 			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newShift()">添加班次</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTime()">添加时间点</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTime()">操作时间点</a>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyTime()">删除时间点</a>
			</div>
			<div>
				<div style="margin-left:5px;">
		    	选择班次：
				<input id="shift-search" class="easyui-combobox" style=" width:100px"
					   url="GetAllPeriodServlet?index=shift&sheet_id=<%=request.getParameter("sheet_id")%>"
					   data-options="valueField:'shift',textField:'text'">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchShift()">查看</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="destroyShift()">删除</a>
			</div>
			</div>
		
		</div>

		<div id="edit-time-dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
			 closed="true" buttons="#time-edit-dlg-buttons">
				<div style="padding: 20px">
					<div style="float: left; width: 45%;" >
						已选择
						<table style="font-size: 14px; border: 1px solid #6495ED; width: 100%;">
							<tbody id="SelectedRegions">
							</tbody>
						</table>
					</div>
					<div style="float: left; width: 45%; margin-left: 10%">
						未选择
						<table style="font-size: 14px; border: 1px solid #6495ED; width: 100%;">
							<tbody id="RestRegions">
							</tbody>
						</table>
					</div>
				</div>
		</div>
		<div id="add-time-dlg" class="easyui-dialog" style="width:200px;height:250px;padding:10px 20px"
			 closed="true" buttons="#time-dlg-buttons">
			<div class="ftitle">添加时间点</div>
			<form id="add-time-form" method="post">
				<div class="fitem">
					<label>班次名</label>
					<input id="shift-list" name="shift" style=" width:100px">

				</div>
				<div class="fitem">
					<label>时间点</label>
					<input id="times-list" name="time" style=" width:100px">
				</div>
			</form>
		</div>
		<div id="time-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveTime()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-time-dlg').dialog('close')">取消</a>
		</div>
		<div id="add-shift-dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px"
			 closed="true" buttons="#shift-dlg-buttons">
			<div class="ftitle">添加班次</div>
			<form id="add-sheet-form" method="post">
				<div class="fitem">
					<label>班次名</label>
					<input id="shift-name" name="shift" class="easyui-validatebox" required="true">
				</div>
				<div class="checkbox-row">
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox1" name="times" value="00:00">
						<label for="checkbox1"> 00:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox2" name="times" value="02:00">
						<label for="checkbox2"> 02:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox3" name="times" value="04:00">
						<label for="checkbox3"> 04:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox4" name="times" value="06:00">
						<label for="checkbox4"> 06:00</label>
					</div>
				</div>
				<div class="checkbox-row">
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox5" name="times" value="08:00">
						<label for="checkbox5"> 08:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox6" name="times" value="10:00">
						<label for="checkbox6"> 10:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox7" name="times" value="12:00">
						<label for="checkbox7"> 12:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox8" name="times" value="14:00">
						<label for="checkbox8"> 14:00</label>
					</div>
				</div>
				<div class="checkbox-row">
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox9" name="times" value="16:00">
						<label for="checkbox9"> 16:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox10" name="times" value="18:00">
						<label for="checkbox10"> 18:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox11" name="times" value="20:00">
						<label for="checkbox11"> 20:00</label>
					</div>
					<div class="checkbox-item">
						<input type="checkbox" id="checkbox12" name="times" value="22:00">
						<label for="checkbox12"> 22:00</label>
					</div>
				</div>
				<%--<div class="fitem">--%>
				<%--<label>所在部门</label>--%>
				<%--<input name="branch_id" value="<%=request.getParameter("branch_id")%>" class="easyui-validatebox" >--%>
				<%--</div>--%>

			</form>
		</div>
		<div id="shift-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveShift()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-shift-dlg').dialog('close')">取消</a>
		</div>
		<script type="text/javascript" src="js/sheet-data-detail.js"></script>
</body>
</html>