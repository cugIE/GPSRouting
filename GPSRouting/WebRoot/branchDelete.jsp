<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'branchDelete.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

    <table width="800" border="0" align="center" cellpadding="0"
        cellspacing="0">
        <tr>
            <td height="30" colspan="3" bgcolor="#029AC5" class="titletxt">&#8226;部门信息</td>
        </tr>
        <tr>
            <td height="30" colspan="3">
            <form action="ManageBranchServlet?action=delete2" method="post">    
            <table width="100%" border="1"
                    align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="10%" align="center" bgcolor="#80C6FF" class="titletxt">选择</td>
                        <td width="25%" height="30" align="center" bgcolor="#80C6FF"><span
                            class="txt"><span class="titletxt">部门信息ID</span></span></td>
                        <td width="30%" height="30" align="center" bgcolor="#80C6FF"
                            class="titletxt">名称</td>
                        <td width="35%" height="30" align="center" bgcolor="#80C6FF"
                            class="titletxt">相关操作</td>
                    </tr>
                    <%
                        List<Branch> branchDelete=(List<Branch>)request.getAttribute("branchDelete");
                        for(Branch branch:branchDelete){
                    %>
                    <tr>
                        <td align="center"><input name="Id" width="15%"  bgcolor="#FFF5D7" type="checkbox" value="<%=branch.getId() %>" /></td>
                        <td width="25%" height="30" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch.getId() %></span></td>
                        <td width="30%" height="30" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch.getBranchName() %></span></td>
                        <td width="35%" height="30" align="center" bgcolor="#FFF5D7"><span
                            class="txt">
                            <a href="ManageBranchServlet?action=delete3&id=<%=branch.getId()%>">【删除】</a></span></td>
                    </tr>
                    <%   
                     }
                    %>
                 <tr>
                    <td width="10%" align="left" bgcolor="#80C6FF" class="titletxt" colspan="4">
                          <input type="submit" value=" 删除选择"/>     
                     </td>       
                </tr>
                </table>
                </form>   
            </td>
        </tr>
    </table>
  </body>
</html>
