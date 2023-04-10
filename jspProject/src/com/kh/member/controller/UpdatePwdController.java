package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class UpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class UpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//hidden 타입으로 숨겨서 보낸 데이터 꺼내보기 //방법1
		//String userId = request.getParameter("userId");
		//System.out.println("숨겨왔나요 : "+userId);
		
		HttpSession session = request.getSession(); //방법2
		//Member m = (Member)session.getAttribute("loginUser");
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		//System.out.println("세션에 있나요 : "+userId);
		
		String updatePwd = request.getParameter("updatePwd");
		
		
		//int result = new MemberService().updatePwd(userId,updatePwd);
//		if(result>0){
//			session.setAttribute("alertMsg", "비밀번호 변경이 완료되었습니다.");
//			response.sendRedirect(request.getContextPath()+"/myPage.me");
//		}else {
//			request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
//			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
//		}
		
		Member updateMem = new MemberService().updatePwd(userId,updatePwd);
		
		if(updateMem == null) {
			request.setAttribute("errorMsg", "비밀번호 수정 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}else {
			session.setAttribute("alertMsg", "비밀번호 수정 완료. 변경된 비밀번호로 재로그인 해주세요.");
			session.setAttribute("loginUser", updateMem);
			//재요청
			response.sendRedirect(request.getContextPath());
		}
		
				
		
	}

}
