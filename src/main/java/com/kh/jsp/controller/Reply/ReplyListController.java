package com.kh.jsp.controller.Reply;

import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ReplyListController
 */
@WebServlet("/listReply.re")
public class ReplyListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReplyService service = new ReplyService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyListController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardNoStr = request.getParameter("boardNo");
        if(boardNoStr == null || boardNoStr.isEmpty()) {
            response.getWriter().print("[]"); // 빈 배열 반환
            return;
        }

        int boardNo = Integer.parseInt(boardNoStr);
        List<Reply> list = service.selectReplyList(boardNo);

        response.setContentType("application/json; charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < list.size(); i++) {
            Reply r = list.get(i);
            sb.append("{");
            sb.append("\"replyNo\":").append(r.getReplyNo()).append(",");
            sb.append("\"replyContent\":\"").append(r.getReplyContent().replace("\"","\\\"")).append("\",");
            sb.append("\"replyWriterName\":\"").append(r.getReplyWriterName().replace("\"","\\\"")).append("\",");
            sb.append("\"createDate\":\"").append(r.getCreateDate()).append("\"");
            sb.append("}");
            if(i != list.size()-1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
