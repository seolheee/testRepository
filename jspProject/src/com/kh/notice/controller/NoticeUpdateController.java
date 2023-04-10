package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		//parameter도 int로 형변환 해줘야하나
		System.out.println(noticeNo);
		
		Notice n = new NoticeService().selectNotice(noticeNo);
		request.setAttribute("notice", n);
		//다시 읽어보기
		
		request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//int noticeNo = ((Notice)request.getAttribute("notice")).getNoticeNo();
		//왜 java.lang.NullPointerException ?
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//System.out.println(noticeNo);
		
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		
		int result = new NoticeService().updateNotice(n);
		
		if(result>0) { //수정한 글번호를 이용해서 상세보기 페이지 보내기
			request.getSession().setAttribute("alertMsg", "공지사항 수정에 성공하였습니다.");
			//request.setAttribute("errorMsg", "수정에 성공하였습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+noticeNo);
			//해당 디테이리뷰를 보는 방법
		}else {
			request.setAttribute("errorMsg", "공지사항수정에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
