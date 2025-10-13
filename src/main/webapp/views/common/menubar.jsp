<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- google web font notosans-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- 부트스트랩 5.3.3-->
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body{
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
        }
        
        a{
            text-decoration: none;
            color: black;
        }

        ul, li, ol{
            list-style: none;
            margin: 0;
            padding: 0;
        }

        h1{ padding: 24px 0;
        cursor: pointer;}

        .navbar-nav .nav-link {
            font-size: 1.1rem;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .navbar-nav .nav-link:hover {
            color: #ffc107 !important;
        }
    </style>
</head>
<body>
	<c:if test="${not empty alertMsg}">
		<script>
			alert("${alertMsg}");
		</script>
		<c:remove var="alertMsg"/>
	</c:if>

    <div class="container-fluid">
        <h1 class="text-center py-4 mb-4 border-bottom" id="welcome">Welcome KH World</h1>

        <div class="d-flex justify-content-end mb-4">
        <c:choose>
        	<c:when test="${empty sessionScope.loginMember}">
	            <!--로그인 전-->
	            <form action="${pageContext.request.contextPath}/login.me" method="post" class="d-flex gap-2 align-items-end">
	                <div class="form-group">
	                    <label for="userId" class="form-label small">아이디</label>
	                    <input type="text" name="userId" id="userId" class="form-control form-control-sm" required style="width: 120px;">
	                </div>
	                <div class="form-group">
	                    <label for="userPwd" class="form-label small">비밀번호</label>
	                    <input type="password" name="userPwd" id="userPwd" class="form-control form-control-sm" required style="width: 120px;">
	                </div>
	                <div class="d-flex flex-column gap-1">
	                    <button type="submit" class="btn btn-primary btn-sm">로그인</button>
	                    <button type="button" class="btn btn-outline-secondary btn-sm" onclick="enrollPage();">회원가입</button>
	                </div>
	            </form>
	        <script>
	        console.log("${request.contextPath}")
	        	function enrollPage(){
	        		//location.href = "http://localhost:8000/jsp/views/member/enrollForm.jsp";
	        		//내부 디렉터리 구조를 유추할 수 있는 단서가 될 수 있기때문에 디렉터리구조를 url에 직접 노출하지 않고
	        		//서블릿을 통해 요청한다.
	        		
	        		location.href = "${pageContext.request.contextPath}/enrollForm.me";
	        		//단순한 페이지 요청도 servlet을 거처가도록 할 것(url에는 서버 요청을 위한 맵핑값이 나타나도록)
	        	}
	        </script>
        </c:when>
        <c:otherwise>
	            <!--로그인 후-->
	            <div class="d-flex flex-column align-items-end">
	                <div class="mb-2">
	                    <span class="fw-bold text-primary">${loginMember.memberName}님</span>의 방문을 환영합니다.
	                </div>
	                <div class="d-flex gap-2">
	                    <a href="${pageContext.request.contextPath}/myPage.me" class="btn btn-outline-primary btn-sm">마이페이지</a>
	                    <a href="${pageContext.request.contextPath}/logout.me" class="btn btn-outline-danger btn-sm">로그아웃</a>
	                </div>
	            </div>
        </c:otherwise>
        </c:choose>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item"><a class="nav-link px-4" href="${pageContext.request.contextPath}">HOME</a></li>
                <li class="nav-item"><a class="nav-link px-4" href="">공지사항</a></li>
                <li class="nav-item"><a class="nav-link px-4" href="${pageContext.request.contextPath}/list.bo">일반게시판</a></li>
                <li class="nav-item"><a class="nav-link px-4" href="">사진게시판</a></li>
            </ul>
        </div>
    </nav>
</body>
<script>
    document.getElementById("welcome").addEventListener("click", function(){
        location.href = "${pageContext.request.contextPath}";
    });
</script>
</html>