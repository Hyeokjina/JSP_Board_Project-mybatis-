package com.kh.jsp.model.vo;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private int boardWriter;        // 작성자 번호
    private String boardWriterName; // 작성자 이름
    private int categoryNo;         // 카테고리 번호
    private String categoryName;    // 카테고리 이름
    private int boardType;          // 글 타입
    private int readCount;          // 조회수
    private Date createDate;
    private String fileName;

    public static Board createBoard(String title, String content, int writerNo, int categoryNo, int boardType) {
        Board board = new Board();
        board.setBoardTitle(title);
        board.setBoardContent(content);
        board.setBoardWriter(writerNo);
        board.setCategoryNo(categoryNo);
        board.setBoardType(boardType);
        return board;
    }
}