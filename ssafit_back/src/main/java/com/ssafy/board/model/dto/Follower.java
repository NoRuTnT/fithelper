package com.ssafy.board.model.dto;
/**사용자가 팔로우하는 정보를 담고 있는 DTO*/ 
public class Follower {
	private int followId; // DTO 고유번호
	private int follower; // 팔로우하는 주체
	private int following; // 팔로우를 하는 대상
	
	public Follower() {
	}

	public Follower(int followId, int follower, int following) {
		super();
		this.followId = followId;
		this.follower = follower;
		this.following = following;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follower [followId=" + followId + ", follower=" + follower + ", following=" + following + "]";
	}
	
}
