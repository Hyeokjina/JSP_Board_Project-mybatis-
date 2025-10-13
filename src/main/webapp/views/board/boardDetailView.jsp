<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>

    <style>
        .board-container {
            max-width: 1000px;
            margin: 50px auto;
            padding: 2rem;
        }

        .board-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .board-card h2 {
            text-align: center;
            color: #333;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid #4b89fc;
        }

        .detail-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 2rem;
        }

        .detail-table th, .detail-table td {
            padding: 1rem;
            border: 1px solid #e0e0e0;
        }

        .detail-table th {
            background-color: #f8f9fa;
            font-weight: 500;
            color: #555;
            width: 120px;
            text-align: center;
        }

        .content-area {
            min-height: 200px;
            padding: 1rem;
            line-height: 1.6;
        }

        .button-group {
            display: flex;
            justify-content: center;
            gap: 0.5rem;
            margin-top: 2rem;
        }

        .reply-section {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            padding: 2rem;
        }

        .reply-table {
            width: 100%;
            border-collapse: collapse;
        }

        .reply-table th, .reply-table td {
            padding: 1rem;
            border: 1px solid #e0e0e0;
        }

        .reply-table thead {
            background-color: #f8f9fa;
        }

        .reply-table textarea {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 0.95rem;
            resize: none;
        }

        .reply-table tbody td {
            text-align: left;
            vertical-align: top;
            padding: 0.5rem;
        }

        .reply-btn {
            width: 100%;
            height: 100%;
            min-height: 80px;
        }
    </style>
</head>
<body>
<jsp:include page="/views/common/menubar.jsp" />

<div class="board-container">
    <!-- 게시글 상세 -->
    <div class="board-card">
        <h2>일반게시글 상세보기</h2>
        <table class="detail-table">
            <tr>
                <th>카테고리</th>
                <td>${board.categoryName}</td>
                <th>제목</th>
                <td colspan="3">${board.boardTitle}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.boardWriterName}</td>
                <th>작성일</th>
                <td>${board.createDate}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <div class="content-area">
                        ${board.boardContent}
                    </div>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">첨부파일이 없습니다.</td>
            </tr>
        </table>
        <div class="button-group">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/list.bo">목록가기</a>
            <c:if test="${loginUserNo == board.boardWriter}">
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/updateForm.bo?boardNo=${board.boardNo}">수정하기</a>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete.bo?boardNo=${board.boardNo}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제하기</a>
            </c:if>
        </div>
    </div>

    <div class="board-card">
        <h2>댓글 확인</h2>

        <!-- 댓글 섹션 -->
        <div class="reply-section">
            <table class="reply-table">
                <thead>
                    <tr>
                        <th width="120">댓글작성</th>
                        <td><textarea id="reply-content" cols="50" rows="3"></textarea></td>
                        <td width="100"><button class="btn btn-primary reply-btn" onclick="insertReply()">댓글등록</button></td>
                    </tr>
                </thead>
                <tbody id="reply-list">
                    <!-- 댓글 목록 AJAX로 로딩 -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    const boardNo = "${board.boardNo}";
    const loginUserNo = "${loginUserNo}";

    // 댓글 등록
    function insertReply() {
        const content = document.getElementById("reply-content").value.trim();
        if(!content) {
            alert("댓글을 입력하세요.");
            return;
        }

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/insertReply.re", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                if(xhr.responseText.trim() === "1") { // 성공시 1 반환
                    document.getElementById("reply-content").value = "";
                    loadReplies();
                } else {
                    alert("댓글 등록 실패");
                }
            }
        };
        xhr.send("refBno=" + boardNo + "&replyWriter=" + loginUserNo + "&replyContent=" + encodeURIComponent(content));
    }

    // 댓글 목록 로드
    function loadReplies() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "${pageContext.request.contextPath}/listReply.re?boardNo=" + boardNo, true);
        xhr.onreadystatechange = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
                let html = "";
                data.forEach(r => {
                    html += "<tr><td colspan='3'><b>" + r.replyWriterName + "</b>: " + r.replyContent + " (" + r.createDate + ")</td></tr>";
                });
                document.getElementById("reply-list").innerHTML = html;
            }
        };
        xhr.send();
    }

    // 페이지 로드 시 댓글 초기화
    window.onload = loadReplies;
</script>
</body>
</html>
