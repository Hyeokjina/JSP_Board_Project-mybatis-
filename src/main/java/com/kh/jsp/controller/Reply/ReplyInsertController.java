package com.kh.jsp.controller.Reply;

import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/insertReply.re")
public class ReplyInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReplyService service = new ReplyService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String refBnoStr = request.getParameter("refBno");
        String replyWriterStr = request.getParameter("replyWriter");
        String content = request.getParameter("replyContent");

        if (refBnoStr == null || refBnoStr.isEmpty() ||
            replyWriterStr == null || replyWriterStr.isEmpty() ||
            content == null || content.trim().isEmpty()) {
            response.getWriter().print("0"); // 실패
            return;
        }

        int refBno = Integer.parseInt(refBnoStr);
        int replyWriter = Integer.parseInt(replyWriterStr);

        Reply r = new Reply();
        r.setRefBno(refBno);
        r.setReplyWriter(replyWriter);
        r.setReplyContent(content);

        int result = service.insertReply(r);

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().print(result); // 1이면 성공, 0이면 실패
    }
}
