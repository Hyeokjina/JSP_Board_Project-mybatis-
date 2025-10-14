package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.kh.jsp.model.dao.AttachmentDao;
import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;

public class AttachmentService {

    private final AttachmentDao dao = new AttachmentDao();

    // 게시글 번호(boardNo)에 해당하는 첨부파일 조회
    public Attachment selectAttachment(int boardNo) {
        Connection conn = getConnection();
        Attachment attachment = dao.selectAttachment(boardNo, conn);
        close(conn);
        return attachment;
    }
}