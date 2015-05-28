<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/pstags" prefix="ps"%>
<html>
	<head>
		<title>Pay & Spray</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
		<link rel="stylesheet" href="css/skel-noscript.css" />
		<link rel="stylesheet" href="css/style-login.css" />
		<link rel="stylesheet" href="css/style-desktop.css" />
		<link rel="stylesheet" href="css/style-wallet.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
	</head>
	<body class="no-sidebar">

		<!-- Header -->
		<ps:header>${username}</ps:header>
		<div id="header">
			<div class="container"> 

				<!-- Logo -->
				<div id="logo">
					<h1><a href="garage.html">Pay & Spray</a></h1>
					
				</div>

				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li ><a href="index.jsp">Главная</a></li>
						<li class="active"><a href="wallet.jsp">Мой кошелек</a></li>
						<% if (session.getAttribute("userid") != null && (Integer)session.getAttribute("userid") == 1){
							out.println("<li><a href=\"admin.jsp\">Администрирование</a></li>");
						} %>
					</ul>
				</nav>
			</div>
		</div>

		<!-- Main -->
		<div id="main">
			<div class="container">
				<div class="row"> 

					<!-- Content -->
					<div id="content" class="12u skel-cell-important">
						<section>
						<ps:WalletAmount/>
						</section>
						<ps:wallletHistory/>
					</div>

				</div>
			</div>
		</div>

	
	
	</body>
</html>