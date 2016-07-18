<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service.*"%>
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
	<table id="people-data" title="人员管理" class="easyui-datagrid" style="max-height:600px;"
	url="ManagePeopleServlet?action=list&userbranch=<%=session.getAttribute("SesBranchId") %>"
	toolbar="#toolbar"
	rownumbers="true" fitColumns="true" singleSelect="true">
	<%-- <jsp:useBean id="service1" class="com.service.PeopleService"
								scope="session">
	</jsp:useBean>
	<%
		List<People> projectlist = service1.fill();
			Iterator<People> iterproject = projectlist.iterator();
	%>
	<jsp:useBean id="service5" class="com.service.BranchService"
								scope="session">
	</jsp:useBean>
	<%
		List<Branch> projectlist2 = service5.fill();
			Iterator<Branch> iterproject2 = projectlist2.iterator();
	%> --%>
	<thead>
		<tr>
			<th field="id" width="10" ></th>
			<th field="username" width="10" >用户名</th>
			<th field="name" width="10">姓名</th>
			<th field="teamname" width="15">职位</th>
			<th field="braname" width="15">所属部门</th>			
			<th field="peoplecode" width="20">人员编码</th>
			<th field="peopRemark" width="20">备注信息</th>
		</tr>
	</thead>
	<%-- <tbody>
		<%
		while (iterproject.hasNext()) {
			People people1 = iterproject.next();
			%>
			<tr>
				<td><%=people1.getId() %></td>
				<td><%=people1.getUsername()%></td>
				<td><%=people1.getName()%></td>
				<td>
					<% 
					if(people1.getTeamId().equals("0001")){
						out.print("管理员");
					}else if(people1.getTeamId().equals("0002")){
						out.print("工程师");				
					}else if(people1.getTeamId().equals("0003")){
						out.print("值班干部");
					}else if(people1.getTeamId().equals("0004")){
						out.print("值班员");
					}else{
						out.print("巡检员");
					}
					%>
				</td>
				<td><% Branch bra = service5.fill(people1.getBranchId());
						String branchName = bra.getBranchName();
						out.print(branchName);
					%>
				</td>
				<td><%=people1.getPeopRemark()%></td>
				<td>
					<a class="easyui-linkbutton" 
					href="#" iconCls="icon-edit" plain="true" 
					onclick="addTab('修改人员信息','ManagePeopleServlet?id=<%=people1.getId()%>&action=update')">修改</a> 
					<a id="destroyPeo" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
					onclick="destroyU()" value="<%=people1.getId() %>">删除</a>							
				</td>
			</tr>
				<%
					}			
				%>

			</tbody> --%>
		</table>
		<div id="toolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加人员</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改人员</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除人员</a>
			
		</div>

		<div id="dlg-people" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">人员信息</div>
		<form id="fm-people" method="post">
			<div class="fitem">
				<label>用户名</label>
				<input name="username" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>姓名</label>
				<input name="name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>编码</label>
				<input name="peoplecode" class="easyui-validatebox" placeholder="编码格式：AABBCC">		
			</div>
			<jsp:useBean id="service6" class="com.service.BranchService"
								scope="session">
			</jsp:useBean>
			<%
				
				Branch tempbranch = service6.fill(""+session.getAttribute("SesBranchId"));
				List<Branch> projectlist3 = service6.fill();
					Iterator<Branch> iterproject3 = projectlist3.iterator();
			%>
			<div class="fitem">
				<label>部门</label>
				<%
					if(tempbranch.getBranchType().equals("管理")){
				%>
				<select name="branchid" id="branchid" class="easyui-validatebox">
				<option value="" disabled selected>请选择部门</option>
					<%
						while (iterproject3.hasNext()) {
							Branch branch2 = iterproject3.next();
					%>
				<option value=<%=branch2.getId() %>><%=branch2.getBranchName() %></option>												
					<%
						}
					%>
			</select>
			<%
					}else if(tempbranch.getBranchType().equals("站场")){
			%>		
				<input type="hidden" id="branchid" name="branchid" value=<%=tempbranch.getId() %>>
				<input type="text" id="branchName" name="branchName" value=<%=tempbranch.getBranchName() %> class="easyui-validatebox" disabled="disabled">
				<%
					}
				%>
			</div>
			<div class="fitem">
				<label>组类型</label>
				<select name="teamid" id="teamid" class="form-control">
					<option value="" disabled selected>请选择职位</option>
					<option value="0001">管理员</option>
					<option value="0002">工程师</option>
					<option value="0003">值班干部</option>
					<option value="0004">值班员</option>
					<option value="0005">巡检员</option>
				</select>
			</div>
			<div class="fitem">
				<label>备注</label>							
				<input name="peopRemark" class="easyui-validatebox" placeholder="备注信息">
			</div>	
			<!-- <div class="fitem">
				<input type="submit" class="easyui-validatebox" value="提交">
				<input type="reset" class="easyui-validatebox" value="取消">
			</div> -->
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-people').dialog('close')">取消</a>
	</div>
	
	<script type="text/javascript" src="js/people-data.js"></script>
</body>
</html>