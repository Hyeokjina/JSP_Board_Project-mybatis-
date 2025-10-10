package com.kh.jsp.controller.member;

import java.io.IOException;

import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/insert.me")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//member 추가
		
		
		//전달받은 데이터를 추출
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone"); // "010~" || ""
		String email = request.getParameter("email");  // "~~" || ""
		String address = request.getParameter("address"); // "~~" || ""
		String[] interestArr = request.getParameterValues("interest"); // ["sports...] || null
		
		//String[] -> string
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = Member.insertCreateMember(userId, userPwd, userName, phone, email, address, interest);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) { //가입성공
			request.getSession().setAttribute("alertMsg", "성공적으로 회원가입을 완료하였습니다.");
			
			response.sendRedirect(request.getContextPath());
		} else { //가입실패
			request.setAttribute("errorMsg", "회원가입에 실패하였습니다.");
			request.getRequestDispatcher("views/common/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}