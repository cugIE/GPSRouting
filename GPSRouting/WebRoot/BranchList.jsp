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
    
    <title>My JSP 'BranchList.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <td height="30" bgcolor="#029AC5" class="txt" align="center">查询部门信息</td>
        </tr>
    </table>  
    <%
    Branch branch=(Branch)request.getAttribute("branch");
    if(branch!=null){   
    %>
   		部门ID：<%=branch.getId() %><br />
   		部门编码：<%=branch.getBranchCode() %><br />
    	名称：<%=branch.getBranchName() %><br />
    	类型：<%=branch.getBranchType() %><br /> 
  	  	分公司名称：<%=branch.getComName() %><br />
    	分公司编码：<%=branch.getComId() %><br />
    <%
    }
    else
    {  
    %>
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
                            class="titletxt">名称</td>
                        <td width="35%" height="20" align="center" bgcolor="#80C6FF"
                            class="titletxt">相关操作</td>
                    </tr>
                    <%
                        while(iterproject.hasNext()){   
                            Branch branch1=iterproject.next();
                    %>
                    <tr>
                        <td width="30%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch1.getId()%></span></td>
                        <td width="35%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt"><%=branch1.getBranchName()%></span></td>
                        <td width="35%" height="20" align="center" bgcolor="#FFF5D7"><span
                            class="txt">
                            <a href="ManageBranchServlet?id=<%=branch1.getId()%>&action=list2">【详细】</a></span></td>
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
