package com.kh.jsp.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jsp.common.JDBCTemplate;
import com.kh.jsp.common.vo.PageInfo;
import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Category;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		super();
		
		String path = JDBCTemplate.class.getResource("/db/sql/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board> selectAllBoard(Connection conn, PageInfo pi){
		//select -> ResultSet(여러개) -> ArrayList
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllBoard");
		
		try {
			/*
			 * currentPage : 1 -> 1~10    boardLimit : 10
			 * currentPage : 2 -> 11~20
			 * currentPage : 3 -> 21~30
			 */
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setCategoryName(rset.getString("CATEGORY_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setMemberId(rset.getString("MEMBER_ID"));
				b.setCount(rset.getInt("COUNT"));
				b.setCreateDate(rset.getString("CREATE_DATE"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		//boardNo에 해당하는 board -> update -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public Board selectBoardByBoardNo(Connection conn, int boardNo){
		//select -> ResultSet(한개) -> Board
		Board b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardByBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setCategoryNo(rset.getInt("CATEGORY_No"));
				b.setCategoryName(rset.getString("CATEGORY_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemberId(rset.getString("MEMBER_ID"));
				b.setCreateDate(rset.getString("CREATE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}
	
	public int selectAllBoardCount(Connection conn) {
		//select -> ResultSet(한개) -> int
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllBoardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public Attachment selectAttachment(Connection conn, int boardNo){
		//select -> ResultSet(한개) -> Board
		Attachment at = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return at;
	}
	
	public ArrayList<Category> selectAllCategory(Connection conn){
		//select -> ResultSet(여러개) -> ArrayList
		ArrayList<Category> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category category = new Category(
							rset.getInt("CATEGORY_NO"),
							rset.getString("CATEGORY_NAME")
						);
				
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int updateBoard(Connection conn, Board board) {
		//boardNo에 해당하는 board -> update -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCategoryNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertBoard(Connection conn, Board board) {
		//새로운 board -> insert -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCategoryNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getBoardWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		//새로운 Attachment -> insert -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("insertAttachment");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
		//새로운 Attachment -> insert -> int(1 또는 0)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("insertNewAttachment");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getRefBoardNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateAttachment(Connection conn, Attachment at) {
		//Attachment -> update -> int(1 또는 0)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("updateAttachment");		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}