<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><sitemesh:write property='title' /></title>
<link rel="stylesheet" href="<spring:theme code="theme.default.css"/>/style.css?time=@time_param@" />
<script src="<spring:theme code="theme.default.js"/>/libs/jquery-1.9.1.js?time=@time_param@"></script>
<script src="<spring:theme code="theme.default.js"/>/menu.active.js?time=@time_param@"></script>
<script src="<spring:theme code="theme.default.js"/>/application.js?time=@time_param@"></script>
<sitemesh:write property='head' />
</head>
<body>
	<div id="wrapper">
		<header class="header">
			<div class="grid-row">
				<a class="logo">
					<div class="title">Artificial Intelligent</div>
					<div class="sub-title">Remove and recover text accent</div>
				</a>
				<nav class="main-nav">
					<ul>
						<li><a rel="home" href="home">Home</a></li>
						<li><a rel="training" href="training">Training</a></li>
						<li><a rel="recover-accent" href="recover-accent">Recover accent</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<section class="main">
			<sitemesh:write property='body' />
		</section>

	</div>
	<footer class="footer"> </footer>
</body>
</html>