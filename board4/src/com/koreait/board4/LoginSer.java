package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.UserDAO;
import com.koreait.board4.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/WEB-INF/jsp/login.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");
		
		System.out.println("cid : " + cid);
		System.out.println("cpw : " + cpw);
		
		UserVO param = new UserVO();
		param.setCid(cid);
		param.setCpw(cpw);
		
		//0, 1, 2, 3
		int result = UserDAO.login(param);
		if(result == 1) { //로그인 성공
			HttpSession hs = request.getSession();
			
			param.setCpw(null);
			hs.setAttribute("loginUser", param);
			response.sendRedirect("/boardList ");
			return;
		}
		
		String msg = "에러발생";
		switch(result) {
			case 2:
				msg = "아이디가 없습니다.";
				break;
			case 3:
				msg = "비밀번호를 확인해 주세요.";
				break;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("writedCid", cid);			
		doGet(request, response);
	}

}
