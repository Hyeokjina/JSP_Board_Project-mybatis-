package com.kh.jsp.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageInfo {
	private int currentPage; //지금 보여줄 페이지(사용자가 요청한 페이지)
	private int listCount; //현재 총 게시글 수
	private int pageLimit; //페이지 버튼을 몇개 보여줄 것인가
	private int boardLimit; //한 페이지에 데이터를 몇개 보여줄 것인가?
	
	private int maxPage; //가장 마지막페이지가 몇인가?(총 페이지 수)
	private int startPage; //하단에 보여질 페이징 버튼의 시작 수
	private int endPage; //하단에 보여질 페이징 버튼의 마지막 수
	public PageInfo(int currentPage, int listCount, int pageLimit, int boardLimit) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		/*
		 * maxPage : 우리 데이터의 마지막 페이지는 몇페이지인가?
		 * 
		 * 100			10			->	10
		 * 107			10			->  11
		 * 
		 * 총 게시글 수 / 한 페이지에 보여줄 게시글 수 -> 올림
		 */
		
		this.maxPage = (int)Math.ceil((double)this.listCount/this.boardLimit);
		
		/*
		 * startPage : 페이지 버큰의 시작 수
		 * 
		 * currentPage       pageLimit       startPage
		 * 	    1                5                1
		 *      7                5                6
		 *      11				 10				  11
		 *      
		 * startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		 */
		
		this.startPage = ((this.currentPage - 1) / this.pageLimit) * this.pageLimit + 1;
		
		/*
		 * endPage : 페이지버튼의 마지막 수
		 * 
		 * pageLimit -> 10일때
		 * 
		 * startPage: 1 -> 10
		 * startPage: 11 -> 20
		 * startPage: 21 -> 25
		 * 
		 * endPage : startPage + pageLimit - 1;
		 * */
		
		this.endPage = this.startPage + this.pageLimit - 1;
		
		// 만약 maxPage가 25라면?
		this.endPage = this.endPage > this.maxPage ? this.maxPage : this.endPage;
	}
	
	
}