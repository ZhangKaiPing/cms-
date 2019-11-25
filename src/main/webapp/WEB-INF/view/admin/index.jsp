<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS后台中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<!-- 	<link rel="stylesheet" type="text/css"
	href="/resource/open-iconic/font/css/open-iconic-bootstrap.css" /> -->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>


<script type="text/javascript"
	src="/resource/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="margin-top: 2px; min-height: 50px">
		<div class="col-md-12" style="background-color: #000">
		<img alt="" src="/resource/images/logo.jpg" class="rounded-circle">
			<a class="navbar-brand mr-1" href="index">CMS系统后台</a>
			<ul>
				
					<c:choose>
						<%-- 登录显示用户菜单 --%>
						<c:when test="${sessionScope.admin != null}">
							<li class="nav-item"><a class="nav-link" href="/my/home">
									<img alt="" src="/resource/images/default.png"
									style="max-height: 1.5rem" class="rounded img-fluid">
							</a></li>
							<li class="nav-item">

								<div class="dropdown" style="padding-top: 0.1rem;">
									<a href="#" class="nav-link dropdown-toggle" role="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <c:out
											value="${admin.username}" default="cms-User" />
									</a>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a class="dropdown-item" href="/passport/logout">退出</a>
									</div>
								</div>
							</li>
						</c:when>
						<c:otherwise>
							<%-- 未登录显示登录注册链接 --%>
							<li class="nav-item"><a class="nav-link"
								href="/passport/reg">注册</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/passport/login">登录</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			
		 </div>
		</div>
		<div class="row" style="margin-top: 5px; min-height: 500px;">
			<div class="col-md-2"
				style="padding-top: 20px; background-color: #563d7c;">
				<ul class="navbar">
					<li class="navbar-brand "><a
						class="nav-link  list-group-item-danger" href="/admin/index"><span
							class="oi oi-monitor">&nbsp;后台首页</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/user/users"><span class="oi oi-star">&nbsp;用户管理</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/article/articles"><span class="oi oi-sun">&nbsp;文章管理</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/links/selects">&nbsp;友情链接</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#">&nbsp;分类管理</a></li>
					<li class="navbar-brand"><a class="nav-link " href="#">
							&nbsp;系统设置</a></li>
				</ul>


			</div>

			<div class="col-md-10 split" id="center">
				<div align="center">
					<img alt="" src="/resource/images/bg_admin.jpg"
						class="rounded-circle">
				</div>

			</div>


		</div>
	</div>
</body>
<script type="text/javascript">
   
   //文档就绪函数
	$(function(){
		
		//为左侧菜单添加点击事件
		$(".nav-link").click(function(){
			var li=$("ul li a");
			
			//先移除所有的list-group-item-info样式
			li.removeClass("list-group-item-danger");
			//为左侧菜单添加动态点击样式
			$(this).addClass("list-group-item-danger");
			//获取点击的url
			var url=$(this).attr("data");
			$("#center").load(url);
		})
	})

</script>
</html>