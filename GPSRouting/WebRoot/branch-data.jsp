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
		<table id="branch-data" title="部门管理" class="easyui-datagrid" style="max-height:600px;"
		        url="ManageBranchServlet?action=list"
		        toolbar="#toolbar"
		        rownumbers="true" fitColumns="true" singleSelect="true"
		        pagination="true">

		     <jsp:useBean id="bservice" class="com.service.BranchService"
		        scope="session">
		    </jsp:useBean>
		    <%
			    List<Branch> projectlist = bservice.fill();
			    Iterator<Branch> iterproject = projectlist.iterator();
		    %>
		    <thead>
		        <tr>
		        	<th field="id" width="15"></th>
		        	<th field="branchName" width="15">部门名称</th>
		            <th field="branchType" width="15">部门类型</th>
		            <th field="branchCode" width="15">部门编码</th>
		            <th field="comName" width="20">分公司名称</th>
		            <th field="comId" width="20">分公司编码</th>
		        </tr>
		    </thead>
		    <%-- <tbody>
		    	<%
		    	while (iterproject.hasNext()) {
		    		Branch branch1 = iterproject.next();
		    		%>
		    		<tr>
		    			<td><%=branch1.getId() %></td>
		    			<td><%=branch1.getBranchName() %></td>
		    			<td><%=branch1.getBranchType() %></td>
		    			<td><%=branch1.getBranchCode() %></td>
		    			<td><%=branch1.getComName() %></td>
		    			<td>
		    			<a class="easyui-linkbutton" 
					href="#" iconCls="icon-edit" plain="true" 
					onclick="addTab('修改部门信息','ManageBranchServlet?id=<%=branch1.getId()%>&action=update')">修改</a>
		    			<a class="btn btn-success"
		    				href="ManageBranchServlet?id=<%=branch1.getId() %>&action=list2">
		    				<i class="fa fa-search-plus "></i>
		    			</a> <a class="btn btn-info"
		    			href="ManageBranchServlet?id=<%=branch1.getId() %>&action=update">
		    			<i class="fa fa-edit "></i>
		    		</a> <a class="btn btn-danger branch-delete" 
		    		value= "<%=branch1.getId() %>"> <i
		    		class="fa fa-trash-o "></i>
		    	</a></td>
		    </tr>
		    <%
		}

	%>
</tbody> --%>
		</table>
		<div id="toolbar">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBranch()">添加部门</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBranch()">修改部门</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyBranch()">删除部门</a>
		</div>

<div id="dlg-branch" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">部门信息</div>
		<form id="fm-branch" method="post">
			<div class="fitem">
				<label>部门名称</label>
				<input name="branchName" class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>部门类型</label>
				<select id="branchType" name="branchType" class="easyui-validatebox">
					<option value="" disabled selected>请选择部门类型</option>
					<option value="管理">管理</option>
					<option value="站场">站场</option>
				</select>
			</div>
			<div class="fitem">
				<label>部门编码</label>
				<input name="branchCode" class="easyui-validatebox" placeholder="编码格式：AABBCC">
			</div>
			<div class="fitem">
				<label>分公司名称</label>
				<input name="comName" class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>分公司编码</label>
				<input name="comId" class="easyui-validatebox">
			</div>
			<!-- <div style="text-align:center;">
							<input type="submit" 
								value="提交">
							<input type="reset"
								value="重置">
						</div> -->
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBranch()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-branch').dialog('close')">取消</a>
	</div>

	<script type="text/javascript" src="js/branch-data.js"></script>
</body>
</html>