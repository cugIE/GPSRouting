<%--
  Created by IntelliJ IDEA.
  User: XinLi
  Date: 16/6/23
  Time: 上午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.bean.Record"
         import="net.sf.json.*"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<%
    String record_id = request.getParameter("record_id");
    Record rcd= new Record();
    int team = (int) request.getSession().getAttribute("SesTeamId");
    if (record_id!=null){
        rcd = Record.getOneRecord(record_id);
    }
%>

<div class="easyui-panel" title="主要信息" style="padding: 10px">
                <div style="float: left; width: 25%;">
                    <i class="fa fa-asterisk bk-padding-right-10"></i>记录编号：
                    <i class="bk-padding-right-15" id = "eva-record-id"><%=rcd.getId() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    记录区域：
                    <i class="bk-padding-right-15" ><%=rcd.getRegion() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    所属部门：
                    <i class="bk-padding-right-15" ><%=rcd.getBranch() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    <i class="fa fa-database bk-padding-right-10"></i>区域类型：
                    <%
                        String Region_type = "巡线点";
                        if(rcd.getType().equals("site")){
                            Region_type = "巡站点";
                        } %>
                    <i class="bk-padding-right-15" ><%=Region_type %></i>
                </div>
                <div style="float: left; width: 25%;">
                    记录人员：
                    <i class="bk-padding-right-15" ><%=rcd.getGener() %></i>
                </div>

                <div style="float: left; width: 25%;">
                    班次：<i class="bk-padding-right-15" ><%=rcd.getPeriod_shift() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    时间段：
                    <i class="bk-padding-right-15" ><%=rcd.getPeriod_time() %></i>
                </div>
                <div style="float: left; width: 25%;">
                    开始时间：
                    <%if(rcd.getStart()!=null) {%>
                    <i class="bk-padding-right-15"><%=rcd.getStart().toString() %></i><%}else{ %>
                    <i class="bk-padding-right-15"></i><%} %>
                </div>
                <div style="float: left; width: 25%;">
                    结束时间：
                    <%if(rcd.getEnd()!=null) {%>
                    <i class="bk-padding-right-15"><%=rcd.getEnd().toString() %></i><%}else{ %>
                    <i class="bk-padding-right-15"></i><%} %>
                </div>
                <div style="float: left; width: 25%;">
                    提交时间：
                    <%if(rcd.getSubmit()!=null) {%>
                    <i class="bk-padding-right-15"><%=rcd.getSubmit().toString() %></i><%}else{ %>
                    <i class="bk-padding-right-15"></i><%} %>
                </div>
                <div style="float: left; width: 25%;">
                    审核时间：
                    <%if(rcd.getCheck()!=null) {%>
                    <i class="bk-padding-right-15"><%=rcd.getCheck().toString() %></i><%}else{ %>
                    <i class="bk-padding-right-15"></i><%} %>
                </div>
                <div style="float: left; width: 25%;">
                    审核人：
                    <%if(rcd.getCheck()!=null) {%>
                    <i class="bk-padding-right-15" id="record-checker"><%=rcd.getChecker() %></i><%}else{ %>
                    <i class="bk-padding-right-15" id="record-checker" ></i><%} %>
                </div>
</div>
<br>
<div class="easyui-panel" title="文字记录" style="padding: 10px">

    <p class = "bk-padding-left-20 bk-padding-right-20" id = "sheet-intro"><%=rcd.getNote() %></p>
</div>

<br>
<div class="easyui-panel" title="图片记录" style="padding: 10px">
    <%
        String picandvedio = rcd.getPicture();
        String pic = "";
        JSONObject jsonObjecttemp = new JSONObject();
        try {
            jsonObjecttemp = JSONObject.fromObject(picandvedio);
            pic = (String) jsonObjecttemp.get("picture");
        }
        catch (JSONException JSE){
            JSE.printStackTrace();
        }

        String[] pics = pic.split(",");
        for(int i = 0; i<pics.length;i++){
    %>

    <div style="float: left;padding: 5px;">

        <a href="<%=pics[i] %>" class="thumbnail">
            <img style="height: 150px;width: 250px;  border: 3px solid #6495ED;" src="<%=pics[i] %>" alt="点击查看原图">
        </a>

    </div>
    <%
        }
    %>

</div>
<br>
<div class="easyui-panel" title="视频记录" style="padding: 10px">

    <% if(jsonObjecttemp.get("vedio")!=null){
    %>
    <div>

        <video  controls="controls">
            <source src="<%=jsonObjecttemp.get("vedio") %>" type="video/mp4" >视频</source>
            您的浏览器不支持video标签
        </video>

    </div>
    <%
        }
    %>
</div>
<br>
<div class="easyui-panel" title="地址记录" style="padding: 10px">
        <div style="width: 600px; height: 400px;" id="bdmap-record-position" gps = "<%=rcd.getGps() %>"></div>
</div>
<br>
<div class="easyui-panel" title="详细记录内容" style="padding: 10px">

<%
JSONArray JA = JSONArray.fromObject(rcd.getAsws());
%>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>标题</th>
            <th>可选回答</th>
            <th>正常回答</th>
            <th>实际回答</th>
        </tr>
        </thead>

        <tbody>
        <% for(int i = 0; i<JA.size(); i++){
            JSONObject jso = JA.getJSONObject(i);
            String trcolor = "success";
            if (jso.get("error").equals("1")){
                trcolor = "danger";
            }
        %>
        <tr class="<%=trcolor %>">
            <td><%=jso.get("title") %></td>
            <td><%=jso.get("possasws") %></td>
            <td><%=jso.get("normalasws") %></td>
            <td><%=jso.get("choosedasws") %></td>
        </tr>
        <%} %>
        </tbody>
    </table>
    <h6 style="color: red;">共<%=rcd.getError() %>个问题不符合正常情况</h6>
</div>

<br>
<div class="easyui-panel" title="审核内容" style="padding: 10px">

        <% if(rcd.getComment().equals("")||rcd.getComment()==null){ if (team<=3){%>

        <div class="col-md-8 col-md-offset-2">
            <textarea id="record-comment" name="intro" rows="4" class="form-control" placeholder="输入评论内容"></textarea>
        </div>
        <a class="easyui-linkbutton" id = "check-button" href="#">确认审核
        </a>
        <%}}else{%>

        <p class="text-primary text-center col-md-8 col-md-offset-2"><%=rcd.getComment()%></p>
        <%}%>

</div>





<script src="js/result-data-detail-line.js"></script>

<!-- end: JavaScript-->

</body>

</html>