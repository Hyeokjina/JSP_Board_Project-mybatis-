package com.kh.jsp.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxReplyListController
 */
@WebServlet("/rlist.bo")
public class AjaxReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글목록을 DB에서 추출한다음 응답
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		ArrayList<Reply> list = new BoardService().selectReplyByBoardNo(boardNo);
		
//		String res = "{result : [";
//		for(Reply r : list) {
//			res += ("{" + "replyNo : " + r.getReplyNo() + ","
//					+ "replyContent : \"" + r.getReplyContent() + "\""
//					+ "}");
//		}
//		
//		res += "]}";
//		System.out.println(list);
//		
//		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().print(res);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}