package com.kh.jsp.controller.Reply;

import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/replyInsert.bo")
public class ReplyInsertController extends HttpServlet {
    private ReplyService service = new ReplyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int userNo = Integer.parseInt(request.getParameter("userNo"));
        String content = request.getParameter("content");

        Reply r = new Reply();
        r.setRefBno(boardNo);
        r.setReplyWriter(userNo);
        r.setReplyContent(content);

        int result = service.insertReply(r);

        if(result > 0) {
            List<Reply> replyList = service.selectReplyList(boardNo);
            StringBuilder sb = new StringBuilder();
            for(Reply reply : replyList) {
                sb.append("<tr>");
                sb.append("<td>").append(reply.getReplyWriterName()).append("</td>");
                sb.append("<td>").append(reply.getReplyContent()).append("</td>");
                sb.append("<td>").append(reply.getCreateDate()).append("</td>");
                sb.append("</tr>");
            }
            response.getWriter().print(sb.toString());
        } else {
            response.getWriter().print("fail");
        }
    }
}
