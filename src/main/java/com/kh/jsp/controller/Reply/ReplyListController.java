package com.kh.jsp.controller.Reply;

import com.kh.jsp.model.vo.Reply;
import com.kh.jsp.service.ReplyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listReply.re")
public class ReplyListController extends HttpServlet {
	private ReplyService service = new ReplyService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		List<Reply> list = service.selectReplyList(boardNo);

		response.setContentType("application/json; charset=UTF-8");
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0; i<list.size(); i++){
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
}
