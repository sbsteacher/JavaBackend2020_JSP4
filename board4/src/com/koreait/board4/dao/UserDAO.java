package com.koreait.board4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.board4.vo.UserChangeVO;
import com.koreait.board4.vo.UserVO;

public class UserDAO {
	//회원가입 성공 1, 실패 0 
	public static int join(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO t_user3 "
				+ " (i_user, cid, cpw, nm) "
				+ " VALUES "
				+ " (seq_user.nextval, ?, ?, ?) ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, param.getCid());
			ps.setNString(2, param.getCpw());
			ps.setNString(3, param.getNm());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
	
	public static int chkId(String cid) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		String sql = " SELECT count(cid) FROM t_user3 WHERE cid = ? ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, cid);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		
		return result;
	}
	
	//1:로그인성공, 2:아이디없음, 3:비밀번호 틀림, 0:에러발생
	public static int login(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String sql = " SELECT i_user, cpw, nm FROM t_user3 WHERE cid = ? ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, param.getCid());
			rs = ps.executeQuery();			
			if(rs.next()) {
				String dbCpw = rs.getNString("cpw");				
				//result = dbCpw.equals(param.getCpw()) ? 1 : 3;
				
				if(dbCpw.equals(param.getCpw())) {
					int i_user = rs.getInt("i_user");
					String nm = rs.getNString("nm");
					
					param.setI_user(i_user);
					param.setNm(nm);
					
					result = 1;
				} else {
					result = 3;
				}
				
			} else {
				result = 2;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}		
		return result;
	}
	
	
	public static int changePw(UserChangeVO param) {
		int result = 2; //2:에러 발생, 1:수정완료, 0:기존 비밀번호 틀림
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_user3 "
				+ " SET cpw = ? "
				+ " WHERE i_user = ? AND cpw = ? ";
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setNString(1, param.getChangePw());
			ps.setInt(2, param.getI_user());
			ps.setNString(3, param.getCurrentPw());
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
}














