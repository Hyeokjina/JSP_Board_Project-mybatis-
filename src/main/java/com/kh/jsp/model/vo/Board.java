package com.kh.jsp.model.vo;

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
	private int boardType;
	private int categoryNo;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private int count;
	private String createDate;
	private String status;
	
	//데이터 전달용 필드
	private String categoryName;
	private String memberId;
	private String thumbnailImg;
	
}