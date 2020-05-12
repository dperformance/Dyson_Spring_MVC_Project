<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

<div class="boardTitle">
  <h2>게시글 수정</h2>
  <p>이곳은 게시글을 수정하는 곳 입니다.</p>
<hr width="60%">
</div>

<div class="boardBody">
	<form id="boardForm" class="boardForm" method="post">
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="fname">제목</label>
			</div>
			<div class="col-75">
				<input class="inputGroup" type="text" name="b_title" value="${bvo.b_title }" />
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="lname">작성자</label>
			</div>
			<div class="col-75">
				<input class="inputGroup" type="text" name="b_writer" value="${bvo.b_writer }" readonly>
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<!-- <label class="labelGroup" for="country">미구현</label>  -->
			</div>
			<div class="col-75">
				<!-- <input class="inputGroup" type="text" placeholder="미구현 입니다." readonly>  -->
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="subject">내용</label>
			</div>
			<div class="col-75">
				<textarea class="inputGroup" name="b_content" style="height: 200px">${bvo.b_content }</textarea>

			</div>
		</div>
		<div class="board-btn">
			<button type="button" class="btn btn-primary" id="btn-update">글수정</button>
			<input type="hidden" name="b_seq" value="${bvo.b_seq }" id="b_seq" />
			<a href="javascript:history.go(-1)" role="button" class="btn btn-secondary">취소</a>
		</div>
	</form>
</div>
</body>
</html>