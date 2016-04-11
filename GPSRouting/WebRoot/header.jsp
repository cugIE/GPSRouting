<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="pull-left">
	<div class = "row">
		<div class="btn-group" role="group">
		   <button type = "button" class="btn btn-primary bk-margin-10" name="back" value="返回" onclick="javascript:history.back(-1);"><i class = "fa fa-arrow-left"></i></button>
		</div>
	</div>					
</div>