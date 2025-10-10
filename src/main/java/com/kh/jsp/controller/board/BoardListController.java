package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Board> boardList = service.selectBoardList();
        request.setAttribute("boardList", boardList);
        request.getRequestDispatcher("/views/board/boardListView.jsp").forward(request, response);
    }
}
