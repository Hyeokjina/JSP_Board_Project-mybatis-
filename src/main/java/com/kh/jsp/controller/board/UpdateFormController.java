package com.kh.jsp.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Category;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet(name = "updateForm.bo", urlPatterns = { "/updateForm.bo" })
public class UpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		BoardService boardService = new BoardService();
		
		ArrayList<Category> categories = boardService.selectAllCategory();
		Board b = boardService.selectBoardByBoardNo(boardNo);
		Attachment at = boardService.selectAttachment(boardNo);
		
		request.setAttribute("categories", categories);
		request.setAttribute("board", b);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/board/updateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}