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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=M7TQ1t1WsazHr9whomCjQ8rP"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<script src="<%=path%>/assets/plugins/modernizr/js/modernizr.js"></script>
	    <!-- Baidu Map -->

	</head>
	
	<body>
	<% 
	String record_id = request.getParameter("record_id");
	Record rcd= new Record();
	if (record_id!=null){
	 rcd = Record.getOneRecord(record_id);
	}
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
																                                  
															</div>
														</div>
													</div>																	
												</div>
											</div>
											<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
												<div class="row">
													<div class=" col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-asterisk bk-padding-right-10"></i>记录编号：
														<i class="bk-padding-right-15" id = "eva-record-id"><%=rcd.getId() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>记录区域：
														<i class="bk-padding-right-15" ><%=rcd.getRegion() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>所属部门：
														<i class="bk-padding-right-15" ><%=rcd.getBranch() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>区域类型：
														<% 
														String Region_type = "巡线点";
														if(rcd.getType().equals("site")){
															Region_type = "巡站点";
														} %>
														<i class="bk-padding-right-15" ><%=Region_type %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-user bk-padding-right-10"></i>记录人员：
														<i class="bk-padding-right-15" ><%=rcd.getGener() %></i>
													</div>
													
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-archive bk-padding-right-10"></i>班次：
														<i class="bk-padding-right-15" ><%=rcd.getPeriod_shift() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-archive bk-padding-right-10"></i>时间段：
														<i class="bk-padding-right-15" ><%=rcd.getPeriod_time() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-clock-o bk-padding-right-10"></i>开始时间：
														<%if(rcd.getStart()!=null) {%>
														<i class="bk-padding-right-15"><%=rcd.getStart().toString() %></i><%}else{ %>
														<i class="bk-padding-right-15"></i><%} %>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-clock-o bk-padding-right-10"></i>结束时间：
														<%if(rcd.getEnd()!=null) {%>
														<i class="bk-padding-right-15"><%=rcd.getEnd().toString() %></i><%}else{ %>
														<i class="bk-padding-right-15"></i><%} %>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-clock-o bk-padding-right-10"></i>提交时间：
														<%if(rcd.getSubmit()!=null) {%>
														<i class="bk-padding-right-15"><%=rcd.getSubmit().toString() %></i><%}else{ %>
														<i class="bk-padding-right-15"></i><%} %>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-clock-o bk-padding-right-10"></i>审核时间：
														<%if(rcd.getCheck()!=null) {%>
														<i class="bk-padding-right-15"><%=rcd.getCheck().toString() %></i><%}else{ %>
														<i class="bk-padding-right-15"></i><%} %>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-user bk-padding-right-10"></i>审核人：
														<%if(rcd.getCheck()!=null) {%>
														<i class="bk-padding-right-15" id="record-checker"><%=rcd.getChecker() %></i><%}else{ %>
														<i class="bk-padding-right-15" id="record-checker" ></i><%} %>
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
																	<h4 class="bk-margin-off">文字记录</h4>
																	            
																</div>
									
															</div>
														</div> 
														<p class = "bk-padding-left-20 bk-padding-right-20" id = "sheet-intro"><%=rcd.getNote() %></p>
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
																	<h4 class="bk-margin-off">图片记录</h4>
																	            
																</div>
									
															</div>
														</div>
															<div class="col-xs-6 col-md-3">
															<a href="<%=rcd.getPicture() %>" class="thumbnail">
														      <img src="<%=rcd.getPicture() %>" alt="点击查看原图">
														    </a>
														    </div>
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
																	<h4 class="bk-margin-off">地址记录</h4>
																	            
																</div>
									
															</div>
														</div> 
														<div class="col-md-8 bk-padding-bottom-20">
														<div id="bdmap-record-position" gps = "<%=rcd.getGps() %>"></div>
														</div>
														
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
																	<h4 class="bk-margin-off">记录详细内容</h4>
																</div>
																
															</div>
														</div>
													</div>
													<%
													JSONArray JA = JSONArray.fromObject(rcd.getAsws());
													 %>
													 
													<div class="col-md-10 col-md-offset-1">
														<div class=" table-responsive ">
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
														</div>
													</div>												
												</div>
											</div>
											<div class="col-md-12">
												<h6 class="text-danger text-center">共<%=rcd.getError() %>个问题不符合正常情况</h6>
												<a class="col-md-6 col-md-offset-3 btn btn-success " id = "check-button" href="#">
													<i class="fa fa-check"></i>                                            
												</a>            
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
		<script src="<%=path%>/assets/js/pages/task-evaluation-detail.js"></script>
		<script src="<%=path%>/assets/js/pages/record-postion-map.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>