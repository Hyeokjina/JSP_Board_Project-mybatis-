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
public class Attachment {
	private int fileNo;
	private int refBoardNo;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private String status;
}