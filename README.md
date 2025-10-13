# 🚀 JSP 커뮤니티
> 회원 관리와 게시판 기능을 갖춘 JSP 기반 웹 애플리케이션만들기

## 📘 개요 (Overview)
- 프로젝트 목적과 주요 기능을 간단히 설명
- 기술스택과 핵심 특징 요약
	본 프로젝트는 **Servlet과 JSP를 이용한 MVC 패턴 기반의 웹 애플리케이션**으로,  
	회원 관리(로그인·회원가입) 및 게시판 CRUD 기능을 중심으로 구성되었습니다. 
	회원에 정보에 따라 게시글 글쓰기와 수정과 삭제가 가능하며,
	다른 회원에 게시글에 댓글작성이 가능합니다. 
	Oracle 데이터베이스와 JDBC를 통해 데이터 연동을 수행하며,  
	Eclipse + Tomcat 환경에서 실행 가능합니다.

## 🧱 기술 스택 (Tech Stack)
| 구분 | 사용 기술 |
|------|------------|
| Frontend | HTML, CSS, JavaScript, JSP |
| Backend | Java (Servlet, JDBC)|
| Server| Apache Tomcat |
| Database | Oracle |
| Tools | Eclipse, Git, GitHub |

## 🛠️ 설치 및 실행 (Installation & Run)
# 1. 프로젝트 클론
git clone https://github.com/Hyeokjina/JSP_Board_Project.git

# 2. 이클립스(Eclipse)에서 Import
- File > Import > Existing Projects into Workspace
- 복제한 프로젝트 폴더 선택 후 Import

# 3. 데이터베이스(Oracle) 설정
- Oracle 실행 후 데이터베이스 및 테이블 생성
- src/main/webapp/WEB-INF/classes/sql 폴더 내 SQL 스크립트 실행
- JDBC 연결 정보(application.properties 또는 JDBCTemplate.java) 수정

# 4. Tomcat 서버 설정
- Eclipse > Servers > New > Server > Apache Tomcat 선택
- 프로젝트를 서버에 Add 후 실행

# 5. 웹 애플리케이션 실행
- 브라우저에서 접속
http://localhost:8080/jspProject/

## 📂 프로젝트 구조 (Directory Structure)
<pre>
project/
 ├── src/
 │   ├── com/project/controller/     # Servlet 컨트롤러
 │   ├── com/project/model/dao/      # 데이터 접근 로직 (DAO)
 │   ├── com/project/model/vo/       # VO (Value Object)
 │   ├── com/project/service/        # 비즈니스 로직
 │   └── com/project/common/         # 공용 유틸 (JDBCTemplate 등)
 ├── webapp/
 │   ├── WEB-INF/
 │   │   ├── views/                  # JSP 뷰 페이지
 │   │   └── web.xml                 # 배포 서술자
 │   ├── resources/                  # CSS, JS, 이미지
 │   └── index.jsp                   # 메인 페이지
 └── README.md
<pre>
## 🌟 주요 기능 (Key Features)
- 회원가입 / 로그인 / 로그아웃 / 댓글 작성 기능
- 게시글 등록, 조회, 수정, 삭제 (CRUD)
- 기능별 오류 페이지 구성
- Oracle DB 연동을 통한 데이터 관리
- MVC 패턴 기반 구조로 모듈화된 개발
- JSP include를 통한 공통 레이아웃 구성

## 📸 화면 미리보기 (Preview)

| 기능 | 미리보기 |
|------|-----------|
| 로그인 화면 | ![Login Page](./assets/login.gif) |
| 회원가입 화면 | ![Register Page](./assets/register.png) |
| 게시판 목록 | ![Board List](./assets/board-list.jpg) |
| 게시글 작성 | ![Post Write](./assets/post-write.gif) |


## 💡 학습 포인트 (Learning Points)

- JSP & Servlet 기반 MVC 구조 설계 방법 학습
- JDBC를 통한 데이터베이스 연결 및 SQL 처리 로직 구현
- Tomcat 서버를 활용한 배포 및 실행 환경 이해
- JSP 내 JSTL / EL 사용으로 동적 페이지 구현

### 🧾 MEMBER 테이블 구조

| 컬럼명 (COLUMN)   | 데이터 타입 (TYPE)        | NULL 허용 | 기본값 (DEFAULT) | 순번 | 설명 (DESCRIPTION)               |
|-------------------|---------------------------|-----------|------------------|------|----------------------------------|
| MEMBER_NO         | NUMBER                    | No        |                  | 1    | 회원번호 (PK)                    |
| MEMBER_ID         | VARCHAR2(30 BYTE)         | No        |                  | 2    | 회원 아이디                      |
| MEMBER_PWD        | VARCHAR2(100 BYTE)        | No        |                  | 3    | 회원 비밀번호                    |
| MEMBER_NAME       | VARCHAR2(15 BYTE)         | No        |                  | 4    | 회원 이름                        |
| PHONE             | VARCHAR2(13 BYTE)         | Yes       |                  | 5    | 전화번호                         |
| EMAIL             | VARCHAR2(100 BYTE)        | Yes       |                  | 6    | 이메일                           |
| ADDRESS           | VARCHAR2(100 BYTE)        | Yes       |                  | 7    | 주소                             |
| INTEREST          | VARCHAR2(100 BYTE)        | Yes       |                  | 8    | 취미                             |
| ENROLL_DATE       | DATE                      | Yes       | SYSDATE          | 9    | 회원가입일                       |
| MODIFY_DATE       | DATE                      | Yes       | SYSDATE          | 10   | 정보수정일                       |
| STATUS            | VARCHAR2(1 BYTE)          | Yes       | 'Y'              | 11   | 상태값 (Y: 활성 / N: 비활성)     |


### 🧾 BOARD 테이블 구조

| 컬럼명 (COLUMN)   | 데이터 타입 (TYPE)        | NULL 허용 | 기본값 (DEFAULT) | 순번 | 설명 (DESCRIPTION)               |
|-------------------|---------------------------|-----------|------------------|------|----------------------------------|
| BOARD_NO          | NUMBER                    | No        |                  | 1    | 게시글번호 (PK)                  |
| BOARD_TYPE        | NUMBER                    | Yes       |                  | 2    | 게시글타입 (일반1 / 사진2)       |
| CATEGORY_NO       | NUMBER                    | Yes       |                  | 3    | 카테고리번호                     |
| BOARD_TITLE       | VARCHAR2(100 BYTE)        | No        |                  | 4    | 게시글제목                       |
| BOARD_CONTENT     | VARCHAR2(4000 BYTE)       | No        |                  | 5    | 게시글내용                       |
| BOARD_WRITER      | NUMBER                    | No        |                  | 6    | 작성자회원번호 (FK → MEMBER_NO)  |
| COUNT             | NUMBER                    | Yes       | 0                | 7    | 조회수                           |
| CREATE_DATE       | DATE                      | No        | SYSDATE          | 8    | 작성일                           |
| STATUS            | VARCHAR2(1 BYTE)          | Yes       | 'Y'              | 9    | 상태값 (Y: 활성 / N: 비활성)     |
