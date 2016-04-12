<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script language="javascript">

				function check() {
					var inputs = document.getElementsByTagName('input');
					for (var i = 0, len = 2; i < len; i++) {
						if (inputs[i].value.replace(/\s/g, '') == '') {
							alert('必填项不能为空！');
							inputs[i].focus();
							return false;
						}
					}
					return true;
				}
			</script>
<base href="<%=basePath%>">
<title>My JSP 'addbranch.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	<div align="center">
		<H3>添加部门</H3>
		<form id="form1" name="form1" method="post"
			action="ManageBranchServlet?action=add">
			<table width="90%" border="1" class="tableEdit">
				<tr>
					<td width="34%">部门编码</td>
					<td width="66%"><label> <input type="text" name="branch_code"
							id="branch_code" />
					</label> *</td>
				</tr>
				<tr>
					<td>部门名称</td>
					<td><input type="text" name="branch_name" id="branch_name" /> *</td>
				</tr>
				<tr>
					<td>类型</td>
					<td><input type="text" name="branch_type" id="branch_type" /></td>
				</tr>
				<tr>
					<td>分公司名称</td>
					<td><input type="text" name="com_name" id="com_name" /></td>
				</tr>
				<tr>
					<td>分公司编码</td>
					<td><input type="text" name="com_id" id="com_id" /></td>
				</tr>
				
				<tr>
					<td colspan="2"><label> <input type="submit"
							name="button" id="button" value="提交" />
					</label></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
