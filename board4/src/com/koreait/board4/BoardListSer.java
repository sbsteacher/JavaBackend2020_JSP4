package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.dao.BoardDAO;
import com.koreait.board4.vo.BoardListModel;
import com.koreait.board4.vo.UserVO;

@WebServlet("/boardList")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private int recordCnt = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser"); //추가
		if(loginUser == null) { //수정
			response.sendRedirect("/login");
			return;
		}
		
		String strPage = request.getParameter("page");//추가
		int page = 1;//추가
		if(strPage != null) {//추가
			page = Integer.parseInt(strPage);//추가
		}//추가
		System.out.println("page : " + page);//추가
		request.setAttribute("totalPageCnt", BoardDAO.selectTotalPageCnt(recordCnt));//추가
		
		int endIdx = page * recordCnt; //추가
		int startIdx = endIdx - recordCnt; //추가
		
		BoardListModel param = new BoardListModel(); 
		param.setI_user(loginUser.getI_user()); 
		param.setStartIdx(startIdx);//추가
		param.setEndIdx(endIdx);//추가
		
		request.setAttribute("data", BoardDAO.selectBoardList(param));
		
		String jsp = "/WEB-INF/jsp/boardList.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ddd");
		HttpSession hs = request.getSession();
		hs.invalidate();
		response.sendRedirect("/login");
	}

}
