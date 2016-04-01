<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.bean.*" import="java.util.*"
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
	<%
		String region_id = request.getParameter("region_id");
		Region rg = Region.getOneRegion(region_id);
		List<Question> qsts = Question.getAllQuestion(region_id);
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
						<div class="col-lg-12">	
							<div class="row profile">
								<div class="col-lg-12 col-md-12 col-sm-12 ">
									<div class="panel">
										<div class="panel-body bk-padding-left-30">
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10">
																	<h4 class="bk-margin-off">主要信息</h4>
																</div>                            
															</div>
														</div> 
														
													</div>																	
												</div>
											</div>
											<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
												<div class="row">
													<div class=" col-md-3 bk-bg-white bk-padding-top-10">
														<i class="fa fa-asterisk bk-padding-right-10"></i>区域编号：
														<i class="bk-padding-right-15"><%=rg.getId() %></i>
													</div>
													<div class="col-md-3 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>区域名称：
														<i class="bk-padding-right-15"><%=rg.getName() %></i>
													</div>
													<div class="col-md-3  bk-bg-white bk-padding-top-10">
														<i class="fa fa-group bk-padding-right-10"></i>所属部门：
														<i class="bk-padding-right-15"><%=rg.getBranch() %></i>
													</div>
													<div class="col-md-3 bk-bg-white bk-padding-top-10">
														<i class="fa fa-user bk-padding-right-10"></i>添加者：
														<i class="bk-padding-right-15"><%=rg.getGener() %></i>
													</div>
													
												</div>
											</div>
											<hr class="bk-margin-off" />
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10">
																	<h4 class="bk-margin-off">介绍</h4>
																</div>                            
															</div>
														</div> 
														<p class = "bk-padding-left-20 bk-padding-right-20"><%=rg.getIntro() %></p>
													</div>															
												</div>
											</div>
											<hr class="bk-margin-off" />
											<div class="bk-ltr bk-bg-white">
												<div class="row">
													<div class="col-md-12">
														<div class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
															<div class="row">
																<div class="text-left bk-padding-left-10">
																	<h4 class="bk-margin-off">问题列表</h4>
																</div>                            
															</div>
														</div> 
													</div>
													<div class="col-md-9 col-md-offset-1">
														<table class = "table table-bordered">
															<thead>
															<tr>
														      <th>问题标题</th>
														      <th>问题可选择答案</th>
														      <th>正常答案</th>
														    </tr>
															
															</thead>
															<tbody>
																<% 
																if (qsts.size()!=0){
																	for(int i = 0; i < qsts.size(); i++){
																%>
																<tr>
															      <td><%=qsts.get(i).getTitle() %></td>
															      <td><%=qsts.get(i).getPossibleAsw() %></td>
															      <td><%=qsts.get(i).getNormalAsw() %>
															      	<a class = "edit pull-right bk-margin-left-10" data-toggle="modal" data-target="#myModal" id="<%=qsts.get(i).getId() %>"><i class="fa fa-pencil fa-2x"></i></a>
															      	<a class = "delete pull-right" id="<%=qsts.get(i).getId() %>" href = "data-region-detail.jsp"><i class="fa fa-trash-o fa-2x"></i></a>
															      </td>
															    </tr>
															    <%}} %>
															</tbody>
															
															
														</table>
													</div>
													<div class="col-md-12">
													<form action="" method="post" class="form-inline">
														<div class="form-group">
															<input type="text" id="text-input" name="text-input" class="form-control col-md-4 col-md-offset-1" placeholder="问题内容">
															<input type="text" id="text-input" name="text-input" class="form-control col-md-4 bk-margin-left-15" placeholder="问题可选答案">
															<input type="text" id="text-input" name="text-input" class="form-control col-md-4 bk-margin-left-15" placeholder="正常答案">
															<a class="btn btn-default bk-margin-left-10" ><i class = "fa fa-plus "></i></a>
															<span class="help-block col-md-3 col-md-offset-4">每一个答案用英文";"隔开</span>
															<span class="help-block col-md-3">选择上一个问题中的一个</span>
															
														</div>
														
													</form>
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
		
				<!-- Usage -->
				
			
			</div>
		</div><!--/container-->
		
		
		<!-- Modal Dialog -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">修改</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
						<form action="" method="post" enctype="multipart/form-data" class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">问题内容</label>
								<div class="col-md-9">
									<input type="text" id="text-input" name="text-input" class="form-control" placeholder="是否有漏油?">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">可选答案</label>
								<div class="col-md-9">
									<input type="text" id="text-input" name="text-input" class="form-control" placeholder="是;否">
									<span class="help-block">每一个答案用英文";"隔开</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">正常答案</label>
								<div class="col-md-9">
									<input type="text" id="text-input" name="text-input" class="form-control" placeholder="否">
									<span class="help-block">在这里天上设备正常时应该有的答案</span>
								</div>
							</div>
						</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">保存设置</button>
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
		<script src="assets/plugins/autosize/jquery.autosize.min.js"></script>
		<script src="assets/plugins/placeholder/js/jquery.placeholder.min.js"></script>
		<script src="assets/plugins/wizard/js/jquery.bootstrap.wizard.min.js"></script>
		<script src="assets/plugins/maskedinput/js/jquery.maskedinput.js"></script>
				
		
		<!-- Theme JS -->		
		<script src="<%=path%>/assets/js/jquery.mmenu.min.js"></script>
		<script src="<%=path%>/assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="<%=path%>/assets/js/pages/table.js"></script>
		<script type="text/javascript" src="<%=path%>/assets/js/pages/data-region-detail.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>