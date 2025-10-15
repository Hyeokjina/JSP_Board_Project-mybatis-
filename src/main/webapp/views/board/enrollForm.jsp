<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시글 작성</title>

	<style>
		.board-container {
			max-width: 800px;
			margin: 50px auto;
			padding: 2rem;
		}

		.board-card {
			background: white;
			border-radius: 8px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			padding: 2rem;
		}

		.board-card h2 {
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

		.form-table th {
			width: 120px;
			padding: 1rem;
			font-weight: 500;
			color: #555;
			text-align: left;
			vertical-align: top;
		}

		.form-table td {
			padding: 1rem;
		}

		.form-table select,
		.form-table input[type="text"],
		.form-table textarea,
		.form-table input[type="file"] {
			width: 100%;
			padding: 0.5rem;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 0.95rem;
			font-family: "Noto Sans KR", sans-serif;
		}

		.form-table textarea {
			resize: none;
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

	<div class="board-container">
		<div class="board-card">
			<h2>일반게시글 작성하기</h2>
			
			<%--
				파일을 전송하기 위해서는 form태그에 enctype="mulipart/form-data"속성을 추가해야한다.
				기본적인 form 전송시 인코딩 타입 -> application/x-www-form-urlencoded
				-> 이 방식은 모든 데이터를 문자열로 인코딩해서 한줄의 텍스트로 전달
				
				파일업로드시 위의 방식대로 모든 데이터를 문자열로 변경시
				파일의 바이너리 형태의 데이터도 url인코딩 방식으로 변경하게 된다.
				이때 데이터가 너무 커지고, 이과정에서 파일이 손상되면 서버가 이를 정상적으로 받아줄 수 없다
				그래서 파일의 원본 그대로 전달할 수 있는 다른 전송 인코딩 방식을 사용 
			 --%>

			<form action="${pageContext.request.contextPath}/insert.bo" method="post" enctype="multipart/form-data" >
				<table class="form-table">
					<tr>
						<th>카테고리</th>
						<td>
							<select name="category">	
								<c:forEach var="c" items="${categories}">
									<option value="${c.categoryNo}">${c.categoryName}</option>
								</c:forEach>					
							</select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" required>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content" rows="10"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="upfile">
						</td>
					</tr>
				</table>

				<div class="button-group">
					<button type="submit" class="btn btn-primary">작성하기</button>
					<button type="reset" class="btn btn-secondary">취소하기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>