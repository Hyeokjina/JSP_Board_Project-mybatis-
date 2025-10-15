package com.kh.jsp.service;

import com.kh.jsp.model.dao.ReplyDao;
import com.kh.jsp.model.vo.Reply;
import java.sql.Connection;
import java.util.List;
import static com.kh.jsp.common.JDBCTemplate.*;

public class ReplyService {
    private ReplyDao dao = new ReplyDao();

    public int insertReply(Reply r) {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = dao.insertReply(conn, r);
            if(result > 0) commit(conn);
            else rollback(conn);
        } catch(Exception e) {
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return result;
    }

    public List<Reply> selectReplyList(int boardNo) {
        Connection conn = getConnection();
        List<Reply> list = null;
        try {
            list = dao.selectReplyList(conn, boardNo);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }
}
