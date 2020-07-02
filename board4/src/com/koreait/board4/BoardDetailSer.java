package com.koreait.board4;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.BoardDAO;

@WebServlet("/boardDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();		
		if(hs.getAttribute("loginUser") == null) {
			response.sendRedirect("/login");
			return;
		}
		String typ = request.getParameter("typ");
		
		String err = request.getParameter("err");
		if(err != null) {
			String msg = "";
			switch(err) {
			case "1":
				msg = "삭제 실패";
				break;
			case "2":
				msg = "수정 실패";
				break;
			}
			
			request.setAttribute("msg",  msg);
		}
		
		int i_board = Integer.parseInt(request.getParameter("i_board"));		
		request.setAttribute("data", BoardDAO.selectBoard(i_board));
		
		String jsp = "/WEB-INF/jsp/boardDetail.jsp";
		if("mod".equals(typ)) {
			jsp = "/WEB-INF/jsp/boardRegmod.jsp";
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
	}


}
