package com.ssafy.board.model.dto;
/** 게시글에 좋아요를 누른 사람을 나타내기 위한 DTO*/
public class BoardLike {
	private int boardLikeId; // 이 DTO의 식별번호
	private int userId; // 게시글에 좋아요를 단 사용자 식별번호
	private int boardId; // 게시글 번호
	
	public BoardLike() {
	}

	public BoardLike(int boardLikeId, int userId, int boardId) {
		super();
		this.boardLikeId = boardLikeId;
		this.userId = userId;
		this.boardId = boardId;
	}

	public int getBoardLikeId() {
		return boardLikeId;
	}

	public void setBoardLikeId(int boardLikeId) {
		this.boardLikeId = boardLikeId;
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

	@Override
	public String toString() {
		return "boardLike [boardLikeId=" + boardLikeId + ", userId=" + userId + ", boardId=" + boardId + "]";
	}
	
}
