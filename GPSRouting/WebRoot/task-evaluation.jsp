<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="com.bean.*" 

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
		<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.css" rel="stylesheet" />
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
	if (start==null||end==null){
	//Timestamp ts = new Timestamp(System.currentTimeMillis());
	
		Date endDate = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
		Date startDate = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(endDate);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前3月
		startDate = calendar.getTime(); //得到前3月的时间
		
	
		end = dateFormat.format(endDate); 
		start = dateFormat.format(startDate); 
		System.out.println(end+";"+start); 
		
	}
	List<Record> rcds = Record.getAllRecord(start, end);
	
	 
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
						<form class="form-horizontal form-bordered" action="task-evaluation.jsp" mehod="get">
							<div class="form-group">
								<label class="col-md-3 control-label">提交时间范围</label>
								<div class="col-md-6">
									<div class="input-daterange input-group" data-plugin-datepicker>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
										<input type="text" class="form-control" name="start" value="<%=start %>" />
										<span class="input-group-addon">to</span>
										<input type="text" class="form-control" name="end" value="<%=end %>" />
									</div>
								</div>
								<button type="submit" class = "col-md-1 btn btn-success">确认</button>
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
											  <th>开始时间</th>
											  <th>结束时间</th>
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
											<td><%=rcd.getPeriod_shift()+"/"+rcd.getPeriod_time()%></td>
											<td><%=rcd.getStart().toString() %></td>
											<td><%=rcd.getEnd().toString() %></td>
											<td><%=rcd.getSubmit().toString() %></td>
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
