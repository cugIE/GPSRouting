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
		<table id="question-data" title="巡检内容" class="easyui-datagrid" style="max-height:500px;"
		        url="GetAllQuestionServlet?index=region&region_id=<%=request.getParameter("region_id")%>"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
					<th field="id" width="10"></th>
		        	<th field="title" width="40">问题</th>
					<th field="type" width="40">巡检点</th>
		            <th field="possasws" width="40">答案</th>
		            <th field="normalasws" width="20">正确答案</th>
		        </tr>
		    </thead>

		</table>
		<div id="toolbar">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newQuestion()">添加问题</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editQuestion()">修改问题</a>
		    
		    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyQuestion()">删除问题</a>
		</div>

		<div id="add-question-dlg" class="easyui-dialog" style="width:300px;height:300px;padding:10px 20px"
			 closed="true" buttons="#sheet-dlg-buttons">
			<div class="ftitle">添加表</div>
			<form id="add-question-form" method="post">
				<div class="fitem">
					<label>巡检点</label>
					<input name="type" class="easyui-validatebox" required="true" style=" width:160px">
				</div>
				<div class="fitem">
					<label>问题标题</label>
					<input name="title" class="easyui-validatebox" required="true" style=" width:160px">
				</div>

				<div class="fitem">
					<label>答案</label>
					<input name="possasws" class="easyui-validatebox" style=" width:160px" >
				</div>
				<div class="ftips">
					每一种答案请用英文";"号隔开,如果没有固定答案请输入无;
				</div>
				<div class="fitem">
					<label>正确答案</label>
					<input name="normalasws" class="easyui-validatebox" style=" width:160px" >
				</div>
				<%--<div class="fitem">--%>
				<%--<label>所在部门</label>--%>
				<%--<input name="branch_id" value="<%=request.getParameter("branch_id")%>" class="easyui-validatebox" >--%>
				<%--</div>--%>

			</form>
		</div>
		<div id="sheet-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveQuestion()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-question-dlg').dialog('close')">取消</a>
		</div>

	<script type="text/javascript" src="js/region-data-detail.js"></script>
</body>
</html>