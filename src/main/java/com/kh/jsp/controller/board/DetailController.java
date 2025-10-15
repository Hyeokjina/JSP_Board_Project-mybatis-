package com.kh.jsp.controller.board;

import java.io.IOException;

import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailController
 */
@WebServlet("/detail.bo")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//board정보를 조회해서 detailView.jsp을 응답
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		BoardService boardService = new BoardService();
		
		//board의 조회수 1 증가
		int result = boardService.increaseCount(boardNo);
		Board board = boardService.selectBoardByBoardNo(boardNo);
		
		if(result > 0 && board != null) {
			//at조회 -> request담기
			Attachment at = boardService.selectAttachment(board.getBoardNo());
			
			request.setAttribute("board", board);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/board/detailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "정상적인 접근이 아닙니다.");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}