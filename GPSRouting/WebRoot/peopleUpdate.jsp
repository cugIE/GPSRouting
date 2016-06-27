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
	<table id="people-data" title="修改人员信息" class="easyui-datagrid" style="max-height:600px;"	
	rownumbers="true" fitColumns="true" singleSelect="true">
	<%
		People people = (People) request.getAttribute("people");				
	%>
	<jsp:useBean id="service11" class="com.service.BranchService"
	scope="session">
	</jsp:useBean>
	<%
		Branch branch2 = service11.fill(people.getBranchId());
		List<Branch> projectlist3 = service11.fill();
		Iterator<Branch> iterproject3 = projectlist3.iterator();
	%>

	<jsp:useBean id="service12" class="com.util.TeamidtoName"
	scope="session">
	</jsp:useBean>
	<%
		String teamname = service12.idtoname(people.getTeamId());
	%>

	<div class="ftitle">修改人员信息</div>
		<form id="updatefm" action="ManagePeopleServlet?action=update2" method="post">
			<div class="fitem">
				<label>用户名</label>
				<input type="text" id="username" name="username" class="easyui-validatebox" value=<%=people.getUsername() %>>
				<input type="hidden" id="userid" name="userid" value=<%=people.getId() %>>
			</div>
			<div class="fitem">
				<label>密码</label>
				<input type="password" id="password" name="password" class="easyui-validatebox" value=<%=people.getPassword() %> />
			</div>
			<div class="fitem">
				<label>名字</label>
				<input type="text" id="name" name="name" class="easyui-validatebox" value=<%=people.getName() %> />
			</div>
			<div class="fitem">
				<label>編碼</label>
				<input type="text" id="peoplecode" name="peoplecode" class="easyui-validatebox" value=<%=people.getCode() %> />		
			</div>
			<div class="fitem">
				<label>部门</label>
				<select name="branchid" id="branchid" class="easyui-validatebox">
					<option value=<%=branch2.getId() %>><%=branch2.getBranchName() %></option>
					<%
					while (iterproject3.hasNext()) {
					Branch branch1 = iterproject3.next();
					%>
					<option value=<%=branch1.getId() %>><%=branch1.getBranchName() %></option>
					<%
						}
					%>
				</select>		
			</div>
			<div class="fitem">
				<label>组类型</label>
				<select name="teamid" id="teamid" class="easyui-validatebox">
					<option value=<%=people.getTeamId() %>><%=teamname %></option>
					<option value="0001">管理员</option>
					<option value="0002">工程师</option>
					<option value="0003">值班干部</option>
					<option value="0004">值班员</option>
					<option value="0005">巡检员</option>
				</select>
			</div>
			<div class="fitem">
				<label>备注</label>							
				<input type="text" id="peopremark" name="peopremark" value=<%=people.getPeopRemark() %> class="easyui-validatebox" placeholder="备注信息">
			</div>
			<div>
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>
			</div>
		</form>
</table>
<script type="text/javascript" src="js/people-data.js"></script>
</body>
</html>