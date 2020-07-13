package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.BoardCmtDAO;
import com.koreait.board4.vo.BoardCmtVO;
import com.koreait.board4.vo.UserVO;

@WebServlet("/boardCmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		int i_cmt = Integer.parseInt(request.getParameter("i_cmt"));
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_cmt(i_cmt);
		param.setI_user(loginUser.getI_user());
		
		BoardCmtDAO.deleteCmt(param);
		
		response.sendRedirect("/boardDetail?i_board=" + i_board);		
	}

	//등록&수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		String cmt = request.getParameter("cmt");
		
		System.out.println("i_board: " + strI_board);
		System.out.println("cmt: " + cmt);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user());
		param.setCmt(cmt);
		
		int result = BoardCmtDAO.insertCmt(param);
		
		response.sendRedirect("/boardDetail?i_board=" + strI_board);
	}

}








