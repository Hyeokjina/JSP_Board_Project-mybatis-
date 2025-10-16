<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사진게시판</title>

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

        .write-btn-area {
            text-align: right;
            margin-bottom: 1rem;
        }

        .list-area {
            min-height: 500px;
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            gap: 20px;
        }

        .thumbnail {
            background: white;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            width: calc(33.333% - 14px);
            padding: 15px;
            box-sizing: border-box;
            transition: all 0.3s ease;
            overflow: hidden;
            max-height: max-content;
        }

        .thumbnail:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 12px rgba(75, 137, 252, 0.3);
            border-color: #4b89fc;
            cursor: pointer;
        }

        .thumbnail img{
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 4px;
            margin-bottom: 10px;
            display: block;
        }

        .thumbnail p {
            margin: 0;
            color: #333;
            text-align: center;
        }

        .thumbnail p span {
            display: block;
            font-weight: 500;
            margin-bottom: 8px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
        }

        .thumbnail p small {
            color: #666;
            font-size: 0.9rem;
        }

        .empty-message {
            text-align: center;
            width: 100%;
            padding: 3rem 0;
            color: #666;
        }

        .empty-message h3 {
            margin-bottom: 1rem;
            color: #999;
        }
    </style>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />

	<div class="board-container">
        <div class="board-card">
            <h2>사진게시판</h2>

            <c:if test="${not empty loginMember}">   
	            <div class="write-btn-area">
	                <a class="btn btn-primary" href="${pageContext.request.contextPath}/enrollForm.th">게시글 작성</a>
	            </div> 
			</c:if>
		
            <div class="list-area">   
            	<c:choose>
	            	<c:when test="${not empty list}">
	            		<c:forEach var="b" items="${list}">
	            			<div class="thumbnail" onclick="location.href='${pageContext.request.contextPath}/detail.th?bno=${b.boardNo}'">
	            				<img src="${pageContext.request.contextPath}/${b.thumbnailImg}" alt="썸네일이미지">
	            				<p>
	            					<span>No. ${b.boardNo} ${b.boardTitle}</span>
	            					<small>조회수 : ${b.count} </small>
	            				</p>
	            			</div>
	            		</c:forEach>
	            	</c:when>
	            	<c:otherwise>
	    	            <div class="empty-message">
		                    <h3>게시글이 없습니다.</h3>          
		                </div>
	            	</c:otherwise>  
            	</c:choose>        
            </div>
        </div>
	</div>
</body>
</html>