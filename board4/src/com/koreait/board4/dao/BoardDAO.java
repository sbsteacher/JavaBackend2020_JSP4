package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		String sql = " INSERT INTO t_board3 " + " (i_board, title, ctnt, i_user) " + " VALUES "
				+ " (seq_board3.nextval, ?, ?, ?) ";

		String cols[] = { "i_board" };

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql, cols);
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_user());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
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

	public static BoardListModel selectBoard(BoardListModel param) {
		BoardListModel result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT   A.i_board, A.title, A.r_dt, A.ctnt, A.cnt, B.i_user, B.nm as userNm "
				+ " , nvl(C.i_user, 0) as likeUser "
				+ " FROM t_board3 A INNER JOIN t_user3 B  ON A.i_user = B.i_user "
				+ " LEFT JOIN t_board3_like C ON A.i_board = C.i_board AND C.i_user = ? "
				+ " WHERE A.i_board = ? ";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_user());
			ps.setInt(2, param.getI_board());			
			rs = ps.executeQuery();

			if (rs.next()) {
				result = new BoardListModel();

				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				String r_dt = rs.getNString("r_dt");
				int i_user = rs.getInt("i_user");
				String userNm = rs.getNString("userNm");
				int cnt = rs.getInt("cnt");
				int likeUser = rs.getInt("likeUser");

				result.setI_board(param.getI_board());
				result.setTitle(title);
				result.setCtnt(ctnt);
				result.setR_dt(r_dt);
				result.setI_user(i_user);
				result.setUserNm(userNm);
				result.setCnt(cnt);
				result.setLikeUser(likeUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}

		return result;
	}

	public static List<BoardListModel> selectBoardList(BoardListModel param) {
		List<BoardListModel> list = new ArrayList();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT A.i_board, A.title, A.r_dt, A.cnt, B.i_user, B.nm as userNm"
				+ "  , NVL(C.i_user, 0) as likeUser " //추가
				+ " FROM t_board3 A INNER JOIN t_user3 B  ON A.i_user = B.i_user "
				+ " LEFT JOIN t_board3_like C ON A.i_board = C.i_board AND C.i_user = ? " //추가
				+ " ORDER BY i_board DESC ";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_user()); //추가
			
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardListModel model = new BoardListModel();

				model.setI_board(rs.getInt("i_board"));
				model.setTitle(rs.getNString("title"));
				model.setR_dt(rs.getNString("r_dt"));
				model.setI_user(rs.getInt("i_user"));
				model.setUserNm(rs.getNString("userNm"));
				model.setCnt(rs.getInt("cnt"));
				model.setLikeUser(rs.getInt("likeUser")); //추가

				list.add(model);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		return list;
	}

	public static void updCntAdd(int i_board) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " UPDATE t_board3 SET cnt = cnt + 1 " + " WHERE i_board = ? ";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);			
			ps.setInt(1, i_board);			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
	}

	public static int modBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " UPDATE t_board3 " 
						+ " SET title = ? " 
						+ " , ctnt = ? "
						+ " , m_dt = sysdate "
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

		String sql = " DELETE FROM t_board3_like WHERE i_board = ? ";
		String sql2 = " DELETE FROM t_board3 WHERE i_board = ? AND i_user = ? ";

		try {
			con = DbCon.getCon();
			con.setAutoCommit(false);
			
			//sql
			ps = con.prepareStatement(sql);  //A
			ps.setInt(1, param.getI_board());
			result = ps.executeUpdate();
			ps.close();
			System.out.println("좋아요테이블 삭제 레코드 수 : " + result);
			
			//sql2
			ps = con.prepareStatement(sql2); //B
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			result = ps.executeUpdate();
			
			if(result == 0) {
				con.rollback();
			} else {
				con.commit();	
			}			
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			if(con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
		} finally {			
			DbCon.close(con, ps);
		}

		return result;
	}
}
