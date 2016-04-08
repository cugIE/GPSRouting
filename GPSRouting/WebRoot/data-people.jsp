<%@ page language="java" contentType="text/html; charset=utf-8" import = "com.bean.People"  pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>燃气巡检系统</title>
		
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<!-- Import google fonts -->
        
        
		<!-- Favicon and touch icons -->
		<link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
		<link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
		<link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />
		
	    <!-- start: CSS file-->
		
		<!-- Vendor CSS-->
		<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		
		<!-- Plugins CSS-->
		<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
		<link href="assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />
		<link href="assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />					
		
		<!-- Theme CSS -->
		<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
		
		<!-- Page CSS -->		
		<link href="assets/css/style.css" rel="stylesheet" />
		<link href="assets/css/add-ons.min.css" rel="stylesheet" />
		
		<!-- end: CSS file-->	
	    
		
		<!-- Head Libs -->
		<script src="assets/plugins/modernizr/js/modernizr.js"></script>
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->		
		
	</head>
	
	<body>
	<!-- Start: Header -->
		<div class="navbar" role="navigation">
			<div class="container-fluid container-nav">
				<!-- Navbar Action -->
				<!-- Navbar Action -->
				<ul class="nav navbar-nav navbar-actions navbar-left">
					<li class="visible-md visible-lg"><a href="index.html#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
					<li class="visible-xs visible-sm"><a href="index.html#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
				</ul>
				<!-- Navbar Left -->
				
				<!-- Navbar Right -->
				<div class="navbar-right">
					<ul class="notifications hidden-sm hidden-xs">
						<li>
							<a href="msg-inbox.html" class="notification-icon" >
								<i class="fa fa-envelope"></i>
								<span class="badge">5</span>
							</a>
							
						</li>

						<li>
							<a href="msg-inbox.html" class="notification-icon">
								<i class="fa fa-bell"></i>
								<span class="badge">3</span>
							</a>
							
						</li>
					</ul>
					<div class="userbox">
						<a href="people-profile.html" class="dropdown-toggle" data-toggle="dropdown">
							<div class="profile-info">
								<span class="name">张三</span>
								<span class="role">管理员</span>
							</div>			
							<i class="fa custom-caret"></i>
						</a>
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">						
									<div class="progress progress-xs  progress-striped active">
										<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
											60%
										</div>
									</div>							
								</li>	
								<li>
									<a href="people-profile.html"><i class="fa fa-user"></i> 详细资料</a>
								</li>
								<li>
									<a href="data-people-form.html"><i class="fa fa-wrench"></i> 编辑</a>
								</li>								
								<li>
									<a href="page-login.html"><i class="fa fa-power-off"></i> 登出</a>
								</li>
							</ul>
						</div>							
					</div>
					<!-- End Userbox -->
				</div>
				<!-- End Navbar Right -->
			</div>		
		</div>
		<div class="copyrights">Collect from CUG </div>
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">
			
				<!-- Sidebar -->
				<div class="sidebar">
					<div class="sidebar-collapse">
						<!-- Sidebar Header Logo-->

						<!-- Sidebar Menu-->
						<div class="sidebar-menu">						
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-sidebar">
									<div class="panel-body text-center">								
										<div class="bk-avatar">
											<img src="assets/img/avatar.jpg" class="img-circle bk-img-60" alt="" />
										</div>
										<div class="bk-padding-top-10">
											<i class="fa fa-circle text-success"></i> <small>管理员</small>
										</div>
									</div>
									<div class="divider2"></div>
									<li class="active">
										<a href="index.html">
											<i class="fa fa-laptop" aria-hidden="true"></i><span>主界面</span>
										</a>
									</li>
									
									<li class="nav-parent">
										<a>
											<i class="fa fa-list-alt" aria-hidden="false"></i><span>数据管理</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="data-people.html"><span class="text"> 人员管理</span></a></li>
											<li><a href="data-device.html"><span class="text"> 管道设备管理</span></a></li>
											<li><a href="data-position.html"><span class="text"> 位置管理</span></a></li>					
										</ul>
									</li>
									<li class="nav-parent">
										<a>
											<i class="fa fa-copy" aria-hidden="true"></i><span>任务管理</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="task-search.html"><span class="text">巡检任务</span></a></li>
											<li><a href="task-evaluation.html"><span class="text"> 考核管理</span></a></li>
											<li><a href="task-disability.html"><span class="text">故障维修管理</span></a></li>
											
										</ul>
									</li>

									<li class="nav-parent">
										<a>
											<i class="fa fa-envelope" aria-hidden="true"></i><span>信息管理</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="msg-board.html"><span class="text"> 公告 </span></a></li>
											<li><a href="msg-inbox.html"><span class="text"> 消息</span></a></li>										</ul>
									</li>
								</ul>
							</nav>
						</div>
						<!-- End Sidebar Menu-->
					</div>
					<!-- Sidebar Footer-->
					<div class="sidebar-footer">	
						<div class="copyright text-center">
							<small> <i class="fa fa-coffee"></i> from <a href="http://www.cssmoban.com/" title="CUG" target="_blank">CUG</a></small>
						</div>					
					</div>
					<!-- End Sidebar Footer-->
				</div>
				<!-- End Sidebar -->
				<!-- Main Page -->
				<div class="main sidebar-minified">			
					<!-- Page Header -->
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">								
								<li><a href="index.html"><i class="icon fa fa-home"></i>Home</a></li>
								<li class="active"><i class="fa fa-table"></i>Tables</li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2>Tables</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">		
						<div class="col-lg-12">
							<div class="panel">
								<div class="panel-heading bk-bg-primary">
									<h6><i class="fa fa-table red"></i><span class="break"></span>人员名单</h6>
									<div class="panel-actions">
										<a href="table.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
										<a href="data-people-form.html" class="btn-plus"><i class="fa fa-plus"></i></a>
										<a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
									</div>
								</div>
									<%
									People people=(People)request.getAttribute("people");
									if(people!=null){   
									%>
									用户名：<%=people.getUsername() %><br />
									姓名：<%=people.getName() %><br /> 
									组类型：<%=people.getTeamId() %><br />
									所属部门：<%=people.getBranchId() %><br />
									备注信息：<%=people.getPeopRemark() %><br />
									<%
									}
									else
									{  
									%>
									<jsp:useBean id="service" class="com.service.PeopleService" scope="session"/>
									<%
										List<People> projectlist=service.fill();
										Iterator<People> iterproject=projectlist.iterator();
									%>	
							<div class="panel-body">
								<div class="table-responsive">		
									<table class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>用户名</th>
												<th>员工名</th>
												<th>职位</th>
												<th>所属部门</th>
												<th>备注信息</th>
												<th>操作</th>
											</tr>
										</thead>   
										<tbody>				
										<%
											while(iterproject.hasNext()){   
												People people1=iterproject.next();
										%>
											<tr>
												<td><%=people1.getUsername()%></td>
												<td><%=people1.getName()%></td>
												<td><%=people1.getTeamId()%></td>
												<td><%=people1.getBranchId()%></td>
												<td><%=people1.getPeopRemark()%></td>
												<td>
													<a class="btn btn-success" href="PeopleServlet?id=<%=people1.getId()%>&action=list2">
														<i class="fa fa-search-plus "></i>                                            
													</a>
													<a class="btn btn-info" href="data-people-form.html">
														<i class="fa fa-edit "></i>                                            
													</a>
													<a class="btn btn-danger" href="table.html#">
														<i class="fa fa-trash-o "></i> 
													</a>
												</td>	
											</tr>
										  <%
											}
									}
										%>
										
										</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>					
					</div>   
				</div>
				<!-- End Main Page -->	
		
				<!-- Usage -->
				
			
			</div>
		</div><!--/container-->
		
		
		<!-- Modal Dialog -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">Modal title</h4>
					</div>
					<div class="modal-body">
						<p class="bk-fg-danger">Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div><!-- End Modal Dialog -->		
		
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<script src="assets/vendor/js/jquery.min.js"></script>
		<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/skycons/js/skycons.js"></script>	
		
		<!-- Plugins JS-->
		<script src="assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>			
		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="assets/js/pages/table.js"></script>
		
		<!-- end: JavaScript-->
		
	</body>
	
</html>