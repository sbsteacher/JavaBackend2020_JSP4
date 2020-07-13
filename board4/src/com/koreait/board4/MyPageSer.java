package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.dao.UserDAO;
import com.koreait.board4.vo.UserChangeVO;
import com.koreait.board4.vo.UserVO;
import com.koreait.board4.vo.UserVO;

@WebServlet("/myPage")
public class MyPageSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Utils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		String typ = request.getParameter("typ");
		
		String jsp = "/WEB-INF/jsp/";
		switch(typ) {
		case "1": //비밀번호 바꾸기
			jsp += "changePw.jsp"; //"/WEB-INF/jsp/changePw.jsp"
			break;
		case "2": //이름 바꾸기
			jsp += "?.jsp";
			break;
		case "3": //프사 보기(바꾸기 가능)
			jsp += "?.jsp";
			break;
		case "4": //상태메시지 바꾸기
			jsp += "?.jsp";
			break;
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typ = request.getParameter("typ");
		UserVO loginUser = Utils.getLoginUser(request);
		
		
		try {
			switch(typ) {
				case "1": //비밀번호 수정			
					procTyp1(loginUser, request, response);
					break;
				case "2"://이름 바꾸기
					//procTyp2();
					break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//proc 비밀번호 수정
	private void procTyp1(UserVO loginUser, HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String currentPw = request.getParameter("currentPw");
		String changePw = request.getParameter("changePw");
		
		UserChangeVO param = new UserChangeVO();
		param.setI_user(loginUser.getI_user());
		param.setChangePw(changePw);
		param.setCurrentPw(currentPw);
		
		int result = UserDAO.changePw(param);
		if(result == 1) {
			Utils.logout(request);
			response.sendRedirect("/login");
			return;
		}
		
		String msg = "";
		switch(result) {
		case 0:
			msg = "기존 비밀번호를 확인해 주세요";
			break;
		case 2:
			msg = "통신 에러 발생";
			break;
		}	
		
		request.setAttribute("msg",  msg);
		doGet(request, response);
	}

}













