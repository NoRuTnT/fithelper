package com.ssafy.board.model.service;

import com.ssafy.board.model.dto.GymLike;

public interface GymLikeService {
	
	/**좋아요 표시하기*/ 
	void addGymLike(GymLike gymLike);
	/**좋아요 취소하기. 사용자 번호를 입력으로 받는다.*/
	void cancelGymLike(int userId);
}
