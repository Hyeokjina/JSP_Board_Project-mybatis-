package com.kh.jsp.controller.Reply;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;

@WebServlet("/replyList.bo")
public class ReplyListController extends HttpServlet {
    private ReplyService service = new ReplyService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        List<Reply> list = service.selectReplyList(boardNo);

        StringBuilder sb = new StringBuilder();
        for(Reply r : list) {
            sb.append("<tr>");
            sb.append("<td colspan='2'>").append(r.getReplyContent()).append("</td>");
            sb.append("<td>").append(r.getCreateDate()).append("</td>");
            sb.append("</tr>");
        }
        response.getWriter().print(sb.toString());
    }
}
