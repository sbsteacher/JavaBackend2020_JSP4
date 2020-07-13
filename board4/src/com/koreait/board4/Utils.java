package com.koreait.board4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.board4.vo.UserVO;


public class Utils {
	public static boolean isLogout(HttpServletRequest request) {
		return getLoginUser(request) == null;
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute("loginUser");
	}
	
	public static void logout(HttpServletRequest request) {
		request.getSession().invalidate();		
	}
}
