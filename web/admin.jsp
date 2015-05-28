<%@page import="java.util.List"%>
<%@page import="model.Model"%>
<%@page import="model.DB.ColorPrices"%>
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
		<link href="css/admin-forms.css" media="screen" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdn.jquerytools.org/1.2.6/full/jquery.tools.min.js"></script>
		<script type="text/javascript" src="js/jquery.uniform.min.js"></script>
		<script type="text/javascript" src="js/adminForms.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
		<link rel="stylesheet" href="css/skel-noscript.css" />
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
	</head>
	<body>

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
							<li><a href="wallet.jsp">Мой кошелек</a></li>
							<li  class="active"><a href="admin.jsp">Администрирование</a></li>
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
								<header>
									<h2 style="text-align: center;">Измение цен на краски</h2>
								</header>
								<div align="center" class="TTWForm-container">
								<%
									int[] colorPrices = Model.getColorPrices();
								%>


								<form action="changeColorPrices" class="TTWForm" method="post">


									<div id="field1-container" class="field f_100">
										<label for="field1">
											Красная краска
										</label>
										<input type="number" name="redColor" id="field1" value="<%=colorPrices[0]%>" required pattern="^[ 0-9]+$">
									</div>


									<div id="field3-container" class="field f_100">
										<label for="field3">
											Зеленая краска
										</label>
										<input type="number" name="greenColor" id="field3" value="<%=colorPrices[1]%>" required pattern="^[ 0-9]+$">
									</div>


									<div id="field2-container" class="field f_100">
										<label for="field2">
											Синяя краска
										</label>
										<input type="number" name="blueColor" id="field2" value="<%=colorPrices[2]%>" required pattern="^[ 0-9]+$">
									</div>


									<div id="field4-container" class="field f_100">
										<label for="field4">
											Черная краска
										</label>
										<input type="number" name="blackColor" id="field4"  value="<%=colorPrices[3]%>" required pattern="^[ 0-9]+$">
									</div>


									<div id="form-submit" class="field f_100 clearfix submit">
										<input type="submit" value="Применить">
									</div>
								</form>
							</div>

						</section>
					</div>

					<hr width="100%" style="margin-bottom:30px;">

					<script type="text/javascript">
						var modelPrices = new Array();
						<% int[] modelPrices = Model.getModelPrices();
							for (int i = 0; i < modelPrices.length; i++) {
						%>
						modelPrices[<%=i%>] = <%=modelPrices[i]%>
						<%
									}
						%>
							
						function setPrice(price){
							document.getElementById("field6").value = modelPrices[price];
						}
					</script>

					<div id="content" class="12u skel-cell-important">
						<section>
							<header>
								<h2 style="text-align: center;">Измение цен на автомобили</h2>
							</header>
							<div align="center" class="TTWForm-container">


								<form action="changeModelPrice" class="TTWForm" method="post">


									<div id="field5-container" class="field f_100">
										<label for="field5">
											Модель
										</label>
										<select name="model" id="field5" required="required" onchange="setPrice(this.selectedIndex)">
											<option id="field5-1" value="Ferrari California">
												Ferrari California
											</option>
											<option id="field5-2" value="Mitsubishi Lancer">
												Mitsubishi Lamcer
											</option>
											<option id="field5-3" value="Audi S3">
												Audi S3
											</option>
											<option name="field5" id="field5-4" value="Mazda 3">
												Mazda 3
											</option>
											<option name="model" id="model-5" value="Chevrolet Camaro">
												Chevrolet Camaro
											</option>
											<option name="model" id="model-6" value="Nissan 350Z">
												Nissan 350Z
											</option>
											<option name="model" id="model-7" value="Mercedes SLR500">
												Mercedes SLR500
											</option>
											<option name="model" id="model-8" value="Aston Martin V8">
												Aston Martin V8
											</option>
										</select>
									</div>


									<div id="field6-container" class="field f_100">
										<label for="field6">
											Цена
										</label>
										<input type="number" value="<%=modelPrices[0]%>" name="price" id="field6" required="required" min="0">
									</div>


									<div id="form-submit" class="field f_100 clearfix submit">
										<input type="submit" value="Применить">
									</div>
								</form>
							</div>

						</section>

					</div>

					<hr width="100%" style="margin-bottom:30px;">

					<div id="content" class="12u skel-cell-important">
						<section>
							<header>
								<h2 style="text-align: center;">Зачисление на счет</h2>
							</header>
							<div align="center" class="TTWForm-container">


								<form action="moneyTransfer" class="TTWForm" method="post">


									<div id="field7-container" class="field f_100">
										<label for="field7">
											Имя пользователя
										</label>
										<input type="text" name="username" id="field7" required="required">
									</div>


									<div id="field8-container" class="field f_100">
										<label for="field8">
											Зачисляемая сумма
										</label>
										<input type="number" name="summ" id="field8" required="required" min="0">
										<span style="font-size: 1.5em;">${tmsg}</span>
									</div>


									<div id="form-submit" class="field f_100 clearfix submit">
										<input type="submit" value="Применить">
									</div>
								</form>
							</div>
					</div>

					</body>
					</html>