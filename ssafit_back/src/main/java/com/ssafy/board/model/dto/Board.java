package com.ssafy.board.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value="게시판 바구니", description = "게시글 정보")
public class Board {
	private int boardId;
	private int userId;
	private String title;
	private String content;
	private int viewCnt;
	private String regDate;
	private int likeCount; // 이 게시글의 좋아요 개수
	
	public Board() {
	}

	public Board(int boardId, int userId, String title, String content, int viewCnt, String regDate, int likeCount) {
		super();
		this.boardId = boardId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.likeCount = likeCount;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", likeCount=" + likeCount + "]";
	}

	
}
