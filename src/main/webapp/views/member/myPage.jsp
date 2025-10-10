<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>마이페이지</title>
	
	<style>
		.mypage-container {
			max-width: 800px;
			margin: 50px auto;
			padding: 2rem;
		}

		.mypage-card {
			background: white;
			border-radius: 8px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			padding: 2rem;
		}

		.mypage-card h2 {
			text-align: center;
			color: #333;
			margin-bottom: 2rem;
			padding-bottom: 1rem;
			border-bottom: 2px solid #4b89fc;
		}

		.form-table {
			width: 100%;
			margin-bottom: 2rem;
		}

		.form-table tr {
			border-bottom: 1px solid #f0f0f0;
		}

		.form-table td {
			padding: 1rem 0.5rem;
		}

		.form-table td:first-child {
			width: 150px;
			font-weight: 500;
			color: #555;
		}

		.form-table input[type="text"],
		.form-table input[type="password"] {
			width: 100%;
			padding: 0.5rem;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 0.95rem;
		}

		.form-table input[readonly] {
			background-color: #f5f5f5;
			cursor: not-allowed;
		}

		.interest-group {
			display: flex;
			flex-wrap: wrap;
			gap: 1rem;
		}

		.interest-group label {
			display: flex;
			align-items: center;
			gap: 0.3rem;
			cursor: pointer;
		}

		.button-group {
			display: flex;
			justify-content: center;
			gap: 0.5rem;
			margin-top: 2rem;
		}
	</style>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />

	<div class="mypage-container">
		<div class="mypage-card">
			<h2>내 정보</h2>
			<form action="${pageContext.request.contextPath}/update.me" method="post" id="update-form">
				<table class="form-table">
					<tr>
						<td>* 아이디</td>
						<td><input type="text" name="userId" maxlength="15" readonly value="${loginMember.memberId}"></td>
					</tr>
					<tr>
						<td>* 이름</td>
						<td><input type="text" maxlength="8" readonly value="${loginMember.memberName}"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="phone" placeholder="- 포함해서 입력" value="${loginMember.phone}"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" value="${loginMember.email}"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address" value="${loginMember.address}"></td>
					</tr>
					<tr>
						<td>관심분야</td>
						<td>
							<div class="interest-group">
								<label>
									<input type="checkbox" name="interest" value="sports" class="form-check-input">
									운동
								</label>
								<label>
									<input type="checkbox" name="interest" value="hiking" class="form-check-input">
									등산
								</label>
								<label>
									<input type="checkbox" name="interest" value="fishing" class="form-check-input">
									낚시
								</label>
								<label>
									<input type="checkbox" name="interest" value="cooking" class="form-check-input">
									요리
								</label>
								<label>
									<input type="checkbox" name="interest" value="gaming" class="form-check-input">
									게임
								</label>
								<label>
									<input type="checkbox" name="interest" value="movie" class="form-check-input">
									영화
								</label>
								<label>
									<input type="checkbox" name="interest" value="etc" class="form-check-input">
									기타
								</label>
							</div>
						</td>
					</tr>
				</table>
				<script>
					const interest = "${loginMember.interest}";
					const checkBoxArr = document.querySelectorAll("input[name=interest]");
					
					for(let box of checkBoxArr){
						if(interest.includes(box.value)){
							box.checked = true;
						}
					}
				</script>

				<div class="button-group">
					<button type="submit" class="btn btn-success">정보수정</button>
					<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-pwd-modal">비밀번호 변경</button>
					<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete-member-modal">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>


	<!-- 비밀번호 변경 팝업 -->
	<div class="modal fade" id="update-pwd-modal" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h5 class="modal-title">비밀번호 변경</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/updatePwd.me" method="post">
						<div class="mb-3">
							<label for="currentPwd" class="form-label">현재 비밀번호</label>
							<input type="password" class="form-control" id="currentPwd" name="userPwd" required>
						</div>
						<div class="mb-3">
							<label for="newPwd" class="form-label">변경할 비밀번호</label>
							<input type="password" class="form-control" id="newPwd" name="updatePwd" required>
						</div>
						<div class="mb-3">
							<label for="checkPwd" class="form-label">변경할 비밀번호 확인</label>
							<input type="password" class="form-control" id="checkPwd" required>
						</div>
						<div class="d-grid">
							<button id="edit-pwd-btn" type="submit" class="btn btn-primary">비밀번호 변경</button>
						</div>
					</form>

			
				</div>
			</div>
		</div>
	</div>

	<!-- 탈퇴 팝업 -->
	<div class="modal fade" id="delete-member-modal" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h5 class="modal-title">회원탈퇴</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/delete.me" method="post">
						<div class="alert alert-warning" role="alert">
							<strong>경고:</strong> 탈퇴 후 복구가 불가능합니다.<br>
							정말로 탈퇴하시겠습니까?
						</div>
						<div class="mb-3">
							<label for="deletePwd" class="form-label">비밀번호</label>
							<input type="password" class="form-control" id="deletePwd" name="userPwd" required>
						</div>
						<div class="d-grid">
							<button type="submit" class="btn btn-danger">탈퇴하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>