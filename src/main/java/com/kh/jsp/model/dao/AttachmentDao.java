package com.kh.jsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jsp.model.vo.Attachment;

public class AttachmentDao {
	public Attachment selectAttachment(int boardNo, Connection conn) {
	    String sql = "SELECT FILE_NO, REF_BNO, ORIGIN_NAME, CHANGE_NAME, FILE_PATH, UPLOAD_DATE, FILE_LEVEL, STATUS "
	               + "FROM ATTACHMENT "
	               + "WHERE REF_BNO = ? AND STATUS = 'Y'";

	    Attachment at = null;

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, boardNo);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                at = new Attachment();
	                at.setFileNo(rs.getInt("FILE_NO"));
	                at.setRefBoardNo(rs.getInt("REF_BNO"));
	                at.setOrginName(rs.getString("ORIGIN_NAME"));
	                at.setChangeName(rs.getString("CHANGE_NAME"));
	                at.setFilePath(rs.getString("FILE_PATH"));
	                at.setUploadDate(rs.getDate("UPLOAD_DATE"));
	                at.setFileLevel(rs.getInt("FILE_LEVEL"));
	                at.setStatus(rs.getString("STATUS"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return at;
	}
}
