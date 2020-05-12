<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar(Header) -->
<%@ include file="/WEB-INF/views/nav/navbar.jsp" %>
	
	<div id="register_container">
		<h1>Create Account</h1>
		<hr>
		<div>
		  <form id="regiForm" method="post">
		  	<div>
		  		<c:if test="${snsMemVO.mem_images != null }">
					<span>
						<img id="oauthImg" src="${snsMemVO.mem_images }" alt="photo">
						<input type="hidden" name="mem_images" value="${snsMemVO.mem_images }">
					</span>
				</c:if>
				<c:if test="${snsMemVO.mem_images == null }">
					<span>
						<img id="oauthImg" alt="userDefaultIcon" src="/resources/images/userDefaultIcon1.png">
						<input type="hidden" name="mem_images" value="/resources/images/userDefaultIcon1.png">
					</span>
				</c:if>
			</div>
			<div>
				<span class="spanText">
					<label>Nickname *</label>
				</span>
				<br>
				<input type="text" name="mem_name" class="register_box" value="${snsMemVO.mem_name }" readonly>
			</div>
			<div>
				<span class="spanText">
					<label>Email *</label>
				</span>
				<br>
				<input type="email" name="mem_email" class="register_box" value="${snsMemVO.mem_email }" readonly>
			</div>
			<br>
			<hr>
			<div class="regiDiv">			
				<input type="button" id="submit" value="Create Account">
				<input type="hidden" name="mem_pw" value="${snsMemVO.mem_pw }">
				
			</div>
		</form>
	 </div>
 </div>
</body>
</html> 