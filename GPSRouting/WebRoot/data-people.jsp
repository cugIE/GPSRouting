<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>燃气巡检系统</title>

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Import google fonts -->


<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico"
	type="image/x-icon" />
<link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
<link rel="apple-touch-icon" sizes="57x57"
	href="assets/ico/apple-touch-icon-57x57.png" />
<link rel="apple-touch-icon" sizes="72x72"
	href="assets/ico/apple-touch-icon-72x72.png" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/ico/apple-touch-icon-76x76.png" />
<link rel="apple-touch-icon" sizes="114x114"
	href="assets/ico/apple-touch-icon-114x114.png" />
<link rel="apple-touch-icon" sizes="120x120"
	href="assets/ico/apple-touch-icon-120x120.png" />
<link rel="apple-touch-icon" sizes="144x144"
	href="assets/ico/apple-touch-icon-144x144.png" />
<link rel="apple-touch-icon" sizes="152x152"
	href="assets/ico/apple-touch-icon-152x152.png" />

<!-- start: CSS file-->

<!-- Vendor CSS-->
<link href="<%=path%>/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=path%>/assets/vendor/skycons/css/skycons.css"
	rel="stylesheet" />
<link
	href="<%=path%>/assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />

<!-- Plugins CSS-->
<link href="<%=path%>/assets/plugins/bootkit/css/bootkit.css"
	rel="stylesheet" />
<link href="<%=path%>/assets/plugins/fullcalendar/css/fullcalendar.css"
	rel="stylesheet" />
<link
	href="<%=path%>/assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css"
	rel="stylesheet" />

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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body>

	<!-- Start: Header -->
	<!-- Start: Header -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- End: Header -->
	<div class="copyrights">Collect from CUG</div>
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
								<h6>
									<i class="fa fa-table red"></i><span class="break"></span>人员名单
								</h6>
								<div class="panel-actions">
									<a href="table.html#" class="btn-setting"><i
										class="fa fa-rotate-right"></i></a> <a data-toggle="modal"
										data-target="#myModal" class="btn-plus"><i
										class="fa fa-plus"></i></a> <a href="table.html#"
										class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<%
								People people = (People) request.getAttribute("people");
								if (people != null) {
							%>
							用户名：<%=people.getUsername()%><br /> 姓名：<%=people.getName()%><br />
							组类型：<%=people.getTeamId()%><br /> 所属部门：<%=people.getBranchId()%><br />
							备注信息：<%=people.getPeopRemark()%><br />
							<%
								} else {
							%>
							<jsp:useBean id="service1" class="com.service.PeopleService"
								scope="session">
							</jsp:useBean>
							<%
								List<People> projectlist = service1.fill();
									Iterator<People> iterproject = projectlist.iterator();
							%>
							<jsp:useBean id="service5" class="com.service.BranchService"
								scope="session">
							</jsp:useBean>
							<%
								List<Branch> projectlist2 = service5.fill();
									Iterator<Branch> iterproject2 = projectlist2.iterator();
							%>

							<div class="panel-body">
								<div class="form-group">
									<div class="col-md-3">
										<select id="select" name="select" class="form-control" size="1">
											<%
												while (iterproject2.hasNext()) {
														Branch branch1 = iterproject2.next();
											%>
											<option value=<%=branch1.getId() %>><%=branch1.getBranchName() %></option>			
									
										<%
												}
										%>
										</select>
									</div>
									<button class = "btn btn-success">确认</button>
								</div>
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered bootstrap-datatable datatable">
										<thead>
											<tr>
												<th>用户名</th>
												<th>员工名</th>
												<th>职位</th>
												<th>所属部门</th>
												<th>备注信息</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<%
												while (iterproject.hasNext()) {
														People people1 = iterproject.next();
											%>
											<tr>
												<td><%=people1.getUsername()%></td>
												<td><%=people1.getName()%></td>
												<td><%=people1.getTeamId()%></td>
												<td><%=people1.getBranchId()%></td>
												<td><%=people1.getPeopRemark()%></td>
												<td><a class="btn btn-success"
													href="ManagePeopleServlet?id=<%=people1.getId()%>&action=list2">
														<i class="fa fa-search-plus "></i>
												</a> <a class="btn btn-info"
													href="ManagePeopleServlet?id=<%=people1.getId()%>&action=update">
														<i class="fa fa-edit "></i>
												</a> <a class="btn btn-danger" 
													href="ManagePeopleServlet?id=<%=people1.getId()%>&action=delete3"> <i
														class="fa fa-trash-o "></i>
												</a></td>
											</tr>
											<%
												}
												}
											%>

										</tbody>
									</table>
								</div>
							</div>

						</div>

					</div>
				</div>
				<!-- End Main Page -->

				<!-- Usage -->


			</div>
		</div>
	</div>
	<!--/container-->


	<!-- Modal Dialog -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title bk-fg-primary">添加人员信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal "
						action="ManagePeopleServlet?action=add" method="post">
						<div class="form-group">
							<label class=" col-md-4 control-label">用户名</label>
							<div class="col-md-5">
								<input type="text" id="username" name="username"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class=" col-md-4 control-label" for="password-input">密码</label>
							<div class="col-md-5">
								<input type="password" id="password" name="password"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">姓名</label>
							<div class="col-md-5">
								<input type="text" id="name" name="name" class="form-control"
									placeholder="姓名">
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label">性别</label>
							<div class="col-md-5">
								<div class="radio-custom radio-inline">
									<input type="radio" id="inline-radio1" name="sex"
										value="option1"> <label for="inline-radio1"> 男</label>
								</div>
								<div class="radio-custom radio-inline">
									<input type="radio" id="inline-radio2" name="sex"
										value="option2"> <label for="inline-radio2"> 女</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">编码</label>
							<div class="col-md-5">
								<input type="text" id="peoplecode" name="peoplecode"
									class="form-control" placeholder="编码格式：AABBCC">
							</div>
						</div>
						<jsp:useBean id="service6" class="com.service.BranchService"
								scope="session">
							</jsp:useBean>
							<%
								List<Branch> projectlist3 = service6.fill();
									Iterator<Branch> iterproject3 = projectlist3.iterator();
							%>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">部门</label>
							<div class="col-md-5">
								<select name="branchid" id="branchid" class="form-control">
									<%
												while (iterproject3.hasNext()) {
														Branch branch2 = iterproject3.next();
											%>
											<option value=<%=branch2.getId() %>><%=branch2.getBranchName() %></option>			
									
										<%
												}
										%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">组类型</label>
							<div class="col-md-5">
								<select name="teamid" id="teamid" class="form-control">
									<option value="0001">管理员</option>
									<option value="0002">工程师</option>
									<option value="0003">值班干部</option>
									<option value="0004">值班员</option>
									<option value="0005">巡检员</option>
								</select>
								<!-- <input
									type="text" name="teamtext" class="form-control" value="组说明"
									disabled="true"> -->
							</div>
						</div>

						<div class="form-group">
							<label class="  col-md-4 control-label" for="text-input">备注</label>
							<div class="col-md-5">
								<input type="text" id="peopRemark" name="peopRemark"
									class="form-control" placeholder="备注信息">
							</div>
						</div>

						<div class="form-group">
							<label class="  col-md-4 control-label" for="file-input">更换照片</label>
							<div class="col-md-5">
								<input type="file" id="file-input" name="file-input">
							</div>
						</div>
						<div class="form-group">
							<input type="submit" class="bk-margin-5 btn btn-primary"
								value="提交">
						</div>
						<br>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Modal Dialog -->

	<div class="clearfix"></div>


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="<%=path%>/assets/vendor/js/jquery.min.js"></script>
	<script src="<%=path%>/assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="<%=path%>/assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="<%=path%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path%>/assets/vendor/skycons/js/skycons.js"></script>

	<!-- Plugins JS-->
	<script
		src="<%=path%>/assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
	<script src="<%=path%>/assets/plugins/moment/js/moment.min.js"></script>
	<script
		src="<%=path%>/assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>

	<!-- Theme JS -->
	<script src="<%=path%>/assets/js/jquery.mmenu.min.js"></script>
	<script src="<%=path%>/assets/js/core.min.js"></script>

	<!-- Pages JS -->
	<script src="<%=path%>/assets/js/pages/table.js"></script>
	<script type="text/javascript"
		src="<%=path%>/assets/js/pages/device-map.js"></script>

	<!-- end: JavaScript-->
</body>

</html>