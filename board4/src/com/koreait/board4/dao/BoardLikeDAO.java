package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.koreait.board4.vo.BoardLikeVO;

public class BoardLikeDAO {
	public static int enableLike(BoardLikeVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " INSERT INTO t_board3_like "
				+ " (i_board, i_user) "
				+ " VALUES "
				+ " (?, ?) ";

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
	
	public static int disableLike(BoardLikeVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " DELETE FROM t_board3_like "
				+ " WHERE i_board = ? AND i_user = ? ";

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
