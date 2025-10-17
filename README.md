# JSP Board Project - MyBatis Rebuild

## 🧩 개요
기존 JDBC를 사용한 JSP 기반 게시판 프로젝트(https://github.com/Hyeokjina/JSP_Board_Project) 를 **MyBatis 프레임워크**를 적용하여 재구성한 프로젝트입니다.  
MyBatis를 적용함으로써 **코드의 가독성**, **유지보수성**, **SQL 관리 효율성**을 크게 향상시켰습니다.

---

## ⚙️ MyBatis란?

**MyBatis**는 자바 기반의 **데이터베이스 매퍼 프레임워크**입니다.  
SQL을 XML 또는 애너테이션으로 분리하여 작성하고, Java 객체와 DB 컬럼을 **자동으로 매핑**할 수 있습니다.

### 주요 특징
- SQL과 Java 코드 분리 → 유지보수 용이  
- ResultSet → 객체 자동 매핑  
- 동적 SQL 지원 (`<if>`, `<foreach>`, `<choose>` 등)  
- 트랜잭션 관리 구조화 가능 (`SqlSession` 이용)  
- JDBC 코드의 반복 제거 → 간결한 코드 작성 가능  

### 사용 목적
- 기존 JDBC 기반 프로젝트를 효율적이고 구조적으로 개선  
- SQL 관리와 Java 비즈니스 로직 분리를 통해 **가독성 및 생산성 향상**

---

## 🚀 주요 변경 및 장점

### 1️⃣ 코드 간결화 및 유지보수 용이
- JDBC 방식은 SQL 작성, ResultSet 매핑, 예외 처리, 자원 반납 등 반복 코드가 많음  
- MyBatis 적용 시, SQL과 Java 코드 분리로 **코드량 감소** 및 **가독성 향상**

```java
// MyBatis 예제
Board board = sqlSession.selectOne("BoardMapper.selectBoard", boardNo);
