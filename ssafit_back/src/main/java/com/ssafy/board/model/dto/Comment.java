package com.ssafy.board.model.dto;

import io.swagger.annotations.ApiModel;

public class Comment {
	private int commentId;
	private int userId;
	private int boardId;
	private String content;
	private String regDate;
	
	public Comment() {
	}

	public Comment(String content) {
		super();
		this.content = content;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId + ", boardId=" + boardId + ", content="
				+ content + ", regDate=" + regDate + "]";
	}


	
	
}
