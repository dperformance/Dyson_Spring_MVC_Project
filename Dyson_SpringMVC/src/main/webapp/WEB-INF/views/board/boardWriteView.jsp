<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

<div class="boardTitle">
  <h2>게시판 등록</h2>
  <p>이곳은 게시글을 등록하는 곳 입니다.</p>
<hr width="60%">
</div>

<div class="boardBody">
	<form id="boardForm" method="post">
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="fname">제목</label>
			</div>
			<div class="col-75">
				<input class="inputGroup" type="text" name="b_title" placeholder="글 제목을 입력해주세요." required autofocus="autofocus" />
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="lname">작성자</label>
			</div>
			<div class="col-75">
				<input class="inputGroup" type="text" name="b_writer" value="${loginUser.mem_name }" readonly />
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="country"></label>
			</div>
			<div class="col-75">
				<!-- <input class="inputGroup" type="text" name="no" placeholder="미구현 입니다." readonly>  -->
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="subject">내용</label>
			</div>
			<div class="col-75">
				<textarea class="inputGroup" name="b_content" placeholder="Write something.." style="height: 200px"  required></textarea>
			</div>
		</div>
		<div class="board-btn">
			<button type="button" class="btn btn-primary" id="btn-save">글등록</button>
			<a href="javascript:history.go(-1)" role="button" class="btn btn-secondary">취소</a>
		</div>
	</form>
</div>
</body>
</html>