<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>사진게시글 작성</title>

	<style>
		.board-container {
			max-width: 900px;
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
			width: 150px;
			padding: 1rem;
			font-weight: 500;
			color: #555;
			text-align: left;
			vertical-align: top;
		}

		.form-table td {
			padding: 1rem;
		}

		.form-table input[type="text"],
		.form-table textarea {
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

		.image-upload-area {
			text-align: center;
		}

		.thumbnail-preview {
			display: inline-block;
			width: 100%;
			max-width: 400px;
			height: 250px;
			border: 2px dashed #ddd;
			border-radius: 8px;
			background: #f8f9fa;
			display: flex;
			align-items: center;
			justify-content: center;
			cursor: pointer;
			transition: all 0.3s ease;
			overflow: hidden;
		}

		.thumbnail-preview:hover {
			border-color: #4b89fc;
			background: #f0f5ff;
		}

		.thumbnail-preview img {
			max-width: 100%;
			max-height: 100%;
			object-fit: contain;
		}

		.detail-images {
			display: flex;
			gap: 15px;
			justify-content: center;
		}

		.detail-image-preview {
			width: 180px;
			height: 140px;
			border: 2px dashed #ddd;
			border-radius: 8px;
			background: #f8f9fa;
			display: flex;
			align-items: center;
			justify-content: center;
			cursor: pointer;
			transition: all 0.3s ease;
			overflow: hidden;
		}

		.detail-image-preview:hover {
			border-color: #4b89fc;
			background: #f0f5ff;
		}

		.detail-image-preview img {
			max-width: 100%;
			max-height: 100%;
			object-fit: contain;
		}

		.upload-placeholder {
			color: #999;
			font-size: 0.9rem;
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
			<h2>사진게시글 작성하기</h2>

			<form action="${pageContext.request.contextPath}/insert.th" method="post" enctype="multipart/form-data">
				<table class="form-table">
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" required placeholder="제목을 입력하세요">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content" rows="7" placeholder="내용을 입력하세요"></textarea>
						</td>
					</tr>
					<tr>
						<th>대표이미지 *</th>
						<td class="image-upload-area">
							<div class="thumbnail-preview" onclick="chooseFile('#file1')">
								<img id="tumbnail-img" style="display: none;">
								<div class="upload-placeholder" id="thumbnail-placeholder">
									클릭하여 대표이미지를 선택하세요
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>상세이미지</th>
						<td>
							<div class="detail-images">
								<div class="detail-image-preview" onclick="chooseFile('#file2')">
									<img id="content-img1" style="display: none;">
									<div class="upload-placeholder" id="img1-placeholder">이미지 1</div>
								</div>
								<div class="detail-image-preview" onclick="chooseFile('#file3')">
									<img id="content-img2" style="display: none;">
									<div class="upload-placeholder" id="img2-placeholder">이미지 2</div>
								</div>
								<div class="detail-image-preview" onclick="chooseFile('#file4')">
									<img id="content-img3" style="display: none;">
									<div class="upload-placeholder" id="img3-placeholder">이미지 3</div>
								</div>
							</div>
						</td>
					</tr>
				</table>

				<div style="display: none;">
					<input type="file" name="file1" id="file1" required onchange="loadImg(this, '#tumbnail-img', '#thumbnail-placeholder')">
					<input type="file" name="file2" id="file2" onchange="loadImg(this, '#content-img1', '#img1-placeholder')">
					<input type="file" name="file3" id="file3" onchange="loadImg(this, '#content-img2', '#img2-placeholder')">
					<input type="file" name="file4" id="file4" onchange="loadImg(this, '#content-img3', '#img3-placeholder')">
				</div>

				<div class="button-group">
					<button type="submit" class="btn btn-primary">작성하기</button>
					<button type="reset" class="btn btn-secondary">취소하기</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		//file input이 변경되었을 때 해당 이미지를 미리보기 img태그에 넣어 보여주기
		function loadImg(changeInput, targetImgId, placeholderId){
			console.log("이미지 변경됨")
			const img = document.querySelector(targetImgId);
			const placeholder = document.querySelector(placeholderId);

			if(changeInput.files.length > 0){ //선택된 파일이 있는 경우
				const reader = new FileReader();
				//파일을 읽어서 Base64 인코딩된 문자열(Data URL)로 변환
				reader.readAsDataURL(changeInput.files[0]);

				//변환이 완료되었을 때 load이벤트를 실행
				reader.onload = function(ev){ 
					img.src = ev.target.result;
					img.style.display = 'block';
					placeholder.style.display = 'none';
				}
			} else {
				img.src = null;
				img.style.display = 'none';
				placeholder.style.display = 'block';
			}
		}

		function chooseFile(selector){
			const fileInput = document.querySelector(selector);
			fileInput.click();
		}
	</script>
</body>
</html>