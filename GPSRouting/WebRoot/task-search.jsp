<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.bean.Sheet" import="java.util.List"
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
		<link href="assets/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
		<!-- Theme CSS -->
		<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
		
		<!-- Page CSS -->		
		<link href="assets/css/style.css" rel="stylesheet" />
		<link href="assets/css/add-ons.min.css" rel="stylesheet" />
		
		
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
	<%
	 	List<Sheet> shts = Sheet.getAllSheet();
	%>
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
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading bk-bg-primary">
									<h6><i class="fa fa-table red"></i><span class="break"></span>任务表列表</h6>
									<div class="panel-actions">
										<a href="table.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
										<a data-toggle="modal" data-target="#add-sheet-modal" class="btn-plus"><i class="fa fa-plus"></i></a>
										<a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>

									</div>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<div class="col-md-3">
											<select id="select" name="select" class="form-control" size="1">
												<option value="0">选择部门</option>
												<option value="1">Option #1</option>
												<option value="2">Option #2</option>
												<option value="3">Option #3</option>
											</select>
										</div>
										<button class = "btn btn-success">确认</button>
									</div>
									<div class="table-data-show">	
										<table class="table table-hover table-bordered bootstrap-datatable datatable">
											<thead>
												<tr>
													<th>表名</th>
													<th>所属部门</th>
													<th>创建者</th>
												</tr>
											</thead>   
											<tbody>
											<% for(int i= 0; i < shts.size(); i ++){ %>
												<tr style=cursor:pointer; onclick=location.href="task-search-detail.jsp?sheet_id=<%=shts.get(i).getId() %>">
												
													<td><%=shts.get(i).getName() %></td>
													<td><%=shts.get(i).getBranch() %></td>
													<td><%=shts.get(i).getGener() %>
														<a class="btn btn-danger sheet-delete pull-right" value="0">
															<i class="fa fa-trash-o "></i> 

														</a>
													</td>
													
												</tr>
												<%} %>
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
		<div class="modal fade" id="add-sheet-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">Modal title</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
							<form id="add-sheet-form" method="post" class="form-horizontal col-md-8 col-md-offset-2" action = "AddSheetServlet" role="form">
								<div class="form-group">
									<label class="col-md-3 control-label">表名</label>
									<div class="col-md-9">
										<input type="text" name="name" class="form-control" />
									</div>
								</div>
								<div class="form-group" hidden="hidden">
									<label class="col-md-3 control-label">创建者名</label>
									<div class="col-md-9">
										<input type="text" name="gener_id" class="form-control" value="0001" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for="select">所属部门</label>
									<div class="col-md-9">
										<select id="sheet-add-branhc" name="branch_id" class="form-control" size="1">
											<option value="1">部门1</option>
											<option value="2">部门2</option>
										</select>
									</div>
								</div>
								<div class="form-group ">
									<label class="col-md-3 control-label">表介绍</label>
									<div class="col-md-9">
										<textarea id="sheet-add-intro" name="intro" rows="4" class="form-control" placeholder="输入内容"></textarea>
									</div>
								</div>
								<button type = "submit" class = "btn btn-success col-md-12">确认</button>											
	
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
		<script src="assets/vendor/js/jquery.form.js"></script>
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
		<script src="assets/plugins/chosen/js/chosen.jquery.min.js"></script>
		<script src="assets/plugins/autosize/jquery.autosize.min.js"></script>
		<script src="assets/plugins/placeholder/js/jquery.placeholder.min.js"></script>
		<script src="assets/plugins/wizard/js/jquery.bootstrap.wizard.min.js"></script>
		<script src="assets/plugins/maskedinput/js/jquery.maskedinput.js"></script>
		<script src="assets/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.js"></script>

		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="assets/js/pages/task-search.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>