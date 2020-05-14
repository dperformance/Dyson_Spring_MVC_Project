<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>
<!-- main css 선언 -->
<link href="/resources/main/css/main.css" rel="stylesheet" type="text/css"/>
<div class="container">
	<br>
	<div class="jumbotron">
		<h1 class="text-center">저의 홈페이지에 접속해 주셔서 감사합니다.</h1> <br>
		<p class="text-center">로그인, 회원가입, 게시판, 댓글 기능을 구현한 개인 포트폴리오 입니다.</p> <br>
		<p class="text-center">
			<a class="btn btn-success btn-lg" href="/board" role="button">게시판으로 이동하기</a>
		</p>
	</div>
	<P style="text-align:center;">${serverTime}.</P>
</div>
</body>
</html>
