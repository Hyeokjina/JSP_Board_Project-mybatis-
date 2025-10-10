package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/enroll.bo")
public class BoardEnrollController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();

    @Override
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("loginMember") == null) {
            // 로그인 페이지로 이동 (컨트롤러 URL)
            response.sendRedirect(request.getContextPath() + "/login.me");
            return;
        }
        request.getRequestDispatcher("/views/board/enrollForm.jsp").forward(request, response);
    }
}
