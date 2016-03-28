<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="pull-left">
	<dir class = "row">
		<div class="btn-group" role="group">
		    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      选择部门
		    	<span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu">
		    	<li><a href="#">部门1</a></li>
		    	<li><a href="#">部门2</a></li>
		    </ul>
		</div>
	</dir>					
</div>