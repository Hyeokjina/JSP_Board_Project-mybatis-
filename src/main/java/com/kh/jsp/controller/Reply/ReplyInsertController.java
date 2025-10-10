package com.kh.jsp.controller.Reply;

import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/insertReply.re")
public class ReplyInsertController extends HttpServlet {
	private ReplyService service = new ReplyService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int refBno = Integer.parseInt(request.getParameter("refBno"));
		int replyWriter = Integer.parseInt(request.getParameter("replyWriter"));
		String content = request.getParameter("replyContent");

		Reply r = new Reply();
		r.setRefBno(refBno);
		r.setReplyWriter(replyWriter);
		r.setReplyContent(content);

		int result = service.insertReply(r);

		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print(result); // 1이면 성공, 0이면 실패
	}
}
