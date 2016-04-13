<%@ page language="java" contentType="text/html; charset=UTF-8" import = "com.bean.Region" import = "com.bean.Branch" import = "java.util.*"  import = "com.service.*"
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
	<% 
		
		String brch_id = request.getParameter("branch_id");
		List<Region> rgs= null;
		
		HttpSession tempSession = request.getSession();
		int branch_id = (int)session.getAttribute("SesBranchId");
		int team_id = (int)session.getAttribute("SesTeamId");
		BranchService bs = new BranchService();
		Branch brch = bs.fill(branch_id+"");
		String branchType = brch.getBranchType();
		if(branchType.equals("管理")){
			if(brch_id==null){
				rgs=Region.getAllRegion();
			}
			else{
				rgs=Region.getAllRegion(brch_id);
			}
		}
		else{
			rgs=Region.getAllRegion(branch_id+"");
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
							<h2><%=brch.getBranchName() %></h2>
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
										<a data-toggle="modal" data-target="#myModal" class="btn-plus"><i class="fa fa-plus"></i></a>
										<a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>

									</div>
								</div>
								<div class="panel-body">
								<% if (brch.getBranchType().equals("管理")){ 
									List<Branch> brchs = bs.fill();
								%>
								<form action="data-region.jsp"  method="get">
									<div class="form-group">
										<div class="col-md-3">
											<select id="select" name="branch_id" class="form-control" size="1">
												<%for (int i = 0; i<brchs.size();i++){ %>
												<option value="<%=brchs.get(i).getId() %>"><%=brchs.get(i).getBranchName() %></option>
												<%} %>
											</select>
										</div>
										<button class = "btn btn-success">确认</button>
									</div>
								</form>
								<%} %>
									<div class="table-data-show">	
										<table class="table table-striped table-bordered bootstrap-datatable datatable">
											<thead>
												<tr>
													<th>区域编号</th>
													<th>区域名</th>
													<th>所属部门</th>
													<th>操作</th>
												</tr>
											</thead>   
											<tbody>								
																								
												<%
												for(int i = 0; i < rgs.size(); i++){
													Region rg = rgs.get(i);
													
												%>
												<tr>
													<td><%=rg.getId() %></td>
													<td><%=rg.getName() %></td>
													<td><%=rg.getBranch() %></td>
													<td>
														<div class="btn-group">

															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																<i class="fa fa-qrcode"></i>
															</button>
														    <ul class="dropdown-menu">
															    <li><a class = "btn btn-default" href="<%= basePath %>GenerateQRCode?region_id=<%=rg.getId() %>&size=200">200*200</a></li>
															    <li><a class = "btn btn-default" href="<%= basePath %>GenerateQRCode?region_id=<%=rg.getId() %>&size=400">400*400</a></li>
															    <li><a class = "btn btn-default" href="<%= basePath %>GenerateQRCode?region_id=<%=rg.getId() %>&size=1000">1000*1000</a></li>
														    </ul>
													    </div>
														<a class="btn btn-info" href="<%= basePath %>data-region-detail.jsp?region_id=<%=rg.getId() %>">
															<i class="fa fa-search "></i>                                            
														</a>
														
														<a class="btn btn-danger region-delete" value="<%=rg.getId() %>">
															<i class="fa fa-trash-o "></i> 

														</a>
													</td>
													
												</tr>
												<% } %>
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
									<div class="form-group">
										<div class="col-md-3">
											<select id="selectRoute" class="form-control" size="1"> 
												<option value="0">选择巡线路线</option>   
										        <option value="1">1号线</option>   
										        <option value="2">2号线</option>   
										        <option value="3">3号线</option>   
										        <option value="4">4号线</option>   
									        	<option value="5">5号线</option>             
									     	</select>  
										</div>
										<button class = "btn btn-success" onclick = "getSelRoute()">确认</button>
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
						<h4 class="modal-title bk-fg-primary">添加设备</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
						<form id = "addregion" action="<%=basePath %>AddRegionServlet" method="post" class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group" hidden="hidden">>
								<label class="col-md-3 control-label text-vertical-center" for="text-input">创建者编号</label>
								<div class="col-md-9">
									<input type="text" value="0001" id="region_gener" name="gener_id" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">设备名称</label>
								<div class="col-md-9">
									<input type="text" id="region_name" name="name" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="textarea-input">设备介绍</label>
								<div class="col-md-9">
									<textarea id="region_intro" name="intro" rows="4" class="form-control" placeholder="Content.."></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="select">所在部门</label>
								<div class="col-md-9">
									<select id="region_branch" name="branch_id" class="form-control" size="1">
										<option value="1">部门1</option>
										<option value="2">部门2</option>
										<option value="3">部门3</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="select">区域种类</label>
								<div class="col-md-9">
									<select id="region_type" name="type" class="form-control" size="1">
										<option value="site">训站点</option>
										<option value="route">巡线点</option>
									</select>
								</div>
							</div>
							<div class="form-group" id="range_group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">范围（m）</label>
								<div class="col-md-9">
									<input type="text" id="region_range" name="range" class="form-control" value="0">
								</div>
							</div>
							<button type="submit" class="btn btn-success col-md-12">提交</button>
						</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
		<script src="assets/vendor/js/jquery.form.js"></script>
		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script type="text/javascript" src="assets/js/pages/device-map.js"></script>
		<script type="text/javascript" src="assets/js/pages/data-region.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>