package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.BoardDAO;
import com.koreait.board4.vo.BoardVO;
import com.koreait.board4.vo.UserVO;

@WebServlet("/boardDel")
public class BoardDelSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user());
		
		int result = BoardDAO.delBoard(param); //0:삭제 실패, 1:삭제 완료
		if(result == 0) { //디테일
			
			String url = String.format("/boardDetail?i_board=%d&err=1", i_board);
			response.sendRedirect(url);
			return;
		} 
		
		response.sendRedirect("/boardList");
	}
}
