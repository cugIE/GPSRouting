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
																<div class="text-left bk-padding-left-10 col-md-10">
																	<h4 class="bk-margin-off">主要信息</h4>
																</div>
																<div class="col-md-2">
																	<a class="btn btn-default " id = "region-edit-button" data-toggle="modal" data-target="#region-edit-modal" href="#">
																		<i class="fa fa-edit "></i>                                            
																	</a>            
																</div>
																、                                        
															</div>
														</div> 
														
													</div>																	
												</div>
											</div>
											<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
												<div class="row">
													<div class=" col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-asterisk bk-padding-right-10"></i>区域编号：
														<i class="bk-padding-right-15" id="region-id"><%=rg.getId() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-database bk-padding-right-10"></i>区域名称：
														<i class="bk-padding-right-15" id="region-name"><%=rg.getName() %></i>
													</div>
													<div class="col-md-4  bk-bg-white bk-padding-top-10" >
														<i class="fa fa-group bk-padding-right-10"></i>所属部门：
														<i class="bk-padding-right-15"><%=rg.getBranch() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-user bk-padding-right-10"></i>添加者：
														<i class="bk-padding-right-15" ><%=rg.getGener() %></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-archive bk-padding-right-10" id="region-type" value="<%=rg.getType() %>")></i>类型：
														<i class="bk-padding-right-15">
														<%
															if(rg.getType().equals("site")){
																out.print("巡站点");
															} 
															else if(rg.getType().equals("route") ){
																out.print("巡路点");
															}else{
																out.print("临时");
															}
														%></i>
													</div>
													<div class="col-md-4 bk-bg-white bk-padding-top-10">
														<i class="fa fa-circle-o bk-padding-right-10"></i>范围：
														<i class="bk-padding-right-15" id="region-range"><%=rg.getRange() %></i>
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
														<p class = "bk-padding-left-20 bk-padding-right-20" id = "region-intro"><%=rg.getIntro() %></p>
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
																	<h4 class="bk-margin-off">内容列表</h4>
																</div>                            
															</div>
														</div> 
													</div>
													<div class="col-md-9 col-md-offset-1">
														<table  class = "table table-bordered">
															<thead>
															<tr>
														      <th>内容标题</th>
														      <th>问题可选择答案</th>
														      <th>正常答案</th>
														    </tr>
															
															</thead>
															<tbody id="questions-table">
																<% 
																if (qsts.size()!=0){
																	for(int i = 0; i < qsts.size(); i++){
																%>
																<tr value= "<%=qsts.get(i).getId() %>">
															      <td><%=qsts.get(i).getTitle() %></td>
															      <td><%=qsts.get(i).getPossibleAsw() %></td>
															      <td><%=qsts.get(i).getNormalAsw() %>
															      	<a class = "search-regions-actions question-edit pull-right bk-margin-left-10" data-toggle="modal" data-target="#myModal" href = "#" id="<%=qsts.get(i).getId() %>"><i class="fa fa-pencil"></i></a>
															      	<a class = "search-regions-actions-delete pull-right question-delete" value="<%=qsts.get(i).getId() %>" href = "#"><i class="fa fa-trash-o"></i></a>
															      </td>
															    </tr>
															    <%}} %>
															</tbody>
															
															
														</table>
													</div>
													<div class="col-md-9 col-md-offset-1">
														<div class="panel panel-accordion">
															<div class="panel-heading bk-bg-success text-center">
																<h4 class="panel-title">
																	<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionPrimary" href="#collapsePrimaryTwo">
																		添加新问题
																	</a>
																</h4>
															</div>
															<div id="collapsePrimaryTwo" class="accordion-body collapse">
																<div class="panel-body">
																	<form action="AddQuestionServlet" id="addquestion" method="post" class="form-inline">
																		<div class="form-group">
																			<div class = "col-md-4">
																			<input type="text" id="question-title" name="title" class="form-control " placeholder="问题内容">
																			</div>
																			<div class = "col-md-4">
																			<input type="text" id="question-possasws" name="possasws" class="form-control " placeholder="问题可选答案">
																			</div>
																			<div class = "col-md-2">
																			<input type="text" id="question-normalasws" name="normalasws" class="form-control " placeholder="正常答案">
																			</div>
																			
																			<button type="submit" class = "btn btn-default pull-right" ><i class = "fa fa-plus "></i></button>
																			<span class="help-block col-md-4 col-md-offset-4">每一个答案用英文";"隔开</span>
																			<span class="help-block col-md-4">选择上可能的答案中的一个</span>
																		</div>
																		<div class = "col-md-6"  hidden="hidden">
																			<input type="text" id="gener_id" name="gener_id" value="0002" class="form-control ">
																			</div>
																			<div class = "col-md-6"  hidden="hidden">
																			<input type="text" id="region_id" name="region_id" value="<%=region_id %>" class="form-control ">
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
						</div>
					</div>   
				</div>
				<!-- End Main Page -->	
		
				<!-- Usage -->
				
			
			</div>
		</div><!--/container-->
		
		<div class="modal fade" id="region-edit-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">修改区域</h4>
					</div>
					<div class="modal-body">
						<div class = "row">
						<form action="ChangeRegionServlet" method="post" id="changeregion"  class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group">
								<label class="col-md-3 control-label">编号</label>
								<div class="col-md-9">
									<p class="form-control-static" id="region-edit-static">0000</p>
								</div>
							</div>
							<div class="form-group" hidden = "hidden">
								<div class="col-md-9">
									<input type="text" id="region-edit-id" name="id" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">区域名称</label>
								<div class="col-md-9">
									<input type="text" id="region-edit-name" name="name" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label" for="textarea-input">区域介绍</label>
								<div class="col-md-9">
									<textarea id="region-edit-intro" name="intro" rows="4" class="form-control" placeholder="输入内容"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="select">区域种类</label>
								<div class="col-md-9">
									<select id="region-edit-type" name="type" class="form-control" size="1">
										<option value="site">训站点</option>
										<option value="route">巡线点</option>
									</select>
								</div>
							</div>
							<div class="form-group" id="region-edit-range-group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">范围（m）</label>
								<div class="col-md-9">
									<input type="text" id="region-edit-range" name="range" class="form-control" value="0">
								</div>
							</div>
							<button type="submit" class="btn btn-success col-md-12">保存</button>
						</form>
						
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div><!-- End Modal Dialog -->		
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
						<form action="ChangeQuestionServlet" id="changequestion" method="post" class="form-horizontal col-md-8 col-md-offset-2">
							<div class="form-group" hidden="hidden">
								<div class="col-md-9">
									<input type="text" id="question-edit-id" name="question_id" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-vertical-center" for="text-input">问题内容</label>
								<div class="col-md-9">
									<input type="text" id="question-edit-title" name="title" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">可选答案</label>
								<div class="col-md-9">
									<input type="text" id="question-edit-possasws" name="possasws" class="form-control">
									<span class="help-block">每一个答案用英文";"隔开,若没有请写上"无"</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="text-input">正常答案</label>
								<div class="col-md-9">
									<input type="text" id="question-edit-normalasws" name="normalasws" class="form-control" placeholder="否">
									<span class="help-block">在这里填上设备正常时应该有的答案,若没有请写上"#"</span>
								</div>
							</div>
							<button type="submit" class="btn btn-success col-md-12">保存设置</button>
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
		<script src="<%=path%>/assets/vendor/js/jquery.min.js"></script>
		<script src="<%=path%>/assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="<%=path%>/assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="<%=path%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=path%>/assets/vendor/skycons/js/skycons.js"></script>	
		
		<!-- Plugins JS-->
		<script src="<%=path%>/assets/vendor/js/jquery.form.js"></script>
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