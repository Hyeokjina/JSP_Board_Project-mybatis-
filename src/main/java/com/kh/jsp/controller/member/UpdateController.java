package com.kh.jsp.controller.member;

import java.io.IOException;

import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/update.me")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청된 회원정보 -> 정보수정 -> int -> 성공(mypage), 실패(error)
		
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member updateMember = Member.updateCreateMember(userId, phone, email, address, interest);
		
		updateMember = new MemberService().updateMember(updateMember);
		if(updateMember == null) { //업데이트 실패
			request.setAttribute("errorMsg", "회원정보 수정에 실패하였습니다");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", updateMember);
			session.setAttribute("alertMsg", "성공적으로 수정하였습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPage.me");
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