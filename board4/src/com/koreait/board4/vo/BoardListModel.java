package com.koreait.board4.vo;

public class BoardListModel extends BoardVO {
	private String userNm;
	private int likeUser;
	
	public int getLikeUser() {
		return likeUser;
	}

	public void setLikeUser(int likeUser) {
		this.likeUser = likeUser;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
}
