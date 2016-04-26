<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="com.bean.*" 
import="com.util.*" 
import = "com.bean.Branch"
import = "com.service.*"
import="java.util.*" 
import="java.text.SimpleDateFormat"
import="java.sql.Timestamp"
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
		<!-- Plugins CSS-->
		<link href="<%=path%>/assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
		<link href="<%=path%>/assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />
		<link href="<%=path%>/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.css" rel="stylesheet" />
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
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String branch_id = request.getParameter("branch_id");
		String gener_id = request.getParameter("gener_id");

		HttpSession tempSession = request.getSession();
		int brch_id = (int) session.getAttribute("SesBranchId");
		int team_id = (int) session.getAttribute("SesTeamId");
		BranchService bs = new BranchService();
		Branch brch = bs.fill(brch_id + "");
		String branchType = brch.getBranchType();
		System.out.println(branchType);
		if (branch_id == null) {
			branch_id = "";
		}
		if (gener_id == null) {
			gener_id = "";
		}
		List<Record> rcds = null;
		if (start == null || end == null) {
			//Timestamp ts = new Timestamp(System.currentTimeMillis());

			String result[] = DateHelper.getInitDate();
			start = result[0];
			end = result[1];

		}
		if (branchType.equals("管理")) {
			if (!branch_id.equals("")) {
				rcds = Record.getAllRecord(Integer.parseInt(branch_id),
						start, end + " 23:59:59");
			} else if (!gener_id.equals("")) {
				rcds = Record.getAllRecord(gener_id, start, end + " 23:59:59");
			} else {
				rcds = Record.getAllRecord(start, end + " 23:59:59");
			}
		} else {
			if (!gener_id.equals("")) {
				rcds = Record.getAllRecord(gener_id, brch_id, start, end + " 23:59:59");
			} else {
				rcds = Record.getAllRecord(brch_id, start, end + " 23:59:59");
			}
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
						<form class="form-horizontal form-bordered" action="task-evaluation.jsp" mehod="get">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-label">提交时间范围</label>
								<div class="col-md-5">
									<div class="input-daterange input-group" data-plugin-datepicker>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
										<input type="text" class="form-control" name="start" value="<%=start %>" />
										<span class="input-group-addon">to</span>
										<input type="text" class="form-control" name="end" value="<%=end %>" />
									</div>
								</div>
								<div class="col-md-3" id="record_gener_id" hidden="hidden">
									<label class="col-md-5 control-label" for="部门">巡检员</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="gener_id" value="" />
									</div>
								</div>
								<% 
								if(branchType.equals("管理")){
								List<Branch> brchs = bs.fill();
								%>
								<div class="col-md-3" id="record_branch_id" hidden="hidden">
									<label class="col-md-4 control-label" for="部门">部门</label>
									<div class="col-md-8">
										<select name="branch_id" class="form-control" size="1">
										<option value=""></option>
										<%for (int i = 0; i<brchs.size();i++){ %>
										<option value="<%=brchs.get(i).getId() %>"><%=brchs.get(i).getBranchName() %></option>
										<%} %>
										</select>
									</div>
								</div>
								<%} %>
								<div class="btn-group col-md-3" id ="record_choose_button" >
								  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    选择搜索条件 <span class="caret"></span>
								  </button>
								  <ul class="dropdown-menu">
								    <li><a href="#" onclick="$('#record_choose_button').hide(); $('#record_gener_id').fadeIn();  ">巡检员</a></li>
								    
								    <%if(branchType.equals("管理")){ %>
								    <li role="separator" class="divider"></li>
								    <li><a href="#" onclick="$('#record_choose_button').hide(); $('#record_branch_id').fadeIn();  ">部门</a></li>
								  	<%} %>
								  </ul>
								</div>
								<button type="submit"  class="btn btn-success ">搜索</button>
								
							</div>
							
						</form>
						
						<div class="col-md-10 col-md-offset-1">
							<div class="table-responsive table-data-show">
								<table class="table table-hover record-table">
									  <thead>
										  <tr>
											  <th>编号</th>
											  <th>巡检区域</th>
											  <th>班次/时间点</th>
											  <th>所在部门</th>
											  <th>区域类型</th>
											  <th>提交时间</th>
											  <th>巡检员</th> 
											  <th>审核状态</th>                                      
										  </tr>
									  </thead>   
									  <tbody>
									  <%
									  for(int i = 0; i<rcds.size();i++){
									  Record rcd = rcds.get(i);
									  String statusColor = "success";
									  String status = "已检查";
									  if(rcd.getStatus().equals("0")){
									  statusColor = "warning";
									  status = "等待中";
									  }
									   %>
										<tr>
											<td><%=rcd.getId() %></td>
											<td><%=rcd.getRegion() %></td>
											<td><%=rcd.getPeriod_shift()+"/"+rcd.getPeriod_time() %></td>
											<td><%=rcd.getBranch() %></td>
											<%
											String Region_type = "巡线点";
											if(rcd.getRegion().equals("site")) {
											Region_type = "巡站点";} %>
											<td><%=Region_type %></td>
											<%if(rcd.getSubmit()!=null) {%>
											<td><%=rcd.getStart().toString() %></td>
											<%}else{ %><td></td><%} %>
											<td><%=rcd.getGener() %></td>
											<td>
												<span class="label label-<%=statusColor %>"><%=status %></span>
											</td>                                      
										</tr>
										<%
										} %>
																	                                  
									  </tbody>
								</table>
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
		<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
		<script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>			
		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="assets/js/pages/task-evaluation.js"></script>
		<script src="assets/js/pages/task-evaluation-datepicker.js"></script>

		<!-- end: JavaScript-->
		
	</body>
	
</html>
