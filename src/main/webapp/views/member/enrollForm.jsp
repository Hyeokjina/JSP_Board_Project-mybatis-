<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

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
            <form id="enroll-form" action="${pageContext.request.contextPath}/insert.me" method="post">
                <div class="row form-row">
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="userId" required placeholder="아이디 입력...">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-outline-primary w-100" onclick="idDulpicateCheck()">중복확인</button>
                    </div>
                </div>

                <div class="form-row">
                    <input type="password" class="form-control" name="userPwd" required placeholder="비밀번호 입력...">
                </div>

                <div class="form-row">
                    <input type="password" class="form-control" required placeholder="비밀번호 확인...">
                </div>

                <div class="form-row">
                    <input type="text" class="form-control" name="userName" required placeholder="이름 입력...">
                </div>

                <div class="form-row">
                    <input type="text" class="form-control" name="phone" placeholder="전화번호 입력...">
                </div>

                <div class="form-row">
                    <input type="email" class="form-control" name="email" placeholder="이메일 입력...">
                </div>

                <div class="form-row">
                    <input type="text" class="form-control" name="address" placeholder="주소 입력...">
                </div>

                <div class="form-row">
                    <label class="form-label fw-bold">관심분야</label>
                    <div class="interest-section">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="sports" id="sports">
                            <label class="form-check-label" for="sports">운동</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="hiking" id="hiking">
                            <label class="form-check-label" for="hiking">등산</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="fishing" id="fishing">
                            <label class="form-check-label" for="fishing">낚시</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="cooking" id="cooking">
                            <label class="form-check-label" for="cooking">요리</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="gaming" id="gaming">
                            <label class="form-check-label" for="gaming">게임</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="movie" id="movie">
                            <label class="form-check-label" for="movie">영화</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="interest" value="etc" id="etc">
                            <label class="form-check-label" for="etc">기타</label>
                        </div>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <button disabled type="submit" class="btn btn-primary btn-lg me-3" onclick="return validationCheck()">회원가입</button>
                    <button type="reset" class="btn btn-outline-secondary btn-lg">다시입력</button>
                </div>
            </form>
        </div>
    </div>
    <script>
        function validationCheck(){
            const pwdInputList = document.querySelectorAll("#enroll-form input[type=password]");
            
            if(pwdInputList[0].value !== pwdInputList[1].value){
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
        }
        //Ajax란?
        //웹페이지를 새로고침하지 않고 서버와 데이터를 주고받을 수 있게 해주는 기술
        //예를들면 게시판에서 댓글을 달았는데 페이지가 새로고침되지않고 댓글목록이 수정된다. -> Ajax활용
        // 기존 웹 개발 방식
        // -> 버튼을 클릭할 때마다 페이지 전체가 서버에 전송되며, 응답을 받기 전 잠시 화면 전체를 횐화면에서 대기함
        //    서버에서 새로운 HTML만들어서 리턴해준다면 이때 화면을 그려줌. -> 매번 화면이 깜빡인다.
        /*
            js를 사용해서 Ajax를 구현할 때는 기본적으로 제공해주는 XMLHttpRequest객체를 사용.
            기본적으로 XML의 데이터 형식을 사용했지만 최근에는 전부 JSON형식의 데이터를 주고받는다.

            const xhr = new XMLHttpRequest();
            xhr.open("요청방식", "idDulpicateCheck.me?checkId=" + encodeURIComponent(("user01"), true));

            xhr.onreadystatechange = function(){
                if(xhr.readyState === 4){ //요청완료상태
                    if(xhr.status === 200){ //응답성공
                        const result = xhr.responseText;
                        if(result === "생각했던 결과"){
                            //성공시 실행할 코드
                        } else {
                            //실패시 실행할 코드
                        }
                    }

                }
            }
        */

        //ajax란
        //jQuery에서 Ajax기능을 쉽게 사용할 수 있도록 만든 함수다
        /*
            $.ajax({
                url : "요청을 보낼 주소",
                type : "요청방식",
                data : {},
                success: function(){
                    성공시 실행코드
                },
                error: function(){
                    실패시 실행코드
                }
            })
        */
        
        //중복확인버튼 클릭시 사용자가 입력한 아이디가 이미 존재하는지에 대한 결과를 알고싶다.
        //만약 존재한다면 -> 사용불가 -> alert메시지 출력(이미 존재하는 id입니다.)
        //존재하지 않는다면 -> 사용가능 -> 정말 사용할거야? yes -> 더이상 변경 x
        //                                              no -> 다시 입력

        function idDulpicateCheck(){
            const idInput = document.querySelector("#enroll-form input[name=userId]");

            if(idInput.value.length < 5) { //아이디 형식에대한 예외처리
                return;
            }

            $.ajax({
                url : "idDulpicateCheck.me",
                type : "get",
                data : {
                    checkId : idInput.value
                },
                success: function(result){
                	if(result === "NNNNN") { //존재한다면
                		alert("이미 존재하는 ID입니다.");
                		idInput.focus();
                	} else { //존재하지 않는다면 
                		if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){
                			idInput.setAttribute("readonly", true);

                            const submitBtn = document.querySelector("#enroll-form button[type=submit]");
                            submitBtn.removeAttribute("disabled");
                		} else{
                            idInput.focus();
                        }
                	}
                }, 
                error: function(err){
                    console.log("아이디 체크 요청 실패 : ", err);
                }
            })
        }
    </script>
</body>
</html>