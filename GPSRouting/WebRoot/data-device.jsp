<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script src="assets/plugins/modernizr/js/modernizr.js"></script>
	    <!-- Baidu Map -->
	    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<!-- 		<script type="text/javascript" src="assets/js/pages/device-map.js"></script>
 -->		
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
									<h6><i class="fa fa-table red"></i><span class="break"></span>设备详细列表</h6>
									<div class="panel-actions">
										<a href="table.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
										<a href="data-device-form.html#" class="btn-plus"><i class="fa fa-plus"></i></a>
										<a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>

									</div>
								</div>
								<div class="panel-body">
									<div class="table-responsive">	
										<table class="table table-striped table-bordered bootstrap-datatable datatable">
											<thead>
												<tr>
													<th>设备编号</th>
													<th>设备名</th>
													<th>设备所在地</th>
													<th>问题</th>
													<th>操作</th>
												</tr>
											</thead>   
											<tbody>								
												<tr>												
												<td>2563</td>
													<td>气缸</td>
													<td>中国地质大学</td>
													<td>22:10 09/01/2015</td>
													<td>
														<div class="btn-group">

															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																<i class="fa fa-qrcode"></i>
															</button>
														    <ul class="dropdown-menu">
															    <li><a href="pic/qrcode.png"><img class="img-thumbnail" src="pic/qrcode.png"></a></li>
														    </ul>
													    </div>
														<a class="btn btn-info" href="data-device-form.html#">
															<i class="fa fa-edit "></i>                                            
														</a>
														<a class="btn btn-success" href="data-device-addques.html#">
															<i class="fa fa-plus "></i>                                            
														</a>
														<a class="btn btn-danger" href="table.html#">
															<i class="fa fa-trash-o "></i> 

														</a>
													</td>
												</tr>
												<tr>												
													<td>2563</td>
													<td>气缸</td>
													<td>中国地质大学</td>
													<td>22:10 09/01/2015</td>
													<td>
														<div class="btn-group">

															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																<i class="fa fa-qrcode"></i>
															</button>
														    <ul class="dropdown-menu">
															    <li><a href="pic/qrcode.png"><img class="img-thumbnail" src="pic/qrcode.png"></a></li>
														    </ul>
													    </div>
														<a class="btn btn-info" href="data-device-form.html#">
															<i class="fa fa-edit "></i>                                            
														</a>
														<a class="btn btn-success" href="data-device-addques.html#">
															<i class="fa fa-plus "></i>                                            
														</a>

														<a class="btn btn-danger" href="table.html#">
															<i class="fa fa-trash-o "></i> 

														</a>
													</td>
												</tr>
												<tr>												
													<td>2563</td>
													<td>气缸</td>
													<td>中国地质大学</td>
													<td>22:10 09/01/2015</td>
													<td>
														<div class="btn-group">

															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																<i class="fa fa-qrcode"></i>
															</button>
														    <ul class="dropdown-menu">
															    <li><a href="pic/qrcode.png"><img class="img-thumbnail" src="pic/qrcode.png"></a></li>
														    </ul>
													    </div>
														<a class="btn btn-info" href="data-device-form.html#">
															<i class="fa fa-edit "></i>                                            
														</a>
														<a class="btn btn-success" href="data-device-addques.html#">
															<i class="fa fa-plus "></i>                                            
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
							<div class="panel">
								<div class="panel-heading bk-bg-primary">
									<h6><i class="fa fa-table red"></i><span class="break"></span>设备位置</h6>
									<div class="panel-actions">
										<a href="table.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
										<a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
									</div>
									
								</div>
								<div class="panel-body">
								<!-- put baidu map here-->
									<button type="button" class="btn btn-info btn-sm col-md-1 bk-margin-5 pull-right" onclick = "getSelRoute()"><i class="fa fa-search"></i></button>  
										<div class="col-md-3 bk-margin-5 center-block pull-right">
											<select id="selectRoute" class="form-control" size="1"> 
												<option value="0">选择巡线路线</option>   
										        <option value="1">1号线</option>   
										        <option value="2">2号线</option>   
										        <option value="3">3号线</option>   
										        <option value="4">4号线</option>   
									        	<option value="5">5号线</option>             
									     	</select>  
										</div>
									<div id="allmap"></div>	
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
		<script type="text/javascript" src="assets/js/pages/device-map.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>