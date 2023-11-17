package com.ssafy.board.model.service;

public interface FollowService {
	
	/**1.선택한 사람을 팔로우하기*/
	public void insertFollow(int follower, int following);
	/**2.선택한 사람을 팔로우 취소하기*/
	public void deleteFollow(int follower, int following);
	
	
}
