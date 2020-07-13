package com.koreait.board4.vo;

public class BoardListModel extends BoardVO {
	private String userNm;
	private int likeUser;
	
	private int startIdx;
	private int endIdx;
	
	public int getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}

	public int getEndIdx() {
		return endIdx;
	}

	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}

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
