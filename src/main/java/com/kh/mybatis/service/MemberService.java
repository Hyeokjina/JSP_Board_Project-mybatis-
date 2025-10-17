package com.kh.mybatis.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.Template;
import com.kh.mybatis.model.dao.MemberDao;
import com.kh.mybatis.model.vo.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public Member loginMember(String userId, String userPwd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Member m = new MemberDao().loginMember(sqlSession, userId, userPwd);
		
		sqlSession.close();
		
		return m;
	}
	
	public int idCheck(String checkId) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int count = memberDao.idCheck(sqlSession, checkId);
		
		sqlSession.close();
		
		return count;
	}
	
	public int insertMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = memberDao.insertMember(sqlSession, m);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return result;
	}
}