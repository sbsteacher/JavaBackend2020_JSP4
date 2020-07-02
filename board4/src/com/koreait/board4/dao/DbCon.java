package com.koreait.board4.dao;

import java.sql.*;

public class DbCon {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String id = "korea01";
	private static final String pw = "1234";
	
	public static Connection getCon() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");				
		Connection con = DriverManager.getConnection(url, id, pw);
		System.out.println("연결 성공!");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) {}
		}
		if(ps != null) {
			try { ps.close(); } catch (SQLException e) {}
		}
		if(con != null) {
			try { con.close(); } catch (SQLException e) {}
		}		
	}
}


