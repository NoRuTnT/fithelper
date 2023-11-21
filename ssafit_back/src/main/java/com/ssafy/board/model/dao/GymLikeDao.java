package com.ssafy.board.model.dao;

import com.ssafy.board.model.dto.GymLike;

public interface GymLikeDao {
	/**좋아요 표시하기*/ 
	public void insertGymLike(GymLike gymLike);
	/**좋아요 취소하기. 사용자 번호를 입력으로 받는다.*/
	public void deleteGymLike(int userId);
}
