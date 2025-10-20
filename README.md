# JSP Board Project - MyBatis Rebuild

## 🧩 개요

기존 JDBC를 사용한 JSP 기반 게시판 프로젝트([https://github.com/Hyeokjina/JSP_Board_Project](https://github.com/Hyeokjina/JSP_Board_Project)) 를 **MyBatis 프레임워크**를 적용하여 재구성한 프로젝트입니다.
MyBatis를 적용함으로써 **코드의 가독성**, **유지보수성**, **SQL 관리 효율성**을 크게 향상시켰습니다.

---

## ⚙️ MyBatis란?

**MyBatis**는 자바 기반의 **데이터베이스 매퍼 프레임워크**입니다.
SQL을 XML 또는 애너테이션으로 분리하여 작성하고, Java 객체와 DB 컬럼을 **자동으로 매핑**할 수 있습니다.

### 주요 특징

* SQL과 Java 코드 분리 → 유지보수 용이
* ResultSet → 객체 자동 매핑
* 동적 SQL 지원 (`<if>`, `<foreach>`, `<choose>` 등)
* 트랜잭션 관리 구조화 가능 (`SqlSession` 이용)
* JDBC 코드의 반복 제거 → 간결한 코드 작성 가능

### 사용 목적

* 기존 JDBC 기반 프로젝트를 효율적이고 구조적으로 개선
* SQL 관리와 Java 비즈니스 로직 분리를 통해 **가독성 및 생산성 향상**

---

## 🚀 주요 변경 및 장점

### 1️⃣ 코드 간결화 및 유지보수 용이

* JDBC 방식은 SQL 작성, ResultSet 매핑, 예외 처리, 자원 반납 등 반복 코드가 많음
* MyBatis 적용 시, SQL과 Java 코드 분리로 **코드량 감소** 및 **가독성 향상**

```java
// MyBatis 예제
Board board = sqlSession.selectOne("BoardMapper.selectBoard", boardNo);
```

---

## MyBatis의 확장 사용방법

### 동적 SQL (Dynamic SQL)

MyBatis에서는 XML 내부에서 `<if>`, `<choose>`, `<where>` 등의 제어 태그를 이용해, 조건에 따라 **SQL 문장을 동적으로 생성**할 수 있습니다.

| 태그         | 설명                        | 주요 속성                       |
| ---------- | ------------------------- | --------------------------- |
| `<if>`     | 조건문 (if)                  | `test`                      |
| `<choose>` | 다중 분기 (if-else)           | `<when>`, `<otherwise>`     |
| `<where>`  | WHERE 자동 관리               | —                           |
| `<trim>`   | SQL 구문 다듬기 (SET, 콤마 처리 등) | `prefix`, `suffixOverrides` |

#### 예시

```xml
<select id="searchMember" parameterType="map" resultType="Member">
    SELECT *
      FROM MEMBER
     WHERE 1=1
     <if test="memberName != null and memberName != ''">
        AND MEMBER_NAME LIKE '%' || #{memberName} || '%'
     </if>
     <if test="status != null">
        AND STATUS = #{status}
     </if>
</select>
```

---

### 공통 SQL 재사용 (`<sql>` / `<include>`)

```xml
<sql id="memberColumns">
    MEMBER_NO, MEMBER_ID, MEMBER_NAME, EMAIL, STATUS
</sql>

<select id="selectList" resultType="Member">
    SELECT <include refid="memberColumns"/>
      FROM MEMBER
     WHERE STATUS = 'Y'
</select>
```

---

### 컬렉션 매개변수 (`<foreach>`)

```xml
<select id="selectByIds" parameterType="list" resultType="Member">
    SELECT * FROM MEMBER
     WHERE MEMBER_NO IN
     <foreach collection="list" item="id" open="(" separator="," close=")">
         #{id}
     </foreach>
</select>
```

---

### 다중 INSERT 사용 예시

```xml
<insert id="insertMembers" parameterType="list">
    INSERT INTO MEMBER (MEMBER_ID, MEMBER_NAME, EMAIL)
    VALUES
    <foreach collection="list" item="m" separator=",">
        (#{m.memberId}, #{m.memberName}, #{m.email})
    </foreach>
</insert>
```

---

### 자동 생성키 (`useGeneratedKeys` / `selectKey`)

#### MySQL / MariaDB

```xml
<insert id="insertBoard" parameterType="Board"
        useGeneratedKeys="true" keyProperty="boardNo">
    INSERT INTO BOARD (TITLE, CONTENT, WRITER)
    VALUES (#{title}, #{content}, #{writer})
</insert>
```

```java
Board b = new Board("공지사항", "내용", "관리자");
sqlSession.insert("BoardMapper.insertBoard", b);
int newId = b.getBoardNo(); // 자동 생성된 PK
```

#### Oracle (시퀀스 사용)

```xml
<insert id="insertBoard" parameterType="Board">
    <selectKey keyProperty="boardNo" resultType="int" order="BEFORE">
        SELECT SEQ_BNO.NEXTVAL FROM DUAL
    </selectKey>
    INSERT INTO BOARD (
        BOARD_NO, TITLE, CONTENT, WRITER
    ) VALUES (
        #{boardNo}, #{title}, #{content}, #{writer}
    )
</insert>
```

```java
Board b = new Board("공지사항", "내용", "관리자");
sqlSession.insert("BoardMapper.insertBoard", b);
int newId = b.getBoardNo(); // 시퀀스로 생성된 PK
```

> ⚠️ 주의
>
> * MySQL은 `useGeneratedKeys` 사용 가능
> * Oracle은 반드시 `selectKey` 사용, JDBC `getGeneratedKeys()`만 사용하면 ORA-17132 오류 발생 가능

---

### 🔗 참고

* [MyBatis 공식 문서](https://mybatis.org/mybatis-3/)
* [Oracle ORA-17132 Error Help](https://docs.oracle.com/error-help/db/ora-17132/)
