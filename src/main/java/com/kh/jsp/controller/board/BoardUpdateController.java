package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
    private final BoardService service = new BoardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        Board board = new Board();
        board.setBoardNo(boardNo);
        board.setBoardTitle(title);
        board.setBoardContent(content);
        board.setCategoryNo(categoryNo);

        int result = service.updateBoard(board);

        if(result > 0) {
            response.sendRedirect(request.getContextPath() + "/detail.bo?boardNo=" + boardNo);
        } else {
            request.setAttribute("errorMsg", "게시글 수정 실패");
            request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
        }
    }
}
