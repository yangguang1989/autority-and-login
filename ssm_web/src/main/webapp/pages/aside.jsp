<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--引入security标签库--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username" />
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<%--其他角色访问--%>
			<security:authorize access="hasAnyRole( 'ROLE_USER_QUERY','ROLE_USER_CREATE','ROLE_USER_UPDATE','ROLE_USER_DELETE')">
			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>个人中心</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/user/findOne"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>
				</ul></li>
			</security:authorize>

			<%--只有admin角色才可以 访问--%>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
				</span>


				</a>
					<ul class="treeview-menu">

						<li id="system-setting"><a
								href="${pageContext.request.contextPath}/user/findAll"> <i
								class="fa fa-circle-o"></i> 用户管理
						</a></li>
						<li id="system-setting"><a
								href="${pageContext.request.contextPath}/role/findAll"> <i
								class="fa fa-circle-o"></i> 角色管理
						</a></li>
						<li id="system-setting"><a
								href="${pageContext.request.contextPath}/permission/findAll">
							<i class="fa fa-circle-o"></i> 权限管理
						</a></li>
						<li id="system-setting"><a
								href="${pageContext.request.contextPath}/pages/syslog-list.jsp"> <i
								class="fa fa-circle-o"></i> 访问日志
						</a></li>
					</ul></li>
			</security:authorize>
			<%--user和admin都可以访问--%>
			<%--<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">--%>
		<%--	<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/product/findAll">
							<i class="fa fa-circle-o"></i> 产品管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/orders/findAll">
							<i class="fa fa-circle-o"></i> 订单管理
					</a></li>

				</ul></li>--%>
			<%--</security:authorize>--%>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>