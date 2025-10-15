package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.jsp.model.dao.MemberDao;
import com.kh.jsp.model.vo.Member;

public class MemberService {
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(m, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(userId, userPwd, conn);
		
		close(conn);
		
		return m;
	}
	
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(m, conn);
		
		Member updateMember = null;
		if(result > 0) {
			commit(conn);
			updateMember = new MemberDao().selectMemberByUserId(m.getMemberId(), conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}
	
	public Member updateMemberPwd(String memberId, String updatePwd) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMemberPwd(memberId, updatePwd, conn);
		
		Member updateMember = null;
		if(result > 0) {
			commit(conn);
			updateMember = new MemberDao().selectMemberByUserId(memberId, conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}
	
	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(memberId, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int idCheck(String checkId) {
		Connection conn = getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		
		return count;
	}
}