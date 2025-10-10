package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.*;
import com.kh.jsp.model.dao.ReplyDao;
import com.kh.jsp.model.vo.Reply;

import java.sql.Connection;
import java.util.List;

public class ReplyService {
    private ReplyDao dao = new ReplyDao();

    public int insertReply(Reply r) {
        Connection conn = getConnection();
        int result = dao.insertReply(conn, r);
        if(result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    public List<Reply> selectReplyList(int boardNo) {
        Connection conn = getConnection();
        List<Reply> list = dao.selectReplyList(conn, boardNo);
        close(conn);
        return list;
    }
}
