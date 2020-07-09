package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.BoardDAO;
import com.koreait.board4.vo.*;

@WebServlet("/boardReg")
public class BoardRegSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//등록화면
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/WEB-INF/jsp/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);		
	}
	
	//처리(reg, mod 같이 쓸거임)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		System.out.println("i_board : " + strI_board);
		System.out.println("title : " + title);
		System.out.println("ctnt : " + ctnt);
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		//아이디, 비밀번호 사용하려고
		//EL식 사용하려고
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(loginUser.getI_user());
		
		if("".equals(strI_board)) { //등록
			int i_board = BoardDAO.regBoard(param);
			response.sendRedirect("/boardDetail?i_board=" + i_board);
			//보드디테일 화면으로 이동
			
			//리스트화면으로 이동
			//response.sendRedirect("/boardList");
			return;
		} 
		//수정
		int i_board = Integer.parseInt(strI_board);
		param.setI_board(i_board);
		
		int result = BoardDAO.modBoard(param);
		String qStr = "";
		if(result == 0) {
			qStr = "&err=2";
		}
		String url = String.format("/boardDetail?i_board=%d%s", i_board, qStr);
		response.sendRedirect(url);

	}

}
