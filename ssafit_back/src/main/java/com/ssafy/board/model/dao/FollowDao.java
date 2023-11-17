package com.ssafy.board.model.dao;

import org.apache.ibatis.annotations.Param;

public interface FollowDao {
	/**1.선택한 사람을 팔로우하기*/
	public void insertFollow(@Param("follower")int follower, @Param("following")int following);
	/**2.선택한 사람을 팔로우 취소하기*/
	public void deleteFollow(@Param("follower")int follower, @Param("following")int following);
	
	
	
}
