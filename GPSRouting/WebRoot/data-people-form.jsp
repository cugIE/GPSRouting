<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.bean.People"
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
						<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading bk-bg-primary">
									<h6><i class="fa fa-indent red"></i>修改或添加</h6>							
								</div>
								<div class="panel-body">
									<div class="text-left bk-bg-white bk-padding-top-40 bk-padding-bottom-10">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bk-vcenter text-center">                                
												<div class="bk-avatar">
													<img src="assets/img/avatar.jpg" alt="" class="img-circle bk-img-120 bk-border-light-gray bk-border-3x" />
												</div>
											</div>
										</div>
									</div>
									<form action="AddPeopleServlet" method="post" enctype="multipart/form-data" class="form-horizontal ">
										<div class="form-group">
											<label class="col-md-3 control-label">用户名</label>
											<div class="col-md-6">
												<input type="text" id="username" name="username" class="form-control" placeholder="用户名">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="password-input">密码</label>
											<div class="col-md-6">
												<input type="password" id="password" name="password" class="form-control" placeholder="密码">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">姓名</label>
											<div class="col-md-6">
												<input type="text" id="name" name="name" class="form-control" placeholder="姓名">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">性别</label>
											<div class="col-md-9">
												<div class="radio-custom radio-inline">
													<input type="radio" id="inline-radio1" name="sex" value="option1"> 
													<label for="inline-radio1"> 男</label>
												</div>
												<div class="radio-custom radio-inline">
													<input type="radio" id="inline-radio2" name="sex" value="option2"> 
													<label for="inline-radio2"> 女</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">编码</label>
											<div class="col-md-6">
												<input type="text" id="peopleid" name="peopleid" class="form-control" placeholder="编码格式：AABBCC">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">部门</label>
											<div class="col-md-6">
												<select name="branchname" id="branchid" class="form-control">
												<option value="1">111</option>
												<option value="2">222</option>
												<option value="3">333</option>
												<option value="4">444</option>
												<option value="5">555</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">组类型</label>
											<div class="col-md-6">
												<input type="text" id="email-input" name="email-input" class="form-control" placeholder="值班员、值班干部、工程师等"><input type="text"name="teamtext" class="form-control" value="组说明"disabled="true">
											</div>
										</div>
							
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">备注</label>
											<div class="col-md-6">
												<input type="text" id="peopRemark" name="peopRemark" class="form-control" placeholder="备注信息">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-3 control-label" for="file-input">更换照片</label>
											<div class="col-md-9">
												<input type="file" id="file-input" name="file-input">
											</div>
										</div>
										
										<br>
									</form>
									<hr class="bk-margin-off" />
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bk-ltr bk-bg-white text-right">	
										<button type="submit" class="bk-margin-5 btn btn-primary"><i class="fa fa-send"></i> 确认</button>
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
		<script type="text/javascript" src="assets/js/pages/device-map.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>