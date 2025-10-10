package com.kh.jsp.model.dao;

import com.kh.jsp.model.vo.Reply;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.kh.jsp.common.JDBCTemplate.*;

public class ReplyDao {

    // 댓글 등록
    public int insertReply(Connection conn, Reply r) throws SQLException {
        String sql = "INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER, CREATE_DATE, STATUS) " +
                     "VALUES(SEQ_RNO.NEXTVAL, ?, ?, ?, SYSDATE, 'Y')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, r.getReplyContent());
            pstmt.setInt(2, r.getRefBno());
            pstmt.setInt(3, r.getReplyWriter());
            return pstmt.executeUpdate();
        }
    }

    // 댓글 목록 조회
    public List<Reply> selectReplyList(Connection conn, int boardNo) throws SQLException {
        List<Reply> list = new ArrayList<>();
        String sql = "SELECT r.REPLY_NO, r.REPLY_CONTENT, r.REF_BNO, r.REPLY_WRITER, " +
                     "m.MEMBER_NAME AS REPLY_WRITER_NAME, r.CREATE_DATE " +
                     "FROM REPLY r JOIN MEMBER m ON r.REPLY_WRITER = m.MEMBER_NO " +
                     "WHERE r.REF_BNO = ? AND r.STATUS='Y' ORDER BY r.CREATE_DATE ASC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    Reply r = new Reply();
                    r.setReplyNo(rs.getInt("REPLY_NO"));
                    r.setReplyContent(rs.getString("REPLY_CONTENT"));
                    r.setRefBno(rs.getInt("REF_BNO"));
                    r.setReplyWriter(rs.getInt("REPLY_WRITER"));
                    r.setReplyWriterName(rs.getString("REPLY_WRITER_NAME"));
                    r.setCreateDate(rs.getDate("CREATE_DATE"));
                    list.add(r);
                }
            }
        }
        return list;
    }
}
