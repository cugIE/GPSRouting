<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<body>

</body>
</html>
<html>
<head>
	<meta charset="UTF-8">
	<title>燃气巡检系统</title>
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="assets/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="assets/easyui/themes/color.css">

	<script type="text/javascript" src="assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="assets/easyui/jquery.easyui.min.js"></script>
	
	<style type="text/css">
		body,html{
			margin:0px;
			height:100%;
		}
		a{
			text-decoration:none;
		}
		.autoHeight{
			width:100%;
			height:100%;
		}
		.title {
			display: table;
			position:relative;
			height: 70px; 
			width: 100%; 
			background: url(assets/img/timg.jpeg); 
			color: #ffffff; 
			font-family: "Microsoft YaHei";
		}
		.title-text {
			font-size: 20px;

			padding-left: 20px;
			display: table-cell;
			vertical-align: middle;
		}
		.title-msg{

		}
		.title-info{

		}
	</style>
</head>
<body>
	<div class="easyui-layout autoHeight">
		<div class="title" region="north">
		<div class="title-text">燃气巡检系统</div>
		<a href="#" class="easyui-linkbutton c4 l-btn l-btn-small" style="position:absolute; right:200px; bottom:10px">通知</a>
		<a href="#" class="easyui-linkbutton c5 l-btn l-btn-small" style="position:absolute; right:160px; bottom:10px">警报</a>
		<a href="#" onclick="addTab('修改密码','modpwd.jsp')" class="easyui-linkbutton c3 l-btn l-btn-small" style="position:absolute; right:80px; bottom:10px">修改密码</a>		
		<a href="ExitServlet" class="easyui-linkbutton c2 l-btn l-btn-small" style="position:absolute; right:20px; bottom:10px">安全退出</a>
		</div>
		<div region="west" split="true" title="导航栏" style="width:200px;">
			<div class="easyui-panel" style="padding: 5px;width: 100%;height: 133px; text-align: center;">
				<img style="height: 70px;width: 70px;  border: 3px solid #6495ED;"src="assets/img/haven.png"/>
				<p style="font-size: 12px; margin-top: -2px"><%=session.getAttribute("SesName")%>
				</p>
				<%
					BranchService bs = new BranchService();
					Branch tempbranch = bs.fill(""+session.getAttribute("SesBranchId"));
				%>
				<p style="background-color: #104E8B;color: #ffffff;margin-top: -5px; height:20px;font-size: 11px;"><%=tempbranch.getBranchName()%>-<%=session.getAttribute("SesTeamName")%></p>
			</div>
			<div class="easyui-panel" style="padding:5px; width: 100%; height: 100%">
				<ul class="easyui-tree">

				

					<%
					if(tempbranch.getBranchType().equals("管理")){
						List<Branch> branchList = bs.fill();
						for (int i = 0; i<branchList.size();i++){
							Branch branch = branchList.get(i);
							if (branch.getBranchType().equals("站场")){
					%>
					<li data-options="state:'closed'">
						
						<span><%=branch.getBranchName()%></span>

						<ul>

							<li data-options="state:'closed'">
								<span>巡检报表</span>
								<ul>
									
									<li><a href="#" onclick="addTab('<%=branch.getBranchName()%>-巡检日志','result-data.jsp?branch_id=<%=branch.getId()%>')">巡检日志</a></li>
									<li>
										<span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-故障信息','fault-msg.jsp?branch_id=<%=branch.getId() %>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">故障信息</a></span>
									</li>
									<li><span><span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-月度巡检报告','result-month.jsp?branch_id=<%=branch.getId()%>')">月度巡检报告</a></span></li>
								</ul>

							</li>
							
							<li data-options="state:'closed'">
								<span>巡检数据</span>
								<ul>
									<li>
										<span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-巡检表管理','sheet-data.jsp?branch_id=<%=branch.getId()%>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">巡检表管理</a></span>
									</li>
									<li>
									<span> <a href="#" onclick="addTab('<%=branch.getBranchName()%>-巡检区域管理','region-data.jsp?branch_id=<%=branch.getId()%>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">巡检区域管理</a>
									</span>
									</li>
									<li>
										<span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-巡检位置管理','position-data.jsp?branch_id=<%=branch.getId()%>')">巡检位置管理</a></span>
									</li>
									<li>
										<a href="#" onclick="addTab('<%=branch.getBranchName()%>-巡检内容管理','content-data.jsp?branch_id=<%=branch.getId()%>')">巡检内容管理</a>
									</li>
								</ul>
							</li>
							
							<li data-options="state:'closed'" >
								<span>辅助功能</span>
								<ul>
									<li><span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-公告','announcement.jsp?branch_id=<%=branch.getId() %>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">公告</a></span></li>
									<li><span><a href="#" onclick="addTab('<%=branch.getBranchName()%>-警报','alarm-msg.jsp?branch_id=<%=branch.getId() %>')">警报</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
					<%
						}
							}
					}else{
					%>
					<li data-options="state:'closed'">
						
						<span><%=tempbranch.getBranchName() %></span>

						<ul>

							<li data-options="state:'closed'">
								<span>巡检报表</span>
								<ul>
									
									<li><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-巡检日志','result-data.jsp?branch_id=<%=tempbranch.getId()%>')">巡检日志</a></li>
									<li>
										<span><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-故障信息','fault-msg.jsp?branch_id=<%=tempbranch.getId() %>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">故障信息</a></span>
									</li>
									<li><span><span><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-月度巡检报告','result-month.jsp?branch_id=<%=tempbranch.getId()%>')">月度巡检报告</a></span></li>
								</ul>

							</li>
							
							<li data-options="state:'closed'">
								<span>巡检数据</span>
								<ul>
									<li>
										<span><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-巡检表管理','sheet-data.jsp?branch_id=<%=tempbranch.getId()%>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">巡检表管理</a></span>
									</li>
									<li>
									<span> <a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-巡检区域管理','region-data.jsp?branch_id=<%=tempbranch.getId()%>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">巡检区域管理</a>
									</span>
									</li>
									<li>
										<span><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-巡检位置管理','position-data.jsp?branch_id=<%=tempbranch.getId()%>')">巡检位置管理</a></span>
									</li>
									<li>
										<a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-巡检内容管理','content-data.jsp?branch_id=<%=tempbranch.getId()%>')">巡检内容管理</a>
									</li>
								</ul>
							</li>
							
							<li data-options="state:'closed'" >
								<span>辅助功能</span>
								<ul>
									<li><span><a href="#" onclick="addTab('<%=tempbranch.getBranchName() %>-公告','announcement.jsp?branch_id=<%=tempbranch.getId() %>&userTeamid=<%=session.getAttribute("SesTeamId") %>')">公告</a></span></li>
									<li><span><a href="#" onclick="addTab('<%=tempbranch.getBranchName()%>-警报','alarm-msg.jsp?branch_id=<%=tempbranch.getId() %>')">警报</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
					<%
						}
					%>
					<li data-options="state:'closed'">
					
						<span>系统管理</span>
						<ul>
						<li>
							<span ><a href="#" onclick="addTab('部门管理','branch-data.jsp?userTeamid=<%=session.getAttribute("SesTeamId") %>&userBranchtype=<%=tempbranch.getBranchType() %>')">部门管理</a></span>
						</li>
						<li >
							 <span><a href="#" onclick="addTab('人员管理','data-people.jsp?userTeamid=<%=session.getAttribute("SesTeamId") %>')">人员管理</a></span>
						</li>
						<li >
							 <span><a href="#" onclick="addTab('网站参数','data-site.jsp')">网站参数</a></span>
						</li>
						<li >
							 <span><a href="#" onclick="addTab('系统日志','data-log.jsp')">系统日志</a></span>
						</li>
						</ul>
					</li>
					<li>
						<span>帮助</span>
					</li>
				</ul>
			</div>
		</div>
		<div id="tbs" region="center" class="easyui-tabs" >
			<div title="关于" style="padding:10px">
				<div style="width: 60%; padding-left: 15%; height: 300px;">
					<div class="easyui-panel" title="介绍" style="height: 300px;" align="center" >
						<h1 style="color: #104E8B">燃气管道巡检系统</h1>
						<p>更方便更简洁的管理系统。</p>
					</div>
				</div>
			</div>
				
		</div>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
	
</body>
</html>
