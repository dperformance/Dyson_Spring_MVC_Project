<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>
<table>
	<thead>
		<tr>
			<td valign="top" colspan="5">
				<select id="listValue" name="listValue" onchange="listValue()">
					<option value="5" <c:if test="${paging.countPerPage == 5}">selected</c:if>>5개씩</option>
					<option value="10" <c:if test="${paging.countPerPage == 10}">selected</c:if>>10개씩</option>
					<option value="15" <c:if test="${paging.countPerPage == 15}">selected</c:if>>15개씩</option>
					<option value="20" <c:if test="${paging.countPerPage == 20}">selected</c:if>>20개씩</option>
					<option value="30" <c:if test="${paging.countPerPage == 30}">selected</c:if>>30개씩</option>
					<option value="40" <c:if test="${paging.countPerPage == 40}">selected</c:if>>40개씩</option>
					<option value="50" <c:if test="${paging.countPerPage == 50}">selected</c:if>>50개씩</option>
				</select>
			</td>
		</tr>
		<tr class="title">
			<th class="no">번호</th>
			<th class="subject">제목</th>
			<th class="writer">작성자</th>
			<th class="regdate">날짜</th>
			<th class="hit">조회수</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${empty list }">
			<tr>
				<td colspan="5">
					<h2>현재 등록된 게시글이 없습니다.</h2>
				</td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.b_seq }</td>
					<td><a href="/board/content/${vo.b_seq }">${vo.b_title }</a></td>
					<td>${vo.b_writer }</td>
					<td><fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${vo.b_cre_date }" /></td>
					<td>${vo.b_hit }</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr class="writee">
			<td class="write" colspan="5">
				<c:if test="${!empty loginUser }">
					<input type="button" value="글쓰기" class="btn btn-secondary" onclick="location.href='/board/form'"/>
				</c:if>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<div class="pagination">
					<c:if test="${paging.startBlock != 1}"> <%-- [이전으로] 처리 --%>
						<a href="/board?currentPage=${paging.startBlock - 1 }&countPerPage=${paging.countPerPage}">&lt;&lt;이전</a>
					</c:if>
					<c:forEach var="k" begin="${paging.startBlock }" end="${paging.endBlock }">
						<c:if test="${paging.currentPage == k }">
							<a href="" class="active">${k }</a> <%-- [현재 페이지(active page) --%>
						</c:if>
						<c:if test="${paging.currentPage != k }">
							<a href="/board?currentPage=${k }&countPerPage=${paging.countPerPage} ">${k }</a>
						</c:if>
					</c:forEach>
					<c:if test="${paging.endBlock < paging.totalPageCount }"> <%-- [다음으로] 처리 --%>
						<a href="/board?currentPage=${paging.endBlock + 1 }&countPerPage=${paging.countPerPage} ">다음&gt;&gt;</a>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="bottom" colspan="5">
				<form action="/boardSearch">
					<div class="subWrite">
						<select name="conSelect">
							<option value="tc">제목+내용</option>
							<option value="t">제목만</option>
							<option value="w">글작성자</option>
						</select>
						<input type="text" placeholder="검색어를 입력해주세요" name="searchData" required>
						<input type="hidden" id="currentPage" name="currentPage" value="${paging.currentPage }">
						<input type="hidden" name="countPerPage" value="${paging.countPerPage }">
						<input type="submit" class="btn btn-success" value="검색">
					</div>
				</form>
			</td>
		</tr>
	</tfoot>
</table>
</body>
</html>