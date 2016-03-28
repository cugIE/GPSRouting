<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
							<i class="fa fa-circle text-success"></i> <small>管理员</small>
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
							<li><a href="data-branch.jsp"><span class="text">部门管理</span></a></li>
							<li><a href="data-device.jsp"><span class="text"> 管道区域管理</span></a></li>
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
							<li><a href="msg-inbox.jsp"><span class="text"> 消息</span></a></li>										</ul>
					</li>
				</ul>
			</nav>
		</div>
		<!-- End Sidebar Menu-->
	</div>
	<!-- Sidebar Footer-->
<!-- 	<div class="sidebar-footer">	
		<div class="copyright text-center">
			<small> <i class="fa fa-coffee"></i> from <a href="http://www.cssmoban.com/" title="CUG" target="_blank">CUG</a></small>
		</div>					
	</div> -->
	<!-- End Sidebar Footer-->
</div>