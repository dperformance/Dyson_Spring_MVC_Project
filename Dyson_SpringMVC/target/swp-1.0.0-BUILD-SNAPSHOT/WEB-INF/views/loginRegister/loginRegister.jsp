<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

	<div class="align">
		<img class="userLoginlogo" src="/resources/loginRegister/img/logo.svg">
		<div class="card">
			<div class="head">
				<div></div>
				<a id="login" class="selected" href="#login">Login</a>
				<a id="register" href="#register">Register</a>
				<div></div>
			</div>
			<div class="tabs">
				<form id="loginForm" method="post">
					<div class="inputs">
						<div class="input">
							<input placeholder="Email address" type="text" name="mem_email" required>
							<img src="/resources/loginRegister/img/user.svg">
						</div>
						<div class="input">
							<input placeholder="Password" type="password" name="mem_pw" required>
							<img src="/resources/loginRegister/img/pass.svg">
						</div>
						<label class="checkbox">
							<input type="checkbox">
							<span>Remember me</span>
						</label>
					</div>
					<button id="loginbtn">Login</button>
				</form>
				<form id="regiForm" method="post">
					<div class="inputs">
						<div class="input">
							<input placeholder="Email address" type="text" id="mem_email" name="mem_email" required>
							<img src="/resources/loginRegister/img/mail.svg">
						</div>
							<div id="getMemberEmail"></div>
						<div class="input">
							<input placeholder="NickName" type="text" id="mem_name" name="mem_name" required>
							<img src="/resources/loginRegister/img/user.svg">
						</div>
							<div id="getMemberNick"></div>
						<div class="input">
							<input placeholder="Password" type="password" id="mem_pw" name="mem_pw" required>
							<img src="/resources/loginRegister/img/pass.svg">
						</div>
							<div id="Password_check"></div>
					</div>
					<button id="submit">Create Account</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
