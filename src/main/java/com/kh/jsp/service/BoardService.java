package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.common.vo.PageInfo;
import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Category;
import com.kh.jsp.model.vo.Reply;

public class BoardService {
	
	public int selectAllBoardCount(){
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectAllBoardCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectAllBoard(PageInfo pi){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectAllBoard(conn, pi);
		close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectThumnailList(){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumnailList(conn);
		close(conn);
		
		return list;
	}
	
	public int deleteReply(int replyNo){
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteReply(conn, replyNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, boardNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public Board selectBoardByBoardNo(int boardNo) {
		Connection conn = getConnection();
		
		Board board = new BoardDao().selectBoardByBoardNo(conn, boardNo);
	
		close(conn);
		return board;
	}
	
	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		return at;
	}
	
	public ArrayList<Attachment> selectAttachmentList(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		return list;
	}
	
	public ArrayList<Category> selectAllCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> categroyList = new BoardDao().selectAllCategory(conn);
	
		close(conn);
		return categroyList;
	}
	
	public int updateBoard(Board b, Attachment at) {
		//새로운 첨부파일이 존재하지 않을 때  -> (b, null) -> board update
		//새로운 첨부파일이 존재하고 기존첨부파일이 존재할 때 -> (b, at(fileNo)) -> board update, attachment update
		//새로운 첨부파일이 존재하고 기존첨부파일이 존재하지 않을 때 -> (b, at(refBoardNo)) -> board update, attachment insert
	
		Connection conn = getConnection();
		BoardDao boardDao = new BoardDao();
		
		int result = boardDao.updateBoard(conn, b);
		
		if(at != null) {
			if(at.getFileNo() != 0) { //기존첨부파일이 존재할 때
				result *= boardDao.updateAttachment(conn, at);
			} else { //기존첨부파일이 존재하지 않을 때
				result *= boardDao.insertNewAttachment(conn, at);
			}
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		
		b.setBoardType(1);
		int result = bDao.insertBoard(conn, b);
		
		if(at != null) {
			result *= bDao.insertAttachment(conn, at);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int insertBoard(Board b, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		
		b.setBoardType(2);
		int result = bDao.insertBoard(conn, b);
		result *= bDao.insertAttachment(conn, list);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public ArrayList<Reply> selectReplyByBoardNo(int boardNo){
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyByBoardNo(conn, boardNo);
		
		close(conn);
		return list;
	}
	

}