<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <style>
        .form-container{
            max-width: 600px;
            margin: 0 auto;
            padding: 2rem;
        }

        .form-row {
            margin-bottom: 1rem;
        }

        .interest-section {
            background-color: #f8f9fa;
            padding: 1rem;
            border-radius: 0.375rem;
            border: 1px solid #dee2e6;
        }

        .interest-section .form-check {
            display: inline-block;
            margin-right: 1.5rem;
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>
    <jsp:include page="/views/common/menubar.jsp" />

    <div class="container">
        <div class="form-container">
            <h2 class="text-center mb-4">회원가입</h2>
            <form id="signupForm" action="${pageContext.request.contextPath}/insert.me" method="post" onsubmit="return checkPwd()">
                
                <!-- 아이디 입력 + 중복확인 -->
                <div class="row form-row">
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="userId" id="userId" required placeholder="아이디 입력...">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-outline-primary w-100">중복확인</button>
                    </div>
                </div>

                <!-- 비밀번호 -->
                <div class="form-row">
                    <input type="password" class="form-control" name="userPwd" id="password" required placeholder="비밀번호 입력...">
                </div>

                <div class="form-row">
                    <input type="password" class="form-control" id="passwordcheck" required placeholder="비밀번호 확인...">
                </div>

                <!-- 이름 -->
                <div class="form-row">
                    <input type="text" class="form-control" name="userName" required placeholder="이름 입력...">
                </div>

                <!-- 전화번호 -->
                <div class="form-row">
                    <input type="text" class="form-control" name="phone" placeholder="전화번호 입력...">
                </div>

                <!-- 이메일 -->
                <div class="form-row">
                    <input type="email" class="form-control" name="email" placeholder="이메일 입력...">
                </div>

                <!-- 주소 -->
                <div class="form-row">
                    <input type="text" class="form-control" name="address" placeholder="주소 입력...">
                </div>

                <!-- 관심분야 -->
                <div class="form-row">
                    <label class="form-label fw-bold">관심분야</label>
                    <div class="interest-section">
                        <div class="form-check"><input type="checkbox" name="interest" value="sports" id="sports"><label for="sports">운동</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="hiking" id="hiking"><label for="hiking">등산</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="fishing" id="fishing"><label for="fishing">낚시</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="cooking" id="cooking"><label for="cooking">요리</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="gaming" id="gaming"><label for="gaming">게임</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="movie" id="movie"><label for="movie">영화</label></div>
                        <div class="form-check"><input type="checkbox" name="interest" value="etc" id="etc"><label for="etc">기타</label></div>
                    </div>
                </div>

                <!-- 버튼 -->
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary btn-lg me-3">회원가입</button>
                    <button type="reset" class="btn btn-outline-secondary btn-lg">다시입력</button>
                </div>
            </form>
        </div>
    </div>

    <script>
    function checkPwd() {
        const pwd = document.getElementById("password").value;
        const pwdCheck = document.getElementById("passwordcheck").value;

        if (pwd !== pwdCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }
        return true;
    }
    </script>
</body>
</html>
