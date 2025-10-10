package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/login.me");
            return;
        }

        String boardNoStr = request.getParameter("boardNo");
        if(boardNoStr == null || boardNoStr.isEmpty()) {
            request.setAttribute("errorMsg", "잘못된 접근입니다.");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            return;
        }

        int boardNo = Integer.parseInt(boardNoStr);
        Board board = service.selectBoard(boardNo);

        if(board == null || board.getBoardWriter() != ((Member) session.getAttribute("loginMember")).getMemberNo()) {
            request.setAttribute("errorMsg", "수정 권한이 없습니다.");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("board", board);
        request.getRequestDispatcher("/views/board/boardUpdateForm.jsp").forward(request, response);
    }
}
