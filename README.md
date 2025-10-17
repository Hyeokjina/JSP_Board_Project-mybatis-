# JSP Board Project - MyBatis Rebuild

## 개요
기존 JDBC를 사용한 JSP 기반 게시판 프로젝트를 MyBatis 프레임워크를 적용하여 재구성한 프로젝트입니다.  
MyBatis를 적용함으로써 코드의 가독성과 유지보수성을 높이고, SQL과 Java 코드의 분리를 실현하였습니다.

---

## 주요 변경 및 장점

### 1. 코드 간결화 및 유지보수 용이
- JDBC 방식은 SQL 작성, ResultSet 매핑, 예외 처리, 자원 반납 등 반복 코드가 많음.
- MyBatis 적용 시, SQL과 Java 코드 분리로 **코드량 감소** 및 **가독성 향상**.
```java
// MyBatis 예제
Board board = sqlSession.selectOne("BoardMapper.selectBoard", boardNo);
