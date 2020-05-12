<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dyson SpringMVC</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
	<!-- navbar css, js 경로 선언-->
	<link href="/resources/header/css/navbar.css" rel="stylesheet" type="text/css"/>
	<script src="/resources/header/js/navbar.js"></script>
	
	<!-- main css 선언 -->
	<link href="/resources/main/css/main.css" rel="stylesheet" type="text/css"/>
	
	<!-- login css, js 경로 선언 -->
	<link href="/resources/login/css/login.css" rel="stylesheet" type="text/css" />
	<script src="/resources/login/js/login.js"></script>
	
	<!-- register css, js 경로 선언 -->
	<link href="/resources/register/css/register.css" rel="stylesheet" type="text/css" />
	<script src="/resources/register/js/register.js"></script>
	
	<!-- board css, js 경로 선언 -->
	<link href="/resources/board/css/board.css" rel="stylesheet" type="text/css" />
	<link href="/resources/board/css/boardView.css" rel="stylesheet" type="text/css" />
	<script src="/resources/board/js/board.js"></script>
	
</head>
<body>
	<nav>
		<ul class="navUl"> <!-- menu였음. -->
			<li class="navLogo"> <!-- logo 였음 -->
				<a href="/"><img src="/resources/images/spring-boot-logo.png">Dyson <span class="logoName"><strong>SpringMVC </strong></span>web <br/>
					<span class="description">Bootstrap your application</span> 
				</a>
			</li>
			<li class="item"><span class="lineHover"><a href="/">Home</a></span></li>
			<li class="item"><span class="lineHover"><a href="/board">Board</a></span></li>
			<!-- <li class="item"><span class="lineHover"><a href="#">Services</a></span></li>  -->
			
			<c:if test="${empty loginUser }">
				<li class="item button"><a href="/login">Log In</a></li>
			</c:if>
			<c:if test="${!empty loginUser }">
				<li id ="userData" class="item">
					<img id="userImage" alt="" src="${loginUser.mem_images }"> ${loginUser.mem_name } 님 환영합니다!
				</li>
				<li class="item button"><a href="/logout">Log out</a></li>
			</c:if>
			<li class="toggle"><span class="bars"></span></li>
		</ul>
	</nav>