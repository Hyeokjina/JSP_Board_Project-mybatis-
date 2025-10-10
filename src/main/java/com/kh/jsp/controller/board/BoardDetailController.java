package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardNoStr = request.getParameter("boardNo");

        if(boardNoStr == null || boardNoStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/list.bo");
            return;
        }

        int boardNo = Integer.parseInt(boardNoStr);
        int result = service.increaseReadCount(boardNo);
        Board board = service.selectBoard(boardNo);

        if(board == null) {
            request.setAttribute("errorMsg", "존재하지 않는 게시글입니다.");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        Integer loginUserNo = null;
        if(session != null && session.getAttribute("loginMember") != null) {
            loginUserNo = ((Member) session.getAttribute("loginMember")).getMemberNo();
        }

        request.setAttribute("board", board);
        request.setAttribute("loginUserNo", loginUserNo); // JSP에서 작성자 비교용
        request.getRequestDispatcher("/views/board/boardDetailView.jsp").forward(request, response);
    }

}
