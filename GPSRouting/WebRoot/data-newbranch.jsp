<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
		<link href="<%=path%>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="<%=path%>/assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="<%=path%>/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		
		<!-- Plugins CSS-->
		<link href="<%=path%>/assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
		<link href="<%=path%>/assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />
		<link href="<%=path%>/assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />					
		
		<!-- Theme CSS -->
		<link href="<%=path%>/assets/css/jquery.mmenu.css" rel="stylesheet" />
		
		<!-- Page CSS -->		
		<link href="<%=path%>/assets/css/style.css" rel="stylesheet" />
		<link href="<%=path%>/assets/css/add-ons.min.css" rel="stylesheet" />
		
		<!-- end: CSS file-->	
	    
		
		<!-- Head Libs -->
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->		
		<script src="<%=path%>/assets/plugins/modernizr/js/modernizr.js"></script>
	    <!-- Baidu Map -->

	</head>
	
	<body>
	
		<!-- Start: Header -->
		<!-- Start: Header -->
		<jsp:include page="navbar.jsp"></jsp:include>
		<!-- End: Header -->
		<div class="copyrights">Collect from CUG </div>
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">
			
				<!-- Sidebar -->
				<jsp:include page="sidebar.jsp"></jsp:include>
				<!-- End Sidebar -->
		
				<!-- Main Page -->
				<div class="main ">
					<!-- Page Header -->
					<div class="page-header">
						<jsp:include page="header.jsp"></jsp:include>
						<div class="pull-right">
							<h2>Tables</h2>
						</div>					
					</div>
					<!-- End Page Header -->

					<div class="row">		
						<div class="col-lg-12">	
							<div class="panel">
								<div class="panel-heading bk-bg-primary">
								<h6>
									<i class="fa fa-table red"></i><span class="break"></span>部门信息
								</h6>
								<div class="panel-actions">
									<a href="table.html#" class="btn-setting"><i
										class="fa fa-rotate-right"></i></a> <a data-toggle="modal"
										data-target="#myModal" class="btn-plus"><i
										class="fa fa-plus"></i></a> <a href="table.html#"
										class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<%
								Branch branch = (Branch)request.getAttribute("branch");
								if (branch != null) {
							%>
							<div class="panel-body bk-padding-left-30">
							<div class="bk-ltr bk-bg-white">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
											<div class="row">
												<div class="text-left bk-padding-left-10 col-md-10">
													<h4 class="bk-margin-off">部门详细信息</h4>
												</div>                       
											</div>
										</div> 
														
									</div>																	
								</div>
							</div>
							<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
								<div class="row">
									<div class=" col-md-4 bk-bg-white bk-padding-top-10">
										<i class="fa fa-asterisk bk-padding-right-10"></i>部门ID：
										<i class="bk-padding-right-15" id="region-id"><%=branch.getId() %></i>
									</div>
									<div class="col-md-4 bk-bg-white bk-padding-top-10">
										<i class="fa fa-user bk-padding-right-10"></i>名称：
										<i class="bk-padding-right-15" id="region-name"><%=branch.getBranchName() %></i>
									</div>
									<div class="col-md-4  bk-bg-white bk-padding-top-10" >
										<i class="fa fa-group bk-padding-right-10"></i>部门编码：
										<i class="bk-padding-right-15"><%=branch.getBranchCode() %></i>
									</div>
									<div class="col-md-4 bk-bg-white bk-padding-top-10">
										<i class="fa fa-user-md bk-padding-right-10"></i>部门类型：
										<i class="bk-padding-right-15" >
										<%=branch.getBranchType() %></i>
									</div>
									
									<div class="col-md-4 bk-bg-white bk-padding-top-10">
										<i class="fa fa-circle-o bk-padding-right-10"></i>所属分公司：
										<i class="bk-padding-right-15" id="region-range"><%=branch.getComName() %></i>
									</div>
									<div class="col-md-4 bk-bg-white bk-padding-top-10">
										<i class="fa fa-circle-o bk-padding-right-10"></i>分公司编码：
										<i class="bk-padding-right-15" id="region-range"><%=branch.getComId() %></i>
									</div>														
								</div>
							</div>	
						</div>
							<%-- 部门ID：<%=branch.getId() %><br /> 
							名称：<%=branch.getBranchName() %><br />
							部门编码：<%=branch.getBranchCode() %><br /> 
							部门类型：<%=branch.getBranchType() %><br />
							所属分公司：<%=branch.getComName() %><br />
							公司编码：<%=branch.getComId() %><br /> --%>
							<%
								} else {
							%>
							<jsp:useBean id="service" class="com.service.BranchService"
								scope="session">
							</jsp:useBean>
							<%
								List<Branch> projectlist = service.fill();
									Iterator<Branch> iterproject = projectlist.iterator();
							%>

							<div class="panel-body">
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>部门ID</th>
												<th>部门名称</th>
												<th>部门类型</th>
												<th>部门编码</th>
												<th>分公司名称</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<%
												while (iterproject.hasNext()) {
														Branch branch1 = iterproject.next();
											%>
											<tr>
												<td><%=branch1.getId() %></td>
												<td><%=branch1.getBranchName() %></td>
												<td><%=branch1.getBranchType() %></td>
												<td><%=branch1.getBranchCode() %></td>
												<td><%=branch1.getComName() %></td>
												<td><a class="btn btn-success"
													href="ManageBranchServlet?id=<%=branch1.getId() %>&action=list2">
														<i class="fa fa-search-plus "></i>
												</a> <a class="btn btn-info"
													href="ManageBranchServlet?id=<%=branch1.getId() %>&action=update">
														<i class="fa fa-edit "></i>
												</a> <a class="btn btn-danger branch-delete" 
												value= "<%=branch1.getId() %>"> <i
														class="fa fa-trash-o "></i>
												</a></td>
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
						<h4 class="modal-title bk-fg-primary">添加部门信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal "
						action="ManageBranchServlet?action=add" method="post">
						<div class="form-group">
							<label class=" col-md-4 control-label">部门名称</label>
							<div class="col-md-5">
								<input type="text" id="branch_name" name="branch_name"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class=" col-md-4 control-label" for="text-input">部门类型</label>
							<div class="col-md-5">
								<select id="branch_type" name="branch_type" class="form-control">
									<option value="" disabled selected>请选择部门类型</option>
									<option value="管理">管理</option>
									<option value="站场">站场</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">部门编码</label>
							<div class="col-md-5">
								<div class="input-group">
									<input type="text" id="branch_code" name="branch_code" class="form-control"
										placeholder="编码格式：AABBCC">
								<div class="input-group-btn">
        							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:red">#</button>
        							<ul class="dropdown-menu">
          								<li>
          									编码格式：AABBCC<br>
          									AA:分公司代码，默认01开始<br>
          									BB:部门代码，默认01开始<br>
          									CC:部门人员代码：默认01开始<br>         								
          								</li>
       								</ul>
     							 </div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">分公司名称</label>
							<div class="col-md-5">
								<input type="text" id="com_name" name="com_name"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">分公司编码</label>
							<div class="col-md-5">
								<input type="text" id="com_id" name="com_id"
									class="form-control" />
							</div>
						</div>
						<div class="form-group" style="text-align:center;">
							<input type="submit" class="bk-margin-5 btn btn-primary"
								value="提交">
							<input type="reset" class="bk-margin-5 btn btn-primary"
								value="重置">
						</div>
						<br>
					</form>
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
		<script src="<%=path%>/assets/vendor/js/jquery.min.js"></script>
		<script src="<%=path%>/assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="<%=path%>/assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="<%=path%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=path%>/assets/vendor/skycons/js/skycons.js"></script>	
		
		<!-- Plugins JS-->
		<script src="<%=path%>/assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
		<script src="<%=path%>/assets/plugins/moment/js/moment.min.js"></script>	
		<script src="<%=path%>/assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>			
		
		<!-- Theme JS -->		
		<script src="<%=path%>/assets/js/jquery.mmenu.min.js"></script>
		<script src="<%=path%>/assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="<%=path%>/assets/js/pages/table.js"></script>
		
		
		<script type="text/javascript"
		src="<%=path%>/assets/js/pages/data-branch.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>