<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일반게시판</title>
    <style>
        .board-container {
            max-width: 1000px;
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
        .write-btn-area {
            text-align: right;
            margin-bottom: 1rem;
        }
        .board-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 2rem;
        }
        .board-table thead {
            background: #4b89fc;
            color: white;
        }
        .board-table th, .board-table td {
            padding: 1rem;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
        }
        .board-table th {
            font-weight: 500;
        }
        .board-table tbody tr {
            transition: all 0.2s ease;
        }
        .board-table tbody tr:hover {
            background-color: #f5f8ff;
            cursor: pointer;
            transform: translateY(-2px);
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .pagination {
            display: flex;
            justify-content: center;
            gap: 0.5rem;
            margin-top: 2rem;
        }
        .pagination .btn {
            min-width: 40px;
        }
    </style>
</head>
<body>
<jsp:include page="/views/common/menubar.jsp" />

<div class="board-container">
    <div class="board-card">
        <h2>일반게시판</h2>
        <div class="write-btn-area">
            <a href="${pageContext.request.contextPath}/enroll.bo" class="btn btn-primary">글쓰기</a>
        </div>
        <table class="board-table">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>카테고리</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="b" items="${boardList}">
                    <tr onclick="location.href='${pageContext.request.contextPath}/detail.bo?boardNo=${b.boardNo}'">
                        <td>${b.boardNo}</td>
                        <td>${b.boardTitle}</td>
                        <td>${b.boardWriterName}</td>
                        <td>${b.categoryName}</td>
                        <td>${b.readCount}</td>
                        <td>${b.createDate}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty boardList}">
                    <tr>
                        <td colspan="6">게시글이 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <div class="pagination">
                <button class="btn btn-primary">
                &lt; 이전
                </button>
              	<button class="btn btn-outline-primary" >1</button>
              	<button class="btn btn-outline-primary" >2</button>
              	<button class="btn btn-outline-primary" >3</button>
              	<button class="btn btn-outline-primary" >4</button>
              	<button class="btn btn-outline-primary" >5</button>
           	    <button class="btn btn-primary">
                &lt; 다음
                </button>
          </div>
    </div>
</div>
</body>
</html>
