package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board4.vo.BoardListModel;
import com.koreait.board4.vo.BoardVO;

public class BoardDAO {
	public static int regBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i_board = 0;
		
		String sql = " INSERT INTO t_board3 "
				+ " (i_board, title, ctnt, i_user) "
				+ " VALUES "
				+ " (seq_board3.nextval, ?, ?, ?) ";
		
		String cols[] = {"i_board"}; 
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql, cols);
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3,  param.getI_user());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				i_board = rs.getInt(1);				
				System.out.println("i_board : " + i_board);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		return i_board;
	}
	

	
	public static BoardListModel selectBoard(int i_board) {
		BoardListModel result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT " + 
				"    A.i_board, A.title, A.r_dt, A.ctnt " + 
				"    , B.i_user, B.nm as userNm " + 
				" FROM t_board3 A " + 
				" INNER JOIN t_user3 B " + 
				" ON A.i_user = B.i_user " + 
				" WHERE A.i_board = ? ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_board);			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = new BoardListModel();
								
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				String r_dt = rs.getNString("r_dt");
				int i_user = rs.getInt("i_user");
				String userNm = rs.getNString("userNm");
				
				result.setI_board(i_board);
				result.setTitle(title);
				result.setCtnt(ctnt);
				result.setR_dt(r_dt);
				result.setI_user(i_user);
				result.setUserNm(userNm);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		
		return result;
	}
	
	public static List<BoardListModel> selectBoardList() {
		List<BoardListModel> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.i_board, A.title, A.r_dt " + 
				"    , B.i_user, B.nm as userNm " + 
				" FROM t_board3 A " + 
				" INNER JOIN t_user3 B " + 
				" ON A.i_user = B.i_user " + 
				" ORDER BY i_board DESC ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardListModel model = new BoardListModel();
				
				model.setI_board(rs.getInt("i_board"));
				model.setTitle(rs.getNString("title"));
				model.setR_dt(rs.getNString("r_dt"));
				model.setI_user(rs.getInt("i_user"));
				model.setUserNm(rs.getNString("userNm"));
				
				list.add(model);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		return list;
	}
	
	public static int modBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_board3 "
				+ " SET title = ? "
				+ " , ctnt = ? "
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";
				
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_board());
			ps.setInt(4, param.getI_user());
			result = ps.executeUpdate();
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
	
	public static int delBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_board3 WHERE i_board = ? AND i_user = ? ";
				
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			result = ps.executeUpdate();
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
}















