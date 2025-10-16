<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사진게시글 상세보기</title>

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

        .detail-table th,
        .detail-table td {
            padding: 1rem;
            border: 1px solid #e0e0e0;
        }

        .detail-table th {
            background-color: #f8f9fa;
            font-weight: 500;
            color: #555;
            width: 150px;
            text-align: center;
        }

        .detail-table td {
            background-color: white;
        }

        .content-area {
            min-height: 100px;
            padding: 1rem;
            line-height: 1.6;
        }

        .thumbnail-area {
            text-align: center;
            padding: 1.5rem;
        }

        .thumbnail-area img {
            max-width: 500px;
            width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .detail-images-area {
            display: flex;
            gap: 15px;
            justify-content: center;
            padding: 1rem;
            flex-wrap: wrap;
        }

        .detail-images-area img {
            width: 200px;
            height: 150px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #e0e0e0;
            transition: all 0.3s ease;
        }

        .detail-images-area img:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 12px rgba(75, 137, 252, 0.3);
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
            <h2>사진게시글 상세보기</h2>

            <table class="detail-table">
                <tr>
                    <th>제목</th>
                    <td colspan="3">
                        ${board.boardTitle}
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${board.memberId}</td>
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
                    <th>대표이미지</th>
                    <td colspan="3">
                        <div class="thumbnail-area">
                            <img src="${pageContext.request.contextPath}/${atList[0].filePath}${atList[0].changeName}" alt="대표이미지">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>상세이미지</th>
                    <td colspan="3">
                        <div class="detail-images-area">
                            <c:forEach var="at" items="${atList}">
                            	<c:if test="${at.fileLevel == 2}">
                            		<img src="${pageContext.request.contextPath}/${at.filePath}${at.changeName}" alt="상세이미지">
                            	</c:if>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>

            <div class="button-group">
                <a href="${pageContext.request.contextPath}/list.th" class="btn btn-primary">목록가기</a>
            </div>
        </div>
    </div>
</body>
</html>