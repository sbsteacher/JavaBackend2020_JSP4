package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board4.vo.BoardCmtVO;

public class BoardCmtDAO {
	public static int insertCmt(BoardCmtVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " INSERT INTO t_board3_cmt"
				+ " (i_cmt, i_board, i_user, cmt)"
				+ " VALUES"
				+ " (seq_cmt.nextval, ?, ?, ?) ";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);			
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			ps.setString(3, param.getCmt());
			
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
	 
	public static List<BoardCmtVO> selectBoardCmtList(int i_board) {
		List<BoardCmtVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.i_cmt, A.i_user, A.cmt, A.r_dt  "
				+ " , B.nm as writerNm "
				+ " FROM t_board3_cmt A "
				+ " INNER JOIN t_user3 B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE i_board = ? "
				+ " ORDER BY i_cmt ASC ";
		
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_board);			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardCmtVO vo = new BoardCmtVO();
				vo.setI_cmt(rs.getInt("i_cmt"));
				vo.setI_user(rs.getInt("i_user"));
				vo.setCmt(rs.getNString("cmt"));
				vo.setR_dt(rs.getNString("r_dt"));
				vo.setWriterNm(rs.getNString("writerNm"));
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		
		return list;
	}
	
	public static void deleteCmt(BoardCmtVO param) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " DELETE FROM t_board3_cmt WHERE i_cmt = ? and i_user = ? ";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);			
			ps.setInt(1, param.getI_cmt());
			ps.setInt(2, param.getI_user());
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
