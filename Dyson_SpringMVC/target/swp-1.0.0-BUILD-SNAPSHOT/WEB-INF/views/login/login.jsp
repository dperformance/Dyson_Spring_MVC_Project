<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

<div id="login_container">
	<h1>Login Page</h1>
		<div id="login">
		  <form id="loginForm" method="post">
			<ul id="login_ul">
				<li>
					<span>
						<input type="email" name="mem_email" class="input_box" placeholder="Email address" autofocus="autofocus">
					</span>
				</li>
				<li>
					<span>
						<input type="password" name="mem_pw" class="input_box" placeholder="Password"> 
																			 
					</span>
				</li>
				<li>
					<label class="returnText">
						<input type="checkbox" id="useCookie" name="useCookie" checked /> Remember Me
					</label>
					<span class="returnText">
						<!-- <a id="forgot" href="resetPassword.do">Forgot your password?</a>  -->
					</span>
				</li>
				<li>
					<span>
						<button id="loginbtn">Sign In</button>
					</span>
				</li>
			</ul>
		</form>
	 </div>
		  <p class="returnText">
			<span>
				"Don't have an account? "
				<a class="a_href" href="/register">Register now</a>
			</span>
	 	 </p>
	 	 <br>
	<div id="login_url">		
		<div id="naver_url">
			<a href="${naver_url }"><img src="<c:url value="resources/images/naver_login_button_en_green.png"/>" alt="Naver Login" /></a>
		</div>
		<div id="google_url">
			<a href="${google_url }"><img src="<c:url value="resources/images/google_login_button_en.png"/>" alt="Google Login" /></a>
		</div>
	</div>
</div>
</body>
</html>