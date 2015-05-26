<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html >
	<head>
		<meta charset="UTF-8">


		<title>Paint Service Login</title>


		<link rel="stylesheet" href="css/normalize.css">

		<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/style-login.css">




	</head>

	<body>

		<div class="logmod">
			<div class="logmod__wrapper">
				<div class="logmod__container">
					<ul class="logmod__tabs">
						<li data-tabtar="lgm-2"><a href="#">Вход</a></li>
						<li data-tabtar="lgm-1"><a href="#">Регистрация</a></li>
					</ul>
					<div class="logmod__tab-wrapper">
						<div class="logmod__tab lgm-1">
							<div class="logmod__heading">
								<span class="logmod__heading-subtitle">Введите свои персональные данные чтобы  <strong>создать аккаунт</strong></span>
							</div>
							<div class="logmod__form">
								<form accept-charset="utf-8" action="Registration" method="POST" class="simform">
									<div class="sminputs">
										<div class="input full">
											<label class="string optional" for="username">Имя пользователя *</label>
											<input class="string optional" maxlength="255" id="username" name="username" placeholder="Имя пользователя" type="text" size="50" />
										</div>
									</div>
									<div class="sminputs">
										<div class="input string optional">
											<label class="string optional" for="user-pwd">Пароль</label>
											<input class="string optional" maxlength="255" id="user-pw" name="user-pwd" placeholder="Пароль" type="password" size="50" />
										</div>
										<div class="input string optional">
											<label class="string optional" for="user-pwd-repeat">Повтор пароля *</label>
											<input class="string optional" maxlength="255" id="user-pwd-repeat" name="user-pwd-repeat" placeholder="Повтор пароля" type="password" size="50" />
										</div>
									</div>
									<div class="simform__actions">
										<input class="submit" name="commit" type="submit" value="Создать аккаунт" />
										<span class="simform__actions-sidetext">${msgR}</span>
									</div> 
								</form>
							</div> 
						</div>

						<div class="logmod__tab lgm-2">
							<div class="logmod__heading">
								<span class="logmod__heading-subtitle">Введите имя пользователя и пароль, чтобы <strong>войти</strong></span>
							</div> 
							<div class="logmod__form">
								<form accept-charset="utf-8" action="loginCheck" method="POST" class="simform">
									<div class="sminputs">
										<div class="input full">
											<label class="string optional" for="username">Имя пользователя *</label>
											<input class="string optional" maxlength="255" id="username" name="username" placeholder="Имя пользователя" type="text" size="50" />
										</div>
									</div>
									<div class="sminputs">
										<div class="input full">
											<label class="string optional" for="user-pw">Пароль *</label>
											<input class="string optional" maxlength="255" id="user-pw" name="user-pwd" placeholder="Пароль" type="password" size="50" />
											<span class="hide-password">Показать</span>
										</div>
									</div>
									<div class="simform__actions">
										<input class="submit" name="commit" type="submit" value="Войти" />
										<span class="simform__actions-sidetext">${msg}</span>
									</div> 
								</form>
							</div> 

						</div>
					</div>
				</div>
			</div>
		</div>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


		<script src="js/index.js"></script>




	</body>
</html>
