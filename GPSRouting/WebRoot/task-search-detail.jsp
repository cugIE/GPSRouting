<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.bean.*" import="java.util.List"
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
	String shid = request.getParameter("sheet_id");
	Sheet sht = Sheet.getOneSheet(shid);
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
							
							<div class="row profile">
								<div class="col-md-12">
									<div class="panel">
										<div class="panel-body bk-padding-left-30">
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-md-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10 col-md-10">
																	<h4 class="bk-margin-off"><%=sht.getName() %></h4>
																</div>
																<div class="col-md-2">
																	<a class="btn btn-default " id = "sheet-edit-button" data-toggle="modal" data-target="#sheet-edit-modal" href="#">
																		<i class="fa fa-edit "></i>                                            
																	</a>            
																</div>                                   
															</div>
														</div>
													</div>																	
												</div>
											</div>
											<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
												<div class="row">
													<div class=" col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-asterisk bk-padding-right-10"></i>表编号：
														<i class="bk-padding-right-15" id="sheet-id"><%=sht.getId() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>表名称：
														<i class="bk-padding-right-15" id="sheet-name"><%=sht.getName() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-user bk-padding-right-10"></i>所属部门：
														<i class="bk-padding-right-15" ><%=sht.getBranch() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-circle-o bk-padding-right-10"></i>添加者：
														<i class="bk-padding-right-15"><%=sht.getGener() %></i>
													</div>
													
												</div>
											</div>
											<hr class="bk-margin-off" />
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-md-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10 ">
																	<h4 class="bk-margin-off">介绍</h4>
																	            
																</div>
									
															</div>
														</div> 
														<p class = "bk-padding-left-20 bk-padding-right-20" id = "sheet-intro"><%=sht.getIntro() %></p>
													</div>															
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						

					</div>					
					<div class="row">						
						<div class="col-lg-12">
							<div class="panel bk-bg-white">
								<div class="panel-heading bk-bg-primary">
									<h6><i class="fa fa-tags red"></i>任务</h6>
									<div class="panel-actions">
										<a href="form-wizard.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
										<a href="form-wizard.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
										<a href="form-wizard.html#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div id="wizard1" class="wizard-type1">
										<ul class="steps">
											<li><a href="form-wizard.html#tab11" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-calendar-o"></i></span> 班次</a></li>
											<li><a href="form-wizard.html#tab12" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-clock-o"></i></span> 选择时间点</a></li>
											<li><a href="form-wizard.html#tab13" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-building"></i></span> 安排区域</a></li>
											<li><a href="form-wizard.html#tab14" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-bars"></i></span> 任务详情</a></li>
										</ul>
										<div class="progress thin progress-striped active">
											<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
											</div>
										</div>
										<div class="tab-content">
											<div class="tab-pane" id="tab11">
												<form class="form-horizontal" role="form">
													<label for="name-w1">班次名</label>
													<div class="row">
														<div class="form-group col-md-5 has-feedback">
															
															<input type="text" class="form-control" id="sheet-add-shift-text" placeholder="输入班次名" />

														</div>
														<button class ="col-md-1 btn btn-primary btn-sm" id="sheet-add-shift-button" type = "button">添加</button>
														<div class="col-md-6">
															
															<div class="form-group">
																<div class="col-md-12">
																	<input name="" id="sheet-shifts" data-role="tagsinput" data-tag-class="label label-primary" class="form-control" value="<%=Period.getShift(shid) %>" />
																	
																</div>
															</div>
									
															
														</div>
													</div>
													
												</form>	
											</div>
											<div class="tab-pane" id="tab12">
												
												<div class="row">
													<form>
													<div class="form-group col-md-4">
														<label for="ccmonth-w1">班次</label>
														<select class="form-control" id="ccmonth-w1">
															<option>白班</option>
															<option>夜班</option>
																											
														</select>
													</div>
													<div class="form-group col-md-8">
														<label class="col-md-3 control-label">选择时间点</label>
														<div class="col-md-3">
															<div class="checkbox-custom">
																<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																<label for="checkbox1"> 00:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox2" name="checkbox2" value="option2"> 
																<label for="checkbox2"> 02:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox3" name="checkbox3" value="option3"> 
																<label for="checkbox3"> 04:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox4" name="checkbox4" value="option4"> 
																<label for="checkbox4"> 06:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox5" name="checkbox5" value="option5"> 
																<label for="checkbox5"> 08:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox5" name="checkbox5" value="option5"> 
																<label for="checkbox5"> 10:00</label>
															</div>
														</div>
														<div class="col-md-3">
															<div class="checkbox-custom">
																<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																<label for="checkbox1"> 12:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox2" name="checkbox2" value="option2"> 
																<label for="checkbox2"> 14:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox3" name="checkbox3" value="option3"> 
																<label for="checkbox3"> 16:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox4" name="checkbox4" value="option4"> 
																<label for="checkbox4"> 18:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox5" name="checkbox5" value="option5"> 
																<label for="checkbox5"> 20:00</label>
															</div>
															<div class="checkbox-custom">
																	<input type="checkbox" id="checkbox5" name="checkbox5" value="option5"> 
																<label for="checkbox5"> 22:00</label>
															</div>
														</div>
														<button class="btn btn-info btn-sm col-md-2" type = "submit" >确认</button>
													</div>
													</form>
												</div>
											</div>
											<div class="tab-pane" id="tab13">
												<div class="row">
													<form>
													<div class="form-group col-md-4">
														<label for="ccmonth-w1">时间点</label>
														<select class="form-control" id="ccmonth-w1">
															<option>白班 00:00</option>
															<option>夜班 21:00</option>
																											
														</select>
													</div>
													<div class="col-md-6">
														<div class="table-responsive" >
															<table id="region-table" class="table table-bordered" >
																  <thead>
																	  <tr>
																		  <th>区域</th>				
																	  </tr>
																  </thead>   
																  <tbody>
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域1</label>
																			</div>
																		</td>

																	</tr>
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域2</label>
																			</div>
																		</td>

																	</tr>
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域3</label>
																			</div>
																		</td>

																	</tr>							
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域2</label>
																			</div>
																		</td>

																	</tr>
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域2</label>
																			</div>
																		</td>

																	</tr>
																	<tr>
																		<td>
																			<div class="checkbox-custom">
																				<input type="checkbox" id="checkbox1" name="checkbox1" value="option1"> 
																				<label for="checkbox1"> 区域2</label>
																			</div>
																		</td>

																	</tr>                                  
																  </tbody>
															</table>
														</div>
														<button class="bk-margin-top-15 btn btn-success btn-sm pull-right" type="submit">确认</button>
													</div>
													
													</form>
												</div>
											</div>
											<div class="tab-pane" id="tab14">
												<div class="table-responsive">	
													<table class="table table-striped table-bordered bootstrap-datatable datatable">
														<thead>
															<tr>
																<th>Employe</th>
																<th>Position</th>
																<th>Salary</th>
																<th>Status</th>
																<th>Actions</th>
															</tr>
														</thead>   
														<tbody>								
															<tr>
																<td>Willson</td>
																<td>Developer</td>
																<td>2563$</td>
																<td>
																	<span class="label label-warning">Pending</span>
																</td>
																<td>
																	<a class="btn btn-success" href="table.html#">
																		<i class="fa fa-search-plus "></i>                                            
																	</a>
																	<a class="btn btn-info" href="table.html#">
																		<i class="fa fa-edit "></i>                                            
																	</a>
																	<a class="btn btn-danger" href="table.html#">
																		<i class="fa fa-trash-o "></i> 

																	</a>
																</td>
															</tr>
															<tr>
																<td>James</td>
																<td>SEO</td>
																<td>5000$</td>
																<td>
																	<span class="label label-warning">Pending</span>
																</td>
																<td>
																	<a class="btn btn-success" href="table.html#">
																		<i class="fa fa-search-plus "></i>                                            
																	</a>
																	<a class="btn btn-info" href="table.html#">
																		<i class="fa fa-edit "></i>                                            
																	</a>
																	<a class="btn btn-danger" href="table.html#">
																		<i class="fa fa-trash-o "></i> 
																	</a>
																</td>
															</tr>
															<tr>
																<td>Steven</td>
																<td>Photographer</td>
																<td>1269$</td>
																<td>
																	<span class="label label-warning">Pending</span>
																</td>
																<td>
																	<a class="btn btn-success" href="table.html#">
																		<i class="fa fa-search-plus "></i>                                            
																	</a>
																	<a class="btn btn-info" href="table.html#">
																		<i class="fa fa-edit "></i>                                            
																	</a>
																	<a class="btn btn-danger" href="table.html#">
																		<i class="fa fa-trash-o "></i> 
																	</a>
																</td>
															</tr>
															<tr>
																<td>Aselay</td>
																<td>Project manger</td>
																<td>6253$</td>
																<td>
																	<span class="label label-warning">Pending</span>
																</td>
																<td>
																	<a class="btn btn-success" href="table.html#">
																		<i class="fa fa-search-plus "></i>                                            
																	</a>
																	<a class="btn btn-info" href="table.html#">
																		<i class="fa fa-edit "></i>                                            
																	</a>
																	<a class="btn btn-danger" href="table.html#">
																		<i class="fa fa-trash-o "></i> 
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="actions">								
											<input type="button" class="btn btn-info button-previous" name="previous" value="Previous" />
											<input type="button" class="btn btn-primary button-next pull-right" name="next" value="Next" />
											
										</div>
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
		<div class="modal fade" id="sheet-edit-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">修改</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
							<form method="post" id="sheet-edit-form" class="form-horizontal col-md-offset-2 col-md-8" action = "ChangeSheetServlet" role="form">
								<div class="form-group">
									<label class="col-md-3 control-label">编号</label>
									<div class="col-md-9">
										<input type="text" name="sheet_id" id = "sheet-edit-id" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">表名</label>
									<div class="col-md-9">
										<input type="text" name="name" id = "sheet-edit-name" class="form-control" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">表介绍</label>
									<div class="col-md-9">
									<textarea id="sheet-edit-intro" name="intro" rows="4" class="form-control"></textarea>									</div>
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
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
		<script src="assets/plugins/chosen/js/chosen.jquery.min.js"></script>
		<script src="assets/plugins/autosize/jquery.autosize.min.js"></script>
		<script src="assets/plugins/placeholder/js/jquery.placeholder.min.js"></script>
		<script src="assets/plugins/wizard/js/jquery.bootstrap.wizard.min.js"></script>
		<script src="assets/plugins/maskedinput/js/jquery.maskedinput.js"></script>
		<script src="assets/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.js"></script>
		<script src="assets/vendor/js/jquery.form.js"></script>

		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="assets/js/pages/task-search-detail.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>