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
	String shiftString = Period.getShift(shid);
	String[] shifts = shiftString.split(",");
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
																	<h4 class="bk-margin-off">简介</h4>
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
														<i class="bk-padding-right-15" id="sheet-branch-id" value="<%=sht.getBranch_id() %>"><%=sht.getBranch() %></i>
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
											<hr class="bk-margin-off" />
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-md-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10 col-md-10">
																	<h4 class="bk-margin-off">班次及时间点</h4>
																</div>
																<div class="col-md-2">
																	<a class="btn btn-default " id = "sheet-add-shift-button" data-toggle="modal" data-target="#sheet-add-shift-modal" href="#">
																		<i class="fa fa-plus "></i>                                            
																	</a>            
																</div>
									
															</div>
														</div>
														<div class="panel-group col-md-6" id="accordionPrimary">
														<% for (int i = 0; i< shifts.length; i++) {%>
															<div class="panel panel-accordion">
																<div class="panel-heading bk-bg-primary">
																		<h4 class="panel-title">
																			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionPrimary" href="#collapsePrimary<%=i%>">
																			<%=shifts[i] %>
																			</a>
																		</h4>
																</div>
																<div id="collapsePrimary<%=i%>" class="accordion-body collapse">
																	<div class="panel-body sheet-times">
																	
																	<% 
																	List<Period> prs =Period.getPeriodfromShift(shid, shifts[i]);
																	for (int j = 0; j<prs.size(); j++){
																		Period tempP = prs.get(j);
																	%>
																	<div class="btn-group col-md-3 single-time" value = "<%=tempP.getTime() %>">
																		<button class = "btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																		<%=tempP.getTime() %>
																		</button>
																		<ul class="dropdown-menu" >
																		    <li><a href="#" value = "<%=tempP.getId() %>" class = "sheet-view-period-button" data-toggle="modal" data-target="#sheet-view-period-modal" >查看检查区域</a></li>
																		    <li><a href="#"  value = "<%=tempP.getId() %>,<%=shifts[i]%>,<%=tempP.getTime() %>"class = "sheet-edit-period-button" data-toggle="modal" data-target="#sheet-edit-period-modal">修改</a></li>
																		    <li><a href="#" value = "<%=tempP.getId() %>"class = "sheet-delete-period-time">删除</a></li>
																		</ul>
																	</div>
																	<%
																	}
																	%>
																	<div class="btn-group col-md-6"  >
																
																		<button class = "btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																		操作
																		</button>
																		<ul class="dropdown-menu">
																		    <li><a href="#" class = "sheet-add-period" data-toggle="modal" data-target="#sheet-add-period-modal" value = "<%=shifts[i] %>">添加时间点</a></li>
																		    <li><a href="#" class = "sheet-delete-shift"  value = "<%=shifts[i] %>">删除班次</a></li>
																		</ul>
																	</div>
																	</div>
																</div>
															</div>
															
														<%} %>
														</div>
													</div>															
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>   
				</div>
				<!-- End Main Page -->	
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
									<textarea id="sheet-edit-intro" name="intro" rows="4" class="form-control"></textarea>									
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
		
		<div class="modal fade" id="sheet-add-period-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">添加时间点</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
						<form id = "addperiodtime" action="AddPeriodServlet" method="post" class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group" hidden = "hidden">
							<input type="text" name="index" class="form-control" value="one" />
							<input type="text" name="gener_id" id = "period-add-time-gener" class="form-control" />
							<input type="text" name="sheet_id" id = "period-add-time-sheet" class="form-control" />
							</div>
							<div class="form-group" hidden = "hidden">
								<label class="col-md-3 control-label">班次</label>
								<div class="col-md-9">
									<input type="text" name="shift" id = "period-add-time-shift" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="select">时间点</label>
								<div class="col-md-9">
									<select id="period-add-time-options" name="time" class="form-control" size="1">
										
									</select>
								</div>
							</div>
							
							<button type = "submit" class = "btn btn-success col-md-12">确认</button>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="sheet-edit-period-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">修改时间点</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
						<form id = "changeperiodtime" action="ChangePeriodServlet" method="post" class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group" hidden="hidden">
							<input type="text" name="period_id" id = "period-edit-time-id" class="form-control" />
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">班次</label>
								<div class="col-md-9">
									<select id="period-edit-shift-options" name="shift" class="form-control" size="1">
						
										<%
										for(int k=0; k< shifts.length; k++){
										%>
										<option value = "<%=shifts[k] %>"><%=shifts[k] %></option>
										<%
										}%>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="select">时间点</label>
								<div class="col-md-9">
									<select id="period-edit-time-options" name="time" class="form-control" size="1">
									</select>
								</div>
							</div>
							
							<button type = "submit" class = "btn btn-success col-md-12">确认</button>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="sheet-view-period-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">添加链接</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
							<div class="col-md-offset-1 col-md-10">
								<div class="col-md-5" >
								已选择
								<table class = "col-md-12 table table-hover table-bordered" >
								<tbody id="SelectedRegions">
								</tbody>
								</table>
								</div> 
								<div class ="col-md-1 bk-margin-top-15" ><i class ="fa fa-arrows-h fa-2x"></i></div>
								<div class="col-md-5 col-md-offset-1" >
								未选择
								<table class = "col-md-12 table table-hover table-bordered">
								<tbody id="RestRegions">
								</tbody>
								</table>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="sheet-add-shift-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">添加班次</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
							<form class="form-horizontal col-md-offset-2 col-md-8" role="form">
								<div class="form-group">
									<label class="col-md-3 control-label">班次名</label>
									<div class="col-md-9">
										<input type="text" name="shift-add-name" id = "shift-add-name" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">时间点(至少1个)</label>
									<div class="col-md-9">
										<div class="col-md-4">
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox1" name="times" value="00:00"> 
												<label for="checkbox1"> 00:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox2" name="times" value="02:00"> 
												<label for="checkbox2"> 02:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox3" name="times" value="04:00"> 
												<label for="checkbox3"> 04:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox4" name="times" value="06:00"> 
												<label for="checkbox4"> 06:00</label>
											</div>
										</div>
										<div class="col-md-4">
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox5" name="times" value="08:00"> 
												<label for="checkbox5"> 08:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox6" name="times" value="10:00"> 
												<label for="checkbox6"> 10:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox7" name="times" value="12:00"> 
												<label for="checkbox7"> 12:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox8" name="times" value="14:00"> 
												<label for="checkbox8"> 14:00</label>
											</div>
										</div>
										<div class="col-md-4">
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox9" name="times" value="16:00"> 
												<label for="checkbox9"> 16:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox10" name="times" value="18:00"> 
												<label for="checkbox10"> 18:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox11" name="times" value="20:00"> 
												<label for="checkbox11"> 20:00</label>
											</div>
											<div class="checkbox-custom">
												<input type="checkbox" id="checkbox12" name="times" value="22:00"> 
												<label for="checkbox12"> 22:00</label>
											</div>
										</div>
									</div>
								</div>
								
																			
	
							</form>
							<button id = "shift-add-submit-button" class = "btn btn-success col-md-8 col-md-offset-2">确认</button>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
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