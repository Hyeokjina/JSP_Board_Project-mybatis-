package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class BoardEnrollController
 */
@WebServlet("/enroll.bo")
public class BoardEnrollController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("loginMember") == null) {
            
        	request.setAttribute("errorMsg", "해당기능은 회원가입 및 로그인 후 사용가능합니다.");
            request.getRequestDispatcher("/views/common/error2.jsp").forward(request, response);

            return;
        }
        request.getRequestDispatcher("/views/board/enrollForm.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if(loginMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.me");
            return;
        }

        int writerNo = loginMember.getMemberNo();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String categoryParam = request.getParameter("category");

        if(categoryParam == null || categoryParam.isEmpty()) {
            request.setAttribute("errorMsg", "카테고리를 선택해주세요.");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            return;
        }

        int categoryNo = Integer.parseInt(categoryParam);

        Board board = new Board();
        board.setBoardTitle(title);
        board.setBoardContent(content);
        board.setBoardWriter(writerNo);
        board.setCategoryNo(categoryNo);
        board.setBoardType(1);

        int result = service.insertBoard(board);

        if(result > 0) {
            response.sendRedirect(request.getContextPath() + "/list.bo");
        } else {
            request.setAttribute("errorMsg", "게시글 등록 실패");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
        }
    }
}
