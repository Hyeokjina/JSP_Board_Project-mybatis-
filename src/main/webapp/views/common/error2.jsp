<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.error-container {
		display: flex;
		justify-content: center;
		align-items: center;
		min-height: 400px;
		padding: 2rem;
	}

	.error-card {
		max-width: 500px;
		width: 100%;
	}
</style>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />
	
	<div class="error-container">
		<div class="error-card">
			<div class="alert alert-danger text-center" role="alert">
				<div class="display-6 mb-3">
					<i class="bi bi-exclamation-triangle-fill"></i>
				</div>
				<h4 class="alert-heading">오류 발생</h4>
				<p class="mb-0">
					<c:out value="${errorMsg}" default="알 수 없는 오류가 발생하였습니다." />
				</p>
				<hr>
				<a href="javascript:history.back()" class="btn btn-outline-danger">이전 페이지로</a>
				<a href="${pageContext.request.contextPath}/" class="btn btn-danger ms-2">홈으로</a>
				<a href="${pageContext.request.contextPath}/eenrollForm.me" class="btn btn-danger ms-2">회원가입하기</a>
				
			</div>
		</div>
	</div>
</body>
</html>