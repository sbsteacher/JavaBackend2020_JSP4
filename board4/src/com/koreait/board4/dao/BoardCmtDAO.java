package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
