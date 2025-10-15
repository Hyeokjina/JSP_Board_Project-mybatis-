<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style>
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 2rem;
            border: 1px solid #ddd;
            border-radius: 8px;
            background: #f9f9f9;
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
            margin-right: 1rem;
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>
<jsp:include page="/views/common/menubar.jsp" />

<div class="form-container">
    <h2 class="text-center mb-4">회원가입</h2>
    <form id="enroll-form" action="${pageContext.request.contextPath}/insert.me" method="post">
        <div class="form-row">
            <input type="text" name="userId" placeholder="아이디 입력..." required>
            <button type="button" onclick="idDulpicateCheck()">중복확인</button>
        </div>

        <div class="form-row">
            <input type="password" name="userPwd" placeholder="비밀번호 입력..." required>
        </div>
        <div class="form-row">
            <input type="password" placeholder="비밀번호 확인..." required>
        </div>
        <div class="form-row">
            <input type="text" name="userName" placeholder="이름 입력..." required>
        </div>
        <div class="form-row">
            <input type="text" name="phone" placeholder="전화번호 입력...">
        </div>
        <div class="form-row">
            <input type="email" name="email" placeholder="이메일 입력...">
        </div>
        <div class="form-row">
            <input type="text" name="address" placeholder="주소 입력...">
        </div>

        <div class="form-row">
            <label>관심분야</label>
            <div class="interest-section">
                <div class="form-check">
                    <input type="checkbox" name="interest" value="sports" id="sports">
                    <label for="sports">운동</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="hiking" id="hiking">
                    <label for="hiking">등산</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="fishing" id="fishing">
                    <label for="fishing">낚시</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="cooking" id="cooking">
                    <label for="cooking">요리</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="gaming" id="gaming">
                    <label for="gaming">게임</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="movie" id="movie">
                    <label for="movie">영화</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="interest" value="etc" id="etc">
                    <label for="etc">기타</label>
                </div>
            </div>
        </div>

        <div class="form-row">
            <button type="submit">회원가입</button>
            <button type="reset">다시입력</button>
        </div>
    </form>
</div>

<!-- jQuery CDN 반드시 body 끝에 넣기 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
    function validationCheck(){
        const pwd = document.querySelectorAll("#enroll-form input[type=password]");
        if(pwd[0].value !== pwd[1].value){
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }
    }

    function idDulpicateCheck(){
        const idInput = document.querySelector("#enroll-form input[name=userId]");
        if(idInput.value.length < 5) return;

        $.ajax({
            url: "idDulpicateCheck.me",
            type: "get",
            data: { checkId: idInput.value },
            success: function(result){
                if(result === "NNNNN"){
                    alert("이미 존재하는 ID입니다.");
                    idInput.focus();
                } else {
                    if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")){
                        idInput.readOnly = true;
                        document.querySelector("#enroll-form button[type=submit]").disabled = false;
                    } else {
                        idInput.focus();
                    }
                }
            },
            error: function(err){
                console.log("아이디 체크 실패: ", err);
            }
        });
    }
</script>
</body>
</html>
