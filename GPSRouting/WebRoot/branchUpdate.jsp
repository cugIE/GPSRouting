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
    
    <title>My JSP 'branchUpdate.jsp' starting page</title>
    
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
    <table width="834" border="0" cellpadding="0" cellspacing="0">
        <tr>
             
            <td height="30" bgcolor="#029AC5" class="txt" align="center">修改部门信息</td>
        </tr>
    </table>  
 
    <%
    Branch branch=(Branch)request.getAttribute("branch");
    if(branch!=null){   
    %>
<table width="100%" height="30" align="center" cellpadding="0" cellspacing="0" >
 <tr>
 <td height="30" align="center"><form id="form1" name="form1" method="post" action="ManageBranchServlet?action=update2">
 <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="E7E7E7">
 <tr>
 <td width="24%" height="30" align="right" class="txt"><span class="txt">部门编码</span></td>
 <td height="30" align="left" ><label for="textfield"></label>
 <input type="text" name="branch_code" id="branch_code" value=<%=branch.getBranchCode()%> />
  <input type="hidden" name="branch_id" id="branch_id" value=<%=branch.getId() %> /><span class="txt">*</span></td>
  </tr>
   
 
   <tr>
 <td width="24%" height="30" align="right" class="txt"><span class="txt"> 名称</span></td>
 <td height="30" align="left" >
 <input  height="20" width="400" type="text" name="branch_name" id="branch_name" value=<%=branch.getBranchName() %> />  <span class="txtred">*</span></td>
  </tr>
      
   <tr>
 <td  height="30" align="right" class="txt">类型</td>
 <td height="30" align="left" >
 <input  height="20" width="150" type="text" name="branch_type" id="branch_type" value=<%=branch.getBranchType() %> />  </td>
  </tr>
  <tr>
 <td  height="30" align="right" class="txt">分公司名称：</td>
 <td height="30" align="left" >
 <input  height="20" width="200" type="text" name="com_name" id="com_name" value=<%=branch.getComName() %>/>  </td>
  </tr>
 
  <tr>
 <td  height="30" align="right" class="txt">分公司编码：</td>
 <td height="30" align="left" ><span class="txtred">
 <input  height="20" width="200" type="text" name="com_id" id="com_id" value=<%=branch.getComId() %>/>  </span></td>
  </tr>
  </table>
   
<p>
 <input type="submit" name="button" id="button" value="确定"/>
</p>
</form>
<p>&nbsp;</p></td>
</tr>
</table>
<% }else{%>
    <jsp:useBean id="service" class="com.service.BranchService" scope="session"/>
    <%
        List<Branch> projectlist=service.fill();
        Iterator<Branch> iterproject=projectlist.iterator();
    %>
    <table width="800" border="0" align="center" cellpadding="0"
        cellspacing="0">
         
        <tr>
            <td height="30" colspan="3"><table width="100%" border="1"
                    align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="30%" height="20" align="center" bgcolor="#80C6FF"><span
                            class="txt"><span class="titletxt">部门ID</span></span></td>
                        <td width="35%" height="20" align="center" bgcolor="#80C6FF"
                            class="titletxt"> 名称</td>
                        <td width="35%" height="20" align="center" bgcolor="#80C6FF"
                            class="titletxt">相关操作</td>
                    </tr>
                    <%
                        while(iterproject.hasNext()){   
                            Branch branch1=iterproject.next();
                    %>
                    <tr>
                        <td width="30%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch1.getId() %></span></td>
                        <td width="35%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch1.getBranchName() %></span></td>
                        <td width="35%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt">
                            <a href="ManageBranchServlet?id=<%=branch1.getId() %>&action=update">【修改】</a></span></td>
                    </tr>
                    <%
                        }
                     }
                    %>
                </table></td>
        </tr>
    </table>
  </body>
</html>
