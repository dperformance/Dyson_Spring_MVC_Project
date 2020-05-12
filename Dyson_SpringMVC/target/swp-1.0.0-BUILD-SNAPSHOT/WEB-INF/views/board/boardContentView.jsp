<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>

<div class="boardTitle">
  <h2>게시글 상세</h2>
  <p>게시글을 봅니다.</p>
<hr width="60%">
</div>
<div class="boardBody">
	<form id="boardForm" method="post">
		<div class="row">
			<div class="col-25">
				<label class="labelGroup" for="fname">제목</label>
			</div>
			<div class="col-75">
				<input class="inputGroup" type="text" name="b_title" value="${bvo.b_title }" readonly>
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
				<label class="labelGroup" for="country"></label>
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
				<textarea class="inputGroup" name="b_content" style="height: 200px" readonly>${bvo.b_content }</textarea>
			</div>
		</div>
		<div class="board-btn">
			<c:if test="${loginUser.mem_name == bvo.b_writer }">
					<input type="button" class="btn btn-primary" value="수정" onclick="location.href='/board/modify'"/>
					<input type="button" class="btn btn-danger"value="삭제" id="btn-delete" />
			</c:if>
			<a href="javascript:history.go(-1)" role="button" class="btn btn-secondary">취소</a>
		</div>
	</form>
</div>
<hr width="60%">
<%-- 댓글 출력 부분--%>
<p class="commCount">댓글${commVO.size() }  |  조회수${bvo.b_hit }</p>
	
	<%-- 댓글 출력 --%>
	<c:forEach var="commList" items="${commVO }">
		<c:if test="${commList.c_lev == 0 }"> <%-- 메인 댓글을 출력하기 위해 lev값이 0인것만 출력 --%>
			<div class="comment">
				<p class="commPtag">
					<span class="commWriter">[${commList.c_writer }]</span>
					<c:if test="${commList.c_writer == bvo.b_writer }"><span class="writerCheck">(글작성자)</span></c:if>
					<span class="writeDate">
						<fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${commList.c_cre_date }" />
					</span>
					<input type="button" name="reOpen" value="답글" onclick="replyView(${commList.c_seq})">
					<c:if test="${commList.c_writer == loginUser.mem_name}">
						<input type="button" value="댓글삭제" onclick="commDel(${commList.c_seq})" />
						<input type="hidden" id="commSeq" name="c_seq" value="${commList.c_seq }" />
					</c:if>
					
				</p>
				<p><textarea class="commText" name="c_content" rows="4" cols="55" readonly>${commList.c_content }</textarea></p>
				<hr>
			</div>
		</c:if>
		
		<c:if test="${commList.c_lev >= 1 }"> <%-- 메인 댓글의 대댓글(답글)을 출력하기 위해 lev값이 1이상인것 을 출력한다. --%>
			<div class="comment">
				<div class="c-box">
					<p>&nbsp;&nbsp; ㄴ <span class="commWriter">[${commList.c_writer }]</span>
					<c:if test="${commList.c_writer == bvo.b_writer }"><span class="writerCheck">(글작성자)</span></c:if>
					<span class="writeDate">
						<fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${commList.c_cre_date }" />
					</span>
					<input type="button" name="reOpen" value="답글" onclick="replyView(${commList.c_seq})">
					<c:if test="${commList.c_writer == loginUser.mem_name}">
						<input type="button" value="댓글삭제" onclick="commDel(${commList.c_seq})" />
						<input type="hidden" id="commSeq" name="c_seq" value="${commList.c_seq }" />
					</c:if>
					<p><textarea class="reCommText" name="c_content" rows="4" cols="55" readonly>${commList.c_content }</textarea></p>
				</div>
				<hr>
			</div>
		</c:if>
		
		<!-- 대댓글 입력 폼 -->
		<div id="${commList.c_seq}" style="display:none" class="c-c">
			<form id="reCommForm${commList.c_seq }" method="post">
				<p><textarea class="commText" name="c_content" rows="4" cols="55" placeholder="댓글을 입력하세요."></textarea></p>
				<input type="button" value="답글저장" onclick="reComm(${commList.c_seq})">
				<input type="hidden" name="c_lev" value="${commList.c_lev}">
				<input type="hidden" name="b_seq" value="${bvo.b_seq }" />
				<input type="hidden" name="c_step" value="${commList.c_step}" />
				<input type="hidden" name="c_writer" value="${loginUser.mem_name }" />
				<input type="hidden" name="c_bname" value="mainBoard" />
				
			</form>
		</div>
	</c:forEach>
	
	<%-- 댓글 입력 폼 --%>
	<c:if test="${!empty loginUser }">
		<form id="commForm" method="post">
			<div class="commWrite">
				<p><textarea class="commText" name="c_content" rows="4" cols="55" placeholder="댓글을 입력하세요."></textarea></p>
				<input type="button" value="댓글저장" id="btn-comm"/>
				<input type="hidden" id="b_seq" name="b_seq" value="${bvo.b_seq }" />
				<c:if test="${commStep != 0}">
					<input type="hidden" name="c_step" value="${commStep}" />
				</c:if>
				<input type="hidden" name="c_writer" value="${loginUser.mem_name }" />
				<input type="hidden" name="c_bname" value="mainBoard" />
			</div>
		</form>
	</c:if>
</body>
</html>