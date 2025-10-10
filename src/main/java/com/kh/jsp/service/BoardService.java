package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Board;

public class BoardService {
    private final BoardDao dao = new BoardDao();

    public List<Board> selectBoardList() {
        Connection conn = getConnection();
        List<Board> list = dao.selectBoardList(conn);
        close(conn);
        return list;
    }

    public Board selectBoard(int boardNo) {
        Connection conn = getConnection();
        Board board = dao.selectBoard(conn, boardNo);
        close(conn);
        return board;
    }

    public int insertBoard(Board board) {
        Connection conn = getConnection();
        int result = dao.insertBoard(board, conn);
        if(result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    public int increaseReadCount(int boardNo) {
        Connection conn = getConnection();
        int result = dao.updateReadCount(conn, boardNo);
        if(result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }
    
    public int deleteBoard(int boardNo) {
        Connection conn = getConnection();
        int result = dao.deleteBoard(conn, boardNo);
        if(result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }
    
    public int updateBoard(Board board) {
        Connection conn = getConnection();
        int result = dao.updateBoard(conn, board);
        if(result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }


}
