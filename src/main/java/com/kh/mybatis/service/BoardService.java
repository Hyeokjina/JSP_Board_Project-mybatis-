package com.kh.mybatis.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.model.vo.Category;
import com.kh.mybatis.model.vo.Attachment;
import com.kh.mybatis.common.Template;
import com.kh.mybatis.common.vo.PageInfo;
import com.kh.mybatis.model.dao.BoardDao;
import com.kh.mybatis.model.vo.Board;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	private Category category = new Category();
	private Attachment attachment = new Attachment();
	
	public int selectAllBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = boardDao.selectAllBoardCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}
	
	public ArrayList<Board> selectAllBoard(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = boardDao.selectAllBoard(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}
	
	public ArrayList<Category> selectAllCategory(){
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Category> categroyList = boardDao.selectAllCategory(sqlSession);
	
		sqlSession.close();
		
		return categroyList;
		
	}
	
	public int insertBoard(Board b, Attachment at) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = 0;

		 try {
		        // 게시글 등록
		        b.setBoardType(1);
		        result = boardDao.insertBoard(sqlSession, b);

		        // 첨부파일이 있으면 등록
		        if(at != null) {
		            result *= boardDao.insertAttachment(sqlSession, at);
		        }

		        // 성공 시 commit, 실패 시 rollback
		        if(result > 0) {
		            sqlSession.commit();
		        } else {
		            sqlSession.rollback();
		        }

		    } catch (Exception e) {
		        sqlSession.rollback();
		        e.printStackTrace();
		    } finally {
		        sqlSession.close();
		    }

		    return result;
		}

}