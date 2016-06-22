<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.bean.*" import="com.util.*" import="net.glxn.qrgen.javase.*" import="net.glxn.qrgen.core.image.*" import="net.sf.json.JSONObject"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QRCode</title>
</head>
<%
String region_id = request.getParameter("region_id");
String size = request.getParameter("size");
Region rg = Region.getOneRegion(region_id);
 %>
<body>
<h4>---区域名：<%=rg.getName() %>---所在部门：<%=rg.getBranch() %>---</h4>
<img alt="大小为<%=size %>x<%=size %>"  height="<%=size %>" width="<%=size %>" src="<%=rg.getQrcode() %>">

</body>
</html>