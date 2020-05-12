<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

<div id="register_container">
		<h1>Create Account</h1>
		<hr>
		<form id="regiForm" method="post">
			<div>
				<span>
					<img id="oauthImg" alt="userDefaultIcon" src="/resources/images/userDefaultIcon1.png">
				</span>
			</div>
			<div>
				<span class="spanText">
					<label>Nickname *</label>
				</span>
				<br>
				<input type="text" id="mem_name" name="mem_name" class="register_box" placeholder="Name" autofocus="autofocus" required>
				<div class="regiCheck" id="getMemberNick"></div>
			</div>
			<div>
				<span class="spanText">
					<label>Email *</label>
				</span>
				<br>
				<input type="text" id="mem_email" name="mem_email" class="register_box" placeholder="email address" required>
				<div class="regiCheck" id="getMemberEmail"></div>
			</div>
			<div>
				<span class="spanText">
					<label>Password *</label>
				</span>
				<br>
				<input type="password" id="mem_pw" name="mem_pw" class="register_box" placeholder="At least 6 characters" required>
				<div class="regiCheck" id="password_check"></div>
			</div>
			<br>
			<hr>
			<div class="regiDiv">			
				<input type="button" id="submit" value="Create Account">
				<input type="hidden" name="mem_images" value="/resources/images/userDefaultIcon1.png">
			</div>
		</form>
	</div>
</body>
</html>