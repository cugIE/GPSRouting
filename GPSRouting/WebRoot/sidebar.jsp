<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar">
	<div class="sidebar-collapse">
		<!-- Sidebar Header Logo-->

		<!-- Sidebar Menu-->
		<div class="sidebar-menu">						
			<nav id="menu" class="nav-main" role="navigation">
				<ul class="nav nav-sidebar">
					<div class="panel-body text-center">								
						<div class="bk-avatar">
							<img src="assets/img/avatar.jpg" class="img-circle bk-img-60" alt="" />
						</div>
						<div class="bk-padding-top-10">
							<i class="fa fa-circle text-success"></i> <small><%=request.getSession().getAttribute("SesUser")%></small>
						</div>
					</div>
					<div class="divider2"></div>
					<li class="active">
						<a href="index.jsp">
							<i class="fa fa-laptop" aria-hidden="true"></i><span>主界面</span>
						</a>
					</li>
					
					<li class="nav-parent">
						<a>
							<i class="fa fa-list-alt" aria-hidden="true" id = "data-list"></i><span>数据管理</span>
						</a>
						<ul class="nav nav-children">
							<li><a href="data-people.jsp"><span class="text"> 人员管理</span></a></li>
							<li><a href="data-newbranch.jsp"><span class="text">部门管理</span></a></li>
							<li><a href="data-region.jsp"><span class="text"> 管道区域管理</span></a></li>
							<li><a href="data-position.jsp"><span class="text"> 位置管理</span></a></li>					
						</ul>
					</li>
					<li class="nav-parent">
						<a>
							<i class="fa fa-copy" aria-hidden="true"></i><span>任务管理</span>
						</a>
						<ul class="nav nav-children">
							<li><a href="task-search.jsp"><span class="text">巡检任务</span></a></li>
							<li><a href="task-evaluation.jsp"><span class="text"> 日志报告及考核</span></a></li>
							<li><a href="task-disability.jsp"><span class="text">故障维修管理</span></a></li>
							
						</ul>
					</li>

					<li class="nav-parent">
						<a>
							<i class="fa fa-envelope" aria-hidden="true"></i><span>信息管理</span>
						</a>
						<ul class="nav nav-children">
							<li><a href="msg-board.jsp"><span class="text"> 公告 </span></a></li>
							<li><a href="send-msg.jsp"><span class="text"> 通知</span></a></li>	
							<li><a href=""><span class="test">故障信息</span></a>
						</ul>
					</li>
					
					<li class="nav-parent">
						<a>
							<i class="fa fa-certificate" aria-hidden="true"></i><span>系统管理</span>
						</a>
						<ul class="nav nav-children">
							<li><a href="data-log.jsp"><span class="text"> 系统日志管理 </span></a></li>
							<li><a href="ManageSystemServlet?id=0001&action=updatesite"><span class="text"> 网站参数修改</span></a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
		<!-- End Sidebar Menu-->
	</div>
	<!-- Sidebar Footer-->

 	<div class="sidebar-footer">	
		<div class="copyright text-center">
			<small> <i class="fa fa-coffee"></i> from <a href="" title="CUG" target="_blank">CUG</a></small>
		</div>					
	</div> 
	<!-- End Sidebar Footer-->
</div>
