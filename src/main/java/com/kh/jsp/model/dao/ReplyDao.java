package com.kh.jsp.model.dao;

import static com.kh.jsp.common.JDBCTemplate.*;
import com.kh.jsp.model.vo.Reply;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

    public int insertReply(Connection conn, Reply r) {
        String sql = "INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER) VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, r.getReplyContent());
            pstmt.setInt(2, r.getRefBno());
            pstmt.setInt(3, r.getReplyWriter());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Reply> selectReplyList(Connection conn, int boardNo) {
        List<Reply> list = new ArrayList<>();
        String sql = "SELECT r.REPLY_NO, r.REPLY_CONTENT, r.REF_BNO, r.REPLY_WRITER, m.USER_NAME AS REPLY_WRITER_NAME, r.CREATE_DATE " +
                     "FROM REPLY r JOIN MEMBER m ON r.REPLY_WRITER = m.USER_NO " +
                     "WHERE r.REF_BNO = ? AND r.STATUS='Y' ORDER BY r.CREATE_DATE ASC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardNo);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Reply r = new Reply();
                r.setReplyNo(rs.getInt("REPLY_NO"));
                r.setReplyContent(rs.getString("REPLY_CONTENT"));
                r.setRefBno(rs.getInt("REF_BNO"));
                r.setReplyWriter(rs.getInt("REPLY_WRITER"));
                r.setReplyWriterName(rs.getString("REPLY_WRITER_NAME"));
                r.setCreateDate(new java.sql.Date(rs.getTimestamp("CREATE_DATE").getTime()));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
