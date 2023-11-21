package com.ssafy.board.model.dto;
/** 운동장소에 좋아요를 누른 사람을 나타내기 위한 DTO*/
public class GymLike {
	private int gymLikeId; // 이 DTO의 식별번호
	private int userId; // 게시글에 좋아요를 단 사용자 식별번호
	private int gymId; // 게시글 번호
	
	public GymLike() {
	}

	public GymLike(int gymLikeId, int userId, int gymId) {
		super();
		this.gymLikeId = gymLikeId;
		this.userId = userId;
		this.gymId = gymId;
	}

	public int getGymLikeId() {
		return gymLikeId;
	}

	public void setGymLikeId(int gymLikeId) {
		this.gymLikeId = gymLikeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	@Override
	public String toString() {
		return "GymLike [gymLikeId=" + gymLikeId + ", userId=" + userId + ", gymId=" + gymId + "]";
	}

	
	
}
