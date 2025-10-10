<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
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
        .form-table textarea,
        .form-table input[type="file"] {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 0.95rem;
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
        .button-group button.back-btn {
            background: #aaa;
        }
        .existing-file {
            color: #555;
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>
    <jsp:include page="/views/common/menubar.jsp" />

    <div class="board-container">
        <div class="board-card">
            <h2>게시글 수정</h2>

            <form action="<c:url value='/update.bo'/>" method="post">
                <!-- boardNo를 반드시 hidden으로 전송 이건 몰랐던 타입이였음 -->
                <input type="hidden" name="boardNo" value="${board.boardNo}" />

                <table class="form-table">
                    <tr>
                        <th>카테고리</th>
                        <td>
                            <select name="categoryNo" required>
                                <option value="10" ${board.categoryNo == 10 ? 'selected' : ''}>공통</option>
                                <option value="20" ${board.categoryNo == 20 ? 'selected' : ''}>운동</option>
                                <option value="30" ${board.categoryNo == 30 ? 'selected' : ''}>등산</option>
                                <option value="40" ${board.categoryNo == 40 ? 'selected' : ''}>게임</option>
                                <option value="50" ${board.categoryNo == 50 ? 'selected' : ''}>낚시</option>
                                <option value="60" ${board.categoryNo == 60 ? 'selected' : ''}>요리</option>
                                <option value="70" ${board.categoryNo == 70 ? 'selected' : ''}>기타</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" value="${board.boardTitle}" required />
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" rows="10" required>${board.boardContent}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            <c:if test="${not empty board.fileName}">
                                <div class="existing-file">현재 파일: ${board.fileName}</div>
                            </c:if>
                            <input type="file" name="upfile" />
                        </td>
                    </tr>
                </table>

                <div class="button-group">
                    <button type="submit">수정 완료</button>
                    <button type="button" class="back-btn" onclick="history.back();">취소</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
