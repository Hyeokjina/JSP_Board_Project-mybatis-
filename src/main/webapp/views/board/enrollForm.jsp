<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
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
            border-collapse: collapse;
        }
        .form-table th {
            width: 120px;
            padding: 1rem;
            text-align: left;
            vertical-align: top;
            color: #555;
        }
        .form-table td {
            padding: 1rem;
        }
        .form-table select,
        .form-table input[type="text"],
        .form-table textarea {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ddd;
            border-radius: 4px;
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
        .button-group button {
            padding: 0.5rem 1.5rem;
            border: none;
            border-radius: 4px;
            background: #4b89fc;
            color: white;
            cursor: pointer;
            font-size: 1rem;
        }
        .button-group button[type="reset"] {
            background: #aaa;
        }
    </style>
</head>
<body>
<jsp:include page="/views/common/menubar.jsp" />

<div class="board-container">
    <div class="board-card">
        <h2>게시글 작성</h2>
        <form action="<c:url value='/enroll.bo'/>" method="post">
            <table class="form-table">
                <tr>
                    <th>카테고리</th>
                    <td>
                        <select name="category" required>
                            <option value="10">공통</option>
							<option value="20">운동</option>
							<option value="30">등산</option>
							<option value="40">게임</option>
							<option value="50">낚시</option>
							<option value="60">요리</option>
							<option value="70">기타</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" rows="10" required></textarea></td>
                </tr>
                <tr>
				    <th>첨부파일</th>
				    <td>
				        <input type="file" name="upfile">
				    </td>
				</tr>
            </table>
            <div class="button-group">
                <button type="submit">작성하기</button>
                <button type="reset">취소하기</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
