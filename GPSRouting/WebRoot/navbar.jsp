<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="navbar" role="navigation">
	<div class="container-fluid container-nav">
		<!-- Navbar Action -->
		<!-- Navbar Action -->
<!-- 		<ul class="nav navbar-nav navbar-actions navbar-left">
			<li class="visible-md visible-lg"><a href="index.html#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
			<li class="visible-xs visible-sm"><a href="index.html#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
		</ul> -->
		<!-- Navbar Left -->
		
		<!-- Navbar Right -->
		<div class="navbar-right">
			<ul class="notifications hidden-sm hidden-xs">
				<li>
					<a href="msg-inbox.html" class="notification-icon" >
						<i class="fa fa-envelope"></i>
						<span class="badge">5</span>
					</a>
					
				</li>

				<li>
					<a href="msg-inbox.html" class="notification-icon">
						<i class="fa fa-bell"></i>
						<span class="badge">3</span>
					</a>
					
				</li>
			</ul>
			<div class="userbox">
				<a href="people-profile.html" class="dropdown-toggle" data-toggle="dropdown">
					<div class="profile-info">
						<span class="name"><%=request.getSession().getAttribute("username")%></span>
						<span class="role">管理员</span>
					</div>			
					<i class="fa custom-caret"></i>
				</a>
				<div class="dropdown-menu">
					<ul class="list-unstyled">
						<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">						
							<div class="progress progress-xs  progress-striped active">
								<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
									60%
								</div>
							</div>							
						</li>	
						<li>
							<a href="people-profile.html"><i class="fa fa-user"></i> 详细资料</a>
						</li>
						<li>
							<a href="data-people-form.html"><i class="fa fa-wrench"></i> 编辑</a>
						</li>								
						<li>
							<a id="logoutbutton"><i class="fa fa-power-off"></i> 登出</a>
						</li>
					</ul>
				</div>						
			</div>
			<!-- End Userbox -->
		</div>
		<!-- End Navbar Right -->
	</div>		
</div>