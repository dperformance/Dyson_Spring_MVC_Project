<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- loginForm.css 경로 -->
    <link href="/resources/bootstrap/signin.css" rel="stylesheet">
    <link href="/resources/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  -->
</head> 
<script>
$(function(){ 
		//$('#loginButton').on('click', function(){
			$('#mem_email').blur(function(){
			alert("test임돠");
			var data = {
				mem_email: $('#mem_email').val(),
				mem_pw: $('#mem_pw').val()
			};
			
			$.ajax({
				url : './login',
				type : 'post',
				//dataType: 'json',
				data : $('#loginForm').serialize(),
				//contentType : "application/json; charset=utf-8",
				//data: JSON.stringify(data)
				//data : {mem_email:mem_email, mem_pw:mem_pw},
				success: function(data) {
					console.log("dddddddd제발좀..");
					alert("111111환영합니다 고갱님 ^^  메인페이지로 이동합니다.");
					
					if (data == "1") { // 1 : 회원 정보 존재 
						alert("환영합니다 고갱님 ^^  메인페이지로 이동합니다." + mvo.getMem_email);
						location.href='home.jsp';
					} else { // 0 : 회원 정보 미 존재
						alert("회원정보가 존재 하지 않습니다. 다시 입력해 주세요");
						
					}
				},
				error: function() {
					alert("실패");
				}
				
			});
		}); 	
	
});	
		/*function loginCheck(){
			alert("우선 loginCheck 진입");
			var json = {
				mem_email : $("#inputemail").val(),
				mem_pw : $("#inputPassword").val()
			};
			*/
			
			/*
			$.ajax({
				url : '/login/signin',
				type : 'post',
				//data : json,
				data : $('#loginForm').serialize(),
				//data : {mem_email:"ttt", mem_pw:"ttt"},
			
				success : function(data) {
					if (data == 1) { // 1 : 회원 정보 존재 
						location.href='home.jsp';
					} else { // 0 : 회원 정보 미 존재
						alert("회원정보가 존재 하지 않습니다. 다시 입력해 주세요");
						
					}
				},
				error : function() {
					alert("실패");
				}
				
			});
		}
			*/
	
</script>

<body>
	<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>
	<div class="loginform">
		<form id="loginForm" class="form-signin" action="/login" method="post">
			<img class="mb-4" src="resources/images/bootstrap-solid.svg" alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="email" id="mem_email" class="form-control" name="mem_email" placeholder="Email address" required="" autofocus=""> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="mem_pw" class="form-control" name="mem_pw" placeholder="Password" required="">
			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button id="loginButton" class="btn btn-lg btn-primary btn-block">Sign in</button>
			<p class="mt-5 mb-3 text-light">© 2017-2019</p>
		</form>
	</div>

</body>
</html>