package com.koreait.board4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.dao.UserDAO;

@WebServlet("/chkId")
public class CheckIdSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		System.out.println("cid : " + cid);
		
		int result = UserDAO.chkId(cid);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(String.format("{\"isExist\": %d}", result));
		out.flush();
		
	}


}
