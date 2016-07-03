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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>燃气巡检系统</title>

    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="assets/easyui/themes/color.css">

    <script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />

</head>

<body>
    <jsp:useBean id="pService" class="com.service.PeopleService"
    scope="session"></jsp:useBean>
    <%
        Faultmsg faultmsg = (Faultmsg)request.getAttribute("faultmsg");
    	int team = (int) request.getSession().getAttribute("SesTeamId");
    	
    %>

<div class="easyui-panel" title="主要信息" style="padding: 10px">
                <div style="float: left; width: 25%;">
                    <i class="fa fa-asterisk bk-padding-right-10"></i>故障编号：
                    <i class="bk-padding-right-15" id = "eva-record-id"><%=faultmsg.getId() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    故障标题：
                    <i class="bk-padding-right-15" ><%=faultmsg.getFaultTitle() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    所属部门：
                    <i class="bk-padding-right-15" ><%=session.getAttribute("SesBranchName") %></i>
                </div>
                <div style="float: left; width: 25%;">
                    <i class="fa fa-database bk-padding-right-10"></i>提交时间：
                    <i class="bk-padding-right-15" ><%=faultmsg.getFaultTime() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    提交人：
                    <i class="bk-padding-right-15" ><%=faultmsg.getGenerId() %></i>
                </div>

                <div style="float: left; width: 25%;">
                    审核人：<i class="bk-padding-right-15" ><%=faultmsg.getDutyPeople() %></i>
                </div>
               
</div>
<br>
<div class="easyui-panel" title="文字记录" style="padding: 10px">

    <p class = "bk-padding-left-20 bk-padding-right-20" id = "sheet-intro"><%=faultmsg.getFaultWord() %></p>
</div>

<br>
<div class="easyui-panel" title="图片记录" style="padding: 10px">
    

    <div style="float: left;padding: 5px;">

        <a href="<%=faultmsg.getFaultUrL() %>" class="thumbnail">
            <img style="height: 150px;width: 250px;  border: 3px solid #6495ED;" src="<%=faultmsg.getFaultUrL() %>" alt="点击查看原图">
        </a>

    </div>

</div>
<br>

<div class="easyui-panel" title="故障信息处理" style="padding: 10px">

        <%-- <% if(faultmsg.getFaultState().equals("0")){ if (team<=3){%>

        <div class="col-md-8 col-md-offset-2">
            <textarea id="record-comment" name="intro" rows="4" class="form-control" placeholder="输入评论内容"></textarea>
        </div>
        <a class="easyui-linkbutton" id = "check-button" href="#">确认审核
        </a>
        <%}}else{%>

        <p class="text-primary text-center col-md-8 col-md-offset-2"><%=faultmsg.getFaultState() %></p>
        <%}%> --%>
        
        <%if(team<=3){ %>
        <div class="col-md-8 col-md-offset-2">
        	<form id="doFault" method="post">
        		<div>
        			<div>
        			<label>标题：</label>
        			
        			</div>
        			<input type="hidden" id="faultid" name="faultid" value=<%=faultmsg.getId() %>>
        			<input type="text" id="faulttitle" name="faulttitle" value=<%=faultmsg.getFaultTitle() %>>
        		</div>
        		<div>
        			<label>内容：</label>
        			<div>
        			
            		<textarea id="faultword" name="faultword" rows="4"  value=<%=faultmsg.getFaultWord() %>><%=faultmsg.getFaultWord() %></textarea>
            		
            		</div>
            	</div>
            	<div>
            		<p>发送故障信息至</p><input type="text" id = "pnum"  name="pnum" placeholder="手机号码">
            		<a class="easyui-linkbutton" id = "check-button" href="#" onclick="sendFault()">确认发送</a>
            	</div>
            </form>
        </div>
        <%}else{ %>
        	<p class="text-primary text-center col-md-8 col-md-offset-2">您当前不具备处理故障权限，请及时联系管理员或者工程师！</p>
        <%} %>

</div>





<script src="js/fault-msg-detail.js"></script>

<!-- end: JavaScript-->

</body>

</html>