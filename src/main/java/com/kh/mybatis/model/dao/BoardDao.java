package com.kh.mybatis.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.model.vo.Category;
import com.kh.mybatis.common.vo.PageInfo;
import com.kh.mybatis.model.vo.Attachment;
import com.kh.mybatis.model.vo.Board;

public class BoardDao {
	public int selectAllBoardCount(SqlSession sqlSession) {
		return sqlSession.selectOne("BoardMapper.selectAllBoardCount");
	}
	
	public ArrayList<Board> selectAllBoard(SqlSession sqlSession, PageInfo pi) {
		//mybatis에서 자체적으로 페이징 처리를 위해 RowBounds라는 class를 제공한다.
		//offset : 몇개의 게시글을 건너 뛰고 조회할 것인가?
		//boardLimit : 몇개의 게시글을 가지고 올 것인가?
		//51~60 = 50개를 건너뛰고 10개를 가지고 오고싶어
		
		//한페이지를 보여줄 boardLimit 10
		// 1 -> 0 -> 0
		// 2 -> 11~10 -> 10
		// 3 -> 21~30 -> 20
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		ArrayList<Board> list = (ArrayList)sqlSession.selectList("BoardMapper.selectAllBoard", null, rowBounds);
		
		return list;
	}
	
	public ArrayList<Category> selectAllCategory(SqlSession sqlSession){
		return (ArrayList) sqlSession.selectList("BoardMapper.selectAllCategory");
	}
	
	public int insertBoard(SqlSession sqlSession, Board b) {
	    return sqlSession.insert("BoardMapper.insertBoard", b);
	}

	public int insertAttachment(SqlSession sqlSession, Attachment at) {
	    return sqlSession.insert("BoardMapper.insertAttachment", at);
	}
}
