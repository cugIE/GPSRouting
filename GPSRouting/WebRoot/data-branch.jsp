<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.bean.*"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'data-branch.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> -->
	<!-- <link href="css/style.css" rel="stylesheet" type="text/css" /> -->

  </head>
  
  <body>
    <div >
<table width="900" height="20" border="0" align="center">
  <tr>
    <td width="170" valign="top" bgcolor="#FFFFCC" align="center"><ul>
      <li><a href="addbranch.jsp" target="content">添加部门信息</a></li>
      <li><a href="ManageBranchServlet?action=list" target="content">查询部门信息</a></li>
      <li><a href="branchUpdate.jsp" target="content">编辑部门信息</a></li>
      <li><a href="ManageBranchServlet?action=delete" target="content">删除部门信息</a></li>
    </ul>
    </td>
    <td width="730" valign="top">
     <iframe frameborder="0" name="content" width="100%" height="590" scrolling="auto">
     <marquee>欢迎管理员的进入</marquee>
     </iframe>
    </td>
  </tr>
</table>
  
  </div>
  </body>
</html>
