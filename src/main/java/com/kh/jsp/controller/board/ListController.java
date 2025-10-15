package com.kh.jsp.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.jsp.common.vo.PageInfo;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.bo")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//board목록을 가져와서 응답페이지로 전달
		//모든 board의 목록이 아닌 당장 보여줄 페이지의 목록만 가져오기
		
		//-------------------페이징 처리-----------------------------
		int currentPage = request.getParameter("cpage") != null ? 
							Integer.parseInt(request.getParameter("cpage")) : 1; //지금 보여줄 페이지(사용자가 요청한 페이지)
		int listCount = new BoardService().selectAllBoardCount();//현재 총 게시글 수
		int pageLimit = 5; //페이지 버튼을 몇개 보여줄 것인가
		int boardLimit = 5; //한 페이지에 데이터를 몇개 보여줄 것인가?
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit);
		
		ArrayList<Board> list = new BoardService().selectAllBoard(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/board/listView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}